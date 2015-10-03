package com.mg.merp.overall.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.model.Personnel;
import com.mg.merp.personnelref.model.PrefJob;
import com.mg.merp.personnelref.model.PrefPosition;
import com.mg.merp.reference.model.Contractor;
import java.util.Date;
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
import org.hibernate.annotations.Formula;

/**
 * OvrCard generated by hbm2java
 */
@Entity
@Table(name = "OVR_CARD")
public class OvrCard extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private PrefPosition StfPosition;

    private PrefJob StfJob;

    private SysClient SysClient;

    private Personnel RefPersonnel;

    private Contractor OrgUnit;

    private Date ActDateFrom;

    private Date ActDateTo;

    private String OvrCardNumber;

    private Integer OvrNormHeadId;

    private Set<OvrCardHist> cardHistories = new HashSet<OvrCardHist>(0);

    private Set<Size> sizes = new HashSet<Size>(0);

    private Set<OvrCardDocumentLink> cardDocumentLinks = new HashSet<OvrCardDocumentLink>(0);

    public OvrCard() {
    }

    public OvrCard(Contractor OrgUnit) {
        this.OrgUnit = OrgUnit;
    }

    public OvrCard(PrefPosition StfPosition, PrefJob StfJob, SysClient SysClient, Personnel RefPersonnel, Contractor OrgUnit, Date ActDateFrom, Date ActDateTo, String OvrCardNumber, Set<OvrCardHist> cardHistories, Set<Size> sizes, Set<OvrCardDocumentLink> cardDocumentLinks) {
        this.StfPosition = StfPosition;
        this.StfJob = StfJob;
        this.SysClient = SysClient;
        this.RefPersonnel = RefPersonnel;
        this.OrgUnit = OrgUnit;
        this.ActDateFrom = ActDateFrom;
        this.ActDateTo = ActDateTo;
        this.OvrCardNumber = OvrCardNumber;
        this.cardHistories = cardHistories;
        this.sizes = sizes;
        this.cardDocumentLinks = cardDocumentLinks;
    }

    @SequenceGenerator(name = "generator", sequenceName = "OVR_CARD_ID_GEN")
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
    @JoinColumn(name = "STF_POSITION_ID")
    public PrefPosition getStfPosition() {
        return this.StfPosition;
    }

    public void setStfPosition(PrefPosition StfPosition) {
        this.StfPosition = StfPosition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STF_JOB_ID")
    public PrefJob getStfJob() {
        return this.StfJob;
    }

    public void setStfJob(PrefJob StfJob) {
        this.StfJob = StfJob;
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
    @JoinColumn(name = "REF_PERSONNEL_ID")
    public Personnel getRefPersonnel() {
        return this.RefPersonnel;
    }

    public void setRefPersonnel(Personnel RefPersonnel) {
        this.RefPersonnel = RefPersonnel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGUNIT_ID", nullable = false, columnDefinition = "INTEGER")
    public Contractor getOrgUnit() {
        return this.OrgUnit;
    }

    public void setOrgUnit(Contractor OrgUnit) {
        this.OrgUnit = OrgUnit;
    }

    @Column(name = "ACTDATE_FROM", columnDefinition = "TIMESTAMP")
    public Date getActDateFrom() {
        return this.ActDateFrom;
    }

    public void setActDateFrom(Date ActDateFrom) {
        this.ActDateFrom = ActDateFrom;
    }

    @Column(name = "ACTDATE_TO", columnDefinition = "TIMESTAMP")
    public Date getActDateTo() {
        return this.ActDateTo;
    }

    public void setActDateTo(Date ActDateTo) {
        this.ActDateTo = ActDateTo;
    }

    @Column(name = "OVR_CARD_NUMBER", columnDefinition = "CHAR", length = 20)
    public String getOvrCardNumber() {
        return this.OvrCardNumber;
    }

    public void setOvrCardNumber(String OvrCardNumber) {
        this.OvrCardNumber = OvrCardNumber;
    }

    @Formula(value = "(select och.ovr_norm_head_id from ovr_card_hist och where och.ovr_card_id = Id)")
    public Integer getOvrNormHeadId() {
        return this.OvrNormHeadId;
    }

    public void setOvrNormHeadId(Integer OvrNormHeadId) {
        this.OvrNormHeadId = OvrNormHeadId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OvrCard")
    public Set<OvrCardHist> getCardHistories() {
        return this.cardHistories;
    }

    public void setCardHistories(Set<OvrCardHist> cardHistories) {
        this.cardHistories = cardHistories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OvrCard")
    public Set<Size> getSizes() {
        return this.sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OvrCard")
    public Set<OvrCardDocumentLink> getCardDocumentLinks() {
        return this.cardDocumentLinks;
    }

    public void setCardDocumentLinks(Set<OvrCardDocumentLink> cardDocumentLinks) {
        this.cardDocumentLinks = cardDocumentLinks;
    }
}

