package com.mg.merp.account.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AccBatchHistory generated by hbm2java
 */
@Entity
@Table(name="ACCBATCHHISTORY"
)
public class AccBatchHistory extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private AccBatch AccBatch;
     private SysClient SysClient;
     private Date BeginDate;
     private Date EndDate;
     private BigDecimal Quantity;
     private BigDecimal CostNat;
     private BigDecimal CostCur;
     private Short ActionType;

    public AccBatchHistory() {
    }

	
    public AccBatchHistory(Integer Id, Date BeginDate, BigDecimal CostNat) {
        this.Id = Id;
        this.BeginDate = BeginDate;
        this.CostNat = CostNat;
    }
    public AccBatchHistory(Integer Id, AccBatch AccBatch, SysClient SysClient, Date BeginDate, Date EndDate, BigDecimal Quantity, BigDecimal CostNat, BigDecimal CostCur, Short ActionType) {
       this.Id = Id;
       this.AccBatch = AccBatch;
       this.SysClient = SysClient;
       this.BeginDate = BeginDate;
       this.EndDate = EndDate;
       this.Quantity = Quantity;
       this.CostNat = CostNat;
       this.CostCur = CostCur;
       this.ActionType = ActionType;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCBATCH_ID")
    public AccBatch getAccBatch() {
        return this.AccBatch;
    }
    
    public void setAccBatch(AccBatch AccBatch) {
        this.AccBatch = AccBatch;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="BEGINDATE", nullable=false, columnDefinition="TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }
    
    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

    
    @Column(name="ENDDATE", columnDefinition="TIMESTAMP")
    public Date getEndDate() {
        return this.EndDate;
    }
    
    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    
    @Column(name="QUANTITY", columnDefinition="NUMERIC", precision=15, scale=3)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }
    
    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    
    @Column(name="COSTNAT", nullable=false, columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getCostNat() {
        return this.CostNat;
    }
    
    public void setCostNat(BigDecimal CostNat) {
        this.CostNat = CostNat;
    }

    
    @Column(name="COSTCUR", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getCostCur() {
        return this.CostCur;
    }
    
    public void setCostCur(BigDecimal CostCur) {
        this.CostCur = CostCur;
    }

    
    @Column(name="ACTIONTYPE", columnDefinition="SMALLINT")
    public Short getActionType() {
        return this.ActionType;
    }
    
    public void setActionType(Short ActionType) {
        this.ActionType = ActionType;
    }




}

