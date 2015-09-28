package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Measure;
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
 * PlanningForecast generated by hbm2java
 */
@Entity
@Table(name="PP_PLANNING_FORECAST")
public class PlanningForecast extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Measure Measure;
     private PlanningLevel PlanningLevel;
     private Contractor Contractor;
     private ForecastVersion ForecastVersion;
     private SysClient SysClient;
     private GenericItem PlanningItem;
     private Catalog Catalog;
     private ForecastType ForecastType;
     private ForecastMethod ForecastMethod;
     private Short BucketOffset;
     private Date RequiredDate;
     private BigDecimal ForecastQuantity;
     private Date BucketStartDate;
     private Date BucketEndDate;

    public PlanningForecast() {
    }

    public PlanningForecast(Measure Measure, PlanningLevel PlanningLevel, Contractor Contractor, ForecastVersion ForecastVersion, SysClient SysClient, GenericItem PlanningItem, Catalog Catalog, ForecastType ForecastType, ForecastMethod ForecastMethod, Short BucketOffset, Date RequiredDate, BigDecimal ForecastQuantity) {
       this.Measure = Measure;
       this.PlanningLevel = PlanningLevel;
       this.Contractor = Contractor;
       this.ForecastVersion = ForecastVersion;
       this.SysClient = SysClient;
       this.PlanningItem = PlanningItem;
       this.Catalog = Catalog;
       this.ForecastType = ForecastType;
       this.ForecastMethod = ForecastMethod;
       this.BucketOffset = BucketOffset;
       this.RequiredDate = RequiredDate;
       this.ForecastQuantity = ForecastQuantity;
    }

     @SequenceGenerator(name="generator", sequenceName="PP_PLANNING_FORECAST_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")


    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEASURE_ID")
    public Measure getMeasure() {
        return this.Measure;
    }

    public void setMeasure(Measure Measure) {
        this.Measure = Measure;
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
    @JoinColumn(name="WAREHOUSE_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }

    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FORECAST_VERSION_ID")
    public ForecastVersion getForecastVersion() {
        return this.ForecastVersion;
    }

    public void setForecastVersion(ForecastVersion ForecastVersion) {
        this.ForecastVersion = ForecastVersion;
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
    @JoinColumn(name="PLANNING_ITEM_ID")
    public GenericItem getPlanningItem() {
        return this.PlanningItem;
    }

    public void setPlanningItem(GenericItem PlanningItem) {
        this.PlanningItem = PlanningItem;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }

    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }


    @Column(name="FORECAST_TYPE", columnDefinition="SMALLINT")
    public ForecastType getForecastType() {
        return this.ForecastType;
    }

    public void setForecastType(ForecastType ForecastType) {
        this.ForecastType = ForecastType;
    }


    @Column(name="FORECAST_METHOD", columnDefinition="SMALLINT")
    public ForecastMethod getForecastMethod() {
        return this.ForecastMethod;
    }

    public void setForecastMethod(ForecastMethod ForecastMethod) {
        this.ForecastMethod = ForecastMethod;
    }


    @Column(name="BUCKET_OFFSET", columnDefinition="SMALLINT")
    public Short getBucketOffset() {
        return this.BucketOffset;
    }

    public void setBucketOffset(Short BucketOffset) {
        this.BucketOffset = BucketOffset;
    }


    @Column(name="REQUIRED_DATE", columnDefinition="TIMESTAMP")
    public Date getRequiredDate() {
        return this.RequiredDate;
    }

    public void setRequiredDate(Date RequiredDate) {
        this.RequiredDate = RequiredDate;
    }


    @Column(name="FORECAST_QUANTITY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getForecastQuantity() {
        return this.ForecastQuantity;
    }

    public void setForecastQuantity(BigDecimal ForecastQuantity) {
        this.ForecastQuantity = ForecastQuantity;
    }

    @Formula(value="(select plb.start_date from pp_planning_level_bucket plb where (plb.planning_level_id = PLANNING_LEVEL_ID) and (plb.bucket_offset = BUCKET_OFFSET))")
    public Date getBucketStartDate() {
        return this.BucketStartDate;
    }

    public void setBucketStartDate(Date BucketStartDate) {
        this.BucketStartDate = BucketStartDate;
    }

    @Formula(value="(select plb.end_date from pp_planning_level_bucket plb where (plb.planning_level_id = PLANNING_LEVEL_ID) and (plb.bucket_offset = BUCKET_OFFSET))")
    public Date getBucketEndDate() {
        return this.BucketEndDate;
    }

    public void setBucketEndDate(Date BucketEndDate) {
        this.BucketEndDate = BucketEndDate;
    }

}


