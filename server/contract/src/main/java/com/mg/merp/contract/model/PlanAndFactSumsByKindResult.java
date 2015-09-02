/*
 * PlanAndFactSumsByKindResult.java
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
package com.mg.merp.contract.model;

import java.math.BigDecimal;

/**
 * ��������� �������������� �� ����� �������, �������� ���������� � ����������� ����
 * 
 * @author Artem V. Sharapov
 * @version $Id: PlanAndFactSumsByKindResult.java,v 1.1 2007/07/16 10:12:50 sharapov Exp $
 */
public class PlanAndFactSumsByKindResult {

	private BigDecimal planShippedPayment;
	private BigDecimal planReceivedPayment;
	private BigDecimal planShippedGood;
	private BigDecimal planReceivedGood;
	
	private BigDecimal factShippedPayment;
	private BigDecimal factReceivedPayment;
	private BigDecimal factShippedGood;
	private BigDecimal factReceivedGood;
	
	
	/**
	 * @param planShippedPayment - ����� �������� �����������, �� �����
	 * @param planReceivedPayment - ����� �������� �� �����������, �� �����
	 * @param planShippedGood - ����� ��� � ����� �����������, �� �����
	 * @param planReceivedGood - ����� ��� � ����� �� �����������, �� �����
	 * @param factShippedPayment - ����� �������� �����������, ����������
	 * @param factReceivedPayment - ����� �������� �� �����������, ����������
	 * @param factShippedGood - ����� ��� � ����� �����������, ����������
	 * @param factReceivedGood - ����� ��� � ����� �� �����������, ����������
	 */
	public PlanAndFactSumsByKindResult(BigDecimal planShippedPayment, BigDecimal planReceivedPayment, BigDecimal planShippedGood, BigDecimal planReceivedGood, BigDecimal factShippedPayment, BigDecimal factReceivedPayment, BigDecimal factShippedGood, BigDecimal factReceivedGood) {
		this.planShippedPayment = planShippedPayment;
		this.planReceivedPayment = planReceivedPayment;
		this.planShippedGood = planShippedGood;
		this.planReceivedGood = planReceivedGood;
		this.factShippedPayment = factShippedPayment;
		this.factReceivedPayment = factReceivedPayment;
		this.factShippedGood = factShippedGood;
		this.factReceivedGood = factReceivedGood;
	}
	
	
	
	/**
	 * @return the factReceivedGood
	 */
	public BigDecimal getFactReceivedGood() {
		return this.factReceivedGood;
	}
	
	/**
	 * @return the factReceivedPayment
	 */
	public BigDecimal getFactReceivedPayment() {
		return this.factReceivedPayment;
	}
	
	/**
	 * @return the factShippedGood
	 */
	public BigDecimal getFactShippedGood() {
		return this.factShippedGood;
	}
	
	/**
	 * @return the factShippedPayment
	 */
	public BigDecimal getFactShippedPayment() {
		return this.factShippedPayment;
	}
	
	/**
	 * @return the planReceivedGood
	 */
	public BigDecimal getPlanReceivedGood() {
		return this.planReceivedGood;
	}
	
	/**
	 * @return the planReceivedPayment
	 */
	public BigDecimal getPlanReceivedPayment() {
		return this.planReceivedPayment;
	}
	
	/**
	 * @return the planShippedGood
	 */
	public BigDecimal getPlanShippedGood() {
		return this.planShippedGood;
	}
	
	/**
	 * @return the planShippedPayment
	 */
	public BigDecimal getPlanShippedPayment() {
		return this.planShippedPayment;
	}
	
}
