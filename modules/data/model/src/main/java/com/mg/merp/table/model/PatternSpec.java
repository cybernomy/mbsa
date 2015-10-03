package com.mg.merp.table.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.math.BigDecimal;
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
 * PatternSpec generated by hbm2java
 */
@Entity
@Table(name="TAB_PATTERN_SPEC"
)
public class PatternSpec extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private TimeKind TimeKind;
     private SysClient SysClient;
     private PatternHead PatternHead;
     private Integer DayNumber;
     private BigDecimal HoursQuantity;

    public PatternSpec() {
    }

    public PatternSpec(TimeKind TimeKind, SysClient SysClient, PatternHead PatternHead, Integer DayNumber, BigDecimal HoursQuantity) {
       this.TimeKind = TimeKind;
       this.SysClient = SysClient;
       this.PatternHead = PatternHead;
       this.DayNumber = DayNumber;
       this.HoursQuantity = HoursQuantity;
    }
   
     @SequenceGenerator(name="generator", sequenceName="TAB_PATTERN_SPEC_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_KIND_ID")
    public TimeKind getTimeKind() {
        return this.TimeKind;
    }
    
    public void setTimeKind(TimeKind TimeKind) {
        this.TimeKind = TimeKind;
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
    @JoinColumn(name="PATTERN_HEAD_ID")
    public PatternHead getPatternHead() {
        return this.PatternHead;
    }
    
    public void setPatternHead(PatternHead PatternHead) {
        this.PatternHead = PatternHead;
    }

    
    @Column(name="DAY_NUMBER", columnDefinition="INTEGER")
    public Integer getDayNumber() {
        return this.DayNumber;
    }
    
    public void setDayNumber(Integer DayNumber) {
        this.DayNumber = DayNumber;
    }

    
    @Column(name="HOURS_QUANTITY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getHoursQuantity() {
        return this.HoursQuantity;
    }
    
    public void setHoursQuantity(BigDecimal HoursQuantity) {
        this.HoursQuantity = HoursQuantity;
    }




}


