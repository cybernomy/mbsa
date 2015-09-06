/* NotEnoughDisposalPositionsException.java
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

import com.mg.framework.api.BusinessException;
import com.mg.merp.warehouse.support.Messages;

/**
 * ИС "Недостаточно позиций для списания со склада"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: NotEnoughDisposalPositionsException.java,v 1.2 2007/03/26 13:31:18 poroxnenko Exp
 *          $
 */
@javax.ejb.ApplicationException
public class NotEnoughDisposalPositionsException extends
    BusinessException {

  public NotEnoughDisposalPositionsException(String code, Throwable cause) {
    super(Messages.getInstance().getMessage(Messages.NOTENOUGH_DISPOSAL_POSITIONS, new Object[]{code}), cause);
  }

  public NotEnoughDisposalPositionsException(String code) {
    super(Messages.getInstance().getMessage(Messages.NOTENOUGH_DISPOSAL_POSITIONS, new Object[]{code}));
  }

}
