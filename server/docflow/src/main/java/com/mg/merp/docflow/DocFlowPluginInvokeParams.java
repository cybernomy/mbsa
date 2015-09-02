/*
 * DocFlowPluginInvokeParams.java
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
import java.util.Date;
import java.util.List;

import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

/**
 * ��������� ������� (������) ����������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginInvokeParams.java,v 1.3 2008/09/01 07:44:22 safonov Exp $
 */
public class DocFlowPluginInvokeParams {
	private DocHead document;
	private BigDecimal performedSum;
	private DocProcessStage performedStage;
	private Date processDate;
	private boolean isSilent;
	private Integer data1;
	private Integer data2;
	private Serializable headStateValue;
	private List<DocumentSpecItem> specList;
	
	public DocFlowPluginInvokeParams(DocHead document, Date processDate, DocProcessStage performedStage, boolean isSilent, BigDecimal docSum, List<DocumentSpecItem> specList) {
		this.document = document;
		this.performedSum = docSum;
		this.performedStage = performedStage;
		this.processDate = processDate;
		this.isSilent = isSilent;
		this.specList = specList;
	}
	
	/**
	 * �������� �������������� ��������
	 * 
	 * @return ��������
	 */
	public DocHead getDocument() {
		return document;
	}

	/**
	 * �������� ����� ��������� (������)
	 * 
	 * @return	�����
	 */
	public BigDecimal getPerformedSum() {
		return performedSum;
	}
	
	/**
	 * ���������� ����� ��������� (������)
	 * 
	 * @param value	����� ��������� (������)
	 */
	public void setPerformedSum(BigDecimal value) {
		this.performedSum = value;
	}
	
	/**
	 * �������� ������� ���� ����������������
	 * 
	 * @return	������� ����
	 */
	public DocProcessStage getPerformedStage() {
		return performedStage;
	}
	
	/**
	 * �������� ���� ���������
	 * 
	 * @return	���� ���������
	 */
	public Date getProcessDate() {
		return processDate;
	}

	/**
	 * �������� ������� "������" ���������� ��, � ������ ���� �������� <code>true</code>, �� ����
	 * �� �� ������ ����������������� � ���������������� �����������, ���� ���������� �����������
	 * �� �������������� � �������������, �� ���������� ����� ������ ������������ �� {@link com.mg.merp.docflow.SilentException}
	 * 
	 * @return ������� "������" ���������� ��
	 */
	public boolean isSilent() {
		return isSilent;
	}

	/**
	 * ���������� ���� ���������
	 * 
	 * @param processDate ���� ���������
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	/**
	 * �������� ��������� ���������� ����������������, ��� ������� ������������ � ����������
	 * ������ ��� �������������� ��������� �������
	 * 
	 * @return ��������� ����������
	 */
	public Serializable getHeadStateValue() {
		return headStateValue;
	}

	/**
	 * ���������� ��������� ���������� ����������������, ������������ ��� ����������
	 * ����� ���������� ���������� ��� ��������� ���������, ������ ���������� ����� ��������
	 * ��� ���������� ������
	 * 
	 * @param headStateValue ��������� ����������
	 */
	public void setHeadStateValue(Serializable data) {
		this.headStateValue = data;
	}

	/**
	 * �������� ��������� ���������� ���������������� 1, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #getHeadStateValue()}
	 * 
	 * @return ��������� 1
	 */
	public Integer getData1() {
		return data1;
	}

	/**
	 * ���������� ��������� ���������� ���������������� 1, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #setHeadStateValue()}
	 * 
	 * @param data1	��������� 1
	 */
	public void setData1(Integer data1) {
		this.data1 = data1;
	}

	/**
	 * �������� ��������� ���������� ���������������� 2, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #getHeadStateValue()}
	 * 
	 * @return ��������� 2
	 */
	public Integer getData2() {
		return data2;
	}

	/**
	 * ���������� ��������� ���������� ���������������� 2, ������������ ��� �������������
	 * � ����������� ��������, ��� ���������� ����� ������ �� ������������� ������������
	 * {@link #setHeadStateValue()}
	 * 
	 * @param data1	��������� 2
	 */
	public void setData2(Integer data2) {
		this.data2 = data2;
	}

	/**
	 * �������� ������ ������������ ��������� ��������������� ��� ���������� � ����������������
	 * 
	 * @return ������ ������������
	 */
	public List<DocumentSpecItem> getSpecList() {
		if (specList == null)
			throw new IllegalStateException("Specification list is null");
		return specList;
	}
}
