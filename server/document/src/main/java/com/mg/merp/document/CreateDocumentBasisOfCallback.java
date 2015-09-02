/**
 * CreateDocumentBasisOfCallback.java
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
package com.mg.merp.document;

import java.io.Serializable;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSpec;

/**
 * ќбъект обратного вызова дл€ перехвата обработки создани€ документа, в качестве
 * реализации рекомендуетс€ использовать экземпл€р класса наследованного от
 * {@link com.mg.merp.document.generic.AbstractCreateDocumentBasisOfCallback}
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateDocumentBasisOfCallback.java,v 1.1 2007/09/28 12:03:43 safonov Exp $
 */
public interface CreateDocumentBasisOfCallback extends Serializable {

	/**
	 * вызываетс€ при обработки заголовка документа, возможна установка атрибутов
	 * объекта <code>dstDoc</code>
	 * 
	 * @param dstDoc	создаваемый документ
	 * @param srcDoc	документа источник
	 * @param pattern	образец создаваемого документа
	 */
	void processDocumentHead(DocHead dstDoc, DocHead srcDoc, DocHeadModel pattern);

	/**
	 * вызываетс€ при обработки заголовка документа, возможна установка атрибутов
	 * объекта <code>dstDocSpec</code>
	 * 
	 * @param dstDoc	создаваемый документ
	 * @param srcDoc	документа источник
	 * @param dstDocSpec	создаваема€ спецификаци€ документа
	 * @param srcDocSpec	спецификаци€ документа источника
	 */
	void processDocumentSpec(DocHead dstDoc, DocHead srcDoc, DocSpec dstDocSpec, DocSpec srcDocSpec);
	
}
