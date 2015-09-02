/*
 * IdentDoc.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: IdentDoc.java,v 1.4 2006/08/11 07:35:48 leonova Exp $
 */
@DataItemName("Reference.IdentDoc")
public class IdentDoc extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Nationality Nationality;

	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

	private com.mg.merp.reference.model.IdentDocKind IdentDocKind;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.PersonAddress PersonAddress;

	private boolean IsBasic;

	private java.lang.String SeriesLeft;

	private java.lang.String SeriesDivider;

	private java.lang.String SeriesRight;

	private java.lang.String DocNumber;

	private java.lang.String WhoIssued;

	private java.util.Date WhenIssued;

	private java.util.Date ActiveTill;

	private java.lang.String CitizenShip;

	private java.util.Date Birthdate;

	private java.lang.String BirthPlaceCountry;

	private java.lang.String BirthPlaceRegion;

	private java.lang.String BirthPlaceDistrict;

	private java.lang.String BirthPlaceCity;

	private java.lang.String BirthPlaceOkato;

	// Constructors

	/** default constructor */
	public IdentDoc() {
	}

	/** constructor with id */
	public IdentDoc(int Id) {
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

	public com.mg.merp.reference.model.Nationality getNationality() {
		return this.Nationality;
	}

	public void setNationality(
			com.mg.merp.reference.model.Nationality Nationality) {
		this.Nationality = Nationality;
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
	public com.mg.merp.reference.model.IdentDocKind getIdentDocKind() {
		return this.IdentDocKind;
	}

	public void setIdentDocKind(
			com.mg.merp.reference.model.IdentDocKind IdentDocKind) {
		this.IdentDocKind = IdentDocKind;
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
	@DataItemName("Reference.NPers.Doc.Address")
	public com.mg.merp.reference.model.PersonAddress getPersonAddress() {
		return this.PersonAddress;
	}

	public void setPersonAddress(
			com.mg.merp.reference.model.PersonAddress PersonAddress) {
		this.PersonAddress = PersonAddress;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.IsBasic")
	public boolean getIsBasic() {
		return this.IsBasic;
	}

	public void setIsBasic(boolean IsBasic) {
		this.IsBasic = IsBasic;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.SeriesLeft")
	public java.lang.String getSeriesLeft() {
		return this.SeriesLeft;
	}

	public void setSeriesLeft(java.lang.String SeriesLeft) {
		this.SeriesLeft = SeriesLeft;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.SeriesDivider")
	public java.lang.String getSeriesDivider() {
		return this.SeriesDivider;
	}

	public void setSeriesDivider(java.lang.String SeriesDivider) {
		this.SeriesDivider = SeriesDivider;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.SeriesRight")
	public java.lang.String getSeriesRight() {
		return this.SeriesRight;
	}

	public void setSeriesRight(java.lang.String SeriesRight) {
		this.SeriesRight = SeriesRight;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.DocNumber")
	public java.lang.String getDocNumber() {
		return this.DocNumber;
	}

	public void setDocNumber(java.lang.String DocNumber) {
		this.DocNumber = DocNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.WhoIssued")
	public java.lang.String getWhoIssued() {
		return this.WhoIssued;
	}

	public void setWhoIssued(java.lang.String WhoIssued) {
		this.WhoIssued = WhoIssued;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.WhenIssued")
	public java.util.Date getWhenIssued() {
		return this.WhenIssued;
	}

	public void setWhenIssued(java.util.Date WhenIssued) {
		this.WhenIssued = WhenIssued;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.IdentDoc.ActiveTill")
	public java.util.Date getActiveTill() {
		return this.ActiveTill;
	}

	public void setActiveTill(java.util.Date ActiveTill) {
		this.ActiveTill = ActiveTill;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.CitizenShip")
	public java.lang.String getCitizenShip() {
		return this.CitizenShip;
	}

	public void setCitizenShip(java.lang.String CitizenShip) {
		this.CitizenShip = CitizenShip;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Birthdate")
	public java.util.Date getBirthdate() {
		return this.Birthdate;
	}

	public void setBirthdate(java.util.Date Birthdate) {
		this.Birthdate = Birthdate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BirthPlaceCountry")
	public java.lang.String getBirthPlaceCountry() {
		return this.BirthPlaceCountry;
	}

	public void setBirthPlaceCountry(java.lang.String BirthPlaceCountry) {
		this.BirthPlaceCountry = BirthPlaceCountry;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BirthPlaceRegion")
	public java.lang.String getBirthPlaceRegion() {
		return this.BirthPlaceRegion;
	}

	public void setBirthPlaceRegion(java.lang.String BirthPlaceRegion) {
		this.BirthPlaceRegion = BirthPlaceRegion;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BirthPlaceDistrict")
	public java.lang.String getBirthPlaceDistrict() {
		return this.BirthPlaceDistrict;
	}

	public void setBirthPlaceDistrict(java.lang.String BirthPlaceDistrict) {
		this.BirthPlaceDistrict = BirthPlaceDistrict;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BirthPlaceCity")
	public java.lang.String getBirthPlaceCity() {
		return this.BirthPlaceCity;
	}

	public void setBirthPlaceCity(java.lang.String BirthPlaceCity) {
		this.BirthPlaceCity = BirthPlaceCity;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.BirthPlaceOkato")
	public java.lang.String getBirthPlaceOkato() {
		return this.BirthPlaceOkato;
	}

	public void setBirthPlaceOkato(java.lang.String BirthPlaceOkato) {
		this.BirthPlaceOkato = BirthPlaceOkato;
	}

}