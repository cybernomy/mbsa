/*
 * FeeBAiResult.java
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

import com.mg.merp.personnelref.model.CostsAnl;

/**
 * ��������� ���������� BAi ������� ����������/��������� �� 
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeBAiResult.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public class FeeBAiResult {

	/**
	 * C���� �/�
	 */
	private BigDecimal sum;
	
	/**
	 * ��������� �
	 */
	private Date beginDate;
	
	/**
	 * ��������� ��
	 */
	private Date endDate;
	
	/**
	 * �� ������ �
	 */
	private Date periodBeginDate;
	
	/**
	 * �� ������ ��
	 */
	private Date periodEndDate;
	
	/**
	 * ��������� ������� ������ 1-�� ������
	 */
	private CostsAnl costsAnl1;
	
	/**
	 * ��������� ������� ������ 2-�� ������
	 */
	private CostsAnl costsAnl2;
	
	/**
	 * ��������� ������� ������ 3-�� ������
	 */
	private CostsAnl costsAnl3;
	
	/**
	 * ��������� ������� ������ 4-�� ������
	 */
	private CostsAnl costsAnl4;
	
	/**
	 * ��������� ������� ������ 5-�� ������
	 */
	private CostsAnl costsAnl5;

	
	public FeeBAiResult() {
	}
		
	/**
	 * ������� ��������� ���������� ������� ����������/��������� ��
	 * @param sum - c���� �/�
	 * @param beginDate - ��������� �
	 * @param endDate - ��������� ��
	 * @param periodBeginDate - �� ������ �
	 * @param periodEndDate - �� ������ ��
	 * @param costsAnl1 - ��������� ������� ������ 1-�� ������
	 * @param costsAnl2 - ��������� ������� ������ 2-�� ������
	 * @param costsAnl3 - ��������� ������� ������ 3-�� ������
	 * @param costsAnl4 - ��������� ������� ������ 4-�� ������
	 * @param costsAnl5 - ��������� ������� ������ 5-�� ������
	 */
	public FeeBAiResult(BigDecimal sum, Date beginDate, Date endDate, Date periodBeginDate, Date periodEndDate, CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
		this.sum = sum;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.periodBeginDate = periodBeginDate;
		this.periodEndDate = periodEndDate;
		this.costsAnl1 = costsAnl1;
		this.costsAnl2 = costsAnl2;
		this.costsAnl3 = costsAnl3;
		this.costsAnl4 = costsAnl4;
		this.costsAnl5 = costsAnl5;
	}

	
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return this.beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the costsAnl1
	 */
	public CostsAnl getCostsAnl1() {
		return this.costsAnl1;
	}

	/**
	 * @param costsAnl1 the costsAnl1 to set
	 */
	public void setCostsAnl1(CostsAnl costsAnl1) {
		this.costsAnl1 = costsAnl1;
	}

	/**
	 * @return the costsAnl2
	 */
	public CostsAnl getCostsAnl2() {
		return this.costsAnl2;
	}

	/**
	 * @param costsAnl2 the costsAnl2 to set
	 */
	public void setCostsAnl2(CostsAnl costsAnl2) {
		this.costsAnl2 = costsAnl2;
	}

	/**
	 * @return the costsAnl3
	 */
	public CostsAnl getCostsAnl3() {
		return this.costsAnl3;
	}

	/**
	 * @param costsAnl3 the costsAnl3 to set
	 */
	public void setCostsAnl3(CostsAnl costsAnl3) {
		this.costsAnl3 = costsAnl3;
	}

	/**
	 * @return the costsAnl4
	 */
	public CostsAnl getCostsAnl4() {
		return this.costsAnl4;
	}

	/**
	 * @param costsAnl4 the costsAnl4 to set
	 */
	public void setCostsAnl4(CostsAnl costsAnl4) {
		this.costsAnl4 = costsAnl4;
	}

	/**
	 * @return the costsAnl5
	 */
	public CostsAnl getCostsAnl5() {
		return this.costsAnl5;
	}

	/**
	 * @param costsAnl5 the costsAnl5 to set
	 */
	public void setCostsAnl5(CostsAnl costsAnl5) {
		this.costsAnl5 = costsAnl5;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the periodBeginDate
	 */
	public Date getPeriodBeginDate() {
		return this.periodBeginDate;
	}

	/**
	 * @param periodBeginDate the periodBeginDate to set
	 */
	public void setPeriodBeginDate(Date periodBeginDate) {
		this.periodBeginDate = periodBeginDate;
	}

	/**
	 * @return the periodEndDate
	 */
	public Date getPeriodEndDate() {
		return this.periodEndDate;
	}

	/**
	 * @param periodEndDate the periodEndDate to set
	 */
	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	/**
	 * @return the sum
	 */
	public BigDecimal getSum() {
		return this.sum;
	}

	/**
	 * @param sum the sum to set
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
}
