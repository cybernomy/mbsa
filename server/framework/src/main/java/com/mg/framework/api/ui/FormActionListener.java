/*
 * FormActionListener.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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


/**
 * ��������� ������� ����� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FormActionListener.java,v 1.2 2006/08/31 09:01:06 safonov Exp $
 */
public interface FormActionListener extends Serializable {
	
	/**
	 * ���������� � �������� ������������� ������� �� �����
	 * 
	 * @param event	������� �����
	 */
	void actionPerformed(FormEvent event);
}
