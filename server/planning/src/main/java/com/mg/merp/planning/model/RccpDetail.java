/*
 * RccpDetail.java
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
package com.mg.merp.planning.model;



/**
 * @author hbm2java
 * @version $Id: RccpDetail.java,v 1.2 2005/07/29 12:25:40 pashistova Exp $
 */
public class RccpDetail extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.planning.model.RccpHeader RccpHeader;
 private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;
 private com.mg.merp.reference.model.Measure Measure;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.planning.model.GenericItem GenericItem;
 private java.lang.Short BucketOffset;
 private java.lang.Short MpsBucketOffset;
 private java.math.BigDecimal LoadValue;
 private java.math.BigDecimal LoadAdjustmentValue;
 private java.math.BigDecimal LoadProductionValue;
 private java.math.BigDecimal LoadDemandValue;
 private java.util.Date BucketStartDate;
 private java.util.Date BucketEndDate;

    // Constructors

    /** default constructor */
    public RccpDetail() {
    }
    
    /** constructor with id */
    public RccpDetail(java.lang.Integer Id) {
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
    
    public com.mg.merp.planning.model.RccpHeader getRccpHeader () {
        return this.RccpHeader;
    }
    
   public void setRccpHeader (com.mg.merp.planning.model.RccpHeader RccpHeader) {
        this.RccpHeader = RccpHeader;
    }
    /**
    
    */
    
    public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup () {
        return this.ResourceGroup;
    }
    
   public void setResourceGroup (com.mg.merp.mfreference.model.ResourceGroup ResourceGroup) {
        this.ResourceGroup = ResourceGroup;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Measure getMeasure () {
        return this.Measure;
    }
    
   public void setMeasure (com.mg.merp.reference.model.Measure Measure) {
        this.Measure = Measure;
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
    
    public com.mg.merp.planning.model.GenericItem getGenericItem () {
        return this.GenericItem;
    }
    
   public void setGenericItem (com.mg.merp.planning.model.GenericItem GenericItem) {
        this.GenericItem = GenericItem;
    }
    /**
    
    */
    
    public java.lang.Short getBucketOffset () {
        return this.BucketOffset;
    }
    
   public void setBucketOffset (java.lang.Short BucketOffset) {
        this.BucketOffset = BucketOffset;
    }
    /**
    
    */
    
    public java.lang.Short getMpsBucketOffset () {
        return this.MpsBucketOffset;
    }
    
   public void setMpsBucketOffset (java.lang.Short MpsBucketOffset) {
        this.MpsBucketOffset = MpsBucketOffset;
    }
    /**
    
    */
    
    public java.math.BigDecimal getLoadValue () {
        return this.LoadValue;
    }
    
   public void setLoadValue (java.math.BigDecimal LoadValue) {
        this.LoadValue = LoadValue;
    }
    /**
    
    */
    
    public java.math.BigDecimal getLoadAdjustmentValue () {
        return this.LoadAdjustmentValue;
    }
    
   public void setLoadAdjustmentValue (java.math.BigDecimal LoadAdjustmentValue) {
        this.LoadAdjustmentValue = LoadAdjustmentValue;
    }
    /**
    
    */
    
    public java.math.BigDecimal getLoadProductionValue () {
        return this.LoadProductionValue;
    }
    
   public void setLoadProductionValue (java.math.BigDecimal LoadProductionValue) {
        this.LoadProductionValue = LoadProductionValue;
    }
    /**
    
    */
    
    public java.math.BigDecimal getLoadDemandValue () {
        return this.LoadDemandValue;
    }
    
   public void setLoadDemandValue (java.math.BigDecimal LoadDemandValue) {
        this.LoadDemandValue = LoadDemandValue;
    }
   /**
   
    */
    
   public java.util.Date getBucketStartDate () {
        return this.BucketStartDate;
    }
    
   public void setBucketStartDate (java.util.Date BucketStartDate) {
        this.BucketStartDate = BucketStartDate;
   }
   /**
   
    */
    
   public java.util.Date getBucketEndDate () {
        return this.BucketEndDate;
    }
    
   public void setBucketEndDate (java.util.Date BucketEndDate) {
        this.BucketEndDate = BucketEndDate;
   }

}