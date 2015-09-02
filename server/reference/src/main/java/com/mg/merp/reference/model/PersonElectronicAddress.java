/*
 * PersonElectronicAddress.java
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

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: PersonElectronicAddress.java,v 1.3 2006/09/01 13:07:17 leonova Exp $
 */
public class PersonElectronicAddress extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.reference.model.NaturalPerson NaturalPerson;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.reference.model.ElectronicAddressKind EaddressKind;
 private ProtokolKind Protocol;
 private java.lang.String Address;
 private boolean IsActive;


    // Constructors

    /** default constructor */
    public PersonElectronicAddress() {
    }
    
    /** constructor with id */
    public PersonElectronicAddress(java.lang.Integer Id) {
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
    
    public com.mg.merp.reference.model.NaturalPerson getNaturalPerson () {
        return this.NaturalPerson;
    }
    
   public void setNaturalPerson (com.mg.merp.reference.model.NaturalPerson NaturalPerson) {
        this.NaturalPerson = NaturalPerson;
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
    
    public com.mg.merp.reference.model.ElectronicAddressKind getEaddressKind () {
        return this.EaddressKind;
    }
    
   public void setEaddressKind (com.mg.merp.reference.model.ElectronicAddressKind EaddressKind) {
        this.EaddressKind = EaddressKind;
    }
    /**
    
    */
    
    public ProtokolKind getProtocol () {
        return this.Protocol;
    }
    
   public void setProtocol (ProtokolKind Protocol) {
        this.Protocol = Protocol;
    }
    /**
    
    */
	@DataItemName("Reference.Partner.Email.Address")    
    public java.lang.String getAddress () {
        return this.Address;
    }
    
   public void setAddress (java.lang.String Address) {
        this.Address = Address;
    }
    /**
    
    */
	@DataItemName("Reference.Partner.Email.IsActive")    
    public boolean getIsActive () {
        return this.IsActive;
    }
    
   public void setIsActive (boolean IsActive) {
        this.IsActive = IsActive;
    }




}