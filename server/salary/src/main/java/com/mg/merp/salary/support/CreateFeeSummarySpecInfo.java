/*
 * CreateFeeSummarySpecInfo.java
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
package com.mg.merp.salary.support;

import java.math.BigDecimal;

import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.personnelref.model.CostsAnl;

/**
 * Информация о номеклатуре для спецификации свода начислений/удержаний по аналитике
 * 
 * @author Artem V. Sharapov
 * @version $Id: CreateFeeSummarySpecInfo.java,v 1.1 2007/08/27 06:19:40 sharapov Exp $
 */
public class CreateFeeSummarySpecInfo implements CreateSpecificationInfo {
	
	private Integer catalogId;
	private BigDecimal price;
	
	private CostsAnl costsAnl1;
	private CostsAnl costsAnl2;
	private CostsAnl costsAnl3;
	private CostsAnl costsAnl4;
	private CostsAnl costsAnl5;
	
	/**
	 * Создать информацию о номеклатуре для спецификации свода начислений/удержаний по аналитике
	 * @param catalogId - идентификатор каталога
	 * @param price - цена
	 * @param costsAnl1 - аналитика состава затрат 1-го уровня
	 * @param costsAnl2 - аналитика состава затрат 2-го уровня
	 * @param costsAnl3 - аналитика состава затрат 3-го уровня
	 * @param costsAnl4 - аналитика состава затрат 4-го уровня
	 * @param costsAnl5 - аналитика состава затрат 5-го уровня
	 */
	public CreateFeeSummarySpecInfo(Integer catalogId, BigDecimal price, CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
		this.catalogId = catalogId;
		this.price = price;
		this.costsAnl1 = costsAnl1;
		this.costsAnl2 = costsAnl2;
		this.costsAnl3 = costsAnl3;
		this.costsAnl4 = costsAnl4;
		this.costsAnl5 = costsAnl5;
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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getQuantity1()
	 */
	public BigDecimal getQuantity1() {
		return BigDecimal.ONE;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getQuantity2()
	 */
	public BigDecimal getQuantity2() {
		return BigDecimal.ZERO;
	}

	/**
	 * @return the costsAnl1
	 */
	public CostsAnl getCostsAnl1() {
		return this.costsAnl1;
	}

	/**
	 * @return the costsAnl2
	 */
	public CostsAnl getCostsAnl2() {
		return this.costsAnl2;
	}

	/**
	 * @return the costsAnl3
	 */
	public CostsAnl getCostsAnl3() {
		return this.costsAnl3;
	}

	/**
	 * @return the costsAnl4
	 */
	public CostsAnl getCostsAnl4() {
		return this.costsAnl4;
	}

	/**
	 * @return the costsAnl5
	 */
	public CostsAnl getCostsAnl5() {
		return this.costsAnl5;
	}

	/**
	 * @param costsAnl1 the costsAnl1 to set
	 */
	public void setCostsAnl1(CostsAnl costsAnl1) {
		this.costsAnl1 = costsAnl1;
	}

	/**
	 * @param costsAnl2 the costsAnl2 to set
	 */
	public void setCostsAnl2(CostsAnl costsAnl2) {
		this.costsAnl2 = costsAnl2;
	}

	/**
	 * @param costsAnl3 the costsAnl3 to set
	 */
	public void setCostsAnl3(CostsAnl costsAnl3) {
		this.costsAnl3 = costsAnl3;
	}

	/**
	 * @param costsAnl4 the costsAnl4 to set
	 */
	public void setCostsAnl4(CostsAnl costsAnl4) {
		this.costsAnl4 = costsAnl4;
	}

	/**
	 * @param costsAnl5 the costsAnl5 to set
	 */
	public void setCostsAnl5(CostsAnl costsAnl5) {
		this.costsAnl5 = costsAnl5;
	}

}
