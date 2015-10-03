package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.mfreference.model.ResourceGroup;
import com.mg.merp.reference.model.Measure;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Formula;

/**
 * RccpResourceLoad generated by hbm2java
 */
@Entity
@Table(name="PP_RCCP_RESOURCE_LOAD")
public class RccpResourceLoad extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private RccpHeader RccpHeader;
     private ResourceGroup ResourceGroup;
     private Measure Measure;
     private SysClient SysClient;
     private Short BucketOffset;
     private BigDecimal LoadValue;
     private BigDecimal LoadAdjustmentValue;
     private BigDecimal LoadProductionValue;
     private BigDecimal LoadDemandValue;
     private BigDecimal Capacity;
     private BigDecimal MaxCapacity;
     private Date BucketStartDate;
     private Date BucketEndDate;

    public RccpResourceLoad() {
    }


    public RccpResourceLoad(Integer Id) {
        this.Id = Id;
    }
    public RccpResourceLoad(Integer Id, RccpHeader RccpHeader, ResourceGroup ResourceGroup, Measure Measure, SysClient SysClient, Short BucketOffset, BigDecimal LoadValue, BigDecimal LoadAdjustmentValue, BigDecimal LoadProductionValue, BigDecimal LoadDemandValue, BigDecimal Capacity, BigDecimal MaxCapacity) {
       this.Id = Id;
       this.RccpHeader = RccpHeader;
       this.ResourceGroup = ResourceGroup;
       this.Measure = Measure;
       this.SysClient = SysClient;
       this.BucketOffset = BucketOffset;
       this.LoadValue = LoadValue;
       this.LoadAdjustmentValue = LoadAdjustmentValue;
       this.LoadProductionValue = LoadProductionValue;
       this.LoadDemandValue = LoadDemandValue;
       this.Capacity = Capacity;
       this.MaxCapacity = MaxCapacity;
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
    @JoinColumn(name="RCCP_HEADER_ID")
    public RccpHeader getRccpHeader() {
        return this.RccpHeader;
    }

    public void setRccpHeader(RccpHeader RccpHeader) {
        this.RccpHeader = RccpHeader;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCE_GROUP_ID")
    public ResourceGroup getResourceGroup() {
        return this.ResourceGroup;
    }

    public void setResourceGroup(ResourceGroup ResourceGroup) {
        this.ResourceGroup = ResourceGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOAD_MEASURE_ID")
    public Measure getMeasure() {
        return this.Measure;
    }

    public void setMeasure(Measure Measure) {
        this.Measure = Measure;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }


    @Column(name="BUCKET_OFFSET", columnDefinition="SMALLINT")
    public Short getBucketOffset() {
        return this.BucketOffset;
    }

    public void setBucketOffset(Short BucketOffset) {
        this.BucketOffset = BucketOffset;
    }


    @Column(name="LOAD_VALUE", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getLoadValue() {
        return this.LoadValue;
    }

    public void setLoadValue(BigDecimal LoadValue) {
        this.LoadValue = LoadValue;
    }


    @Column(name="LOAD_ADJUSTMENT_VALUE", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getLoadAdjustmentValue() {
        return this.LoadAdjustmentValue;
    }

    public void setLoadAdjustmentValue(BigDecimal LoadAdjustmentValue) {
        this.LoadAdjustmentValue = LoadAdjustmentValue;
    }


    @Column(name="LOAD_PRODUCTION_VALUE", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getLoadProductionValue() {
        return this.LoadProductionValue;
    }

    public void setLoadProductionValue(BigDecimal LoadProductionValue) {
        this.LoadProductionValue = LoadProductionValue;
    }


    @Column(name="LOAD_DEMAND_VALUE", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getLoadDemandValue() {
        return this.LoadDemandValue;
    }

    public void setLoadDemandValue(BigDecimal LoadDemandValue) {
        this.LoadDemandValue = LoadDemandValue;
    }


    @Column(name="CAPACITY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getCapacity() {
        return this.Capacity;
    }

    public void setCapacity(BigDecimal Capacity) {
        this.Capacity = Capacity;
    }


    @Column(name="MAX_CAPACITY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getMaxCapacity() {
        return this.MaxCapacity;
    }

    public void setMaxCapacity(BigDecimal MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
    }

    @Formula(value="(select lb.start_date from pp_rccp_header rh left join pp_mps mp on mp.id = rh.mps_id left join pp_planning_level_bucket lb on (rd.bucket_offset = lb.bucket_offset) and (LB.planning_level_id = mp.planning_level_id) where RH.id = RCCP_HEADER_ID)")
    public Date getBucketStartDate() {
        return this.BucketStartDate;
    }

    public void setBucketStartDate(Date BucketStartDate) {
        this.BucketStartDate = BucketStartDate;
    }

    @Formula(value="(select lb.end_date from pp_rccp_header rh left join pp_mps mp on mp.id = rh.mps_id left join pp_planning_level_bucket lb on (rd.bucket_offset = lb.bucket_offset) and (LB.planning_level_id = mp.planning_level_id) where RH.id = RCCP_HEADER_ID)")
    public Date getBucketEndDate() {
        return this.BucketEndDate;
    }

    public void setBucketEndDate(Date BucketEndDate) {
        this.BucketEndDate = BucketEndDate;
    }
}

