/*
 * ExecutionParams.java
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

import com.mg.merp.reference.model.Currency;

/**
 * Модель данных отображения параметров в диалоге "исполнение обязательства"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ExecutionParams.java,v 1.1 2007/05/14 05:12:10 sharapov Exp $
 */
public class ExecutionParams {

	// Fields
	
	private String resourceName;
	private BigDecimal sumToExecute;
	private Currency executionCur;
	private Date actDate;
	private Date dateToExecute;
	
	// Default constructor
	public ExecutionParams() {
	}
	
	// Property accessors
	
	public Date getActDate() {
		return actDate;
	}
	
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	
	public Date getDateToExecute() {
		return dateToExecute;
	}
	
	public void setDateToExecute(Date dateToExecute) {
		this.dateToExecute = dateToExecute;
	}
	
	public Currency getExecutionCur() {
		return executionCur;
	}
	
	public void setExecutionCur(Currency executionCur) {
		this.executionCur = executionCur;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public BigDecimal getSumToExecute() {
		return sumToExecute;
	}
	
	public void setSumToExecute(BigDecimal sumToExecute) {
		this.sumToExecute = sumToExecute;
	}
	
}
