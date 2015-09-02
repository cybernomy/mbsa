/*
 * WarehouseSecurity.java
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
package com.mg.merp.warehouse.model;




/**
 * @author hbm2java
 * @version $Id: WarehouseSecurity.java,v 1.1 2005/06/10 06:51:25 safonov Exp $
 */
public class WarehouseSecurity extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.security.model.Groups SecGroups;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.Short DisableSpecPrice;
 private java.lang.Short DisableDiscount;
 private java.lang.Short DisableExceedQuant;


    // Constructors

    /** default constructor */
    public WarehouseSecurity() {
    }
    
    /** constructor with id */
    public WarehouseSecurity(int Id) {
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
    
    public com.mg.merp.security.model.Groups getSecGroups () {
        return this.SecGroups;
    }
    
   public void setSecGroups (com.mg.merp.security.model.Groups SecGroups) {
        this.SecGroups = SecGroups;
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
    
    public java.lang.Short getDisableSpecPrice () {
        return this.DisableSpecPrice;
    }
    
   public void setDisableSpecPrice (java.lang.Short DisableSpecPrice) {
        this.DisableSpecPrice = DisableSpecPrice;
    }
    /**
    
    */
    
    public java.lang.Short getDisableDiscount () {
        return this.DisableDiscount;
    }
    
   public void setDisableDiscount (java.lang.Short DisableDiscount) {
        this.DisableDiscount = DisableDiscount;
    }
    /**
    
    */
    
    public java.lang.Short getDisableExceedQuant () {
        return this.DisableExceedQuant;
    }
    
   public void setDisableExceedQuant (java.lang.Short DisableExceedQuant) {
        this.DisableExceedQuant = DisableExceedQuant;
    }




}