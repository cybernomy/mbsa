/*
 * BankRequis.java
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
 * @version $Id: BankRequis.java,v 1.1 2005/06/10 06:51:36 safonov Exp $
 */
public class BankRequis extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.reference.model.BankRequisId id;


    // Constructors

    /** default constructor */
    public BankRequis() {
    }
    
    /** constructor with id */
    public BankRequis(com.mg.merp.reference.model.BankRequisId id) {
        this.id = id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public com.mg.merp.reference.model.BankRequisId getId () {
        return this.id;
    }
    
   public void setId (com.mg.merp.reference.model.BankRequisId id) {
        this.id = id;
    }




}