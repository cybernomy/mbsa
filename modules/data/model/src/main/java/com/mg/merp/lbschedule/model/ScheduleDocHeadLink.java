package com.mg.merp.lbschedule.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import javax.persistence.CascadeType;
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
 * ScheduleDocHeadLink generated by hbm2java
 */
@Entity
@Table(name="LS_SCHEDULE_DOCHEAD"
)
public class ScheduleDocHeadLink extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private Schedule Schedule;
     private DocHead DocHead;

    public ScheduleDocHeadLink() {
    }

	
    public ScheduleDocHeadLink(Schedule Schedule, DocHead DocHead) {
        this.Schedule = Schedule;
        this.DocHead = DocHead;
    }
    public ScheduleDocHeadLink(SysClient SysClient, Schedule Schedule, DocHead DocHead) {
       this.SysClient = SysClient;
       this.Schedule = Schedule;
       this.DocHead = DocHead;
    }
   
     @SequenceGenerator(name="generator", sequenceName="LS_SCHEDULE_DOCHEAD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    @JoinColumn(name="LS_SCHEDULE_ID", nullable=false)
    public Schedule getSchedule() {
        return this.Schedule;
    }
    
    public void setSchedule(Schedule Schedule) {
        this.Schedule = Schedule;
    }

@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    @JoinColumn(name="DOCHEAD_ID", nullable=false)
    public DocHead getDocHead() {
        return this.DocHead;
    }
    
    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
    }




}


