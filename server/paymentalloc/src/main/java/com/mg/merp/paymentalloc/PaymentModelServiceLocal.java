/*
 * PaymentModelServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.paymentalloc;

import com.mg.merp.paymentalloc.model.Payment;

/**
 * 
 * @author leonova
 * @version $Id: PaymentModelServiceLocal.java,v 1.3 2007/05/04 06:55:55 arychkov Exp $
 */
public interface PaymentModelServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<Payment, Integer>
{
	/**
	 * тип папки для образцов записей журнала платажей
	 */
	final static short FOLDER_PART = 13301;

	final static String LOCAL_SERVICE_NAME= "merp/paymentalloc/PaymentModel";
}
