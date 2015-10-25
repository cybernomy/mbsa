package com.mg.merp.crm.model;

import com.mg.merp.core.model.SysClient;
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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * Operation generated by hbm2java
 */
@Entity
@Table(name = "CRM_OPERATION")
public class Operation extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private OperationPriority Priority;

    private Operation Parent;

    private User Curator;

    private User Responsible;

    private SysClient SysClient;

    private Contact Contact;

    private Relation Relation;

    private OperationKind Kind;

    private OperationPurpose Purpose;

    private String Code;

    private boolean IsPlan;

    private Date CreateDate;

    private Date PlanDateFrom;

    private Date PlanDateTill;

    private Date FactDateFrom;

    private Date FactDateTill;

    private String OperationPlan;

    private String OperationResult;

    private String OperationNext;

    private OperationStatusKind Status;

    private OperationState State;

    private boolean Notified;

    private Set<LinkedDocument> LinkedDocs = new HashSet<LinkedDocument>(0);

    private Set<Offer> Offers = new HashSet<Offer>(0);

    private Set<Operation> Operations = new HashSet<Operation>(0);

    public Operation() {
    }

    public Operation(OperationPriority Priority, Operation Parent, User Curator, User Responsible, SysClient SysClient, Contact Contact, Relation Relation, OperationKind Kind, OperationPurpose Purpose, String Code, boolean IsPlan, Date CreateDate, Date PlanDateFrom, Date PlanDateTill, Date FactDateFrom, Date FactDateTill, String OperationPlan, String OperationResult, String OperationNext, OperationStatusKind Status, OperationState State, boolean Notified, Set<LinkedDocument> LinkedDocs, Set<Offer> Offers, Set<Operation> Operations) {
        this.Priority = Priority;
        this.Parent = Parent;
        this.Curator = Curator;
        this.Responsible = Responsible;
        this.SysClient = SysClient;
        this.Contact = Contact;
        this.Relation = Relation;
        this.Kind = Kind;
        this.Purpose = Purpose;
        this.Code = Code;
        this.IsPlan = IsPlan;
        this.CreateDate = CreateDate;
        this.PlanDateFrom = PlanDateFrom;
        this.PlanDateTill = PlanDateTill;
        this.FactDateFrom = FactDateFrom;
        this.FactDateTill = FactDateTill;
        this.OperationPlan = OperationPlan;
        this.OperationResult = OperationResult;
        this.OperationNext = OperationNext;
        this.Status = Status;
        this.State = State;
        this.Notified = Notified;
        this.LinkedDocs = LinkedDocs;
        this.Offers = Offers;
        this.Operations = Operations;
    }

    @SequenceGenerator(name = "generator", sequenceName = "CRM_OPERATION_ID_GEN")
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
    @JoinColumn(name = "PRIORITY_ID")
    public OperationPriority getPriority() {
        return this.Priority;
    }

    public void setPriority(OperationPriority Priority) {
        this.Priority = Priority;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public Operation getParent() {
        return this.Parent;
    }

    public void setParent(Operation Parent) {
        this.Parent = Parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURATOR_ID")
    public User getCurator() {
        return this.Curator;
    }

    public void setCurator(User Curator) {
        this.Curator = Curator;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPONSIBLE_ID")
    public User getResponsible() {
        return this.Responsible;
    }

    public void setResponsible(User Responsible) {
        this.Responsible = Responsible;
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
    @JoinColumn(name = "CONTACT_ID")
    public Contact getContact() {
        return this.Contact;
    }

    public void setContact(Contact Contact) {
        this.Contact = Contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RELATION_ID")
    public Relation getRelation() {
        return this.Relation;
    }

    public void setRelation(Relation Relation) {
        this.Relation = Relation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KIND_ID")
    public OperationKind getKind() {
        return this.Kind;
    }

    public void setKind(OperationKind Kind) {
        this.Kind = Kind;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURPOSE_ID")
    public OperationPurpose getPurpose() {
        return this.Purpose;
    }

    public void setPurpose(OperationPurpose Purpose) {
        this.Purpose = Purpose;
    }

    @Column(name = "CODE", columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "IS_PLAN", columnDefinition = "SMALLINT")
    public boolean isPlan() {
        return this.IsPlan;
    }

    public void setPlan(boolean IsPlan) {
        this.IsPlan = IsPlan;
    }

    @Column(name = "CREATE_DATE", columnDefinition = "TIMESTAMP")
    public Date getCreateDate() {
        return this.CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Column(name = "PLAN_DATE_FROM", columnDefinition = "TIMESTAMP")
    public Date getPlanDateFrom() {
        return this.PlanDateFrom;
    }

    public void setPlanDateFrom(Date PlanDateFrom) {
        this.PlanDateFrom = PlanDateFrom;
    }

    @Column(name = "PLAN_DATE_TILL", columnDefinition = "TIMESTAMP")
    public Date getPlanDateTill() {
        return this.PlanDateTill;
    }

    public void setPlanDateTill(Date PlanDateTill) {
        this.PlanDateTill = PlanDateTill;
    }

    @Column(name = "FACT_DATE_FROM", columnDefinition = "TIMESTAMP")
    public Date getFactDateFrom() {
        return this.FactDateFrom;
    }

    public void setFactDateFrom(Date FactDateFrom) {
        this.FactDateFrom = FactDateFrom;
    }

    @Column(name = "FACT_DATE_TILL", columnDefinition = "TIMESTAMP")
    public Date getFactDateTill() {
        return this.FactDateTill;
    }

    public void setFactDateTill(Date FactDateTill) {
        this.FactDateTill = FactDateTill;
    }

    @Column(name = "OPERATION_PLAN", columnDefinition = "VARCHAR", length = 2048)
    public String getOperationPlan() {
        return this.OperationPlan;
    }

    public void setOperationPlan(String OperationPlan) {
        this.OperationPlan = OperationPlan;
    }

    @Column(name = "OPERATION_RESULT", columnDefinition = "VARCHAR", length = 2048)
    public String getOperationResult() {
        return this.OperationResult;
    }

    public void setOperationResult(String OperationResult) {
        this.OperationResult = OperationResult;
    }

    @Column(name = "NEXT_OPERATION", columnDefinition = "VARCHAR", length = 2048)
    public String getOperationNext() {
        return this.OperationNext;
    }

    public void setOperationNext(String OperationNext) {
        this.OperationNext = OperationNext;
    }

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    public OperationStatusKind getStatus() {
        return this.Status;
    }

    public void setStatus(OperationStatusKind Status) {
        this.Status = Status;
    }

    @Column(name = "STATE")
    @Enumerated(EnumType.ORDINAL)
    public OperationState getState() {
        return this.State;
    }

    public void setState(OperationState State) {
        this.State = State;
    }

    @Column(name = "NOTIFIED", columnDefinition = "SMALLINT")
    public boolean isNotified() {
        return this.Notified;
    }

    public void setNotified(boolean Notified) {
        this.Notified = Notified;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Operation")
    public Set<LinkedDocument> getLinkedDocs() {
        return this.LinkedDocs;
    }

    public void setLinkedDocs(Set<LinkedDocument> LinkedDocs) {
        this.LinkedDocs = LinkedDocs;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Operation")
    public Set<Offer> getOffers() {
        return this.Offers;
    }

    public void setOffers(Set<Offer> Offers) {
        this.Offers = Offers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Parent")
    public Set<Operation> getOperations() {
        return this.Operations;
    }

    public void setOperations(Set<Operation> Operations) {
        this.Operations = Operations;
    }
}

