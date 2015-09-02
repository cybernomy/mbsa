package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ����� ���������� ��������� �������� �� ������� ����
 * 
 * @author leonova
 * @version $Id: LaborAllocationType.java,v 1.1 2006/06/21 05:13:37 leonova Exp $
 */
@DataItemName("MfReference.LaborAllocationType")
public enum LaborAllocationType {

	/**
	 * <>
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.None")
	NONE,
	/**
	 * �� ������� �������
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.UnitTime")
	UNITTIME,
	
	/**
	 * �� ������� ��
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.UnitProduction")
	UNITPRODUCTION,
	
	/**
	 * ������� �� ��������� �����
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.Percent")
	PERCENT,
	
	/**
	 * ������������� ��������� �� ������
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.PriceParty")
	PRICEPARTY
	
}