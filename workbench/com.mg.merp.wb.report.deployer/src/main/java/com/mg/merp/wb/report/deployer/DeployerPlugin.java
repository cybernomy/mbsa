/* DeployerPlugin.java
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
package com.mg.merp.wb.report.deployer;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;
import com.mg.merp.wb.report.deployer.ui.RptView;

import org.osgi.framework.BundleContext;

import java.util.ResourceBundle;

/**
 * Точка входа в модуль управления отчётами
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: DeployerPlugin.java,v 1.8 2007/04/11 07:02:03 poroxnenko Exp $
 */
public class DeployerPlugin extends BusinessObjectPlugin<RptMainTransfer> {

  public static final String ID = "com.mg.merp.wb.report.deployer";
  public static final String CHECK_SERVER_MESSAGE = "server.check.message";
  private static final String RESOURCE_NAME = "com.mg.merp.wb.report.deployer.messages";
  private static final String REPORT_SERVICE_NAME = "merp:service=RptBIRTDeployerService";
  private static DeployerPlugin plugin;

  /**
   * The constructor.
   */
  public DeployerPlugin() {
    plugin = this;
  }

  /**
   * Returns the shared instance.
   */
  public static DeployerPlugin getDefault() {
    return plugin;
  }

  /**
   * This method is called upon plug-in activation
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
  }

  /**
   * This method is called when the plug-in is stopped
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
  }

  /**
   * Удалённое разворачивание шаблона на сервере приложений
   *
   * @param rmt отчёт
   * @return изменённый отчёт, с новой временной меткой, пустой отчёт в случае изменеия отчёта в
   * базе третьей стороной и NULL, если отчёт с таким же кодом и идентификатором отсутствует
   */
  public RptMainTransfer persistTemplate(RptMainTransfer rmt)
      throws Exception {
    return (RptMainTransfer) invoke(REPORT_SERVICE_NAME,
        "persistTemplate", new Object[]{rmt}, new String[]{RptMainTransfer.class.getName()}); //$NON-NLS-1$
  }

  @Override
  public RptMainTransfer addBusinessObject(RptMainTransfer bo) throws Exception {
    return (RptMainTransfer) invoke(REPORT_SERVICE_NAME,
        "addReport", new Object[]{bo}, new String[]{RptMainTransfer.class.getName()}); //$NON-NLS-1$
  }

  @Override
  public void deleteBusinessObjectsList(Integer[] ids) throws Exception {
    invoke(REPORT_SERVICE_NAME, "deleteReportList", new Object[]{ids}, new String[]{Integer[].class.getName()}); //$NON-NLS-1$
  }

  @Override
  public RptMainTransfer editBusinessObject(RptMainTransfer bo) throws Exception {
    return (RptMainTransfer) invoke(REPORT_SERVICE_NAME,
        "editReport", new Object[]{bo}, new String[]{RptMainTransfer.class.getName()}); //$NON-NLS-1$
  }

  @Override
  public String getViewId() {
    return RptView.ID;
  }

  @Override
  public RptMainTransfer[] synchronize(String query) throws Exception {
    return (RptMainTransfer[]) invoke(
        REPORT_SERVICE_NAME,
        "getReports", new Object[]{query}, new String[]{String.class.getName()}); //$NON-NLS-1$
  }
}
