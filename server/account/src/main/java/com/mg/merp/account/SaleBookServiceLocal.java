/*
 * SaleBookServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.SaleBook;

/**
 * 
 * @author leonova
 * @version $Id: SaleBookServiceLocal.java,v 1.2 2006/08/23 10:30:02 leonova Exp $
 */
public interface SaleBookServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<SaleBook, Integer>
{
	/**
	 * тип папки для книги продаж
	 */
	final static short FOLDER_PART = 27;
	
   public void makeSaleBook( java.util.Date dateFrom,java.util.Date dateTill,int folderId,int contractorId,java.lang.String docType,java.lang.String docNumber ) throws com.mg.framework.api.ApplicationException;

}
