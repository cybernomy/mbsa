package com.mg.merp.planning.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.util.Date;
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
 * MrpVersionControl generated by hbm2java
 */
@Entity
@Table(name="PP_MRP_VERSION_CONTROL"
)
public class MrpVersionControl extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String Code;
     private String Description;
     private Integer MrpVersion;
     private boolean MrpSoFlag;
     private boolean MrpSfFlag;
     private boolean MrpPoFlag;
     private boolean MrpPfFlag;
     private boolean MrpQohFlag;
     private boolean MrpFirmPlannedOrdersFlag;
     private boolean MrpJobFlag;
     private Short DampingDays;
     private Short QcReceivingDays;
     private Date RunDate;
     private Date MrpStartDate;
     private Date MrpEndDate;
     private Date LastRunDatetime;
     private boolean MrpSuggestedOrdersFirmed;

    public MrpVersionControl() {
    }

	
    public MrpVersionControl(String Description) {
        this.Description = Description;
    }
    public MrpVersionControl(SysClient SysClient, String Code, String Description, Integer MrpVersion, boolean MrpSoFlag, boolean MrpSfFlag, boolean MrpPoFlag, boolean MrpPfFlag, boolean MrpQohFlag, boolean MrpFirmPlannedOrdersFlag, boolean MrpJobFlag, Short DampingDays, Short QcReceivingDays, Date RunDate, Date MrpStartDate, Date MrpEndDate, Date LastRunDatetime, boolean MrpSuggestedOrdersFirmed) {
       this.SysClient = SysClient;
       this.Code = Code;
       this.Description = Description;
       this.MrpVersion = MrpVersion;
       this.MrpSoFlag = MrpSoFlag;
       this.MrpSfFlag = MrpSfFlag;
       this.MrpPoFlag = MrpPoFlag;
       this.MrpPfFlag = MrpPfFlag;
       this.MrpQohFlag = MrpQohFlag;
       this.MrpFirmPlannedOrdersFlag = MrpFirmPlannedOrdersFlag;
       this.MrpJobFlag = MrpJobFlag;
       this.DampingDays = DampingDays;
       this.QcReceivingDays = QcReceivingDays;
       this.RunDate = RunDate;
       this.MrpStartDate = MrpStartDate;
       this.MrpEndDate = MrpEndDate;
       this.LastRunDatetime = LastRunDatetime;
       this.MrpSuggestedOrdersFirmed = MrpSuggestedOrdersFirmed;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PP_MRP_VERSION_CONTROL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="CODE", columnDefinition="CHAR", length=20)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="DESCRIPTION", nullable=false, columnDefinition="VARCHAR", length=80)
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    @Column(name="MRP_VERSION", columnDefinition="INTEGER")
    public Integer getMrpVersion() {
        return this.MrpVersion;
    }
    
    public void setMrpVersion(Integer MrpVersion) {
        this.MrpVersion = MrpVersion;
    }

    
    @Column(name="MRP_SO_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpSoFlag() {
        return this.MrpSoFlag;
    }
    
    public void setMrpSoFlag(boolean MrpSoFlag) {
        this.MrpSoFlag = MrpSoFlag;
    }

    
    @Column(name="MRP_SF_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpSfFlag() {
        return this.MrpSfFlag;
    }
    
    public void setMrpSfFlag(boolean MrpSfFlag) {
        this.MrpSfFlag = MrpSfFlag;
    }

    
    @Column(name="MRP_PO_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpPoFlag() {
        return this.MrpPoFlag;
    }
    
    public void setMrpPoFlag(boolean MrpPoFlag) {
        this.MrpPoFlag = MrpPoFlag;
    }

    
    @Column(name="MRP_PF_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpPfFlag() {
        return this.MrpPfFlag;
    }
    
    public void setMrpPfFlag(boolean MrpPfFlag) {
        this.MrpPfFlag = MrpPfFlag;
    }

    
    @Column(name="MRP_QOH_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpQohFlag() {
        return this.MrpQohFlag;
    }
    
    public void setMrpQohFlag(boolean MrpQohFlag) {
        this.MrpQohFlag = MrpQohFlag;
    }

    
    @Column(name="MRP_FIRM_PLANNED_ORDERS_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpFirmPlannedOrdersFlag() {
        return this.MrpFirmPlannedOrdersFlag;
    }
    
    public void setMrpFirmPlannedOrdersFlag(boolean MrpFirmPlannedOrdersFlag) {
        this.MrpFirmPlannedOrdersFlag = MrpFirmPlannedOrdersFlag;
    }

    
    @Column(name="MRP_JOB_FLAG", columnDefinition="SMALLINT")
    public boolean isMrpJobFlag() {
        return this.MrpJobFlag;
    }
    
    public void setMrpJobFlag(boolean MrpJobFlag) {
        this.MrpJobFlag = MrpJobFlag;
    }

    
    @Column(name="DAMPING_DAYS", columnDefinition="SMALLINT")
    public Short getDampingDays() {
        return this.DampingDays;
    }
    
    public void setDampingDays(Short DampingDays) {
        this.DampingDays = DampingDays;
    }

    
    @Column(name="QC_RECEIVING_DAYS", columnDefinition="SMALLINT")
    public Short getQcReceivingDays() {
        return this.QcReceivingDays;
    }
    
    public void setQcReceivingDays(Short QcReceivingDays) {
        this.QcReceivingDays = QcReceivingDays;
    }

    
    @Column(name="RUN_DATE", columnDefinition="TIMESTAMP")
    public Date getRunDate() {
        return this.RunDate;
    }
    
    public void setRunDate(Date RunDate) {
        this.RunDate = RunDate;
    }

    
    @Column(name="MRP_START_DATE", columnDefinition="TIMESTAMP")
    public Date getMrpStartDate() {
        return this.MrpStartDate;
    }
    
    public void setMrpStartDate(Date MrpStartDate) {
        this.MrpStartDate = MrpStartDate;
    }

    
    @Column(name="MRP_END_DATE", columnDefinition="TIMESTAMP")
    public Date getMrpEndDate() {
        return this.MrpEndDate;
    }
    
    public void setMrpEndDate(Date MrpEndDate) {
        this.MrpEndDate = MrpEndDate;
    }

    
    @Column(name="LAST_RUN_DATETIME", columnDefinition="TIMESTAMP")
    public Date getLastRunDatetime() {
        return this.LastRunDatetime;
    }
    
    public void setLastRunDatetime(Date LastRunDatetime) {
        this.LastRunDatetime = LastRunDatetime;
    }

    
    @Column(name="MRP_SUGGESTED_ORDERS_FIRMED", columnDefinition="SMALLINT")
    public boolean isMrpSuggestedOrdersFirmed() {
        return this.MrpSuggestedOrdersFirmed;
    }
    
    public void setMrpSuggestedOrdersFirmed(boolean MrpSuggestedOrdersFirmed) {
        this.MrpSuggestedOrdersFirmed = MrpSuggestedOrdersFirmed;
    }




}


