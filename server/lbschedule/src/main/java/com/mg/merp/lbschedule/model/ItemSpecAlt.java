/*
 * ItemSpecAlt.java
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Возможные замены позиции спецификации пункта графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecAlt.java,v 1.3 2007/04/17 12:49:41 sharapov Exp $
 */
public class ItemSpecAlt extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.reference.model.Catalog Catalog;
	private com.mg.merp.lbschedule.model.ItemSpec ItemSpec;
	private com.mg.merp.core.model.SysClient SysClient;

	// Constructors

	/** default constructor */
	public ItemSpecAlt() {
	}

	/** constructor with id */
	public ItemSpecAlt(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}

	public com.mg.merp.reference.model.Catalog getCatalog () {
		return this.Catalog;
	}

	public void setCatalog (com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * @return the itemSpec
	 */
	public com.mg.merp.lbschedule.model.ItemSpec getItemSpec() {
		return ItemSpec;
	}

	/**
	 * @param itemSpec the itemSpec to set
	 */
	public void setItemSpec(com.mg.merp.lbschedule.model.ItemSpec itemSpec) {
		ItemSpec = itemSpec;
	}

}