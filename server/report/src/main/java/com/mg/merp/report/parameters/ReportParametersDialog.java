/* ReportParameterDialog.java
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
package com.mg.merp.report.parameters;

import com.mg.framework.api.ui.DialogForm;

import java.util.Map;

/**
 * Интерфейс формы запроса параметров отчёта
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: ReportParametersDialog.java,v 1.2 2008/03/27 08:12:15 safonov Exp $
 */
public interface ReportParametersDialog extends DialogForm {

  /**
   * получить парамтеры отчета
   *
   * @return список параметров отчета
   */
  Map<String, ReportParameter> getParameters();

  /**
   * установить параметры отчета
   *
   * @param params список параметров отчета
   */
  void setParameters(Map<String, ReportParameter> params);

}
