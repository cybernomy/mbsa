/*
 * ContractorCard.java
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
package com.mg.merp.settlement.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ContractorCard.java,v 1.6 2006/10/27 07:21:36 leonova Exp $
 */
public class ContractorCard extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.reference.model.Contractor OrgUnit;

	private java.math.BigDecimal TotalIncome;

	private java.math.BigDecimal TotalExpenses;

	private java.math.BigDecimal DebitorInDebLimit;

	private java.math.BigDecimal CreditorInDebLimit;

	private java.math.BigDecimal PlanIncome;

	private java.math.BigDecimal PlanExpenses;

	private java.math.BigDecimal DebitorInDebSum;

	private java.math.BigDecimal CreditorInDebSum;

	private java.math.BigDecimal PlanDebitorInDebSum;

	private java.math.BigDecimal PlanCreditorInDebSum;
	
	private java.util.Date dateFrom;
	
	private java.util.Date dateTill;
	
	private com.mg.merp.reference.model.Currency curCode;

	private java.util.Set SetOfContractorcardplan;

	private java.util.Set SetOfContractorcardhist;

	// Constructors

	/** default constructor */
	public ContractorCard() {
	}

	/** constructor with id */
	public ContractorCard(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.Contractor")
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Contractor getOrgUnit() {
		return this.OrgUnit;
	}

	public void setOrgUnit(com.mg.merp.reference.model.Contractor Contractor_1) {
		this.OrgUnit = Contractor_1;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.TotalIncome")
	public java.math.BigDecimal getTotalIncome() {
		return this.TotalIncome;
	}

	public void setTotalIncome(java.math.BigDecimal Totalincome) {
		this.TotalIncome = Totalincome;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.TotalExpenses")
	public java.math.BigDecimal getTotalExpenses() {
		return this.TotalExpenses;
	}

	public void setTotalExpenses(java.math.BigDecimal Totalexpenses) {
		this.TotalExpenses = Totalexpenses;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.DebitorInDebLimit")
	public java.math.BigDecimal getDebitorInDebLimit() {
		return this.DebitorInDebLimit;
	}

	public void setDebitorInDebLimit(java.math.BigDecimal Debitorindeblimit) {
		this.DebitorInDebLimit = Debitorindeblimit;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.CreditorInDebLimit")
	public java.math.BigDecimal getCreditorInDebLimit() {
		return this.CreditorInDebLimit;
	}

	public void setCreditorInDebLimit(java.math.BigDecimal Creditorindeblimit) {
		this.CreditorInDebLimit = Creditorindeblimit;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.PlanIncome")
	public java.math.BigDecimal getPlanIncome() {
		return this.PlanIncome;
	}

	public void setPlanIncome(java.math.BigDecimal Planincome) {
		this.PlanIncome = Planincome;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.PlanExpenses")
	public java.math.BigDecimal getPlanExpenses() {
		return this.PlanExpenses;
	}

	public void setPlanExpenses(java.math.BigDecimal Planexpenses) {
		this.PlanExpenses = Planexpenses;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.DebitorInDebSum")
	public java.math.BigDecimal getDebitorInDebSum() {
		return this.DebitorInDebSum;
	}

	public void setDebitorInDebSum(java.math.BigDecimal Debitorindebsum) {
		this.DebitorInDebSum = Debitorindebsum;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.CreditorInDebSum")
	public java.math.BigDecimal getCreditorInDebSum() {
		return this.CreditorInDebSum;
	}

	public void setCreditorInDebSum(java.math.BigDecimal Creditorindebsum) {
		this.CreditorInDebSum = Creditorindebsum;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.PlanDebitorInDebSum")
	public java.math.BigDecimal getPlanDebitorInDebSum() {
		return this.PlanDebitorInDebSum;
	}

	public void setPlanDebitorInDebSum(java.math.BigDecimal Plandebitorindebsum) {
		this.PlanDebitorInDebSum = Plandebitorindebsum;
	}

	/**
	 * 
	 */
	@DataItemName("Settlement.PlanCreditorInDebSum")
	public java.math.BigDecimal getPlanCreditorInDebSum() {
		return this.PlanCreditorInDebSum;
	}

	public void setPlanCreditorInDebSum(
			java.math.BigDecimal Plancreditorindebsum) {
		this.PlanCreditorInDebSum = Plancreditorindebsum;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfContractorcardplan() {
		return this.SetOfContractorcardplan;
	}

	public void setSetOfContractorcardplan(java.util.Set SetOfContractorcardplan) {
		this.SetOfContractorcardplan = SetOfContractorcardplan;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfContractorcardhist() {
		return this.SetOfContractorcardhist;
	}

	public void setSetOfContractorcardhist(java.util.Set SetOfContractorcardhist) {
		this.SetOfContractorcardhist = SetOfContractorcardhist;
	}

	public com.mg.merp.reference.model.Currency getCurCode() {
		return curCode;
	}

	public void setCurCode(com.mg.merp.reference.model.Currency curCode) {
		this.curCode = curCode;
	}

	public java.util.Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(java.util.Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public java.util.Date getDateTill() {
		return dateTill;
	}

	public void setDateTill(java.util.Date dateTill) {
		this.dateTill = dateTill;
	}

}