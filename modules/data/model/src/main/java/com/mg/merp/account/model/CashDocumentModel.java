package com.mg.merp.account.model;

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
 * CashDocumentModel generated by hbm2java
 */
@Entity
@Table(name = "CASHDOCUMENTMODEL")
public class CashDocumentModel extends com.mg.merp.document.model.DocHeadModel implements java.io.Serializable {

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

    public CashDocumentModel() {
    }

    public CashDocumentModel(DocSection DocSection, boolean ManualDocNumber) {
        super(DocSection, ManualDocNumber);
    }

    public CashDocumentModel(SysCompany SysCompany, Contractor Through, Folder ModelDestFolder, Contractor From, Contractor To, Contractor DstMol, CurrencyRateAuthority CurrencyRateAuthority, Contractor SrcMol, Folder Folder, SysClient SysClient, Contractor DstStock, CurrencyRateType CurrencyRateType, Contractor SrcStock, DocSection DocSection, String ModelName, DocType DocType, String DocNumber, Date DocDate, Currency Currency, BigDecimal CurCource, BigDecimal SumCur, BigDecimal SumNat, DocHead BaseDocument, DocType BaseDocType, String BaseDocNumber, Date BaseDocDate, DocHead Contract, DocType ContractType, String ContractNumber, Date ContractDate, BigDecimal Weight, BigDecimal Volume, PriceListHead PriceList, PriceType PriceType, CalcTaxesKind CalcTaxesKind, Folder DiscountFolder, boolean ManualDocNumber, String Description, Contractor Bookkeeper, Contractor Chief, AccPlan Acc, Contractor Company, Contractor Cashier, String AnlCode, String TargetCode, String Base, String Comment, Date GetDate, String GetDocument, String Comment1) {
        super(SysCompany, Through, ModelDestFolder, From, To, DstMol, CurrencyRateAuthority, SrcMol, Folder, SysClient, DstStock, CurrencyRateType, SrcStock, DocSection, ModelName, DocType, DocNumber, DocDate, Currency, CurCource, SumCur, SumNat, BaseDocument, BaseDocType, BaseDocNumber, BaseDocDate, Contract, ContractType, ContractNumber, ContractDate, Weight, Volume, PriceList, PriceType, CalcTaxesKind, DiscountFolder, ManualDocNumber, Description);
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKKEEPER")
    public Contractor getBookkeeper() {
        return this.Bookkeeper;
    }

    public void setBookkeeper(Contractor Bookkeeper) {
        this.Bookkeeper = Bookkeeper;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHIEF")
    public Contractor getChief() {
        return this.Chief;
    }

    public void setChief(Contractor Chief) {
        this.Chief = Chief;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACC_ID")
    public AccPlan getAcc() {
        return this.Acc;
    }

    public void setAcc(AccPlan Acc) {
        this.Acc = Acc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY")
    public Contractor getCompany() {
        return this.Company;
    }

    public void setCompany(Contractor Company) {
        this.Company = Company;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASHIER")
    public Contractor getCashier() {
        return this.Cashier;
    }

    public void setCashier(Contractor Cashier) {
        this.Cashier = Cashier;
    }

    @Column(name = "ANLCODE", columnDefinition = "CHAR", length = 15)
    public String getAnlCode() {
        return this.AnlCode;
    }

    public void setAnlCode(String AnlCode) {
        this.AnlCode = AnlCode;
    }

    @Column(name = "TARGETCODE", columnDefinition = "CHAR", length = 15)
    public String getTargetCode() {
        return this.TargetCode;
    }

    public void setTargetCode(String TargetCode) {
        this.TargetCode = TargetCode;
    }

    @Column(name = "BASE", columnDefinition = "VARCHAR", length = 256)
    public String getBase() {
        return this.Base;
    }

    public void setBase(String Base) {
        this.Base = Base;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Column(name = "GETDATE", columnDefinition = "TIMESTAMP")
    public Date getGetDate() {
        return this.GetDate;
    }

    public void setGetDate(Date GetDate) {
        this.GetDate = GetDate;
    }

    @Column(name = "GETDOCUMENT", columnDefinition = "VARCHAR", length = 256)
    public String getGetDocument() {
        return this.GetDocument;
    }

    public void setGetDocument(String GetDocument) {
        this.GetDocument = GetDocument;
    }

    @Column(name = "COMMENT1", columnDefinition = "VARCHAR", length = 256)
    public String getComment1() {
        return this.Comment1;
    }

    public void setComment1(String Comment1) {
        this.Comment1 = Comment1;
    }
}

