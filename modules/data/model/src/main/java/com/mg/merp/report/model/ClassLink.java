package com.mg.merp.report.model;

import com.mg.merp.core.model.SysClass;
import com.mg.merp.core.model.SysClient;
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
 * ClassLink generated by hbm2java
 */
@Entity
@Table(name = "RPT_CLASS_LINK")
public class ClassLink extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClass SysClass;

    private RptMain Report;

    private SysClient SysClient;

    public ClassLink() {
    }

    public ClassLink(SysClass SysClass, RptMain Report, SysClient SysClient) {
        this.SysClass = SysClass;
        this.Report = Report;
        this.SysClient = SysClient;
    }

    @SequenceGenerator(name = "generator", sequenceName = "RPT_CLASS_LINK_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID", nullable = false)
    public SysClass getSysClass() {
        return this.SysClass;
    }

    public void setSysClass(SysClass SysClass) {
        this.SysClass = SysClass;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RPT_ID", nullable = false)
    public RptMain getReport() {
        return this.Report;
    }

    public void setReport(RptMain Report) {
        this.Report = Report;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }
}

