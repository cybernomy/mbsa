/*
 * TurnOnEsFeatures.java
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
package com.mg.merp.account.model;



/**
 * @author hbm2java
 * @version $Id: TurnOnEsFeatures.java,v 1.1 2005/06/21 07:06:03 pashistova Exp $
 */
public class TurnOnEsFeatures extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String UserName;
 private java.lang.String AccDbCode;
 private java.lang.String AccKtCode;
 private java.lang.Integer AnlDb1Id;
 private java.lang.Integer AnlDb2Id;
 private java.lang.Integer AnlDb3Id;
 private java.lang.Integer AnlDb4Id;
 private java.lang.Integer AnlDb5Id;
 private java.lang.Integer AnlKt1Id;
 private java.lang.Integer AnlKt2Id;
 private java.lang.Integer AnlKt3Id;
 private java.lang.Integer AnlKt4Id;
 private java.lang.Integer AnlKt5Id;
 private java.lang.Integer Feature1Id;
 private java.lang.Integer Feature2Id;
 private java.lang.Integer Feature3Id;
 private java.lang.Integer Feature4Id;
 private java.lang.Integer Feature5Id;
 private java.lang.Integer OrgUnitId;
 private java.math.BigDecimal SummNat;
 private java.math.BigDecimal SummCur;
 private java.util.Date OperDate;
 private java.lang.Short TotalSign;


    // Constructors

    /** default constructor */
    public TurnOnEsFeatures() {
    }
    
    /** constructor with id */
    public TurnOnEsFeatures(java.lang.Integer Id) {
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
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }
    /**
    
    */
    
    public java.lang.String getUserName () {
        return this.UserName;
    }
    
   public void setUserName (java.lang.String Username) {
        this.UserName = Username;
    }
    /**
    
    */
    
    public java.lang.String getAccDbCode () {
        return this.AccDbCode;
    }
    
   public void setAccDbCode (java.lang.String AccdbCode) {
        this.AccDbCode = AccdbCode;
    }
    /**
    
    */
    
    public java.lang.String getAccKtCode () {
        return this.AccKtCode;
    }
    
   public void setAccKtCode (java.lang.String AccktCode) {
        this.AccKtCode = AccktCode;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlDb1Id () {
        return this.AnlDb1Id;
    }
    
   public void setAnlDb1Id (java.lang.Integer Anldb1Id) {
        this.AnlDb1Id = Anldb1Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlDb2Id () {
        return this.AnlDb2Id;
    }
    
   public void setAnlDb2Id (java.lang.Integer Anldb2Id) {
        this.AnlDb2Id = Anldb2Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlDb3Id () {
        return this.AnlDb3Id;
    }
    
   public void setAnlDb3Id (java.lang.Integer Anldb3Id) {
        this.AnlDb3Id = Anldb3Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlDb4Id () {
        return this.AnlDb4Id;
    }
    
   public void setAnlDb4Id (java.lang.Integer Anldb4Id) {
        this.AnlDb4Id = Anldb4Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlDb5Id () {
        return this.AnlDb5Id;
    }
    
   public void setAnlDb5Id (java.lang.Integer Anldb5Id) {
        this.AnlDb5Id = Anldb5Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlKt1Id () {
        return this.AnlKt1Id;
    }
    
   public void setAnlKt1Id (java.lang.Integer Anlkt1Id) {
        this.AnlKt1Id = Anlkt1Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlKt2Id () {
        return this.AnlKt2Id;
    }
    
   public void setAnlKt2Id (java.lang.Integer Anlkt2Id) {
        this.AnlKt2Id = Anlkt2Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlKt3Id () {
        return this.AnlKt3Id;
    }
    
   public void setAnlKt3Id (java.lang.Integer Anlkt3Id) {
        this.AnlKt3Id = Anlkt3Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlKt4Id () {
        return this.AnlKt4Id;
    }
    
   public void setAnlKt4Id (java.lang.Integer Anlkt4Id) {
        this.AnlKt4Id = Anlkt4Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlKt5Id () {
        return this.AnlKt5Id;
    }
    
   public void setAnlKt5Id (java.lang.Integer Anlkt5Id) {
        this.AnlKt5Id = Anlkt5Id;
    }
    /**
    
    */
    
    public java.lang.Integer getFeature1Id () {
        return this.Feature1Id;
    }
    
   public void setFeature1Id (java.lang.Integer Feature1Id) {
        this.Feature1Id = Feature1Id;
    }
    /**
    
    */
    
    public java.lang.Integer getFeature2Id () {
        return this.Feature2Id;
    }
    
   public void setFeature2Id (java.lang.Integer Feature2Id) {
        this.Feature2Id = Feature2Id;
    }
    /**
    
    */
    
    public java.lang.Integer getFeature3Id () {
        return this.Feature3Id;
    }
    
   public void setFeature3Id (java.lang.Integer Feature3Id) {
        this.Feature3Id = Feature3Id;
    }
    /**
    
    */
    
    public java.lang.Integer getFeature4Id () {
        return this.Feature4Id;
    }
    
   public void setFeature4Id (java.lang.Integer Feature4Id) {
        this.Feature4Id = Feature4Id;
    }
    /**
    
    */
    
    public java.lang.Integer getFeature5Id () {
        return this.Feature5Id;
    }
    
   public void setFeature5Id (java.lang.Integer Feature5Id) {
        this.Feature5Id = Feature5Id;
    }
    /**
    
    */
    
    public java.lang.Integer getOrgUnitId () {
        return this.OrgUnitId;
    }
    
   public void setOrgUnitId (java.lang.Integer OrgunitId) {
        this.OrgUnitId = OrgunitId;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSummNat () {
        return this.SummNat;
    }
    
   public void setSummNat (java.math.BigDecimal Summnat) {
        this.SummNat = Summnat;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSummCur () {
        return this.SummCur;
    }
    
   public void setSummCur (java.math.BigDecimal Summcur) {
        this.SummCur = Summcur;
    }
    /**
    
    */
    
    public java.util.Date getOperDate () {
        return this.OperDate;
    }
    
   public void setOperDate (java.util.Date Operdate) {
        this.OperDate = Operdate;
    }
    /**
    
    */
    
    public java.lang.Short getTotalSign () {
        return this.TotalSign;
    }
    
   public void setTotalSign (java.lang.Short TotalSign) {
        this.TotalSign = TotalSign;
    }




}