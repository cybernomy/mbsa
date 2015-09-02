/*
 * CostDetailLineItem.java
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
package com.mg.merp.mfreference;

import java.math.BigDecimal;

import com.mg.merp.mfreference.model.CostCategories;

/**
 * ������������ �������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CostDetailLineItem.java,v 1.1 2007/07/30 10:25:31 safonov Exp $
 */
public class CostDetailLineItem {
	private CostCategories costCategory;
	private BigDecimal cost;
	
	public CostDetailLineItem(CostCategories costCategory, BigDecimal cost) {
		super();
		this.costCategory = costCategory;
		this.cost = cost;
	}

	/**
	 * �������� �������������
	 * 
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * �������� ��������� ������
	 * 
	 * @return the costCategory
	 */
	public CostCategories getCostCategory() {
		return costCategory;
	}

	/**
	 * ���������� �������������
	 * 
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * ���������� ��������� ������
	 * 
	 * @param costCategory the costCategory to set
	 */
	public void setCostCategory(CostCategories costCategory) {
		this.costCategory = costCategory;
	}

}