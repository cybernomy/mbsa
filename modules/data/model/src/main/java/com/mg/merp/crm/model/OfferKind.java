package com.mg.merp.crm.model;

import com.mg.merp.core.model.SysClient;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * OfferKind generated by hbm2java
 */
@Entity
@Table(name = "CRM_OFFER_KIND")
public class OfferKind extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Code;

    private String Name;

    private Set<Offer> SetOfCrmOffer = new HashSet<Offer>(0);

    public OfferKind() {
    }

    public OfferKind(SysClient SysClient, String Code, String Name, Set<Offer> SetOfCrmOffer) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.Name = Name;
        this.SetOfCrmOffer = SetOfCrmOffer;
    }

    @SequenceGenerator(name = "generator", sequenceName = "CRM_OFFER_KIND_ID_GEN")
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

    @Column(name = "CODE", columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "KIND_ID", updatable = false)
    public Set<Offer> getSetOfCrmOffer() {
        return this.SetOfCrmOffer;
    }

    public void setSetOfCrmOffer(Set<Offer> SetOfCrmOffer) {
        this.SetOfCrmOffer = SetOfCrmOffer;
    }
}

