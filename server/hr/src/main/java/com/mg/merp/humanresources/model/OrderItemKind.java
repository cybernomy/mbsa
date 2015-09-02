/*
 * OrderItemKind.java
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
 * Модель бизнес-компонента "Виды пунктов приказов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderItemKind.java,v 1.2 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderItemKind extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private java.lang.String Code;
	private java.lang.String Name;
	private java.lang.String SysCode;
	private java.lang.Integer Priority;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<OrderItemKindParam> OrderItemKindParams;
	private java.util.Set<OrderModelItem> OrderModelItems;
	private java.util.Set<OrderItem> OrderItems;


	// Constructors

	/** default constructor */
	public OrderItemKind() {
	}

	/** constructor with id */
	public OrderItemKind(java.lang.Integer Id) {
		this.Id = Id;
	}


	// Property accessors

	/**
	 * 
	 * @return
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId () {
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

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 * @return
	 */
	public java.lang.String getCode () {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 * @return
	 */
	public java.lang.String getName() {
		return this.Name;
	}

	/**
	 * 
	 * @param Name
	 */
	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 * @return
	 */
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	/**
	 * 
	 * @param Priority
	 */
	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 * @return
	 */
	public java.lang.String getSysCode() {
		return this.SysCode;
	}

	/**
	 * 
	 * @param SysCode
	 */
	public void setSysCode(java.lang.String SysCode) {
		this.SysCode = SysCode;
	}

	/**
	 * @return the orderItemKindParams
	 */
	public java.util.Set<OrderItemKindParam> getOrderItemKindParams() {
		return this.OrderItemKindParams;
	}

	/**
	 * @param orderItemKindParams the orderItemKindParams to set
	 */
	public void setOrderItemKindParams(java.util.Set<OrderItemKindParam> orderItemKindParams) {
		this.OrderItemKindParams = orderItemKindParams;
	}

	/**
	 * @return the orderItems
	 */
	public java.util.Set<OrderItem> getOrderItems() {
		return this.OrderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(java.util.Set<OrderItem> orderItems) {
		this.OrderItems = orderItems;
	}

	/**
	 * @return the orderModelItems
	 */
	public java.util.Set<OrderModelItem> getOrderModelItems() {
		return this.OrderModelItems;
	}

	/**
	 * @param orderModelItems the orderModelItems to set
	 */
	public void setOrderModelItems(java.util.Set<OrderModelItem> orderModelItems) {
		this.OrderModelItems = orderModelItems;
	}

}