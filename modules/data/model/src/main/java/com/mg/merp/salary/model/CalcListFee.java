package com.mg.merp.salary.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.model.CostsAnl;
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

/**
 * CalcListFee generated by hbm2java
 */
@Entity
@Table(name="SAL_CALC_LIST_FEE"
)
public class CalcListFee extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private CostsAnl CostsAnl1;
     private CostsAnl CostsAnl2;
     private CostsAnl CostsAnl3;
     private CalcListSection CalcListSection;
     private CostsAnl CostsAnl4;
     private FeeModel FeeModel;
     private SysClient SysClient;
     private CostsAnl CostsAnl5;
     private Date BeginDate;
     private Date EndDate;
     private Date PeriodBeginDate;
     private Date PeriodEndDate;
     private BigDecimal Summa;
     private boolean NeedParams;
     private boolean IsCalculated;
     private boolean DontRecalc;
     private Set<CalcListFeeParam> CalcListFeeParams = new HashSet<CalcListFeeParam>(0);

    public CalcListFee() {
    }

    public CalcListFee(CostsAnl CostsAnl1, CostsAnl CostsAnl2, CostsAnl CostsAnl3, CalcListSection CalcListSection, CostsAnl CostsAnl4, FeeModel FeeModel, SysClient SysClient, CostsAnl CostsAnl5, Date BeginDate, Date EndDate, Date PeriodBeginDate, Date PeriodEndDate, BigDecimal Summa, boolean NeedParams, boolean IsCalculated, boolean DontRecalc, Set<CalcListFeeParam> CalcListFeeParams) {
       this.CostsAnl1 = CostsAnl1;
       this.CostsAnl2 = CostsAnl2;
       this.CostsAnl3 = CostsAnl3;
       this.CalcListSection = CalcListSection;
       this.CostsAnl4 = CostsAnl4;
       this.FeeModel = FeeModel;
       this.SysClient = SysClient;
       this.CostsAnl5 = CostsAnl5;
       this.BeginDate = BeginDate;
       this.EndDate = EndDate;
       this.PeriodBeginDate = PeriodBeginDate;
       this.PeriodEndDate = PeriodEndDate;
       this.Summa = Summa;
       this.NeedParams = NeedParams;
       this.IsCalculated = IsCalculated;
       this.DontRecalc = DontRecalc;
       this.CalcListFeeParams = CalcListFeeParams;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SAL_CALC_LIST_FEE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL1_ID")
    public CostsAnl getCostsAnl1() {
        return this.CostsAnl1;
    }
    
    public void setCostsAnl1(CostsAnl CostsAnl1) {
        this.CostsAnl1 = CostsAnl1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL2_ID")
    public CostsAnl getCostsAnl2() {
        return this.CostsAnl2;
    }
    
    public void setCostsAnl2(CostsAnl CostsAnl2) {
        this.CostsAnl2 = CostsAnl2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL3_ID")
    public CostsAnl getCostsAnl3() {
        return this.CostsAnl3;
    }
    
    public void setCostsAnl3(CostsAnl CostsAnl3) {
        this.CostsAnl3 = CostsAnl3;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CALC_LIST_SECTION_ID")
    public CalcListSection getCalcListSection() {
        return this.CalcListSection;
    }
    
    public void setCalcListSection(CalcListSection CalcListSection) {
        this.CalcListSection = CalcListSection;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL4_ID")
    public CostsAnl getCostsAnl4() {
        return this.CostsAnl4;
    }
    
    public void setCostsAnl4(CostsAnl CostsAnl4) {
        this.CostsAnl4 = CostsAnl4;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEE_MODEL_ID")
    public FeeModel getFeeModel() {
        return this.FeeModel;
    }
    
    public void setFeeModel(FeeModel FeeModel) {
        this.FeeModel = FeeModel;
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
    @JoinColumn(name="COSTS_ANL5_ID")
    public CostsAnl getCostsAnl5() {
        return this.CostsAnl5;
    }
    
    public void setCostsAnl5(CostsAnl CostsAnl5) {
        this.CostsAnl5 = CostsAnl5;
    }

    
    @Column(name="BEGINDATE", columnDefinition="TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }
    
    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

    
    @Column(name="ENDDATE", columnDefinition="TIMESTAMP")
    public Date getEndDate() {
        return this.EndDate;
    }
    
    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    
    @Column(name="PERIOD_BEGINDATE", columnDefinition="TIMESTAMP")
    public Date getPeriodBeginDate() {
        return this.PeriodBeginDate;
    }
    
    public void setPeriodBeginDate(Date PeriodBeginDate) {
        this.PeriodBeginDate = PeriodBeginDate;
    }

    
    @Column(name="PERIOD_ENDDATE", columnDefinition="TIMESTAMP")
    public Date getPeriodEndDate() {
        return this.PeriodEndDate;
    }
    
    public void setPeriodEndDate(Date PeriodEndDate) {
        this.PeriodEndDate = PeriodEndDate;
    }

    
    @Column(name="SUMMA", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumma() {
        return this.Summa;
    }
    
    public void setSumma(BigDecimal Summa) {
        this.Summa = Summa;
    }

    
    @Column(name="NEED_PARAMS", columnDefinition="SMALLINT")
    public boolean isNeedParams() {
        return this.NeedParams;
    }
    
    public void setNeedParams(boolean NeedParams) {
        this.NeedParams = NeedParams;
    }

    
    @Column(name="IS_CALCULATED", columnDefinition="SMALLINT")
    public boolean isIsCalculated() {
        return this.IsCalculated;
    }
    
    public void setIsCalculated(boolean IsCalculated) {
        this.IsCalculated = IsCalculated;
    }

    
    @Column(name="DONT_RECALC", columnDefinition="SMALLINT")
    public boolean isDontRecalc() {
        return this.DontRecalc;
    }
    
    public void setDontRecalc(boolean DontRecalc) {
        this.DontRecalc = DontRecalc;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="CalcListFee")
    public Set<CalcListFeeParam> getCalcListFeeParams() {
        return this.CalcListFeeParams;
    }
    
    public void setCalcListFeeParams(Set<CalcListFeeParam> CalcListFeeParams) {
        this.CalcListFeeParams = CalcListFeeParams;
    }




}


