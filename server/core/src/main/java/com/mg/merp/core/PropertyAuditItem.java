/**
 * PropertyAuditItem.java
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
 * Настройка аудита атрибута сущности
 * 
 * @author Oleg V. Safonov
 * @version $Id: PropertyAuditItem.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public class PropertyAuditItem {
	private String i18nName;
	private String propertyName;
	private Boolean audit;

	public PropertyAuditItem(String i18nName, String propertyName, Boolean audit) {
		super();
		this.i18nName = i18nName;
		this.propertyName = propertyName;
		this.audit = audit;
	}
	/**
	 * @return the i18nName
	 */
	public String getI18nName() {
		return i18nName;
	}
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * @return the audit
	 */
	public Boolean getAudit() {
		return audit;
	}
	/**
	 * @param audit the audit to set
	 */
	public void setAudit(Boolean audit) {
		this.audit = audit;
	}

}
