/*
 * MrpTotalsByDate.java
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
 * @version $Id: MrpTotalsByDate.java,v 1.1 2005/06/10 06:52:14 safonov Exp $
 */
public class MrpTotalsByDate extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.reference.model.Catalog Catalog;
 private com.mg.merp.reference.model.Contractor Contractor;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;
 private java.util.Date RequiredDate;
 private java.math.BigDecimal SalesOrderQty;
 private java.math.BigDecimal SalesForecastQty;
 private java.math.BigDecimal SalesQty;
 private java.math.BigDecimal ProductionQty;
 private java.math.BigDecimal PlannedProductionQty;
 private java.math.BigDecimal ProductionRequirements;
 private java.math.BigDecimal PlannedProductionRequirements;
 private java.math.BigDecimal SuggestedPurchases;
 private java.math.BigDecimal QtyOnHand;
 private java.math.BigDecimal QtyAvailable;
 private java.math.BigDecimal SuggestedTransfers;
 private java.math.BigDecimal TotalOutputs;
 private java.math.BigDecimal PurchaseQtyBefore;
 private java.math.BigDecimal TotalInputsAfter;
 private java.math.BigDecimal TransferQtyAfter;
 private java.math.BigDecimal TotalInputsBefore;
 private java.math.BigDecimal TransferQtyBefore;
 private java.math.BigDecimal PurchaseOrderQtyAfter;
 private java.math.BigDecimal FirmPlanTransfInAfter;
 private java.math.BigDecimal PurchaseForecastQtyAfter;
 private java.math.BigDecimal FirmPlanTransfInBefore;
 private java.math.BigDecimal ExpiryQty;
 private java.math.BigDecimal PurchaseOrderQtyBefore;
 private java.math.BigDecimal PurchaseForecastQtyBefore;
 private java.math.BigDecimal FirmPlannedPurchasesBefore;
 private java.math.BigDecimal FirmPlanTransfOutAfter;
 private java.math.BigDecimal FirmPlanTransfOutBefore;
 private java.math.BigDecimal PurchaseQtyAfter;
 private java.math.BigDecimal FirmPlannedPurchasesAfter;


    // Constructors

    /** default constructor */
    public MrpTotalsByDate() {
    }
    
    /** constructor with id */
    public MrpTotalsByDate(java.lang.Integer Id) {
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
    
    public com.mg.merp.reference.model.Catalog getCatalog () {
        return this.Catalog;
    }
    
   public void setCatalog (com.mg.merp.reference.model.Catalog Catalog) {
        this.Catalog = Catalog;
    }
    /**
    
    */
    
    public com.mg.merp.reference.model.Contractor getContractor () {
        return this.Contractor;
    }
    
   public void setContractor (com.mg.merp.reference.model.Contractor Contractor) {
        this.Contractor = Contractor;
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
    
    public com.mg.merp.planning.model.MrpVersionControl getMrpVersionControl () {
        return this.MrpVersionControl;
    }
    
   public void setMrpVersionControl (com.mg.merp.planning.model.MrpVersionControl MrpVersionControl) {
        this.MrpVersionControl = MrpVersionControl;
    }
    /**
    
    */
    
    public java.util.Date getRequiredDate () {
        return this.RequiredDate;
    }
    
   public void setRequiredDate (java.util.Date RequiredDate) {
        this.RequiredDate = RequiredDate;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSalesOrderQty () {
        return this.SalesOrderQty;
    }
    
   public void setSalesOrderQty (java.math.BigDecimal SalesOrderQty) {
        this.SalesOrderQty = SalesOrderQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSalesForecastQty () {
        return this.SalesForecastQty;
    }
    
   public void setSalesForecastQty (java.math.BigDecimal SalesForecastQty) {
        this.SalesForecastQty = SalesForecastQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSalesQty () {
        return this.SalesQty;
    }
    
   public void setSalesQty (java.math.BigDecimal SalesQty) {
        this.SalesQty = SalesQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getProductionQty () {
        return this.ProductionQty;
    }
    
   public void setProductionQty (java.math.BigDecimal ProductionQty) {
        this.ProductionQty = ProductionQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPlannedProductionQty () {
        return this.PlannedProductionQty;
    }
    
   public void setPlannedProductionQty (java.math.BigDecimal PlannedProductionQty) {
        this.PlannedProductionQty = PlannedProductionQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getProductionRequirements () {
        return this.ProductionRequirements;
    }
    
   public void setProductionRequirements (java.math.BigDecimal ProductionRequirements) {
        this.ProductionRequirements = ProductionRequirements;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPlannedProductionRequirements () {
        return this.PlannedProductionRequirements;
    }
    
   public void setPlannedProductionRequirements (java.math.BigDecimal PlannedProductionRequirements) {
        this.PlannedProductionRequirements = PlannedProductionRequirements;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSuggestedPurchases () {
        return this.SuggestedPurchases;
    }
    
   public void setSuggestedPurchases (java.math.BigDecimal SuggestedPurchases) {
        this.SuggestedPurchases = SuggestedPurchases;
    }
    /**
    
    */
    
    public java.math.BigDecimal getQtyOnHand () {
        return this.QtyOnHand;
    }
    
   public void setQtyOnHand (java.math.BigDecimal QtyOnHand) {
        this.QtyOnHand = QtyOnHand;
    }
    /**
    
    */
    
    public java.math.BigDecimal getQtyAvailable () {
        return this.QtyAvailable;
    }
    
   public void setQtyAvailable (java.math.BigDecimal QtyAvailable) {
        this.QtyAvailable = QtyAvailable;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSuggestedTransfers () {
        return this.SuggestedTransfers;
    }
    
   public void setSuggestedTransfers (java.math.BigDecimal SuggestedTransfers) {
        this.SuggestedTransfers = SuggestedTransfers;
    }
    /**
    
    */
    
    public java.math.BigDecimal getTotalOutputs () {
        return this.TotalOutputs;
    }
    
   public void setTotalOutputs (java.math.BigDecimal TotalOutputs) {
        this.TotalOutputs = TotalOutputs;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseQtyBefore () {
        return this.PurchaseQtyBefore;
    }
    
   public void setPurchaseQtyBefore (java.math.BigDecimal PurchaseQtyBefore) {
        this.PurchaseQtyBefore = PurchaseQtyBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getTotalInputsAfter () {
        return this.TotalInputsAfter;
    }
    
   public void setTotalInputsAfter (java.math.BigDecimal TotalInputsAfter) {
        this.TotalInputsAfter = TotalInputsAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getTransferQtyAfter () {
        return this.TransferQtyAfter;
    }
    
   public void setTransferQtyAfter (java.math.BigDecimal TransferQtyAfter) {
        this.TransferQtyAfter = TransferQtyAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getTotalInputsBefore () {
        return this.TotalInputsBefore;
    }
    
   public void setTotalInputsBefore (java.math.BigDecimal TotalInputsBefore) {
        this.TotalInputsBefore = TotalInputsBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getTransferQtyBefore () {
        return this.TransferQtyBefore;
    }
    
   public void setTransferQtyBefore (java.math.BigDecimal TransferQtyBefore) {
        this.TransferQtyBefore = TransferQtyBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseOrderQtyAfter () {
        return this.PurchaseOrderQtyAfter;
    }
    
   public void setPurchaseOrderQtyAfter (java.math.BigDecimal PurchaseOrderQtyAfter) {
        this.PurchaseOrderQtyAfter = PurchaseOrderQtyAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlanTransfInAfter () {
        return this.FirmPlanTransfInAfter;
    }
    
   public void setFirmPlanTransfInAfter (java.math.BigDecimal FirmPlanTransfInAfter) {
        this.FirmPlanTransfInAfter = FirmPlanTransfInAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseForecastQtyAfter () {
        return this.PurchaseForecastQtyAfter;
    }
    
   public void setPurchaseForecastQtyAfter (java.math.BigDecimal PurchaseForecastQtyAfter) {
        this.PurchaseForecastQtyAfter = PurchaseForecastQtyAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlanTransfInBefore () {
        return this.FirmPlanTransfInBefore;
    }
    
   public void setFirmPlanTransfInBefore (java.math.BigDecimal FirmPlanTransfInBefore) {
        this.FirmPlanTransfInBefore = FirmPlanTransfInBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getExpiryQty () {
        return this.ExpiryQty;
    }
    
   public void setExpiryQty (java.math.BigDecimal ExpiryQty) {
        this.ExpiryQty = ExpiryQty;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseOrderQtyBefore () {
        return this.PurchaseOrderQtyBefore;
    }
    
   public void setPurchaseOrderQtyBefore (java.math.BigDecimal PurchaseOrderQtyBefore) {
        this.PurchaseOrderQtyBefore = PurchaseOrderQtyBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseForecastQtyBefore () {
        return this.PurchaseForecastQtyBefore;
    }
    
   public void setPurchaseForecastQtyBefore (java.math.BigDecimal PurchaseForecastQtyBefore) {
        this.PurchaseForecastQtyBefore = PurchaseForecastQtyBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlannedPurchasesBefore () {
        return this.FirmPlannedPurchasesBefore;
    }
    
   public void setFirmPlannedPurchasesBefore (java.math.BigDecimal FirmPlannedPurchasesBefore) {
        this.FirmPlannedPurchasesBefore = FirmPlannedPurchasesBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlanTransfOutAfter () {
        return this.FirmPlanTransfOutAfter;
    }
    
   public void setFirmPlanTransfOutAfter (java.math.BigDecimal FirmPlanTransfOutAfter) {
        this.FirmPlanTransfOutAfter = FirmPlanTransfOutAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlanTransfOutBefore () {
        return this.FirmPlanTransfOutBefore;
    }
    
   public void setFirmPlanTransfOutBefore (java.math.BigDecimal FirmPlanTransfOutBefore) {
        this.FirmPlanTransfOutBefore = FirmPlanTransfOutBefore;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPurchaseQtyAfter () {
        return this.PurchaseQtyAfter;
    }
    
   public void setPurchaseQtyAfter (java.math.BigDecimal PurchaseQtyAfter) {
        this.PurchaseQtyAfter = PurchaseQtyAfter;
    }
    /**
    
    */
    
    public java.math.BigDecimal getFirmPlannedPurchasesAfter () {
        return this.FirmPlannedPurchasesAfter;
    }
    
   public void setFirmPlannedPurchasesAfter (java.math.BigDecimal FirmPlannedPurchasesAfter) {
        this.FirmPlannedPurchasesAfter = FirmPlannedPurchasesAfter;
    }




}