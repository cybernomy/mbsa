package com.mg.merp.core.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.api.metadata.BusinessServiceImplKind;
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
 * SysClassImplementation generated by hbm2java
 */
@Entity
@Table(name="SYS_CLASS_IMPL"
)
public class SysClassImplementation extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private ApplicationLayer ApplicationLayer;
     private SysClass SysClass;
     private BusinessServiceImplKind Kind;
     private String Name;

    public SysClassImplementation() {
    }

	
    public SysClassImplementation(ApplicationLayer ApplicationLayer, SysClass SysClass, BusinessServiceImplKind Kind, String Name) {
        this.ApplicationLayer = ApplicationLayer;
        this.SysClass = SysClass;
        this.Kind = Kind;
        this.Name = Name;
    }
    public SysClassImplementation(SysClient SysClient, ApplicationLayer ApplicationLayer, SysClass SysClass, BusinessServiceImplKind Kind, String Name) {
       this.SysClient = SysClient;
       this.ApplicationLayer = ApplicationLayer;
       this.SysClass = SysClass;
       this.Kind = Kind;
       this.Name = Name;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SYS_CLASS_IMPL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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

    
    @Column(name="APP_LAYER", nullable=false, columnDefinition="SMALLINT")
    public ApplicationLayer getApplicationLayer() {
        return this.ApplicationLayer;
    }
    
    public void setApplicationLayer(ApplicationLayer ApplicationLayer) {
        this.ApplicationLayer = ApplicationLayer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLASS_ID", nullable=false)
    public SysClass getSysClass() {
        return this.SysClass;
    }
    
    public void setSysClass(SysClass SysClass) {
        this.SysClass = SysClass;
    }

    
    @Column(name="KIND", nullable=false, columnDefinition="SMALLINT")
    public BusinessServiceImplKind getKind() {
        return this.Kind;
    }
    
    public void setKind(BusinessServiceImplKind Kind) {
        this.Kind = Kind;
    }

    
    @Column(name="NAME", nullable=false, columnDefinition="VARCHAR", length=128)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }




}


