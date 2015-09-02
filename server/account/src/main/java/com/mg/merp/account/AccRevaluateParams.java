/*
 * AccRevaluateParams.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocType;

/**
 * ����� ��� �������� ���������� � ����� {@link #revaluate()} ������� ������-���������� "����������� ���������"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccRevaluateParams.java,v 1.1 2008/04/28 10:09:51 alikaev Exp $
 */
public class AccRevaluateParams implements Serializable {
	
	private Date revalDate;
	
	private Folder folder;
	
	private short kind;
	
	private BigDecimal value;
	
	private DocType docType;
	
	private String docNumber;
	
	private Date docDate;
	
	private DocType baseDocType;
	
	private String baseDocNumber;
	
	private Date baseDocDate;
	
	private AccPlan accPlan;
	
	private AnlPlan anl1;

	private AnlPlan anl2;
	
	private AnlPlan anl3;
	
	private AnlPlan anl4;
	
	private AnlPlan anl5;

	private boolean isOverestimation;
	
	/**
	 * �����������
	 * 
	 * @param revalDate
	 * 			- ���� �����
	 * @param folder
	 * 			- ����� �������� ��� ���-��������
	 * @param kind
	 * 			- �������: 0 - ������, 1 - �� �����, 2 - �� �����
	 * @param value
	 * 			- �������� �� ��������
	 * @param docType
	 * 			- ��� ���������
	 * @param docNumber
	 * 			- ����� ���������
	 * @param docDate
	 * 			- ���� ���������
	 * @param baseDocType
	 * 			- ��� ���������-���������
	 * @param baseDocNumber
	 * 			- ����� ���������-���������
	 * @param baseDocDate
	 * 			- ���� ���������-���������
	 * @param accPlan
	 * 			- ����
	 * @param anl1
	 * 			- ��������� 1-�� ������
	 * @param anl2
	 * 			- ��������� 2-�� ������
	 * @param anl3
	 * 			- ��������� 3-�� ������
	 * @param anl4
	 * 			- ��������� 4-�� ������
	 * @param anl5
	 * 			- ��������� 5-�� ������
	 * @param isOverestimation
	 * 			- <code>false</code> - ����������, <code>true</code> - ��������
	 */
	public AccRevaluateParams(Date revalDate, Folder folder, short kind, BigDecimal value,	DocType docType, String docNumber, Date docDate,
			DocType baseDocType, String baseDocNumber, Date baseDocDate, AccPlan accPlan, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3,
			AnlPlan anl4, AnlPlan anl5, boolean isOverestimation) {
		this.revalDate = revalDate;
		this.folder = folder;
		this.kind = kind;
		this.value = value;
		this.docType = docType;
		this.docNumber = docNumber;
		this.docDate = docDate;
		this.baseDocType = baseDocType;
		this.baseDocNumber = baseDocNumber;
		this.baseDocDate = baseDocDate;
		this.accPlan = accPlan;
		this.anl1 = anl1;
		this.anl2 = anl2;
		this.anl3 = anl3;
		this.anl4 = anl4;
		this.anl5 = anl5;
		this.isOverestimation = isOverestimation;
	}

	/**
	 * ���� �����
	 * 
	 * @return revalDate
	 */
	public Date getRevalDate() {
		return revalDate;
	}

	/**
	 * @param revalDate ���������� revalDate
	 */
	public void setRevalDate(Date revalDate) {
		this.revalDate = revalDate;
	}

	/**
	 * �������: 0 - ������, 1 - �� �����, 2 - �� �����
	 * @return kind
	 */
	public short getKind() {
		return kind;
	}

	/**
	 * @param kind ���������� kind
	 */
	public void setKind(short kind) {
		this.kind = kind;
	}

	/**
	 * �������� �� ��������
	 * @return value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value ���������� value
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * ��� ���������
	 * @return docType
	 */
	public DocType getDocType() {
		return docType;
	}

