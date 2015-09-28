package com.mg.merp.reference.model;
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
 * Measure generated by hbm2java
 */
@Entity
@Table(name="MEASURE"
)
public class Measure extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String UpCode;
     private String Code;
     private String FullName;
     private boolean Dividing;
     private String UniversalCode;
     private String InternalCode;

    public Measure() {
    }

	
    public Measure(String UpCode, String Code, String FullName) {
        this.UpCode = UpCode;
        this.Code = Code;
        this.FullName = FullName;
    }
    public Measure(SysClient SysClient, String UpCode, String Code, String FullName, boolean Dividing, String UniversalCode, String InternalCode) {
       this.SysClient = SysClient;
       this.UpCode = UpCode;
       this.Code = Code;
       this.FullName = FullName;
       this.Dividing = Dividing;
       this.UniversalCode = UniversalCode;
       this.InternalCode = InternalCode;
    }
   
     @SequenceGenerator(name="generator", sequenceName="MEASURE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="UPCODE", unique=true, nullable=false, columnDefinition="CHAR", length=5)
    public String getUpCode() {
        return this.UpCode;
    }
    
    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    
    @Column(name="CODE", nullable=false, columnDefinition="CHAR", length=5)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="MNAME", nullable=false, columnDefinition="VARCHAR", length=80)
    public String getFullName() {
        return this.FullName;
    }
    
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    
    @Column(name="DIVIDE", columnDefinition="SMALLINT")
    public boolean isDividing() {
        return this.Dividing;
    }
    
    public void setDividing(boolean Dividing) {
        this.Dividing = Dividing;
    }

    
    @Column(name="UNIVERSAL_CODE", columnDefinition="CHAR", length=5)
    public String getUniversalCode() {
        return this.UniversalCode;
    }
    
    public void setUniversalCode(String UniversalCode) {
        this.UniversalCode = UniversalCode;
    }

    
    @Column(name="INTERNAL_CODE", columnDefinition="CHAR", length=20)
    public String getInternalCode() {
        return this.InternalCode;
    }
    
    public void setInternalCode(String InternalCode) {
        this.InternalCode = InternalCode;
    }




}


