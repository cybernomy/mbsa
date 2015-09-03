/*
 * ExecutionDlg.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.reference.model.Currency;

/**
 * Контроллер диалога "Исполнить обязательство"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ExecutionDlg.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class ExecutionDlg extends DefaultDialog {
	
	private String resourceName;
	private BigDecimal sumToExecute;
	private Currency executionCur;
	private Date executionDate;
	private Date dateToExecute;
	
	public ExecutionDlg() {
	}

	
	/**
	 * @return the dateToExecute
	 */
	public Date getDateToExecute() {
		return dateToExecute;
	}
	
	/**
	 * @param dateToExecute the dateToExecute to set
	 */
	public void setDateToExecute(Date dateToExecute) {
		this.dateToExecute = dateToExecute;
	}
	
	/**
	 * @return the executionDate
	 */
	public Date getExecutionDate() {
		return executionDate;
	}
	
	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	
	/**
	 * @return the sumToExecute
	 */
	public BigDecimal getSumToExecute() {
		return sumToExecute;
	}
	
	/**
	 * @param sumToExecute the sumToExecute to set
	 */
	public void setSumToExecute(BigDecimal sumToExecute) {
		this.sumToExecute = sumToExecute;
	}

	/**
	 * @return the executionCur
	 */
	public Currency getExecutionCur() {
		return executionCur;
	}

	/**
	 * @param executionCur the executionCur to set
	 */
	public void setExecutionCur(Currency executionCur) {
		this.executionCur = executionCur;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
