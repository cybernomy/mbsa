/*
 * AbstractDocumentNumberStrategy.java
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
import com.mg.merp.document.DocumentNumberStrategy;
import com.mg.merp.document.model.DocHead;

/**
 * Абстрактная стратегия формирования номера документа, супер класс для всех стратегий
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractDocumentNumberStrategy.java,v 1.1 2007/03/23 13:52:29 safonov Exp $
 */
public abstract class AbstractDocumentNumberStrategy implements
		DocumentNumberStrategy {
	protected Logger logger = ServerUtils.getLogger(this.getClass());

	/**
	 * реализация формирования номера документа
	 * 
	 * @param docHead	сущность документа
	 * @return	номер документа
	 */
	protected abstract String doGenerateNumber(DocHead docHead);
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.DocumentNumberStrategy#generateNumber(com.mg.merp.document.model.DocHead)
	 */
	public String generateNumber(DocHead docHead) {
		return doGenerateNumber(docHead);
	}

}
