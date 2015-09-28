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
import org.hibernate.annotations.Formula;

/**
 * AdvanceRepHead generated by hbm2java
 */
@Entity
@Table(name="ACC_ADVANCEREPHEAD")
public class AdvanceRepHead extends com.mg.merp.document.model.DocHead implements java.io.Serializable {


     private AccPlan Acc;
     private Contractor AccountAnt;
     private Contractor Chief;
     private Contractor Company;
     private DocHead RestDoc;
     private Contractor ChiefAccountAnt;
     private String Purpose;
     private BigDecimal PrevAdvanceSum;
     private String Received1Src;
     private Date Received1Date;
     private BigDecimal Received1Sum;
     private String Received2Src;
     private Date Received2Date;
     private BigDecimal Received2Sum;
     private String Received3Src;
     private Date Received3Date;
     private BigDecimal Received3Sum;
     private boolean RestDocKind;
     private DocType RestDocType;
     private String RestDocNumber;
     private Date RestDocDate;
     private BigDecimal RestSum;
     private Integer AttachedDocs;
     private Integer AttachedDocsSheets;
     private String Comments;
     private String Office;
     private String TabNum;
     private BigDecimal ReceivedSum;
     private boolean BalanceOrOverRun;

    public AdvanceRepHead() {
    }


