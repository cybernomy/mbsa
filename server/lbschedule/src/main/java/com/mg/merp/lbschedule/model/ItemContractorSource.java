/*
 * ItemContractorSource.java
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: ItemContractorSource.java,v 1.1 2006/03/30 11:27:50 safonov Exp $
 */
@DataItemName ("LbSchedule.Item.Source")
public enum ItemContractorSource {
	/**
	 * <>
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.None")
	NONE,
	
	/**
	 * �� ����
	 */	
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.From")
	FROM,
	
	/**
	 * ����
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.To")
	TO,
	
	/**
	 * ����� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.Through")
	THROUGH,
	
	/**
	 * �����-��������
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.StockFrom")
	STOCKFROM,
	
	/**
	 * �����-��������
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.StockTo")
	STOCKTO,	
	
	/**
	 * �����-��������
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.MolFrom")
	MOLFROM,
	
	/**
	 * �����-��������
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#ItemContractorSource.Type.MolTo")
	MOLTO	
}