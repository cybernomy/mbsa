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
 * Модель данных "Права на типы документов"
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
	 * Создать модель данных "Права на типы документов"
	 * @param roleId - иденификатор группы пользователей
	 * @param roleName - наименование группы пользователей
	 * @param permission - признак наличия права
	 * @param permissionId - идентификатор права доступа для типа документа
	 */
	public DocTypePermission(Integer roleId, String roleName, Boolean permission, Integer permissionId) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.permission = permission;
		this.permissionId = permissionId;
	}
	
	
	/**
	 * Получить идентификатор права доступа для типа документа
	 * @return the permissionId - идентификатор права доступа для типа документа
	 */
	public Integer getPermissionId() {
		return permissionId;
	}
	
	/**
	 * Установить идентификатор права доступа для типа документа
	 * @param permissionId - идентификатор права доступа для типа документа
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * Получить иденификатор группы пользователей
	 * @return иденификатор группы пользователей
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * Получить наименование группы пользователей
	 * @return наименование группы пользователей
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Установить признак наличия права
	 * @param permission - <code>true<code> установить право; <code>false</code> отменить право; 
	 */
	public void setPermission(Boolean permission) {
		this.permission = permission;
	}
	
	/**
	 * Получить признак наличия права
	 * @return <code>true<code> если право установлено; <code>false</code> если право отменено; <code>null<code> если право не установлено
	 */
	public Boolean getPermission() {
		return permission;
	}

}
