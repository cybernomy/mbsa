package com.mg.merp.personnelref.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.humanresources.model.Order;
import com.mg.merp.reference.model.OriginalDocument;
import java.util.Date;
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
 * PersonnelAttestation generated by hbm2java
 */
@Entity
@Table(name = "PREF_PERSONNEL_ATTESTATION")
public class PersonnelAttestation extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Order Order;

    private Personnel Personnel;

    private SysClient SysClient;

    private OriginalDocument ResolutionDocument;

    private Date AttestationDate;

    private String Resolution;

    public PersonnelAttestation() {
    }

    public PersonnelAttestation(Order Order, Personnel Personnel, SysClient SysClient, OriginalDocument ResolutionDocument, Date AttestationDate, String Resolution) {
        this.Order = Order;
        this.Personnel = Personnel;
        this.SysClient = SysClient;
        this.ResolutionDocument = ResolutionDocument;
        this.AttestationDate = AttestationDate;
        this.Resolution = Resolution;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PREF_PERS_ATTESTATION_ID_GEN")
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
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return this.Order;
    }

    public void setOrder(Order Order) {
        this.Order = Order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONNEL_ID")
    public Personnel getPersonnel() {
        return this.Personnel;
    }

    public void setPersonnel(Personnel Personnel) {
        this.Personnel = Personnel;
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
    @JoinColumn(name = "RESOLUTION_DOCUMENT_ID")
    public OriginalDocument getResolutionDocument() {
        return this.ResolutionDocument;
    }

    public void setResolutionDocument(OriginalDocument ResolutionDocument) {
        this.ResolutionDocument = ResolutionDocument;
    }

    @Column(name = "ATTESTATION_DATE", columnDefinition = "TIMESTAMP")
    public Date getAttestationDate() {
        return this.AttestationDate;
    }

    public void setAttestationDate(Date AttestationDate) {
        this.AttestationDate = AttestationDate;
    }

    @Column(name = "RESOLUTION", columnDefinition = "VARCHAR", length = 80)
    public String getResolution() {
        return this.Resolution;
    }

    public void setResolution(String Resolution) {
        this.Resolution = Resolution;
    }
}

