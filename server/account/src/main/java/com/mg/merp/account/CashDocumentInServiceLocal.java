/*
 * CashDocumentInServiceLocal.java
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

import com.mg.merp.account.model.CashDocument;

/**
 * 
 * @author leonova
 * @version $Id: CashDocumentInServiceLocal.java,v 1.3 2006/09/20 10:43:40 safonov Exp $
 */
public interface CashDocumentInServiceLocal
   extends com.mg.merp.document.Document<CashDocument, Integer, CashDocumentModelInServiceLocal>
{
	/**
	 * тип папки для приходных кассовых документов
	 */
	final static short FOLDER_PART = 11;
	
	/**
	 * docsection для приходных кассовых документов
	 */
	final static short DOCSECTION = 5;
}
