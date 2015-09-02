/*
 * BOMServiceLocal.java
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
package com.mg.merp.mfreference;

import java.util.Date;

import com.mg.merp.mfreference.model.Bom;

/**
 * ������-��������� "������ �������"
 * 
 * @author leonova
 * @version $Id: BOMServiceLocal.java,v 1.4 2007/08/06 12:16:10 safonov Exp $
 */
public interface BOMServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<Bom, Integer>
{
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/mfreference/BOM";

	/**
	 * ��� ����� ��� �������� �������
	 */
	final static short FOLDER_PART = 12001;

	/**
	 * ������ ���������� ������� ����������
	 * 
	 * @param actualityDate	���� ������������
	 * @param catalogList	������ ������� ��������
	 */
	void calculateOperLeadTimes(Date actualityDate, int[] catalogList);

	/**
	 * ����� �������� ������� �������
	 * 
	 * @param catalogId	������������� ������� ��������
	 * @return	������ ������� ��� <code>null</code> ���� �� ������
	 */
	Bom findCurrentBOM(int catalogId);

	/**
	 * ����� ������������ ������� �������
	 * 
	 * @param catalogId	������������� ������� ��������
	 * @return	������ ������� ��� <code>null</code> ���� �� ������
	 */
	Bom findStandartBOM(int catalogId);
	
	/**
	 * ��������� ���� ������� ����������� �������������
	 * 
	 * @param bom	������ �������
	 * @param date	���� �������
	 */
	void updateRollupDateTime(Bom bom, Date date);
	
}
