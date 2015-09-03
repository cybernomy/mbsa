/*
 * InvoiceHeadModelServiceLocal.java
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
package com.mg.merp.retail;

import com.mg.merp.document.DocumentPattern;
import com.mg.merp.retail.model.RtlInvoiceHeadModel;

/**
 * 
 * @author leonova
 * @version $Id: InvoiceHeadModelServiceLocal.java,v 1.3 2006/09/20 10:58:15 safonov Exp $
 */
public interface InvoiceHeadModelServiceLocal
   extends DocumentPattern<RtlInvoiceHeadModel, Integer>
{
	/**
	 * тип папки для образцов разничных документов
	 */
	final static short FOLDER_PART = 13201;
}
