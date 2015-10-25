package com.mg.merp.planning.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
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
 * GenericItem generated by hbm2java
 */
@Entity
@Table(name = "PP_GENERIC_ITEM")
public class GenericItem extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private Catalog Catalog;

    private Measure Measure;

    private String GenericItemCode;

    private String GenericItemName;

    private boolean PlanningItemFlag;

    private short PlanningShelfLife;

    private short DemandFenceDays;

    private short DaysStockCover;

    private short LowLevelCode;

    public GenericItem() {
    }

    public GenericItem(Measure Measure) {
        this.Measure = Measure;
    }

    public GenericItem(SysClient SysClient, Catalog Catalog, Measure Measure, String GenericItemCode, String GenericItemName, boolean PlanningItemFlag, short PlanningShelfLife, short DemandFenceDays, short DaysStockCover, short LowLevelCode) {
        this.SysClient = SysClient;
        this.Catalog = Catalog;
        this.Measure = Measure;
        this.GenericItemCode = GenericItemCode;
        this.GenericItemName = GenericItemName;
        this.PlanningItemFlag = PlanningItemFlag;
        this.PlanningShelfLife = PlanningShelfLife;
        this.DemandFenceDays = DemandFenceDays;
        this.DaysStockCover = DaysStockCover;
        this.LowLevelCode = LowLevelCode;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PP_GENERIC_ITEM_ID_GEN")
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
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }

    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_ID", nullable = false, columnDefinition = "INTEGER")
    public Measure getMeasure() {
        return this.Measure;
    }

    public void setMeasure(Measure Measure) {
        this.Measure = Measure;
    }

    @Column(name = "GENERIC_ITEM_CODE", columnDefinition = "CHAR", length = 20)
    public String getGenericItemCode() {
        return this.GenericItemCode;
    }

    public void setGenericItemCode(String GenericItemCode) {
        this.GenericItemCode = GenericItemCode;
    }

    @Column(name = "GENERIC_ITEM_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getGenericItemName() {
        return this.GenericItemName;
    }

    public void setGenericItemName(String GenericItemName) {
        this.GenericItemName = GenericItemName;
    }

    @Column(name = "PLANNING_ITEM_FLAG", columnDefinition = "SMALLINT")
    public boolean isPlanningItemFlag() {
        return this.PlanningItemFlag;
    }

    public void setPlanningItemFlag(boolean PlanningItemFlag) {
        this.PlanningItemFlag = PlanningItemFlag;
    }

    @Column(name = "PLANNING_SHELF_LIFE", columnDefinition = "SMALLINT")
    public short getPlanningShelfLife() {
        return this.PlanningShelfLife;
    }

    public void setPlanningShelfLife(short PlanningShelfLife) {
        this.PlanningShelfLife = PlanningShelfLife;
    }

    @Column(name = "DEMAND_FENCE_DAYS", columnDefinition = "SMALLINT")
    public short getDemandFenceDays() {
        return this.DemandFenceDays;
    }

    public void setDemandFenceDays(short DemandFenceDays) {
        this.DemandFenceDays = DemandFenceDays;
    }

    @Column(name = "DAYS_STOCK_COVER", columnDefinition = "SMALLINT")
    public short getDaysStockCover() {
        return this.DaysStockCover;
    }

    public void setDaysStockCover(short DaysStockCover) {
        this.DaysStockCover = DaysStockCover;
    }

    @Column(name = "LOW_LEVEL_CODE", columnDefinition = "SMALLINT")
    public short getLowLevelCode() {
        return this.LowLevelCode;
    }

    public void setLowLevelCode(short LowLevelCode) {
        this.LowLevelCode = LowLevelCode;
    }
}

