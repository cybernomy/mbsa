/*
 * MaintenanceFormActionListener.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.EventListener;

/**
 * ��������� ����� ��������� ������-����������
 * 
 * @see MaintenanceHelper
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceFormActionListener.java,v 1.2 2006/08/31 09:01:06 safonov Exp $
 */
public interface MaintenanceFormActionListener extends EventListener, Serializable {
	
	/**
	 * ��������� �������� �� ��������� ��������
	 * 
	 * @param event	�������
	 */
	void performed(MaintenanceFormEvent event);
	
	/**
	 * �������� �� ��������� ��������
	 * 
	 * @param event	�������
	 */
	void canceled(MaintenanceFormEvent event);
}
