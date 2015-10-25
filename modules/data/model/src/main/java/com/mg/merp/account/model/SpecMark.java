package com.mg.merp.account.model;

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
 * SpecMark generated by hbm2java
 */
@Entity
@Table(name = "SPECMARK")
public class SpecMark extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Code;

    private String SmName;

    private String UpCode;

    public SpecMark() {
    }

    public SpecMark(String Code, String SmName, String UpCode) {
        this.Code = Code;
        this.SmName = SmName;
        this.UpCode = UpCode;
    }

    public SpecMark(SysClient SysClient, String Code, String SmName, String UpCode) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.SmName = SmName;
        this.UpCode = UpCode;
    }

    @SequenceGenerator(name = "generator", sequenceName = "SPECMARK_ID_GEN")
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

    @Column(name = "CODE", nullable = false, columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "SMNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getSmName() {
        return this.SmName;
    }

    public void setSmName(String SmName) {
        this.SmName = SmName;
    }

    @Column(name = "UPCODE", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getUpCode() {
        return this.UpCode;
    }

    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }
}

