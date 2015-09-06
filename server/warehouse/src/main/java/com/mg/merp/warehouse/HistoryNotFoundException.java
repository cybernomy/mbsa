/* HistoryNotFoundException.java
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
package com.mg.merp.warehouse;

import com.mg.framework.api.InternalException;
import com.mg.merp.warehouse.support.Messages;

/**
 * ИС "Невозможно откатить документ - не найдена история"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: HistoryNotFoundException.java,v 1.2 2007/03/26 13:31:18 poroxnenko Exp $
 */
@javax.ejb.ApplicationException
public class HistoryNotFoundException extends InternalException {

  public HistoryNotFoundException(Throwable cause) {
    super(Messages.getInstance().getMessage(Messages.HISTORY_NOT_FOUND), cause);
  }

  public HistoryNotFoundException() {
    super(Messages.getInstance().getMessage(Messages.HISTORY_NOT_FOUND));
  }
}
