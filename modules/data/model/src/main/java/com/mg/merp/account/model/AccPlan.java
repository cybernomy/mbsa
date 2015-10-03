package com.mg.merp.account.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Currency;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * AccPlan generated by hbm2java
 */
@Entity
@Table(name = "ACCPLAN")
public class AccPlan extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Folder Folder;

    private SysClient SysClient;

    private Currency Currency;

    private String UpAcc;

    private String Acc;

    private String AccName;

    private boolean IsAnl;

    private boolean IsBal;

    private boolean IsWork;

    private AnlForm AnlForm;

    private AccType AccType;

    private Set<AnlPlan> AnlPlans = new HashSet<AnlPlan>(0);

    public AccPlan() {
    }

    public AccPlan(String UpAcc, String Acc, String AccName, boolean IsAnl, boolean IsBal, boolean IsWork, AnlForm AnlForm, AccType AccType) {
        this.UpAcc = UpAcc;
        this.Acc = Acc;
        this.AccName = AccName;
        this.IsAnl = IsAnl;
        this.IsBal = IsBal;
        this.IsWork = IsWork;
        this.AnlForm = AnlForm;
        this.AccType = AccType;
    }

    public AccPlan(Folder Folder, SysClient SysClient, Currency Currency, String UpAcc, String Acc, String AccName, boolean IsAnl, boolean IsBal, boolean IsWork, AnlForm AnlForm, AccType AccType, Set<AnlPlan> AnlPlans) {
        this.Folder = Folder;
        this.SysClient = SysClient;
        this.Currency = Currency;
        this.UpAcc = UpAcc;
        this.Acc = Acc;
        this.AccName = AccName;
        this.IsAnl = IsAnl;
        this.IsBal = IsBal;
        this.IsWork = IsWork;
        this.AnlForm = AnlForm;
        this.AccType = AccType;
        this.AnlPlans = AnlPlans;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ACCPLAN_ID_GEN")
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
    @JoinColumn(name = "FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }

    public void setFolder(Folder Folder) {
        this.Folder = Folder;
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
    @JoinColumn(name = "CURRENCY_CODE")
    public Currency getCurrency() {
        return this.Currency;
    }

    public void setCurrency(Currency Currency) {
        this.Currency = Currency;
    }

    @Column(name = "UPACC", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getUpAcc() {
        return this.UpAcc;
    }

    public void setUpAcc(String UpAcc) {
        this.UpAcc = UpAcc;
    }

    @Column(name = "ACC", nullable = false, columnDefinition = "CHAR", length = 20)
    public String getAcc() {
        return this.Acc;
    }

    public void setAcc(String Acc) {
        this.Acc = Acc;
    }

    @Column(name = "ACCNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getAccName() {
        return this.AccName;
    }

    public void setAccName(String AccName) {
        this.AccName = AccName;
    }

    @Column(name = "ISANL", nullable = false, columnDefinition = "SMALLINT")
    public boolean isIsAnl() {
        return this.IsAnl;
    }

    public void setIsAnl(boolean IsAnl) {
        this.IsAnl = IsAnl;
    }

    @Column(name = "ISBAL", nullable = false, columnDefinition = "SMALLINT")
    public boolean isIsBal() {
        return this.IsBal;
    }

    public void setIsBal(boolean IsBal) {
        this.IsBal = IsBal;
    }

    @Column(name = "ISWORK", nullable = false, columnDefinition = "SMALLINT")
    public boolean isIsWork() {
        return this.IsWork;
    }

    public void setIsWork(boolean IsWork) {
        this.IsWork = IsWork;
    }

    @Column(name = "ANLFORM", nullable = false, columnDefinition = "SMALLINT")
    public AnlForm getAnlForm() {
        return this.AnlForm;
    }

    public void setAnlForm(AnlForm AnlForm) {
        this.AnlForm = AnlForm;
    }

    @Column(name = "ACCTYPE", nullable = false, columnDefinition = "SMALLINT")
    public AccType getAccType() {
        return this.AccType;
    }

    public void setAccType(AccType AccType) {
        this.AccType = AccType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "AccPlan")
    public Set<AnlPlan> getAnlPlans() {
        return this.AnlPlans;
    }

    public void setAnlPlans(Set<AnlPlan> AnlPlans) {
        this.AnlPlans = AnlPlans;
    }
}

