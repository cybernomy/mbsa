/* StandartEditorForm.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.wb.core.ui.editor;

import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import java.rmi.ConnectException;

/**
 * Стандартная форма редактирования
 *
 * EP-формы редактирования, наследники StandartEditorPage<br> T-класс, представляющий собой
 * содержание формы редактирования
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartEditorForm.java,v 1.1 2006/12/29 15:19:41 poroxnenko Exp $
 */
public abstract class StandartEditorForm<EP extends StandartEditorPage, T>
    extends FormEditor {

  public static final String FORM_PART_ERROR = "form.edit.page.error.create";

  private EP editorPage = getEditorPage();

  @Override
  protected void addPages() {
    try {
      addPage(editorPage);
    } catch (PartInitException e) {
      Dialogs.openError(null, UiPlugin.getDefault().getString(
          FORM_PART_ERROR), UiPlugin.ID, e);
    }
    pagePostCreating();
  }

  /**
   * сохранение формы редактирования
   */
  @Override
  public void doSave(IProgressMonitor monitor) {
    BusinessObjectPlugin<T> businessObj = getBusinessObjectTool();
    StandartEditorInput<T> input = ((StandartEditorInput<T>) getEditorInput());
    T obj = input.getData();

    boolean isNew = input.isCreateNew();

    try {
      if (isNew)
        obj = businessObj.addBusinessObject(obj);
      else
        obj = businessObj.editBusinessObject(obj);

      //после успешного выполнения закрываем форму
      close(false);
      //обновляем вид
      businessObj.getView().refresh();
    } catch (java.rmi.ConnectException e) {
      hookServerConnectException(isNew, e);
    } catch (RuntimeException e) {
      hookBusinessObjectActException(isNew, e);
    } catch (Exception e) {
      Dialogs.openError(UiPlugin.getDefault().getString(
          UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.getDefault()
          .getString(UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.ID, e);
    }

  }

  @Override
  public void doSaveAs() {
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  /**
   * @return форму редактирования бизнес объекта
   */
  protected abstract EP getEditorPage();

  /**
   * инициализация формы редактирования
   */
  protected abstract void pagePostCreating();

  protected abstract void hookServerConnectException(boolean isNew, ConnectException e);

  protected abstract void hookBusinessObjectActException(boolean isNew, RuntimeException e);

  /**
   * @return плагин, отвечающий за управление бизнес компонентом
   */
  protected abstract BusinessObjectPlugin<T> getBusinessObjectTool();

}
