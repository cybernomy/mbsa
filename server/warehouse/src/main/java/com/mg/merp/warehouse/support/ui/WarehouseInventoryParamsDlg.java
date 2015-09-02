/*
 * WarehouseInventoryParamsDlg.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.warehouse.support.InventoryParametrs;

/**
 * ���������� ������� "��������� �������������� ������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: WarehouseInventoryParamsDlg.java,v 1.2 2007/11/13 09:47:21 alikaev Exp $
 */
public class WarehouseInventoryParamsDlg extends DefaultDialog {

	/**
	 * ������ ��������� ����� ������� ��������
	 */
	private String catalogCodeFrom;

	/**
	 * ����� ��������� ����� ������� ��������
	 */
	private String catalogCodeTill;
	
	/**
	 * ������� ������������ ����� �� �������(� ������ ����)
	 */
	private InventoryParametrs.StockInventoryKind stockInventoryKind = InventoryParametrs.StockInventoryKind.ONEPRICEKIND;
	
	/**
	 * 	������� ������������ ����� �� �������(� ������ ���-��)
	 */
	private InventoryParametrs.MolInventoryKind molInventoryKind = InventoryParametrs.MolInventoryKind.ONEMOLKIND;
	
	/**
	 * 	������� �������� ������������ �� ���� �������������� ����� ��������������� 
	 */
	private boolean isDeleteSpecList;

	/**
	 * 	������� ��������� ������� � ������� ��������
	 */	
	private boolean isIncludePositionsWithZeroRemn;
	
	
	// Default constructor
	public WarehouseInventoryParamsDlg() {
	}
	
	// Property accessors
	
	public String getCatalogCodeFrom() {
		return this.catalogCodeFrom;
	}

	public void setCatalogCodeFrom(String catalogCodeFrom) {
		this.catalogCodeFrom = catalogCodeFrom;
	}

	public String getCatalogCodeTill() {
		return this.catalogCodeTill;
	}

	public boolean isDeleteSpecList() {
		return isDeleteSpecList;
	}


	public void setDeleteSpecList(boolean isDeleteSpecList) {
		this.isDeleteSpecList = isDeleteSpecList;
	}


	public void setCatalogCodeTill(String catalogCodeTill) {
		this.catalogCodeTill = catalogCodeTill;
	}

	public boolean isIncludePositionsWithZeroRemn() {
		return this.isIncludePositionsWithZeroRemn;
	}

	public void setIncludePositionsWithZeroRemn(boolean isIncludePositionsWithZeroRemn) {
		this.isIncludePositionsWithZeroRemn = isIncludePositionsWithZeroRemn;
	}

	public InventoryParametrs.StockInventoryKind getStockInventoryKind() {
		return stockInventoryKind;
	}


	public void setStockInventoryKind(
			InventoryParametrs.StockInventoryKind stockInventoryKind) {
		this.stockInventoryKind = stockInventoryKind;
	}


	public InventoryParametrs.MolInventoryKind getMolInventoryKind() {
		return molInventoryKind;
	}


	public void setMolInventoryKind(
			InventoryParametrs.MolInventoryKind molInventoryKind) {
		this.molInventoryKind = molInventoryKind;
	}

}
