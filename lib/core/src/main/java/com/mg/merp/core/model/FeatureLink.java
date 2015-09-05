/*
 * FeatureLink.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: FeatureLink.java,v 1.3 2007/01/18 16:11:32 safonov Exp $
 */
public class FeatureLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.core.model.Feature Feature;
 private com.mg.merp.core.model.SysClass SysClass;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.Integer RecId;
 private java.lang.String Val;
 private java.lang.Integer ValFolder;
 private java.lang.String ValText;


    // Constructors

    /** default constructor */
    public FeatureLink() {
    }
    
    /** constructor with id */
    public FeatureLink(int Id) {
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
    
    public com.mg.merp.core.model.Feature getFeature () {
        return this.Feature;
    }
    
   public void setFeature (com.mg.merp.core.model.Feature Feature) {
        this.Feature = Feature;
    }
    /**
    
    */
    
    public com.mg.merp.core.model.SysClass getSysClass () {
        return this.SysClass;
    }
    
   public void setSysClass (com.mg.merp.core.model.SysClass SysClass) {
        this.SysClass = SysClass;
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
    
    public java.lang.Integer getRecId () {
        return this.RecId;
    }
    
   public void setRecId (java.lang.Integer RecId) {
        this.RecId = RecId;
    }
    /**
    
    */
    
    public java.lang.String getVal () {
        return this.Val;
    }
    
   public void setVal (java.lang.String Val) {
        this.Val = Val;
    }
    /**
    
    */
    
    public java.lang.Integer getValFolder () {
        return this.ValFolder;
    }
    
   public void setValFolder (java.lang.Integer ValFolder) {
        this.ValFolder = ValFolder;
    }
    /**
    
    */
    
    public java.lang.String getValText () {
        return this.ValText;
    }
    
   public void setValText (java.lang.String ValText) {
        this.ValText = ValText;
    }




}