/*
 * ItemSpecCreateData.java
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
package com.mg.merp.lbschedule.model;

import java.math.BigDecimal;

import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.PriceListHead;

/**
 * Модель данных для создания позиции спецификации пункта графика
 * 
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecCreateData.java,v 1.1 2007/04/17 12:49:25 sharapov Exp $
 */
public class ItemSpecCreateData {

	private com.mg.merp.reference.model.Catalog catalog;
	private PriceListHead priceList;
	private com.mg.merp.document.model.DocSpec docSpec;
	private com.mg.merp.reference.model.Measure measure1;
	private java.math.BigDecimal qty1;
	private com.mg.merp.reference.model.Measure measure2;
	private java.math.BigDecimal qty2;
	private java.math.BigDecimal price;
	
	public ItemSpecCreateData(Catalog catalog, DocSpec docSpec, Measure measure1, BigDecimal qty1, Measure measure2, BigDecimal qty2, BigDecimal price) {
		this.catalog = catalog;
		this.docSpec = docSpec;
		this.measure1 = measure1;
		this.qty1 = qty1;
		this.measure2 = measure2;
		this.qty2 = qty2;
		this.price = price;
	}

	/**
	 * @return the catalog
	 */
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(com.mg.merp.reference.model.Catalog catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return the docSpec
	 */
	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return docSpec;
	}

	/**
	 * @param docSpec the docSpec to set
	 */
	public void setDocSpec(com.mg.merp.document.model.DocSpec docSpec) {
		this.docSpec = docSpec;
	}
	
	/**
	 * @return the measure1
	 */
	public com.mg.merp.reference.model.Measure getMeasure1() {
		return measure1;
	}

	/**
	 * @param measure1 the measure1 to set
	 */
	public void setMeasure1(com.mg.merp.reference.model.Measure measure1) {
		this.measure1 = measure1;
	}

	/**
	 * @return the measure2
	 */
	public com.mg.merp.reference.model.Measure getMeasure2() {
		return measure2;
	}

	/**
	 * @param measure2 the measure2 to set
	 */
	public void setMeasure2(com.mg.merp.reference.model.Measure measure2) {
		this.measure2 = measure2;
	}

	/**
	 * @return the price
	 */
	public java.math.BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the qty1
	 */
	public java.math.BigDecimal getQty1() {
		return qty1;
	}

	/**
	 * @param qty1 the qty1 to set
	 */
	public void setQty1(java.math.BigDecimal qty1) {
		this.qty1 = qty1;
	}

	/**
	 * @return the qty2
	 */
	public java.math.BigDecimal getQty2() {
		return qty2;
	}

	/**
	 * @param qty2 the qty2 to set
	 */
	public void setQty2(java.math.BigDecimal qty2) {
		this.qty2 = qty2;
	}

	/**
	 * @return the priceList
	 */
	public PriceListHead getPriceList() {
		return priceList;
	}

	/**
	 * @param priceList the priceList to set
	 */
	public void setPriceList(PriceListHead priceList) {
		this.priceList = priceList;
	}

}
