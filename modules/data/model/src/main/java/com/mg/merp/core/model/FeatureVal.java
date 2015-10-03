package com.mg.merp.core.model;

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
 * FeatureVal generated by hbm2java
 */
@Entity
@Table(name = "FEATUREVAL")
public class FeatureVal extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private Feature Feature;

    private SysClient SysClient;

    private String Val;

    public FeatureVal() {
    }

    public FeatureVal(Feature Feature, SysClient SysClient, String Val) {
        this.Feature = Feature;
        this.SysClient = SysClient;
        this.Val = Val;
    }

    @SequenceGenerator(name = "generator", sequenceName = "FEATUREVAL_ID_GEN")
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
    @JoinColumn(name = "FEATURE_ID")
    public Feature getFeature() {
        return this.Feature;
    }

    public void setFeature(Feature Feature) {
        this.Feature = Feature;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "VAL", columnDefinition = "VARCHAR", length = 80)
    public String getVal() {
        return this.Val;
    }

    public void setVal(String Val) {
        this.Val = Val;
    }
}

