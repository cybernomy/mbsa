/*
 * OvrCardHist.java
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
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.TimePeriodKind;

/**
 * Модель бизнес-компонента "История выдачи спецодежды"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCardHist.java,v 1.7 2008/06/30 04:15:16 alikaev Exp $
 */
public class OvrCardHist extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.overall.model.OvrCard OvrCard;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Measure MeasureUpCode;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.overall.model.NormSpec OvrNormSpec;

	private com.mg.merp.overall.model.NormHead OvrNormHead;

	private CatalogGroupsType CatalogGroupsType;

	private BasicNormKind IsBasic;

	private PeriodicNormKind IsPeriodic;

	private boolean Returnable;

	private java.util.Date GiveDate;

	private java.util.Date ReadOutDate;

	private java.math.BigDecimal ShelfLife;

	private TimePeriodKind ShelfLifeMeas;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal NdeCost;

	private java.math.BigDecimal NdeSumma;

	private java.lang.Integer Deterioration;

	private java.math.BigDecimal DepreciableValue;

	private java.math.BigDecimal CommonSummaForDinch;

	private java.math.BigDecimal DinchedSumma;

	private java.math.BigDecimal RestOfDinchSumma;

	private java.math.BigDecimal ArrearSumma;

	private java.lang.Integer GiveDocHeadId;

	private java.lang.Integer GiveDocSpecId;

	private DocType GiveDocType;

	private java.lang.String GiveDocNumber;

	private java.util.Date GiveDocDate;

	private java.lang.Integer ExplDocHeadId;

	private java.lang.Integer ExplDocSpecId;

	private DocType ExplDocType;

	private java.lang.String ExplDocNumber;

	private java.util.Date ExplDocDate;

	private java.lang.Integer RemoveDocHeadId;

	private java.lang.Integer RemoveDocSpecId;

	private DocType RemoveDocType;

	private java.lang.String RemoveDocNumber;

	private java.util.Date RemoveDocDate;

	private com.mg.merp.overall.model.RemoveType RemoveType;

	private java.util.Date RemoveDate;

	private java.util.Date PlanRemoveDate;
	
	private java.util.Set<OvrCardHistDocSpecLink> cardHistDocSpecLinks;

	private java.util.Set<HistStatus> histStatusGroup;

	// Constructors

	/** default constructor */
	public OvrCardHist() {
	}

	/** constructor with id */
	public OvrCardHist(int Id) {
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

	public com.mg.merp.overall.model.OvrCard getOvrCard() {
		return this.OvrCard;
	}

	public void setOvrCard(com.mg.merp.overall.model.OvrCard OvrCard) {
		this.OvrCard = OvrCard;
	}

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	public com.mg.merp.reference.model.Measure getMeasureUpCode() {
		return this.MeasureUpCode;
	}

	public void setMeasureUpCode(com.mg.merp.reference.model.Measure MeasureUpcode) {
		this.MeasureUpCode = MeasureUpcode;
	}

//	@DataItemName("Overall.Card.CatalogId")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	public com.mg.merp.overall.model.NormSpec getOvrNormSpec() {
		return this.OvrNormSpec;
	}

	public void setOvrNormSpec(com.mg.merp.overall.model.NormSpec OvrNormSpec) {
		this.OvrNormSpec = OvrNormSpec;
	}

	public com.mg.merp.overall.model.NormHead getOvrNormHead() {
		return this.OvrNormHead;
	}

	public void setOvrNormHead(com.mg.merp.overall.model.NormHead OvrNormHead) {
		this.OvrNormHead = OvrNormHead;
	}

	public CatalogGroupsType getCatalogGroupsType() {
		return this.CatalogGroupsType;
	}

	public void setCatalogGroupsType(CatalogGroupsType CatalogGroupsType) {
		this.CatalogGroupsType = CatalogGroupsType;
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

	@DataItemName ("Overall.Card.Returnable")
	public boolean getReturnable() {
		return this.Returnable;
	}

	public void setReturnable(boolean returnable) {
		this.Returnable = returnable;
	}

	@DataItemName("Overall.OvrCardHist.GiveDate")
	public java.util.Date getGiveDate() {
		return this.GiveDate;
	}

	public void setGiveDate(java.util.Date GiveDate) {
		this.GiveDate = GiveDate;
	}

	@DataItemName("Overall.OvrCardHist.ReadOutDate")
	public java.util.Date getReadOutDate() {
		return this.ReadOutDate;
	}

	public void setReadOutDate(java.util.Date ReadoutDate) {
		this.ReadOutDate = ReadoutDate;
	}

	@DataItemName("Overall.Spec.Shelflife")
	public java.math.BigDecimal getShelfLife() {
		return this.ShelfLife;
	}

	public void setShelfLife(java.math.BigDecimal Shelflife) {
		this.ShelfLife = Shelflife;
	}

	@DataItemName("Overall.Spec.ShelfLifeMeas")
	public TimePeriodKind getShelfLifeMeas() {
		return this.ShelfLifeMeas;
	}

	public void setShelfLifeMeas(TimePeriodKind shelflifeMeas) {
		this.ShelfLifeMeas = shelflifeMeas;
	}

	@DataItemName("Overall.Card.Quantity")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	@DataItemName("Overall.Card.NdeCost")
	public java.math.BigDecimal getNdeCost() {
		return this.NdeCost;
	}

	public void setNdeCost(java.math.BigDecimal NdeCost) {
		this.NdeCost = NdeCost;
	}

	@DataItemName("Overall.Card.NdeSumma")
	public java.math.BigDecimal getNdeSumma() {
		return this.NdeSumma;
	}

	public void setNdeSumma(java.math.BigDecimal NdeSumma) {
		this.NdeSumma = NdeSumma;
	}

	@DataItemName("Overall.Card.Deterioration")
	public java.lang.Integer getDeterioration() {
		return this.Deterioration;
	}

	public void setDeterioration(java.lang.Integer Deterioration) {
		this.Deterioration = Deterioration;
	}

	@DataItemName("Overall.Card.DepreciableValue")
	public java.math.BigDecimal getDepreciableValue() {
		return this.DepreciableValue;
	}

	public void setDepreciableValue(java.math.BigDecimal DepreciableValue) {
		this.DepreciableValue = DepreciableValue;
	}

	@DataItemName("Overall.Card.CommonSummaForDinch")
	public java.math.BigDecimal getCommonSummaForDinch() {
		return this.CommonSummaForDinch;
	}

	public void setCommonSummaForDinch(java.math.BigDecimal CommonSummaForDinch) {
		this.CommonSummaForDinch = CommonSummaForDinch;
	}

	@DataItemName("Overall.Card.DinchedSumma")
	public java.math.BigDecimal getDinchedSumma() {
		return this.DinchedSumma;
	}

	public void setDinchedSumma(java.math.BigDecimal DinchedSumma) {
		this.DinchedSumma = DinchedSumma;
	}

	@DataItemName("Overall.Card.RestOfDinchSumma")
	public java.math.BigDecimal getRestOfDinchSumma() {
		return this.RestOfDinchSumma;
	}

	public void setRestOfDinchSumma(java.math.BigDecimal RestOfDinchSumma) {
		this.RestOfDinchSumma = RestOfDinchSumma;
	}

	@DataItemName("Overall.Card.ArrearSumma")
	public java.math.BigDecimal getArrearSumma() {
		return this.ArrearSumma;
	}

	public void setArrearSumma(java.math.BigDecimal ArrearSumma) {
		this.ArrearSumma = ArrearSumma;
	}

	public java.lang.Integer getGiveDocHeadId() {
		return this.GiveDocHeadId;
	}

	public void setGiveDocHeadId(java.lang.Integer GiveDocheadId) {
		this.GiveDocHeadId = GiveDocheadId;
	}

	public java.lang.Integer getGiveDocSpecId() {
		return this.GiveDocSpecId;
	}

	public void setGiveDocSpecId(java.lang.Integer GiveDocspecId) {
		this.GiveDocSpecId = GiveDocspecId;
	}

	public DocType getGiveDocType() {
		return this.GiveDocType;
	}

	public void setGiveDocType(DocType giveDoctype) {
		this.GiveDocType = giveDoctype;
	}

	public java.lang.String getGiveDocNumber() {
		return this.GiveDocNumber;
	}

	public void setGiveDocNumber(java.lang.String GiveDocnumber) {
		this.GiveDocNumber = GiveDocnumber;
	}

	public java.util.Date getGiveDocDate() {
		return this.GiveDocDate;
	}

	public void setGiveDocDate(java.util.Date GiveDocdate) {
		this.GiveDocDate = GiveDocdate;
	}

	public java.lang.Integer getExplDocHeadId() {
		return this.ExplDocHeadId;
	}

	public void setExplDocHeadId(java.lang.Integer ExplDocheadId) {
		this.ExplDocHeadId = ExplDocheadId;
	}

	public java.lang.Integer getExplDocSpecId() {
		return this.ExplDocSpecId;
	}

	public void setExplDocSpecId(java.lang.Integer ExplDocspecId) {
		this.ExplDocSpecId = ExplDocspecId;
	}

	public DocType getExplDocType() {
		return this.ExplDocType;
	}

	public void setExplDocType(DocType explDoctype) {
		this.ExplDocType = explDoctype;
	}

	public java.lang.String getExplDocNumber() {
		return this.ExplDocNumber;
	}

	public void setExplDocNumber(java.lang.String ExplDocnumber) {
		this.ExplDocNumber = ExplDocnumber;
	}

	public java.util.Date getExplDocDate() {
		return this.ExplDocDate;
	}

	public void setExplDocDate(java.util.Date ExplDocdate) {
		this.ExplDocDate = ExplDocdate;
	}

	public java.lang.Integer getRemoveDocHeadId() {
		return this.RemoveDocHeadId;
	}

	public void setRemoveDocHeadId(java.lang.Integer RemoveDocheadId) {
		this.RemoveDocHeadId = RemoveDocheadId;
	}

	public java.lang.Integer getRemoveDocSpecId() {
		return this.RemoveDocSpecId;
	}

	public void setRemoveDocSpecId(java.lang.Integer RemoveDocspecId) {
		this.RemoveDocSpecId = RemoveDocspecId;
	}

	public DocType getRemoveDocType() {
		return this.RemoveDocType;
	}

	public void setRemoveDocType(DocType removeDoctype) {
		this.RemoveDocType = removeDoctype;
	}

	public java.lang.String getRemoveDocNumber() {
		return this.RemoveDocNumber;
	}

	public void setRemoveDocNumber(java.lang.String RemoveDocnumber) {
		this.RemoveDocNumber = RemoveDocnumber;
	}

	public java.util.Date getRemoveDocDate() {
		return this.RemoveDocDate;
	}

	public void setRemoveDocDate(java.util.Date RemoveDocdate) {
		this.RemoveDocDate = RemoveDocdate;
	}

	public com.mg.merp.overall.model.RemoveType getRemoveType() {
		return this.RemoveType;
	}

	public void setRemoveType(com.mg.merp.overall.model.RemoveType RemoveType) {
		this.RemoveType = RemoveType;
	}

	@DataItemName("Overall.OvrCardHist.RemoveDate")
	public java.util.Date getRemoveDate() {
		return this.RemoveDate;
	}

	public void setRemoveDate(java.util.Date RemoveDate) {
		this.RemoveDate = RemoveDate;
	}

	public java.util.Set<OvrCardHistDocSpecLink> getCardHistDocSpecLinks() {
		return this.cardHistDocSpecLinks;
	}

	public void setCardHistDocSpecLinks(java.util.Set<OvrCardHistDocSpecLink> cardHistDocSpecLinks) {
		this.cardHistDocSpecLinks = cardHistDocSpecLinks;
	}

	public java.util.Set<HistStatus> getHistStatusGroup() {
		return this.histStatusGroup;
	}

	public void setHistStatusGroup(java.util.Set<HistStatus> histStatusGroup) {
		this.histStatusGroup = histStatusGroup;
	}

	@DataItemName("Overall.OvrCardHist.PlanRemoveDate")
	public java.util.Date getPlanRemoveDate() {
		return this.ReadOutDate;
	}

	public void setPlanRemoveDate(java.util.Date planRemoveDate) {
		PlanRemoveDate = planRemoveDate;
	}

}