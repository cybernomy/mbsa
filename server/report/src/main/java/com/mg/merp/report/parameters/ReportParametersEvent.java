/* ReportParameterEvent.java
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

import com.mg.framework.api.ui.UIEvent;

import java.util.Map;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ReportParametersEvent.java,v 1.2 2008/03/27 08:12:15 safonov Exp $
 * @deprecated
 */
@Deprecated
public class ReportParametersEvent extends UIEvent {

  public ReportParametersEvent(Map<String, ReportParameter> source) {
    super(source);
  }

  @SuppressWarnings("unchecked")
  public Map<String, ReportParameter> getParameters() {
    return (Map<String, ReportParameter>) getSource();
  }

}
