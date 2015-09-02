/*
 * BankDocumentInServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.BankDocument;

/**
 * Бизнес-компонент "Входящие банковские документы"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentInServiceLocal.java,v 1.4 2007/11/08 12:19:22 sharapov Exp $
 */
public interface BankDocumentInServiceLocal extends com.mg.merp.document.Document<BankDocument, Integer, BankDocumentModelInServiceLocal> {
	
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/account/BankDocumentIn"; //$NON-NLS-1$
	
	/**
	 * тип папки для входящих банковских документов
	 */
	final static short FOLDER_PART = 9;
	
	/**
	 * docsection для входящих банковских документов
	 */
	final static short DOCSECTION = 2;
	
	/**
	 * Рассчитать и установить суммы НДС
	 * @param bankDocument - входящий банковский документ
	 */
	void calculateNdsSum(BankDocument bankDocument);
	
	/**
	 * Рассчитать и установить сумму документа
	 * @param bankDocument - входящий банковский документ
	 */
	void calculateDocSum(BankDocument bankDocument);
	
}
