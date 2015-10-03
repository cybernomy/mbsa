package com.mg.merp.document.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.OmittedWhitespaceStringType;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.core.model.SysCompany;
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
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * DocHead generated by hbm2java
 */
@Entity
@Table(name="DOCHEAD"
)
public class DocHead extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Date SysVersion;
     private SysCompany SysCompany;
     private Contractor Through;
     private Contractor From;
     private CurrencyRateAuthority CurrencyRateAuthority;
     private Contractor SrcMol;
     private PriceType PriceType;
     private Folder DiscountFolder;
     private Folder Folder;
     private DocType DocType;
     private DocHead Contract;
     private Currency Currency;
     private Contractor SrcStock;
     private DocType ContractType;
     private Contractor To;
     private Contractor DstMol;
     private DocHead BaseDocument;
     private SysClient SysClient;
     private Contractor DstStock;
     private CalcTaxesKind CalcTaxesKind;
     private DocType BaseDocType;
     private CurrencyRateType CurrencyRateType;
     private PriceListHead PriceList;
     private DocSection DocSection;
     private OmittedWhitespaceStringType DocNumber;
     private Date DocDate;
     private BigDecimal CurCource;
     private BigDecimal SumCur;
     private BigDecimal SumNat;
     private OmittedWhitespaceStringType BaseDocNumber;
     private Date BaseDocDate;
     private OmittedWhitespaceStringType ContractNumber;
     private Date ContractDate;
     private BigDecimal Weight;
     private BigDecimal Volume;
     private Short Requester;
     private byte[] Original;
     private String UNID;
     private boolean ManualDocNumber;
     private String Description;

    public DocHead() {
    }

	
    public DocHead(SysCompany SysCompany, DocSection DocSection, Short Requester, boolean ManualDocNumber) {
        this.SysCompany = SysCompany;
        this.DocSection = DocSection;
        this.Requester = Requester;
        this.ManualDocNumber = ManualDocNumber;
    }
    public DocHead(SysCompany SysCompany, Contractor Through, Contractor From, CurrencyRateAuthority CurrencyRateAuthority, Contractor SrcMol, PriceType PriceType, Folder DiscountFolder, Folder Folder, DocType DocType, DocHead Contract, Currency Currency, Contractor SrcStock, DocType ContractType, Contractor To, Contractor DstMol, DocHead BaseDocument, SysClient SysClient, Contractor DstStock, CalcTaxesKind CalcTaxesKind, DocType BaseDocType, CurrencyRateType CurrencyRateType, PriceListHead PriceList, DocSection DocSection, OmittedWhitespaceStringType DocNumber, Date DocDate, BigDecimal CurCource, BigDecimal SumCur, BigDecimal SumNat, OmittedWhitespaceStringType BaseDocNumber, Date BaseDocDate, OmittedWhitespaceStringType ContractNumber, Date ContractDate, BigDecimal Weight, BigDecimal Volume, Short Requester, byte[] Original, String UNID, boolean ManualDocNumber, String Description) {
       this.SysCompany = SysCompany;
       this.Through = Through;
       this.From = From;
       this.CurrencyRateAuthority = CurrencyRateAuthority;
       this.SrcMol = SrcMol;
       this.PriceType = PriceType;
       this.DiscountFolder = DiscountFolder;
       this.Folder = Folder;
       this.DocType = DocType;
       this.Contract = Contract;
       this.Currency = Currency;
       this.SrcStock = SrcStock;
       this.ContractType = ContractType;
       this.To = To;
       this.DstMol = DstMol;
       this.BaseDocument = BaseDocument;
       this.SysClient = SysClient;
       this.DstStock = DstStock;
       this.CalcTaxesKind = CalcTaxesKind;
       this.BaseDocType = BaseDocType;
       this.CurrencyRateType = CurrencyRateType;
       this.PriceList = PriceList;
       this.DocSection = DocSection;
       this.DocNumber = DocNumber;
       this.DocDate = DocDate;
       this.CurCource = CurCource;
       this.SumCur = SumCur;
       this.SumNat = SumNat;
       this.BaseDocNumber = BaseDocNumber;
       this.BaseDocDate = BaseDocDate;
       this.ContractNumber = ContractNumber;
       this.ContractDate = ContractDate;
       this.Weight = Weight;
       this.Volume = Volume;
       this.Requester = Requester;
       this.Original = Original;
       this.UNID = UNID;
       this.ManualDocNumber = ManualDocNumber;
       this.Description = Description;
    }
   
     @SequenceGenerator(name="generator", sequenceName="DOCHEAD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="SYS_VERSION", nullable=false)
    public Date getSysVersion() {
        return this.SysVersion;
    }
    
    public void setSysVersion(Date SysVersion) {
        this.SysVersion = SysVersion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SYS_COMPANY_ID", nullable=false)
    public SysCompany getSysCompany() {
        return this.SysCompany;
    }
    
    public void setSysCompany(SysCompany SysCompany) {
        this.SysCompany = SysCompany;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="THROUGH_ID")
    public Contractor getThrough() {
        return this.Through;
    }
    
    public void setThrough(Contractor Through) {
        this.Through = Through;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ID")
    public Contractor getFrom() {
        return this.From;
    }
    
    public void setFrom(Contractor From) {
        this.From = From;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATEAUTHORITY_ID")
    public CurrencyRateAuthority getCurrencyRateAuthority() {
        return this.CurrencyRateAuthority;
    }
    
    public void setCurrencyRateAuthority(CurrencyRateAuthority CurrencyRateAuthority) {
        this.CurrencyRateAuthority = CurrencyRateAuthority;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MOLSRC")
    public Contractor getSrcMol() {
        return this.SrcMol;
    }
    
    public void setSrcMol(Contractor SrcMol) {
        this.SrcMol = SrcMol;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRICETYPE_ID")
    public PriceType getPriceType() {
        return this.PriceType;
    }
    
    public void setPriceType(PriceType PriceType) {
        this.PriceType = PriceType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DISCOUNT_FOLDER_ID")
    public Folder getDiscountFolder() {
        return this.DiscountFolder;
    }
    
    public void setDiscountFolder(Folder DiscountFolder) {
        this.DiscountFolder = DiscountFolder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }
    
    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCTYPE")
    public DocType getDocType() {
        return this.DocType;
    }
    
    public void setDocType(DocType DocType) {
        this.DocType = DocType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTRACT_ID")
    public DocHead getContract() {
        return this.Contract;
    }
    
    public void setContract(DocHead Contract) {
        this.Contract = Contract;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_CODE")
    public Currency getCurrency() {
        return this.Currency;
    }
    
    public void setCurrency(Currency Currency) {
        this.Currency = Currency;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STOCKSRC")
    public Contractor getSrcStock() {
        return this.SrcStock;
    }
    
    public void setSrcStock(Contractor SrcStock) {
        this.SrcStock = SrcStock;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTRACTTYPE")
    public DocType getContractType() {
        return this.ContractType;
    }
    
    public void setContractType(DocType ContractType) {
        this.ContractType = ContractType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ID")
    public Contractor getTo() {
        return this.To;
    }
    
    public void setTo(Contractor To) {
        this.To = To;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MOLDEST")
    public Contractor getDstMol() {
        return this.DstMol;
    }
    
    public void setDstMol(Contractor DstMol) {
        this.DstMol = DstMol;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASEDOC_ID")
    public DocHead getBaseDocument() {
        return this.BaseDocument;
    }
    
    public void setBaseDocument(DocHead BaseDocument) {
        this.BaseDocument = BaseDocument;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STOCKDEST")
    public Contractor getDstStock() {
        return this.DstStock;
    }
    
    public void setDstStock(Contractor DstStock) {
        this.DstStock = DstStock;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CALCTAXESKIND_ID")
    public CalcTaxesKind getCalcTaxesKind() {
        return this.CalcTaxesKind;
    }
    
    public void setCalcTaxesKind(CalcTaxesKind CalcTaxesKind) {
        this.CalcTaxesKind = CalcTaxesKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASEDOCTYPE")
    public DocType getBaseDocType() {
        return this.BaseDocType;
    }
    
    public void setBaseDocType(DocType BaseDocType) {
        this.BaseDocType = BaseDocType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATETYPE_ID")
    public CurrencyRateType getCurrencyRateType() {
        return this.CurrencyRateType;
    }
    
    public void setCurrencyRateType(CurrencyRateType CurrencyRateType) {
        this.CurrencyRateType = CurrencyRateType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRICELIST_ID")
    public PriceListHead getPriceList() {
        return this.PriceList;
    }
    
    public void setPriceList(PriceListHead PriceList) {
        this.PriceList = PriceList;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCSECTION", nullable=false)
    public DocSection getDocSection() {
        return this.DocSection;
    }
    
    public void setDocSection(DocSection DocSection) {
        this.DocSection = DocSection;
    }

    
    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public OmittedWhitespaceStringType getDocNumber() {
        return this.DocNumber;
    }
    
    public void setDocNumber(OmittedWhitespaceStringType DocNumber) {
        this.DocNumber = DocNumber;
    }

    
    @Column(name="DOCDATE", columnDefinition="TIMESTAMP")
    public Date getDocDate() {
        return this.DocDate;
    }
    
    public void setDocDate(Date DocDate) {
        this.DocDate = DocDate;
    }

    
    @Column(name="CURCOURCE", columnDefinition="NUMERIC", precision=15, scale=5)
    public BigDecimal getCurCource() {
        return this.CurCource;
    }
    
    public void setCurCource(BigDecimal CurCource) {
        this.CurCource = CurCource;
    }

    
    @Column(name="SUMMACUR", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumCur() {
        return this.SumCur;
    }
    
    public void setSumCur(BigDecimal SumCur) {
        this.SumCur = SumCur;
    }

    
    @Column(name="SUMMANAT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumNat() {
        return this.SumNat;
    }
    
    public void setSumNat(BigDecimal SumNat) {
        this.SumNat = SumNat;
    }

    
    @Column(name="BASEDOCNUMBER", columnDefinition="CHAR", length=20)
    public OmittedWhitespaceStringType getBaseDocNumber() {
        return this.BaseDocNumber;
    }
    
    public void setBaseDocNumber(OmittedWhitespaceStringType BaseDocNumber) {
        this.BaseDocNumber = BaseDocNumber;
    }

    
    @Column(name="BASEDOCDATE", columnDefinition="TIMESTAMP")
    public Date getBaseDocDate() {
        return this.BaseDocDate;
    }
    
    public void setBaseDocDate(Date BaseDocDate) {
        this.BaseDocDate = BaseDocDate;
    }

    
    @Column(name="CONTRACTNUMBER", columnDefinition="CHAR", length=20)
    public OmittedWhitespaceStringType getContractNumber() {
        return this.ContractNumber;
    }
    
    public void setContractNumber(OmittedWhitespaceStringType ContractNumber) {
        this.ContractNumber = ContractNumber;
    }

    
    @Column(name="CONTRACTDATE", columnDefinition="TIMESTAMP")
    public Date getContractDate() {
        return this.ContractDate;
    }
    
    public void setContractDate(Date ContractDate) {
        this.ContractDate = ContractDate;
    }

    
    @Column(name="WEIGHT", columnDefinition="NUMERIC", precision=15, scale=3)
    public BigDecimal getWeight() {
        return this.Weight;
    }
    
    public void setWeight(BigDecimal Weight) {
        this.Weight = Weight;
    }

    
    @Column(name="VOLUME", columnDefinition="NUMERIC", precision=15, scale=3)
    public BigDecimal getVolume() {
        return this.Volume;
    }
    
    public void setVolume(BigDecimal Volume) {
        this.Volume = Volume;
    }

    
    @Column(name="REQUESTER", nullable=false, columnDefinition="SMALLINT")
    public Short getRequester() {
        return this.Requester;
    }
    
    public void setRequester(Short Requester) {
        this.Requester = Requester;
    }

    
    @Column(name="ORIGINAL", columnDefinition="BLOB SUB_TYPE 0")
    public byte[] getOriginal() {
        return this.Original;
    }
    
    public void setOriginal(byte[] Original) {
        this.Original = Original;
    }

    
    @Column(name="UNID", unique=true, updatable=false, columnDefinition="VARCHAR", length=32)
    public String getUNID() {
        return this.UNID;
    }
    
    public void setUNID(String UNID) {
        this.UNID = UNID;
    }

    
    @Column(name="MANUAL_DOC_NUMBER", nullable=false, columnDefinition="SMALLINT")
    public boolean isManualDocNumber() {
        return this.ManualDocNumber;
    }
    
    public void setManualDocNumber(boolean ManualDocNumber) {
        this.ManualDocNumber = ManualDocNumber;
    }

    
    @Column(name="DESCRIPTION", columnDefinition="VARCHAR", length=1024)
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }




}

