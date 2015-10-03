package com.mg.merp.paymentalloc.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.reference.model.Tax;
import java.math.BigDecimal;
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
 * TransactTax generated by hbm2java
 */
@Entity
@Table(name="PMA_TRTAX"
)
public class TransactTax extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocumentSpecTax TaxSumm;
     private TransactSpec TrSpec;
     private Tax Tax;
     private SysClient SysClient;
     private BigDecimal TotalSum;
     private BigDecimal AllocSum;

    public TransactTax() {
    }

    public TransactTax(DocumentSpecTax TaxSumm, TransactSpec TrSpec, Tax Tax, SysClient SysClient, BigDecimal TotalSum, BigDecimal AllocSum) {
       this.TaxSumm = TaxSumm;
       this.TrSpec = TrSpec;
       this.Tax = Tax;
       this.SysClient = SysClient;
       this.TotalSum = TotalSum;
       this.AllocSum = AllocSum;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PMA_TRTAX_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAXSUMM_ID")
    public DocumentSpecTax getTaxSumm() {
        return this.TaxSumm;
    }
    
    public void setTaxSumm(DocumentSpecTax TaxSumm) {
        this.TaxSumm = TaxSumm;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRSPEC_ID")
    public TransactSpec getTrSpec() {
        return this.TrSpec;
    }
    
    public void setTrSpec(TransactSpec TrSpec) {
        this.TrSpec = TrSpec;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAX_ID")
    public Tax getTax() {
        return this.Tax;
    }
    
    public void setTax(Tax Tax) {
        this.Tax = Tax;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="TOTALSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getTotalSum() {
        return this.TotalSum;
    }
    
    public void setTotalSum(BigDecimal TotalSum) {
        this.TotalSum = TotalSum;
    }

    
    @Column(name="ALLOCSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getAllocSum() {
        return this.AllocSum;
    }
    
    public void setAllocSum(BigDecimal AllocSum) {
        this.AllocSum = AllocSum;
    }




}


