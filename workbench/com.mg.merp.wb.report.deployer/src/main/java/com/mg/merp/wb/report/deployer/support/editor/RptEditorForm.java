/* RptEditorForm.java
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
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.editor.StandartEditorForm;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;
import com.mg.merp.wb.report.deployer.DeployerPlugin;

import java.rmi.ConnectException;

/**
 * Форма редактирования отчёта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: RptEditorForm.java,v 1.6 2007/11/04 14:35:01 safonov Exp $
 */
public class RptEditorForm extends StandartEditorForm<RptEditorPage, RptMainTransfer> {

  public static final String FORM_PART_ERROR = "report.form.edit.page.error.create";
  public static final String EDITOR_ID = "com.mg.merp.wb.report.deployer.ReportEditor";
  private static final String FORM_PAGE = "report.form.edit.page";
  private static final String REPORT_CREATE_ERROR = "report.create.error";

  private static final String REPORT_EDIT_ERROR = "tmpl.menu.edit.save.error";

  @Override
  protected BusinessObjectPlugin<RptMainTransfer> getBusinessObjectTool() {
    return DeployerPlugin.getDefault();
  }

  @Override
  protected RptEditorPage getEditorPage() {
    return new RptEditorPage(this);
  }

  @Override
  protected void hookBusinessObjectActException(boolean isNew, RuntimeException e) {
    String str = REPORT_EDIT_ERROR;
    if (isNew)
      str = REPORT_CREATE_ERROR;
    Dialogs.openError(DeployerPlugin.getDefault().getString(str), e.getLocalizedMessage()
        , DeployerPlugin.ID, e);
  }

  @Override
  protected void hookServerConnectException(boolean isNew, ConnectException e) {
    String str = REPORT_EDIT_ERROR;
    if (isNew)
      str = REPORT_CREATE_ERROR;
    Dialogs.openError(DeployerPlugin.getDefault().getString(str), DeployerPlugin
        .getDefault().getString(UiPlugin.CHECK_SERVER), DeployerPlugin.ID, e);
  }

  @Override
  protected void pagePostCreating() {
    setPageText(0, DeployerPlugin.getDefault().getString(FORM_PAGE));
  }

}
