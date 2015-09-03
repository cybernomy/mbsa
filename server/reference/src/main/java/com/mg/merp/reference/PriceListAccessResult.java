/*
 * PriceListAccessResult.java
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
package com.mg.merp.reference;

import java.io.Serializable;


/**
 * Модель данных "Права на прайс-листы"
 * 
 * @author pashistova
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListAccessResult.java,v 1.2 2008/05/13 06:57:27 alikaev Exp $
 */
public class PriceListAccessResult implements Serializable {
	
	private Integer roleId;
	
	private String roleName;
	
	private Boolean permission;
	
	private Integer permissionId;
	
	/**
	 * Создать модель данных "Права на прайс-листы"
	 * 
	 * @param roleId 
	 * 				- иденификатор группы пользователей
	 * @param roleName 
	 * 				- наименование группы пользователей
	 * @param permission 
	 * 				- признак наличия права
	 * @param permissionId 
	 * 				- идентификатор права доступа для прайс-листа
	 */
	public PriceListAccessResult(Integer roleId, String roleName, Boolean permission, Integer permissionId) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.permission = permission;
		this.permissionId = permissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getPermission() {
		return permission;
	}

	public void setPermission(Boolean permission) {
		this.permission = permission;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
}
