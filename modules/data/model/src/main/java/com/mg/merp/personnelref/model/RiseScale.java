package com.mg.merp.personnelref.model;

import com.mg.merp.core.model.SysClient;
import java.util.Date;
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
 * RiseScale generated by hbm2java
 */
@Entity
@Table(name = "PREF_RISE_SCALE")
public class RiseScale extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Rise Rise;

    private SysClient SysClient;

    private Integer ScaleNumber;

    private String SName;

    private Date BeginDate;

    private Set<RisePercent> SetOfPrefRisePercent = new HashSet<RisePercent>(0);

    public RiseScale() {
    }

    public RiseScale(Rise Rise, SysClient SysClient, Integer ScaleNumber, String SName, Date BeginDate, Set<RisePercent> SetOfPrefRisePercent) {
        this.Rise = Rise;
        this.SysClient = SysClient;
        this.ScaleNumber = ScaleNumber;
        this.SName = SName;
        this.BeginDate = BeginDate;
        this.SetOfPrefRisePercent = SetOfPrefRisePercent;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PREF_RISE_SCALE_ID_GEN")
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
    @JoinColumn(name = "RISE_ID")
    public Rise getRise() {
        return this.Rise;
    }

    public void setRise(Rise Rise) {
        this.Rise = Rise;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "SCALE_NUMBER", columnDefinition = "INTEGER")
    public Integer getScaleNumber() {
        return this.ScaleNumber;
    }

    public void setScaleNumber(Integer ScaleNumber) {
        this.ScaleNumber = ScaleNumber;
    }

    @Column(name = "SNAME", columnDefinition = "VARCHAR", length = 80)
    public String getSName() {
        return this.SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    @Column(name = "BEGINDATE", columnDefinition = "TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }

    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "RiseScale")
    public Set<RisePercent> getSetOfPrefRisePercent() {
        return this.SetOfPrefRisePercent;
    }

    public void setSetOfPrefRisePercent(Set<RisePercent> SetOfPrefRisePercent) {
        this.SetOfPrefRisePercent = SetOfPrefRisePercent;
    }
}

