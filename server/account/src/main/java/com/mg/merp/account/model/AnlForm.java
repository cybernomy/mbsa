/*
 * AnlForm.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ��� ������������� ���������
 * 
 * @author leonova
 * @version $Id: AnlForm.java,v 1.1 2006/03/30 11:22:13 safonov Exp $
 */
@DataItemName ("Account.Plan.AnlForm")
public enum AnlForm {
	/**
	 * ���
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.None")
	NONE,

	/**
	 * �������� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.BaseMeans")
	BASEMEANS,
	
	/**
	 * ����������� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Capital")
	CAPITAL,
	
	/**
	 * ���������, ������ ������� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.CalcCost")
	CALCCOST,
	
	/**
	 * ���������, ������ ���������� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.BatchCalc")
	BATCHCALC,
	
	/**
	 * ���������, ������ ������� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.AverageCost")
	AVERAGECOST,
	
	/**
	 * ���������, ������ FIFO
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.FIFO")
	FIFO,
	
	/**
	 * ���������, ������ LIFO
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.LIFO")
	LIFO,
	
	/**
	 * ���
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.MBP")
	MBP,
	
	/**
	 * �������� �������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.TradeAdd")
	TRADEADD,
	
	/**
	 * ����������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Realisation")
	REALISARION,
	
	/**
	 * ��������-��������� (�� ���������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKBase")
	DKBASE,
	
	/**
	 * ��������-��������� (�� ��������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKContract")
	DKCONTRACT,
	
	/**
	 * ��������-��������� (�� ���������/��������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKBaseContract")
	DKBASECONTRACT,
	
	/**
	 * ��������-��������� (�� ��������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.DKPartner")
	DKPARTNER,
	
	/**
	 * ����������� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.ReportFace")
	REPORTFACE,
	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.Expense")
	EXPENSE,
	
	/**
	 * ������� ������� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.ExpenseFuture")
	EXPENSEFUTURE,
	
	/**
	 * �������� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlForm.MoneyMeans")
	MONEYMEANS
}     