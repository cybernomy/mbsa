/*
 * ContractorCardHist.java
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

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: ContractorCardHist.java,v 1.4 2006/12/19 12:06:10 leonova Exp $
 */
public class ContractorCardHist extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.document.model.DocHead DocHead;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.settlement.model.ContractorCard ContractorCard;
 private java.lang.Short Kind;
 private java.util.Date ProcessDate;
 private java.math.BigDecimal SumCur;
 private java.math.BigDecimal SumNat;
 private java.util.Date DateTime;


    // Constructors

    /** default constructor */
    public ContractorCardHist() {
    }
    
    /** constructor with id */
    public ContractorCardHist(java.lang.Integer Id) {
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
    
    public com.mg.merp.settlement.model.ContractorCard getContractorCard () {
        return this.ContractorCard;
    }
    
   public void setContractorCard (com.mg.merp.settlement.model.ContractorCard Contractorcard) {
        this.ContractorCard = Contractorcard;
    }
    /**
    
    */
    
    public java.lang.Short getKind () {
        return this.Kind;
    }
    
   public void setKind (java.lang.Short Kind) {
        this.Kind = Kind;
    }
    /**
    
    */
    @DataItemName("Settlement.ContrCardHist.ProcessDate")
    public java.util.Date getProcessDate () {
        return this.ProcessDate;
    }
    
   public void setProcessDate (java.util.Date Processdate) {
        this.ProcessDate = Processdate;
    }
    /**
    
    */
   @DataItemName("Settlement.ContrCardHist.SumCur")
    public java.math.BigDecimal getSumCur () {
        return this.SumCur;
    }
    
   public void setSumCur (java.math.BigDecimal Sumcur) {
        this.SumCur = Sumcur;
    }
    /**
    
    */
    @DataItemName("Settlement.ContrCardHist.SumNat")
    public java.math.BigDecimal getSumNat () {
        return this.SumNat;
    }
    
   public void setSumNat (java.math.BigDecimal Sumnat) {
        this.SumNat = Sumnat;
    }
    /**
    
    */
    
    public java.util.Date getDateTime () {
        return this.DateTime;
    }
    
   public void setDateTime (java.util.Date Datetime) {
        this.DateTime = Datetime;
    }




}