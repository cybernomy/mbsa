/*
 * InvHeadServiceLocal.java
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

import java.util.Date;
import java.util.List;

import com.mg.merp.account.model.AccKind;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.Period;

/**
 * ������-��������� "���������� ���������"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InvHeadServiceLocal.java,v 1.3 2008/04/28 10:09:51 alikaev Exp $
 */
public interface InvHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<InvHead, Integer> {

	/**
	 * ��� ����� ��� ����������� ���������
	 */
	final static short FOLDER_PART = 7;

	/**
	 * ���������� �����������
	 * 
	 * @param month
	 * 			- ����� ���������� ����������� � ���������� ���������� (���*12 + �����)
	 * @param invHeads
	 * 			- ����������� ��������
	 * @param accKind
	 * 			- ��� ����� �������� ������� � �������������� �������
	 * @return
	 */
	Integer calcAmortization(Short month, List<InvHead> invHeads, AccKind accKind);

	/**
	 * ����������/��������
	 * 
	 * @param invHeads
	 * 			- ������ ����������� ��������
	 * @param accKind
	 * 			- ��� �����
	 * @param accRevaluateParams
	 * 			- ��������� ��� ���������� ����������
	 */
	void revaluate(List<InvHead> invHeads, AccKind accKind, AccRevaluateParams accRevaluateParams);

	/**
	 * �����������
	 * 
	 * @param invHeads
	 * 			- ������ ����������� ��������
	 * @param accKind
	 * 			- ��� �����
	 * @param params
	 * 			- ��������� ��� ���������� �������� ����������� ����������� ��������
	 */
	void moveInventory(List<InvHead> invHeads, AccKind accKind, AccInventoryMoveParams params);

	/**
	 * ��������
	 * 
	 * @param invHeads
	 * 			- ������ ����������� ��������
	 * @param accKind
	 * 			- ��� �����
	 * @param params
	 * 			- ��������� ��� ���������� �������� �������� ����������� ��������
	 */
	void retire(List<InvHead> invHeads, AccKind accKind, AccInventoryRetireParams params);

	/**
	 * �����������
	 * 
	 * @param invHeads
	 * 			- ������ ����������� ��������
	 * @param accKind
	 * 			- ��� �����
	 * @param freezeDate
	 * 			- ���� �� ������� ������������ ����������� ��������
	 */
	void freeze(List<InvHead> invHeads, AccKind accKind, Date freezeDate);

	/**
	 * ������������ ��������
	 * 
	 * @param invHeads
	 * 				- ������ ����������� ��������
	 * @param accKind
	 * 				- ��� �����
	 * @param period
	 * 				- ������� ������
	 */
	void makeRemains(List<InvHead> invHeads, AccKind accKind, Period period);

	/**
	 * �������� ��������� ��������
	 * 
	 * @param invHeads
	 * 			- ����������� ���������
	 * @param accKinds
	 * 			- ������ ����� �����
	 */
	void rollback(List<InvHead> invHeads, List<AccKind> accKinds);

}
