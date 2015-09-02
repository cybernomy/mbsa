/*
 * SalaryBusinessAddin.java
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
package com.mg.merp.salary.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.DeductionClass;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.salary.model.CalcList;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.TripleSumSign;

/**
 * ������� ����� ������-���������� ������� ��������
 * 
 * @author Artem V. Sharapov
 * @version $Id: SalaryBusinessAddin.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public abstract class SalaryBusinessAddin<T> extends AbstractBusinessAddin<T> {
	
	public final static String STAFF_LIST_PARAM = "STAFF_LIST_PARAM"; //$NON-NLS-1$
	public final static String FEE_MODEL_PARAM = "FEE_MODEL_PARAM"; //$NON-NLS-1$
	public final static String PAY_ROLL_PARAM = "PAY_ROLL_PARAM"; //$NON-NLS-1$
	public final static String CALC_LIST_PARAM = "CALC_LIST_PARAM"; //$NON-NLS-1$
	public final static String CALC_LIST_FEE_PARAM = "CALC_LIST_FEE_PARAM"; //$NON-NLS-1$
	public final static String POSITION_FILL_PARAM = "POSITION_FILL_PARAM"; //$NON-NLS-1$
	public final static String PERSONAL_ACCOUNT_PARAM = "PERSONAL_ACCOUNT_PARAM"; //$NON-NLS-1$
	public final static String BEGIN_DATE_PARAM = "BEGIN_DATE_PARAM"; //$NON-NLS-1$
	public final static String END_DATE_PARAM = "END_DATE_PARAM"; //$NON-NLS-1$
	public final static String PERIOD_BEGIN_DATE_PARAM = "PERIOD_BEGIN_DATE_PARAM"; //$NON-NLS-1$
	public final static String PERIOD_END_DATE_PARAM = "PERIOD_END_DATE_PARAM"; //$NON-NLS-1$
	public final static String COSTS_ANL_1_PARAM = "COSTS_ANL_1_PARAM"; //$NON-NLS-1$
	public final static String COSTS_ANL_2_PARAM = "COSTS_ANL_2_PARAM"; //$NON-NLS-1$
	public final static String COSTS_ANL_3_PARAM = "COSTS_ANL_3_PARAM"; //$NON-NLS-1$
	public final static String COSTS_ANL_4_PARAM = "COSTS_ANL_4_PARAM"; //$NON-NLS-1$
	public final static String COSTS_ANL_5_PARAM = "COSTS_ANL_5_PARAM"; //$NON-NLS-1$
	
	private StaffList staffList;
	private FeeModel feeModel;
	private PayRoll payRoll;
	private CalcList calcList;
	private CalcListFee calcListFee;
	private PositionFill positionFill;
	private PersonalAccount personalAccount;
	private Date beginDate;
	private Date endDate;
	private Date periodBeginDate;
	private Date periodEndDate;
	private CostsAnl costsAnl1;
	private CostsAnl costsAnl2;
	private CostsAnl costsAnl3;
	private CostsAnl costsAnl4;
	private CostsAnl costsAnl5;
	
	private Map<String, ? extends Object> contextParams;
	
	
	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		contextParams = params;
		staffList = (StaffList) params.get(STAFF_LIST_PARAM);
		feeModel = (FeeModel) params.get(FEE_MODEL_PARAM);
		payRoll = (PayRoll) params.get(PAY_ROLL_PARAM);
		calcList = (CalcList) params.get(CALC_LIST_PARAM);
		calcListFee = (CalcListFee) params.get(CALC_LIST_FEE_PARAM);
		positionFill = (PositionFill) params.get(POSITION_FILL_PARAM);
		personalAccount = (PersonalAccount) params.get(PERSONAL_ACCOUNT_PARAM);
		beginDate = (Date) params.get(BEGIN_DATE_PARAM);
		endDate = (Date) params.get(END_DATE_PARAM);
		periodBeginDate = (Date) params.get(PERIOD_BEGIN_DATE_PARAM);
		periodEndDate = (Date) params.get(PERIOD_END_DATE_PARAM);
		costsAnl1 = (CostsAnl) params.get(COSTS_ANL_1_PARAM);
		costsAnl2 = (CostsAnl) params.get(COSTS_ANL_2_PARAM);
		costsAnl3 = (CostsAnl) params.get(COSTS_ANL_3_PARAM);
		costsAnl4 = (CostsAnl) params.get(COSTS_ANL_4_PARAM);
		costsAnl5 = (CostsAnl) params.get(COSTS_ANL_5_PARAM);
	}
	

	/**
	 * @return the beginDate
	 */
	protected Date getBeginDate() {
		return this.beginDate;
	}

	/**
	 * �������� ��������� ������
	 * @return ��������� ������
	 */
	protected CalcList getCalcList() {
		return this.calcList;
	}

	/**
	 * @return the calcListFee
	 */
	protected CalcListFee getCalcListFee() {
		return this.calcListFee;
	}

	/**
	 * �������� ��������� ������� ������ 1-�� ������
	 * @return ��������� ������� ������ 1-�� ������
	 */
	protected CostsAnl getCostsAnl1() {
		return this.costsAnl1;
	}

	/**
	 * �������� ��������� ������� ������ 2-�� ������
	 * @return ��������� ������� ������ 2-�� ������
	 */
	protected CostsAnl getCostsAnl2() {
		return this.costsAnl2;
	}

	/**
	 * �������� ��������� ������� ������ 3-�� ������
	 * @return ��������� ������� ������ 3-�� ������
	 */
	protected CostsAnl getCostsAnl3() {
		return this.costsAnl3;
	}

	/**
	 * �������� ��������� ������� ������ 4-�� ������
	 * @return ��������� ������� ������ 4-�� ������
	 */
	protected CostsAnl getCostsAnl4() {
		return this.costsAnl4;
	}

	/**
	 * �������� ��������� ������� ������ 5-�� ������
	 * @return ��������� ������� ������ 5-�� ������
	 */
	protected CostsAnl getCostsAnl5() {
		return this.costsAnl5;
	}

	/**
	 * @return the endDate
	 */
	protected Date getEndDate() {
		return this.endDate;
	}

	/**
	 * �������� ������� ����������/���������
	 * @return ������� ����������/���������
	 */
	protected FeeModel getFeeModel() {
		return this.feeModel;
	}

	/**
	 * �������� ��������� ���������
	 * @return ��������� ���������
	 */
	protected PayRoll getPayRoll() {
		return this.payRoll;
	}

	/**
	 * @return the periodBeginDate
	 */
	protected Date getPeriodBeginDate() {
		return this.periodBeginDate;
	}

	/**
	 * @return the periodEndDate
	 */
	protected Date getPeriodEndDate() {
		return this.periodEndDate;
	}

	/**
	 * �������� ������� ���� ����������
	 * @return ������� ���� ����������
	 */
	protected PersonalAccount getPersonalAccount() {
		return this.personalAccount;
	}

	/**
	 * �������� ��������� ���������� �����������
	 * @return ��������� ���������� �����������
	 */
	protected PositionFill getPositionFill() {
		return this.positionFill;
	}

	/**
	 * �������� ������� ����������
	 * @return ������� ����������
	 */
	protected StaffList getStaffList() {
		return this.staffList;
	}
		
	
	/**
	 * �������� ������ ����������� �������� �� ����������� �� ��������� ����
	 * @param actualDate - ����
	 * @return ������ ����������� �������� �� ����������� �� ��������� ����
	 */
	protected BigDecimal getMinSalary(Date actualDate) {
		return SalaryHelper.getMinSalary(actualDate);
	}
	
	/**
	 * �������� ��������� ������� ������ ��������� � ������
	 * @param tariffCode - ��� �����������
	 * @return ���������(���� �������) ������� ������ ��������� � ������
	 */
	protected CostsAnlResult getCostsAnlFromTariff(String tariffCode) {
		return SalaryHelper.getCostsAnlFromTariff(tariffCode, positionFill, staffList, beginDate);
	}
	
	/**
	 * �������� �����
	 * @param deductionKindCode - ��� ���� ������ �� �����������
	 * @param actualDate - ����������� ����
	 * @param income - ����� ������. ���� ��� ��������� ������������ ����� ������, �� ��������� ����� ����� 0
	 * @return ����� ������ �� ����
	 */
	protected BigDecimal getDeduction(String deductionKindCode, Date actualDate, BigDecimal income) {
		return SalaryHelper.getDeduction(deductionKindCode, actualDate, income);
	}
	
	/**
	 * �������� ����� �������, ����������� �� ������ �����
	 * @param deductionClass - ��������� �������: NULL - ��� ������, ��� ����� ���������; 0 - "���"; 1 - "�� ����"; 2 - "�� �����"; 3 - "�� ����������"
	 * @param actualDate -  ����������� ����
	 * @param income - ����� ������. ���� ��� ��������� ������������ ����� ������, �� ������� ��������� �����, ���� ����� �� ����� �����������.
	 * @return ����� ������ �� ������ �����
	 */
	protected BigDecimal getFamilyDeductionSum(DeductionClass deductionClass, Date actualDate, BigDecimal income) {
		return SalaryHelper.getFamilyDeductionSum(deductionClass, actualDate, income, personalAccount);
	}
	
	/**
	 * �������� ����� ����������/��������� �� ����, �� ��������� �������
	 * @param feeCode - ��� ����������/���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param payRoll - ��������� ���������. ���� NULL, �� ��� ���� ��������� ����������
	 * @param positionFill - ���������� ���������. ���� NULL, �� ��� ���� ����������, ����������� �����������
	 * @return ����� ����������/��������� �� ����, �� ��������� �������
	 */
	protected BigDecimal getFeeSum(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
		return SalaryHelper.getFeeSum(feeCode, beginDate, endDate, payRoll, positionFill, personalAccount);
	}
	
	/**
	 * �������� ����� ����������/���������, ���� ������� ������� �� �������� "�������� �/�" ����������� �/�, �� ��������� ������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param payRoll - ��������� ���������. ���� null, �� ��� ���� ��������� ����������
	 * @param positionFill - ����������� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @param feeSign - ����, � ������� ������ ����������:
	 * 					TripleSumSign.NONE - ����� ��� �������� ����������. �������� �� ������ "����" ������������ � ����� �����, �������� �� ������ "�����" ���������� �� ����� �����;
	 * 					TripleSumSign.Plus - ������������ ������ ����������, �������� �� ������ "����";
	 * 					TripleSumSign.Minus - ������������ ������ ����������, �������� �� ������ "�����". ��������� > 0 !
	 * @return ����� ����������/���������, ���� ������� ������� �� �������� "�������� �/�" ����������� �/�, �� ��������� ������
	 */
	protected BigDecimal getIncludedFeeSum(Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill, TripleSumSign feeSign) {
		return SalaryHelper.getIncludedFeeSum(beginDate, endDate, payRoll, positionFill, feeSign, feeModel, personalAccount);
	}
	
	/**
	 * �������� ����� �/�, ���� ������� ������� �� �������� "�������� �/�" ����������� �/�, �� ��������� ������. ����������� ����� ����������.
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param payRoll - �/�. ���� null, �� ��� ���� �/�.
	 * @param payRollKindName - ������������ ���� �/�. ���� null, �� ��� ���� �/�.
	 * @param positionFillKindCode - ��� ���� ���������� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @param positionFill - ���������� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @param taxCalcKindCode - ��� ����� ������� ������� ��� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @param feeSign - ����, � ������� ������ ����������:
	 * 					TripleSumSign.NONE - ����� ��� �������� ����������. �������� �� ������ "����" ������������ � ����� �����, �������� �� ������ "�����" ���������� �� ����� �����;
	 * 					TripleSumSign.Plus - ������������ ������ ����������, �������� �� ������ "����";
	 * 					TripleSumSign.Minus - ������������ ������ ����������, �������� �� ������ "�����". ��������� > 0 !
	 * @return ����� �/�, ���� ������� ������� �� �������� "�������� �/�" ����������� �/�, �� ��������� ������
	 */
	protected BigDecimal getIncludedFeeSumEx(Date beginDate, Date endDate, PayRoll payRoll, String payRollKindName, String positionFillKindCode, PositionFill positionFill, String taxCalcKindCode, TripleSumSign feeSign) {
		return SalaryHelper.getIncludedFeeSumEx(beginDate, endDate, payRoll, payRollKindName, positionFillKindCode, positionFill, personalAccount, taxCalcKindCode, feeSign, feeModel);
	}
	
	/**
	 * �������� �/�, ���������� �� ���� ������ ��������, �� ��������� ������. ������������ ���������!
	 * @param feeCode - ��� �/�
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param payRoll - �/�. ���� null, �� ��� ���� �/�
	 * @param positionFill - ������������� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @return �/�, ���������� �� ���� ������ ��������, �� ��������� ������
	 */
	protected BigDecimal getLastFee(String feeCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
		return SalaryHelper.getLastFee(feeCode, beginDate, endDate, payRoll, positionFill, personalAccount);
	}
	
	/**
	 * �������� ���� ���������� � ����� �� ���� �����
	 * @param serviceKindCode - ��� �����
	 * @param actualDate - ����������� ����
	 * @return ���� ���������� � ����� �� ���� �����
	 */
	protected BigDecimal getLengthOfService(String serviceKindCode, Date actualDate) {
		return SalaryHelper.getLengthOfService(serviceKindCode, actualDate, personalAccount);
	}
	
	/**
	 * �������� ���������� ������, ������� �������� ��������� �� ��������� ���������, �� ���� ������ �������� �/�
	 * @param positionFill - ������������� ���������
	 * @return ���������� ������, ������� �������� ��������� �� ��������� ���������, �� ���� ������ �������� �/�
	 */
	protected BigDecimal getNumberOfRates(PositionFill positionFill) {
		return SalaryHelper.getNumberOfRates(positionFill);
	}
	
	/**
	 * �������� �������� �������� �/�
	 * @param paramCode - ��� ���������
	 * @return �������� �������� �/�
	 */
	protected Object getParam(String paramCode) {
		return SalaryHelper.getParam(paramCode, calcListFee);
	}
	
	/**
	 * �������� �������� �/�, ���������� �� ���� ������ ��������, �� ��������� ������. ������������ ���������!
	 * @param feeCode - ��� �/�
	 * @param paramCode - ��� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param payRoll - �/�. ���� null, �� ��� ���� �/�
	 * @param positionFill - ������������� ���������. ���� null, �� ��� ���� ����������, ����������� �����������
	 * @return �������� �/�, ���������� �� ���� ������ ��������, �� ��������� ������
	 */
	protected Object getParamFromLastFee(String feeCode, String paramCode, Date beginDate, Date endDate, PayRoll payRoll, PositionFill positionFill) {
		return SalaryHelper.getParamFromLastFee(feeCode, paramCode, beginDate, endDate, payRoll, positionFill, personalAccount);
	}
	
	/**
	 * �������� ��� ���� ���������� ��������� ���������
	 * @param positionFill - ������������� ���������
	 * @return ��� ���� ���������� ��������� ���������
	 */
	protected String getPositionKindCode(PositionFill positionFill) {
		return SalaryHelper.getPositionKindCode(positionFill);
	}
	
	/**
	 * �������� ��� ��������� ��������� ��� ��������� ���������
	 * @param positionFill - ������������� ���������
	 * @param actualDate - ����������� ����
	 * @return ��� ��������� ��������� ��� ��������� ���������
	 */
	protected String getStaffCategoryCode(PositionFill positionFill, Date actualDate) {
		return SalaryHelper.getStaffCategoryCode(positionFill, actualDate, staffList);
	}
	
	/**
	 * �������� �� ���� ����� �� �������� ����� ����������, ����������� �� ���� ������ �������� �/�
	 * @param tariffCode - ��� ������
	 * @return ����� �� �������� ����� ����������, ����������� �� ���� ������ �������� �/�
	 */
	protected BigDecimal getTariff(String tariffCode) {
		return SalaryHelper.getTariff(tariffCode, positionFill, staffList, beginDate, contextParams);
	}
	
	/**
	 * �������� ����� �������� ������� � ����, �� ��������� ���������� ���������, �� �������� ����
	 * @param positionFill - ������������� ���������
	 * @param actualDate - ����������� ����
	 * @return ����� �������� ������� � ����, �� ��������� ���������� ���������, �� �������� ����
	 */
	protected BigDecimal getWorkDaysNorm(PositionFill positionFill, Date actualDate) {
		return SalaryHelper.getWorkDaysNorm(positionFill, actualDate, staffList);
	}
	
	/**
	 * �������� ����� �������� ������� � �����, �� ��������� ���������� ���������, �� �������� ����
	 * @param positionFill - ������������� ���������
	 * @param actualDate - ����������� ����
	 * @return ����� �������� ������� � �����, �� ��������� ���������� ���������, �� �������� ����
	 */
	protected BigDecimal getWorkHoursNorm(PositionFill positionFill, Date actualDate) {
		return SalaryHelper.getWorkHoursNorm(positionFill, actualDate, staffList);
	}
	
	/**
	 * �������� ���������� � ������ �� ���������� ������� ������
	 * @param taxCode - ��� ������ �� �����������
	 * @param actualDate - ����������� ����
	 * @param scaleNumber - ����� �����
	 * @param income - ����� ������
	 * @return ���������� � ������ �� ���������� ������� ������
	 */
	protected TaxResult getTaxByIncome(String taxCode, Date actualDate, Integer scaleNumber, BigDecimal income) {
		return SalaryHelper.getTaxByIncome(taxCode, actualDate, scaleNumber, income);
	}
	
	/**
	 * �������� ���������� � ������ �� ������ ������
	 * @param taxCode - ��� ������ �� �����������
	 * @param actualDate - ����������� ����
	 * @param scaleNumber - ����� �����
	 * @param rateNumber - ����� ������
	 * @return ���������� � ������ �� ������ ������
	 */
	protected TaxResult getTaxByNumber(String taxCode, Date actualDate, Integer scaleNumber, Integer rateNumber) {
		return SalaryHelper.getTaxByNumber(taxCode, actualDate, scaleNumber, rateNumber);
	}
	
	/**
	 * @return true, ���� ��� ������ ��������� ������� ������ �� ������
	 */
	protected boolean isCostsAnlEmpty() {
		return SalaryHelper.isCostsAnlEmpty(costsAnl1, costsAnl2, costsAnl3, costsAnl4, costsAnl5);
	}
	
	/**
	 * �������� �������� ��������� �� ����������� �� ���� � ���� ������������
	 * @param constantCode - ��� ���������
	 * @param actualDate - ���� ������������
	 * @return �������� ��������� �� ����������� �� ���� � ���� ������������
	 */
	protected Object getConstant(String constantCode, Date actualDate) {
		return SalaryHelper.getConstantValue(constantCode, actualDate);
	}
	
	
	
	/**
	 * �������� ���������� ���� �� ��������� ������, ��� ������� � ������� ����� ���� ������ � ��������� ����� �������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ���� �� ��������� ������, ��� ������� � ������� ����� ���� ������ � ��������� ����� �������
	 */
	protected BigDecimal getDaysFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return TableHelper.getDaysFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
	}
	
	/**
	 * �������� ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 */
	protected BigDecimal getHoursFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return TableHelper.getHoursFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
	}
	
	/**
	 * �������� ���������� ���� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ���� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 */
	protected BigDecimal getDaysFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return TableHelper.getDaysFromTimeBoard(timeKindCode, positionFill, beginDate, endDate);
	}
	
	/**
	 * �������� ���������� ����� � ��������� ����� ������� �� ������, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ����� � ��������� ����� ������� �� ������, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 */
	public BigDecimal getHoursFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return TableHelper.getHoursFromTimeBoard(timeKindCode, positionFill, beginDate, endDate);
	}
	
	/**
	 * ���������� true, ���� ��� ���������� ����� ������ ����� �� ��������� ����
	 * @param positionFill - ���������� ���������
	 * @param actualDate - ����������� ����
	 * @return true, ���� ��� ���������� ����� ������ ����� �� ��������� ����
	 */
	protected  boolean isScheduleExists(PositionFill positionFill, Date actualDate) {
		return TableHelper.isScheduleExists(positionFill, actualDate, staffList);
	}

	/**
	 * ���������� true, ���� �� ��������� ���� ��������� ���� � ������
	 * @param positionFill - ���������� ���������
	 * @param actualDate - ���������� ���������
	 * @return true, ���� �� ��������� ���� ��������� ���� � ������
	 */
	protected boolean isTimeBoardExists(PositionFill positionFill, Date actualDate) {
		return TableHelper.isTimeBoardExists(positionFill, actualDate);
	}
		
}
