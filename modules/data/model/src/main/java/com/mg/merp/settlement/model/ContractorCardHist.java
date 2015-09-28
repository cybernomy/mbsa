package com.mg.merp.settlement.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
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

/**
 * ContractorCardHist generated by hbm2java
 */
@Entity
@Table(name="CONTRACTORCARDHIST"
)
public class ContractorCardHist extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocHead DocHead;
     private SysClient SysClient;
     private ContractorCard ContractorCard;
     private Short Kind;
     private Date ProcessDate;
     private BigDecimal SumCur;
     private BigDecimal SumNat;
     private Date DateTime;

    public ContractorCardHist() {
    }

    public ContractorCardHist(DocHead DocHead, SysClient SysClient, ContractorCard ContractorCard, Short Kind, Date ProcessDate, BigDecimal SumCur, BigDecimal SumNat, Date DateTime) {
       this.DocHead = DocHead;
       this.SysClient = SysClient;
       this.ContractorCard = ContractorCard;
       this.Kind = Kind;
       this.ProcessDate = ProcessDate;
       this.SumCur = SumCur;
       this.SumNat = SumNat;
       this.DateTime = DateTime;
    }
   
     @SequenceGenerator(name="generator", sequenceName="CONTRACTORCARDHIST_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCHEAD_ID")
    public DocHead getDocHead() {
        return this.DocHead;
    }
    
    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
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
    @JoinColumn(name="CONTRACTORCARD_ID")
    public ContractorCard getContractorCard() {
        return this.ContractorCard;
    }
    
    public void setContractorCard(ContractorCard ContractorCard) {
        this.ContractorCard = ContractorCard;
    }

    
    @Column(name="KIND", columnDefinition="SMALLINT")
    public Short getKind() {
        return this.Kind;
    }
    
    public void setKind(Short Kind) {
        this.Kind = Kind;
    }

    
    @Column(name="PROCESSDATE", columnDefinition="TIMESTAMP")
    public Date getProcessDate() {
        return this.ProcessDate;
    }
    
    public void setProcessDate(Date ProcessDate) {
        this.ProcessDate = ProcessDate;
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

    
    @Column(name="DATETIME", columnDefinition="TIMESTAMP")
    public Date getDateTime() {
        return this.DateTime;
    }
    
    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }




}


