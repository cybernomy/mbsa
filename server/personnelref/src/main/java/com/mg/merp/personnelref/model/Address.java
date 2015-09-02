/*
 * Address.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Address.java,v 1.5 2006/06/19 10:00:22 leonova Exp $
 */
public class Address extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.PersonAddressKind AddressKind;

	private java.util.Date BeginDate;

	private java.lang.String FullAddress;

	private java.lang.String PostIndex;

	private java.lang.String Country;

	private java.lang.String Region;

	private java.lang.String District;

	private java.lang.String DistrictType;

	private java.lang.String City;

	private java.lang.String CityType;

	private java.lang.String Area;

	private java.lang.String AreaType;

	private java.lang.String Street;

	private java.lang.String StreetType;

	private java.lang.String House;

	private java.lang.String Block;

	private java.lang.String Flat;

	private java.lang.String RegionType;

	private java.lang.String KladrCode;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** constructor with id */
	public Address(int Id) {
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

	public com.mg.merp.personnelref.model.Personnel getPersonnel() {
		return this.Personnel;
	}

	public void setPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.Personnel = PrefPersonnel;
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

	public com.mg.merp.reference.model.PersonAddressKind getAddressKind() {
		return this.AddressKind;
	}

	public void setAddressKind(
			com.mg.merp.reference.model.PersonAddressKind AddressKind) {
		this.AddressKind = AddressKind;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.FullAddress")
	public java.lang.String getFullAddress() {
		return this.FullAddress;
	}

	public void setFullAddress(java.lang.String FullAddress) {
		this.FullAddress = FullAddress;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Index")
	public java.lang.String getPostIndex() {
		return this.PostIndex;
	}

	public void setPostIndex(java.lang.String PostIndex) {
		this.PostIndex = PostIndex;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Country")
	public java.lang.String getCountry() {
		return this.Country;
	}

	public void setCountry(java.lang.String Country) {
		this.Country = Country;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Region")
	public java.lang.String getRegion() {
		return this.Region;
	}

	public void setRegion(java.lang.String Region) {
		this.Region = Region;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.District")
	public java.lang.String getDistrict() {
		return this.District;
	}

	public void setDistrict(java.lang.String District) {
		this.District = District;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Type")
	public java.lang.String getDistrictType() {
		return this.DistrictType;
	}

	public void setDistrictType(java.lang.String DistrictType) {
		this.DistrictType = DistrictType;
	}

	/**
	 * 
	 */

	public java.lang.String getCity() {
		return this.City;
	}

	public void setCity(java.lang.String City) {
		this.City = City;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Type")
	public java.lang.String getCityType() {
		return this.CityType;
	}

	public void setCityType(java.lang.String CityType) {
		this.CityType = CityType;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Area")
	public java.lang.String getArea() {
		return this.Area;
	}

	public void setArea(java.lang.String Area) {
		this.Area = Area;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Type")
	public java.lang.String getAreaType() {
		return this.AreaType;
	}

	public void setAreaType(java.lang.String AreaType) {
		this.AreaType = AreaType;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Street")
	public java.lang.String getStreet() {
		return this.Street;
	}

	public void setStreet(java.lang.String Street) {
		this.Street = Street;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Type")
	public java.lang.String getStreetType() {
		return this.StreetType;
	}

	public void setStreetType(java.lang.String StreetType) {
		this.StreetType = StreetType;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.House")
	public java.lang.String getHouse() {
		return this.House;
	}

	public void setHouse(java.lang.String House) {
		this.House = House;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Block")
	public java.lang.String getBlock() {
		return this.Block;
	}

	public void setBlock(java.lang.String Block) {
		this.Block = Block;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Flat")
	public java.lang.String getFlat() {
		return this.Flat;
	}

	public void setFlat(java.lang.String Flat) {
		this.Flat = Flat;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Address.Type")
	public java.lang.String getRegionType() {
		return this.RegionType;
	}

	public void setRegionType(java.lang.String RegionType) {
		this.RegionType = RegionType;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.KladrCode")
	public java.lang.String getKladrCode() {
		return this.KladrCode;
	}

	public void setKladrCode(java.lang.String KladrCode) {
		this.KladrCode = KladrCode;
	}

}