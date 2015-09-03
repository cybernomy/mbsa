/**
 * EntityAuditItem.java
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
package com.mg.merp.core;

/**
 * Настройка аудита сущности
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityAuditItem.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public class EntityAuditItem {
	private String i18nName;
	private String entityName;
	private Boolean auditCreate;
	private Boolean auditModify;
	private Boolean auditRemove;
	
	public EntityAuditItem(String i18nName, String entityName, Boolean auditCreate,
			Boolean auditModify, Boolean auditRemove) {
		super();
		this.i18nName = i18nName;
		this.entityName = entityName;
		this.auditCreate = auditCreate;
		this.auditModify = auditModify;
		this.auditRemove = auditRemove;
	}
	/**
	 * @return the i18nName
	 */
	public String getI18nName() {
		return i18nName;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @return the auditCreate
	 */
	public Boolean getAuditCreate() {
		return auditCreate;
	}
	/**
	 * @return the auditModify
	 */
	public Boolean getAuditModify() {
		return auditModify;
	}
	/**
	 * @return the auditRemove
	 */
	public Boolean getAuditRemove() {
		return auditRemove;
	}
	/**
	 * @param auditCreate the auditCreate to set
	 */
	public void setAuditCreate(Boolean auditCreate) {
		this.auditCreate = auditCreate;
	}
	/**
	 * @param auditModify the auditModify to set
	 */
	public void setAuditModify(Boolean auditModify) {
		this.auditModify = auditModify;
	}
	/**
	 * @param auditRemove the auditRemove to set
	 */
	public void setAuditRemove(Boolean auditRemove) {
		this.auditRemove = auditRemove;
	}

}
