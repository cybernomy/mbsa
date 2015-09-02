/*
 * AccBatch.java
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
 * @version $Id: AccBatch.java,v 1.2 2005/07/26 10:28:59 safonov Exp $
 */
public class AccBatch extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.account.model.AccPlan AccPlan;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.reference.model.Contractor Contractor;
 private java.util.Date InComeDate;
 private java.math.BigDecimal InComeCostNat;
 private java.math.BigDecimal InComeCostCur;
 private java.lang.Integer CatalogId;
 private java.lang.Short DocSection;
 private java.lang.Integer DocId;
 private java.lang.String DocType;
 private java.lang.String DocNumber;
 private java.util.Date DocDate;
 private java.math.BigDecimal BeginQuan;
 private java.math.BigDecimal EndQuan;
 private java.lang.Integer AnlPlan1Id;
 private java.lang.Integer AnlPlan2Id;
 private java.lang.Integer AnlPlan3Id;
 private java.lang.Integer AnlPlan4Id;
 private java.lang.Integer AnlPlan5Id;
 private java.util.Set setOfAccBatchHistory;


    // Constructors

    /** default constructor */
    public AccBatch() {
    }
    
    /** constructor with id */
    public AccBatch(java.lang.Integer Id) {
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
    
    public com.mg.merp.account.model.AccPlan getAccPlan () {
        return this.AccPlan;
    }
    
   public void setAccPlan (com.mg.merp.account.model.AccPlan AccPlan) {
        this.AccPlan = AccPlan;
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
    
    public com.mg.merp.reference.model.Contractor getContractor () {
        return this.Contractor;
    }
    
   public void setContractor (com.mg.merp.reference.model.Contractor Contractor) {
        this.Contractor = Contractor;
    }
    /**
    
    */
    
    public java.util.Date getInComeDate () {
        return this.InComeDate;
    }
    
   public void setInComeDate (java.util.Date InComeDate) {
        this.InComeDate = InComeDate;
    }
    /**
    
    */
    
    public java.math.BigDecimal getInComeCostNat () {
        return this.InComeCostNat;
    }
    
   public void setInComeCostNat (java.math.BigDecimal InComeCostNat) {
        this.InComeCostNat = InComeCostNat;
    }
    /**
    
    */
    
    public java.math.BigDecimal getInComeCostCur () {
        return this.InComeCostCur;
    }
    
   public void setInComeCostCur (java.math.BigDecimal InComeCostCur) {
        this.InComeCostCur = InComeCostCur;
    }
    /**
    
    */
    
    public java.lang.Integer getCatalogId () {
        return this.CatalogId;
    }
    
   public void setCatalogId (java.lang.Integer CatalogId) {
        this.CatalogId = CatalogId;
    }
    /**
    
    */
    
    public java.lang.Short getDocSection () {
        return this.DocSection;
    }
    
   public void setDocSection (java.lang.Short DocSection) {
        this.DocSection = DocSection;
    }
    /**
    
    */
    
    public java.lang.Integer getDocId () {
        return this.DocId;
    }
    
   public void setDocId (java.lang.Integer DocId) {
        this.DocId = DocId;
    }
    /**
    
    */
    
    public java.lang.String getDocType () {
        return this.DocType;
    }
    
   public void setDocType (java.lang.String DocType) {
        this.DocType = DocType;
    }
    /**
    
    */
    
    public java.lang.String getDocNumber () {
        return this.DocNumber;
    }
    
   public void setDocNumber (java.lang.String DocNumber) {
        this.DocNumber = DocNumber;
    }
    /**
    
    */
    
    public java.util.Date getDocDate () {
        return this.DocDate;
    }
    
   public void setDocDate (java.util.Date DocDate) {
        this.DocDate = DocDate;
    }
    /**
    
    */
    
    public java.math.BigDecimal getBeginQuan () {
        return this.BeginQuan;
    }
    
   public void setBeginQuan (java.math.BigDecimal BeginQuan) {
        this.BeginQuan = BeginQuan;
    }
    /**
    
    */
    
    public java.math.BigDecimal getEndQuan () {
        return this.EndQuan;
    }
    
   public void setEndQuan (java.math.BigDecimal EndQuan) {
        this.EndQuan = EndQuan;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlPlan1Id () {
        return this.AnlPlan1Id;
    }
    
   public void setAnlPlan1Id (java.lang.Integer AnlPlan1Id) {
        this.AnlPlan1Id = AnlPlan1Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlPlan2Id () {
        return this.AnlPlan2Id;
    }
    
   public void setAnlPlan2Id (java.lang.Integer AnlPlan2Id) {
        this.AnlPlan2Id = AnlPlan2Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlPlan3Id () {
        return this.AnlPlan3Id;
    }
    
   public void setAnlPlan3Id (java.lang.Integer AnlPlan3Id) {
        this.AnlPlan3Id = AnlPlan3Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlPlan4Id () {
        return this.AnlPlan4Id;
    }
    
   public void setAnlPlan4Id (java.lang.Integer AnlPlan4Id) {
        this.AnlPlan4Id = AnlPlan4Id;
    }
    /**
    
    */
    
    public java.lang.Integer getAnlPlan5Id () {
        return this.AnlPlan5Id;
    }
    
   public void setAnlPlan5Id (java.lang.Integer AnlPlan5Id) {
        this.AnlPlan5Id = AnlPlan5Id;
    }
    /**
    
    */
    
    public java.util.Set getSetOfAccBatchHistory () {
        return this.setOfAccBatchHistory;
    }
    
   public void setSetOfAccBatchHistory (java.util.Set SetOfAccbatchhistory) {
        this.setOfAccBatchHistory = SetOfAccbatchhistory;
    }
}