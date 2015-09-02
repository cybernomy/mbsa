/*
 * MenuCommand.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.Map;

/**
 * ������� ����, ������������ ��� �������� ������� ����������� �� ���� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MenuCommand.java,v 1.3 2008/05/14 09:36:24 safonov Exp $
 */
public interface MenuCommand extends Serializable {

	/**
	 * ������������� �������
	 * 
	 * @param params	����������� ������ ����������, ������ ���������� �������������� ������ ������
	 */
	void init(Map<String, String> params);
	
	/**
	 * ��������� ��������
	 * 
	 * @throws Exception	� ������ ����� ��
	 */
	void execute() throws Exception;
	
	/**
	 * �������� ����������� ������� �������� ����������
	 * 
	 * @return	<code>true</code> ���� �� ���������� ������� � �������� ���������� ������� �����
	 */
	boolean isPermitted();
}
