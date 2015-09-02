/*
 * PmcResource.java
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Средство платежа" 
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcResource.java,v 1.9 2007/05/14 05:12:10 sharapov Exp $
 */
@DataItemName("PaymentControl.PmcResource") //$NON-NLS-1$
public class PmcResource extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.document.model.DocHeadModel InDocModel1;
	private com.mg.merp.document.model.DocHeadModel InDocModel2;

	private com.mg.merp.document.model.DocHeadModel OutDocModel1;
	private com.mg.merp.document.model.DocHeadModel OutDocModel2;

	private com.mg.merp.reference.model.Currency CurCode;
	private com.mg.merp.reference.model.CurrencyRateType CurRateType;
	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor OrgUnit;

	private java.lang.String Name;

	private java.lang.String Description;

	private java.util.Date ActDateTill;

	private java.util.Date ActDateFrom;

	private java.util.Set SetOfLsItem;

	private java.util.Set SetOfPmcExecution;

	private java.util.Set SetOfPmcLiability;

	private java.util.Set SetOfLsItem_1;

	// Constructors

	/** default constructor */
	public PmcResource() {
	}

	/** constructor with id */
	public PmcResource(java.lang.Integer Id) {
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
	@DataItemName("PaymentControl.PmcResource.Catalog") //$NON-NLS-1$
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.PmcResource.OutDocModel2") //$NON-NLS-1$
	public com.mg.merp.document.model.DocHeadModel getOutDocModel2() {
		return this.OutDocModel2;
	}

	public void setOutDocModel2(
			com.mg.merp.document.model.DocHeadModel Docheadmodel) {
		this.OutDocModel2 = Docheadmodel;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
		return this.CurRateAuthority;
	}

	public void setCurRateAuthority(com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurRateAuthority = RefCurrencyRateAuthority;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.PmcResource.OutDocModel1") //$NON-NLS-1$
	public com.mg.merp.document.model.DocHeadModel getOutDocModel1() {
		return this.OutDocModel1;
	}

	public void setOutDocModel1(
			com.mg.merp.document.model.DocHeadModel Docheadmodel_1) {
		this.OutDocModel1 = Docheadmodel_1;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("PaymentControl.PmcResource.InDocModel1") //$NON-NLS-1$
	public com.mg.merp.document.model.DocHeadModel getInDocModel1() {
		return this.InDocModel1;
	}

	public void setInDocModel1(
			com.mg.merp.document.model.DocHeadModel Docheadmodel_2) {
		this.InDocModel1 = Docheadmodel_2;
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
	@DataItemName("PaymentControl.PmcResource.InDocModel2") //$NON-NLS-1$
	public com.mg.merp.document.model.DocHeadModel getInDocModel2() {
		return this.InDocModel2;
	}

	public void setInDocModel2(
			com.mg.merp.document.model.DocHeadModel Docheadmodel_3) {
		this.InDocModel2 = Docheadmodel_3;
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
	@DataItemName("PaymentControl.Resource.OrgUnit") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getOrgUnit() {
		return this.OrgUnit;
	}

	public void setOrgUnit(com.mg.merp.reference.model.Contractor Contractor) {
		this.OrgUnit = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Resource.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Resource.Description") //$NON-NLS-1$
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Payment.ActDateTill") //$NON-NLS-1$
	public java.util.Date getActDateTill() {
		return this.ActDateTill;
	}

	public void setActDateTill(java.util.Date Actdatetill) {
		this.ActDateTill = Actdatetill;
	}

	/**
	 * 
	 */
	@DataItemName("PaymentControl.Payment.ActDateFrom") //$NON-NLS-1$
	public java.util.Date getActDateFrom() {
		return this.ActDateFrom;
	}

	public void setActDateFrom(java.util.Date Actdatefrom) {
		this.ActDateFrom = Actdatefrom;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfLsItem() {
		return this.SetOfLsItem;
	}

	public void setSetOfLsItem(java.util.Set SetOfLsItem) {
		this.SetOfLsItem = SetOfLsItem;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfPmcExecution() {
		return this.SetOfPmcExecution;
	}

	public void setSetOfPmcExecution(java.util.Set SetOfPmcExecution) {
		this.SetOfPmcExecution = SetOfPmcExecution;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfPmcLiability() {
		return this.SetOfPmcLiability;
	}

	public void setSetOfPmcLiability(java.util.Set SetOfPmcLiability) {
		this.SetOfPmcLiability = SetOfPmcLiability;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfLsItem_1() {
		return this.SetOfLsItem_1;
	}

	public void setSetOfLsItem_1(java.util.Set SetOfLsItem_1) {
		this.SetOfLsItem_1 = SetOfLsItem_1;
	}

}