/*
 * Execution.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
 * Модель бизнес-компонента "Исполнение обязательств"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: Execution.java,v 1.4 2007/01/22 13:13:46 sharapov Exp $
 */
public class Execution extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;
	private com.mg.merp.core.model.Folder ResourceFolder;
	private com.mg.merp.document.model.DocHead DocHead;
	private com.mg.merp.paymentcontrol.model.VersionStatus VerStatus;
	private com.mg.merp.paymentcontrol.model.Liability Liability;
	private com.mg.merp.core.model.SysClient SysClient;
	private com.mg.merp.reference.model.CurrencyRateType CurRateType;
	private com.mg.merp.paymentalloc.model.Payment Payment;
	private com.mg.merp.reference.model.Currency CurCode;
	private com.mg.merp.paymentcontrol.model.Version Version;
	private com.mg.merp.paymentcontrol.model.PmcResource Resource;
	private boolean Receivable;
	private java.util.Date PlanDate;
	private java.util.Date FactDate;
	private java.math.BigDecimal SumCur;
	private java.math.BigDecimal SumNat;
	private java.lang.Short TransferKind;
	private java.lang.Short FixedPeriod;
	private boolean DocCreated;
	private boolean DocProcessed;
	private boolean Approved;


	// Constructors

	/** default constructor */
	public Execution() {
	}

	/** constructor with id */
	public Execution(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	/**

	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	/**

	 */
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority () {
		return this.CurRateAuthority;
	}

	public void setCurRateAuthority (com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurRateAuthority = RefCurrencyRateAuthority;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.ResourceFolder") //$NON-NLS-1$
	public com.mg.merp.core.model.Folder getResourceFolder () {
		return this.ResourceFolder;
	}

	public void setResourceFolder (com.mg.merp.core.model.Folder Folder) {
		this.ResourceFolder = Folder;
	}

	/**

	 */
	public com.mg.merp.document.model.DocHead getDocHead () {
		return this.DocHead;
	}

	public void setDocHead (com.mg.merp.document.model.DocHead Dochead) {
		this.DocHead = Dochead;
	}

	/**

	 */
	public com.mg.merp.paymentcontrol.model.VersionStatus getVerStatus () {
		return this.VerStatus;
	}

	public void setVerStatus (com.mg.merp.paymentcontrol.model.VersionStatus PmcVerstatus) {
		this.VerStatus = PmcVerstatus;
	}

	/**

	 */
	public com.mg.merp.paymentcontrol.model.Liability getLiability () {
		return this.Liability;
	}

	public void setLiability (com.mg.merp.paymentcontrol.model.Liability PmcLiability) {
		this.Liability = PmcLiability;
	}

	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**

	 */
	public com.mg.merp.reference.model.CurrencyRateType getCurRateType () {
		return this.CurRateType;
	}

	public void setCurRateType (com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurRateType = RefCurrencyRateType;
	}

	/**

	 */
	public com.mg.merp.paymentalloc.model.Payment getPayment () {
		return this.Payment;
	}

	public void setPayment (com.mg.merp.paymentalloc.model.Payment PmaPayment) {
		this.Payment = PmaPayment;
	}

	/**

	 */
	public com.mg.merp.reference.model.Currency getCurCode () {
		return this.CurCode;
	}

	public void setCurCode (com.mg.merp.reference.model.Currency Currency) {
		this.CurCode = Currency;
	}

	/**

	 */
	public com.mg.merp.paymentcontrol.model.Version getVersion () {
		return this.Version;
	}

	public void setVersion (com.mg.merp.paymentcontrol.model.Version PmcVersion) {
		this.Version = PmcVersion;
	}

	/**

	 */
	public com.mg.merp.paymentcontrol.model.PmcResource getResource () {
		return this.Resource;
	}

	public void setResource (com.mg.merp.paymentcontrol.model.PmcResource PmcResource) {
		this.Resource = PmcResource;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.Receivable") //$NON-NLS-1$
	public boolean getReceivable () {
		return this.Receivable;
	}

	public void setReceivable (boolean Receivable) {
		this.Receivable = Receivable;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.PlanDate") //$NON-NLS-1$
	public java.util.Date getPlanDate () {
		return this.PlanDate;
	}

	public void setPlanDate (java.util.Date Plandate) {
		this.PlanDate = Plandate;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.FactDate") //$NON-NLS-1$
	public java.util.Date getFactDate () {
		return this.FactDate;
	}

	public void setFactDate (java.util.Date Factdate) {
		this.FactDate = Factdate;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.SumCur") //$NON-NLS-1$
	public java.math.BigDecimal getSumCur () {
		return this.SumCur;
	}

	public void setSumCur (java.math.BigDecimal Sumcur) {
		this.SumCur = Sumcur;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.SumNat") //$NON-NLS-1$
	public java.math.BigDecimal getSumNat () {
		return this.SumNat;
	}

	public void setSumNat (java.math.BigDecimal Sumnat) {
		this.SumNat = Sumnat;
	}

	/**

	 */
	public java.lang.Short getTransferKind () {
		return this.TransferKind;
	}

	public void setTransferKind (java.lang.Short Transferkind) {
		this.TransferKind = Transferkind;
	}

	/**

	 */
	public java.lang.Short getFixedPeriod () {
		return this.FixedPeriod;
	}

	public void setFixedPeriod (java.lang.Short Fixedperiod) {
		this.FixedPeriod = Fixedperiod;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.DocCreated") //$NON-NLS-1$
	public boolean getDocCreated () {
		return this.DocCreated;
	}

	public void setDocCreated (boolean Doccreated) {
		this.DocCreated = Doccreated;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.DocProcessed") //$NON-NLS-1$
	public boolean getDocProcessed () {
		return this.DocProcessed;
	}

	public void setDocProcessed (boolean Docprocessed) {
		this.DocProcessed = Docprocessed;
	}

	/**

	 */
	@DataItemName("PaymentControl.Execution.Approved") //$NON-NLS-1$
	public boolean getApproved () {
		return this.Approved;
	}

	public void setApproved (boolean Approved) {
		this.Approved = Approved;
	}

}