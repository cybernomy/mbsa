package com.mg.merp.personnelref.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


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
 * RisePercent generated by hbm2java
 */
@Entity
@Table(name="PREF_RISE_PERCENT"
)
public class RisePercent extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private RiseScale RiseScale;
     private SysClient SysClient;
     private Integer PercentNumber;
     private BigDecimal ServiceFrom;
     private BigDecimal ServiceTo;
     private BigDecimal RiseValue;

    public RisePercent() {
    }

    public RisePercent(RiseScale RiseScale, SysClient SysClient, Integer PercentNumber, BigDecimal ServiceFrom, BigDecimal ServiceTo, BigDecimal RiseValue) {
       this.RiseScale = RiseScale;
       this.SysClient = SysClient;
       this.PercentNumber = PercentNumber;
       this.ServiceFrom = ServiceFrom;
       this.ServiceTo = ServiceTo;
       this.RiseValue = RiseValue;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_RISE_PERCENT_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RISESCALE_ID")
    public RiseScale getRiseScale() {
        return this.RiseScale;
    }
    
    public void setRiseScale(RiseScale RiseScale) {
        this.RiseScale = RiseScale;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="PERCENT_NUMBER", columnDefinition="INTEGER")
    public Integer getPercentNumber() {
        return this.PercentNumber;
    }
    
    public void setPercentNumber(Integer PercentNumber) {
        this.PercentNumber = PercentNumber;
    }

    
    @Column(name="SERVICE_FROM", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getServiceFrom() {
        return this.ServiceFrom;
    }
    
    public void setServiceFrom(BigDecimal ServiceFrom) {
        this.ServiceFrom = ServiceFrom;
    }

    
    @Column(name="SERVICE_TO", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getServiceTo() {
        return this.ServiceTo;
    }
    
    public void setServiceTo(BigDecimal ServiceTo) {
        this.ServiceTo = ServiceTo;
    }

    
    @Column(name="RISE_VALUE", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getRiseValue() {
        return this.RiseValue;
    }
    
    public void setRiseValue(BigDecimal RiseValue) {
        this.RiseValue = RiseValue;
    }




}


