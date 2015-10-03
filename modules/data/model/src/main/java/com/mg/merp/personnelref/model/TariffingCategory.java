package com.mg.merp.personnelref.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.core.model.SysClient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TariffingCategory generated by hbm2java
 */
@Entity
@Table(name="PREF_TARIFFING_CATEGORY"
)
public class TariffingCategory extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String CCode;
     private String CName;
     private EnumUserType CType;
     private Integer Priority;

    public TariffingCategory() {
    }

	
    public TariffingCategory(Integer Id, String CCode, EnumUserType CType) {
        this.Id = Id;
        this.CCode = CCode;
        this.CType = CType;
    }
    public TariffingCategory(Integer Id, SysClient SysClient, String CCode, String CName, EnumUserType CType, Integer Priority) {
       this.Id = Id;
       this.SysClient = SysClient;
       this.CCode = CCode;
       this.CName = CName;
       this.CType = CType;
       this.Priority = Priority;
    }
   
     @Id 

    
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

    
    @Column(name="CTYPE", nullable=false, columnDefinition="SMALLINT")
    public EnumUserType getCType() {
        return this.CType;
    }
    
    public void setCType(EnumUserType CType) {
        this.CType = CType;
    }

    
    @Column(name="PRIORITY", columnDefinition="INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }
    
    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }




}

