/*
 * OrderModelItem.java
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
 * Модель бизнес-компонента "Образцы пунктов приказа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderModelItem.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderModelItem extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.humanresources.model.OrderModel OrderModel;
	private com.mg.merp.humanresources.model.OrderItemKind ItemKind;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<OrderModelItemParam> OrderModelItemParams;


	// Constructors

	/** default constructor */
	public OrderModelItem() {
	}

	/** constructor with id */
	public OrderModelItem(java.lang.Integer Id) {
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
	public com.mg.merp.humanresources.model.OrderModel getOrderModel() {
		return this.OrderModel;
	}

	/**
	 * 
	 * @param HrOrderModel
	 */
	public void setOrderModel(com.mg.merp.humanresources.model.OrderModel HrOrderModel) {
		this.OrderModel = HrOrderModel;
	}

	/**
	 * 
	 * @return
	 */
	public com.mg.merp.humanresources.model.OrderItemKind getItemKind() {
		return this.ItemKind;
	}

	/**
	 * 
	 * @param HrOrderItemKind
	 */
	public void setItemKind (com.mg.merp.humanresources.model.OrderItemKind HrOrderItemKind) {
		this.ItemKind = HrOrderItemKind;
	}

	/**
	 * @return the orderModelItemParams
	 */
	public java.util.Set<OrderModelItemParam> getOrderModelItemParams() {
		return this.OrderModelItemParams;
	}

	/**
	 * @param orderModelItemParams the orderModelItemParams to set
	 */
	public void setOrderModelItemParams(java.util.Set<OrderModelItemParam> orderModelItemParams) {
		this.OrderModelItemParams = orderModelItemParams;
	}

}