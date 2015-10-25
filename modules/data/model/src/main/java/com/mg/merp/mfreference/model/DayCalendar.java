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
 * DayCalendar generated by hbm2java
 */
@Entity
@Table(name = "MF_DAY_CAL")
public class DayCalendar extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Code;

    private String DayCalName;

    public DayCalendar() {
    }

    public DayCalendar(SysClient SysClient, String Code, String DayCalName) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.DayCalName = DayCalName;
    }

    @SequenceGenerator(name = "generator", sequenceName = "MF_DAY_CAL_ID_GEN")
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

    @Column(name = "CODE", columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "DAY_CAL_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getDayCalName() {
        return this.DayCalName;
    }

    public void setDayCalName(String DayCalName) {
        this.DayCalName = DayCalName;
    }
}

