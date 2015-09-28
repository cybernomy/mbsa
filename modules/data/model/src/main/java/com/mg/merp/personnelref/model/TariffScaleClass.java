package com.mg.merp.personnelref.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
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
 * TariffScaleClass generated by hbm2java
 */
@Entity
@Table(name="PREF_TARIFF_SCALE_CLASS"
)
public class TariffScaleClass extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private TariffScale TariffScale;
     private Integer ClassNumber;
     private BigDecimal Factor;
     private BigDecimal Rate;

    public TariffScaleClass() {
    }

    public TariffScaleClass(SysClient SysClient, TariffScale TariffScale, Integer ClassNumber, BigDecimal Factor, BigDecimal Rate) {
       this.SysClient = SysClient;
       this.TariffScale = TariffScale;
       this.ClassNumber = ClassNumber;
       this.Factor = Factor;
       this.Rate = Rate;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_TARIFF_SCALE_CLASS_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
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
    @JoinColumn(name="TARIFFSCALE_ID")
    public TariffScale getTariffScale() {
        return this.TariffScale;
    }
    
    public void setTariffScale(TariffScale TariffScale) {
        this.TariffScale = TariffScale;
    }

    
    @Column(name="CLASS_NUMBER", columnDefinition="INTEGER")
    public Integer getClassNumber() {
        return this.ClassNumber;
    }
    
    public void setClassNumber(Integer ClassNumber) {
        this.ClassNumber = ClassNumber;
    }

    
    @Column(name="FACTOR", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getFactor() {
        return this.Factor;
    }
    
    public void setFactor(BigDecimal Factor) {
        this.Factor = Factor;
    }

    
    @Column(name="RATE", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getRate() {
        return this.Rate;
    }
    
    public void setRate(BigDecimal Rate) {
        this.Rate = Rate;
    }




}


