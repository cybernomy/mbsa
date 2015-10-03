package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TaxSumBufId generated by hbm2java
 */
@Embeddable
public class TaxSumBufId extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private SysClient SysClient;
     private Integer SpecificationId;
     private Integer TaxId;
     private BigDecimal Summ;

    public TaxSumBufId() {
    }

    public TaxSumBufId(SysClient SysClient, Integer SpecificationId, Integer TaxId, BigDecimal Summ) {
       this.SysClient = SysClient;
       this.SpecificationId = SpecificationId;
       this.TaxId = TaxId;
       this.Summ = Summ;
    }
   


    @Column(name="CLIENT_ID", nullable=false)
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }


    @Column(name="SPECIFICATION_ID", nullable=false)
    public Integer getSpecificationId() {
        return this.SpecificationId;
    }
    
    public void setSpecificationId(Integer SpecificationId) {
        this.SpecificationId = SpecificationId;
    }


    @Column(name="TAX_ID", nullable=false)
    public Integer getTaxId() {
        return this.TaxId;
    }
    
    public void setTaxId(Integer TaxId) {
        this.TaxId = TaxId;
    }


    @Column(name="SUMM", nullable=false)
    public BigDecimal getSumm() {
        return this.Summ;
    }
    
    public void setSumm(BigDecimal Summ) {
        this.Summ = Summ;
    }




}


