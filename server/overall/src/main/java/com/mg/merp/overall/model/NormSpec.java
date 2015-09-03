/*
 * NormSpec.java
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
package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Спецификации норм выдачи спецодежды"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: NormSpec.java,v 1.5 2008/06/30 04:15:16 alikaev Exp $
 */
public class NormSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.overall.model.NormHead OvrNormHead;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String OvrNormSpecName;

	private java.lang.String MeasureUpCode;

	private CatalogGroupsType CatalogGroupsTypeId;

	private java.math.BigDecimal ShelfLife;

	private com.mg.merp.reference.model.TimePeriodKind ShelfLifeMeas;

	private BasicNormKind IsBasic;

	private PeriodicNormKind IsPeriodic;

	private boolean IsDinch;

	private java.lang.String DinchFormula;

	private java.lang.String DocName;

	private java.math.BigDecimal Quantity;

	private java.util.Set<NormSpecDocSpecLink> normSpecDocSpecLinks;

	private java.util.Set<NormSpecLink> normSpecLinks;

	// Constructors

	/** default constructor */
	public NormSpec() {
	}

	/** constructor with id */
	public NormSpec(int Id) {
		this.Id = Id;
	}

	// Property accessors
	
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public com.mg.merp.overall.model.NormHead getOvrNormHead() {
		return this.OvrNormHead;
	}

	public void setOvrNormHead(com.mg.merp.overall.model.NormHead OvrNormHead) {
		this.OvrNormHead = OvrNormHead;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	@DataItemName("Overall.Spec.Name")
	public java.lang.String getOvrNormSpecName() {
		return this.OvrNormSpecName;
	}

	public void setOvrNormSpecName(java.lang.String OvrNormSpecName) {
		this.OvrNormSpecName = OvrNormSpecName;
	}

	@DataItemName("Overall.Spec.MeasureUpCode")
	public java.lang.String getMeasureUpCode() {
		return this.MeasureUpCode;
	}

	public void setMeasureUpCode(java.lang.String MeasureUpcode) {
		this.MeasureUpCode = MeasureUpcode;
	}

	public CatalogGroupsType getCatalogGroupsTypeId() {
		return this.CatalogGroupsTypeId;
	}

	public void setCatalogGroupsTypeId(CatalogGroupsType CatalogGroupsTypeId) {
		this.CatalogGroupsTypeId = CatalogGroupsTypeId;
	}

	@DataItemName("Overall.Spec.Shelflife")
	public java.math.BigDecimal getShelfLife() {
		return this.ShelfLife;
	}

	public void setShelfLife(java.math.BigDecimal Shelflife) {
		this.ShelfLife = Shelflife;
	}

	@DataItemName("Overall.Spec.ShelfLifeMeas")
	public com.mg.merp.reference.model.TimePeriodKind getShelfLifeMeas() {
		return this.ShelfLifeMeas;
	}

	public void setShelfLifeMeas(
			com.mg.merp.reference.model.TimePeriodKind ShelflifeMeas) {
		this.ShelfLifeMeas = ShelflifeMeas;
	}

	public BasicNormKind getIsBasic() {
		return this.IsBasic;
	}

	public void setIsBasic(BasicNormKind Isbasic) {
		this.IsBasic = Isbasic;
	}

	public PeriodicNormKind getIsPeriodic() {
		return this.IsPeriodic;
	}

	public void setIsPeriodic(PeriodicNormKind Isperiodic) {
		this.IsPeriodic = Isperiodic;
	}

	@DataItemName("Overall.Spec.IsDinch")
	public boolean getIsDinch() {
		return this.IsDinch;
	}

	public void setIsDinch(boolean Isdinch) {
		this.IsDinch = Isdinch;
	}

	@DataItemName("Overall.Spec.DinchFormula")
	public java.lang.String getDinchFormula() {
		return this.DinchFormula;
	}

	public void setDinchFormula(java.lang.String DinchFormula) {
		this.DinchFormula = DinchFormula;
	}

	@DataItemName("Overall.Spec.DocName")
	public java.lang.String getDocName() {
		return this.DocName;
	}

	public void setDocName(java.lang.String DocName) {
		this.DocName = DocName;
	}

	@DataItemName("Overall.Spec.Quantity")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	public java.util.Set<NormSpecDocSpecLink> getNormSpecDocSpecLinks() {
		return this.normSpecDocSpecLinks;
	}

	public void setNormSpecDocSpecLinks(java.util.Set<NormSpecDocSpecLink> normSpecDocSpecLinks) {
		this.normSpecDocSpecLinks = normSpecDocSpecLinks;
	}

	public java.util.Set<NormSpecLink> getNormSpecLinks() {
		return this.normSpecLinks;
	}

	public void setNormSpecLinks(java.util.Set<NormSpecLink> normSpecLinks) {
		this.normSpecLinks = normSpecLinks;
	}

}