/*
 * PersonnelHist.java
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



/**
 * @author hbm2java
 * @version $Id: PersonnelHist.java,v 1.2 2005/06/28 10:03:41 pashistova Exp $
 */
public class PersonnelHist extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.personnelref.model.Personnel Personnel;
 private com.mg.merp.personnelref.model.InsuredClass InsuredClass;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.util.Date ActDate;
 private java.lang.String TableNumber;
 private java.lang.String Name1;
 private java.lang.String Name2;
 private java.lang.String Name3;
 private java.util.Date BornDate;
 private java.lang.Integer Sex;
 private java.math.BigDecimal Stature;
 private java.lang.String MeasureUpCode;
 private java.io.Serializable Photo;
 private java.lang.String PensionNumber;
 private java.lang.String Inn;


    // Constructors

    /** default constructor */
    public PersonnelHist() {
    }
    
    /** constructor with id */
    public PersonnelHist(java.lang.Integer Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public java.lang.Integer getId () {
        return this.Id;
    }
    
   public void setId (java.lang.Integer Id) {
        this.Id = Id;
    }
    /**
    
    */
    
    public com.mg.merp.personnelref.model.Personnel getPersonnel () {
        return this.Personnel;
    }
    
   public void setPersonnel (com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
        this.Personnel = PrefPersonnel;
    }
    /**
    
    */
    
    public com.mg.merp.personnelref.model.InsuredClass getInsuredClass () {
        return this.InsuredClass;
    }
    
   public void setInsuredClass (com.mg.merp.personnelref.model.InsuredClass PrefInsuredclass) {
        this.InsuredClass = PrefInsuredclass;
    }
    /**
    
    */
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }
    /**
    
    */
    
    public java.util.Date getActDate () {
        return this.ActDate;
    }
    
   public void setActDate (java.util.Date ActDate) {
        this.ActDate = ActDate;
    }
    /**
    
    */
    
    public java.lang.String getTableNumber () {
        return this.TableNumber;
    }
    
   public void setTableNumber (java.lang.String TableNumber) {
        this.TableNumber = TableNumber;
    }
    /**
    
    */
    
    public java.lang.String getName1 () {
        return this.Name1;
    }
    
   public void setName1 (java.lang.String Name1) {
        this.Name1 = Name1;
    }
    /**
    
    */
    
    public java.lang.String getName2 () {
        return this.Name2;
    }
    
   public void setName2 (java.lang.String Name2) {
        this.Name2 = Name2;
    }
    /**
    
    */
    
    public java.lang.String getName3 () {
        return this.Name3;
    }
    
   public void setName3 (java.lang.String Name3) {
        this.Name3 = Name3;
    }
    /**
    
    */
    
    public java.util.Date getBornDate () {
        return this.BornDate;
    }
    
   public void setBornDate (java.util.Date Borndate) {
        this.BornDate = Borndate;
    }
    /**
    
    */
    
    public java.lang.Integer getSex () {
        return this.Sex;
    }
    
   public void setSex (java.lang.Integer Sex) {
        this.Sex = Sex;
    }
    /**
    
    */
    
    public java.math.BigDecimal getStature () {
        return this.Stature;
    }
    
   public void setStature (java.math.BigDecimal Stature) {
        this.Stature = Stature;
    }
    /**
    
    */
    
    public java.lang.String getMeasureUpCode () {
        return this.MeasureUpCode;
    }
    
   public void setMeasureUpCode (java.lang.String MeasureUpcode) {
        this.MeasureUpCode = MeasureUpcode;
    }
    /**
    
    */
    
    public java.io.Serializable getPhoto () {
        return this.Photo;
    }
    
   public void setPhoto (java.io.Serializable Photo) {
        this.Photo = Photo;
    }
    /**
    
    */
    
    public java.lang.String getPensionNumber () {
        return this.PensionNumber;
    }
    
   public void setPensionNumber (java.lang.String PensionNumber) {
        this.PensionNumber = PensionNumber;
    }
    /**
    
    */
    
    public java.lang.String getInn () {
        return this.Inn;
    }
    
   public void setInn (java.lang.String Inn) {
        this.Inn = Inn;
    }




}