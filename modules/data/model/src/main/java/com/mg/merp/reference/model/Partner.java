/*
 * Partner.java
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
 * @version $Id: Partner.java,v 1.7 2007/10/04 07:31:35 sharapov Exp $
 */
@DataItemName("Reference.Partner")
public class Partner extends com.mg.merp.reference.model.Contractor implements
    java.io.Serializable {

  // Fields

  private com.mg.merp.reference.model.Region Region1;

  private com.mg.merp.reference.model.Country Country1;

  private com.mg.merp.reference.model.District District1;

  private com.mg.merp.reference.model.Country Country;

  private com.mg.merp.reference.model.Place Place1;

  private com.mg.merp.reference.model.ZipCode ZipCode;

  private com.mg.merp.reference.model.Region Region;

  private com.mg.merp.reference.model.Currency MaxSupCreditCur;

  private com.mg.merp.reference.model.ZipCode ZipCode1;

  private com.mg.merp.reference.model.Currency MaxCusCreditCur;

  private com.mg.merp.reference.model.District District;

  private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

  private com.mg.merp.reference.model.Place Place;

  private java.lang.String INN;

  private java.lang.String OKONH;

  private java.lang.String OKPO;

  private java.lang.String City;

  private java.lang.String Area;

  private java.lang.String Address;

  private java.lang.String CityLegal;

  private java.lang.String AreaLegal;

  private java.lang.String AddressLegal;

  private java.lang.String Comment;

  private java.math.BigDecimal MaxSupCredit;

  private java.lang.Short TermSupCredit;

  private java.math.BigDecimal PenaltySup;

  private java.math.BigDecimal MaxCusCredit;

  private java.lang.Short TermCusCredit;

  private java.math.BigDecimal PenaltyCus;

  private java.lang.String KPP;

  private java.lang.String Zip;

  private java.lang.String ZipLegal;

  private java.lang.Boolean INNUnique;

  private java.lang.String OKVED;

  private java.lang.String Street;

  private java.lang.String House;

  private java.lang.String Building;

  private java.lang.String Room;

  private java.lang.String Street1;

  private java.lang.String House1;

  private java.lang.String Building1;

  private java.lang.String Room1;

  //private java.lang.String DiscountCard;

  private java.util.Set SetOfRefPartnEmplLink;

  private java.util.Set SetOfRefConfidentialData;

  private java.util.Set SetOfPartnEmpl;

  // Constructors

  /**
   * default constructor
   */
  public Partner() {
  }

  // Property accessors

  /**
   *
   */

  public com.mg.merp.reference.model.Region getRegion1() {
    return this.Region1;
  }

  public void setRegion1(com.mg.merp.reference.model.Region RefRegion) {
    this.Region1 = RefRegion;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Country getCountry1() {
    return this.Country1;
  }

  public void setCountry1(com.mg.merp.reference.model.Country RefCountry) {
    this.Country1 = RefCountry;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.District getDistrict1() {
    return this.District1;
  }

  public void setDistrict1(com.mg.merp.reference.model.District RefDistrict) {
    this.District1 = RefDistrict;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Country getCountry() {
    return this.Country;
  }

  public void setCountry(com.mg.merp.reference.model.Country RefCountry_1) {
    this.Country = RefCountry_1;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Place getPlace1() {
    return this.Place1;
  }

  public void setPlace1(com.mg.merp.reference.model.Place RefPlace) {
    this.Place1 = RefPlace;
  }


  public com.mg.merp.reference.model.ZipCode getZipCode() {
    return this.ZipCode;
  }

  public void setZipCode(com.mg.merp.reference.model.ZipCode RefZipcode) {
    this.ZipCode = RefZipcode;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Region getRegion() {
    return this.Region;
  }

  public void setRegion(com.mg.merp.reference.model.Region RefRegion_1) {
    this.Region = RefRegion_1;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.SupCurrency")
  public com.mg.merp.reference.model.Currency getMaxSupCreditCur() {
    return this.MaxSupCreditCur;
  }

  public void setMaxSupCreditCur(com.mg.merp.reference.model.Currency Currency) {
    this.MaxSupCreditCur = Currency;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.ZipCode getZipCode1() {
    return this.ZipCode1;
  }

  public void setZipCode1(com.mg.merp.reference.model.ZipCode RefZipcode_1) {
    this.ZipCode1 = RefZipcode_1;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.CusCurrency")
  public com.mg.merp.reference.model.Currency getMaxCusCreditCur() {
    return this.MaxCusCreditCur;
  }

  public void setMaxCusCreditCur(
      com.mg.merp.reference.model.Currency Currency_1) {
    this.MaxCusCreditCur = Currency_1;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.District getDistrict() {
    return this.District;
  }

  public void setDistrict(com.mg.merp.reference.model.District RefDistrict_1) {
    this.District = RefDistrict_1;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
    return this.NaturalPerson;
  }

  public void setNaturalPerson(
      com.mg.merp.reference.model.NaturalPerson RefNaturalPerson) {
    this.NaturalPerson = RefNaturalPerson;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Place getPlace() {
    return this.Place;
  }

  public void setPlace(com.mg.merp.reference.model.Place RefPlace_1) {
    this.Place = RefPlace_1;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Inn")
  public java.lang.String getINN() {
    return this.INN;
  }

  public void setINN(java.lang.String Inn) {
    this.INN = Inn;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.OKONH")
  public java.lang.String getOKONH() {
    return this.OKONH;
  }

  public void setOKONH(java.lang.String Okonh) {
    this.OKONH = Okonh;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.OKPO")
  public java.lang.String getOKPO() {
    return this.OKPO;
  }

  public void setOKPO(java.lang.String Okpo) {
    this.OKPO = Okpo;
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

  public java.lang.String getArea() {
    return this.Area;
  }

  public void setArea(java.lang.String Area) {
    this.Area = Area;
  }

  /**
   *
   */

  public java.lang.String getAddress() {
    return this.Address;
  }

  public void setAddress(java.lang.String Address) {
    this.Address = Address;
  }

  /**
   *
   */

  public java.lang.String getCityLegal() {
    return this.CityLegal;
  }

  public void setCityLegal(java.lang.String Citylegal) {
    this.CityLegal = Citylegal;
  }

  /**
   *
   */

  public java.lang.String getAreaLegal() {
    return this.AreaLegal;
  }

  public void setAreaLegal(java.lang.String Arealegal) {
    this.AreaLegal = Arealegal;
  }

  /**
   *
   */

  public java.lang.String getAddressLegal() {
    return this.AddressLegal;
  }

  public void setAddressLegal(java.lang.String Addresslegal) {
    this.AddressLegal = Addresslegal;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.SupKredit")
  public java.math.BigDecimal getMaxSupCredit() {
    return this.MaxSupCredit;
  }

  public void setMaxSupCredit(java.math.BigDecimal Maxsupcredit) {
    this.MaxSupCredit = Maxsupcredit;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.TermSupCredit")
  public java.lang.Short getTermSupCredit() {
    return this.TermSupCredit;
  }

  public void setTermSupCredit(java.lang.Short Termsupcredit) {
    this.TermSupCredit = Termsupcredit;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.PenaltySup")
  public java.math.BigDecimal getPenaltySup() {
    return this.PenaltySup;
  }

  public void setPenaltySup(java.math.BigDecimal Penaltysup) {
    this.PenaltySup = Penaltysup;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.CusKredit")
  public java.math.BigDecimal getMaxCusCredit() {
    return this.MaxCusCredit;
  }

  public void setMaxCusCredit(java.math.BigDecimal Maxcuscredit) {
    this.MaxCusCredit = Maxcuscredit;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.TermCusCredit")
  public java.lang.Short getTermCusCredit() {
    return this.TermCusCredit;
  }

  public void setTermCusCredit(java.lang.Short Termcuscredit) {
    this.TermCusCredit = Termcuscredit;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.PenaltyCus")
  public java.math.BigDecimal getPenaltyCus() {
    return this.PenaltyCus;
  }

  public void setPenaltyCus(java.math.BigDecimal Penaltycus) {
    this.PenaltyCus = Penaltycus;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Kpp")
  public java.lang.String getKPP() {
    return this.KPP;
  }

  public void setKPP(java.lang.String Kpp) {
    this.KPP = Kpp;
  }

  /**
   *
   */

  public java.lang.String getZip() {
    return this.Zip;
  }

  public void setZip(java.lang.String Zip) {
    this.Zip = Zip;
  }

  /**
   *
   */

  public java.lang.String getZipLegal() {
    return this.ZipLegal;
  }

  public void setZipLegal(java.lang.String Ziplegal) {
    this.ZipLegal = Ziplegal;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.INNUnique")
  public java.lang.Boolean getINNUnique() {
    return this.INNUnique;
  }

  public void setINNUnique(java.lang.Boolean InnUnique) {
    this.INNUnique = InnUnique;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.OKVED")
  public java.lang.String getOKVED() {
    return this.OKVED;
  }

  public void setOKVED(java.lang.String Okved) {
    this.OKVED = Okved;
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

  /**
   *
   */
  @DataItemName("Reference.Street")
  public java.lang.String getStreet1() {
    return this.Street1;
  }

  public void setStreet1(java.lang.String Street1) {
    this.Street1 = Street1;
  }

  /**
   *
   */
  @DataItemName("Reference.House")
  public java.lang.String getHouse1() {
    return this.House1;
  }

  public void setHouse1(java.lang.String House1) {
    this.House1 = House1;
  }

  /**
   *
   */
  @DataItemName("Reference.Block")
  public java.lang.String getBuilding1() {
    return this.Building1;
  }

  public void setBuilding1(java.lang.String Building1) {
    this.Building1 = Building1;
  }

  /**
   *
   */
  @DataItemName("Reference.Flat")
  public java.lang.String getRoom1() {
    return this.Room1;
  }

  public void setRoom1(java.lang.String Room1) {
    this.Room1 = Room1;
  }

//	/**
//	 * 
//	 */
//
//	public java.lang.String getDiscountCard() {
//		return this.DiscountCard;
//	}
//
//	public void setDiscountCard(java.lang.String DiscountCard) {
//		this.DiscountCard = DiscountCard;
//	}

  /**
   *
   */

  public java.util.Set getSetOfRefPartnEmplLink() {
    return this.SetOfRefPartnEmplLink;
  }

  public void setSetOfRefPartnEmplLink(java.util.Set SetOfRefPartnEmplLink) {
    this.SetOfRefPartnEmplLink = SetOfRefPartnEmplLink;
  }

  /**
   *
   */

  public java.util.Set getSetOfRefConfidentialData() {
    return this.SetOfRefConfidentialData;
  }

  public void setSetOfRefConfidentialData(
      java.util.Set SetOfRefConfidentialData) {
    this.SetOfRefConfidentialData = SetOfRefConfidentialData;
  }

  /**
   *
   */

  public java.util.Set getSetOfPartnEmpl() {
    return this.SetOfPartnEmpl;
  }

  public void setSetOfPartnEmpl(java.util.Set SetOfPartnempl) {
    this.SetOfPartnEmpl = SetOfPartnempl;
  }

}