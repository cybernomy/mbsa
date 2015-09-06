/* RptEditorInput.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.report.deployer.support.editor;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.report.deployer.DeployerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;

/**
 * Класс, реализующий {@link IEditorInput} для формы редактирования отчёта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: RptEditorInput.java,v 1.2 2007/04/11 07:00:35 poroxnenko Exp $
 */
public class RptEditorInput extends StandartEditorInput<RptMainTransfer> {

  public static final String FORM_PART = "report.form.edit.part";
  /**
   * Ссылка на файл с шаблоном
   */
  private IFile template;

  public RptEditorInput(RptMainTransfer rmt, IFile template, boolean createNew) {
    super(rmt, createNew);
    this.template = template;
  }

  @Override
  public String getEditorName() {
    return String.format(DeployerPlugin.getDefault().getString(FORM_PART),
        object.code);
  }

  @Override
  public String getToolTip() {
    return object.code.trim();
  }

  @Override
  public boolean isMatch(RptMainTransfer obj) {
    return object.id == obj.id;
  }

  public IFile getTemplate() {
    return template;
  }

  public void setTemplate(IFile template) {
    this.template = template;
  }

}
