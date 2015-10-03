package com.mg.merp.core.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.framework.api.security.SecurityAuditType;
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
 * SecurityAudit generated by hbm2java
 */
@Entity
@Table(name="SYS_SECURITY_AUDIT"
)
public class SecurityAudit extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Long Id;
     private SysClient SysClient;
     private String UserName;
     private Date EventDateTime;
     private SecurityAuditType AuditType;
     private String AuditBean;
     private String Details;

    public SecurityAudit() {
    }

	
    public SecurityAudit(String UserName, Date EventDateTime, SecurityAuditType AuditType, String AuditBean) {
        this.UserName = UserName;
        this.EventDateTime = EventDateTime;
        this.AuditType = AuditType;
        this.AuditBean = AuditBean;
    }
    public SecurityAudit(SysClient SysClient, String UserName, Date EventDateTime, SecurityAuditType AuditType, String AuditBean, String Details) {
       this.SysClient = SysClient;
       this.UserName = UserName;
       this.EventDateTime = EventDateTime;
       this.AuditType = AuditType;
       this.AuditBean = AuditBean;
       this.Details = Details;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SYS_SECURITY_AUDIT_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="NUMERIC")
    public Long getId() {
        return this.Id;
    }
    
    public void setId(Long Id) {
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

    
    @Column(name="USER_NAME", nullable=false, columnDefinition="CHAR", length=31)
    public String getUserName() {
        return this.UserName;
    }
    
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    
    @Column(name="EVENT_DATETIME", nullable=false, columnDefinition="TIMESTAMP")
    public Date getEventDateTime() {
        return this.EventDateTime;
    }
    
    public void setEventDateTime(Date EventDateTime) {
        this.EventDateTime = EventDateTime;
    }

    
    @Column(name="AUDIT_TYPE", nullable=false, columnDefinition="SMALLINT")
    public SecurityAuditType getAuditType() {
        return this.AuditType;
    }
    
    public void setAuditType(SecurityAuditType AuditType) {
        this.AuditType = AuditType;
    }

    
    @Column(name="AUDIT_BEAN", nullable=false, columnDefinition="VARCHAR", length=256)
    public String getAuditBean() {
        return this.AuditBean;
    }
    
    public void setAuditBean(String AuditBean) {
        this.AuditBean = AuditBean;
    }

    
    @Column(name="DETAILS", columnDefinition="VARCHAR", length=1024)
    public String getDetails() {
        return this.Details;
    }
    
    public void setDetails(String Details) {
        this.Details = Details;
    }




}


