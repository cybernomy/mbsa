/*
 * FolderAccessServiceLocal.java
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
package com.mg.merp.security;

import java.util.List;

import com.mg.merp.core.model.FolderRights;

/**
 * ������-��������� "����� �� �����"
 * 
 * @author leonova
 * @version $Id: FolderAccessServiceLocal.java,v 1.3 2007/02/24 14:16:36 safonov Exp $
 */
public interface FolderAccessServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<FolderRights, Integer>
{

	/**
	 * �������� ���� �� �����
	 * 
	 * @param folderPart	��� �����
	 * @param folderId		������������� �����
	 * @return	������ ����
	 */
	List<FolderPermission> loadFolderPermission(int folderPart, int folderId);
	
	/**
	 * ������������ ����� �� �����
	 * 
	 * @param perm			�����
	 * @param folderPart	��� �����
	 * @param folderId		������������� �����
	 */
	void grantPermission(FolderPermission perm, int folderPart, int folderId);
	
	/**
	 * �������� ����� �� �����
	 * 
	 * @param perm	�����
	 */
	void revokePermission(FolderPermission perm);
	
}
