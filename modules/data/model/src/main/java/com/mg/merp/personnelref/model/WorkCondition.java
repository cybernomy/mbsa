package com.mg.merp.personnelref.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * WorkCondition generated by hbm2java
 */
@Entity
@Table(name="PREF_WORK_CONDITION"
)
public class WorkCondition extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String CCode;
     private String CName;

    public WorkCondition() {
    }

	
    public WorkCondition(String CCode) {
        this.CCode = CCode;
    }
    public WorkCondition(SysClient SysClient, String CCode, String CName) {
       this.SysClient = SysClient;
       this.CCode = CCode;
       this.CName = CName;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_WORK_CONDITION_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="CCODE", unique=true, nullable=false, columnDefinition="CHAR", length=20)
    public String getCCode() {
        return this.CCode;
    }
    
    public void setCCode(String CCode) {
        this.CCode = CCode;
    }

    
    @Column(name="CNAME", columnDefinition="VARCHAR", length=80)
    public String getCName() {
        return this.CName;
    }
    
    public void setCName(String CName) {
        this.CName = CName;
    }




}


