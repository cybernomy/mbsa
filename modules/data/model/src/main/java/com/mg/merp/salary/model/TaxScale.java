package com.mg.merp.salary.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
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
 * TaxScale generated by hbm2java
 */
@Entity
@Table(name="SAL_TAXSCALE"
)
public class TaxScale extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private TaxHead TaxHead;
     private SysClient SysClient;
     private Integer SNumber;
     private Date BeginDate;
     private EnumUserType TaxPayer;
     private String SName;
     private Set<TaxRate> SetOfSalTaxRate = new HashSet<TaxRate>(0);

    public TaxScale() {
    }

	
    public TaxScale(Integer SNumber, Date BeginDate) {
        this.SNumber = SNumber;
        this.BeginDate = BeginDate;
    }
    public TaxScale(TaxHead TaxHead, SysClient SysClient, Integer SNumber, Date BeginDate, EnumUserType TaxPayer, String SName, Set<TaxRate> SetOfSalTaxRate) {
       this.TaxHead = TaxHead;
       this.SysClient = SysClient;
       this.SNumber = SNumber;
       this.BeginDate = BeginDate;
       this.TaxPayer = TaxPayer;
       this.SName = SName;
       this.SetOfSalTaxRate = SetOfSalTaxRate;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SAL_TAXSCALE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAXHEAD_ID")
    public TaxHead getTaxHead() {
        return this.TaxHead;
    }
    
    public void setTaxHead(TaxHead TaxHead) {
        this.TaxHead = TaxHead;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="SNUMBER", nullable=false, columnDefinition="INTEGER")
    public Integer getSNumber() {
        return this.SNumber;
    }
    
    public void setSNumber(Integer SNumber) {
        this.SNumber = SNumber;
    }

    
    @Column(name="BEGINDATE", nullable=false, columnDefinition="TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }
    
    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

    
    @Column(name="TAXPAYER", columnDefinition="INTEGER")
    public EnumUserType getTaxPayer() {
        return this.TaxPayer;
    }
    
    public void setTaxPayer(EnumUserType TaxPayer) {
        this.TaxPayer = TaxPayer;
    }

    
    @Column(name="SNAME", columnDefinition="VARCHAR", length=80)
    public String getSName() {
        return this.SName;
    }
    
    public void setSName(String SName) {
        this.SName = SName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="TaxScale")
    public Set<TaxRate> getSetOfSalTaxRate() {
        return this.SetOfSalTaxRate;
    }
    
    public void setSetOfSalTaxRate(Set<TaxRate> SetOfSalTaxRate) {
        this.SetOfSalTaxRate = SetOfSalTaxRate;
    }




}


