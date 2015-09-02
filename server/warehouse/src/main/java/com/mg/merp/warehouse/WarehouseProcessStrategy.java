/* WarehouseProcessStrategy.java
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
package com.mg.merp.warehouse;


/**
 * ��������� ��������� ��������� ��������� ������ ��
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessStrategy.java,v 1.3 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseProcessStrategy {

	/**
	 * ��������� ��������� ��������
	 * 
	 * @param docLineData	���������� � ������������
	 */
	void process(WarehouseProcessDocumentLineData docLineData);
	
}
