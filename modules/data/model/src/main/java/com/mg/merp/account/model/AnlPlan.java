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
 * AnlPlan generated by hbm2java
 */
@Entity
@Table(name = "ANLPLAN")
public class AnlPlan extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private AnlPlan Parent;

    private SysClient SysClient;

    private AccPlan AccPlan;

    private String UpCode;

    private String Code;

    private String AnlName;

    private short AnlLevel;

    private boolean UseStdForm;

    private AnlStdForm StdForm;

    public AnlPlan() {
    }

    public AnlPlan(String UpCode, String Code, String AnlName, short AnlLevel, boolean UseStdForm) {
        this.UpCode = UpCode;
        this.Code = Code;
        this.AnlName = AnlName;
        this.AnlLevel = AnlLevel;
        this.UseStdForm = UseStdForm;
    }

    public AnlPlan(AnlPlan Parent, SysClient SysClient, AccPlan AccPlan, String UpCode, String Code, String AnlName, short AnlLevel, boolean UseStdForm, AnlStdForm StdForm) {
        this.Parent = Parent;
        this.SysClient = SysClient;
        this.AccPlan = AccPlan;
        this.UpCode = UpCode;
        this.Code = Code;
        this.AnlName = AnlName;
        this.AnlLevel = AnlLevel;
        this.UseStdForm = UseStdForm;
        this.StdForm = StdForm;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ANLPLAN_ID_GEN")
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
    @JoinColumn(name = "PARENT_ID")
    public AnlPlan getParent() {
        return this.Parent;
    }

    public void setParent(AnlPlan Parent) {
        this.Parent = Parent;
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
    @JoinColumn(name = "ACCPLAN_ID")
    public AccPlan getAccPlan() {
        return this.AccPlan;
    }

    public void setAccPlan(AccPlan AccPlan) {
        this.AccPlan = AccPlan;
    }

    @Column(name = "UPCODE", nullable = false, columnDefinition = "CHAR", length = 10)
    public String getUpCode() {
        return this.UpCode;
    }

    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    @Column(name = "CODE", nullable = false, columnDefinition = "CHAR", length = 10)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "ANLNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getAnlName() {
        return this.AnlName;
    }

    public void setAnlName(String AnlName) {
        this.AnlName = AnlName;
    }

    @Column(name = "ANLLEVEL", nullable = false, columnDefinition = "SMALLINT")
    public short getAnlLevel() {
        return this.AnlLevel;
    }

    public void setAnlLevel(short AnlLevel) {
        this.AnlLevel = AnlLevel;
    }

    @Column(name = "USESTDFORM", nullable = false, columnDefinition = "SMALLINT")
    public boolean isUseStdForm() {
        return this.UseStdForm;
    }

    public void setUseStdForm(boolean UseStdForm) {
        this.UseStdForm = UseStdForm;
    }

    @Column(name = "STDFORM", columnDefinition = "SMALLINT")
    public AnlStdForm getStdForm() {
        return this.StdForm;
    }

    public void setStdForm(AnlStdForm StdForm) {
        this.StdForm = StdForm;
    }
}

