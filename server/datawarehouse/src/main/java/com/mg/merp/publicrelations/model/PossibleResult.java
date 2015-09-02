/*
 * PossibleResult.java
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
 * @version $Id: PossibleResult.java,v 1.2 2005/06/28 10:04:07 pashistova Exp $
 */
public class PossibleResult extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String RName;
 private java.util.Set SetOfPrContactHist;


    // Constructors

    /** default constructor */
    public PossibleResult() {
    }
    
    /** constructor with id */
    public PossibleResult(int Id) {
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
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }
    /**
    
    */
    
    public java.lang.String getRName () {
        return this.RName;
    }
    
   public void setRName (java.lang.String Rname) {
        this.RName = Rname;
    }
    /**
    
    */
    
    public java.util.Set getSetOfPrContactHist () {
        return this.SetOfPrContactHist;
    }
    
   public void setSetOfPrContactHist (java.util.Set SetOfPrContactHist) {
        this.SetOfPrContactHist = SetOfPrContactHist;
    }




}