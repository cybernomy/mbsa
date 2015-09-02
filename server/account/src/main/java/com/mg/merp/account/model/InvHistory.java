/*
 * InvHistory.java
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

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: InvHistory.java,v 1.4 2008/03/31 12:54:38 alikaev Exp $
 */
public class InvHistory extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.account.model.Inventory Inventory;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.account.model.Amortization AccAmortization;
 private com.mg.merp.account.model.EconomicSpec EconomicSpec;
 private com.mg.merp.account.model.EconomicOper EconomicOper;
 private InvActionKind Kind;
 private java.math.BigDecimal DeltaBalCost;
 private java.math.BigDecimal DeltaDeprVal;
 private java.math.BigDecimal RevalFactor;
 private java.math.BigDecimal RevalSum;
 private java.util.Date ActDate;
 private java.math.BigDecimal DeltaBeginLoss;
 private java.math.BigDecimal DeltaInitialLoss;
 private java.lang.String OldInvLocation;
 private java.lang.Integer OldInvLocationId;
 private java.lang.String OldInOperDocNum;
 private java.util.Date OldInOperDate;


    // Constructors

    /** default constructor */
    public InvHistory() {
    }
    
    /** constructor with id */
    public InvHistory(int Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    @DataItemName("ID")
    public int getId () {
        return this.Id;
    }
    
   public void setId (int Id) {
        this.Id = Id;
    }
    /**
    
    */
    
    public com.mg.merp.account.model.Inventory getInventory () {
        return this.Inventory;
    }
    
   public void setInventory (com.mg.merp.account.model.Inventory Inventory) {
        this.Inventory = Inventory;
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
    
    public com.mg.merp.account.model.Amortization getAccAmortization () {
        return this.AccAmortization;
    }
    
   public void setAccAmortization (com.mg.merp.account.model.Amortization AccAmortization) {
        this.AccAmortization = AccAmortization;
    }
    /**
    
    */
    
    public com.mg.merp.account.model.EconomicSpec getEconomicSpec () {
        return this.EconomicSpec;
    }
    
   public void setEconomicSpec (com.mg.merp.account.model.EconomicSpec Economicspec) {
        this.EconomicSpec = Economicspec;
    }
    /**
    
    */
    
    public com.mg.merp.account.model.EconomicOper getEconomicOper () {
        return this.EconomicOper;
    }
    
   public void setEconomicOper (com.mg.merp.account.model.EconomicOper Economicoper) {
        this.EconomicOper = Economicoper;
    }
    /**
    
    */
    
    public InvActionKind getKind () {
        return this.Kind;
    }
    
   public void setKind (InvActionKind Kind) {
        this.Kind = Kind;
    }
    /**
    
    */
   @DataItemName("Account.InvHistory.DeltaBalCost")
    public java.math.BigDecimal getDeltaBalCost () {
        return this.DeltaBalCost;
    }
    
   public void setDeltaBalCost (java.math.BigDecimal DeltaBalcost) {
        this.DeltaBalCost = DeltaBalcost;
    }
    /**
    
    */
   @DataItemName("Account.InvHistory.DeltaDeprVal")
    public java.math.BigDecimal getDeltaDeprVal () {
        return this.DeltaDeprVal;
    }
    
   public void setDeltaDeprVal (java.math.BigDecimal DeltaDeprval) {
        this.DeltaDeprVal = DeltaDeprval;
    }
    /**
    
    */
   @DataItemName("Account.InvHistory.RevalFactor") 
    public java.math.BigDecimal getRevalFactor () {
        return this.RevalFactor;
    }
    
   public void setRevalFactor (java.math.BigDecimal RevalFactor) {
        this.RevalFactor = RevalFactor;
    }
    /**
    
    */
   @DataItemName("Account.InvHistory.RevalSum")  
    public java.math.BigDecimal getRevalSum () {
        return this.RevalSum;
    }
    
   public void setRevalSum (java.math.BigDecimal RevalSum) {
        this.RevalSum = RevalSum;
    }
    /**
    
    */
    @DataItemName("Account.InvHistory.ActDate")
    public java.util.Date getActDate () {
        return this.ActDate;
    }
    
   public void setActDate (java.util.Date ActDate) {
        this.ActDate = ActDate;
    }
    /**
    
    */
    
    public java.math.BigDecimal getDeltaBeginLoss () {
        return this.DeltaBeginLoss;
    }
    
   public void setDeltaBeginLoss (java.math.BigDecimal DeltaBeginloss) {
        this.DeltaBeginLoss = DeltaBeginloss;
    }
    /**
    
    */
    
    public java.math.BigDecimal getDeltaInitialLoss () {
        return this.DeltaInitialLoss;
    }
    
   public void setDeltaInitialLoss (java.math.BigDecimal DeltaInitialloss) {
        this.DeltaInitialLoss = DeltaInitialloss;
    }
    /**
    
    */
    
    public java.lang.String getOldInvLocation () {
        return this.OldInvLocation;
    }
    
   public void setOldInvLocation (java.lang.String OldInvlocation) {
        this.OldInvLocation = OldInvlocation;
    }
    /**
    
    */
    
    public java.lang.Integer getOldInvLocationId () {
        return this.OldInvLocationId;
    }
    
   public void setOldInvLocationId (java.lang.Integer OldInvlocationId) {
        this.OldInvLocationId = OldInvlocationId;
    }
    /**
    
    */
    
    public java.lang.String getOldInOperDocNum () {
        return this.OldInOperDocNum;
    }
    
   public void setOldInOperDocNum (java.lang.String OldInoperdocnum) {
        this.OldInOperDocNum = OldInoperdocnum;
    }
    /**
    
    */
    
    public java.util.Date getOldInOperDate () {
        return this.OldInOperDate;
    }
    
   public void setOldInOperDate (java.util.Date OldInoperdate) {
        this.OldInOperDate = OldInoperdate;
    }




}