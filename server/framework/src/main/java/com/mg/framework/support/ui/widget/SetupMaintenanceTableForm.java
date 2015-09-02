/**
 * SetupMaintenanceTableForm.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.ui.Form;

/**
 * ����� ��������� �������� ���� ������� ��������� ������-�����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SetupMaintenanceTableForm.java,v 1.1 2008/12/23 09:19:54 safonov Exp $
 */
public interface SetupMaintenanceTableForm extends Form {

	/**
	 * ��� �����
	 */
	final static String FORM_NAME = "com.mg.merp.core.SetupMaintenanceTableForm";

	/**
	 * ������ ����� ���������
	 * 
	 * @param tableModel	������ �������
	 */
	void execute(MaintenanceTableModel tableModel);

}
