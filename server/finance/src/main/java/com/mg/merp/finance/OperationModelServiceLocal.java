/*
 * OperationModelServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.finance.model.OperationModel;

/**
 * ������-��������� "������� ���������� ��������"
 * 
 * @author leonova
 * @version $Id: OperationModelServiceLocal.java,v 1.3 2007/10/08 14:24:15 safonov Exp $
 */
public interface OperationModelServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<OperationModel, Integer> {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/finance/OperationModel";
	/**
	 * ��� ����� ��� �������� ���������� ��������
	 */
	final static short FOLDER_PART = 53;
}
