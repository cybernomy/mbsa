package com.mg.merp.mfreference.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Measure;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BomLabor generated by hbm2java
 */
@Entity
@Table(name="MF_BOM_LABOR"
)
public class BomLabor extends com.mg.merp.mfreference.model.BomRouteResource implements java.io.Serializable {


     private LaborClass LaborClass;
     private Measure RunTimeLbrUm;
     private long RunTicksLbr;
     private BigDecimal LbrNumber;
     private boolean LbrBackflushFlag;
     private boolean LbrOhBackflushFlag;

    public BomLabor() {
    }

    public BomLabor(ResourceGroup ResourceGroup, SysClient SysClient, CostDetail StandartCostDetail, BomRoute BomRoute, Short ResourceType, Integer TimeSequence, Date EffOnDate, Date EffOffDate, String Comment, LaborClass LaborClass, Measure RunTimeLbrUm, long RunTicksLbr, BigDecimal LbrNumber, boolean LbrBackflushFlag, boolean LbrOhBackflushFlag) {
        super(ResourceGroup, SysClient, StandartCostDetail, BomRoute, ResourceType, TimeSequence, EffOnDate, EffOffDate, Comment);        
       this.LaborClass = LaborClass;
       this.RunTimeLbrUm = RunTimeLbrUm;
       this.RunTicksLbr = RunTicksLbr;
       this.LbrNumber = LbrNumber;
       this.LbrBackflushFlag = LbrBackflushFlag;
       this.LbrOhBackflushFlag = LbrOhBackflushFlag;
    }
   

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LABOR_CLASS_ID")
    public LaborClass getLaborClass() {
        return this.LaborClass;
    }
    
    public void setLaborClass(LaborClass LaborClass) {
        this.LaborClass = LaborClass;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RUN_TIME_LBR_UM")
    public Measure getRunTimeLbrUm() {
        return this.RunTimeLbrUm;
    }
    
    public void setRunTimeLbrUm(Measure RunTimeLbrUm) {
        this.RunTimeLbrUm = RunTimeLbrUm;
    }

    
    @Column(name="RUN_TICKS_LBR", columnDefinition="NUMERIC", precision=18, scale=0)
    public long getRunTicksLbr() {
        return this.RunTicksLbr;
    }
    
    public void setRunTicksLbr(long RunTicksLbr) {
        this.RunTicksLbr = RunTicksLbr;
    }

    
    @Column(name="LBR_NUMBER", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getLbrNumber() {
        return this.LbrNumber;
    }
    
    public void setLbrNumber(BigDecimal LbrNumber) {
        this.LbrNumber = LbrNumber;
    }

    
    @Column(name="LBR_BACKFLUSH_FLAG", columnDefinition="SMALLINT")
    public boolean isLbrBackflushFlag() {
        return this.LbrBackflushFlag;
    }
    
    public void setLbrBackflushFlag(boolean LbrBackflushFlag) {
        this.LbrBackflushFlag = LbrBackflushFlag;
    }

    
    @Column(name="LBR_OH_BACKFLUSH_FLAG", columnDefinition="SMALLINT")
    public boolean isLbrOhBackflushFlag() {
        return this.LbrOhBackflushFlag;
    }
    
    public void setLbrOhBackflushFlag(boolean LbrOhBackflushFlag) {
        this.LbrOhBackflushFlag = LbrOhBackflushFlag;
    }




}

