/*
 * CatalogGroupsType.java
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
package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ��� ������ ������� ���
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogGroupsType.java,v 1.2 2008/06/30 04:15:16 alikaev Exp $
 */
@DataItemName ("Overall.Spec.CatalogGroupsType")
public enum CatalogGroupsType {
	/**
	 * ����������� ������
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.Overall")
	OVERALL,
	
	/**
	 * ��������� ������
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.FromClothes")
	FORMCLOTHES,
	
	/**
	 * �������������� �������� ������
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.Protection")
	PROTECTION,
	
	/**
	 * �����������
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.Instrument")
	INSTRUMENT,
	
	/**
	 * ����������, ������
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.OfficeEquip")
	OFFICEEQUIP,
	
	/**
	 * ������ ���
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#CatalogGroupsType.Other")
	OTHER	
	
}
