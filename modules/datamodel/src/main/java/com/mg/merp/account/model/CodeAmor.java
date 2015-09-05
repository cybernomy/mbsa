/*
 * CodeAmor.java
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
 * @version $Id: CodeAmor.java,v 1.1 2005/06/10 06:52:48 safonov Exp $
 */
public class CodeAmor extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.String UpCode;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String Code;
 private java.lang.String CaName;


    // Constructors

    /** default constructor */
    public CodeAmor() {
    }
    
    /** constructor with id */
    public CodeAmor(java.lang.String UpCode) {
        this.UpCode = UpCode;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public java.lang.String getUpCode () {
        return this.UpCode;
    }
    
   public void setUpCode (java.lang.String UpCode) {
        this.UpCode = UpCode;
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
    
    public java.lang.String getCaName () {
        return this.CaName;
    }
    
   public void setCaName (java.lang.String CaName) {
        this.CaName = CaName;
    }




}