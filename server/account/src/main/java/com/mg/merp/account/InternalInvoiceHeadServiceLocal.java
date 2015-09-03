/*
 * InternalInvoiceHeadServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.document.model.DocHead;

/**
 * 
 * @author leonova
 * @version $Id: InternalInvoiceHeadServiceLocal.java,v 1.3 2006/09/20 10:43:40 safonov Exp $
 */
public interface InternalInvoiceHeadServiceLocal
   extends com.mg.merp.document.GoodsDocument<DocHead, Integer, InternalInvoiceHeadModelServiceLocal, InternalInvoiceSpecServiceLocal>
{

	/**
	 * тип папки для внутренних накладных
	 */
	final static short FOLDER_PART = 19;
	
	/**
	 * docsection для внутренних накладных
	 */
	final static short DOCSECTION = 7;
}
