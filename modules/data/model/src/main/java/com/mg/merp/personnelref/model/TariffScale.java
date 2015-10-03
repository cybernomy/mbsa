package com.mg.merp.personnelref.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.baiengine.model.Repository;
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
 * TariffScale generated by hbm2java
 */
@Entity
@Table(name="PREF_TARIFF_SCALE"
)
public class TariffScale extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private Repository FirstClassAlg;
     private String SCode;
     private String SName;
     private TariffingScaleType SType;
     private Date BeginDate;
     private Set<TariffScaleClass> SetOfPrefTariffScaleClass = new HashSet<TariffScaleClass>(0);

    public TariffScale() {
    }

	
    public TariffScale(String SCode, Date BeginDate) {
        this.SCode = SCode;
        this.BeginDate = BeginDate;
    }
    public TariffScale(SysClient SysClient, Repository FirstClassAlg, String SCode, String SName, TariffingScaleType SType, Date BeginDate, Set<TariffScaleClass> SetOfPrefTariffScaleClass) {
       this.SysClient = SysClient;
       this.FirstClassAlg = FirstClassAlg;
       this.SCode = SCode;
       this.SName = SName;
       this.SType = SType;
       this.BeginDate = BeginDate;
       this.SetOfPrefTariffScaleClass = SetOfPrefTariffScaleClass;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_TARIFF_SCALE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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
    @JoinColumn(name="FIRST_CLASS_ALG_ID")
    public Repository getFirstClassAlg() {
        return this.FirstClassAlg;
    }
    
    public void setFirstClassAlg(Repository FirstClassAlg) {
        this.FirstClassAlg = FirstClassAlg;
    }

    
    @Column(name="SCODE", nullable=false, columnDefinition="CHAR", length=20)
    public String getSCode() {
        return this.SCode;
    }
    
    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    
    @Column(name="SNAME", columnDefinition="VARCHAR", length=80)
    public String getSName() {
        return this.SName;
    }
    
    public void setSName(String SName) {
        this.SName = SName;
    }

    
    @Column(name="STYPE", columnDefinition="SMALLINT")
    public TariffingScaleType getSType() {
        return this.SType;
    }
    
    public void setSType(TariffingScaleType SType) {
        this.SType = SType;
    }

    
    @Column(name="BEGINDATE", nullable=false, columnDefinition="TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }
    
    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="TariffScale")
    public Set<TariffScaleClass> getSetOfPrefTariffScaleClass() {
        return this.SetOfPrefTariffScaleClass;
    }
    
    public void setSetOfPrefTariffScaleClass(Set<TariffScaleClass> SetOfPrefTariffScaleClass) {
        this.SetOfPrefTariffScaleClass = SetOfPrefTariffScaleClass;
    }




}


