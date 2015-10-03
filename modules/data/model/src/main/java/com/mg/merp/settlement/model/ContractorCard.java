package com.mg.merp.settlement.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Contractor;
import java.math.BigDecimal;
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
 * ContractorCard generated by hbm2java
 */
@Entity
@Table(name = "CONTRACTORCARD")
public class ContractorCard extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private Contractor Contractor;

    private Contractor OrgUnit;

    private BigDecimal TotalIncome;

    private BigDecimal TotalExpenses;

    private BigDecimal DebitorInDebLimit;

    private BigDecimal CreditorInDebLimit;

    private BigDecimal PlanIncome;

    private BigDecimal PlanExpenses;

    private BigDecimal DebitorInDebSum;

    private BigDecimal CreditorInDebSum;

    private BigDecimal PlanDebitorInDebSum;

    private BigDecimal PlanCreditorInDebSum;

    private Set<ContractorCardPlan> SetOfContractorcardplan = new HashSet<ContractorCardPlan>(0);

    private Set<ContractorCardHist> SetOfContractorcardhist = new HashSet<ContractorCardHist>(0);

    public ContractorCard() {
    }

    public ContractorCard(SysClient SysClient, Contractor Contractor, Contractor OrgUnit, BigDecimal TotalIncome, BigDecimal TotalExpenses, BigDecimal DebitorInDebLimit, BigDecimal CreditorInDebLimit, BigDecimal PlanIncome, BigDecimal PlanExpenses, BigDecimal DebitorInDebSum, BigDecimal CreditorInDebSum, BigDecimal PlanDebitorInDebSum, BigDecimal PlanCreditorInDebSum, Set<ContractorCardPlan> SetOfContractorcardplan, Set<ContractorCardHist> SetOfContractorcardhist) {
        this.SysClient = SysClient;
        this.Contractor = Contractor;
        this.OrgUnit = OrgUnit;
        this.TotalIncome = TotalIncome;
        this.TotalExpenses = TotalExpenses;
        this.DebitorInDebLimit = DebitorInDebLimit;
        this.CreditorInDebLimit = CreditorInDebLimit;
        this.PlanIncome = PlanIncome;
        this.PlanExpenses = PlanExpenses;
        this.DebitorInDebSum = DebitorInDebSum;
        this.CreditorInDebSum = CreditorInDebSum;
        this.PlanDebitorInDebSum = PlanDebitorInDebSum;
        this.PlanCreditorInDebSum = PlanCreditorInDebSum;
        this.SetOfContractorcardplan = SetOfContractorcardplan;
        this.SetOfContractorcardhist = SetOfContractorcardhist;
    }

    @SequenceGenerator(name = "generator", sequenceName = "CONTRACTORCARD_ID_GEN")
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
    @JoinColumn(name = "ORGUNIT_ID")
    public Contractor getOrgUnit() {
        return this.OrgUnit;
    }

    public void setOrgUnit(Contractor OrgUnit) {
        this.OrgUnit = OrgUnit;
    }

    @Column(name = "TOTALINCOME", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getTotalIncome() {
        return this.TotalIncome;
    }

    public void setTotalIncome(BigDecimal TotalIncome) {
        this.TotalIncome = TotalIncome;
    }

    @Column(name = "TOTALEXPENSES", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getTotalExpenses() {
        return this.TotalExpenses;
    }

    public void setTotalExpenses(BigDecimal TotalExpenses) {
        this.TotalExpenses = TotalExpenses;
    }

    @Column(name = "DEBITORINDEBLIMIT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getDebitorInDebLimit() {
        return this.DebitorInDebLimit;
    }

    public void setDebitorInDebLimit(BigDecimal DebitorInDebLimit) {
        this.DebitorInDebLimit = DebitorInDebLimit;
    }

    @Column(name = "CREDITORINDEBLIMIT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getCreditorInDebLimit() {
        return this.CreditorInDebLimit;
    }

    public void setCreditorInDebLimit(BigDecimal CreditorInDebLimit) {
        this.CreditorInDebLimit = CreditorInDebLimit;
    }

    @Column(name = "PLANINCOME", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getPlanIncome() {
        return this.PlanIncome;
    }

    public void setPlanIncome(BigDecimal PlanIncome) {
        this.PlanIncome = PlanIncome;
    }

    @Column(name = "PLANEXPENSES", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getPlanExpenses() {
        return this.PlanExpenses;
    }

    public void setPlanExpenses(BigDecimal PlanExpenses) {
        this.PlanExpenses = PlanExpenses;
    }

    @Column(name = "DEBITORINDEBSUM", insertable = false, updatable = false, columnDefinition = "NUMERIC", precision = 18, scale = 4)
    public BigDecimal getDebitorInDebSum() {
        return this.DebitorInDebSum;
    }

    public void setDebitorInDebSum(BigDecimal DebitorInDebSum) {
        this.DebitorInDebSum = DebitorInDebSum;
    }

    @Column(name = "CREDITORINDEBSUM", insertable = false, updatable = false, columnDefinition = "NUMERIC", precision = 18, scale = 4)
    public BigDecimal getCreditorInDebSum() {
        return this.CreditorInDebSum;
    }

    public void setCreditorInDebSum(BigDecimal CreditorInDebSum) {
        this.CreditorInDebSum = CreditorInDebSum;
    }

    @Column(name = "PLANDEBITORINDEBSUM", insertable = false, updatable = false, columnDefinition = "NUMERIC", precision = 18, scale = 4)
    public BigDecimal getPlanDebitorInDebSum() {
        return this.PlanDebitorInDebSum;
    }

    public void setPlanDebitorInDebSum(BigDecimal PlanDebitorInDebSum) {
        this.PlanDebitorInDebSum = PlanDebitorInDebSum;
    }

    @Column(name = "PLANCREDITORINDEBSUM", insertable = false, updatable = false, columnDefinition = "NUMERIC", precision = 18, scale = 4)
    public BigDecimal getPlanCreditorInDebSum() {
        return this.PlanCreditorInDebSum;
    }

    public void setPlanCreditorInDebSum(BigDecimal PlanCreditorInDebSum) {
        this.PlanCreditorInDebSum = PlanCreditorInDebSum;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTORCARD_ID", updatable = false)
    public Set<ContractorCardPlan> getSetOfContractorcardplan() {
        return this.SetOfContractorcardplan;
    }

    public void setSetOfContractorcardplan(Set<ContractorCardPlan> SetOfContractorcardplan) {
        this.SetOfContractorcardplan = SetOfContractorcardplan;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTORCARD_ID", updatable = false)
    public Set<ContractorCardHist> getSetOfContractorcardhist() {
        return this.SetOfContractorcardhist;
    }

    public void setSetOfContractorcardhist(Set<ContractorCardHist> SetOfContractorcardhist) {
        this.SetOfContractorcardhist = SetOfContractorcardhist;
    }
}

