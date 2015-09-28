package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
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
 * MrpShortage generated by hbm2java
 */
@Entity
@Table(name="PP_MRP_SHORTAGE"
)
public class MrpShortage extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private MrpVersionControl MrpVersionControl;
     private Date RequiredDate;
     private BigDecimal ShortageQty;
     private Integer WarehouseId;
     private Integer CatalogId;

    public MrpShortage() {
    }

    public MrpShortage(SysClient SysClient, MrpVersionControl MrpVersionControl, Date RequiredDate, BigDecimal ShortageQty, Integer WarehouseId, Integer CatalogId) {
       this.SysClient = SysClient;
       this.MrpVersionControl = MrpVersionControl;
       this.RequiredDate = RequiredDate;
       this.ShortageQty = ShortageQty;
       this.WarehouseId = WarehouseId;
       this.CatalogId = CatalogId;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PP_MRP_SHORTAGE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
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
    @JoinColumn(name="MRP_VERSION_CONTROL_ID")
    public MrpVersionControl getMrpVersionControl() {
        return this.MrpVersionControl;
    }
    
    public void setMrpVersionControl(MrpVersionControl MrpVersionControl) {
        this.MrpVersionControl = MrpVersionControl;
    }

    
    @Column(name="REQUIRED_DATE", columnDefinition="TIMESTAMP")
    public Date getRequiredDate() {
        return this.RequiredDate;
    }
    
    public void setRequiredDate(Date RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    
    @Column(name="SHORTAGE_QTY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getShortageQty() {
        return this.ShortageQty;
    }
    
    public void setShortageQty(BigDecimal ShortageQty) {
        this.ShortageQty = ShortageQty;
    }

    
    @Column(name="WAREHOUSE_ID", columnDefinition="INTEGER")
    public Integer getWarehouseId() {
        return this.WarehouseId;
    }
    
    public void setWarehouseId(Integer WarehouseId) {
        this.WarehouseId = WarehouseId;
    }

    
    @Column(name="CATALOG_ID", columnDefinition="INTEGER")
    public Integer getCatalogId() {
        return this.CatalogId;
    }
    
    public void setCatalogId(Integer CatalogId) {
        this.CatalogId = CatalogId;
    }




}


