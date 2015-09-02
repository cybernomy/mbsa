/*
 * TaxSumBufId.java
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
 * @version $Id: TaxSumBufId.java,v 1.1 2005/06/10 06:51:41 safonov Exp $
 */
public class TaxSumBufId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.Integer SpecificationId;
 private java.lang.Integer TaxId;
 private java.math.BigDecimal Summ;


    // Constructors

    /** default constructor */
    public TaxSumBufId() {
    }
    
   
    
    

    // Property accessors
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
    
    public java.lang.Integer getSpecificationId () {
        return this.SpecificationId;
    }
    
   public void setSpecificationId (java.lang.Integer SpecificationId) {
        this.SpecificationId = SpecificationId;
    }
    /**
    
    */
    
    public java.lang.Integer getTaxId () {
        return this.TaxId;
    }
    
   public void setTaxId (java.lang.Integer TaxId) {
        this.TaxId = TaxId;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSumm () {
        return this.Summ;
    }
    
   public void setSumm (java.math.BigDecimal Summ) {
        this.Summ = Summ;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TaxSumBufId) ) return false;
		 TaxSumBufId castOther = ( TaxSumBufId ) other; 
         
		 return (this.getSysClient()==castOther.getSysClient()) || (this.getSysClient()==null ? false : (castOther.getSysClient()==null ? false : this.getSysClient().equals(castOther.getSysClient())))
 && (this.getSpecificationId()==castOther.getSpecificationId()) || (this.getSpecificationId()==null ? false : (castOther.getSpecificationId()==null ? false : this.getSpecificationId().equals(castOther.getSpecificationId())))
 && (this.getTaxId()==castOther.getTaxId()) || (this.getTaxId()==null ? false : (castOther.getTaxId()==null ? false : this.getTaxId().equals(castOther.getTaxId())))
 && (this.getSumm()==castOther.getSumm()) || (this.getSumm()==null ? false : (castOther.getSumm()==null ? false : this.getSumm().equals(castOther.getSumm())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getSysClient().hashCode();
         result = 37 * result + this.getSpecificationId().hashCode();
         result = 37 * result + this.getTaxId().hashCode();
         result = 37 * result + this.getSumm().hashCode();
         return result;
   }   



}