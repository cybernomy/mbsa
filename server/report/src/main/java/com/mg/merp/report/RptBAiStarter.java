/* RptBAiStarter.java
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
package com.mg.merp.report;

import java.util.Map;

import com.mg.framework.api.dataset.DataSet;

/**
 * ��������� ������� BAi ���������� �������
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptBAiStarter.java,v 1.2 2007/08/30 14:20:50 safonov Exp $
 */
public interface RptBAiStarter {

	/**
	 * ��������� BAi
	 * 
	 * @param code		��� BAi
	 * @param params	���������
	 */
	void perform(String code, Map<String, Object> params);

	/**
	 * �������� �������������� ����� ������
	 * 
	 * @return	����� ������
	 */
	DataSet getDataSet();

}
