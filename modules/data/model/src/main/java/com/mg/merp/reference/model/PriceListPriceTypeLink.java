package com.mg.merp.reference.model;

import com.mg.merp.baiengine.model.Repository;
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
 * PriceListPriceTypeLink generated by hbm2java
 */
@Entity
@Table(name = "PRICELIST_PRICETYPE_LINK")
public class PriceListPriceTypeLink extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private Repository AlgRepository;

    private PriceType PriceType;

    private SysClient SysClient;

    private PriceListHead PriceListHead;

    private Short Priority;

    private BigDecimal RelativePercent;

    public PriceListPriceTypeLink() {
    }

    public PriceListPriceTypeLink(Repository AlgRepository, PriceType PriceType, SysClient SysClient, PriceListHead PriceListHead, Short Priority, BigDecimal RelativePercent) {
        this.AlgRepository = AlgRepository;
        this.PriceType = PriceType;
        this.SysClient = SysClient;
        this.PriceListHead = PriceListHead;
        this.Priority = Priority;
        this.RelativePercent = RelativePercent;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PRICELIST_PT_LINK_ID_GEN")
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
    @JoinColumn(name = "ALG_ID")
    public Repository getAlgRepository() {
        return this.AlgRepository;
    }

    public void setAlgRepository(Repository AlgRepository) {
        this.AlgRepository = AlgRepository;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRICETYPE_ID")
    public PriceType getPriceType() {
        return this.PriceType;
    }

    public void setPriceType(PriceType PriceType) {
        this.PriceType = PriceType;
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
    @JoinColumn(name = "PRICELIST_ID")
    public PriceListHead getPriceListHead() {
        return this.PriceListHead;
    }

    public void setPriceListHead(PriceListHead PriceListHead) {
        this.PriceListHead = PriceListHead;
    }

    @Column(name = "PRIORITY", columnDefinition = "SMALLINT")
    public Short getPriority() {
        return this.Priority;
    }

    public void setPriority(Short Priority) {
        this.Priority = Priority;
    }

    @Column(name = "RELATIVE_PERCENT", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getRelativePercent() {
        return this.RelativePercent;
    }

    public void setRelativePercent(BigDecimal RelativePercent) {
        this.RelativePercent = RelativePercent;
    }
}

