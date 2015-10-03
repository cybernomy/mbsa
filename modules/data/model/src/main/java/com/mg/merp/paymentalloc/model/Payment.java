package com.mg.merp.paymentalloc.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name="PMA_PAYMENT"
)
public class Payment extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Contractor FromUnit;
     private Contractor ContractorFrom;
     private DocHead BaseDoc;
     private Contractor ContractorTo;
     private CurrencyRateAuthority CurRateAuthority;
     private DocHead DocHead;
     private Folder Folder;
     private SysClient SysClient;
     private CurrencyRateType CurRateType;
     private DocHead Contract;
     private Currency CurCode;
     private Contractor ToUnit;
     private boolean Planned;
     private Date PDate;
     private String Name;
     private BigDecimal CurRate;
     private BigDecimal SumCur;
     private BigDecimal SumNat;
     private BigDecimal AllocSumCur;
     private BigDecimal AllocSumNat;
     private DocType DocType;
     private String DocNumber;
     private Date DocDate;
     private DocType BaseDocType;
     private String BaseDocNumber;
     private Date BaseDocDate;
     private DocType ContractType;
     private String ContractNumber;
     private Date ContractDate;
     private String Description;
     private String Comments;
     private boolean IsModel;
     private Folder DestFolder;
     private String ModelName;
     private Set<TransactHead> TransactHeads = new HashSet<TransactHead>(0);

    public Payment() {
    }

    public Payment(Contractor FromUnit, Contractor ContractorFrom, DocHead BaseDoc, Contractor ContractorTo, CurrencyRateAuthority CurRateAuthority, DocHead DocHead, Folder Folder, SysClient SysClient, CurrencyRateType CurRateType, DocHead Contract, Currency CurCode, Contractor ToUnit, boolean Planned, Date PDate, String Name, BigDecimal CurRate, BigDecimal SumCur, BigDecimal SumNat, BigDecimal AllocSumCur, BigDecimal AllocSumNat, DocType DocType, String DocNumber, Date DocDate, DocType BaseDocType, String BaseDocNumber, Date BaseDocDate, DocType ContractType, String ContractNumber, Date ContractDate, String Description, String Comments, boolean IsModel, Folder DestFolder, String ModelName, Set<TransactHead> TransactHeads) {
       this.FromUnit = FromUnit;
       this.ContractorFrom = ContractorFrom;
       this.BaseDoc = BaseDoc;
       this.ContractorTo = ContractorTo;
       this.CurRateAuthority = CurRateAuthority;
       this.DocHead = DocHead;
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.CurRateType = CurRateType;
       this.Contract = Contract;
       this.CurCode = CurCode;
       this.ToUnit = ToUnit;
       this.Planned = Planned;
       this.PDate = PDate;
       this.Name = Name;
       this.CurRate = CurRate;
       this.SumCur = SumCur;
       this.SumNat = SumNat;
       this.AllocSumCur = AllocSumCur;
       this.AllocSumNat = AllocSumNat;
       this.DocType = DocType;
       this.DocNumber = DocNumber;
       this.DocDate = DocDate;
       this.BaseDocType = BaseDocType;
       this.BaseDocNumber = BaseDocNumber;
       this.BaseDocDate = BaseDocDate;
       this.ContractType = ContractType;
       this.ContractNumber = ContractNumber;
       this.ContractDate = ContractDate;
       this.Description = Description;
       this.Comments = Comments;
       this.IsModel = IsModel;
       this.DestFolder = DestFolder;
       this.ModelName = ModelName;
       this.TransactHeads = TransactHeads;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PMA_PAYMENT_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROMUNIT_ID")
    public Contractor getFromUnit() {
        return this.FromUnit;
    }
    
    public void setFromUnit(Contractor FromUnit) {
        this.FromUnit = FromUnit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ID")
    public Contractor getContractorFrom() {
        return this.ContractorFrom;
    }
    
    public void setContractorFrom(Contractor ContractorFrom) {
        this.ContractorFrom = ContractorFrom;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASEDOC_ID")
    public DocHead getBaseDoc() {
        return this.BaseDoc;
    }
    
    public void setBaseDoc(DocHead BaseDoc) {
        this.BaseDoc = BaseDoc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ID")
    public Contractor getContractorTo() {
        return this.ContractorTo;
    }
    
    public void setContractorTo(Contractor ContractorTo) {
        this.ContractorTo = ContractorTo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATEAUTHORITY_ID")
    public CurrencyRateAuthority getCurRateAuthority() {
        return this.CurRateAuthority;
    }
    
    public void setCurRateAuthority(CurrencyRateAuthority CurRateAuthority) {
        this.CurRateAuthority = CurRateAuthority;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOC_ID")
    public DocHead getDocHead() {
        return this.DocHead;
    }
    
    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
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
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATETYPE_ID")
    public CurrencyRateType getCurRateType() {
        return this.CurRateType;
    }
    
    public void setCurRateType(CurrencyRateType CurRateType) {
        this.CurRateType = CurRateType;
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
    @JoinColumn(name="CURCODE")
    public Currency getCurCode() {
        return this.CurCode;
    }
    
    public void setCurCode(Currency CurCode) {
        this.CurCode = CurCode;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TOUNIT_ID")
    public Contractor getToUnit() {
        return this.ToUnit;
    }
    
    public void setToUnit(Contractor ToUnit) {
        this.ToUnit = ToUnit;
    }

    
    @Column(name="PLANNED", columnDefinition="SMALLINT")
    public boolean isPlanned() {
        return this.Planned;
    }
    
    public void setPlanned(boolean Planned) {
        this.Planned = Planned;
    }

    
    @Column(name="PDATE", columnDefinition="TIMESTAMP")
    public Date getPDate() {
        return this.PDate;
    }
    
    public void setPDate(Date PDate) {
        this.PDate = PDate;
    }

    
    @Column(name="NAME", columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="CURRATE", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getCurRate() {
        return this.CurRate;
    }
    
    public void setCurRate(BigDecimal CurRate) {
        this.CurRate = CurRate;
    }

    
    @Column(name="SUMCUR", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumCur() {
        return this.SumCur;
    }
    
    public void setSumCur(BigDecimal SumCur) {
        this.SumCur = SumCur;
    }

    
    @Column(name="SUMNAT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumNat() {
        return this.SumNat;
    }
    
    public void setSumNat(BigDecimal SumNat) {
        this.SumNat = SumNat;
    }

    
    @Column(name="ALLOCSUMCUR", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getAllocSumCur() {
        return this.AllocSumCur;
    }
    
    public void setAllocSumCur(BigDecimal AllocSumCur) {
        this.AllocSumCur = AllocSumCur;
    }

    
    @Column(name="ALLOCSUMNAT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getAllocSumNat() {
        return this.AllocSumNat;
    }
    
    public void setAllocSumNat(BigDecimal AllocSumNat) {
        this.AllocSumNat = AllocSumNat;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCTYPE")
    public DocType getDocType() {
        return this.DocType;
    }
    
    public void setDocType(DocType DocType) {
        this.DocType = DocType;
    }

    
    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public String getDocNumber() {
        return this.DocNumber;
    }
    
    public void setDocNumber(String DocNumber) {
        this.DocNumber = DocNumber;
    }

    
    @Column(name="DOCDATE", columnDefinition="TIMESTAMP")
    public Date getDocDate() {
        return this.DocDate;
    }
    
    public void setDocDate(Date DocDate) {
        this.DocDate = DocDate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASEDOCTYPE")
    public DocType getBaseDocType() {
        return this.BaseDocType;
    }
    
    public void setBaseDocType(DocType BaseDocType) {
        this.BaseDocType = BaseDocType;
    }

    
    @Column(name="BASEDOCNUMBER", columnDefinition="CHAR", length=20)
    public String getBaseDocNumber() {
        return this.BaseDocNumber;
    }
    
    public void setBaseDocNumber(String BaseDocNumber) {
        this.BaseDocNumber = BaseDocNumber;
    }

    
    @Column(name="BASEDOCDATE", columnDefinition="TIMESTAMP")
    public Date getBaseDocDate() {
        return this.BaseDocDate;
    }
    
    public void setBaseDocDate(Date BaseDocDate) {
        this.BaseDocDate = BaseDocDate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTRACTTYPE")
    public DocType getContractType() {
        return this.ContractType;
    }
    
    public void setContractType(DocType ContractType) {
        this.ContractType = ContractType;
    }

    
    @Column(name="CONTRACTNUMBER", columnDefinition="CHAR", length=20)
    public String getContractNumber() {
        return this.ContractNumber;
    }
    
    public void setContractNumber(String ContractNumber) {
        this.ContractNumber = ContractNumber;
    }

    
    @Column(name="CONTRACTDATE", columnDefinition="TIMESTAMP")
    public Date getContractDate() {
        return this.ContractDate;
    }
    
    public void setContractDate(Date ContractDate) {
        this.ContractDate = ContractDate;
    }

    
    @Column(name="DESCRIPTION", columnDefinition="VARCHAR", length=256)
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    @Column(name="COMMENTS", columnDefinition="VARCHAR", length=256)
    public String getComments() {
        return this.Comments;
    }
    
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    
    @Column(name="IS_MODEL", columnDefinition="SMALLINT")
    public boolean isIsModel() {
        return this.IsModel;
    }
    
    public void setIsModel(boolean IsModel) {
        this.IsModel = IsModel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DESTFOLDER_ID")
    public Folder getDestFolder() {
        return this.DestFolder;
    }
    
    public void setDestFolder(Folder DestFolder) {
        this.DestFolder = DestFolder;
    }

    
    @Column(name="MODELNAME", columnDefinition="VARCHAR", length=80)
    public String getModelName() {
        return this.ModelName;
    }
    
    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Payment")
    public Set<TransactHead> getTransactHeads() {
        return this.TransactHeads;
    }
    
    public void setTransactHeads(Set<TransactHead> TransactHeads) {
        this.TransactHeads = TransactHeads;
    }




}

