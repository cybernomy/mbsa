/*
 * AccBatchHistory.java
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
 * @version $Id: AccBatchHistory.java,v 1.2 2005/07/26 10:28:57 safonov Exp $
 */
public class AccBatchHistory extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.account.model.AccBatch AccBatch;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.util.Date BeginDate;
 private java.util.Date EndDate;
 private java.math.BigDecimal Quantity;
 private java.math.BigDecimal CostNat;
 private java.math.BigDecimal CostCur;
 private java.lang.Short ActionType;


    // Constructors

    /** default constructor */
    public AccBatchHistory() {
    }
    
    /** constructor with id */
    public AccBatchHistory(java.lang.Integer Id) {
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
    
    public com.mg.merp.account.model.AccBatch getAccBatch () {
        return this.AccBatch;
    }
    
   public void setAccBatch (com.mg.merp.account.model.AccBatch AccBatch) {
        this.AccBatch = AccBatch;
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
    
    public java.util.Date getBeginDate () {
        return this.BeginDate;
    }
    
   public void setBeginDate (java.util.Date BeginDate) {
        this.BeginDate = BeginDate;
    }
    /**
    
    */
    
    public java.util.Date getEndDate () {
        return this.EndDate;
    }
    
   public void setEndDate (java.util.Date EndDate) {
        this.EndDate = EndDate;
    }
    /**
    
    */
    
    public java.math.BigDecimal getQuantity () {
        return this.Quantity;
    }
    
   public void setQuantity (java.math.BigDecimal Quantity) {
        this.Quantity = Quantity;
    }
    /**
    
    */
    
    public java.math.BigDecimal getCostNat () {
        return this.CostNat;
    }
    
   public void setCostNat (java.math.BigDecimal CostNat) {
        this.CostNat = CostNat;
    }
    /**
    
    */
    
    public java.math.BigDecimal getCostCur () {
        return this.CostCur;
    }
    
   public void setCostCur (java.math.BigDecimal CostCur) {
        this.CostCur = CostCur;
    }
    /**
    
    */
    
    public java.lang.Short getActionType () {
        return this.ActionType;
    }
    
   public void setActionType (java.lang.Short ActionType) {
        this.ActionType = ActionType;
    }
}