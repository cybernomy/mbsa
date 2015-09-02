/*
 * PersonAddress.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PersonAddress.java,v 1.6 2007/09/05 10:46:24 alikaev Exp $
 */
@DataItemName("Reference.PersonAddress")
public class PersonAddress extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {
	// Fields

	private int Id;

	private com.mg.merp.reference.model.District District;

	private com.mg.merp.reference.model.Country Country;

	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Place Place;

	private com.mg.merp.reference.model.ZipCode ZipCode;

	private com.mg.merp.reference.model.Region Region;

	private PersonAddressKind AddressKind;

	private java.util.Date BeginDate;

	private java.lang.String PostIndex;

	private java.lang.String RegionName;

	private java.lang.String City;

	private java.lang.String Street;

	private java.lang.String House;

	private java.lang.String FullAddress;

	private java.lang.String Building;

	private java.lang.String Room;

	// Constructors

	/** default constructor */
	public PersonAddress() {
	}

	/** constructor with id */
	public PersonAddress(int Id) {
		this.Id = Id;
	}

	// Property accessors

	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.District")
	public com.mg.merp.reference.model.District getDistrict() {
		return this.District;
	}

	public void setDistrict(com.mg.merp.reference.model.District District) {
		this.District = District;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country")
	public com.mg.merp.reference.model.Country getCountry() {
		return this.Country;
	}

	public void setCountry(com.mg.merp.reference.model.Country Country) {
		this.Country = Country;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
		return this.NaturalPerson;
	}

	public void setNaturalPerson(
			com.mg.merp.reference.model.NaturalPerson NaturalPerson) {
		this.NaturalPerson = NaturalPerson;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Place")
	public com.mg.merp.reference.model.Place getPlace() {
		return this.Place;
	}

	public void setPlace(com.mg.merp.reference.model.Place Place) {
		this.Place = Place;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.ZipCode getZipCode() {
		return this.ZipCode;
	}

	public void setZipCode(com.mg.merp.reference.model.ZipCode ZipCode) {
		this.ZipCode = ZipCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Region")
	public com.mg.merp.reference.model.Region getRegion() {
		return this.Region;
	}

	public void setRegion(com.mg.merp.reference.model.Region Region) {
		this.Region = Region;
	}

	/**
	 * 
	 */
	public PersonAddressKind getAddressKind() {
		return this.AddressKind;
	}

	public void setAddressKind(PersonAddressKind AddressKind) {
		this.AddressKind = AddressKind;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NPerson.Address.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date BeginDate) {
		this.BeginDate = BeginDate;
	}

	/**
	 * 
	 */
	public java.lang.String getPostIndex() {
		return this.PostIndex;
	}

	public void setPostIndex(java.lang.String PostIndex) {
		this.PostIndex = PostIndex;
	}

	/**
	 * 
	 */
	public java.lang.String getRegionName() {
		return this.RegionName;
	}

	public void setRegionName(java.lang.String RegionName) {
		this.RegionName = RegionName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.City")
	public java.lang.String getCity() {
		return this.City;
	}

	public void setCity(java.lang.String City) {
		this.City = City;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Street")
	public java.lang.String getStreet() {
		return this.Street;
	}

	public void setStreet(java.lang.String Street) {
		this.Street = Street;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.House")
	public java.lang.String getHouse() {
		return this.House;
	}

	public void setHouse(java.lang.String House) {
		this.House = House;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.PersonAddress.FullAddress")
	public java.lang.String getFullAddress() {
		return this.FullAddress;
	}

	public void setFullAddress(java.lang.String FullAddress) {
		this.FullAddress = FullAddress;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Block")
	public java.lang.String getBuilding() {
		return this.Building;
	}

	public void setBuilding(java.lang.String Building) {
		this.Building = Building;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Flat")
	public java.lang.String getRoom() {
		return this.Room;
	}

	public void setRoom(java.lang.String Room) {
		this.Room = Room;
	}

}