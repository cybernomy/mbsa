/*
 * ItemSpecTax.java
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
 * Модель бизнес-компонента "Налоги позиции спецификации пункта графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecTax.java,v 1.3 2007/04/17 12:49:25 sharapov Exp $
 */
public class ItemSpecTax extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.reference.model.Tax Tax;
	private com.mg.merp.lbschedule.model.ItemSpec ItemSpec;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.math.BigDecimal Summa;
	private java.math.BigDecimal Price;


	// Constructors

	/** default constructor */
	public ItemSpecTax() {
	}

	/** constructor with id */
	public ItemSpecTax(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors

	/**
	 * @return the id
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.Integer id) {
		Id = id;
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

	/**
	 * @return the price
	 */
	@DataItemName("LbSchedule.ItemSpecTax.Price") //$NON-NLS-1$
	public java.math.BigDecimal getPrice() {
		return Price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(java.math.BigDecimal price) {
		Price = price;
	}

	/**
	 * @return the summa
	 */
	@DataItemName("LbSchedule.ItemSpecTax.Summa") //$NON-NLS-1$
	public java.math.BigDecimal getSumma() {
		return Summa;
	}

	/**
	 * @param summa the summa to set
	 */
	public void setSumma(java.math.BigDecimal summa) {
		Summa = summa;
	}

	/**
	 * @return the sysClient
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return SysClient;
	}

	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		SysClient = sysClient;
	}

	/**
	 * @return the tax
	 */
	public com.mg.merp.reference.model.Tax getTax() {
		return Tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(com.mg.merp.reference.model.Tax tax) {
		Tax = tax;
	}

}