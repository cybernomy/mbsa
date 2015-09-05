/*
 * DocumentLink.java
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
package com.mg.merp.document.model;


/**
 * @author hbm2java
 * @version $Id: DocumentLink.java,v 1.1 2005/06/10 06:53:12 safonov Exp $
 */
public class DocumentLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.document.model.DocHead Dochead;
 private com.mg.merp.document.model.DocHead Dochead_1;
 private com.mg.merp.core.model.SysClient SysClient;


    // Constructors

    /** default constructor */
    public DocumentLink() {
    }
    
    /** constructor with id */
    public DocumentLink(int Id) {
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
    
    public com.mg.merp.document.model.DocHead getDochead () {
        return this.Dochead;
    }
    
   public void setDochead (com.mg.merp.document.model.DocHead Dochead) {
        this.Dochead = Dochead;
    }
    /**
    
    */
    
    public com.mg.merp.document.model.DocHead getDochead_1 () {
        return this.Dochead_1;
    }
    
   public void setDochead_1 (com.mg.merp.document.model.DocHead Dochead_1) {
        this.Dochead_1 = Dochead_1;
    }
    /**
    
    */
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }




}