package com.mg.merp.planning.model;

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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * MrpInputs generated by hbm2java
 */
@Entity
@Table(name = "PP_MRP_INPUTS")
public class MrpInputs extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private MrpVersionControl MrpVersionControl;

    private String PpReference;

    private Integer ReferenceId;

    private Date RequiredDate;

    private BigDecimal MrpQuantity;

    private MRPOrderType MrpOrderType;

    private MRPSource MrpSource;

    private boolean FixedInput;

    private Date BatchDate;

    private Integer WarehouseId;

    private Integer CatalogId;

    public MrpInputs() {
    }

    public MrpInputs(SysClient SysClient, MrpVersionControl MrpVersionControl, String PpReference, Integer ReferenceId, Date RequiredDate, BigDecimal MrpQuantity, MRPOrderType MrpOrderType, MRPSource MrpSource, boolean FixedInput, Date BatchDate, Integer WarehouseId, Integer CatalogId) {
        this.SysClient = SysClient;
        this.MrpVersionControl = MrpVersionControl;
        this.PpReference = PpReference;
        this.ReferenceId = ReferenceId;
        this.RequiredDate = RequiredDate;
        this.MrpQuantity = MrpQuantity;
        this.MrpOrderType = MrpOrderType;
        this.MrpSource = MrpSource;
        this.FixedInput = FixedInput;
        this.BatchDate = BatchDate;
        this.WarehouseId = WarehouseId;
        this.CatalogId = CatalogId;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PP_MRP_INPUTS_ID_GEN")
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
    @JoinColumn(name = "MRP_VERSION_CONTROL_ID")
    public MrpVersionControl getMrpVersionControl() {
        return this.MrpVersionControl;
    }

    public void setMrpVersionControl(MrpVersionControl MrpVersionControl) {
        this.MrpVersionControl = MrpVersionControl;
    }

    @Column(name = "PP_REFERENCE", columnDefinition = "VARCHAR", length = 80)
    public String getPpReference() {
        return this.PpReference;
    }

    public void setPpReference(String PpReference) {
        this.PpReference = PpReference;
    }

    @Column(name = "REFERENCE_ID", columnDefinition = "INTEGER")
    public Integer getReferenceId() {
        return this.ReferenceId;
    }

    public void setReferenceId(Integer ReferenceId) {
        this.ReferenceId = ReferenceId;
    }

    @Column(name = "REQUIRED_DATE", columnDefinition = "TIMESTAMP")
    public Date getRequiredDate() {
        return this.RequiredDate;
    }

    public void setRequiredDate(Date RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    @Column(name = "MRP_QUANTITY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getMrpQuantity() {
        return this.MrpQuantity;
    }

    public void setMrpQuantity(BigDecimal MrpQuantity) {
        this.MrpQuantity = MrpQuantity;
    }

    @Column(name = "MRP_ORDER_TYPE")
    @Enumerated(EnumType.ORDINAL)
    public MRPOrderType getMrpOrderType() {
        return this.MrpOrderType;
    }

    public void setMrpOrderType(MRPOrderType MrpOrderType) {
        this.MrpOrderType = MrpOrderType;
    }

    @Column(name = "MRP_SOURCE")
    @Enumerated(EnumType.ORDINAL)
    public MRPSource getMrpSource() {
        return this.MrpSource;
    }

    public void setMrpSource(MRPSource MrpSource) {
        this.MrpSource = MrpSource;
    }

    @Column(name = "FIXED_INPUT", columnDefinition = "SMALLINT")
    public boolean isFixedInput() {
        return this.FixedInput;
    }

    public void setFixedInput(boolean FixedInput) {
        this.FixedInput = FixedInput;
    }

    @Column(name = "BATCH_DATE", columnDefinition = "TIMESTAMP")
    public Date getBatchDate() {
        return this.BatchDate;
    }

    public void setBatchDate(Date BatchDate) {
        this.BatchDate = BatchDate;
    }

    @Column(name = "WAREHOUSE_ID", columnDefinition = "INTEGER")
    public Integer getWarehouseId() {
        return this.WarehouseId;
    }

    public void setWarehouseId(Integer WarehouseId) {
        this.WarehouseId = WarehouseId;
    }

    @Column(name = "CATALOG_ID", columnDefinition = "INTEGER")
    public Integer getCatalogId() {
        return this.CatalogId;
    }

    public void setCatalogId(Integer CatalogId) {
        this.CatalogId = CatalogId;
    }
}

