/*
 * TurnAccFlatServiceLocal.java
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

import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.FinPeriod;
import com.mg.merp.finance.model.TurnAccount;

/**
 * ������-��������� "������� � ������� �� ������ ����������� �����"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TurnAccFlatServiceLocal.java,v 1.2 2006/12/26 13:15:40 sharapov Exp $
 */
public interface TurnAccFlatServiceLocal
extends com.mg.framework.api.DataBusinessObjectService<TurnAccount, Integer>
{
	/**
	 * ������� �������� ����������� �����
	 * @param periodFrom - � ���.�������
	 * @param periodTo - �� ���.������
	 * @param allAcc - �� ���� ������
	 * @param accList - ������ ��������� ������
	 */
	void carryForward(FinPeriod periodFrom, FinPeriod periodTo, boolean allAcc, Account[] accList);

}
