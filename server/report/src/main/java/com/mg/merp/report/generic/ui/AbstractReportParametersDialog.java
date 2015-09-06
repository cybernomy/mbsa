/**
 * AbstractReportParametersDialog.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.report.generic.ui;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.report.parameters.ReportParameter;
import com.mg.merp.report.parameters.ReportParametersDialog;

import java.util.Map;

/**
 * Базовый класс контроллера формы запроса параметров отчета
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractReportParametersDialog.java,v 1.2 2008/03/28 06:26:29 safonov Exp $
 */
public class AbstractReportParametersDialog extends DefaultDialog implements
    ReportParametersDialog {
  protected Map<String, ReportParameter> params;

  /* (non-Javadoc)
   * @see com.mg.merp.report.parameters.ReportParametersDialog#getParameters()
   */
  public Map<String, ReportParameter> getParameters() {
    return this.params;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.report.parameters.ReportParametersDialog#setParameters(java.util.Map)
   */
  public void setParameters(Map<String, ReportParameter> params) {
    this.params = params;
  }

}
