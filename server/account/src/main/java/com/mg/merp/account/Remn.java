/*
 * Remn.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;

/**
 * ������-��������� "������� � ������� �� ������ ���. �����"
 * 
 * @author pashistova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: Remn.java,v 1.4 2008/03/21 10:46:18 alikaev Exp $
 */
public interface Remn {

	public byte[] loadEconomicOperDbBrowse(int remnId) throws ApplicationException;

	public byte[] loadEconomicOperKtBrowse(int remnId) throws ApplicationException;

	public byte[] loadEconomicOperDbKtBrowse(int remnId) throws ApplicationException;

	/**
	 * ������� �������� ���. �����
	 * @param periodFrom - ��������� ������
	 * @param periodTo - �������� ������
	 * @param allAcc - ��� �����
	 * @param accList - ������ ��������� ������
	 */
	void carryForward(Period periodFrom, Period periodTo, boolean allAcc, AccPlan[] accList);
	
	/**
	 * �������� ������ �����
	 * 
	 * @param periodFrom
	 * 				- ��������� ������
	 * @param periodTo
	 * 				- �������� ������
	 */
	void removeEmptyRecords(Period periodFrom, Period periodTo);
	
}
