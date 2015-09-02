/*
 * ElectronicAdressItem.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.crm.support.ui;


/**
 * Информациия об электронном адресе
 * 
 * @author Artem V. Sharapov
 * @version $Id: ElectronicAdressItem.java,v 1.1 2007/02/07 07:00:13 sharapov Exp $
 */
public class ElectronicAdressItem {

	// Fields    

	private java.lang.Integer id;
	private java.lang.String addressKind;
	private java.lang.String protocol;
	private java.lang.String address;
	private boolean isActive;
	
	// Constructors
	
	public ElectronicAdressItem() {
	}
	
	public ElectronicAdressItem(java.lang.Integer id) {
		this.id = id;
	}
	
	public ElectronicAdressItem(java.lang.Integer id, java.lang.String addressKind, java.lang.String protocol, java.lang.String address, boolean isActive) {
		this.id = id;
		this.addressKind = addressKind;
		this.protocol = protocol;
		this.address = address;
		this.isActive = isActive;
	}
	
	// Property accessors
	
	/**
	 * @return the address
	 */
	public java.lang.String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	/**
	 * @return the addressKind
	 */
	public java.lang.String getAddressKind() {
		return addressKind;
	}
	
	/**
	 * @param addressKind the addressKind to set
	 */
	public void setAddressKind(java.lang.String addressKind) {
		this.addressKind = addressKind;
	}
	
	/**
	 * @return the id
	 */
	public java.lang.Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the isActive
	 */
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
	 * @return the protocol
	 */
	public java.lang.String getProtocol() {
		return protocol;
	}
	
	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(java.lang.String protocol) {
		this.protocol = protocol;
	}
	
}
