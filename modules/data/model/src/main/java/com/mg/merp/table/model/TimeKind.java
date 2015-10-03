package com.mg.merp.table.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * TimeKind generated by hbm2java
 */
@Entity
@Table(name="TAB_TIME_KIND"
)
public class TimeKind extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String Code;
     private String Name;
     private Integer Priority;
     private boolean IsWholeDay;
     private String MnemoCode;
     private Integer FontColor;
     private Integer BackGroundColor;
     private Set<ScheduleHead> SetOfTabScheduleHead = new HashSet<ScheduleHead>(0);
     private Set<TimeBoardSpec> SetOfTabTimeBoardSpec = new HashSet<TimeBoardSpec>(0);
     private Set<TableConfig> SetOfTabConfig = new HashSet<TableConfig>(0);
     private Set<TableConfig> SetOfTabConfig_1 = new HashSet<TableConfig>(0);
     private Set<PatternSpec> SetOfTabPatternSpec = new HashSet<PatternSpec>(0);
     private Set<ScheduleSpec> SetOfTabScheduleSpec = new HashSet<ScheduleSpec>(0);

    public TimeKind() {
    }

	
    public TimeKind(String Code) {
        this.Code = Code;
    }
    public TimeKind(SysClient SysClient, String Code, String Name, Integer Priority, boolean IsWholeDay, String MnemoCode, Integer FontColor, Integer BackGroundColor, Set<ScheduleHead> SetOfTabScheduleHead, Set<TimeBoardSpec> SetOfTabTimeBoardSpec, Set<TableConfig> SetOfTabConfig, Set<TableConfig> SetOfTabConfig_1, Set<PatternSpec> SetOfTabPatternSpec, Set<ScheduleSpec> SetOfTabScheduleSpec) {
       this.SysClient = SysClient;
       this.Code = Code;
       this.Name = Name;
       this.Priority = Priority;
       this.IsWholeDay = IsWholeDay;
       this.MnemoCode = MnemoCode;
       this.FontColor = FontColor;
       this.BackGroundColor = BackGroundColor;
       this.SetOfTabScheduleHead = SetOfTabScheduleHead;
       this.SetOfTabTimeBoardSpec = SetOfTabTimeBoardSpec;
       this.SetOfTabConfig = SetOfTabConfig;
       this.SetOfTabConfig_1 = SetOfTabConfig_1;
       this.SetOfTabPatternSpec = SetOfTabPatternSpec;
       this.SetOfTabScheduleSpec = SetOfTabScheduleSpec;
    }
   
     @SequenceGenerator(name="generator", sequenceName="TAB_TIME_KIND_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="KCODE", unique=true, nullable=false, columnDefinition="CHAR", length=20)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="KNAME", columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="PRIORITY", columnDefinition="INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }
    
    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }

    
    @Column(name="IS_WHOLE_DAY", columnDefinition="SMALLINT")
    public boolean isIsWholeDay() {
        return this.IsWholeDay;
    }
    
    public void setIsWholeDay(boolean IsWholeDay) {
        this.IsWholeDay = IsWholeDay;
    }

    
    @Column(name="MNEMOCODE", columnDefinition="CHAR", length=5)
    public String getMnemoCode() {
        return this.MnemoCode;
    }
    
    public void setMnemoCode(String MnemoCode) {
        this.MnemoCode = MnemoCode;
    }

    
    @Column(name="FONT_COLOR", columnDefinition="INTEGER")
    public Integer getFontColor() {
        return this.FontColor;
    }
    
    public void setFontColor(Integer FontColor) {
        this.FontColor = FontColor;
    }

    
    @Column(name="BACKGROUND_COLOR", columnDefinition="INTEGER")
    public Integer getBackGroundColor() {
        return this.BackGroundColor;
    }
    
    public void setBackGroundColor(Integer BackGroundColor) {
        this.BackGroundColor = BackGroundColor;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="HOLIDAY_WORK_TIME_KIND_ID", updatable=false)
    public Set<ScheduleHead> getSetOfTabScheduleHead() {
        return this.SetOfTabScheduleHead;
    }
    
    public void setSetOfTabScheduleHead(Set<ScheduleHead> SetOfTabScheduleHead) {
        this.SetOfTabScheduleHead = SetOfTabScheduleHead;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_KIND_ID", updatable=false)
    public Set<TimeBoardSpec> getSetOfTabTimeBoardSpec() {
        return this.SetOfTabTimeBoardSpec;
    }
    
    public void setSetOfTabTimeBoardSpec(Set<TimeBoardSpec> SetOfTabTimeBoardSpec) {
        this.SetOfTabTimeBoardSpec = SetOfTabTimeBoardSpec;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="WORK_TIME_KIND_ID", updatable=false)
    public Set<TableConfig> getSetOfTabConfig() {
        return this.SetOfTabConfig;
    }
    
    public void setSetOfTabConfig(Set<TableConfig> SetOfTabConfig) {
        this.SetOfTabConfig = SetOfTabConfig;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="HOLIDAY_TIME_KIND_ID", updatable=false)
    public Set<TableConfig> getSetOfTabConfig_1() {
        return this.SetOfTabConfig_1;
    }
    
    public void setSetOfTabConfig_1(Set<TableConfig> SetOfTabConfig_1) {
        this.SetOfTabConfig_1 = SetOfTabConfig_1;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_KIND_ID", updatable=false)
    public Set<PatternSpec> getSetOfTabPatternSpec() {
        return this.SetOfTabPatternSpec;
    }
    
    public void setSetOfTabPatternSpec(Set<PatternSpec> SetOfTabPatternSpec) {
        this.SetOfTabPatternSpec = SetOfTabPatternSpec;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_KIND_ID", updatable=false)
    public Set<ScheduleSpec> getSetOfTabScheduleSpec() {
        return this.SetOfTabScheduleSpec;
    }
    
    public void setSetOfTabScheduleSpec(Set<ScheduleSpec> SetOfTabScheduleSpec) {
        this.SetOfTabScheduleSpec = SetOfTabScheduleSpec;
    }




}

