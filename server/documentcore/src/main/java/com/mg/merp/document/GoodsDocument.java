/*
 * GoodsDocument.java
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
package com.mg.merp.document;

import java.io.Serializable;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;


/**
 * Бизнес-компонент "Документ со спецификациями"
 * 
 * @author Oleg V. Safonov
 * @version $Id: GoodsDocument.java,v 1.5 2007/09/26 09:38:34 safonov Exp $
 */
public interface GoodsDocument<T extends DocHead, ID extends Serializable, M extends DocumentPattern, S extends GoodsDocumentSpecification> extends Document<T, ID, M> {
	
	/**
	 * получить бизнес-компонент спецификации документа
	 * 
	 * @return	бизнес-компонент спецификации документа
	 */
	S getSpecificationService();
	
	/**
	 * изменение спецификаций документа, необходимо вызвать для изменения заголовка документа
	 * после изменения спецификаций, в случае использования штатных сервисов системы вызов не требуется
	 * 
	 * @param docSpec	спецификации
	 */
	void modifySpecifaction(DocSpec ... docSpecs);
	
	/**
	 * создание спецификаций документа, необходимо вызвать для изменения заголовка документа
	 * после добавления спецификаций, в случае использования штатных сервисов системы вызов не требуется.
	 * Все спецификации должны пренадлежать одному документу
	 * 
	 * @param docSpecs	спецификации
	 */
	void createSpecifaction(DocSpec ... docSpecs);
	
	/**
	 * удаление спецификаций документа, необходимо вызвать для изменения заголовка документа
	 * после удаления спецификаций, в случае использования штатных сервисов системы вызов не требуется
	 * 
	 * @param docSpec	спецификации
	 */
	void removeSpecifaction(DocSpec ... docSpecs);
	
}
