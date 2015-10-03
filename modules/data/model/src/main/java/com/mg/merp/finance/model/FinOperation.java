package com.mg.merp.finance.model;
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
import org.hibernate.annotations.Formula;

/**
 * FinOperation generated by hbm2java
 */
@Entity
@Table(name="FINOPER")
public class FinOperation extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Contractor From;
     private Contractor To;
     private CurrencyRateAuthority CurRateAuthority;
     private Folder Folder;
     private SysClient SysClient;
     private CurrencyRateType CurRateType;
     private Currency Currency;
     private Contractor Responsible;
     private Date KeepDate;
     private String Comment;
     private DocType BaseDocType;
     private String BaseDocNumber;
     private Date BaseDocDate;
     private DocHead BaseDoc;
     private DocType ConfirmDocType;
     private String ConfirmDocNumber;
     private Date ConfirmDocDate;
     private DocHead ConfirmDoc;
     private Integer ContractId;
     private DocType ContractType;
     private String ContractNumber;
     private Date ContractDate;
     private BigDecimal SumNat;
     private BigDecimal SumCur;
     private BigDecimal CurRate;
     private Integer UserId;
     private boolean Planned;
     private Set<Specification> Specifications = new HashSet<Specification>(0);

    public FinOperation() {
    }

    public FinOperation(Contractor From, Contractor To, CurrencyRateAuthority CurRateAuthority, Folder Folder, SysClient SysClient, CurrencyRateType CurRateType, Currency Currency, Contractor Responsible, Date KeepDate, String Comment, DocType BaseDocType, String BaseDocNumber, Date BaseDocDate, DocHead BaseDoc, DocType ConfirmDocType, String ConfirmDocNumber, Date ConfirmDocDate, DocHead ConfirmDoc, Integer ContractId, DocType ContractType, String ContractNumber, Date ContractDate, BigDecimal CurRate, Integer UserId, boolean Planned, Set<Specification> Specifications) {
       this.From = From;
       this.To = To;
       this.CurRateAuthority = CurRateAuthority;
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.CurRateType = CurRateType;
       this.Currency = Currency;
       this.Responsible = Responsible;
       this.KeepDate = KeepDate;
       this.Comment = Comment;
       this.BaseDocType = BaseDocType;
       this.BaseDocNumber = BaseDocNumber;
       this.BaseDocDate = BaseDocDate;
       this.BaseDoc = BaseDoc;
       this.ConfirmDocType = ConfirmDocType;
       this.ConfirmDocNumber = ConfirmDocNumber;
       this.ConfirmDocDate = ConfirmDocDate;
       this.ConfirmDoc = ConfirmDoc;
       this.ContractId = ContractId;
       this.ContractType = ContractType;
       this.ContractNumber = ContractNumber;
       this.ContractDate = ContractDate;
       this.CurRate = CurRate;
       this.UserId = UserId;
       this.Planned = Planned;
       this.Specifications = Specifications;
    }

     @SequenceGenerator(name="generator", sequenceName="FINOPER_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")


    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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
    @JoinColumn(name="TO_ID")
    public Contractor getTo() {
        return this.To;
    }

    public void setTo(Contractor To) {
        this.To = To;
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
    @JoinColumn(name="CURCODE")
    public Currency getCurrency() {
        return this.Currency;
    }

    public void setCurrency(Currency Currency) {
        this.Currency = Currency;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESPONSIBLE")
    public Contractor getResponsible() {
        return this.Responsible;
    }

    public void setResponsible(Contractor Responsible) {
        this.Responsible = Responsible;
    }


    @Column(name="KEEPDATE", columnDefinition="TIMESTAMP")
    public Date getKeepDate() {
        return this.KeepDate;
    }

    public void setKeepDate(Date KeepDate) {
        this.KeepDate = KeepDate;
    }


    @Column(name="COMMENT", columnDefinition="VARCHAR", length=256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCBASETYPE")
    public DocType getBaseDocType() {
        return this.BaseDocType;
    }

    public void setBaseDocType(DocType BaseDocType) {
        this.BaseDocType = BaseDocType;
    }


    @Column(name="DOCBASENUMBER", columnDefinition="CHAR", length=20)
    public String getBaseDocNumber() {
        return this.BaseDocNumber;
    }

    public void setBaseDocNumber(String BaseDocNumber) {
        this.BaseDocNumber = BaseDocNumber;
    }


    @Column(name="DOCBASEDATE", columnDefinition="TIMESTAMP")
    public Date getBaseDocDate() {
        return this.BaseDocDate;
    }

    public void setBaseDocDate(Date BaseDocDate) {
        this.BaseDocDate = BaseDocDate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCBASE_ID")
    public DocHead getBaseDoc() {
        return this.BaseDoc;
    }

    public void setBaseDoc(DocHead BaseDoc) {
        this.BaseDoc = BaseDoc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCTYPE")
    public DocType getConfirmDocType() {
        return this.ConfirmDocType;
    }

    public void setConfirmDocType(DocType ConfirmDocType) {
        this.ConfirmDocType = ConfirmDocType;
    }


    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public String getConfirmDocNumber() {
        return this.ConfirmDocNumber;
    }

    public void setConfirmDocNumber(String ConfirmDocNumber) {
        this.ConfirmDocNumber = ConfirmDocNumber;
    }


    @Column(name="DOCDATE", columnDefinition="TIMESTAMP")
    public Date getConfirmDocDate() {
        return this.ConfirmDocDate;
    }

    public void setConfirmDocDate(Date ConfirmDocDate) {
        this.ConfirmDocDate = ConfirmDocDate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOC_ID")
    public DocHead getConfirmDoc() {
        return this.ConfirmDoc;
    }

    public void setConfirmDoc(DocHead ConfirmDoc) {
        this.ConfirmDoc = ConfirmDoc;
    }


    @Column(name="CONTRACT_ID", columnDefinition="INTEGER")
    public Integer getContractId() {
        return this.ContractId;
    }

    public void setContractId(Integer ContractId) {
        this.ContractId = ContractId;
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

    @Formula(value="(select sum(s.sumnat) from finspec s where s.finoper_id = Id and s.parent_id is null)")
    public BigDecimal getSumNat() {
        return this.SumNat;
    }

    public void setSumNat(BigDecimal SumNat) {
        this.SumNat = SumNat;
    }

    @Formula(value="(select sum(s.sumcur) from finspec s where s.finoper_id = Id and s.parent_id is null)")
    public BigDecimal getSumCur() {
        return this.SumCur;
    }

    public void setSumCur(BigDecimal SumCur) {
        this.SumCur = SumCur;
    }


    @Column(name="CURRATE", columnDefinition="NUMERIC", precision=15, scale=5)
    public BigDecimal getCurRate() {
        return this.CurRate;
    }

    public void setCurRate(BigDecimal CurRate) {
        this.CurRate = CurRate;
    }


    @Column(name="USER_ID", columnDefinition="INTEGER")
    public Integer getUserId() {
        return this.UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }


    @Column(name="PLANNED", columnDefinition="SMALLINT")
    public boolean isPlanned() {
        return this.Planned;
    }

    public void setPlanned(boolean Planned) {
        this.Planned = Planned;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="FinOper")
    public Set<Specification> getSpecifications() {
        return this.Specifications;
    }

    public void setSpecifications(Set<Specification> Specifications) {
        this.Specifications = Specifications;
    }
}

