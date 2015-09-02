/*
 * OperationServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance;

import com.mg.merp.core.model.Folder;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;

/**
 * ������ ������-���������� "���������� ��������"
 * 
 * @author Artem V. Sharapov
 * @version $ID$
 */
public interface OperationServiceLocal
extends com.mg.framework.api.DataBusinessObjectService<FinOperation, Integer>  {
	/**
	 * ��� ����� ��� ���������� ��������
	 */
	final static short FOLDER_PART = 40;

	/**
	 * �������� ���������� �������� �� �������
	 * @param pattern - �������
	 * @param folder - ����� ����������
	 * @return ���������� ��������
	 */
	FinOperation createByPattern(OperationModel pattern, Folder folder);
	
}
