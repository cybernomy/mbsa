/*
 * OperationServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.core.model.Folder;

/**
 * ������ ������-���������� "������������� ��������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: OperationServiceLocal.java,v 1.5 2008/03/27 07:32:19 alikaev Exp $
 */
public interface OperationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<EconomicOper, Integer> {
	
	/**
	 * ��� ����� ��� ������������� ��������
	 */
	final static short FOLDER_PART = 6;

	/**
	 * ����������� �� ��������������
	 * 
	 * @param economicOper
	 * 				- ���.��������
	 * @return
	 * 				- ���� ���. ��������
	 */
	EconomicOper storno(EconomicOper economicOper);

	/**
	 * �������� ���.�������� �� �������
	 * @param pattern - �������  
	 * @param folder - ����� ����������
	 * @return ���.��������
	 */
	EconomicOper createByPattern(EconomicOperModel pattern, Folder folder);

	/**
	 * �������� ���. �������� �� �������� "��������� ������� � ������������"
	 * 
	 * @param remnDbKt
	 * 				- ������� "��������� ������� � ������������"
	 * @param folder
	 * 				- �����-�������� ��� ����������� ���. ��������
	 */
	EconomicOper addFromRemnDbKt(RemnDbKt remnDbKt, Folder folder);

}
