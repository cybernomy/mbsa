/*
 * InvoiceHeadInServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.InvoiceHead;

/**
 * 
 * @author leonova
 * @version $Id: InvoiceHeadInServiceLocal.java,v 1.3 2006/09/20 11:02:09 safonov Exp $
 */
public interface InvoiceHeadInServiceLocal
   extends com.mg.merp.document.GoodsDocument<InvoiceHead, Integer, InvoiceHeadModelInServiceLocal, InvoiceSpecInServiceLocal>
{
	/**
	 * тип папки для входящих накладных
	 */
	final static short FOLDER_PART = 45;
	
	/**
	 * docsection для входящих накладных
	 */
	final static short DOCSECTION = 23;
}
