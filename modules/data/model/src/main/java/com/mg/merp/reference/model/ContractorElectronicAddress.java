package com.mg.merp.reference.model;

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
 * ContractorElectronicAddress generated by hbm2java
 */
@Entity
@Table(name = "REF_CONTRACTOR_EADDRESS")
public class ContractorElectronicAddress extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private Contractor Contractor;

    private ElectronicAddressKind EaddressKind;

    private ProtokolKind Protocol;

    private String Address;

    private boolean IsActive;

    public ContractorElectronicAddress() {
    }

    public ContractorElectronicAddress(SysClient SysClient, Contractor Contractor, ElectronicAddressKind EaddressKind, ProtokolKind Protocol, String Address, boolean IsActive) {
        this.SysClient = SysClient;
        this.Contractor = Contractor;
        this.EaddressKind = EaddressKind;
        this.Protocol = Protocol;
        this.Address = Address;
        this.IsActive = IsActive;
    }

    @SequenceGenerator(name = "generator", sequenceName = "REF_CONTRACTOR_EADDRESS_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTOR_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }

    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KIND_ID")
    public ElectronicAddressKind getEaddressKind() {
        return this.EaddressKind;
    }

    public void setEaddressKind(ElectronicAddressKind EaddressKind) {
        this.EaddressKind = EaddressKind;
    }

    @Column(name = "PROTOCOL", columnDefinition = "SMALLINT")
    public ProtokolKind getProtocol() {
        return this.Protocol;
    }

    public void setProtocol(ProtokolKind Protocol) {
        this.Protocol = Protocol;
    }

    @Column(name = "ADDRESS", columnDefinition = "VARCHAR", length = 80)
    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Column(name = "IS_ACTIVE", columnDefinition = "SMALLINT")
    public boolean isIsActive() {
        return this.IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }
}

