package com.mg.merp.account.model;
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
 * EconomicSpecFeature generated by hbm2java
 */
@Entity
@Table(name="ECONSPEC_FEATURE"
)
public class EconomicSpecFeature extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String UpCode;
     private String Code;
     private String Name;

    public EconomicSpecFeature() {
    }

	
    public EconomicSpecFeature(String UpCode, String Code) {
        this.UpCode = UpCode;
        this.Code = Code;
    }
    public EconomicSpecFeature(SysClient SysClient, String UpCode, String Code, String Name) {
       this.SysClient = SysClient;
       this.UpCode = UpCode;
       this.Code = Code;
       this.Name = Name;
    }
   
     @SequenceGenerator(name="generator", sequenceName="ECONSPECFEATURE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="UPCODE", unique=true, nullable=false, columnDefinition="CHAR", length=10)
    public String getUpCode() {
        return this.UpCode;
    }
    
    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    
    @Column(name="CODE", nullable=false, columnDefinition="CHAR", length=10)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="FNAME", columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }




}


