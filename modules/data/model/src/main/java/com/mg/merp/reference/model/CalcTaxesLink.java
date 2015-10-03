package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
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
 * CalcTaxesLink generated by hbm2java
 */
@Entity
@Table(name="CALCTAXESLINK"
)
public class CalcTaxesLink extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Tax Tax;
     private SysClient SysClient;
     private CalcTaxesKind CalcTaxesKind;
     private Short FeeOrder;
     private boolean Included;
     private CalcTaxesSubject Subject;

    public CalcTaxesLink() {
    }

    public CalcTaxesLink(Tax Tax, SysClient SysClient, CalcTaxesKind CalcTaxesKind, Short FeeOrder, boolean Included, CalcTaxesSubject Subject) {
       this.Tax = Tax;
       this.SysClient = SysClient;
       this.CalcTaxesKind = CalcTaxesKind;
       this.FeeOrder = FeeOrder;
       this.Included = Included;
       this.Subject = Subject;
    }
   
     @SequenceGenerator(name="generator", sequenceName="CALCTAXESLINK_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="KIND_ID")
    public CalcTaxesKind getCalcTaxesKind() {
        return this.CalcTaxesKind;
    }
    
    public void setCalcTaxesKind(CalcTaxesKind CalcTaxesKind) {
        this.CalcTaxesKind = CalcTaxesKind;
    }

    
    @Column(name="FEEORDER", columnDefinition="SMALLINT")
    public Short getFeeOrder() {
        return this.FeeOrder;
    }
    
    public void setFeeOrder(Short FeeOrder) {
        this.FeeOrder = FeeOrder;
    }

    
    @Column(name="INCLUDED", columnDefinition="SMALLINT")
    public boolean isIncluded() {
        return this.Included;
    }
    
    public void setIncluded(boolean Included) {
        this.Included = Included;
    }

    
    @Column(name="SUBJECT", columnDefinition="SMALLINT")
    public CalcTaxesSubject getSubject() {
        return this.Subject;
    }
    
    public void setSubject(CalcTaxesSubject Subject) {
        this.Subject = Subject;
    }




}


