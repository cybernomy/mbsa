/*
 * DocGroupLink.java
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
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: DocGroupLink.java,v 1.4 2006/08/29 12:50:01 leonova Exp $
 */
public class DocGroupLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.paymentalloc.model.DocGroup DocGroup;
 private com.mg.merp.document.model.DocType DocType;


    // Constructors

    /** default constructor */
    public DocGroupLink() {
    }
    
    /** constructor with id */
    public DocGroupLink(java.lang.Integer Id) {
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
    
    public com.mg.merp.paymentalloc.model.DocGroup getDocGroup () {
        return this.DocGroup;
    }
    
   public void setDocGroup (com.mg.merp.paymentalloc.model.DocGroup PmaDocgroup) {
        this.DocGroup = PmaDocgroup;
    }
    /**
    
    */
    @DataItemName("PaymentAlloc.Payment.DocType")
    public com.mg.merp.document.model.DocType getDocType () {
        return this.DocType;
    }
    
   public void setDocType (com.mg.merp.document.model.DocType Typedoc) {
        this.DocType = Typedoc;
    }




}