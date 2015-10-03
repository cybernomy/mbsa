package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


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

/**
 * PriceListHead generated by hbm2java
 */
@Entity
@Table(name="PRICELISTHEAD"
)
public class PriceListHead extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private CurrencyRateAuthority CurrencyRateAuthority;
     private PriceType BasePriceType;
     private SysClient SysClient;
     private Contractor Contractor;
     private CurrencyRateType CurrencyRateType;
     private Currency Currency;
     private String PrName;
     private Date CreateDate;
     private boolean IsCurrent;
     private Boolean InsertSign;
     private Integer PricePrecision;
     private Set<PriceListPriceTypeLink> PriceTypeLinks = new HashSet<PriceListPriceTypeLink>(0);
     private Set<PriceListHeadRights> UserRights = new HashSet<PriceListHeadRights>(0);

    public PriceListHead() {
    }

    public PriceListHead(CurrencyRateAuthority CurrencyRateAuthority, PriceType BasePriceType, SysClient SysClient, Contractor Contractor, CurrencyRateType CurrencyRateType, Currency Currency, String PrName, Date CreateDate, boolean IsCurrent, Boolean InsertSign, Integer PricePrecision, Set<PriceListPriceTypeLink> PriceTypeLinks, Set<PriceListHeadRights> UserRights) {
       this.CurrencyRateAuthority = CurrencyRateAuthority;
       this.BasePriceType = BasePriceType;
       this.SysClient = SysClient;
       this.Contractor = Contractor;
       this.CurrencyRateType = CurrencyRateType;
       this.Currency = Currency;
       this.PrName = PrName;
       this.CreateDate = CreateDate;
       this.IsCurrent = IsCurrent;
       this.InsertSign = InsertSign;
       this.PricePrecision = PricePrecision;
       this.PriceTypeLinks = PriceTypeLinks;
       this.UserRights = UserRights;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PRICELISTHEAD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATEAUTHORITY_ID")
    public CurrencyRateAuthority getCurrencyRateAuthority() {
        return this.CurrencyRateAuthority;
    }
    
    public void setCurrencyRateAuthority(CurrencyRateAuthority CurrencyRateAuthority) {
        this.CurrencyRateAuthority = CurrencyRateAuthority;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASEPRICETYPE")
    public PriceType getBasePriceType() {
        return this.BasePriceType;
    }
    
    public void setBasePriceType(PriceType BasePriceType) {
        this.BasePriceType = BasePriceType;
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
    @JoinColumn(name="CONTRACTOR_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }
    
    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATETYPE_ID")
    public CurrencyRateType getCurrencyRateType() {
        return this.CurrencyRateType;
    }
    
    public void setCurrencyRateType(CurrencyRateType CurrencyRateType) {
        this.CurrencyRateType = CurrencyRateType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_CODE")
    public Currency getCurrency() {
        return this.Currency;
    }
    
    public void setCurrency(Currency Currency) {
        this.Currency = Currency;
    }

    
    @Column(name="PRNAME", columnDefinition="VARCHAR", length=80)
    public String getPrName() {
        return this.PrName;
    }
    
    public void setPrName(String PrName) {
        this.PrName = PrName;
    }

    
    @Column(name="CREATEDATE", columnDefinition="TIMESTAMP")
    public Date getCreateDate() {
        return this.CreateDate;
    }
    
    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    
    @Column(name="ISCURRENT", columnDefinition="SMALLINT")
    public boolean isIsCurrent() {
        return this.IsCurrent;
    }
    
    public void setIsCurrent(boolean IsCurrent) {
        this.IsCurrent = IsCurrent;
    }

    
    @Column(name="INSERTSIGN", columnDefinition="SMALLINT")
    public Boolean getInsertSign() {
        return this.InsertSign;
    }
    
    public void setInsertSign(Boolean InsertSign) {
        this.InsertSign = InsertSign;
    }

    
    @Column(name="PRICE_PRECISION", columnDefinition="INTEGER")
    public Integer getPricePrecision() {
        return this.PricePrecision;
    }
    
    public void setPricePrecision(Integer PricePrecision) {
        this.PricePrecision = PricePrecision;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="PriceListHead")
    public Set<PriceListPriceTypeLink> getPriceTypeLinks() {
        return this.PriceTypeLinks;
    }
    
    public void setPriceTypeLinks(Set<PriceListPriceTypeLink> PriceTypeLinks) {
        this.PriceTypeLinks = PriceTypeLinks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="PriceListHead")
    public Set<PriceListHeadRights> getUserRights() {
        return this.UserRights;
    }
    
    public void setUserRights(Set<PriceListHeadRights> UserRights) {
        this.UserRights = UserRights;
    }




}


