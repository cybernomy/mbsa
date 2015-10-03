package com.mg.merp.table.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListUnit;
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
 * TimeBoardPosition generated by hbm2java
 */
@Entity
@Table(name="TAB_TIME_BOARD_POSITION"
)
public class TimeBoardPosition extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private StaffListUnit StaffListUnit;
     private TimeBoardHead TimeBoardHead;
     private SysClient SysClient;
     private PositionFill PositionFill;
     private Set<TimeBoardSpec> TimeBoardSpecs = new HashSet<TimeBoardSpec>(0);

    public TimeBoardPosition() {
    }

    public TimeBoardPosition(StaffListUnit StaffListUnit, TimeBoardHead TimeBoardHead, SysClient SysClient, PositionFill PositionFill, Set<TimeBoardSpec> TimeBoardSpecs) {
       this.StaffListUnit = StaffListUnit;
       this.TimeBoardHead = TimeBoardHead;
       this.SysClient = SysClient;
       this.PositionFill = PositionFill;
       this.TimeBoardSpecs = TimeBoardSpecs;
    }
   
     @SequenceGenerator(name="generator", sequenceName="TAB_TIME_BOARD_POSITION_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STAFF_LIST_UNIT_ID")
    public StaffListUnit getStaffListUnit() {
        return this.StaffListUnit;
    }
    
    public void setStaffListUnit(StaffListUnit StaffListUnit) {
        this.StaffListUnit = StaffListUnit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TIME_BOARD_HEAD_ID")
    public TimeBoardHead getTimeBoardHead() {
        return this.TimeBoardHead;
    }
    
    public void setTimeBoardHead(TimeBoardHead TimeBoardHead) {
        this.TimeBoardHead = TimeBoardHead;
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
    @JoinColumn(name="POSITION_FILL_ID")
    public PositionFill getPositionFill() {
        return this.PositionFill;
    }
    
    public void setPositionFill(PositionFill PositionFill) {
        this.PositionFill = PositionFill;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="TimeBoardPosition")
    public Set<TimeBoardSpec> getTimeBoardSpecs() {
        return this.TimeBoardSpecs;
    }
    
    public void setTimeBoardSpecs(Set<TimeBoardSpec> TimeBoardSpecs) {
        this.TimeBoardSpecs = TimeBoardSpecs;
    }




}

