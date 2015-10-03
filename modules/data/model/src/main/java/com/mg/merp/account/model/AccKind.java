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
 * AccKind generated by hbm2java
 */
@Entity
@Table(name = "ACC_ACCKIND")
public class AccKind extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private SysClient SysClient;

    private String Code;

    private String Name;

    private Integer Priority;

    public AccKind() {
    }

    public AccKind(String Name) {
        this.Name = Name;
    }

    public AccKind(SysClient SysClient, String Code, String Name, Integer Priority) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.Name = Name;
        this.Priority = Priority;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ACC_ACCKIND_ID_GEN")
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
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "KCODE", columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "KNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "PRIORITY", columnDefinition = "INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }

    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }
}

