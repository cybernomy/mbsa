/*
 * PhaseItemLink.java
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
package com.mg.merp.contract.model;


/**
 * @author hbm2java
 * @version $Id: PhaseItemLink.java,v 1.2 2005/06/21 07:03:33 pashistova Exp $
 */
public class PhaseItemLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.contract.model.PhasePlanItem PhaseItemPlan;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.contract.model.PhaseFactItem PhaseItemFact;
 private java.math.BigDecimal DistSum;


    // Constructors

    /** default constructor */
    public PhaseItemLink() {
    }
    
    /** constructor with id */
    public PhaseItemLink(java.lang.Integer Id) {
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
    
    public com.mg.merp.contract.model.PhasePlanItem getPhaseItemPlan () {
        return this.PhaseItemPlan;
    }
    
   public void setPhaseItemPlan (com.mg.merp.contract.model.PhasePlanItem Phaseitemplan) {
        this.PhaseItemPlan = Phaseitemplan;
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
    
    public com.mg.merp.contract.model.PhaseFactItem getPhaseItemFact () {
        return this.PhaseItemFact;
    }
    
   public void setPhaseItemFact (com.mg.merp.contract.model.PhaseFactItem Phaseitemfact) {
        this.PhaseItemFact = Phaseitemfact;
    }
    /**
    
    */
    
    public java.math.BigDecimal getDistSum () {
        return this.DistSum;
    }
    
   public void setDistSum (java.math.BigDecimal Distsum) {
        this.DistSum = Distsum;
    }




}