package com.mg.merp.warehouse.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.security.model.Groups;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WarehouseSecurity generated by hbm2java
 */
@Entity
@Table(name="WH_SECURITY"
)
public class WarehouseSecurity extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Groups SecGroups;
     private SysClient SysClient;
     private Short DisableSpecPrice;
     private Short DisableDiscount;
     private Short DisableExceedQuant;

    public WarehouseSecurity() {
    }

	
    public WarehouseSecurity(int Id) {
        this.Id = Id;
    }
    public WarehouseSecurity(int Id, Groups SecGroups, SysClient SysClient, Short DisableSpecPrice, Short DisableDiscount, Short DisableExceedQuant) {
       this.Id = Id;
       this.SecGroups = SecGroups;
       this.SysClient = SysClient;
       this.DisableSpecPrice = DisableSpecPrice;
       this.DisableDiscount = DisableDiscount;
       this.DisableExceedQuant = DisableExceedQuant;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    public Groups getSecGroups() {
        return this.SecGroups;
    }
    
    public void setSecGroups(Groups SecGroups) {
        this.SecGroups = SecGroups;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="DISABLE_SPEC_PRICE", columnDefinition="SMALLINT")
    public Short getDisableSpecPrice() {
        return this.DisableSpecPrice;
    }
    
    public void setDisableSpecPrice(Short DisableSpecPrice) {
        this.DisableSpecPrice = DisableSpecPrice;
    }

    
    @Column(name="DISABLE_DISCOUNT", columnDefinition="SMALLINT")
    public Short getDisableDiscount() {
        return this.DisableDiscount;
    }
    
    public void setDisableDiscount(Short DisableDiscount) {
        this.DisableDiscount = DisableDiscount;
    }

    
    @Column(name="DISABLE_EXCEED_QUANT", columnDefinition="SMALLINT")
    public Short getDisableExceedQuant() {
        return this.DisableExceedQuant;
    }
    
    public void setDisableExceedQuant(Short DisableExceedQuant) {
        this.DisableExceedQuant = DisableExceedQuant;
    }




}


