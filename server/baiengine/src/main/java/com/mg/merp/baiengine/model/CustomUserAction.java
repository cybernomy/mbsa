/*
 * CustomUserAction.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CustomUserAction.java,v 1.3 2007/11/15 13:13:51 safonov Exp $
 */
@DataItemName("BAi.CustomUserAction")
public class CustomUserAction extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer id;

	private com.mg.merp.baiengine.model.Repository bai;

	private com.mg.merp.core.model.SysClass sysClass;

	private com.mg.merp.core.model.SysClient sysClient;

	private java.lang.String code;

	private java.lang.String description;

	private java.lang.String caption;

	private java.lang.String hint;

	private java.lang.String keyStroke;

	private boolean fromMenu;

	private boolean fromToolbar;

	private boolean forceRefresh;

	private boolean separatorBefore;

	private boolean separatorAfter;

	private java.lang.String icon;

	private java.lang.Short priority;

	private boolean isActive;
	
	private java.util.Set<CustomUserActionPermiss> permissions;

	// Constructors

	/** default constructor */
	public CustomUserAction() {
	}

	/** constructor with id */
	public CustomUserAction(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * 
	 */	
	public com.mg.merp.baiengine.model.Repository getBAi() {
		return this.bai;
	}

	public void setBAi(
			com.mg.merp.baiengine.model.Repository bai) {
		this.bai = bai;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClass getSysClass() {
		return this.sysClass;
	}

	public void setSysClass(com.mg.merp.core.model.SysClass SysClass) {
		this.sysClass = SysClass;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.sysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Code")
	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String Code) {
		this.code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Description")
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String Description) {
		this.description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Caption")
	public java.lang.String getCaption() {
		return this.caption;
	}

	public void setCaption(java.lang.String Caption) {
		this.caption = Caption;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Hint")
	public java.lang.String getHint() {
		return this.hint;
	}

	public void setHint(java.lang.String Hint) {
		this.hint = Hint;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.KeyStroke")
	public java.lang.String getKeyStroke() {
		return this.keyStroke;
	}

	public void setKeyStroke(java.lang.String keyStroke) {
		this.keyStroke = keyStroke;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.FromMenu")
	public boolean isFromMenu() {
		return this.fromMenu;
	}

	public void setFromMenu(boolean FromMenu) {
		this.fromMenu = FromMenu;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.FromToolbar")
	public boolean isFromToolbar() {
		return this.fromToolbar;
	}

	public void setFromToolbar(boolean FromToolbar) {
		this.fromToolbar = FromToolbar;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.ForceRefresh")
	public boolean isForceRefresh() {
		return this.forceRefresh;
	}

	public void setForceRefresh(boolean ForceRefresh) {
		this.forceRefresh = ForceRefresh;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.SeparatorBefore")
	public boolean isSeparatorBefore() {
		return this.separatorBefore;
	}

	public void setSeparatorBefore(boolean SeparatorBefore) {
		this.separatorBefore = SeparatorBefore;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.SeparatorAfter")
	public boolean isSeparatorAfter() {
		return this.separatorAfter;
	}

	public void setSeparatorAfter(boolean SeparatorAfter) {
		this.separatorAfter = SeparatorAfter;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Icon")
	public java.lang.String getIcon() {
		return this.icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	/**
	 * 
	 */
	@DataItemName("BAi.CustomUserAction.Priority")
	public java.lang.Short getPriority() {
		return this.priority;
	}

	public void setPriority(java.lang.Short Priority) {
		this.priority = Priority;
	}

	/**
	 * @return the isActive
	 */
	@DataItemName("BAi.CustomUserAction.Active")
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * 
	 */

	public java.util.Set<CustomUserActionPermiss> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(
			java.util.Set<CustomUserActionPermiss> permissions) {
		this.permissions = permissions;
	}

}