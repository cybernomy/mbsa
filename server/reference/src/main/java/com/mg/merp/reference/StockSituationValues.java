/* StockSituationValues.java
*
* Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import java.math.BigDecimal;

import com.mg.merp.reference.model.OrgUnit;

/**
 * �������� ������� � ���������� ������� �� ������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StockSituationValues.java,v 1.1 2007/04/05 12:25:41 poroxnenko Exp $ 
 */
public interface StockSituationValues {

	/**
	 * 
	 * @return �����, ��� �������� ���������� ����������
	 */
	OrgUnit getWarehouse();
	
	/**
	 * 
	 * @return ����������� ���������� � ������ ��
	 */
	BigDecimal getLocated1();
	
	/**
	 * 
	 * @return ����������� ���������� �� ������ ��
	 */
	BigDecimal getLocated2();
	
	/**
	 * 
	 * @return ����������� ������ � ������ ��
	 */
	BigDecimal getPlanningReceipt1();
	
	/**
	 * 
	 * @return ����������� ������ �� ������ ��
	 */
	BigDecimal getPlanningReceipt2();
	
	/**
	 * 
	 * @return ����������� ������ � ������ ��
	 */
	BigDecimal getPlanningIssue1();
	
	/**
	 * 
	 * @return ����������� ������ �� ������ ��
	 */
	BigDecimal getPlanningIssue2();
	
	/**
	 * 
	 * @return ����������������� ���������� � ������ ��
	 */
	BigDecimal getReserved1();
	
	/**
	 * 
	 * @return ����������������� ���������� �� ������ ��
	 */
	BigDecimal getReserved2();
	
	/**
	 * 
	 * @return ��������� ���������� � ������ ��
	 */
	BigDecimal getAvailable1();
	
	/**
	 * 
	 * @return ��������� ���������� �� ������ ��
	 */
	BigDecimal getAvailable2();
}
