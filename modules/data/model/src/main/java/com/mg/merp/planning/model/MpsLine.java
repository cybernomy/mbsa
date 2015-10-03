package com.mg.merp.planning.model;

import com.mg.merp.core.model.SysClient;
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
 * MpsLine generated by hbm2java
 */
@Entity
@Table(name = "PP_MPS_LINE")
public class MpsLine extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private InventoryForecast InventoryForecast;

    private Mps Mps;

    private ForecastVersion ForecastVersion;

    private SysClient SysClient;

    private GenericItem PlanningItem;

    private Measure Measure;

    private BigDecimal AdjustmentQty;

    private Short BucketOffset;

    private Date BucketOffsetDate;

    private Date DemandFenceDate;

    private BigDecimal DemandQty;

    private Integer MpsSequence;

    private Integer OutputMpsSequence;

    private BigDecimal PlannedQty;

    private BigDecimal DependantDemand;

    private Short LevelCode;

    private BigDecimal ProductionDemandQty;

    private BigDecimal ProductionProfileQty;

    private BigDecimal ProductionQty;

    private BigDecimal PurchaseForecastQty;

    private BigDecimal PurchaseOrderQty;

    private BigDecimal PurchaseQty;

    private BigDecimal QtyAvailable;

    private BigDecimal SalesForecastQty;

    private BigDecimal SalesOrderQty;

    private BigDecimal SalesQty;

    private BigDecimal TransfersInQty;

    private BigDecimal TransfersOutQty;

    private BigDecimal LiveProductionDemand;

    private BigDecimal SafetyLevelQty;

    private boolean FirmPlanSuggested;

    private boolean MpsOrdered;

    private Date BucketEndDate;

    public MpsLine() {
    }

    public MpsLine(InventoryForecast InventoryForecast, Mps Mps, ForecastVersion ForecastVersion, SysClient SysClient, GenericItem PlanningItem, Measure Measure, BigDecimal AdjustmentQty, Short BucketOffset, Date BucketOffsetDate, Date DemandFenceDate, BigDecimal DemandQty, Integer MpsSequence, Integer OutputMpsSequence, BigDecimal PlannedQty, BigDecimal DependantDemand, Short LevelCode, BigDecimal ProductionDemandQty, BigDecimal ProductionProfileQty, BigDecimal ProductionQty, BigDecimal PurchaseForecastQty, BigDecimal PurchaseOrderQty, BigDecimal PurchaseQty, BigDecimal QtyAvailable, BigDecimal SalesForecastQty, BigDecimal SalesOrderQty, BigDecimal SalesQty, BigDecimal TransfersInQty, BigDecimal TransfersOutQty, BigDecimal LiveProductionDemand, BigDecimal SafetyLevelQty, boolean FirmPlanSuggested, boolean MpsOrdered) {
        this.InventoryForecast = InventoryForecast;
        this.Mps = Mps;
        this.ForecastVersion = ForecastVersion;
        this.SysClient = SysClient;
        this.PlanningItem = PlanningItem;
        this.Measure = Measure;
        this.AdjustmentQty = AdjustmentQty;
        this.BucketOffset = BucketOffset;
        this.BucketOffsetDate = BucketOffsetDate;
        this.DemandFenceDate = DemandFenceDate;
        this.DemandQty = DemandQty;
        this.MpsSequence = MpsSequence;
        this.OutputMpsSequence = OutputMpsSequence;
        this.PlannedQty = PlannedQty;
        this.DependantDemand = DependantDemand;
        this.LevelCode = LevelCode;
        this.ProductionDemandQty = ProductionDemandQty;
        this.ProductionProfileQty = ProductionProfileQty;
        this.ProductionQty = ProductionQty;
        this.PurchaseForecastQty = PurchaseForecastQty;
        this.PurchaseOrderQty = PurchaseOrderQty;
        this.PurchaseQty = PurchaseQty;
        this.QtyAvailable = QtyAvailable;
        this.SalesForecastQty = SalesForecastQty;
        this.SalesOrderQty = SalesOrderQty;
        this.SalesQty = SalesQty;
        this.TransfersInQty = TransfersInQty;
        this.TransfersOutQty = TransfersOutQty;
        this.LiveProductionDemand = LiveProductionDemand;
        this.SafetyLevelQty = SafetyLevelQty;
        this.FirmPlanSuggested = FirmPlanSuggested;
        this.MpsOrdered = MpsOrdered;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PP_MPS_LINE_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTORY_FORECAST_ID")
    public InventoryForecast getInventoryForecast() {
        return this.InventoryForecast;
    }

    public void setInventoryForecast(InventoryForecast InventoryForecast) {
        this.InventoryForecast = InventoryForecast;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MPS_ID")
    public Mps getMps() {
        return this.Mps;
    }

    public void setMps(Mps Mps) {
        this.Mps = Mps;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORECAST_VERSION_ID")
    public ForecastVersion getForecastVersion() {
        return this.ForecastVersion;
    }

    public void setForecastVersion(ForecastVersion ForecastVersion) {
        this.ForecastVersion = ForecastVersion;
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
    @JoinColumn(name = "PLANNING_ITEM_ID")
    public GenericItem getPlanningItem() {
        return this.PlanningItem;
    }

    public void setPlanningItem(GenericItem PlanningItem) {
        this.PlanningItem = PlanningItem;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_ID")
    public Measure getMeasure() {
        return this.Measure;
    }

    public void setMeasure(Measure Measure) {
        this.Measure = Measure;
    }

    @Column(name = "ADJUSTMENT_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getAdjustmentQty() {
        return this.AdjustmentQty;
    }

    public void setAdjustmentQty(BigDecimal AdjustmentQty) {
        this.AdjustmentQty = AdjustmentQty;
    }

    @Column(name = "BUCKET_OFFSET", columnDefinition = "SMALLINT")
    public Short getBucketOffset() {
        return this.BucketOffset;
    }

    public void setBucketOffset(Short BucketOffset) {
        this.BucketOffset = BucketOffset;
    }

    @Column(name = "BUCKET_OFFSET_DATE", columnDefinition = "TIMESTAMP")
    public Date getBucketOffsetDate() {
        return this.BucketOffsetDate;
    }

    public void setBucketOffsetDate(Date BucketOffsetDate) {
        this.BucketOffsetDate = BucketOffsetDate;
    }

    @Column(name = "DEMAND_FENCE_DATE", columnDefinition = "TIMESTAMP")
    public Date getDemandFenceDate() {
        return this.DemandFenceDate;
    }

    public void setDemandFenceDate(Date DemandFenceDate) {
        this.DemandFenceDate = DemandFenceDate;
    }

    @Column(name = "DEMAND_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getDemandQty() {
        return this.DemandQty;
    }

    public void setDemandQty(BigDecimal DemandQty) {
        this.DemandQty = DemandQty;
    }

    @Column(name = "MPS_SEQUENCE", columnDefinition = "INTEGER")
    public Integer getMpsSequence() {
        return this.MpsSequence;
    }

    public void setMpsSequence(Integer MpsSequence) {
        this.MpsSequence = MpsSequence;
    }

    @Column(name = "OUTPUT_MPS_SEQUENCE", columnDefinition = "INTEGER")
    public Integer getOutputMpsSequence() {
        return this.OutputMpsSequence;
    }

    public void setOutputMpsSequence(Integer OutputMpsSequence) {
        this.OutputMpsSequence = OutputMpsSequence;
    }

    @Column(name = "PLANNED_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getPlannedQty() {
        return this.PlannedQty;
    }

    public void setPlannedQty(BigDecimal PlannedQty) {
        this.PlannedQty = PlannedQty;
    }

    @Column(name = "PP_DEPENDANT_DEMAND", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getDependantDemand() {
        return this.DependantDemand;
    }

    public void setDependantDemand(BigDecimal DependantDemand) {
        this.DependantDemand = DependantDemand;
    }

    @Column(name = "PP_LEVEL_CODE", columnDefinition = "SMALLINT")
    public Short getLevelCode() {
        return this.LevelCode;
    }

    public void setLevelCode(Short LevelCode) {
        this.LevelCode = LevelCode;
    }

    @Column(name = "PRODUCTION_DEMAND_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getProductionDemandQty() {
        return this.ProductionDemandQty;
    }

    public void setProductionDemandQty(BigDecimal ProductionDemandQty) {
        this.ProductionDemandQty = ProductionDemandQty;
    }

    @Column(name = "PRODUCTION_PROFILE_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getProductionProfileQty() {
        return this.ProductionProfileQty;
    }

    public void setProductionProfileQty(BigDecimal ProductionProfileQty) {
        this.ProductionProfileQty = ProductionProfileQty;
    }

    @Column(name = "PRODUCTION_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getProductionQty() {
        return this.ProductionQty;
    }

    public void setProductionQty(BigDecimal ProductionQty) {
        this.ProductionQty = ProductionQty;
    }

    @Column(name = "PURCHASE_FORECAST_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getPurchaseForecastQty() {
        return this.PurchaseForecastQty;
    }

    public void setPurchaseForecastQty(BigDecimal PurchaseForecastQty) {
        this.PurchaseForecastQty = PurchaseForecastQty;
    }

    @Column(name = "PURCHASE_ORDER_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getPurchaseOrderQty() {
        return this.PurchaseOrderQty;
    }

    public void setPurchaseOrderQty(BigDecimal PurchaseOrderQty) {
        this.PurchaseOrderQty = PurchaseOrderQty;
    }

    @Column(name = "PURCHASE_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getPurchaseQty() {
        return this.PurchaseQty;
    }

    public void setPurchaseQty(BigDecimal PurchaseQty) {
        this.PurchaseQty = PurchaseQty;
    }

    @Column(name = "QTY_AVAILABLE", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getQtyAvailable() {
        return this.QtyAvailable;
    }

    public void setQtyAvailable(BigDecimal QtyAvailable) {
        this.QtyAvailable = QtyAvailable;
    }

    @Column(name = "SALES_FORECAST_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getSalesForecastQty() {
        return this.SalesForecastQty;
    }

    public void setSalesForecastQty(BigDecimal SalesForecastQty) {
        this.SalesForecastQty = SalesForecastQty;
    }

    @Column(name = "SALES_ORDER_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getSalesOrderQty() {
        return this.SalesOrderQty;
    }

    public void setSalesOrderQty(BigDecimal SalesOrderQty) {
        this.SalesOrderQty = SalesOrderQty;
    }

    @Column(name = "SALES_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getSalesQty() {
        return this.SalesQty;
    }

    public void setSalesQty(BigDecimal SalesQty) {
        this.SalesQty = SalesQty;
    }

    @Column(name = "TRANSFERS_IN_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getTransfersInQty() {
        return this.TransfersInQty;
    }

    public void setTransfersInQty(BigDecimal TransfersInQty) {
        this.TransfersInQty = TransfersInQty;
    }

    @Column(name = "TRANSFERS_OUT_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getTransfersOutQty() {
        return this.TransfersOutQty;
    }

    public void setTransfersOutQty(BigDecimal TransfersOutQty) {
        this.TransfersOutQty = TransfersOutQty;
    }

    @Column(name = "LIVE_PRODUCTION_DEMAND", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getLiveProductionDemand() {
        return this.LiveProductionDemand;
    }

    public void setLiveProductionDemand(BigDecimal LiveProductionDemand) {
        this.LiveProductionDemand = LiveProductionDemand;
    }

    @Column(name = "SAFETY_LEVEL_QTY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getSafetyLevelQty() {
        return this.SafetyLevelQty;
    }

    public void setSafetyLevelQty(BigDecimal SafetyLevelQty) {
        this.SafetyLevelQty = SafetyLevelQty;
    }

    @Column(name = "FIRM_PLAN_SUGGESTED", columnDefinition = "SMALLINT")
    public boolean isFirmPlanSuggested() {
        return this.FirmPlanSuggested;
    }

    public void setFirmPlanSuggested(boolean FirmPlanSuggested) {
        this.FirmPlanSuggested = FirmPlanSuggested;
    }

    @Column(name = "MPS_ORDERED", columnDefinition = "SMALLINT")
    public boolean isMpsOrdered() {
        return this.MpsOrdered;
    }

    public void setMpsOrdered(boolean MpsOrdered) {
        this.MpsOrdered = MpsOrdered;
    }

    @Formula(value = "(select b.end_date from pp_mps mps left join pp_planning_level_bucket b on mps.planning_level_id = b.planning_level_id and b.bucket_offset = BUCKET_OFFSET where mps.id = MPS_ID)")
    public Date getBucketEndDate() {
        return this.BucketEndDate;
    }

    public void setBucketEndDate(Date BucketEndDate) {
        this.BucketEndDate = BucketEndDate;
    }
}

