package com.mg.merp.reference.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
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
 * PersonElectronicAddress generated by hbm2java
 */
@Entity
@Table(name="REF_PERSON_EADDRESS"
)
public class PersonElectronicAddress extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private NaturalPerson NaturalPerson;
     private SysClient SysClient;
     private ElectronicAddressKind EaddressKind;
     private EnumUserType Protocol;
     private String Address;
     private boolean IsActive;

    public PersonElectronicAddress() {
    }

    public PersonElectronicAddress(NaturalPerson NaturalPerson, SysClient SysClient, ElectronicAddressKind EaddressKind, EnumUserType Protocol, String Address, boolean IsActive) {
       this.NaturalPerson = NaturalPerson;
       this.SysClient = SysClient;
       this.EaddressKind = EaddressKind;
       this.Protocol = Protocol;
       this.Address = Address;
       this.IsActive = IsActive;
    }
   
     @SequenceGenerator(name="generator", sequenceName="REF_PERSON_EADDRESS_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSON_ID")
    public NaturalPerson getNaturalPerson() {
        return this.NaturalPerson;
    }
    
    public void setNaturalPerson(NaturalPerson NaturalPerson) {
        this.NaturalPerson = NaturalPerson;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="KIND_ID")
    public ElectronicAddressKind getEaddressKind() {
        return this.EaddressKind;
    }
    
    public void setEaddressKind(ElectronicAddressKind EaddressKind) {
        this.EaddressKind = EaddressKind;
    }

    
    @Column(name="PROTOCOL", columnDefinition="SMALLINT")
    public EnumUserType getProtocol() {
        return this.Protocol;
    }
    
    public void setProtocol(EnumUserType Protocol) {
        this.Protocol = Protocol;
    }

    
    @Column(name="ADDRESS", columnDefinition="VARCHAR", length=80)
    public String getAddress() {
        return this.Address;
    }
    
    public void setAddress(String Address) {
        this.Address = Address;
    }

    
    @Column(name="IS_ACTIVE", columnDefinition="SMALLINT")
    public boolean isIsActive() {
        return this.IsActive;
    }
    
    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }




}


