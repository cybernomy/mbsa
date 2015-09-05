/*
 * OrderModelItemParam.java
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
package com.mg.merp.humanresources.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Параметры образца пункта приказа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderModelItemParam.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderModelItemParam extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.humanresources.model.OrderItemKindParam ItemKindParam;
	private com.mg.merp.humanresources.model.OrderModelItem ModelItem;
	private java.lang.String ParamValue;
	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public OrderModelItemParam() {
	}

	/** constructor with id */
	public OrderModelItemParam(java.lang.Integer Id) {
		this.Id = Id;
	}


	// Property accessors

	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	/**
	 * 
	 * @param Id
	 */
	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 * @return
	 */
	public com.mg.merp.humanresources.model.OrderItemKindParam getItemKindParam() {
		return this.ItemKindParam;
	}

	public void setItemKindParam(com.mg.merp.humanresources.model.OrderItemKindParam HrOrderItemKindParam) {
		this.ItemKindParam = HrOrderItemKindParam;
	}

	/**
	 * 
	 * @return
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	/**
	 * 
	 * @param SysClient
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 * @return
	 */
	public com.mg.merp.humanresources.model.OrderModelItem getModelItem() {
		return this.ModelItem;
	}

	/**
	 * 
	 * @param HrOrderModelItem
	 */
	public void setModelItem(com.mg.merp.humanresources.model.OrderModelItem HrOrderModelItem) {
		this.ModelItem = HrOrderModelItem;
	}

	/**
	 * 
	 * @return
	 */
	public java.lang.String getParamValue() {
		return this.ParamValue;
	}

	/**
	 * 
	 * @param ParamValue
	 */
	public void setParamValue(java.lang.String ParamValue) {
		this.ParamValue = ParamValue;
	}

}