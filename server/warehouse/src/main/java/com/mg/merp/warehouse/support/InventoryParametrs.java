/*
 * InventoryParametrs.java
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
package com.mg.merp.warehouse.support;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;

/**
 * ��������� ��������������
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: InventoryParametrs.java,v 1.3 2007/11/29 14:02:00 alikaev Exp $
 */
public class InventoryParametrs {
	
	/**
	 * ������� ������������ ����� �� �������(� ������ ����)
	 */
	@DataItemName("InventoryParams.StockInvKind")
	public static enum StockInventoryKind { 
		/**
		 * ���� ������ ��� �� ����� ���� �������
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.StockInvKind.OnePriceKind") 
		ONEPRICEKIND, 
		
		/**
		 *���� ������ ��� �� ���� ����� ������� 
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.StockInvKind.AgregatePriceKind") 
		AGREGATEPRICEKIND
	}

	/**
	 * 	������� ������������ ����� �� �������(� ������ ���-��)
	 */
	@DataItemName("InventoryParams.MolInvKind")
	public static enum MolInventoryKind {
		/**
		 * ���� ������ ��� �� ������ ���-� ������ ��������������
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.MolInvKind.OnePriceKind") 
		ONEMOLKIND, 
		
		/**
		 * ���� ������ ��� �� ���� ���-�� ������ ��������������
		 */
		@EnumConstantText ("resource://com.mg.merp.warehouse.resources.dataitemlabels#InventoryParams.MolInvKind.AgregatePriceKind") 
		AGREGATEMOLKIND
		
	}

	/**
	 * 	����� ��������������
	 */
	private OrgUnit stock;
	
	/**
	 *	������ ��� - �� ������ ��������������
	 */
	private Contractor mol;

	/**
	 *	���� ��������������
	 */
	private Date endDate;

	/**
	 * ������ ��������� ����� ������� ��������
	 */
	private String beginCode;

	/**
	 * ����� ��������� ����� ������� ��������
	 */
	private String endCode;

	/**
	 * 	������� ������������ ����� �� �������(� ������ ���)
	 */
	private StockInventoryKind stockKind;
	
	/**
	 * 	������� ������������ ����� �� �������(� ������ ���-��)
	 */
	private MolInventoryKind molKind;

	/**
	 * 	������� ��������� ������� � ������� ��������
	 */
	private boolean isIncludeEmpty;

	/**
	 * 	������� �������� ������������ �� ���� �������������� ����� ��������������� 
	 */
	private boolean isDeleteSpecList;

	
	public InventoryParametrs() {
	}
	
	/**
	 * ����������� ��� ������� ���������� ��������������
	 * 
	 * @param stock				����� ��������������
	 * @param molList			������ ��� - �� ������
	 * @param endDate			���� ��������������
	 * @param beginCode			������ ��������� ����� ������� ��������
	 * @param endCode			����� ��������� ����� ������� ��������
	 * @param stockKind			������� ������������ ����� �� �������:
	 *								0 - ���� ������ ��� �� ����� ���� �������;
	 *								1 - ���� ������ ��� �� ���� ����� �������;
	 * @param molKind			������� ������������ ����� �� �������:
	 *								0 - ���� ������ ��� �� ������ ���-� ������ ��������������;
	 *								1 - ���� ������ ��� �� ���� ���-�� ������ ��������������;
	 * @param isIncludeEmpty	������� ��������� ������� � ������� ��������
	 * @param isDeleteSpecList	������� �������� ������������ �� ���� �������������� ����� ��������������� 
	 */
	public InventoryParametrs(OrgUnit stock, Contractor mol,
			Date endDate, String beginCode, String endCode, StockInventoryKind stockKind,
			MolInventoryKind molKind, boolean isIncludeEmpty, boolean isDeleteSpecList) {
		this.stock = stock;
		this.mol = mol;
		this.endDate = endDate;
		this.beginCode = beginCode;
		this.endCode = endCode;
		this.stockKind = stockKind;
		this.molKind = molKind;
		this.isIncludeEmpty = isIncludeEmpty;
		this.isDeleteSpecList = isDeleteSpecList;
	}

	/**
	 * ���������� ����� 
	 * 
	 * @return stock	�����
	 */
	public OrgUnit getStock() {
		return stock;
	}

	/**
	 * ������������� �������� ������
	 * 
	 * @param stock ���������� stock
	 */
	public void setStock(OrgUnit stock) {
		this.stock = stock;
	}

	/**
	 * ���������� ������ ��� - �� ������ �� ������� ������������� ��������������
	 * 
	 * @return molList	
	 */
	public Contractor getMol() {
		return mol;
	}

	/**
	 * ������������� ������ ��� - �� ������ �� ������� ������������� ��������������
	 * @param molList ���������� molList
	 */
	public void setMol(Contractor mol) {
		this.mol = mol;
	}

	/**
	 * ���������� ���� ��������������
	 * 
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * ������������� ���� ��������������
	 * 
	 * @param endDate ���������� endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * ���������� ������ ��������� ����� ������� ��������
	 * 
	 * @return beginCode
	 */
	public String getBeginCode() {
		return beginCode;
	}

	/**
	 * ������������� ������ ��������� ����� ������� ��������
	 * 
	 * @param beginCode ���������� beginCode
	 */
	public void setBeginCode(String beginCode) {
		this.beginCode = beginCode;
	}

	/**
	 * ���������� ����� ��������� ����� ������� ��������
	 * @return endCode
	 */
	public String getEndCode() {
		return endCode;
	}

	/**
	 * ������������� ����� ��������� ����� ������� ��������
	 * 
	 * @param endCode ���������� endCode
	 */
	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	/**
	 * ��������� ������� ������������ ����� �� �������(�� �����)
	 * 
	 * @return stockKind
	 */
	public StockInventoryKind getStockKind() {
		return stockKind;
	}

	/**
	 * ������������� ������� ������������ ����� �� �������(�� �����)
	 * 
	 * @param stockKind ���������� stockKind
	 */
	public void setStockKind(StockInventoryKind stockKind) {
		this.stockKind = stockKind;
	}

	/**
	 * ���������� ������� ������������ ����� �� �������(�� ���-��)
	 * 
	 * @return molKind
	 */
	public MolInventoryKind getMolKind() {
		return molKind;
	}

	/**
	 * ������������� ������� ������������ ����� �� �������(�� ���-��)
	 * 
	 * @param molKind ���������� molKind
	 */
	public void setMolKind(MolInventoryKind molKind) {
		this.molKind = molKind;
	}

	/**
	 * ���������� ������� ��������� ������� � ������� ��������
	 * 
	 * @return isIncludeEmpty
	 */
	public boolean isIncludeEmpty() {
		return isIncludeEmpty;
	}

	/**
	 * ������������� ������� ��������� ������� � ������� ��������
	 * 
	 * @param isIncludeEmpty ���������� isIncludeEmpty
	 */
	public void setIncludeEmpty(boolean isIncludeEmpty) {
		this.isIncludeEmpty = isIncludeEmpty;
	}

	/**
	 * ���������� ������� �������� ������������ �� ���� �������������� ����� ���������������
	 * @return isDeleteSpecList
	 */
	public boolean isDeleteSpecList() {
		return isDeleteSpecList;
	}

	/** 
	 * ������������� ������� �������� ������������ �� ���� �������������� ����� ���������������
	 * 
	 * @param isDeleteSpecList ���������� isDeleteSpecList
	 */
	public void setDeleteSpecList(boolean isDeleteSpecList) {
		this.isDeleteSpecList = isDeleteSpecList;
	}


}

