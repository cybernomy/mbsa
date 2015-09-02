/*
 * ContactLink.java
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
package com.mg.merp.crm.model;


/**
 * @author hbm2java
 * @version $Id: ContactLink.java,v 1.1 2005/06/10 06:52:23 safonov Exp $
 */
public class ContactLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.crm.model.ContactLinkId id;


    // Constructors

    /** default constructor */
    public ContactLink() {
    }
    
    /** constructor with id */
    public ContactLink(com.mg.merp.crm.model.ContactLinkId id) {
        this.id = id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public com.mg.merp.crm.model.ContactLinkId getId () {
        return this.id;
    }
    
   public void setId (com.mg.merp.crm.model.ContactLinkId id) {
        this.id = id;
    }




}