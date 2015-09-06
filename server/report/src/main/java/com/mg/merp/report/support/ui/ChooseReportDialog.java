/*
 * ChooseReportDialog.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.report.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.report.model.RptMain;

import java.util.List;

/**
 * Контроллер формы выбора доступных отчётов
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: ChooseReportDialog.java,v 1.6 2008/03/27 08:03:06 safonov Exp $
 */
public class ChooseReportDialog extends DefaultDialog {
  public static final String FORM_NAME = "com.mg.merp.report.ChooseReportDialog";

  private final static String[] fieldList = new String[]{"RptName", "Comment", "Code"}; //$NON-NLS-1$ $NON-NLS-2$

  private DefaultTableController reportsList;

  private List<RptMain> reports;

  public ChooseReportDialog() {
    reportsList = new DefaultTableController(new ReportsListModel());
  }

  /**
   * Устанавливает список отчётов
   *
   * @param reports список доступных отчетов
   */
  public void setReports(List<RptMain> reports) {
    this.reports = reports;
    reportsList.getModel().load();
  }

  /**
   * возвращает выбранный отчет
   *
   * @return выбранный отчёт или <code>null</code>
   */
  public RptMain getReport() {
    RptMain[] rptMains = ((ReportsListModel) reportsList.getModel()).getSelectedEntities();
    if (rptMains.length != 0)
      return rptMains[0];
    else
      return null;
  }

  /**
   * Модель таблицы доступных отчётов
   */
  private class ReportsListModel extends DefaultEntityListTableModel<RptMain> {

    @Override
    protected void doLoad() {
      setEntityList(reports, fieldList);
    }

  }

}
