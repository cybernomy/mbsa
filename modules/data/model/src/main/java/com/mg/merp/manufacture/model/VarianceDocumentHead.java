package com.mg.merp.manufacture.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.OmittedWhitespaceStringType;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.core.model.SysCompany;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocType;
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.reference.model.CalcTaxesKind;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VarianceDocumentHead generated by hbm2java
 */
@Entity
@Table(name="MF_VARIANCE_DOC_HEAD"
)
public class VarianceDocumentHead extends com.mg.merp.document.model.DocHead implements java.io.Serializable {


     private WorkCenter WC;
     private Job Job;

    public VarianceDocumentHead() {
    }

	
    public VarianceDocumentHead(SysCompany SysCompany, DocSection DocSection, Short Requester, boolean ManualDocNumber) {
        super(SysCompany, DocSection, Requester, ManualDocNumber);        
    }
    public VarianceDocumentHead(SysCompany SysCompany, Contractor Through, Contractor From, CurrencyRateAuthority CurrencyRateAuthority, Contractor SrcMol, PriceType PriceType, Folder DiscountFolder, Folder Folder, DocType DocType, DocHead Contract, Currency Currency, Contractor SrcStock, DocType ContractType, Contractor To, Contractor DstMol, DocHead BaseDocument, SysClient SysClient, Contractor DstStock, CalcTaxesKind CalcTaxesKind, DocType BaseDocType, CurrencyRateType CurrencyRateType, PriceListHead PriceList, DocSection DocSection, OmittedWhitespaceStringType DocNumber, Date DocDate, BigDecimal CurCource, BigDecimal SumCur, BigDecimal SumNat, OmittedWhitespaceStringType BaseDocNumber, Date BaseDocDate, OmittedWhitespaceStringType ContractNumber, Date ContractDate, BigDecimal Weight, BigDecimal Volume, Short Requester, byte[] Original, String UNID, boolean ManualDocNumber, String Description, WorkCenter WC, Job Job) {
        super(SysCompany, Through, From, CurrencyRateAuthority, SrcMol, PriceType, DiscountFolder, Folder, DocType, Contract, Currency, SrcStock, ContractType, To, DstMol, BaseDocument, SysClient, DstStock, CalcTaxesKind, BaseDocType, CurrencyRateType, PriceList, DocSection, DocNumber, DocDate, CurCource, SumCur, SumNat, BaseDocNumber, BaseDocDate, ContractNumber, ContractDate, Weight, Volume, Requester, Original, UNID, ManualDocNumber, Description);        
       this.WC = WC;
       this.Job = Job;
    }
   

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WC_ID")
    public WorkCenter getWC() {
        return this.WC;
    }
    
    public void setWC(WorkCenter WC) {
        this.WC = WC;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_ID")
    public Job getJob() {
        return this.Job;
    }
    
    public void setJob(Job Job) {
        this.Job = Job;
    }




}


