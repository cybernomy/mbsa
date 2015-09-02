/*
 * Table.java
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



/**
 * @author hbm2java
 * @version $Id: Table.java,v 1.1 2005/06/10 06:53:17 safonov Exp $
 */
public class Table extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer ClassId;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String TableName;


    // Constructors

    /** default constructor */
    public Table() {
    }
    
    /** constructor with id */
    public Table(java.lang.Integer ClassId) {
        this.ClassId = ClassId;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public java.lang.Integer getClassId () {
        return this.ClassId;
    }
    
   public void setClassId (java.lang.Integer ClassId) {
        this.ClassId = ClassId;
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
    
    public java.lang.String getTableName () {
        return this.TableName;
    }
    
   public void setTableName (java.lang.String TableName) {
        this.TableName = TableName;
    }




}