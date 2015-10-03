package com.mg.merp.table.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.model.WorkSchedule;
import java.math.BigDecimal;
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
 * ScheduleHead generated by hbm2java
 */
@Entity
@Table(name="TAB_SCHEDULE_HEAD"
)
public class ScheduleHead extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private PatternHead DefaultPatternHead;
     private TimeKind HolidayWorkTimeKind;
     private WorkSchedule WorkSchedule;
     private HolidayAccountKind HolidayAccountKind;
     private BigDecimal PreHolidayReduction;
     private Set<ScheduleSpec> scheduleSpecs = new HashSet<ScheduleSpec>(0);

    public ScheduleHead() {
    }

    public ScheduleHead(SysClient SysClient, PatternHead DefaultPatternHead, TimeKind HolidayWorkTimeKind, WorkSchedule WorkSchedule, HolidayAccountKind HolidayAccountKind, BigDecimal PreHolidayReduction, Set<ScheduleSpec> scheduleSpecs) {
       this.SysClient = SysClient;
       this.DefaultPatternHead = DefaultPatternHead;
       this.HolidayWorkTimeKind = HolidayWorkTimeKind;
       this.WorkSchedule = WorkSchedule;
       this.HolidayAccountKind = HolidayAccountKind;
       this.PreHolidayReduction = PreHolidayReduction;
       this.scheduleSpecs = scheduleSpecs;
    }
   
     @SequenceGenerator(name="generator", sequenceName="TAB_SCHEDULE_HEAD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEFAULT_PATTERN_HEAD_ID")
    public PatternHead getDefaultPatternHead() {
        return this.DefaultPatternHead;
    }
    
    public void setDefaultPatternHead(PatternHead DefaultPatternHead) {
        this.DefaultPatternHead = DefaultPatternHead;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HOLIDAY_WORK_TIME_KIND_ID")
    public TimeKind getHolidayWorkTimeKind() {
        return this.HolidayWorkTimeKind;
    }
    
    public void setHolidayWorkTimeKind(TimeKind HolidayWorkTimeKind) {
        this.HolidayWorkTimeKind = HolidayWorkTimeKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WORK_SCHEDULE_ID", unique=true)
    public WorkSchedule getWorkSchedule() {
        return this.WorkSchedule;
    }
    
    public void setWorkSchedule(WorkSchedule WorkSchedule) {
        this.WorkSchedule = WorkSchedule;
    }

    
    @Column(name="HOLIDAY_ACCOUNT_KIND", columnDefinition="SMALLINT")
    public HolidayAccountKind getHolidayAccountKind() {
        return this.HolidayAccountKind;
    }
    
    public void setHolidayAccountKind(HolidayAccountKind HolidayAccountKind) {
        this.HolidayAccountKind = HolidayAccountKind;
    }

    
    @Column(name="PREHOLIDAY_REDUCTION", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getPreHolidayReduction() {
        return this.PreHolidayReduction;
    }
    
    public void setPreHolidayReduction(BigDecimal PreHolidayReduction) {
        this.PreHolidayReduction = PreHolidayReduction;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ScheduleHead")
    public Set<ScheduleSpec> getScheduleSpecs() {
        return this.scheduleSpecs;
    }
    
    public void setScheduleSpecs(Set<ScheduleSpec> scheduleSpecs) {
        this.scheduleSpecs = scheduleSpecs;
    }




}


