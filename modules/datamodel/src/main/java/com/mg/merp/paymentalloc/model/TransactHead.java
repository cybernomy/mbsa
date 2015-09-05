/*
 * TransactHead.java
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
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: TransactHead.java,v 1.5 2006/10/06 04:39:42 leonova Exp $
 */
public class TransactHead extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.document.model.DocHead DocHead;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.paymentalloc.model.Payment Payment;
 private java.util.Date PDate;
 private java.math.BigDecimal TotalSumCur;
 private java.math.BigDecimal TotalSumNat;
 private java.math.BigDecimal AllocSumCur;
 private java.math.BigDecimal AllocSumNat;
 private java.util.Set SetOfPmaTrspec;


    // Constructors

    /** default constructor */
    public TransactHead() {
    }
    
    /** constructor with id */
    public TransactHead(java.lang.Integer Id) {
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
    
    public com.mg.merp.document.model.DocHead getDocHead () {
        return this.DocHead;
    }
    
   public void setDocHead (com.mg.merp.document.model.DocHead Dochead) {
        this.DocHead = Dochead;
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
    
    public com.mg.merp.paymentalloc.model.Payment getPayment () {
        return this.Payment;
    }
    
   public void setPayment (com.mg.merp.paymentalloc.model.Payment PmaPayment) {
        this.Payment = PmaPayment;
    }
    /**
    
    */
    @DataItemName("PaymentAlloc.TransactHead.PDate")
    public java.util.Date getPDate () {
        return this.PDate;
    }
    
   public void setPDate (java.util.Date Pdate) {
        this.PDate = Pdate;
    }
    /**
    
    */
   @DataItemName("PaymentAlloc.TransactHead.TotalSumCur")
    public java.math.BigDecimal getTotalSumCur () {
        return this.TotalSumCur;
    }
    
   public void setTotalSumCur (java.math.BigDecimal Totalsumcur) {
        this.TotalSumCur = Totalsumcur;
    }
    /**
    
    */
   @DataItemName("PaymentAlloc.TransactHead.TotalSumNat")
    public java.math.BigDecimal getTotalSumNat () {
        return this.TotalSumNat;
    }
    
   public void setTotalSumNat (java.math.BigDecimal Totalsumnat) {
        this.TotalSumNat = Totalsumnat;
    }
    /**
    
    */
    @DataItemName("PaymentAlloc.TransactHead.AllocSumCur")
    public java.math.BigDecimal getAllocSumCur () {
        return this.AllocSumCur;
    }
    
   public void setAllocSumCur (java.math.BigDecimal Allocsumcur) {
        this.AllocSumCur = Allocsumcur;
    }
    /**
    
    */
   @DataItemName("PaymentAlloc.TransactHead.AllocSumNat")
    public java.math.BigDecimal getAllocSumNat () {
        return this.AllocSumNat;
    }
    
   public void setAllocSumNat (java.math.BigDecimal Allocsumnat) {
        this.AllocSumNat = Allocsumnat;
    }
    /**
    
    */
    
    public java.util.Set getSetOfPmaTrspec () {
        return this.SetOfPmaTrspec;
    }
    
   public void setSetOfPmaTrspec (java.util.Set SetOfPmaTrspec) {
        this.SetOfPmaTrspec = SetOfPmaTrspec;
    }




}