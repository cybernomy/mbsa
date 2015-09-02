/*
 * DataPump.java
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
package com.mg.merp.olap.model;



/**
 * @author hbm2java
 * @version $Id: DataPump.java,v 1.3 2006/10/12 11:47:31 safonov Exp $
 */
public class DataPump extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.baiengine.model.Repository ClearAlg;
 private com.mg.merp.baiengine.model.Repository PumpAlg;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.baiengine.model.Repository DeleteAlg;
 private com.mg.merp.baiengine.model.Repository CreateAlg;
 private java.lang.String PumpName;
 private java.lang.Short OlapDbType;
 private java.lang.String OlapDbName;
 private java.lang.String OlapDbUser;
 private java.lang.String OlapDbPassword;


    // Constructors

    /** default constructor */
    public DataPump() {
    }
    
    /** constructor with id */
    public DataPump(int Id) {
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
    
    public com.mg.merp.baiengine.model.Repository getClearAlg () {
        return this.ClearAlg;
    }
    
   public void setClearAlg (com.mg.merp.baiengine.model.Repository AlgRepository) {
        this.ClearAlg = AlgRepository;
    }
    /**
    
    */
    
    public com.mg.merp.baiengine.model.Repository getPumpAlg () {
        return this.PumpAlg;
    }
    
   public void setPumpAlg (com.mg.merp.baiengine.model.Repository AlgRepository_1) {
        this.PumpAlg = AlgRepository_1;
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
    
    public com.mg.merp.baiengine.model.Repository getDeleteAlg () {
        return this.DeleteAlg;
    }
    
   public void setDeleteAlg (com.mg.merp.baiengine.model.Repository AlgRepository_2) {
        this.DeleteAlg = AlgRepository_2;
    }
    /**
    
    */
    
    public com.mg.merp.baiengine.model.Repository getCreateAlg () {
        return this.CreateAlg;
    }
    
   public void setCreateAlg (com.mg.merp.baiengine.model.Repository AlgRepository_3) {
        this.CreateAlg = AlgRepository_3;
    }
    /**
    
    */
    
    public java.lang.String getPumpName () {
        return this.PumpName;
    }
    
   public void setPumpName (java.lang.String Pumpname) {
        this.PumpName = Pumpname;
    }
    /**
    
    */
    
    public java.lang.Short getOlapDbType () {
        return this.OlapDbType;
    }
    
   public void setOlapDbType (java.lang.Short OlapdbType) {
        this.OlapDbType = OlapdbType;
    }
    /**
    
    */
    
    public java.lang.String getOlapDbName () {
        return this.OlapDbName;
    }
    
   public void setOlapDbName (java.lang.String OlapdbName) {
        this.OlapDbName = OlapdbName;
    }
    /**
    
    */
    
    public java.lang.String getOlapDbUser () {
        return this.OlapDbUser;
    }
    
   public void setOlapDbUser (java.lang.String OlapdbUser) {
        this.OlapDbUser = OlapdbUser;
    }
    /**
    
    */
    
    public java.lang.String getOlapDbPassword () {
        return this.OlapDbPassword;
    }
    
   public void setOlapDbPassword (java.lang.String OlapdbPassword) {
        this.OlapDbPassword = OlapdbPassword;
    }




}