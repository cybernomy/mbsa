/**
 * AbstractCreateDocumentByPatternStrategy.java
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
package com.mg.merp.document.generic;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.CreateDocumentByPatternStrategy;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

/**
 * Базовая реализация стратегии создания документа по образцу
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractCreateDocumentByPatternStrategy.java,v 1.1 2007/09/28 12:04:20 safonov Exp $
 */
public abstract class AbstractCreateDocumentByPatternStrategy implements
		CreateDocumentByPatternStrategy {
	protected Logger logger = ServerUtils.getLogger(getClass());

	/**
	 * реализация создания документа на основании образца
	 * 
	 * @param document	документ
	 * @param documentPattern	образец
	 * @return	документ
	 */
	protected abstract DocHead doCreateDocument(DocHead document, DocHeadModel documentPattern);
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentByPatternStrategy#prepareDocument(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel)
	 */
	public final DocHead createDocument(DocHead document, DocHeadModel documentPattern) {
		return doCreateDocument(document, documentPattern);
	}

}
