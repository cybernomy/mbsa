package com.mg.merp.mfreference.model;

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
 * DayTime generated by hbm2java
 */
@Entity
@Table(name = "MF_DAY_TIME")
public class DayTime extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private DayCalendar DayCal;

    private SysClient SysClient;

    private Long StartTick;

    private Long Ticks;

    public DayTime() {
    }

    public DayTime(DayCalendar DayCal, SysClient SysClient, Long StartTick, Long Ticks) {
        this.DayCal = DayCal;
        this.SysClient = SysClient;
        this.StartTick = StartTick;
        this.Ticks = Ticks;
    }

    @SequenceGenerator(name = "generator", sequenceName = "MF_DAY_TIME_ID_GEN")
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
    @JoinColumn(name = "DAY_CAL_ID")
    public DayCalendar getDayCal() {
        return this.DayCal;
    }

    public void setDayCal(DayCalendar DayCal) {
        this.DayCal = DayCal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "START_TICK", columnDefinition = "NUMERIC", precision = 18, scale = 0)
    public Long getStartTick() {
        return this.StartTick;
    }

    public void setStartTick(Long StartTick) {
        this.StartTick = StartTick;
    }

    @Column(name = "TICKS", columnDefinition = "NUMERIC", precision = 18, scale = 0)
    public Long getTicks() {
        return this.Ticks;
    }

    public void setTicks(Long Ticks) {
        this.Ticks = Ticks;
    }
}

