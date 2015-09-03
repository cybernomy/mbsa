/*
 * GoodsDocumentSpecification.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;


/**
 * Бизнес-компонент "Спецификации документа"
 * 
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocumentSpecification.java,v 1.6 2009/02/11 14:09:26 safonov Exp $
 */
public interface GoodsDocumentSpecification<T extends com.mg.merp.document.model.DocSpec, ID extends Serializable>
		extends DataBusinessObjectService<T, ID> {

	/**
	 * получить раздел документа
	 * 
	 * @return	раздел документа
	 */
	DocSection getDocSection();
	
	/**
	 * массовое добавление спецификаций
	 * 
	 * @param docHead	заголовок документа
	 * @param goodsInfoList	список номенклатуры для создания спецификаций
	 */
	void bulkCreate(DocHead docHead, CreateSpecificationInfo[] goodsInfoList);
	
	/**
	 * расчет атрибутов позиции спецификации, при использовании штатных методов создания и изменения
	 * вызов данного метода не требуется, если атрибут {@link com.mg.merp.document.model.DocSpec#isAdjusted() Adjusted}
	 * установлен в <code>true</code>, то расчет производится не будет, т.о. возможно изменить
	 * стандартное поведение системы
	 * 
	 * @param entity
	 */
	void adjust(T entity);

	/**
	 * Расчет срока годности у позиций спецификации документа
	 * 
	 * @param entity	документ
	 */
	void updateSpecBestBefore(DocHead docHead);
	

}
