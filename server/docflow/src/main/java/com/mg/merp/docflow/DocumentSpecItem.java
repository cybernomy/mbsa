/*
 * DocumentSpecItem.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mg.merp.document.model.DocSpec;

/**
 * ������� ������������ ��������� ��� ��������� (������) ����������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentSpecItem.java,v 1.3 2007/12/14 08:46:31 safonov Exp $
 */
public class DocumentSpecItem {
	private DocSpec docSpec;
	//private BigDecimal initialFreeQuantity1;
	//private BigDecimal initialFreeQuantity2;
	//private BigDecimal initialFreeSum;
	private BigDecimal freeQuantity1;
	private BigDecimal freeQuantity2;
	private BigDecimal freeSum;
	private BigDecimal readyQuantity1;
	private BigDecimal readyQuantity2;
	private BigDecimal readySum;
	private BigDecimal performedQuantity1;
	private BigDecimal performedQuantity2;
	private BigDecimal performedSum;
	private Integer data1;
	private Integer data2;
	private Serializable stateValue;
	
	public DocumentSpecItem(DocSpec docSpec, BigDecimal freeQuantity1, BigDecimal freeQuantity2, BigDecimal freeSum,
			BigDecimal readyQuantity1, BigDecimal readyQuantity2, BigDecimal readySum) {
		this.docSpec = docSpec;
		this.freeQuantity1 = freeQuantity1;
		this.freeQuantity2 = freeQuantity2;
		this.freeSum = freeSum;
		this.readyQuantity1 = readyQuantity1;
		this.readyQuantity2 = readyQuantity2;
		this.readySum = readySum;
		//this.initialFreeQuantity1 = freeQuantity1.add(BigDecimal.ZERO);//create copy
		//this.initialFreeQuantity2 = freeQuantity2.add(BigDecimal.ZERO);//create copy
		//this.performedQuantity1 = freeQuantity1;
		//this.performedQuantity2 = freeQuantity2;
		//this.performedSum = freeSum;
		//this.performedQuantity1 = BigDecimal.ZERO;
		//this.performedQuantity2 = BigDecimal.ZERO;
		//this.performedSum = BigDecimal.ZERO;
	}
	
	/**
	 * �������� �������� ������������ ��� ������ ��
	 * 
	 * @param docSpec			������������ ���������
	 * @param readyQuantity1	������������ ���������� � ������ ��
	 * @param readyQuantity2	������������ ���������� �� ������ ��
	 * @param readySum			������������ �����
	 * @param data1				������ 1
	 * @param data2				������ 2
	 * @param stateValue		������������� ��������� ������������ ������������
	 */
	public DocumentSpecItem(DocSpec docSpec, BigDecimal readyQuantity1, BigDecimal readyQuantity2, BigDecimal readySum,
			Integer data1, Integer data2, Serializable stateValue) {
		this.docSpec = docSpec;
		this.readyQuantity1 = readyQuantity1;
		this.readyQuantity2 = readyQuantity2;
		this.readySum = readySum;
		this.data1 = data1;
		this.data2 = data2;
		this.stateValue = stateValue;
	}
	
	/**
	 * ������� ��������� ���������
	 * 
	 * @return	<code>true</code> ���� ��������� ���������
	 */
	public boolean isPartition() {
		//#2384 fixed, �������� �� ���������, ���������� �������� ����� ��������� ����������
		return freeQuantity1.compareTo(performedQuantity1) != 0 || freeQuantity2.compareTo(performedQuantity2) != 0;
	}
	
	/**
	 * �������� ��������� ���������� ���������������� 1, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #getStateValue()}
	 * 
	 * @return ��������� 1
	 */
	public Integer getData1() {
		return data1;
	}

	/**
	 * ���������� ��������� ���������� ���������������� 1, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #setStateValue()}
	 * 
	 * @param data1	��������� 1
	 */
	public void setData1(Integer data1) {
		this.data1 = data1;
	}

	/**
	 * �������� ��������� ���������� ���������������� 2, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #getStateValue()}
	 * 
	 * @return ��������� 2
	 */
	public Integer getData2() {
		return data2;
	}

	/**
	 * ���������� ��������� ���������� ���������������� 2, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #setStateValue()}
	 * 
	 * @param data1	��������� 2
	 */
	public void setData2(Integer data2) {
		this.data2 = data2;
	}

