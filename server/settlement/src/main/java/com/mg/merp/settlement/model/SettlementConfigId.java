/*
 * SettlementConfigId.java
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
package com.mg.merp.settlement.model;



/**
 * @author hbm2java
 * @version $Id: SettlementConfigId.java,v 1.2 2005/06/21 13:12:48 pashistova Exp $
 */
public class SettlementConfigId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.reference.model.Currency Currency;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.reference.model.Currency NarCurrency;
 private com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType;
 private com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority;
 private java.lang.Integer CurrencyPrec;


    // Constructors

    /** default constructor */
    public SettlementConfigId() {
    }
    
   
    
    

    // Property accessors
    /**
    
    */
    
    public com.mg.merp.reference.model.Currency getCurrency () {
        return this.Currency;
    }
    
   public void setCurrency (com.mg.merp.reference.model.Currency Currency) {
        this.Currency = Currency;
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
    
    public com.mg.merp.reference.model.Currency getNarCurrency () {
        return this.NarCurrency;
    }
    
   public void setNarCurrency (com.mg.merp.reference.model.Currency Currency_1) {
        this.NarCurrency = Currency_1;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.CurrencyRateType getRefCurrencyRateType () {
        return this.RefCurrencyRateType;
    }
    
   public void setRefCurrencyRateType (com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
        this.RefCurrencyRateType = RefCurrencyRateType;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.CurrencyRateAuthority getRefCurrencyRateAuthority () {
        return this.RefCurrencyRateAuthority;
    }
    
   public void setRefCurrencyRateAuthority (com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
        this.RefCurrencyRateAuthority = RefCurrencyRateAuthority;
    }
    /**
    
    */
    
    public java.lang.Integer getCurrencyPrec () {
        return this.CurrencyPrec;
    }
    
   public void setCurrencyPrec (java.lang.Integer CurrencyPrec) {
        this.CurrencyPrec = CurrencyPrec;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SettlementConfigId) ) return false;
		 SettlementConfigId castOther = ( SettlementConfigId ) other; 
         
		 return (this.getCurrency()==castOther.getCurrency()) || (this.getCurrency()==null ? false : (castOther.getCurrency()==null ? false : this.getCurrency().equals(castOther.getCurrency())))
 && (this.getSysClient()==castOther.getSysClient()) || (this.getSysClient()==null ? false : (castOther.getSysClient()==null ? false : this.getSysClient().equals(castOther.getSysClient())))
 && (this.getNarCurrency()==castOther.getNarCurrency()) || (this.getNarCurrency()==null ? false : (castOther.getNarCurrency()==null ? false : this.getNarCurrency().equals(castOther.getNarCurrency())))
 && (this.getRefCurrencyRateType()==castOther.getRefCurrencyRateType()) || (this.getRefCurrencyRateType()==null ? false : (castOther.getRefCurrencyRateType()==null ? false : this.getRefCurrencyRateType().equals(castOther.getRefCurrencyRateType())))
 && (this.getRefCurrencyRateAuthority()==castOther.getRefCurrencyRateAuthority()) || (this.getRefCurrencyRateAuthority()==null ? false : (castOther.getRefCurrencyRateAuthority()==null ? false : this.getRefCurrencyRateAuthority().equals(castOther.getRefCurrencyRateAuthority())))
 && (this.getCurrencyPrec()==castOther.getCurrencyPrec()) || (this.getCurrencyPrec()==null ? false : (castOther.getCurrencyPrec()==null ? false : this.getCurrencyPrec().equals(castOther.getCurrencyPrec())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCurrency().hashCode();
         result = 37 * result + this.getSysClient().hashCode();
         result = 37 * result + this.getNarCurrency().hashCode();
         result = 37 * result + this.getRefCurrencyRateType().hashCode();
         result = 37 * result + this.getRefCurrencyRateAuthority().hashCode();
         result = 37 * result + this.getCurrencyPrec().hashCode();
         return result;
   }   



}