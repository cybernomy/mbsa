/*
 * PermissibleAccountsServiceLocal.java
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

import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.PermissibleAccounts;

/**
 * ������-��������� "���������� ��������������� ������"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @author Artem V. Sharapov
 * @version $Id: PermissibleAccountsServiceLocal.java,v 1.3 2009/03/03 15:15:02 sharapov Exp $
 */
public interface PermissibleAccountsServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PermissibleAccounts, Integer> {

	/**
	 * �������� �� ������ ���. ��������
	 * 
	 * @param beginDate
	 * 				- � ����
	 * @param endDate
	 * 				- �� ����
	 */
   public void createFromEconomicOper(java.util.Date beginDate,java.util.Date endDate);

   /**
    * ��������� �������� ������������ ��������������� ������
    * @param economicSpec - �������� ���.��������
    */
   void ckeckPermissibleCorrespondance(EconomicSpec economicSpec);
   
}
