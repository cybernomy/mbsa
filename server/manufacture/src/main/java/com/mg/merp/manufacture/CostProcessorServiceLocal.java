/*
 * CostProcessorServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture;

import java.util.Date;

/**
 * ������-�������� "������ ������������� ������� �������"
 * 
 * @author leonova
 * @version $Id: CostProcessorServiceLocal.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
public interface CostProcessorServiceLocal extends com.mg.framework.api.BusinessObjectService
{
	static final String SERVICE_NAME = "merp/manufacture/CostProcessor";
	
	/**
	 * ��������� �������� ������������� ��� ������� �������
	 * 
	 * @param actualityDate	���� ������������
	 */
	void calculateBOMStandartCost(Date actualityDate );

	/**
	 * ��������� �������� ������������� ��� ���
	 * 
	 * @param actualityDate	���� ������������
	 */
	void calculateJobStandartCost(Date actualityDate );
	
}
