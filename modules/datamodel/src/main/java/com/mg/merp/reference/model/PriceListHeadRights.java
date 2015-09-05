/*
 * PriceListHeadRights.java
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
package com.mg.merp.reference.model;



/**
 * @author hbm2java
 * @version $Id: PriceListHeadRights.java,v 1.1 2005/06/10 06:51:37 safonov Exp $
 */
public class PriceListHeadRights extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.reference.model.PriceListHead PriceListHead;
 private com.mg.merp.security.model.Groups Groups;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.Integer Rights;


    // Constructors

    /** default constructor */
    public PriceListHeadRights() {
    }
    
    /** constructor with id */
    public PriceListHeadRights(java.lang.Integer Id) {
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
    
    public com.mg.merp.reference.model.PriceListHead getPriceListHead () {
        return this.PriceListHead;
    }
    
   public void setPriceListHead (com.mg.merp.reference.model.PriceListHead PriceListHead) {
        this.PriceListHead = PriceListHead;
    }
    /**
    
    */
    
    public com.mg.merp.security.model.Groups getGroups () {
        return this.Groups;
    }
    
   public void setGroups (com.mg.merp.security.model.Groups Groups) {
        this.Groups = Groups;
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
    
    public java.lang.Integer getRights () {
        return this.Rights;
    }
    
   public void setRights (java.lang.Integer Rights) {
        this.Rights = Rights;
    }




}