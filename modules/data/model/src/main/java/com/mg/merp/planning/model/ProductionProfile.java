package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.mfreference.model.PlanningLevel;
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
import org.hibernate.annotations.Formula;

/**
 * ProductionProfile generated by hbm2java
 */
@Entity
@Table(name="PP_PRODUCTION_PROFILE")
public class ProductionProfile extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private PlanningLevel PlanningLevel;
     private SysClient SysClient;
     private GenericItem GenericItem;
     private Short StartBucketOffset;
     private Short EndBucketOffset;
     private BigDecimal ProductionRatio;
     private BigDecimal BucketProductionRatio;
     private Date StartBucketStartDate;
     private Date EndBucketStartDate;
     private Date StartBucketEndDate;
     private Date EndBucketEndDate;

    public ProductionProfile() {
    }

    public ProductionProfile(PlanningLevel PlanningLevel, SysClient SysClient, GenericItem GenericItem, Short StartBucketOffset, Short EndBucketOffset, BigDecimal ProductionRatio, BigDecimal BucketProductionRatio) {
       this.PlanningLevel = PlanningLevel;
       this.SysClient = SysClient;
       this.GenericItem = GenericItem;
       this.StartBucketOffset = StartBucketOffset;
       this.EndBucketOffset = EndBucketOffset;
       this.ProductionRatio = ProductionRatio;
       this.BucketProductionRatio = BucketProductionRatio;
    }

     @SequenceGenerator(name="generator", sequenceName="PP_PRODUCTION_PROFILE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")


    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PLANNING_LEVEL_ID")
    public PlanningLevel getPlanningLevel() {
        return this.PlanningLevel;
    }

    public void setPlanningLevel(PlanningLevel PlanningLevel) {
        this.PlanningLevel = PlanningLevel;
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
    @JoinColumn(name="GENERIC_ITEM_ID")
    public GenericItem getGenericItem() {
        return this.GenericItem;
    }

    public void setGenericItem(GenericItem GenericItem) {
        this.GenericItem = GenericItem;
    }


    @Column(name="START_BUCKET_OFFSET", columnDefinition="SMALLINT")
    public Short getStartBucketOffset() {
        return this.StartBucketOffset;
    }

    public void setStartBucketOffset(Short StartBucketOffset) {
        this.StartBucketOffset = StartBucketOffset;
    }


    @Column(name="END_BUCKET_OFFSET", columnDefinition="SMALLINT")
    public Short getEndBucketOffset() {
        return this.EndBucketOffset;
    }

    public void setEndBucketOffset(Short EndBucketOffset) {
        this.EndBucketOffset = EndBucketOffset;
    }


    @Column(name="PRODUCTION_RATIO", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getProductionRatio() {
        return this.ProductionRatio;
    }

    public void setProductionRatio(BigDecimal ProductionRatio) {
        this.ProductionRatio = ProductionRatio;
    }


    @Column(name="BUCKET_PRODUCTION_RATIO", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getBucketProductionRatio() {
        return this.BucketProductionRatio;
    }

    public void setBucketProductionRatio(BigDecimal BucketProductionRatio) {
        this.BucketProductionRatio = BucketProductionRatio;
    }

    @Formula(value="(select plb1.start_date from pp_planning_level_bucket plb1 where (plb1.planning_level_id = PLANNING_LEVEL_ID) and (plb1.bucket_offset = START_BUCKET_OFFSET))")
    public Date getStartBucketStartDate() {
        return this.StartBucketStartDate;
    }

    public void setStartBucketStartDate(Date StartBucketStartDate) {
        this.StartBucketStartDate = StartBucketStartDate;
    }

    @Formula(value="(select plb2.start_date from pp_planning_level_bucket plb2 where (plb2.planning_level_id = PLANNING_LEVEL_ID) and (plb2.bucket_offset = END_BUCKET_OFFSET))")
    public Date getEndBucketStartDate() {
        return this.EndBucketStartDate;
    }

    public void setEndBucketStartDate(Date EndBucketStartDate) {
        this.EndBucketStartDate = EndBucketStartDate;
    }

    @Formula(value="(select plb1.end_date from pp_planning_level_bucket plb1 where (plb1.planning_level_id = PLANNING_LEVEL_ID) and (plb1.bucket_offset = START_BUCKET_OFFSET))")
    public Date getStartBucketEndDate() {
        return this.StartBucketEndDate;
    }

    public void setStartBucketEndDate(Date StartBucketEndDate) {
        this.StartBucketEndDate = StartBucketEndDate;
    }

    @Formula(value="(select plb2.end_date from pp_planning_level_bucket plb2 where (plb2.planning_level_id = PLANNING_LEVEL_ID) and (plb2.bucket_offset = END_BUCKET_OFFSET))")
    public Date getEndBucketEndDate() {
        return this.EndBucketEndDate;
    }

    public void setEndBucketEndDate(Date EndBucketEndDate) {
        this.EndBucketEndDate = EndBucketEndDate;
    }
}