	/**
	 * �������� ���������� � ��������� (������) � ������ ��
	 * 
	 * @return ���������� � ��������� (������)
	 */
	public BigDecimal getPerformedQuantity1() {
		return performedQuantity1;
	}

	/**
	 * ���������� ���������� � ��������� (������) � ������ ��
	 * 
	 * @param performedQuantity1 ���������� � ��������� (������)
	 */
	public void setPerformedQuantity1(BigDecimal performedQuantity1) {
		this.performedQuantity1 = performedQuantity1;
	}

	/**
	 * �������� ���������� � ��������� (������) �� ������ ��
	 * 
	 * @return ���������� � ��������� (������)
	 */
	public BigDecimal getPerformedQuantity2() {
		return performedQuantity2;
	}

	/**
	 * ���������� ���������� � ��������� (������) �� ������ ��
	 * 
	 * @param performedQuantity2 ���������� � ��������� (������)
	 */
	public void setPerformedQuantity2(BigDecimal performedQuantity2) {
		this.performedQuantity2 = performedQuantity2;
	}

	/**
	 * �������� ����� � ��������� (������)
	 * 
	 * @return ����� � ��������� (������)
	 */
	public BigDecimal getPerformedSum() {
		return performedSum;
	}

	/**
	 * ���������� ����� � ��������� (������)
	 * 
	 * @param performedSum ����� � ��������� (������)
	 */
	public void setPerformedSum(BigDecimal performedSum) {
		this.performedSum = performedSum;
	}

	/**
	 * �������� ��������� ���������� ����������������, ��� ������� ������������ � ����������
	 * ������ ��� �������������� ��������� �������
	 * 
	 * @return ��������� ����������
	 */
	public Serializable getStateValue() {
		return stateValue;
	}

	/**
	 * ���������� ��������� ���������� ����������������, ������������ ��� ����������
	 * ����� ���������� ���������� ��� ��������� ������������, ������ ���������� ����� ��������
	 * ��� ���������� ������
	 * 
	 * @param stateValue ��������� ����������
	 */
	public void setStateValue(Serializable specStateValue) {
		this.stateValue = specStateValue;
	}

	/**
	 * �������� ������������ ���������
	 * 
	 * @return ������������ ���������
	 */
	public DocSpec getDocSpec() {
		return docSpec;
	}

	/**
	 * �������� ���������� � ������ �� �� ������������ � ������� ����� ����������������, ������ ��������
	 * ����� �� ��������� � ����������� ��� ��������� �� �������� ����� (��. {@link #getPerformedQuantity1()})
	 * 
	 * @return �� ������������ ����������
	 */
	public BigDecimal getFreeQuantity1() {
		return freeQuantity1;
	}

	/**
	 * �������� ���������� �� ������ �� �� ������������ � ������� ����� ����������������, ������ ��������
	 * ����� �� ��������� � ����������� ��� ��������� �� �������� ����� (��. {@link #getPerformedQuantity2()})
	 * 
	 * @return �� ������������ ����������
	 */
	public BigDecimal getFreeQuantity2() {
		return freeQuantity2;
	}

	/**
	 * �������� ����� ������������ �� ������������ � ������� ����� ����������������, ������ ��������
	 * ����� �� ��������� � ������ ��� ��������� �� �������� ����� (��. {@link #getPerformedSum()})
	 * 
	 * @return �� ������������ ����� ������������
	 */
	public BigDecimal getFreeSum() {
		return freeSum;
	}

	/**
	 * �������� ���������� � ������ �� ������������ � ������� ����� ����������������
	 * 
	 * @return ������������ ����������
	 */
	public BigDecimal getReadyQuantity1() {
		return readyQuantity1;
	}

	/**
	 * �������� ���������� �� ������ �� ������������ � ������� ����� ����������������
	 * 
	 * @return ������������ ����������
	 */
	public BigDecimal getReadyQuantity2() {
		return readyQuantity2;
	}

	/**
	 * �������� ����� ������������ ������������ � ������� ����� ����������������
	 * 
	 * @return ������������ ����� ������������
	 */
	public BigDecimal getReadySum() {
		return readySum;
	}

}
