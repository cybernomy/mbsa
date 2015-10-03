package com.mg.merp.salary.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * CalcListFeeParam generated by hbm2java
 */
@Entity
@Table(name="SAL_CALC_LIST_FEE_PARAM"
)
public class CalcListFeeParam extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private CalcListFee CalcListFee;
     private FeeRefParam FeeRefParam;
     private String ParamValue;

    public CalcListFeeParam() {
    }

    public CalcListFeeParam(SysClient SysClient, CalcListFee CalcListFee, FeeRefParam FeeRefParam, String ParamValue) {
       this.SysClient = SysClient;
       this.CalcListFee = CalcListFee;
       this.FeeRefParam = FeeRefParam;
       this.ParamValue = ParamValue;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SAL_CALC_LIST_FEE_PARAM_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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
    @JoinColumn(name="CALC_LIST_FEE_ID")
    public CalcListFee getCalcListFee() {
        return this.CalcListFee;
    }
    
    public void setCalcListFee(CalcListFee CalcListFee) {
        this.CalcListFee = CalcListFee;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEE_REF_PARAM_ID")
    public FeeRefParam getFeeRefParam() {
        return this.FeeRefParam;
    }
    
    public void setFeeRefParam(FeeRefParam FeeRefParam) {
        this.FeeRefParam = FeeRefParam;
    }

    
    @Column(name="PARAM_VALUE", columnDefinition="VARCHAR", length=80)
    public String getParamValue() {
        return this.ParamValue;
    }
    
    public void setParamValue(String ParamValue) {
        this.ParamValue = ParamValue;
    }




}

