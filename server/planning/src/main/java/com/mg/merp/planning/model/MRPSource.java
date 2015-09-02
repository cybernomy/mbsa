/*
 * MRPSource.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: MRPSource.java,v 1.1 2007/07/30 10:37:30 safonov Exp $
 */
@DataItemName ("Planning.MRPSource")
public enum MRPSource {
	/**
	 * ������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourceInventory")
	INVENTORY,	
	/**
	 * ���
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourceJobs")
	JOBS,	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourcePurchases")
	PURCHASES,	
	/**
	 * �������
	 */	
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourceSales")
	SALES,	
	/**
	 * �����������
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourceTransfers")	
	TRANSFERS,
	
	/**
	 * ���
	 */	
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#Plan.MRPRec.SourceMRP.MRPSourceManufacturing")
	MANUFACTURING	
}