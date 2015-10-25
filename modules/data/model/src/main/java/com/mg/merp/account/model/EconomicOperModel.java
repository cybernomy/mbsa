package com.mg.merp.account.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;
import com.mg.merp.reference.model.Contractor;
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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * EconomicOperModel generated by hbm2java
 */
@Entity
@Table(name = "ECONOMICOPERMODEL")
public class EconomicOperModel extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private Folder ModelDestFolder;

    private Contractor From;

    private Contractor To;

    private Folder Folder;

    private SysClient SysClient;

    private SpecMark SpecMark;

    private String ModelName;

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

    private BigDecimal Summa;

    private SourceEconOperContractor SourceFrom;

    private SourceEconOperContractor SourceTo;

    private Integer ContractId;

    private DocType ContractType;

    private String ContractNumber;

    private Date ContractDate;

    private Set<EconomicSpecModel> EconomicSpecsModel = new HashSet<EconomicSpecModel>(0);

    public EconomicOperModel() {
    }

    public EconomicOperModel(Folder ModelDestFolder, Contractor From, Contractor To, Folder Folder, SysClient SysClient, SpecMark SpecMark, String ModelName, Date KeepDate, String Comment, DocType BaseDocType, String BaseDocNumber, Date BaseDocDate, DocHead BaseDoc, DocType ConfirmDocType, String ConfirmDocNumber, Date ConfirmDocDate, DocHead ConfirmDoc, BigDecimal Summa, SourceEconOperContractor SourceFrom, SourceEconOperContractor SourceTo, Integer ContractId, DocType ContractType, String ContractNumber, Date ContractDate, Set<EconomicSpecModel> EconomicSpecsModel) {
        this.ModelDestFolder = ModelDestFolder;
        this.From = From;
        this.To = To;
        this.Folder = Folder;
        this.SysClient = SysClient;
        this.SpecMark = SpecMark;
        this.ModelName = ModelName;
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
        this.Summa = Summa;
        this.SourceFrom = SourceFrom;
        this.SourceTo = SourceTo;
        this.ContractId = ContractId;
        this.ContractType = ContractType;
        this.ContractNumber = ContractNumber;
        this.ContractDate = ContractDate;
        this.EconomicSpecsModel = EconomicSpecsModel;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ECONOMICOPERMODEL_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODELDESTFOLDER_ID")
    public Folder getModelDestFolder() {
        return this.ModelDestFolder;
    }

    public void setModelDestFolder(Folder ModelDestFolder) {
        this.ModelDestFolder = ModelDestFolder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_ID")
    public Contractor getFrom() {
        return this.From;
    }

    public void setFrom(Contractor From) {
        this.From = From;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_ID")
    public Contractor getTo() {
        return this.To;
    }

    public void setTo(Contractor To) {
        this.To = To;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }

    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECMARK")
    public SpecMark getSpecMark() {
        return this.SpecMark;
    }

    public void setSpecMark(SpecMark SpecMark) {
        this.SpecMark = SpecMark;
    }

    @Column(name = "MODELNAME", columnDefinition = "VARCHAR", length = 80)
    public String getModelName() {
        return this.ModelName;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    @Column(name = "KEEPDATE", columnDefinition = "TIMESTAMP")
    public Date getKeepDate() {
        return this.KeepDate;
    }

    public void setKeepDate(Date KeepDate) {
        this.KeepDate = KeepDate;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCBASETYPE")
    public DocType getBaseDocType() {
        return this.BaseDocType;
    }

    public void setBaseDocType(DocType BaseDocType) {
        this.BaseDocType = BaseDocType;
    }

    @Column(name = "DOCBASENUMBER", columnDefinition = "CHAR", length = 20)
    public String getBaseDocNumber() {
        return this.BaseDocNumber;
    }

    public void setBaseDocNumber(String BaseDocNumber) {
        this.BaseDocNumber = BaseDocNumber;
    }

    @Column(name = "DOCBASEDATE", columnDefinition = "TIMESTAMP")
    public Date getBaseDocDate() {
        return this.BaseDocDate;
    }

    public void setBaseDocDate(Date BaseDocDate) {
        this.BaseDocDate = BaseDocDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCBASE_ID")
    public DocHead getBaseDoc() {
        return this.BaseDoc;
    }

    public void setBaseDoc(DocHead BaseDoc) {
        this.BaseDoc = BaseDoc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTYPE")
    public DocType getConfirmDocType() {
        return this.ConfirmDocType;
    }

    public void setConfirmDocType(DocType ConfirmDocType) {
        this.ConfirmDocType = ConfirmDocType;
    }

    @Column(name = "DOCNUMBER", columnDefinition = "CHAR", length = 20)
    public String getConfirmDocNumber() {
        return this.ConfirmDocNumber;
    }

    public void setConfirmDocNumber(String ConfirmDocNumber) {
        this.ConfirmDocNumber = ConfirmDocNumber;
    }

    @Column(name = "DOCDATE", columnDefinition = "TIMESTAMP")
    public Date getConfirmDocDate() {
        return this.ConfirmDocDate;
    }

    public void setConfirmDocDate(Date ConfirmDocDate) {
        this.ConfirmDocDate = ConfirmDocDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOC_ID")
    public DocHead getConfirmDoc() {
        return this.ConfirmDoc;
    }

    public void setConfirmDoc(DocHead ConfirmDoc) {
        this.ConfirmDoc = ConfirmDoc;
    }

    @Column(name = "SUMMA", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getSumma() {
        return this.Summa;
    }

    public void setSumma(BigDecimal Summa) {
        this.Summa = Summa;
    }

    @Column(name = "SOURCEFROM")
    @Enumerated(EnumType.ORDINAL)
    public SourceEconOperContractor getSourceFrom() {
        return this.SourceFrom;
    }

    public void setSourceFrom(SourceEconOperContractor SourceFrom) {
        this.SourceFrom = SourceFrom;
    }

    @Column(name = "SOURCETO")
    @Enumerated(EnumType.ORDINAL)
    public SourceEconOperContractor getSourceTo() {
        return this.SourceTo;
    }

    public void setSourceTo(SourceEconOperContractor SourceTo) {
        this.SourceTo = SourceTo;
    }

    @Column(name = "CONTRACT_ID", columnDefinition = "INTEGER")
    public Integer getContractId() {
        return this.ContractId;
    }

    public void setContractId(Integer ContractId) {
        this.ContractId = ContractId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTTYPE")
    public DocType getContractType() {
        return this.ContractType;
    }

    public void setContractType(DocType ContractType) {
        this.ContractType = ContractType;
    }

    @Column(name = "CONTRACTNUMBER", columnDefinition = "CHAR", length = 20)
    public String getContractNumber() {
        return this.ContractNumber;
    }

    public void setContractNumber(String ContractNumber) {
        this.ContractNumber = ContractNumber;
    }

    @Column(name = "CONTRACTDATE", columnDefinition = "TIMESTAMP")
    public Date getContractDate() {
        return this.ContractDate;
    }

    public void setContractDate(Date ContractDate) {
        this.ContractDate = ContractDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "EconomicOperModel")
    public Set<EconomicSpecModel> getEconomicSpecsModel() {
        return this.EconomicSpecsModel;
    }

    public void setEconomicSpecsModel(Set<EconomicSpecModel> EconomicSpecsModel) {
        this.EconomicSpecsModel = EconomicSpecsModel;
    }
}

