package com.mg.merp.account.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.OmittedWhitespaceStringType;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.core.model.SysCompany;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.CalcTaxesKind;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CashDocument generated by hbm2java
 */
@Entity
@Table(name="CASHDOCUMENT"
)
public class CashDocument extends com.mg.merp.document.model.DocHead implements java.io.Serializable {


     private Contractor Bookkeeper;
     private Contractor Chief;
     private AccPlan Acc;
     private Contractor Company;
     private Contractor Cashier;
     private String AnlCode;
     private String TargetCode;
     private String Base;
     private String Comment;
     private Date GetDate;
     private String GetDocument;
     private String Comment1;

    public CashDocument() {
    }

	
    public CashDocument(SysCompany SysCompany, DocSection DocSection, Short Requester, boolean ManualDocNumber) {
        super(SysCompany, DocSection, Requester, ManualDocNumber);        
    }
    public CashDocument(SysCompany SysCompany, Contractor Through, Contractor From, CurrencyRateAuthority CurrencyRateAuthority, Contractor SrcMol, PriceType PriceType, Folder DiscountFolder, Folder Folder, DocType DocType, DocHead Contract, Currency Currency, Contractor SrcStock, DocType ContractType, Contractor To, Contractor DstMol, DocHead BaseDocument, SysClient SysClient, Contractor DstStock, CalcTaxesKind CalcTaxesKind, DocType BaseDocType, CurrencyRateType CurrencyRateType, PriceListHead PriceList, DocSection DocSection, OmittedWhitespaceStringType DocNumber, Date DocDate, BigDecimal CurCource, BigDecimal SumCur, BigDecimal SumNat, OmittedWhitespaceStringType BaseDocNumber, Date BaseDocDate, OmittedWhitespaceStringType ContractNumber, Date ContractDate, BigDecimal Weight, BigDecimal Volume, Short Requester, byte[] Original, String UNID, boolean ManualDocNumber, String Description, Contractor Bookkeeper, Contractor Chief, AccPlan Acc, Contractor Company, Contractor Cashier, String AnlCode, String TargetCode, String Base, String Comment, Date GetDate, String GetDocument, String Comment1) {
        super(SysCompany, Through, From, CurrencyRateAuthority, SrcMol, PriceType, DiscountFolder, Folder, DocType, Contract, Currency, SrcStock, ContractType, To, DstMol, BaseDocument, SysClient, DstStock, CalcTaxesKind, BaseDocType, CurrencyRateType, PriceList, DocSection, DocNumber, DocDate, CurCource, SumCur, SumNat, BaseDocNumber, BaseDocDate, ContractNumber, ContractDate, Weight, Volume, Requester, Original, UNID, ManualDocNumber, Description);        
       this.Bookkeeper = Bookkeeper;
       this.Chief = Chief;
       this.Acc = Acc;
       this.Company = Company;
       this.Cashier = Cashier;
       this.AnlCode = AnlCode;
       this.TargetCode = TargetCode;
       this.Base = Base;
       this.Comment = Comment;
       this.GetDate = GetDate;
       this.GetDocument = GetDocument;
       this.Comment1 = Comment1;
    }
   

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BOOKKEEPER")
    public Contractor getBookkeeper() {
        return this.Bookkeeper;
    }
    
    public void setBookkeeper(Contractor Bookkeeper) {
        this.Bookkeeper = Bookkeeper;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHIEF")
    public Contractor getChief() {
        return this.Chief;
    }
    
    public void setChief(Contractor Chief) {
        this.Chief = Chief;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACC_ID")
    public AccPlan getAcc() {
        return this.Acc;
    }
    
    public void setAcc(AccPlan Acc) {
        this.Acc = Acc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPANY")
    public Contractor getCompany() {
        return this.Company;
    }
    
    public void setCompany(Contractor Company) {
        this.Company = Company;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CASHIER")
    public Contractor getCashier() {
        return this.Cashier;
    }
    
    public void setCashier(Contractor Cashier) {
        this.Cashier = Cashier;
    }

    
    @Column(name="ANLCODE", columnDefinition="CHAR", length=15)
    public String getAnlCode() {
        return this.AnlCode;
    }
    
    public void setAnlCode(String AnlCode) {
        this.AnlCode = AnlCode;
    }

    
    @Column(name="TARGETCODE", columnDefinition="CHAR", length=15)
    public String getTargetCode() {
        return this.TargetCode;
    }
    
    public void setTargetCode(String TargetCode) {
        this.TargetCode = TargetCode;
    }

    
    @Column(name="BASE", columnDefinition="VARCHAR", length=256)
    public String getBase() {
        return this.Base;
    }
    
    public void setBase(String Base) {
        this.Base = Base;
    }

    
    @Column(name="COMMENT", columnDefinition="VARCHAR", length=256)
    public String getComment() {
        return this.Comment;
    }
    
    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    
    @Column(name="GETDATE", columnDefinition="TIMESTAMP")
    public Date getGetDate() {
        return this.GetDate;
    }
    
    public void setGetDate(Date GetDate) {
        this.GetDate = GetDate;
    }

    
    @Column(name="GETDOCUMENT", columnDefinition="VARCHAR", length=256)
    public String getGetDocument() {
        return this.GetDocument;
    }
    
    public void setGetDocument(String GetDocument) {
        this.GetDocument = GetDocument;
    }

    
    @Column(name="COMMENT1", columnDefinition="VARCHAR", length=256)
    public String getComment1() {
        return this.Comment1;
    }
    
    public void setComment1(String Comment1) {
        this.Comment1 = Comment1;
    }




}


