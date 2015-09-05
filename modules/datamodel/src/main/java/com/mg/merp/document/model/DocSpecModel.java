/*
 * DocSpecModel.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.document.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.SearchHelpName;
import com.mg.merp.reference.model.TimePeriodKind;

/**
 * Модель бизнес-компонента "Спецификации образцов документов" 
 * 
 * @author hbm2java
 * @version $Id: DocSpecModel.java,v 1.6 2008/03/20 14:05:22 alikaev Exp $
 */
public class DocSpecModel extends com.mg.framework.service.PersistentObjectHibernate implements	java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.reference.model.Contractor DstMol;

	private com.mg.merp.reference.model.Contractor SrcMol;

	private com.mg.merp.document.model.DocHeadModel DocHeadModel;

	private com.mg.merp.reference.model.Measure Measure2;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor DstStock;

	private com.mg.merp.reference.model.Measure Measure1;

	private com.mg.merp.reference.model.Contractor SrcStock;

	private com.mg.merp.reference.model.PriceListSpec PriceListSpec;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal Price;

	private java.math.BigDecimal Summa;

	private java.math.BigDecimal Price1;

	private java.math.BigDecimal Summa1;

	private java.math.BigDecimal Weight;

	private java.math.BigDecimal Volume;

	private java.util.Date BestBefore;

	// private java.lang.Integer PackingId;
	// private java.math.BigDecimal Packingquan;
	private com.mg.merp.reference.model.TaxGroup TaxGroup;

	private java.util.Date ProductionDate;

	private java.math.BigDecimal ShelfLife;

	private TimePeriodKind ShelfLifeMeas;

	private java.math.BigDecimal Quantity2;

	private java.lang.String Comment;

	private com.mg.merp.reference.model.Contractor Contractor;

	// Constructors

	/** default constructor */
	public DocSpecModel() {
	}

	/** constructor with id */
	public DocSpecModel(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@SearchHelpName("com.mg.merp.document.support.ui.CatalogByDocSpecSearchHelp")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getDstMol() {
		return this.DstMol;
	}

	public void setDstMol(com.mg.merp.reference.model.Contractor Contractor) {
		this.DstMol = Contractor;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getSrcMol() {
		return this.SrcMol;
	}

	public void setSrcMol(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.SrcMol = Contractor_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.document.model.DocHeadModel getDocHeadModel() {
		return this.DocHeadModel;
	}

	public void setDocHeadModel(
			com.mg.merp.document.model.DocHeadModel Docheadmodel) {
		this.DocHeadModel = Docheadmodel;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Measure2")
	public com.mg.merp.reference.model.Measure getMeasure2() {
		return this.Measure2;
	}

	public void setMeasure2(com.mg.merp.reference.model.Measure Measure) {
		this.Measure2 = Measure;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getDstStock() {
		return this.DstStock;
	}

	public void setDstStock(com.mg.merp.reference.model.Contractor Contractor_2) {
		this.DstStock = Contractor_2;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Measure1")
	public com.mg.merp.reference.model.Measure getMeasure1() {
		return this.Measure1;
	}

	public void setMeasure1(com.mg.merp.reference.model.Measure Measure_1) {
		this.Measure1 = Measure_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getSrcStock() {
		return this.SrcStock;
	}

	public void setSrcStock(com.mg.merp.reference.model.Contractor Contractor_3) {
		this.SrcStock = Contractor_3;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.PriceListSpec getPriceListSpec() {
		return this.PriceListSpec;
	}

	public void setPriceListSpec(
			com.mg.merp.reference.model.PriceListSpec PriceListSpec) {
		this.PriceListSpec = PriceListSpec;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Quantity1")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Price")
	public java.math.BigDecimal getPrice() {
		return this.Price;
	}

	public void setPrice(java.math.BigDecimal Price) {
		this.Price = Price;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Summa")
	public java.math.BigDecimal getSumma() {
		return this.Summa;
	}

	public void setSumma(java.math.BigDecimal Summa) {
		this.Summa = Summa;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Price1")
	public java.math.BigDecimal getPrice1() {
		return this.Price1;
	}

	public void setPrice1(java.math.BigDecimal Price1) {
		this.Price1 = Price1;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Summa1")
	public java.math.BigDecimal getSumma1() {
		return this.Summa1;
	}

	public void setSumma1(java.math.BigDecimal Summa1) {
		this.Summa1 = Summa1;
	}

	/**
	 * 
	 */
	@DataItemName("Document.Weight")
	public java.math.BigDecimal getWeight() {
		return this.Weight;
	}

	public void setWeight(java.math.BigDecimal Weight) {
		this.Weight = Weight;
	}

	/**
	 * 
	 */
	@DataItemName("Document.Volume")
	public java.math.BigDecimal getVolume() {
		return this.Volume;
	}

	public void setVolume(java.math.BigDecimal Volume) {
		this.Volume = Volume;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.BestBefore")
	public java.util.Date getBestBefore() {
		return this.BestBefore;
	}

	public void setBestBefore(java.util.Date Bestbefore) {
		this.BestBefore = Bestbefore;
	}

	/**
	 * 
	 */

	// public java.lang.Integer getPackingId () {
	// return this.PackingId;
	// }
	//    
	// public void setPackingId (java.lang.Integer PackingId) {
	// this.PackingId = PackingId;
	// }
	/**
	 * 
	 */

	// public java.math.BigDecimal getPackingquan () {
	// return this.Packingquan;
	// }
	//    
	// public void setPackingquan (java.math.BigDecimal Packingquan) {
	// this.Packingquan = Packingquan;
	// }
	/**
	 * 
	 */
	public com.mg.merp.reference.model.TaxGroup getTaxGroup() {
		return this.TaxGroup;
	}

	public void setTaxGroup(com.mg.merp.reference.model.TaxGroup TaxGroup) {
		this.TaxGroup = TaxGroup;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.ProductionDate")
	public java.util.Date getProductionDate() {
		return this.ProductionDate;
	}

	public void setProductionDate(java.util.Date Productiondate) {
		this.ProductionDate = Productiondate;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Shelflife")
	public java.math.BigDecimal getShelfLife() {
		return this.ShelfLife;
	}

	public void setShelfLife(java.math.BigDecimal Shelflife) {
		this.ShelfLife = Shelflife;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.ShelfLifeMeas")
	public TimePeriodKind getShelfLifeMeas() {
		return this.ShelfLifeMeas;
	}

	public void setShelfLifeMeas(TimePeriodKind ShelflifeMeas) {
		this.ShelfLifeMeas = ShelflifeMeas;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Quantity2")
	public java.math.BigDecimal getQuantity2() {
		return this.Quantity2;
	}

	public void setQuantity2(java.math.BigDecimal Quantity2) {
		this.Quantity2 = Quantity2;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Comment")
	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

	/**
	 * 
	 */
	@DataItemName("Document.DocSpec.Contractor")
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

}