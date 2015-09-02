/*
 * TimeBoardSpec.java
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
package com.mg.merp.table.model;



/**
 * @author hbm2java
 * @version $Id: TimeBoardSpec.java,v 1.2 2005/06/28 10:04:12 pashistova Exp $
 */
public class TimeBoardSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.table.model.TimeKind TimeKind;
 private com.mg.merp.table.model.TimeBoardPosition TimeBoardPosition;
 private com.mg.merp.core.model.SysClient SysClient;
 private java.math.BigDecimal HoursQuantity;
 private java.util.Date TimeBoardDate;


    // Constructors

    /** default constructor */
    public TimeBoardSpec() {
    }
    
    /** constructor with id */
    public TimeBoardSpec(java.lang.Integer Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    
    public java.lang.Integer getId () {
        return this.Id;
    }
    
   public void setId (java.lang.Integer Id) {
        this.Id = Id;
    }
    /**
    
    */
    
    public com.mg.merp.table.model.TimeKind getTimeKind () {
        return this.TimeKind;
    }
    
   public void setTimeKind (com.mg.merp.table.model.TimeKind TabTimeKind) {
        this.TimeKind = TabTimeKind;
    }
    /**
    
    */
    
    public com.mg.merp.table.model.TimeBoardPosition getTimeBoardPosition () {
        return this.TimeBoardPosition;
    }
    
   public void setTimeBoardPosition (com.mg.merp.table.model.TimeBoardPosition TabTimeBoardPosition) {
        this.TimeBoardPosition = TabTimeBoardPosition;
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
    
    public java.math.BigDecimal getHoursQuantity () {
        return this.HoursQuantity;
    }
    
   public void setHoursQuantity (java.math.BigDecimal HoursQuantity) {
        this.HoursQuantity = HoursQuantity;
    }
    /**
    
    */
    
    public java.util.Date getTimeBoardDate () {
        return this.TimeBoardDate;
    }
    
   public void setTimeBoardDate (java.util.Date TimeBoardDate) {
        this.TimeBoardDate = TimeBoardDate;
    }




}