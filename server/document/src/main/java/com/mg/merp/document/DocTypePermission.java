/*
 * DocTypePermission.java
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

/**
 * ������ ������ "����� �� ���� ����������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocTypePermission.java,v 1.1 2007/11/20 14:46:25 sharapov Exp $
 */
public class DocTypePermission {
	
	private Integer roleId;
	private String roleName;
	private Boolean permission;
	private Integer permissionId;
	
	
	/**
	 * ������� ������ ������ "����� �� ���� ����������"
	 * @param roleId - ������������ ������ �������������
	 * @param roleName - ������������ ������ �������������
	 * @param permission - ������� ������� �����
	 * @param permissionId - ������������� ����� ������� ��� ���� ���������
	 */
	public DocTypePermission(Integer roleId, String roleName, Boolean permission, Integer permissionId) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.permission = permission;
		this.permissionId = permissionId;
	}
	
	
	/**
	 * �������� ������������� ����� ������� ��� ���� ���������
	 * @return the permissionId - ������������� ����� ������� ��� ���� ���������
	 */
	public Integer getPermissionId() {
		return permissionId;
	}
	
	/**
	 * ���������� ������������� ����� ������� ��� ���� ���������
	 * @param permissionId - ������������� ����� ������� ��� ���� ���������
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * �������� ������������ ������ �������������
	 * @return ������������ ������ �������������
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * �������� ������������ ������ �������������
	 * @return ������������ ������ �������������
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * ���������� ������� ������� �����
	 * @param permission - <code>true<code> ���������� �����; <code>false</code> �������� �����; 
	 */
	public void setPermission(Boolean permission) {
		this.permission = permission;
	}
	
	/**
	 * �������� ������� ������� �����
	 * @return <code>true<code> ���� ����� �����������; <code>false</code> ���� ����� ��������; <code>null<code> ���� ����� �� �����������
	 */
	public Boolean getPermission() {
		return permission;
	}

}
