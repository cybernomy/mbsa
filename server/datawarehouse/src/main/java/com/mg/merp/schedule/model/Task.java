/*
 * Task.java
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
package com.mg.merp.schedule.model;



/**
 * @author hbm2java
 * @version $Id: Task.java,v 1.3 2006/10/12 11:56:24 safonov Exp $
 */
public class Task extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.baiengine.model.Repository Algorithm;
 private com.mg.merp.reference.model.Folder Folder;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String Code;
 private java.lang.String Name;
 private java.lang.String Description;


    // Constructors

    /** default constructor */
    public Task() {
    }
    
    /** constructor with id */
    public Task(java.lang.Integer Id) {
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
    
    public com.mg.merp.baiengine.model.Repository getAlgorithm () {
        return this.Algorithm;
    }
    
   public void setAlgorithm (com.mg.merp.baiengine.model.Repository AlgRepository) {
        this.Algorithm = AlgRepository;
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
    
    public java.lang.String getCode () {
        return this.Code;
    }
    
   public void setCode (java.lang.String Code) {
        this.Code = Code;
    }
    /**
    
    */
    
    public java.lang.String getName () {
        return this.Name;
    }
    
   public void setName (java.lang.String Name) {
        this.Name = Name;
    }
    /**
    
    */
    
    public java.lang.String getDescription () {
        return this.Description;
    }
    
   public void setDescription (java.lang.String Description) {
        this.Description = Description;
    }




}