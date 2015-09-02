/**
 * AbstractCreateDocumentBasisOfCallback.java
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

import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSpec;

/**
 * Базовая реализация объекта обратного вызова для перехвата обработки создания документа
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractCreateDocumentBasisOfCallback.java,v 1.1 2007/09/28 12:04:20 safonov Exp $
 */
public abstract class AbstractCreateDocumentBasisOfCallback implements
		CreateDocumentBasisOfCallback {

	/**
	 * реализация обработки документа
	 * 
	 * @param dstDoc	создаваемый документ
	 * @param srcDoc	документа источник
	 * @param pattern
	 */
	protected abstract void doProcessDocumentHead(DocHead dstDoc, DocHead srcDoc,
			DocHeadModel pattern);
	
	/**
	 * реализация обработки спецификации документа
	 * 
	 * @param dstDoc	создаваемый документ
	 * @param srcDoc	документа источник
	 * @param dstDocSpec	создаваемая спецификация документа
	 * @param srcDocSpec	спецификация документа источника
	 */
	protected abstract void doProcessDocumentSpec(DocHead dstDoc, DocHead srcDoc,
			DocSpec dstDocSpec, DocSpec srcDocSpec);
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOfCallback#prepareDocumentHead(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel)
	 */
	public final void processDocumentHead(DocHead dstDoc, DocHead srcDoc,
			DocHeadModel pattern) {
		doProcessDocumentHead(dstDoc, srcDoc, pattern);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOfCallback#prepareDocumentSpec(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocSpec, com.mg.merp.document.model.DocSpec)
	 */
	public final void processDocumentSpec(DocHead dstDoc, DocHead srcDoc,
			DocSpec dstDocSpec, DocSpec srcDocSpec) {
		doProcessDocumentSpec(dstDoc, srcDoc, dstDocSpec, srcDocSpec);
	}

}
