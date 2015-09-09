/* OldReportException.java
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
package com.mg.merp.report;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.report.support.Messages;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: OldReportException.java,v 1.1 2007/04/11 06:46:06 poroxnenko Exp $
 */
public class OldReportException extends ApplicationException {

  public OldReportException() {
    super(Messages.getInstance().getMessage(Messages.REPORT_WAS_CHANGED)); //$NON-NLS-1$
  }
}
