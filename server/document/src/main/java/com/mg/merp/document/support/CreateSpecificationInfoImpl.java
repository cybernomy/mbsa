/*
 * CreateSpecificationInfoImpl.java
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

import java.math.BigDecimal;

import com.mg.merp.document.CreateSpecificationInfo;

/**
 * Базовая реализация информации о номенклатуре для создания спецификаций
 * 
 * @author Oleg V. Safonov
 * @version $Id: CreateSpecificationInfoImpl.java,v 1.1 2007/07/27 12:01:38 safonov Exp $
 */
public class CreateSpecificationInfoImpl implements CreateSpecificationInfo {
	Integer catalogId;
	Integer pricelistId;
	BigDecimal price;
	BigDecimal quantity1;
	BigDecimal quantity2;

	public CreateSpecificationInfoImpl(Integer catalogId, Integer pricelistId, BigDecimal price, BigDecimal quantity1, BigDecimal quantity2) {
		super();
		this.catalogId = catalogId;
		this.pricelistId = pricelistId;
		this.price = price;
		this.quantity1 = quantity1;
		this.quantity2 = quantity2;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getCatalogId()
	 */
	public Integer getCatalogId() {
		return catalogId;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getPrice()
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getPricelistId()
	 */
	public Integer getPricelistId() {
		return pricelistId;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getQuantity1()
	 */
	public BigDecimal getQuantity1() {
		return quantity1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getQuantity2()
	 */
	public BigDecimal getQuantity2() {
		return quantity2;
	}

}
