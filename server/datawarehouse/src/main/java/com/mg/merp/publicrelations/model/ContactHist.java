/*
 * ContactHist.java
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
package com.mg.merp.publicrelations.model;



/**
 * @author hbm2java
 * @version $Id: ContactHist.java,v 1.2 2005/06/28 10:04:07 pashistova Exp $
 */
public class ContactHist extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.publicrelations.model.PossibleResult PossibleResult;
 private com.mg.merp.reference.model.Employees AcceptEmpl;
 private com.mg.merp.reference.model.PartnerEmpl PartnEmpl;
 private com.mg.merp.reference.model.PersonPhone PartnEmplPhone;
 private com.mg.merp.reference.model.Partner Partner;
 private com.mg.merp.reference.model.Folder Folder;
 private com.mg.merp.reference.model.Employees ToEmpl;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.publicrelations.model.ContactKind ContactKind;
 private java.util.Date CDate;
 private java.lang.String CDescription;
 private java.lang.Integer PartnEmplEmailId;
 private java.lang.String ResultComment;
 private java.util.Date NextCDate;
 private java.lang.Integer Priority;
 private java.lang.String Unid;
 private java.util.Set SetOfPrContactHistFiles;


    // Constructors

    /** default constructor */
    public ContactHist() {
    }
    
    /** constructor with id */
    public ContactHist(int Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public int getId () {
        return this.Id;
    }
    
   public void setId (int Id) {
        this.Id = Id;
    }
    /**
    
    */
    
    public com.mg.merp.publicrelations.model.PossibleResult getPossibleResult () {
        return this.PossibleResult;
    }
    
   public void setPossibleResult (com.mg.merp.publicrelations.model.PossibleResult PrPossibleResult) {
        this.PossibleResult = PrPossibleResult;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Employees getAcceptEmpl () {
        return this.AcceptEmpl;
    }
    
   public void setAcceptEmpl (com.mg.merp.reference.model.Employees Employees) {
        this.AcceptEmpl = Employees;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.PartnerEmpl getPartnEmpl () {
        return this.PartnEmpl;
    }
    
   public void setPartnEmpl (com.mg.merp.reference.model.PartnerEmpl Partnempl) {
        this.PartnEmpl = Partnempl;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.PersonPhone getPartnEmplPhone () {
        return this.PartnEmplPhone;
    }
    
   public void setPartnEmplPhone (com.mg.merp.reference.model.PersonPhone RefPersonPhone) {
        this.PartnEmplPhone = RefPersonPhone;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Partner getPartner () {
        return this.Partner;
    }
    
   public void setPartner (com.mg.merp.reference.model.Partner Partner) {
        this.Partner = Partner;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Folder getFolder () {
        return this.Folder;
    }
    
   public void setFolder (com.mg.merp.reference.model.Folder Folder) {
        this.Folder = Folder;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Employees getToEmpl () {
        return this.ToEmpl;
    }
    
   public void setToEmpl (com.mg.merp.reference.model.Employees Employees_1) {
        this.ToEmpl = Employees_1;
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
    
    public com.mg.merp.publicrelations.model.ContactKind getContactKind () {
        return this.ContactKind;
    }
    
   public void setContactKind (com.mg.merp.publicrelations.model.ContactKind PrContactKind) {
        this.ContactKind = PrContactKind;
    }
    /**
    
    */
    
    public java.util.Date getCDate () {
        return this.CDate;
    }
    
   public void setCDate (java.util.Date Cdate) {
        this.CDate = Cdate;
    }
    /**
    
    */
    
    public java.lang.String getCDescription () {
        return this.CDescription;
    }
    
   public void setCDescription (java.lang.String Cdescription) {
        this.CDescription = Cdescription;
    }
    /**
    
    */
    
    public java.lang.Integer getPartnEmplEmailId () {
        return this.PartnEmplEmailId;
    }
    
   public void setPartnEmplEmailId (java.lang.Integer PartnemplemailId) {
        this.PartnEmplEmailId = PartnemplemailId;
    }
    /**
    
    */
    
    public java.lang.String getResultComment () {
        return this.ResultComment;
    }
    
   public void setResultComment (java.lang.String ResultComment) {
        this.ResultComment = ResultComment;
    }
    /**
    
    */
    
    public java.util.Date getNextCDate () {
        return this.NextCDate;
    }
    
   public void setNextCDate (java.util.Date Nextcdate) {
        this.NextCDate = Nextcdate;
    }
    /**
    
    */
    
    public java.lang.Integer getPriority () {
        return this.Priority;
    }
    
   public void setPriority (java.lang.Integer Priority) {
        this.Priority = Priority;
    }
    /**
    
    */
    
    public java.lang.String getUnid () {
        return this.Unid;
    }
    
   public void setUnid (java.lang.String Unid) {
        this.Unid = Unid;
    }
    /**
    
    */
    
    public java.util.Set getSetOfPrContactHistFiles () {
        return this.SetOfPrContactHistFiles;
    }
    
   public void setSetOfPrContactHistFiles (java.util.Set SetOfPrContactHistFiles) {
        this.SetOfPrContactHistFiles = SetOfPrContactHistFiles;
    }




}