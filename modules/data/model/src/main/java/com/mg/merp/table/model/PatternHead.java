package com.mg.merp.table.model;

import com.mg.merp.core.model.SysClient;
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
 * PatternHead generated by hbm2java
 */
@Entity
@Table(name = "TAB_PATTERN_HEAD")
public class PatternHead extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Code;

    private String Name;

    private Integer Duration;

    private PatternKind PatternKind;

    private Set<ScheduleHead> ScheduleHeads = new HashSet<ScheduleHead>(0);

    private Set<PatternSpec> PatternSpecs = new HashSet<PatternSpec>(0);

    public PatternHead() {
    }

    public PatternHead(String Code) {
        this.Code = Code;
    }

    public PatternHead(SysClient SysClient, String Code, String Name, Integer Duration, PatternKind PatternKind, Set<ScheduleHead> ScheduleHeads, Set<PatternSpec> PatternSpecs) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.Name = Name;
        this.Duration = Duration;
        this.PatternKind = PatternKind;
        this.ScheduleHeads = ScheduleHeads;
        this.PatternSpecs = PatternSpecs;
    }

    @SequenceGenerator(name = "generator", sequenceName = "TAB_PATTERN_HEAD_ID_GEN")
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

    @Column(name = "PCODE", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "PNAME", columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "DURATION", columnDefinition = "INTEGER")
    public Integer getDuration() {
        return this.Duration;
    }

    public void setDuration(Integer Duration) {
        this.Duration = Duration;
    }

    @Column(name = "PATTERN_KIND", columnDefinition = "SMALLINT")
    public PatternKind getPatternKind() {
        return this.PatternKind;
    }

    public void setPatternKind(PatternKind PatternKind) {
        this.PatternKind = PatternKind;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "DefaultPatternHead")
    public Set<ScheduleHead> getScheduleHeads() {
        return this.ScheduleHeads;
    }

    public void setScheduleHeads(Set<ScheduleHead> ScheduleHeads) {
        this.ScheduleHeads = ScheduleHeads;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "PatternHead")
    public Set<PatternSpec> getPatternSpecs() {
        return this.PatternSpecs;
    }

    public void setPatternSpecs(Set<PatternSpec> PatternSpecs) {
        this.PatternSpecs = PatternSpecs;
    }
}

