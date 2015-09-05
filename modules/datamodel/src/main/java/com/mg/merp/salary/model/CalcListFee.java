/*
 * CalcListFee.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Начисления/ударжания расчетных листков"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CalcListFee.java,v 1.6 2007/08/21 05:27:58 sharapov Exp $
 */
public class CalcListFee extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;
	private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

	private com.mg.merp.salary.model.CalcListSection CalcListSection;
	private com.mg.merp.salary.model.FeeModel FeeModel;

	private java.util.Date BeginDate;
	private java.util.Date EndDate;

	private java.util.Date PeriodBeginDate;
	private java.util.Date PeriodEndDate;

	private java.math.BigDecimal Summa;

	private boolean NeedParams;
	private boolean IsCalculated;
	private boolean DontRecalc;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.util.Set<CalcListFeeParam> calcListFeeParams;

	
	// Constructors

	/** default constructor */
	public CalcListFee() {
	}

	/** constructor with id */
	public CalcListFee(java.lang.Integer Id) {
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
	@DataItemName("Salary.FeeRef.CostsAnl1") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
		return this.CostsAnl1;
	}

	public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
		this.CostsAnl1 = PrefCostsAnl;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRef.CostsAnl2") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
		return this.CostsAnl2;
	}

	public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
		this.CostsAnl2 = PrefCostsAnl_1;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRef.CostsAnl3") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
		return this.CostsAnl3;
	}

	public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
		this.CostsAnl3 = PrefCostsAnl_2;
	}

	/**
	 * 
	 */
	public com.mg.merp.salary.model.CalcListSection getCalcListSection() {
		return this.CalcListSection;
	}

	public void setCalcListSection(com.mg.merp.salary.model.CalcListSection SalCalcListSection) {
		this.CalcListSection = SalCalcListSection;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.FeeRef.CostsAnl5") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
		return this.CostsAnl5;
	}

	public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
		this.CostsAnl5 = PrefCostsAnl_3;
	}

	/**
	 * 
	 */
	public com.mg.merp.salary.model.FeeModel getFeeModel() {
		return this.FeeModel;
	}

	public void setFeeModel(com.mg.merp.salary.model.FeeModel SalFeeModel) {
		this.FeeModel = SalFeeModel;
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
	@DataItemName("Salary.FeeRef.CostsAnl4") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
		return this.CostsAnl4;
	}

	public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
		this.CostsAnl4 = PrefCostsAnl_4;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.Begindate") //$NON-NLS-1$
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.EndDate") //$NON-NLS-1$
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.PeriodBeginDate") //$NON-NLS-1$
	public java.util.Date getPeriodBeginDate() {
		return this.PeriodBeginDate;
	}

	public void setPeriodBeginDate(java.util.Date PeriodBegindate) {
		this.PeriodBeginDate = PeriodBegindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.PeriodEndDate") //$NON-NLS-1$
	public java.util.Date getPeriodEndDate() {
		return this.PeriodEndDate;
	}

	public void setPeriodEndDate(java.util.Date PeriodEnddate) {
		this.PeriodEndDate = PeriodEnddate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.Summa") //$NON-NLS-1$
	public java.math.BigDecimal getSumma() {
		return this.Summa;
	}

	public void setSumma(java.math.BigDecimal Summa) {
		this.Summa = Summa;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.NeedParams") //$NON-NLS-1$
	public boolean getNeedParams() {
		return this.NeedParams;
	}

	public void setNeedParams(boolean NeedParams) {
		this.NeedParams = NeedParams;
	}

	/**
	 * 
	 */
	public boolean getIsCalculated() {
		return this.IsCalculated;
	}

	public void setIsCalculated(boolean IsCalculated) {
		this.IsCalculated = IsCalculated;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.CalcListFee.DontRecalc") //$NON-NLS-1$
	public boolean getDontRecalc() {
		return this.DontRecalc;
	}

	public void setDontRecalc(boolean DontRecalc) {
		this.DontRecalc = DontRecalc;
	}

	/**
	 * Получить список параметров начисления/удержания расчетного листка
	 * @return список параметров начисления/удержания расчетного листка
	 */
	public java.util.Set<CalcListFeeParam> getCalcListFeeParams() {
		return this.calcListFeeParams;
	}

	/**
	 * @param calcListFeeParams the calcListFeeParams to set
	 */
	public void setCalcListFeeParams(java.util.Set<CalcListFeeParam> calcListFeeParams) {
		this.calcListFeeParams = calcListFeeParams;
	}

}