package com.mg.merp.warehouse.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
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
 * StockBatchHistory generated by hbm2java
 */
@Entity
@Table(name="STOCKBATCHHISTORY"
)
public class StockBatchHistory extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocHead DocHead;
     private StockBatchHistory StockBatchHistory;
     private DocSpec DocSpec;
     private SysClient SysClient;
     private StockBatch StockBatch;
     private Date DateTime;
     private EnumUserType Kind;
     private BigDecimal Quantity;
     private Date ProcessDate;
     private BigDecimal Quantity2;

    public StockBatchHistory() {
    }

	
    public StockBatchHistory(EnumUserType Kind) {
        this.Kind = Kind;
    }
    public StockBatchHistory(DocHead DocHead, StockBatchHistory StockBatchHistory, DocSpec DocSpec, SysClient SysClient, StockBatch StockBatch, Date DateTime, EnumUserType Kind, BigDecimal Quantity, Date ProcessDate, BigDecimal Quantity2) {
       this.DocHead = DocHead;
       this.StockBatchHistory = StockBatchHistory;
       this.DocSpec = DocSpec;
       this.SysClient = SysClient;
       this.StockBatch = StockBatch;
       this.DateTime = DateTime;
       this.Kind = Kind;
       this.Quantity = Quantity;
       this.ProcessDate = ProcessDate;
       this.Quantity2 = Quantity2;
    }
   
     @SequenceGenerator(name="generator", sequenceName="STOCKBATCHHISTORY_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCUMENT_ID")
    public DocHead getDocHead() {
        return this.DocHead;
    }
    
    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PREV_STOCKBATCHHIST_ID")
    public StockBatchHistory getStockBatchHistory() {
        return this.StockBatchHistory;
    }
    
    public void setStockBatchHistory(StockBatchHistory StockBatchHistory) {
        this.StockBatchHistory = StockBatchHistory;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCUMENTSPEC_ID")
    public DocSpec getDocSpec() {
        return this.DocSpec;
    }
    
    public void setDocSpec(DocSpec DocSpec) {
        this.DocSpec = DocSpec;
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
    @JoinColumn(name="STOCKBATCH_ID")
    public StockBatch getStockBatch() {
        return this.StockBatch;
    }
    
    public void setStockBatch(StockBatch StockBatch) {
        this.StockBatch = StockBatch;
    }

    
    @Column(name="DATETIME", columnDefinition="TIMESTAMP")
    public Date getDateTime() {
        return this.DateTime;
    }
    
    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }

    
    @Column(name="KIND", nullable=false, columnDefinition="SMALLINT")
    public EnumUserType getKind() {
        return this.Kind;
    }
    
    public void setKind(EnumUserType Kind) {
        this.Kind = Kind;
    }

    
    @Column(name="QUANTITY", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }
    
    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    
    @Column(name="PROCESSDATE", columnDefinition="TIMESTAMP")
    public Date getProcessDate() {
        return this.ProcessDate;
    }
    
    public void setProcessDate(Date ProcessDate) {
        this.ProcessDate = ProcessDate;
    }

    
    @Column(name="QUANTITY2", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getQuantity2() {
        return this.Quantity2;
    }
    
    public void setQuantity2(BigDecimal Quantity2) {
        this.Quantity2 = Quantity2;
    }




}


