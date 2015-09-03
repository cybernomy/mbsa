/*
 * CreateSpecificationInfo.java
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

import java.math.BigDecimal;

/**
 * Информация о номеклатуре для создания спецификации документа
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateSpecificationInfo.java,v 1.1 2006/12/02 12:35:32 safonov Exp $
 */
public interface CreateSpecificationInfo {

	/**
	 * идентификатор записи каталога
	 * 
	 * @return	идентификатор
	 */
	Integer getCatalogId();
	
	/**
	 * идентификатор записи прайс-листа
	 * 
	 * @return	идентификатор
	 */
	Integer getPricelistId();
	
	/**
	 * получить количество в основной ЕИ
	 * 
	 * @return	количество
	 */
	BigDecimal getQuantity1();
	
	/**
	 * получить количество в дополнительной ЕИ
	 * 
	 * @return	количество
	 */
	BigDecimal getQuantity2();
	
	/**
	 * получить цену
	 * 
	 * @return	цена
	 */
	BigDecimal getPrice();

}
