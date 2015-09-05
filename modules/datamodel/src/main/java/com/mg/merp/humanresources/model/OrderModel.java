/*
 * OrderModel.java
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
 * Модель бизнес-компонента "Образцы приказов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderModel.java,v 1.3 2007/08/27 12:05:53 sharapov Exp $
 */
public class OrderModel extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.core.model.Folder Folder;
	private java.lang.String Code;
	private java.lang.String Name;
	private java.lang.String Header;
	private java.lang.String Footer;
	private com.mg.merp.core.model.SysClient SysClient;
	private java.util.Set<OrderModelItem> OrderModelItems;


	// Constructors

	/** default constructor */
	public OrderModel() {
	}

	/** constructor with id */
	public OrderModel(java.lang.Integer Id) {
		this.Id = Id;
	}

	
	// Property accessors
	
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
	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	/**
	 * 
	 * @param Folder
	 */
	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	public java.lang.String getHeader() {
		return this.Header;
	}

	/**
	 * 
	 * @param Header
	 */
	public void setHeader(java.lang.String Header) {
		this.Header = Header;
	}
	
	/**
	 * 
	 * @return
	 */
	public java.lang.String getFooter() {
		return this.Footer;
	}

	/**
	 * 
	 * @param Footer
	 */
	public void setFooter(java.lang.String Footer) {
		this.Footer = Footer;
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