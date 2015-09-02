/*
 * MethodAccessServiceLocal.java
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
package com.mg.merp.security;

import java.util.List;

import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.MethodAccess;

/**
 * ������-��������� "����� �� ������ ������-�����������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MethodAccessServiceLocal.java,v 1.2 2008/06/10 06:17:16 sharapov Exp $
 */
public interface MethodAccessServiceLocal extends com.mg.framework.api.DataBusinessObjectService<MethodAccess, Integer> {

	/**
	 * ��� �������
	 */
	static String SERVICE_NAME = "merp/security/MethodAccess"; //$NON-NLS-1$
		
	/**
	 * ���������� ����� ��� ���� ������-����������� ������
	 * @param subSystemId - ������������� ������(����������)
	 * @param group - ������ �������������
	 * @param isPermit - <code>true</code> - ���� �����; <code>false</code> - �������� �����
	 * @param methodNames - ������ ��� ������� 
	 */
	void setPermissionForSubsystemBusinessObjects(Integer subSystemId, Groups group, boolean isPermit, List<String> methodNames);
	
}
