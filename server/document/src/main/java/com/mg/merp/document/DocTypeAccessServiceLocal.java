/*
 * DocTypeAccessServiceLocal.java
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
package com.mg.merp.document;

import java.util.List;

import com.mg.merp.document.model.DocTypeRights;

/**
 * ������-��������� "����� �� ���� ����������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocTypeAccessServiceLocal.java,v 1.2 2007/11/20 14:48:21 sharapov Exp $
 */
public interface DocTypeAccessServiceLocal extends com.mg.framework.api.DataBusinessObjectService<DocTypeRights, Integer> {
	
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/document/DocTypeAccess"; //$NON-NLS-1$

	/**
	 * ��������� ������ ���� ������� ��� ���� ���������
	 * @param docTypeId - ������������� ���� ���������
	 * @return ������ ���� ������� ��� ���� ���������
	 */
	List<DocTypePermission> loadDocTypePermissions(Integer docTypeId);
	
	/**
	 * ���������� ����� ������� ��� ���� ���������
	 * @param permission - ����� �������
	 * @param docTypeId - ������������� ���� ���������
	 */
	void grantPermission(DocTypePermission permission, Integer docTypeId);
	
	/**
	 * �������� ����� ������� ��� ���� ���������
	 * @param permission - ����� �������
	 */
	void revokePermission(DocTypePermission permission);

}
