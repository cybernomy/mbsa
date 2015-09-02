/*
 * OrderItemRollback.java
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
 * ������ ������-���������� ""
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderItemRollback.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderItemRollback extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.humanresources.model.OrderItem OrderItem;
	private java.lang.String SysCode;
	private java.lang.String ParamValue;
	private com.mg.merp.core.model.SysClient SysClient;


	// Constructors

	/** default constructor */
	public OrderItemRollback() {
	}

	/** constructor with id */
	public OrderItemRollback(java.lang.Integer Id) {
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
	public com.mg.merp.humanresources.model.OrderItem getOrderItem() {
		return this.OrderItem;
	}
	
	/**
	 * 
	 * @param HrOrderItem
	 */
	public void setOrderItem(com.mg.merp.humanresources.model.OrderItem HrOrderItem) {
		this.OrderItem = HrOrderItem;
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