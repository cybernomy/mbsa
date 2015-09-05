/*
 * OrderItemKindParam.java
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
 * Модель бизнес-компонента "Параметр вида пункта приказа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderItemKindParam.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderItemKindParam extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.humanresources.model.OrderItemKind ItemKind;
	private java.lang.String Code;
	private java.lang.String Name;
	private java.lang.Integer Priority;
	private java.lang.Short ParamType;
	private java.lang.String SysCode;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<OrderModelItemParam> OrderModelItemParams;
	private java.util.Set<OrderItemParam> OrderItemParams;
	


	// Constructors

	/** default constructor */
	public OrderItemKindParam() {
	}

	/** constructor with id */
	public OrderItemKindParam(java.lang.Integer Id) {
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
	public com.mg.merp.humanresources.model.OrderItemKind getItemKind() {
		return this.ItemKind;
	}

	/**
	 * 
	 * @param HrOrderItemKind
	 */
	public void setItemKind(com.mg.merp.humanresources.model.OrderItemKind HrOrderItemKind) {
		this.ItemKind = HrOrderItemKind;
	}
	
	/**
	 * 
	 * @return
	 */
	public java.lang.String getCode() {
		return this.Code;
	}

	/**
	 * 
	 * @param Code
	 */
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
	public java.lang.Short getParamType() {
		return this.ParamType;
	}
		
	/**
	 * 
	 * @param ParamType
	 */
	public void setParamType(java.lang.Short ParamType) {
		this.ParamType = ParamType;
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

	/**
	 * @return the orderItemParams
	 */
	public java.util.Set<OrderItemParam> getOrderItemParams() {
		return this.OrderItemParams;
	}

	/**
	 * @param orderItemParams the orderItemParams to set
	 */
	public void setOrderItemParams(java.util.Set<OrderItemParam> orderItemParams) {
		this.OrderItemParams = orderItemParams;
	}

}