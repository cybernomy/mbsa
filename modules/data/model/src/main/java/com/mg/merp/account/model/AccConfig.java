package com.mg.merp.account.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AccConfig generated by hbm2java
 */
@Entity
@Table(name="ACC_CONFIG"
)
public class AccConfig extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int SysClientId;
     private DocGroup SaleBookDelivDocGroup;
     private DocGroup SaleBookInvoiceDocGroup;
     private Currency BaseCurrency;
     private DocGroup SaleBookAvaninvDocGroup;
     private DocGroup BuyBookDelivDocGroup;
     private DocGroup BuyBookInvoiceDocGroup;
     private Currency NatCurrency;
     private CurrencyRateType CurrencyRateType;
     private CurrencyRateAuthority CurrencyRateAuthority;
     private Integer CurrencyPrec;
     private Short SaleBookKind;

    public AccConfig() {
    }

	
    public AccConfig(int SysClientId) {
        this.SysClientId = SysClientId;
    }
    public AccConfig(int SysClientId, DocGroup SaleBookDelivDocGroup, DocGroup SaleBookInvoiceDocGroup, Currency BaseCurrency, DocGroup SaleBookAvaninvDocGroup, DocGroup BuyBookDelivDocGroup, DocGroup BuyBookInvoiceDocGroup, Currency NatCurrency, CurrencyRateType CurrencyRateType, CurrencyRateAuthority CurrencyRateAuthority, Integer CurrencyPrec, Short SaleBookKind) {
       this.SysClientId = SysClientId;
       this.SaleBookDelivDocGroup = SaleBookDelivDocGroup;
       this.SaleBookInvoiceDocGroup = SaleBookInvoiceDocGroup;
       this.BaseCurrency = BaseCurrency;
       this.SaleBookAvaninvDocGroup = SaleBookAvaninvDocGroup;
       this.BuyBookDelivDocGroup = BuyBookDelivDocGroup;
       this.BuyBookInvoiceDocGroup = BuyBookInvoiceDocGroup;
       this.NatCurrency = NatCurrency;
       this.CurrencyRateType = CurrencyRateType;
       this.CurrencyRateAuthority = CurrencyRateAuthority;
       this.CurrencyPrec = CurrencyPrec;
       this.SaleBookKind = SaleBookKind;
    }
   
     @Id 

    
    @Column(name="CLIENT_ID", unique=true, columnDefinition="INTEGER")
    public int getSysClientId() {
        return this.SysClientId;
    }
    
    public void setSysClientId(int SysClientId) {
        this.SysClientId = SysClientId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SALEBOOK_DELIV_DOCGROUP_ID")
    public DocGroup getSaleBookDelivDocGroup() {
        return this.SaleBookDelivDocGroup;
    }
    
    public void setSaleBookDelivDocGroup(DocGroup SaleBookDelivDocGroup) {
        this.SaleBookDelivDocGroup = SaleBookDelivDocGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SALEBOOK_INVOICE_DOCGROUP_ID")
    public DocGroup getSaleBookInvoiceDocGroup() {
        return this.SaleBookInvoiceDocGroup;
    }
    
    public void setSaleBookInvoiceDocGroup(DocGroup SaleBookInvoiceDocGroup) {
        this.SaleBookInvoiceDocGroup = SaleBookInvoiceDocGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BASE_CURRENCY")
    public Currency getBaseCurrency() {
        return this.BaseCurrency;
    }
    
    public void setBaseCurrency(Currency BaseCurrency) {
        this.BaseCurrency = BaseCurrency;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SALEBOOK_AVANINV_DOCGROUP_ID")
    public DocGroup getSaleBookAvaninvDocGroup() {
        return this.SaleBookAvaninvDocGroup;
    }
    
    public void setSaleBookAvaninvDocGroup(DocGroup SaleBookAvaninvDocGroup) {
        this.SaleBookAvaninvDocGroup = SaleBookAvaninvDocGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUYBOOK_DELIV_DOCGROUP_ID")
    public DocGroup getBuyBookDelivDocGroup() {
        return this.BuyBookDelivDocGroup;
    }
    
    public void setBuyBookDelivDocGroup(DocGroup BuyBookDelivDocGroup) {
        this.BuyBookDelivDocGroup = BuyBookDelivDocGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUYBOOK_INVOICE_DOCGROUP_ID")
    public DocGroup getBuyBookInvoiceDocGroup() {
        return this.BuyBookInvoiceDocGroup;
    }
    
    public void setBuyBookInvoiceDocGroup(DocGroup BuyBookInvoiceDocGroup) {
        this.BuyBookInvoiceDocGroup = BuyBookInvoiceDocGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NAT_CURRENCY")
    public Currency getNatCurrency() {
        return this.NatCurrency;
    }
    
    public void setNatCurrency(Currency NatCurrency) {
        this.NatCurrency = NatCurrency;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUR_RATE_TYPE_ID")
    public CurrencyRateType getCurrencyRateType() {
        return this.CurrencyRateType;
    }
    
    public void setCurrencyRateType(CurrencyRateType CurrencyRateType) {
        this.CurrencyRateType = CurrencyRateType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUR_RATE_AUTHORITY_ID")
    public CurrencyRateAuthority getCurrencyRateAuthority() {
        return this.CurrencyRateAuthority;
    }
    
    public void setCurrencyRateAuthority(CurrencyRateAuthority CurrencyRateAuthority) {
        this.CurrencyRateAuthority = CurrencyRateAuthority;
    }

    
    @Column(name="CURRENCY_PREC", columnDefinition="INTEGER")
    public Integer getCurrencyPrec() {
        return this.CurrencyPrec;
    }
    
    public void setCurrencyPrec(Integer CurrencyPrec) {
        this.CurrencyPrec = CurrencyPrec;
    }

    
    @Column(name="SALEBOOK_KIND", columnDefinition="SMALLINT")
    public Short getSaleBookKind() {
        return this.SaleBookKind;
    }
    
    public void setSaleBookKind(Short SaleBookKind) {
        this.SaleBookKind = SaleBookKind;
    }




}


