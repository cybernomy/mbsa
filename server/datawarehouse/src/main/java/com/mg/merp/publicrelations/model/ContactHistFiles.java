/*
 * ContactHistFiles.java
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
 * @version $Id: ContactHistFiles.java,v 1.2 2005/06/28 10:04:07 pashistova Exp $
 */
public class ContactHistFiles extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.publicrelations.model.ContactHist ContactHist;
 private java.io.Serializable FileDoc;
 private java.lang.String FileName;
 private java.lang.String FDescription;
 private java.util.Date FileDate;


    // Constructors

    /** default constructor */
    public ContactHistFiles() {
    }
    
    /** constructor with id */
    public ContactHistFiles(int Id) {
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
    
    public com.mg.merp.publicrelations.model.ContactHist getContactHist () {
        return this.ContactHist;
    }
    
   public void setContactHist (com.mg.merp.publicrelations.model.ContactHist PrContactHist) {
        this.ContactHist = PrContactHist;
    }
    /**
    
    */
    
    public java.io.Serializable getFileDoc () {
        return this.FileDoc;
    }
    
   public void setFileDoc (java.io.Serializable Filedoc) {
        this.FileDoc = Filedoc;
    }
    /**
    
    */
    
    public java.lang.String getFileName () {
        return this.FileName;
    }
    
   public void setFileName (java.lang.String Filename) {
        this.FileName = Filename;
    }
    /**
    
    */
    
    public java.lang.String getFDescription () {
        return this.FDescription;
    }
    
   public void setFDescription (java.lang.String Fdescription) {
        this.FDescription = Fdescription;
    }
    /**
    
    */
    
    public java.util.Date getFileDate () {
        return this.FileDate;
    }
    
   public void setFileDate (java.util.Date Filedate) {
        this.FileDate = Filedate;
    }




}