	/**
	 * @param docType ���������� docType
	 */
	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	/**
	 * ����� ���������
	 * 
	 * @return docNumber
	 */
	public String getDocNumber() {
		return docNumber;
	}

	/**
	 * @param docNumber ���������� docNumber
	 */
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	/**
	 * ���� ���������
	 * 
	 * @return docDate
	 */
	public Date getDocDate() {
		return docDate;
	}

	/**
	 * @param docDate ���������� docDate
	 */
	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	/**
	 * ��� ���������-���������
	 * 
	 * @return baseDocType
	 */
	public DocType getBaseDocType() {
		return baseDocType;
	}

	/**
	 * @param baseDocType ���������� baseDocType
	 */
	public void setBaseDocType(DocType baseDocType) {
		this.baseDocType = baseDocType;
	}

	/**
	 * ����� ���������-���������
	 * 
	 * @return baseDocNumber
	 */
	public String getBaseDocNumber() {
		return baseDocNumber;
	}

	/**
	 * @param baseDocNumber ���������� baseDocNumber
	 */
	public void setBaseDocNumber(String baseDocNumber) {
		this.baseDocNumber = baseDocNumber;
	}

	/**
	 * ���� ���������-���������
	 * 
	 * @return baseDocDate
	 */
	public Date getBaseDocDate() {
		return baseDocDate;
	}

	/**
	 * @param baseDocDate ���������� baseDocDate
	 */
	public void setBaseDocDate(Date baseDocDate) {
		this.baseDocDate = baseDocDate;
	}

	/**
	 * ����
	 * @return accPlan
	 */
	public AccPlan getAccPlan() {
		return accPlan;
	}

	/**
	 * @param accPlan ���������� accPlan
	 */
	public void setAccPlan(AccPlan accPlan) {
		this.accPlan = accPlan;
	}

	/**
	 * ��������� 1-�� ������
	 * @return anl1
	 */
	public AnlPlan getAnl1() {
		return anl1;
	}

	/**
	 * @param anl1 ���������� anl1
	 */
	public void setAnl1(AnlPlan anl1) {
		this.anl1 = anl1;
	}

	/**
	 * ��������� 2-�� ������
	 * 
	 * @return anl2
	 */
	public AnlPlan getAnl2() {
		return anl2;
	}

	/**
	 * @param anl2 ���������� anl2
	 */
	public void setAnl2(AnlPlan anl2) {
		this.anl2 = anl2;
	}

	/**
	 * ��������� 3-�� ������
	 * 
	 * @return anl3
	 */
	public AnlPlan getAnl3() {
		return anl3;
	}

	/**
	 * @param anl3 ���������� anl3
	 */
	public void setAnl3(AnlPlan anl3) {
		this.anl3 = anl3;
	}

	/**
	 * ��������� 4-�� ������
	 * 
	 * @return anl4
	 */
	public AnlPlan getAnl4() {
		return anl4;
	}

	/**
	 * @param anl4 ���������� anl4
	 */
	public void setAnl4(AnlPlan anl4) {
		this.anl4 = anl4;
	}

	/**
	 * ��������� 5-�� ������
	 * 
	 * @return anl5
	 */
	public AnlPlan getAnl5() {
		return anl5;
	}

	/**
	 * @param anl5 ���������� anl5
	 */
	public void setAnl5(AnlPlan anl5) {
		this.anl5 = anl5;
	}

	/**
	 *  <code>false</code> - ����������, <code>true</code> - ��������
	 *  
	 * @return isOverestimation
	 */
	public boolean isOverestimation() {
		return isOverestimation;
	}

	/**
	 * @param isOverestimation ���������� isOverestimation
	 */
	public void setOverestimation(boolean isOverestimation) {
		this.isOverestimation = isOverestimation;
	}

	/**
	 * ����� �������� ��� ���. ��������
	 * 
	 * @return folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder ���������� folder
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
}
