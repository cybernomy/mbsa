/*
 * SourceEconOperContractor.java
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

import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ��� ��������� � �������� ������������� ��������
 * 
 * @author leonova
 * @version $Id: SourceEconOperContractor.java,v 1.2 2008/03/13 06:19:46 alikaev Exp $
 */
public enum SourceEconOperContractor {
	/**
	 * <> 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.None")
	NONE,
	
	/**
	 * ���������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.Provider")
	PROVIDER,
	
	/**
	 * ���������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.Customer")
	CUSTOMER,
	
	/**
	 * ���������������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.Shipper")
	SHIPPER,
	
	/**
	 * ��������������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.Consignee")
	CONSIGNEE,
	
	/**
	 * ����� �������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.StockSrc")
	STOCKSRC,
	
	/**
	 * ����� �������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.StockDest")
	STOCKDEST,
	
	/**
	 * ��� �������� 
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.MOLSrc")
	MOLSRC,
	
	/**
	 * ��� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.MOLDest")
	MOLDEST,
	
	/**
	 * ����� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#SourceEconOperContractor.Type.Through")
	THROUGH
}
