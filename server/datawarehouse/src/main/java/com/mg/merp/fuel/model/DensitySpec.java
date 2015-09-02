/*
 * DensitySpec.java
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
package com.mg.merp.fuel.model;



/**
 * @author hbm2java
 * @version $Id: DensitySpec.java,v 1.2 2005/07/14 13:40:31 pashistova Exp $
 */
public class DensitySpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private int Id;
 private com.mg.merp.reference.model.Contractor Operator;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.reference.model.Contractor Tank;
 private com.mg.merp.fuel.model.DensityHead Head;
 private com.mg.merp.reference.model.Catalog Fuel;
 private java.util.Date DateTime;
 private java.lang.Double Density;
 private java.lang.Double Temperature;


    // Constructors

    /** default constructor */
    public DensitySpec() {
    }
    
    /** constructor with id */
    public DensitySpec(int Id) {
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
    
    public com.mg.merp.reference.model.Contractor getOperator () {
        return this.Operator;
    }
    
   public void setOperator (com.mg.merp.reference.model.Contractor Contractor) {
        this.Operator = Contractor;
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
    
    public com.mg.merp.reference.model.Contractor getTank () {
        return this.Tank;
    }
    
   public void setTank (com.mg.merp.reference.model.Contractor Contractor_1) {
        this.Tank = Contractor_1;
    }
    /**
    
    */
    
    public com.mg.merp.fuel.model.DensityHead getHead () {
        return this.Head;
    }
    
   public void setHead (com.mg.merp.fuel.model.DensityHead FuelDensityHead) {
        this.Head = FuelDensityHead;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Catalog getFuel () {
        return this.Fuel;
    }
    
   public void setFuel (com.mg.merp.reference.model.Catalog Catalog) {
        this.Fuel = Catalog;
    }
    /**
    
    */
    
    public java.util.Date getDateTime () {
        return this.DateTime;
    }
    
   public void setDateTime (java.util.Date DateTime) {
        this.DateTime = DateTime;
    }
    /**
    
    */
    
    public java.lang.Double getDensity () {
        return this.Density;
    }
    
   public void setDensity (java.lang.Double Density) {
        this.Density = Density;
    }
    /**
    
    */
    
    public java.lang.Double getTemperature () {
        return this.Temperature;
    }
    
   public void setTemperature (java.lang.Double Temperature) {
        this.Temperature = Temperature;
    }




}