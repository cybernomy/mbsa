/*
 * Tax.java
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

import java.util.Set;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Tax.java,v 1.5 2006/11/02 16:20:44 safonov Exp $
 */
@DataItemName("Reference.Tax")
public class Tax extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {
    // Fields    
	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String UpCode;

	private java.lang.String Code;

	private java.lang.String TName;

	private java.util.Date ActiveDate;

	private TaxType TaxType;

	private TaxForm TaxForm;

	private boolean Included;

	private java.math.BigDecimal DirectRate;

	private java.math.BigDecimal InverseRate;

	private java.math.BigDecimal Summ;

	private java.util.Date DeactivateDate;
 
	private Set<com.mg.merp.reference.model.TaxLink> groupLinks;
	
	private Set<com.mg.merp.reference.model.CalcTaxesLink> calculationLinks;
	
	/** default constructor */
    public Tax() {
    }
    
    /** constructor with id */
    public Tax(java.lang.Integer Id) {
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
    
    public java.lang.String getUpCode () {
        return this.UpCode;
    }
    
    public void setUpCode (java.lang.String UpCode) {
        this.UpCode = UpCode;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.Code")
    public java.lang.String getCode () {
        return this.Code;
    }
    
    public void setCode (java.lang.String Code) {
        this.Code = Code;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.Name")
    public java.lang.String getTName () {
        return this.TName;
    }
    
    public void setTName (java.lang.String TName) {
        this.TName = TName;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.ActiveDate")
    public java.util.Date getActiveDate () {
        return this.ActiveDate;
    }
    
    public void setActiveDate (java.util.Date ActiveDate) {
        this.ActiveDate = ActiveDate;
    }
   
    /**
    
    */
    public TaxType getTaxType () {
        return this.TaxType;
    }
    
    public void setTaxType (TaxType TaxType) {
        this.TaxType = TaxType;
    }
   
    /**
    
    */
    public TaxForm getTaxForm () {
        return this.TaxForm;
    }
    
    public void setTaxForm (TaxForm TaxForm) {
        this.TaxForm = TaxForm;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.Included")
    public boolean getIncluded () {
        return this.Included;
    }
    
    public void setIncluded (boolean Included) {
        this.Included = Included;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.DirectRate")
    public java.math.BigDecimal getDirectRate () {
        return this.DirectRate;
    }
    
    public void setDirectRate (java.math.BigDecimal DirectRate) {
        this.DirectRate = DirectRate;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.InverseRate")
    public java.math.BigDecimal getInverseRate () {
        return this.InverseRate;
    }
    
    public void setInverseRate (java.math.BigDecimal InverseRate) {
        this.InverseRate = InverseRate;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.Sum")
    public java.math.BigDecimal getSumm () {
        return this.Summ;
    }
    
    public void setSumm (java.math.BigDecimal Summ) {
        this.Summ = Summ;
    }
   
    /**
    
    */
    @DataItemName("Reference.Tax.DeactivateDate")
    public java.util.Date getDeactivateDate () {
        return this.DeactivateDate;
    }
    
    public void setDeactivateDate (java.util.Date DeactivateDate) {
        this.DeactivateDate = DeactivateDate;
    }

	/**
	 * @return Returns the calculationLinks.
	 */
	public Set<com.mg.merp.reference.model.CalcTaxesLink> getCalculationLinks() {
		return calculationLinks;
	}

	/**
	 * @param calculationLinks The calculationLinks to set.
	 */
	public void setCalculationLinks(
			Set<com.mg.merp.reference.model.CalcTaxesLink> calculationLinks) {
		this.calculationLinks = calculationLinks;
	}

	/**
	 * @return Returns the groupLinks.
	 */
	public Set<com.mg.merp.reference.model.TaxLink> getGroupLinks() {
		return groupLinks;
	}

	/**
	 * @param groupLinks The groupLinks to set.
	 */
	public void setGroupLinks(Set<com.mg.merp.reference.model.TaxLink> groupLinks) {
		this.groupLinks = groupLinks;
	}

}