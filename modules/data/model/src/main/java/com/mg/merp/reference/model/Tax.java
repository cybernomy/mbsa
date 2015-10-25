package com.mg.merp.reference.model;

import com.mg.merp.core.model.SysClient;
import java.math.BigDecimal;
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
 * Tax generated by hbm2java
 */
@Entity
@Table(name = "TAX")
public class Tax extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String UpCode;

    private String Code;

    private String TName;

    private Date ActiveDate;

    private TaxType TaxType;

    private TaxForm TaxForm;

    private boolean Included;

    private BigDecimal DirectRate;

    private BigDecimal InverseRate;

    private BigDecimal Summ;

    private Date DeactivateDate;

    private Set<TaxLink> GroupLinks = new HashSet<TaxLink>(0);

    private Set<CalcTaxesLink> CalculationLinks = new HashSet<CalcTaxesLink>(0);

    public Tax() {
    }

    public Tax(String UpCode, String Code, String TName, TaxType TaxType, TaxForm TaxForm, boolean Included, BigDecimal DirectRate, BigDecimal InverseRate) {
        this.UpCode = UpCode;
        this.Code = Code;
        this.TName = TName;
        this.TaxType = TaxType;
        this.TaxForm = TaxForm;
        this.Included = Included;
        this.DirectRate = DirectRate;
        this.InverseRate = InverseRate;
    }

    public Tax(SysClient SysClient, String UpCode, String Code, String TName, Date ActiveDate, TaxType TaxType, TaxForm TaxForm, boolean Included, BigDecimal DirectRate, BigDecimal InverseRate, BigDecimal Summ, Date DeactivateDate, Set<TaxLink> GroupLinks, Set<CalcTaxesLink> CalculationLinks) {
        this.SysClient = SysClient;
        this.UpCode = UpCode;
        this.Code = Code;
        this.TName = TName;
        this.ActiveDate = ActiveDate;
        this.TaxType = TaxType;
        this.TaxForm = TaxForm;
        this.Included = Included;
        this.DirectRate = DirectRate;
        this.InverseRate = InverseRate;
        this.Summ = Summ;
        this.DeactivateDate = DeactivateDate;
        this.GroupLinks = GroupLinks;
        this.CalculationLinks = CalculationLinks;
    }

    @SequenceGenerator(name = "generator", sequenceName = "TAX_ID_GEN")
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

    @Column(name = "UPCODE", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getUpCode() {
        return this.UpCode;
    }

    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    @Column(name = "CODE", nullable = false, columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "TNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getTName() {
        return this.TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    @Column(name = "ACTIVEDATE", columnDefinition = "TIMESTAMP")
    public Date getActiveDate() {
        return this.ActiveDate;
    }

    public void setActiveDate(Date ActiveDate) {
        this.ActiveDate = ActiveDate;
    }

    @Column(name = "TAXTYPE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public TaxType getTaxType() {
        return this.TaxType;
    }

    public void setTaxType(TaxType TaxType) {
        this.TaxType = TaxType;
    }

    @Column(name = "TAXFORM", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public TaxForm getTaxForm() {
        return this.TaxForm;
    }

    public void setTaxForm(TaxForm TaxForm) {
        this.TaxForm = TaxForm;
    }

    @Column(name = "INCLUDED", nullable = false, columnDefinition = "SMALLINT")
    public boolean isIncluded() {
        return this.Included;
    }

    public void setIncluded(boolean Included) {
        this.Included = Included;
    }

    @Column(name = "DIRECTRATE", nullable = false, columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getDirectRate() {
        return this.DirectRate;
    }

    public void setDirectRate(BigDecimal DirectRate) {
        this.DirectRate = DirectRate;
    }

    @Column(name = "INVERSERATE", nullable = false, columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getInverseRate() {
        return this.InverseRate;
    }

    public void setInverseRate(BigDecimal InverseRate) {
        this.InverseRate = InverseRate;
    }

    @Column(name = "SUMM", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getSumm() {
        return this.Summ;
    }

    public void setSumm(BigDecimal Summ) {
        this.Summ = Summ;
    }

    @Column(name = "DEACTIVATEDATE", columnDefinition = "TIMESTAMP")
    public Date getDeactivateDate() {
        return this.DeactivateDate;
    }

    public void setDeactivateDate(Date DeactivateDate) {
        this.DeactivateDate = DeactivateDate;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAX_ID", nullable = false, updatable = false)
    public Set<TaxLink> getGroupLinks() {
        return this.GroupLinks;
    }

    public void setGroupLinks(Set<TaxLink> GroupLinks) {
        this.GroupLinks = GroupLinks;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAX_ID", nullable = false, updatable = false)
    public Set<CalcTaxesLink> getCalculationLinks() {
        return this.CalculationLinks;
    }

    public void setCalculationLinks(Set<CalcTaxesLink> CalculationLinks) {
        this.CalculationLinks = CalculationLinks;
    }
}

