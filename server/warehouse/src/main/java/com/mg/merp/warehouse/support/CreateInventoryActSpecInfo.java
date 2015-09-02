/*
 * CreateInventoryActSpecInfo.java
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
package com.mg.merp.warehouse.support;

import java.math.BigDecimal;

import com.mg.merp.document.CreateSpecificationInfo;

/**
 * ���������� � ����������� ��� �������� ������������ ���� ��������������
 * 
 * @author Artem V. Sharapov
 * @version $Id: CreateInventoryActSpecInfo.java,v 1.1 2007/06/18 12:51:33 sharapov Exp $
 */
public class CreateInventoryActSpecInfo implements CreateSpecificationInfo {

	private Integer catalogId;
	private BigDecimal price;
	private BigDecimal quantity1;
	private BigDecimal quantity2;
	private BigDecimal summa;
	private BigDecimal summa1;
	
	
	/**
	 * ������� ���������� � ����������� ��� ������������ ���� ��������������
	 * @param catalogId - ������������� ��������
	 * @param price - ����
	 * @param quantity1 - ���������� � �������� ��
	 * @param quantity2 - ���������� � �������������� ��
	 * @param summa - ����� � ��������
	 * @param summa1 - �����
	 */
	public CreateInventoryActSpecInfo(Integer catalogId, BigDecimal price, BigDecimal quantity1, BigDecimal quantity2, BigDecimal summa, BigDecimal summa1) {
		this.catalogId = catalogId;
		this.price = price;
		this.quantity1 = quantity1;
		this.quantity2 = quantity2;
		this.summa = summa;
		this.summa1 = summa1;
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
		return quantity1;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.CreateSpecificationInfo#getQuantity2()
	 */
	public BigDecimal getQuantity2() {
		return quantity2;
	}

	/**
	 * @return the summa
	 */
	public BigDecimal getSumma() {
		return this.summa;
	}

	/**
	 * @param summa the summa to set
	 */
	public void setSumma(BigDecimal summa) {
		this.summa = summa;
	}

	/**
	 * @return the summa1
	 */
	public BigDecimal getSumma1() {
		return this.summa1;
	}

	/**
	 * @param summa1 the summa1 to set
	 */
	public void setSumma1(BigDecimal summa1) {
		this.summa1 = summa1;
	}

}
