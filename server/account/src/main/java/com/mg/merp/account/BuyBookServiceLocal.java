/*
 * BuyBookServiceLocal.java
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

import com.mg.merp.account.model.BuyBook;

/**
 * 
 * @author leonova
 * @version $Id: BuyBookServiceLocal.java,v 1.2 2006/08/23 10:29:44 leonova Exp $
 */
public interface BuyBookServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<BuyBook, Integer>
{
	/**
	 * тип папки для книги покупок
	 */
	final static short FOLDER_PART = 26;
	
   public void makeBuyBook( java.util.Date dateFrom,java.util.Date dateTill,int folderId,int contractorId,java.lang.String docType,java.lang.String docNumber ) throws com.mg.framework.api.ApplicationException;

}
