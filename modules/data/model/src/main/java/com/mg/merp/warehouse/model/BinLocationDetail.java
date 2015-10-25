package com.mg.merp.warehouse.model;

import com.mg.merp.core.model.SysClient;
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
 * BinLocationDetail generated by hbm2java
 */
@Entity
@Table(name = "WH_BIN_LOCATION_DETAIL")
public class BinLocationDetail extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private BinLocation BinLocation;

    private SysClient SysClient;

    private StockBatch StockBatch;

    private BigDecimal Quantity;

    private Integer DocSpecId;

    private short Kind;

    public BinLocationDetail() {
    }

    public BinLocationDetail(short Kind) {
        this.Kind = Kind;
    }

    public BinLocationDetail(BinLocation BinLocation, SysClient SysClient, StockBatch StockBatch, BigDecimal Quantity, Integer DocSpecId, short Kind) {
        this.BinLocation = BinLocation;
        this.SysClient = SysClient;
        this.StockBatch = StockBatch;
        this.Quantity = Quantity;
        this.DocSpecId = DocSpecId;
        this.Kind = Kind;
    }

    @SequenceGenerator(name = "generator", sequenceName = "WH_BIN_LOCATION_DETAIL_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIN_LOCATION_ID")
    public BinLocation getBinLocation() {
        return this.BinLocation;
    }

    public void setBinLocation(BinLocation BinLocation) {
        this.BinLocation = BinLocation;
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
    @JoinColumn(name = "STOCKBATCH_ID")
    public StockBatch getStockBatch() {
        return this.StockBatch;
    }

    public void setStockBatch(StockBatch StockBatch) {
        this.StockBatch = StockBatch;
    }

    @Column(name = "QUANTITY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    @Column(name = "DOCSPEC_ID", columnDefinition = "INTEGER")
    public Integer getDocSpecId() {
        return this.DocSpecId;
    }

    public void setDocSpecId(Integer DocSpecId) {
        this.DocSpecId = DocSpecId;
    }

    @Column(name = "KIND", nullable = false, columnDefinition = "SMALLINT")
    public short getKind() {
        return this.Kind;
    }

    public void setKind(short Kind) {
        this.Kind = Kind;
    }
}