    public AdvanceRepHead(SysCompany SysCompany, DocSection DocSection, Short Requester, boolean ManualDocNumber) {
        super(SysCompany, DocSection, Requester, ManualDocNumber);
    }
    public AdvanceRepHead(SysCompany SysCompany, Contractor Through, Contractor From, CurrencyRateAuthority CurrencyRateAuthority, Contractor SrcMol, PriceType PriceType, Folder DiscountFolder, Folder Folder, DocType DocType, DocHead Contract, Currency Currency, Contractor SrcStock, DocType ContractType, Contractor To, Contractor DstMol, DocHead BaseDocument, SysClient SysClient, Contractor DstStock, CalcTaxesKind CalcTaxesKind, DocType BaseDocType, CurrencyRateType CurrencyRateType, PriceListHead PriceList, DocSection DocSection, OmittedWhitespaceStringType DocNumber, Date DocDate, BigDecimal CurCource, BigDecimal SumCur, BigDecimal SumNat, OmittedWhitespaceStringType BaseDocNumber, Date BaseDocDate, OmittedWhitespaceStringType ContractNumber, Date ContractDate, BigDecimal Weight, BigDecimal Volume, Short Requester, byte[] Original, String UNID, boolean ManualDocNumber, String Description, AccPlan Acc, Contractor AccountAnt, Contractor Chief, Contractor Company, DocHead RestDoc, Contractor ChiefAccountAnt, String Purpose, BigDecimal PrevAdvanceSum, String Received1Src, Date Received1Date, BigDecimal Received1Sum, String Received2Src, Date Received2Date, BigDecimal Received2Sum, String Received3Src, Date Received3Date, BigDecimal Received3Sum, boolean RestDocKind, DocType RestDocType, String RestDocNumber, Date RestDocDate, BigDecimal RestSum, Integer AttachedDocs, Integer AttachedDocsSheets, String Comments, BigDecimal ReceivedSum, boolean BalanceOrOverRun) {
        super(SysCompany, Through, From, CurrencyRateAuthority, SrcMol, PriceType, DiscountFolder, Folder, DocType, Contract, Currency, SrcStock, ContractType, To, DstMol, BaseDocument, SysClient, DstStock, CalcTaxesKind, BaseDocType, CurrencyRateType, PriceList, DocSection, DocNumber, DocDate, CurCource, SumCur, SumNat, BaseDocNumber, BaseDocDate, ContractNumber, ContractDate, Weight, Volume, Requester, Original, UNID, ManualDocNumber, Description);
       this.Acc = Acc;
       this.AccountAnt = AccountAnt;
       this.Chief = Chief;
       this.Company = Company;
       this.RestDoc = RestDoc;
       this.ChiefAccountAnt = ChiefAccountAnt;
       this.Purpose = Purpose;
       this.PrevAdvanceSum = PrevAdvanceSum;
       this.Received1Src = Received1Src;
       this.Received1Date = Received1Date;
       this.Received1Sum = Received1Sum;
       this.Received2Src = Received2Src;
       this.Received2Date = Received2Date;
       this.Received2Sum = Received2Sum;
       this.Received3Src = Received3Src;
       this.Received3Date = Received3Date;
       this.Received3Sum = Received3Sum;
       this.RestDocKind = RestDocKind;
       this.RestDocType = RestDocType;
       this.RestDocNumber = RestDocNumber;
       this.RestDocDate = RestDocDate;
       this.RestSum = RestSum;
       this.AttachedDocs = AttachedDocs;
       this.AttachedDocsSheets = AttachedDocsSheets;
       this.Comments = Comments;
       this.ReceivedSum = ReceivedSum;
       this.BalanceOrOverRun = BalanceOrOverRun;
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
    @JoinColumn(name="ACCOUNTANT_ID")
    public Contractor getAccountAnt() {
        return this.AccountAnt;
    }

    public void setAccountAnt(Contractor AccountAnt) {
        this.AccountAnt = AccountAnt;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHIEF_ID")
    public Contractor getChief() {
        return this.Chief;
    }

    public void setChief(Contractor Chief) {
        this.Chief = Chief;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")
    public Contractor getCompany() {
        return this.Company;
    }

    public void setCompany(Contractor Company) {
        this.Company = Company;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESTDOC_ID")
    public DocHead getRestDoc() {
        return this.RestDoc;
    }

    public void setRestDoc(DocHead RestDoc) {
        this.RestDoc = RestDoc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHIEFACCOUNTANT_ID")
    public Contractor getChiefAccountAnt() {
        return this.ChiefAccountAnt;
    }

    public void setChiefAccountAnt(Contractor ChiefAccountAnt) {
        this.ChiefAccountAnt = ChiefAccountAnt;
    }


    @Column(name="PURPOSE", columnDefinition="VARCHAR", length=256)
    public String getPurpose() {
        return this.Purpose;
    }

    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }


    @Column(name="PREVADVANCESUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getPrevAdvanceSum() {
        return this.PrevAdvanceSum;
    }

    public void setPrevAdvanceSum(BigDecimal PrevAdvanceSum) {
        this.PrevAdvanceSum = PrevAdvanceSum;
    }


    @Column(name="RECEIVED1SRC", columnDefinition="VARCHAR", length=40)
    public String getReceived1Src() {
        return this.Received1Src;
    }

    public void setReceived1Src(String Received1Src) {
        this.Received1Src = Received1Src;
    }


    @Column(name="RECEIVED1DATE", columnDefinition="TIMESTAMP")
    public Date getReceived1Date() {
        return this.Received1Date;
    }

    public void setReceived1Date(Date Received1Date) {
        this.Received1Date = Received1Date;
    }


    @Column(name="RECEIVED1SUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getReceived1Sum() {
        return this.Received1Sum;
    }

    public void setReceived1Sum(BigDecimal Received1Sum) {
        this.Received1Sum = Received1Sum;
    }


    @Column(name="RECEIVED2SRC", columnDefinition="VARCHAR", length=40)
    public String getReceived2Src() {
        return this.Received2Src;
    }

    public void setReceived2Src(String Received2Src) {
        this.Received2Src = Received2Src;
    }


    @Column(name="RECEIVED2DATE", columnDefinition="TIMESTAMP")
    public Date getReceived2Date() {
        return this.Received2Date;
    }

    public void setReceived2Date(Date Received2Date) {
        this.Received2Date = Received2Date;
    }


    @Column(name="RECEIVED2SUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getReceived2Sum() {
        return this.Received2Sum;
    }

    public void setReceived2Sum(BigDecimal Received2Sum) {
        this.Received2Sum = Received2Sum;
    }


    @Column(name="RECEIVED3SRC", columnDefinition="VARCHAR", length=40)
    public String getReceived3Src() {
        return this.Received3Src;
    }

    public void setReceived3Src(String Received3Src) {
        this.Received3Src = Received3Src;
    }


    @Column(name="RECEIVED3DATE", columnDefinition="TIMESTAMP")
    public Date getReceived3Date() {
        return this.Received3Date;
    }

    public void setReceived3Date(Date Received3Date) {
        this.Received3Date = Received3Date;
    }


    @Column(name="RECEIVED3SUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getReceived3Sum() {
        return this.Received3Sum;
    }

    public void setReceived3Sum(BigDecimal Received3Sum) {
        this.Received3Sum = Received3Sum;
    }


    @Column(name="RESTDOCKIND", columnDefinition="SMALLINT")
    public boolean isRestDocKind() {
        return this.RestDocKind;
    }

    public void setRestDocKind(boolean RestDocKind) {
        this.RestDocKind = RestDocKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESTDOCTYPE")
    public DocType getRestDocType() {
        return this.RestDocType;
    }

    public void setRestDocType(DocType RestDocType) {
        this.RestDocType = RestDocType;
    }


    @Column(name="RESTDOCNUMBER", columnDefinition="CHAR", length=20)
    public String getRestDocNumber() {
        return this.RestDocNumber;
    }

    public void setRestDocNumber(String RestDocNumber) {
        this.RestDocNumber = RestDocNumber;
    }


    @Column(name="RESTDOCDATE", columnDefinition="TIMESTAMP")
    public Date getRestDocDate() {
        return this.RestDocDate;
    }

    public void setRestDocDate(Date RestDocDate) {
        this.RestDocDate = RestDocDate;
    }


    @Column(name="RESTSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getRestSum() {
        return this.RestSum;
    }

    public void setRestSum(BigDecimal RestSum) {
        this.RestSum = RestSum;
    }


    @Column(name="ATTACHEDDOCS", columnDefinition="INTEGER")
    public Integer getAttachedDocs() {
        return this.AttachedDocs;
    }

    public void setAttachedDocs(Integer AttachedDocs) {
        this.AttachedDocs = AttachedDocs;
    }


    @Column(name="ATTACHEDDOCSSHEETS", columnDefinition="INTEGER")
    public Integer getAttachedDocsSheets() {
        return this.AttachedDocsSheets;
    }

    public void setAttachedDocsSheets(Integer AttachedDocsSheets) {
        this.AttachedDocsSheets = AttachedDocsSheets;
    }


    @Column(name="COMMENTS", columnDefinition="VARCHAR", length=256)
    public String getComments() {
        return this.Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    @Formula(value="(select emp.office from dochead d left join acc_advancerephead a on a.dochead_id = d.id left join contractor c on c.id = a.company_id left join employees emp on emp.contractor_id = d.from_id where d.id = DOCHEAD_ID)")
    public String getOffice() {
        return this.Office;
    }

    public void setOffice(String Office) {
        this.Office = Office;
    }

    @Formula(value="(select emp.tabnum from dochead d left join acc_advancerephead a on a.dochead_id = d.id left join contractor c on c.id = a.company_id left join employees emp on emp.contractor_id = d.from_id where d.id = DOCHEAD_ID)")
    public String getTabNum() {
        return this.TabNum;
    }

    public void setTabNum(String TabNum) {
        this.TabNum = TabNum;
    }


    @Column(name="RECEIVEDSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getReceivedSum() {
        return this.ReceivedSum;
    }

    public void setReceivedSum(BigDecimal ReceivedSum) {
        this.ReceivedSum = ReceivedSum;
    }


    @Column(name="BALANCEOROVERRUN", columnDefinition="SMALLINT")
    public boolean isBalanceOrOverRun() {
        return this.BalanceOrOverRun;
    }

    public void setBalanceOrOverRun(boolean BalanceOrOverRun) {
        this.BalanceOrOverRun = BalanceOrOverRun;
    }

}


