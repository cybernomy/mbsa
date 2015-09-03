/*
 * DocumentNumberStrategyFactory.java
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
package com.mg.merp.document.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocumentNumberStrategy;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;

/**
 * Фабрика стратегий формирования номера документов
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentNumberStrategyFactory.java,v 1.2 2007/03/23 15:54:32 safonov Exp $
 */
public class DocumentNumberStrategyFactory {

	/**
	 * создание стратегии
	 * 
	 * @param docHead	сущность документа
	 * @return	стратегия
	 */
	public static DocumentNumberStrategy createStrategy(DocHead docHead) {
		DocType docType = null;
		if (docHead.getDocType() != null)
			docType = ServerUtils.getPersistentManager().find(DocType.class, docHead.getDocType().getId());
		if (docType == null || docType.getNumberingAlgorithm() == null)
			return new DefaultDocumentNumberStrategy();
		else
			return new BAiDocumentNumberStrategy(docType.getNumberingAlgorithm());
	}

}
