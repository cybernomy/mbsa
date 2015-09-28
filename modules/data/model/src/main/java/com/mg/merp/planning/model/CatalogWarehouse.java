package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.model.Warehouse;
import java.math.BigDecimal;
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
 * CatalogWarehouse generated by hbm2java
 */
@Entity
@Table(name="PP_CATALOG_WAREHOUSE"
)
public class CatalogWarehouse extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Catalog Catalog;
     private Warehouse Warehouse;
     private SysClient SysClient;
     private BigDecimal SafetyLevel;
     private Short MrpDampingDays;
     private Short DemandFenceDays;
     private Short OrderIntervalDays;

    public CatalogWarehouse() {
    }

    public CatalogWarehouse(Catalog Catalog, Warehouse Warehouse, SysClient SysClient, BigDecimal SafetyLevel, Short MrpDampingDays, Short DemandFenceDays, Short OrderIntervalDays) {
       this.Catalog = Catalog;
       this.Warehouse = Warehouse;
       this.SysClient = SysClient;
       this.SafetyLevel = SafetyLevel;
       this.MrpDampingDays = MrpDampingDays;
       this.DemandFenceDays = DemandFenceDays;
       this.OrderIntervalDays = OrderIntervalDays;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PP_CATALOG_WAREHOUSE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }
    
    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WAREHOUSE_ID")
    public Warehouse getWarehouse() {
        return this.Warehouse;
    }
    
    public void setWarehouse(Warehouse Warehouse) {
        this.Warehouse = Warehouse;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="SAFETY_LEVEL", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getSafetyLevel() {
        return this.SafetyLevel;
    }
    
    public void setSafetyLevel(BigDecimal SafetyLevel) {
        this.SafetyLevel = SafetyLevel;
    }

    
    @Column(name="MRP_DAMPING_DAYS", columnDefinition="SMALLINT")
    public Short getMrpDampingDays() {
        return this.MrpDampingDays;
    }
    
    public void setMrpDampingDays(Short MrpDampingDays) {
        this.MrpDampingDays = MrpDampingDays;
    }

    
    @Column(name="DEMAND_FENCE_DAYS", columnDefinition="SMALLINT")
    public Short getDemandFenceDays() {
        return this.DemandFenceDays;
    }
    
    public void setDemandFenceDays(Short DemandFenceDays) {
        this.DemandFenceDays = DemandFenceDays;
    }

    
    @Column(name="ORDER_INTERVAL_DAYS", columnDefinition="SMALLINT")
    public Short getOrderIntervalDays() {
        return this.OrderIntervalDays;
    }
    
    public void setOrderIntervalDays(Short OrderIntervalDays) {
        this.OrderIntervalDays = OrderIntervalDays;
    }




}


