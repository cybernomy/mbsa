/*
 * PlanPaymentItem.java
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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Модель данных позиции таблицы "Планирование платежей"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PlanPaymentItem.java,v 1.1 2007/05/14 05:12:10 sharapov Exp $
 */
public class PlanPaymentItem {

	// Fields
	
	private Integer id;
	private Integer level;
	private Integer periodId; 
	private String period;
	private Date dateFrom;
	private Date dateTill;
	private String currencyCode;
	private Integer resourceFolderId;
	private Integer saveResourceFolderId;
	private Integer resourceId;
	private String resource;
	private BigDecimal beginSaldo;
	private BigDecimal income;
	private BigDecimal expense;
	private BigDecimal endSaldo;
	
	// Constructors
	
	public PlanPaymentItem() {
	}
		
	public PlanPaymentItem(Integer id, Integer level, Integer periodId, String period, Date dateFrom, Date dateTill, String currencyCode, Integer resourceFolderId, Integer saveResourceFolderId, Integer resourceId, String resource, BigDecimal beginSaldo, BigDecimal income, BigDecimal expense, BigDecimal endSaldo) {
		this.id = id;
		this.level = level;
		this.periodId = periodId;
		this.period = period;
		this.dateFrom = dateFrom;
		this.dateTill = dateTill;
		this.currencyCode = currencyCode;
		this.resourceFolderId = resourceFolderId;
		this.saveResourceFolderId = saveResourceFolderId;
		this.resourceId = resourceId;
		this.resource = resource;
		this.beginSaldo = beginSaldo;
		this.income = income;
		this.expense = expense;
		this.endSaldo = endSaldo;
	}
	
	// Property accessors
	
	/**
	 * @return the beginSaldo
	 */
	public BigDecimal getBeginSaldo() {
		return beginSaldo;
	}

	/**
	 * @param beginSaldo the beginSaldo to set
	 */
	public void setBeginSaldo(BigDecimal beginSaldo) {
		this.beginSaldo = beginSaldo;
	}

	/**
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTill
	 */
	public Date getDateTill() {
		return dateTill;
	}

	/**
	 * @param dateTill the dateTill to set
	 */
	public void setDateTill(Date dateTill) {
		this.dateTill = dateTill;
	}

	/**
	 * @return the endSaldo
	 */
	public BigDecimal getEndSaldo() {
		return endSaldo;
	}

	/**
	 * @param endSaldo the endSaldo to set
	 */
	public void setEndSaldo(BigDecimal endSaldo) {
		this.endSaldo = endSaldo;
	}

	/**
	 * @return the expense
	 */
	public BigDecimal getExpense() {
		return expense;
	}

	/**
	 * @param expense the expense to set
	 */
	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the income
	 */
	public BigDecimal getIncome() {
		return income;
	}

	/**
	 * @param income the income to set
	 */
	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the periodId
	 */
	public Integer getPeriodId() {
		return periodId;
	}

	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @return the resourceFolderId
	 */
	public Integer getResourceFolderId() {
		return resourceFolderId;
	}

	/**
	 * @param resourceFolderId the resourceFolderId to set
	 */
	public void setResourceFolderId(Integer resourceFolderId) {
		this.resourceFolderId = resourceFolderId;
	}

	/**
	 * @return the resourceId
	 */
	public Integer getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the saveResourceFolderId
	 */
	public Integer getSaveResourceFolderId() {
		return saveResourceFolderId;
	}

	/**
	 * @param saveResourceFolderId the saveResourceFolderId to set
	 */
	public void setSaveResourceFolderId(Integer saveResourceFolderId) {
		this.saveResourceFolderId = saveResourceFolderId;
	}
	
}
