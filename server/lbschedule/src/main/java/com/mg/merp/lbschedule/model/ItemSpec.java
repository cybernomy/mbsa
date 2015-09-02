/*
 * ItemSpec.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Спецификация пункта графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ItemSpec.java,v 1.4 2007/04/21 11:49:33 sharapov Exp $
 */
public class ItemSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.lbschedule.model.Item Item;
	private com.mg.merp.reference.model.Catalog Catalog;
	private com.mg.merp.document.model.DocSpec DocSpec;
	private com.mg.merp.reference.model.Measure Measure1;
	private com.mg.merp.reference.model.Measure Measure2;
	private java.math.BigDecimal Price;
	private java.math.BigDecimal ClearPrice;
	private java.math.BigDecimal Summa;
	private java.math.BigDecimal ClearSumma;
	private java.math.BigDecimal Qty1;
	private java.math.BigDecimal Qty2;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<ItemSpecTax> LsItemSpecTaxes;
	private java.util.Set<ItemSpecAlt> LsItemSpecAlts;


	// Constructors

	/** default constructor */
	public ItemSpec() {
	}

	/** constructor with id */
	public ItemSpec(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.lbschedule.model.Item getItem() {
		return this.Item;
	}

	public void setItem (com.mg.merp.lbschedule.model.Item LsItem) {
		this.Item = LsItem;
	}

	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog (com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	public com.mg.merp.document.model.DocSpec getDocSpec() {
		return this.DocSpec;
	}

	public void setDocSpec (com.mg.merp.document.model.DocSpec Docspec) {
		this.DocSpec = Docspec;
	}

	@DataItemName("LbSchedule.ItemSpec.Measure2.Code") //$NON-NLS-1$
	public com.mg.merp.reference.model.Measure getMeasure2() {
		return this.Measure2;
	}

	public void setMeasure2 (com.mg.merp.reference.model.Measure Measure) {
		this.Measure2 = Measure;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("LbSchedule.ItemSpec.Measure1.Code") //$NON-NLS-1$
	public com.mg.merp.reference.model.Measure getMeasure1() {
		return this.Measure1;
	}

	public void setMeasure1 (com.mg.merp.reference.model.Measure Measure_1) {
		this.Measure1 = Measure_1;
	}

	@DataItemName("LbSchedule.ItemSpec.Price") //$NON-NLS-1$
	public java.math.BigDecimal getPrice() {
		return this.Price;
	}

	public void setPrice (java.math.BigDecimal Price) {
		this.Price = Price;
	}

	@DataItemName("LbSchedule.ItemSpec.ClearPrice") //$NON-NLS-1$
	public java.math.BigDecimal getClearPrice() {
		return this.ClearPrice;
	}

	public void setClearPrice (java.math.BigDecimal Clearprice) {
		this.ClearPrice = Clearprice;
	}

	@DataItemName("LbSchedule.ItemSpec.Summa") //$NON-NLS-1$
	public java.math.BigDecimal getSumma() {
		return this.Summa;
	}

	public void setSumma (java.math.BigDecimal Summa) {
		this.Summa = Summa;
	}

	@DataItemName("LbSchedule.ItemSpec.ClearSumma") //$NON-NLS-1$
	public java.math.BigDecimal getClearSumma() {
		return this.ClearSumma;
	}

	public void setClearSumma (java.math.BigDecimal Clearsumma) {
		this.ClearSumma = Clearsumma;
	}

	@DataItemName("LbSchedule.ItemSpec.Qty1") //$NON-NLS-1$
	public java.math.BigDecimal getQty1() {
		return this.Qty1;
	}

	public void setQty1 (java.math.BigDecimal Qty1) {
		this.Qty1 = Qty1;
	}

	@DataItemName("LbSchedule.ItemSpec.Qty2") //$NON-NLS-1$
	public java.math.BigDecimal getQty2() {
		return this.Qty2;
	}

	public void setQty2 (java.math.BigDecimal Qty2) {
		this.Qty2 = Qty2;
	}

	public java.util.Set getLsItemSpecTaxes() {
		return this.LsItemSpecTaxes;
	}

	public void setLsItemSpecTaxes (java.util.Set<ItemSpecTax> SetOfLsItemspectax) {
		this.LsItemSpecTaxes = SetOfLsItemspectax;
	}

	public java.util.Set getLsItemSpecAlts() {
		return this.LsItemSpecAlts;
	}

	public void setLsItemSpecAlts (java.util.Set<ItemSpecAlt> SetOfLsItemspecalt) {
		this.LsItemSpecAlts = SetOfLsItemspecalt;
	}

}