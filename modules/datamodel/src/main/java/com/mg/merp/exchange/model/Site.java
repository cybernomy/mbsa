/*
 * Site.java
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
package com.mg.merp.exchange.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: Site.java,v 1.3 2006/06/20 04:42:41 leonova Exp $
 */
public class Site extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String Code;
 private java.lang.String Name;
 private java.lang.String PathIn;
 private java.lang.String PathOut;
 private java.lang.Integer PacketIn;
 private java.util.Date PacketInTime;
 private java.lang.Integer PacketOut;
 private java.util.Date PacketOutTime;
 private boolean RequestDone;
 private java.lang.String ExportCmd;
 private java.lang.String ImportCmd;
 private java.util.Set SetOfExchExport;
 private java.util.Set SetOfExchImport;


    // Constructors

    /** default constructor */
    public Site() {
    }
    
    /** constructor with id */
    public Site(java.lang.Integer Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    @DataItemName("ID")   
    public java.lang.Integer getId () {
        return this.Id;
    }
    
   public void setId (java.lang.Integer Id) {
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
    @DataItemName("Exchange.Site.Code")
    public java.lang.String getCode () {
        return this.Code;
    }
    
   public void setCode (java.lang.String Code) {
        this.Code = Code;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.Name")
    public java.lang.String getName () {
        return this.Name;
    }
    
   public void setName (java.lang.String Name) {
        this.Name = Name;
    }
    /**
    
    */
    @DataItemName("Exchange.Site.PathIn")
    public java.lang.String getPathIn () {
        return this.PathIn;
    }
    
   public void setPathIn (java.lang.String PathIn) {
        this.PathIn = PathIn;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.PathOut")    
    public java.lang.String getPathOut () {
        return this.PathOut;
    }
    
   public void setPathOut (java.lang.String PathOut) {
        this.PathOut = PathOut;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.PacketIn")    
    public java.lang.Integer getPacketIn () {
        return this.PacketIn;
    }
    
   public void setPacketIn (java.lang.Integer PacketIn) {
        this.PacketIn = PacketIn;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.PacketInTime")  
    public java.util.Date getPacketInTime () {
        return this.PacketInTime;
    }
    
   public void setPacketInTime (java.util.Date PacketInTime) {
        this.PacketInTime = PacketInTime;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.PacketOut")  
    public java.lang.Integer getPacketOut () {
        return this.PacketOut;
    }
    
   public void setPacketOut (java.lang.Integer PacketOut) {
        this.PacketOut = PacketOut;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.PacketOutTime")  
    public java.util.Date getPacketOutTime () {
        return this.PacketOutTime;
    }
    
   public void setPacketOutTime (java.util.Date PacketOutTime) {
        this.PacketOutTime = PacketOutTime;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.RequestDone")  
    public boolean getRequestDone () {
        return this.RequestDone;
    }
    
   public void setRequestDone (boolean RequestDone) {
        this.RequestDone = RequestDone;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.ExportCmd")  
    public java.lang.String getExportCmd () {
        return this.ExportCmd;
    }
    
   public void setExportCmd (java.lang.String ExportCmd) {
        this.ExportCmd = ExportCmd;
    }
    /**
    
    */
   @DataItemName("Exchange.Site.ImportCmd")  
    public java.lang.String getImportCmd () {
        return this.ImportCmd;
    }
    
   public void setImportCmd (java.lang.String ImportCmd) {
        this.ImportCmd = ImportCmd;
    }
    /**
    
    */
   
    public java.util.Set getSetOfExchExport () {
        return this.SetOfExchExport;
    }
    
   public void setSetOfExchExport (java.util.Set SetOfExchExport) {
        this.SetOfExchExport = SetOfExchExport;
    }
    /**
    
    */
    
    public java.util.Set getSetOfExchImport () {
        return this.SetOfExchImport;
    }
    
   public void setSetOfExchImport (java.util.Set SetOfExchImport) {
        this.SetOfExchImport = SetOfExchImport;
    }




}