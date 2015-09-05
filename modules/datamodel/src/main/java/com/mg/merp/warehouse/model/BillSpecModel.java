/*
 * BillSpecModel.java
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
package com.mg.merp.warehouse.model;



/**
 * @author hbm2java
 * @version $Id: BillSpecModel.java,v 1.3 2005/07/13 10:29:42 safonov Exp $
 */
public class BillSpecModel extends com.mg.merp.document.model.DocSpecModel implements java.io.Serializable {

    // Fields    

 private java.math.BigDecimal Discount;
 private java.math.BigDecimal PriceWithDiscount;
 private java.math.BigDecimal SummaWithDiscount;
 private java.math.BigDecimal Cost;
 private java.math.BigDecimal AcceptanceQuan;
 private java.math.BigDecimal AcceptanceSum;
 private java.math.BigDecimal AcceptancePackingQuan;
 private java.math.BigDecimal DocDiscount;


    // Constructors

    /** default constructor */
    public BillSpecModel() {
    }
    
    
    // Property accessors
    
    public java.math.BigDecimal getDiscount () {
        return this.Discount;
    }
    
   public void setDiscount (java.math.BigDecimal Discount) {
        this.Discount = Discount;
    }
    /**
    
    */
    
    public java.math.BigDecimal getPriceWithDiscount () {
        return this.PriceWithDiscount;
    }
    
   public void setPriceWithDiscount (java.math.BigDecimal PriceWithDiscount) {
        this.PriceWithDiscount = PriceWithDiscount;
    }
    /**
    
    */
    
    public java.math.BigDecimal getSummaWithDiscount () {
        return this.SummaWithDiscount;
    }
    
   public void setSummaWithDiscount (java.math.BigDecimal SummaWithDiscount) {
        this.SummaWithDiscount = SummaWithDiscount;
    }
    /**
    
    */
    
    public java.math.BigDecimal getCost () {
        return this.Cost;
    }
    
   public void setCost (java.math.BigDecimal Cost) {
        this.Cost = Cost;
    }
    /**
    
    */
    
    public java.math.BigDecimal getAcceptanceQuan () {
        return this.AcceptanceQuan;
    }
    
   public void setAcceptanceQuan (java.math.BigDecimal AcceptanceQuan) {
        this.AcceptanceQuan = AcceptanceQuan;
    }
    /**
    
    */
    
    public java.math.BigDecimal getAcceptanceSum () {
        return this.AcceptanceSum;
    }
    
   public void setAcceptanceSum (java.math.BigDecimal AcceptanceSum) {
        this.AcceptanceSum = AcceptanceSum;
    }
    /**
    
    */
    
    public java.math.BigDecimal getAcceptancePackingQuan () {
        return this.AcceptancePackingQuan;
    }
    
   public void setAcceptancePackingQuan (java.math.BigDecimal AcceptancePackingQuan) {
        this.AcceptancePackingQuan = AcceptancePackingQuan;
    }
    /**
    
    */
    
    public java.math.BigDecimal getDocDiscount () {
        return this.DocDiscount;
    }
    
   public void setDocDiscount (java.math.BigDecimal DocDiscount) {
        this.DocDiscount = DocDiscount;
    }




}