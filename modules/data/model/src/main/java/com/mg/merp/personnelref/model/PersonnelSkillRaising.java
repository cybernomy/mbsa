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
 * PersonnelSkillRaising generated by hbm2java
 */
@Entity
@Table(name = "PREF_PERSONNEL_SKILL_RAISING")
public class PersonnelSkillRaising extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Order Order;

    private SkillRaisingKind SkillRaisingKind;

    private Personnel Personnel;

    private SysClient SysClient;

    private OriginalDocument CertificateDocument;

    private Date StudyBeginDate;

    private Date StudyEndDate;

    private String InstitutionName;

    private String InstitutionAddress;

    public PersonnelSkillRaising() {
    }

    public PersonnelSkillRaising(SkillRaisingKind SkillRaisingKind) {
        this.SkillRaisingKind = SkillRaisingKind;
    }

    public PersonnelSkillRaising(Order Order, SkillRaisingKind SkillRaisingKind, Personnel Personnel, SysClient SysClient, OriginalDocument CertificateDocument, Date StudyBeginDate, Date StudyEndDate, String InstitutionName, String InstitutionAddress) {
        this.Order = Order;
        this.SkillRaisingKind = SkillRaisingKind;
        this.Personnel = Personnel;
        this.SysClient = SysClient;
        this.CertificateDocument = CertificateDocument;
        this.StudyBeginDate = StudyBeginDate;
        this.StudyEndDate = StudyEndDate;
        this.InstitutionName = InstitutionName;
        this.InstitutionAddress = InstitutionAddress;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PREF_PERS_SKILL_RAISING_ID_GEN")
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
    @JoinColumn(name = "SKILL_RAISING_KIND_ID", nullable = false)
    public SkillRaisingKind getSkillRaisingKind() {
        return this.SkillRaisingKind;
    }

    public void setSkillRaisingKind(SkillRaisingKind SkillRaisingKind) {
        this.SkillRaisingKind = SkillRaisingKind;
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
    @JoinColumn(name = "CERTIFICATE_DOCUMENT_ID")
    public OriginalDocument getCertificateDocument() {
        return this.CertificateDocument;
    }

    public void setCertificateDocument(OriginalDocument CertificateDocument) {
        this.CertificateDocument = CertificateDocument;
    }

    @Column(name = "STUDY_BEGINDATE", columnDefinition = "TIMESTAMP")
    public Date getStudyBeginDate() {
        return this.StudyBeginDate;
    }

    public void setStudyBeginDate(Date StudyBeginDate) {
        this.StudyBeginDate = StudyBeginDate;
    }

    @Column(name = "STUDY_ENDDATE", columnDefinition = "TIMESTAMP")
    public Date getStudyEndDate() {
        return this.StudyEndDate;
    }

    public void setStudyEndDate(Date StudyEndDate) {
        this.StudyEndDate = StudyEndDate;
    }

    @Column(name = "INSTITUTION_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getInstitutionName() {
        return this.InstitutionName;
    }

    public void setInstitutionName(String InstitutionName) {
        this.InstitutionName = InstitutionName;
    }

    @Column(name = "INSTITUTION_ADDRESS", columnDefinition = "VARCHAR", length = 256)
    public String getInstitutionAddress() {
        return this.InstitutionAddress;
    }

    public void setInstitutionAddress(String InstitutionAddress) {
        this.InstitutionAddress = InstitutionAddress;
    }
}

