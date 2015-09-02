/*
 * DensityHead.java
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
package com.mg.merp.fuel.model;



/**
 * @author hbm2java
 * @version $Id: DensityHead.java,v 1.2 2005/07/14 13:40:31 pashistova Exp $
 */
public class DensityHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.reference.model.Folder Folder;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.reference.model.Contractor Contractor;
 private java.util.Date DateTime;
 private java.lang.String Code;
 private java.lang.String DhName;
 private java.util.Set SetOfFuelDensitySpec;


    // Constructors

    /** default constructor */
    public DensityHead() {
    }
    
    /** constructor with id */
    public DensityHead(int Id) {
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
    
    public com.mg.merp.reference.model.Folder getFolder () {
        return this.Folder;
    }
    
   public void setFolder (com.mg.merp.reference.model.Folder Folder) {
        this.Folder = Folder;
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
    
    public java.util.Date getDateTime () {
        return this.DateTime;
    }
    
   public void setDateTime (java.util.Date DateTime) {
        this.DateTime = DateTime;
    }
    /**
    
    */
    
    public java.lang.String getCode () {
        return this.Code;
    }
    
   public void setCode (java.lang.String Code) {
        this.Code = Code;
    }
    /**
    
    */
    
    public java.lang.String getDhName () {
        return this.DhName;
    }
    
   public void setDhName (java.lang.String Dhname) {
        this.DhName = Dhname;
    }
    /**
    
    */
    
    public java.util.Set getSetOfFuelDensitySpec () {
        return this.SetOfFuelDensitySpec;
    }
    
   public void setSetOfFuelDensitySpec (java.util.Set SetOfFuelDensitySpec) {
        this.SetOfFuelDensitySpec = SetOfFuelDensitySpec;
    }




}