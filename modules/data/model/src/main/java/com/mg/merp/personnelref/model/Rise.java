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
 * Rise generated by hbm2java
 */
@Entity
@Table(name="PREF_RISE"
)
public class Rise extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String RCode;
     private String RName;

    public Rise() {
    }

	
    public Rise(String RCode) {
        this.RCode = RCode;
    }
    public Rise(SysClient SysClient, String RCode, String RName) {
       this.SysClient = SysClient;
       this.RCode = RCode;
       this.RName = RName;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_RISE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="RCODE", unique=true, nullable=false, columnDefinition="CHAR", length=20)
    public String getRCode() {
        return this.RCode;
    }
    
    public void setRCode(String RCode) {
        this.RCode = RCode;
    }

    
    @Column(name="RNAME", columnDefinition="VARCHAR", length=80)
    public String getRName() {
        return this.RName;
    }
    
    public void setRName(String RName) {
        this.RName = RName;
    }




}


