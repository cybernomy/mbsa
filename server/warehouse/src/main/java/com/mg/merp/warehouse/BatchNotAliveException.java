/* BatchNotAliveException.java
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
 * ИС "Невозможно откатить документ - было списание с партии"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: BatchNotAliveException.java,v 1.2 2007/03/26 13:31:18 poroxnenko Exp $ 
 */
@javax.ejb.ApplicationException
public class BatchNotAliveException extends BusinessException {

	public BatchNotAliveException(Throwable cause) {
		super(Messages.getInstance().getMessage(Messages.BATCH_NOT_ALIVE), cause);
	}
	
	public BatchNotAliveException() {
		super(Messages.getInstance().getMessage(Messages.BATCH_NOT_ALIVE));
	}
}
