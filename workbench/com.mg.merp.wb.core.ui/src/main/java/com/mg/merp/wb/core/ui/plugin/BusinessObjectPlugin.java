/* BusinessObjectPlugin.java
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
package com.mg.merp.wb.core.ui.plugin;

import com.mg.merp.wb.core.ui.editor.StandartEditorForm;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.core.ui.view.StandartBrowserView;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Класс-предок для плугинов управления бизнес объектами<br> T-Класс наполнение бизнес объекта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessObjectPlugin.java,v 1.2 2007/05/07 13:05:06 poroxnenko Exp $
 */
public abstract class BusinessObjectPlugin<T> extends MerpUIPlugin {

  /**
   * Открывает форму редактирования бизнес-объекта
   *
   * @param input данные бизнес-объекта
   * @return Форма редактирования бизнес-объекта
   */
  public static <EI extends StandartEditorInput> StandartEditorForm openEditor(
      EI input, String editorId) {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    if (window != null) {
      IWorkbenchPage page = window.getActivePage();
      try {
        return (StandartEditorForm) page.openEditor(input, editorId);
      } catch (PartInitException e) {
        return null;
      }
    }
    return null;
  }

  /**
   * Удалённое добавление объекта в базу
   *
   * @param bo объекта
   * @return объект с новой временной меткой или NULL, в случае ошибки
   */
  abstract public T addBusinessObject(T bo) throws Exception;

  /**
   * Удаление объектов из базы
   *
   * @param keys список ключей объектов, подлежащих удалению
   * @return код выполнения. 0 - успешно. -1 - ошибка
   */
  abstract public void deleteBusinessObjectsList(Integer[] ids)
      throws Exception;

  /**
   * Удалённое изменение объекта
   *
   * @param bo объект
   * @return объект с новой временной меткой, NULL-в случае ошибки
   */
  abstract public T editBusinessObject(T bo) throws Exception;

  /**
   * Удалённое получение списка объектов
   *
   * @param query Маска. Формат допустимый для предложения "like" в EJBQL(Символы '*' автоматически
   *              заменяются на '%', а '?' на '_')
   * @return список объектов или NULL, в случае ошибки
   */
  abstract public T[] synchronize(String query) throws Exception;

  public StandartBrowserView getView() {
    return (StandartBrowserView) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().findView(getViewId());
  }

  /**
   * @return ID вида бизнес-объекта
   */
  public abstract String getViewId();

}
