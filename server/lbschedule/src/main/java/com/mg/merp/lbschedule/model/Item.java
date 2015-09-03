/*
 * Item.java
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
 * Модель бизнес-компонента "Пункт графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: Item.java,v 1.8 2007/04/21 11:49:33 sharapov Exp $
 */
public class Item extends com.mg.framework.service.PersistentObjectHibernate
implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor From;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

	private com.mg.merp.reference.model.PriceType PriceType;

	private com.mg.merp.lbschedule.model.Item DateRelItem;

	private com.mg.merp.lbschedule.model.ItemKind ItemKind;

	private com.mg.merp.reference.model.Contractor To;

	private com.mg.merp.reference.model.PriceListHead PriceListHead;

	private com.mg.merp.lbschedule.model.Item SumRelItem;

	private com.mg.merp.lbschedule.model.Schedule Schedule;

	private com.mg.merp.paymentcontrol.model.PmcResource ResourceTo;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.paymentcontrol.model.PmcResource ResourceFrom;

	private com.mg.merp.reference.model.CurrencyRateType CurRateType;

	private com.mg.merp.reference.model.Currency CurCode;

	private java.lang.String Comments;

	private java.lang.Short Num;

	private ScheduleStatus Status;

	private ItemContractorSource ToSource;

	private ItemContractorSource FromSource;

	private SpecSource SpecSource;

	private boolean HasSpec;

	private java.util.Date AbsDate;

	private boolean IsAbsDate;

	private DateOffSetKind DateOffSetKind;

	private java.lang.Integer DateOffSet;

	private java.math.BigDecimal AbsSum;

	private boolean IsAbsSum;

	private java.util.Date ResultDate;

	private boolean IsRelFact;

	private java.math.BigDecimal FactSum;

	private java.math.BigDecimal ResultSum;

	private boolean IsDateRelDoc;

	private boolean IsSumRelDoc;

	private java.math.BigDecimal Perc;

	private java.util.Date ResultDateEnd;

	private boolean IsDateRelEnd;
	
	private java.util.Set<ItemSpec> LsItemSpecs;

	// Constructors

	/** default constructor */
	public Item() {
	}

	/** constructor with id */
	public Item(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.From") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getFrom() {
		return this.From;
	}

	public void setFrom(com.mg.merp.reference.model.Contractor Contractor) {
		this.From = Contractor;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
		return this.CurRateAuthority;
	}

	public void setCurRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurRateAuthority = RefCurrencyRateAuthority;
	}

	/**
	 * 
	 */	
	@DataItemName("LbSchedule.Item.PriceType") //$NON-NLS-1$
	public com.mg.merp.reference.model.PriceType getPriceType() {
		return this.PriceType;
	}

	public void setPriceType(com.mg.merp.reference.model.PriceType Pricetype) {
		this.PriceType = Pricetype;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.DateRelItem") //$NON-NLS-1$
	public com.mg.merp.lbschedule.model.Item getDateRelItem() {
		return this.DateRelItem;
	}

	public void setDateRelItem(com.mg.merp.lbschedule.model.Item LsItem) {
		this.DateRelItem = LsItem;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ItemKind") //$NON-NLS-1$
	public com.mg.merp.lbschedule.model.ItemKind getItemKind() {
		return this.ItemKind;
	}

	public void setItemKind(com.mg.merp.lbschedule.model.ItemKind LsItemkind) {
		this.ItemKind = LsItemkind;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.To") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getTo() {
		return this.To;
	}

	public void setTo(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.To = Contractor_1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.PriceListHead getPriceListHead() {
		return this.PriceListHead;
	}

	public void setPriceListHead(
			com.mg.merp.reference.model.PriceListHead Pricelisthead) {
		this.PriceListHead = Pricelisthead;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.SumRelItem") //$NON-NLS-1$
	public com.mg.merp.lbschedule.model.Item getSumRelItem() {
		return this.SumRelItem;
	}

	public void setSumRelItem(com.mg.merp.lbschedule.model.Item LsItem_1) {
		this.SumRelItem = LsItem_1;
	}

	/**
	 * 
	 */

	public com.mg.merp.lbschedule.model.Schedule getSchedule() {
		return this.Schedule;
	}

	public void setSchedule(com.mg.merp.lbschedule.model.Schedule LsSchedule) {
		this.Schedule = LsSchedule;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ResourceTo") //$NON-NLS-1$
	public com.mg.merp.paymentcontrol.model.PmcResource getResourceTo() {
		return this.ResourceTo;
	}

	public void setResourceTo(
			com.mg.merp.paymentcontrol.model.PmcResource PmcResource) {
		this.ResourceTo = PmcResource;
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
	@DataItemName("LbSchedule.Item.ResourceFrom") //$NON-NLS-1$
	public com.mg.merp.paymentcontrol.model.PmcResource getResourceFrom() {
		return this.ResourceFrom;
	}

	public void setResourceFrom(
			com.mg.merp.paymentcontrol.model.PmcResource PmcResource_1) {
		this.ResourceFrom = PmcResource_1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
		return this.CurRateType;
	}

	public void setCurRateType(
			com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurRateType = RefCurrencyRateType;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Currency getCurCode() {
		return this.CurCode;
	}

	public void setCurCode(com.mg.merp.reference.model.Currency Currency) {
		this.CurCode = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.Comment") //$NON-NLS-1$
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.Num") //$NON-NLS-1$
	public java.lang.Short getNum() {
		return this.Num;
	}

	public void setNum(java.lang.Short Num) {
		this.Num = Num;
	}

	/**
	 * 
	 */

	public ScheduleStatus getStatus() {
		return this.Status;
	}

	public void setStatus(ScheduleStatus Status) {
		this.Status = Status;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ToSource") //$NON-NLS-1$
	public ItemContractorSource getToSource() {
		return this.ToSource;
	}

	public void setToSource(ItemContractorSource Tosource) {
		this.ToSource = Tosource;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.FromSource") //$NON-NLS-1$
	public ItemContractorSource getFromSource() {
		return this.FromSource;
	}

	public void setFromSource(ItemContractorSource Fromsource) {
		this.FromSource = Fromsource;
	}

	/**
	 * 
	 */

	public SpecSource getSpecSource() {
		return this.SpecSource;
	}

	public void setSpecSource(SpecSource Specsource) {
		this.SpecSource = Specsource;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.HasSpec") //$NON-NLS-1$
	public boolean getHasSpec() {
		return this.HasSpec;
	}

	public void setHasSpec(boolean HasSpec) {
		this.HasSpec = HasSpec;
	}

	/**
	 * 
	 */

	public java.util.Date getAbsDate() {
		return this.AbsDate;
	}

	public void setAbsDate(java.util.Date AbsDate) {
		this.AbsDate = AbsDate;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsAbsDate") //$NON-NLS-1$
	public boolean getIsAbsDate() {
		return this.IsAbsDate;
	}

	public void setIsAbsDate(boolean IsAbsDate) {
		this.IsAbsDate = IsAbsDate;
	}

	/**
	 * 
	 */

	public DateOffSetKind getDateOffSetKind() {
		return this.DateOffSetKind;
	}

	public void setDateOffSetKind(DateOffSetKind Dateoffsetkind) {
		this.DateOffSetKind = Dateoffsetkind;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.DateOffSet") //$NON-NLS-1$
	public java.lang.Integer getDateOffSet() {
		return this.DateOffSet;
	}

	public void setDateOffSet(java.lang.Integer Dateoffset) {
		this.DateOffSet = Dateoffset;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getAbsSum() {
		return this.AbsSum;
	}

	public void setAbsSum(java.math.BigDecimal AbsSum) {
		this.AbsSum = AbsSum;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsAbsSum") //$NON-NLS-1$
	public boolean getIsAbsSum() {
		return this.IsAbsSum;
	}

	public void setIsAbsSum(boolean IsAbsSum) {
		this.IsAbsSum = IsAbsSum;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ResultDate") //$NON-NLS-1$
	public java.util.Date getResultDate() {
		return this.ResultDate;
	}

	public void setResultDate(java.util.Date Resultdate) {
		this.ResultDate = Resultdate;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsRelFact") //$NON-NLS-1$
	public boolean getIsRelFact() {
		return this.IsRelFact;
	}

	public void setIsRelFact(boolean IsRelfact) {
		this.IsRelFact = IsRelfact;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.FactSum") //$NON-NLS-1$
	public java.math.BigDecimal getFactSum() {
		return this.FactSum;
	}

	public void setFactSum(java.math.BigDecimal Factsum) {
		this.FactSum = Factsum;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ResultSum") //$NON-NLS-1$
	public java.math.BigDecimal getResultSum() {
		return this.ResultSum;
	}

	public void setResultSum(java.math.BigDecimal Resultsum) {
		this.ResultSum = Resultsum;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsDateRelDoc") //$NON-NLS-1$
	public boolean getIsDateRelDoc() {
		return this.IsDateRelDoc;
	}

	public void setIsDateRelDoc(boolean IsDatereldoc) {
		this.IsDateRelDoc = IsDatereldoc;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsSumRelDoc") //$NON-NLS-1$
	public boolean getIsSumRelDoc() {
		return this.IsSumRelDoc;
	}

	public void setIsSumRelDoc(boolean IsSumreldoc) {
		this.IsSumRelDoc = IsSumreldoc;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.Perc") //$NON-NLS-1$
	public java.math.BigDecimal getPerc() {
		return this.Perc;
	}

	public void setPerc(java.math.BigDecimal Perc) {
		this.Perc = Perc;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.ResultDateEnd") //$NON-NLS-1$
	public java.util.Date getResultDateEnd() {
		return this.ResultDateEnd;
	}

	public void setResultDateEnd(java.util.Date Resultdateend) {
		this.ResultDateEnd = Resultdateend;
	}

	/**
	 * 
	 */
	@DataItemName("LbSchedule.Item.IsDateRelEnd") //$NON-NLS-1$
	public boolean getIsDateRelEnd() {
		return this.IsDateRelEnd;
	}

	public void setIsDateRelEnd(boolean IsDaterelend) {
		this.IsDateRelEnd = IsDaterelend;
	}

	/**
	 * @return the lsItemSpecs
	 */
	public java.util.Set<ItemSpec> getLsItemSpecs() {
		return LsItemSpecs;
	}

	/**
	 * @param lsItemSpecs the lsItemSpecs to set
	 */
	public void setLsItemSpecs(java.util.Set<ItemSpec> lsItemSpecs) {
		LsItemSpecs = lsItemSpecs;
	}

}