package com.mg.merp.finance.model;
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
 * Specification generated by hbm2java
 */
@Entity
@Table(name="FINSPEC"
)
public class Specification extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private TurnFeature DstTurnFeat;
     private TurnAccount DstTurnAcc;
     private Specification Parent;
     private FinOperation FinOper;
     private TurnAccount SrcTurnAcc;
     private SysClient SysClient;
     private Account DstAcc;
     private Account SrcAcc;
     private TurnFeature SrcTurnFeat;
     private Integer SrcAnl1;
     private Integer SrcAnl2;
     private Integer SrcAnl3;
     private Integer SrcAnl4;
     private Integer SrcAnl5;
     private Integer DstAnl1;
     private Integer DstAnl2;
     private Integer DstAnl3;
     private Integer DstAnl4;
     private Integer DstAnl5;
     private BigDecimal SumNat;
     private BigDecimal SumCur;
     private boolean Planned;

    public Specification() {
    }

    public Specification(TurnFeature DstTurnFeat, TurnAccount DstTurnAcc, Specification Parent, FinOperation FinOper, TurnAccount SrcTurnAcc, SysClient SysClient, Account DstAcc, Account SrcAcc, TurnFeature SrcTurnFeat, Integer SrcAnl1, Integer SrcAnl2, Integer SrcAnl3, Integer SrcAnl4, Integer SrcAnl5, Integer DstAnl1, Integer DstAnl2, Integer DstAnl3, Integer DstAnl4, Integer DstAnl5, BigDecimal SumNat, BigDecimal SumCur, boolean Planned) {
       this.DstTurnFeat = DstTurnFeat;
       this.DstTurnAcc = DstTurnAcc;
       this.Parent = Parent;
       this.FinOper = FinOper;
       this.SrcTurnAcc = SrcTurnAcc;
       this.SysClient = SysClient;
       this.DstAcc = DstAcc;
       this.SrcAcc = SrcAcc;
       this.SrcTurnFeat = SrcTurnFeat;
       this.SrcAnl1 = SrcAnl1;
       this.SrcAnl2 = SrcAnl2;
       this.SrcAnl3 = SrcAnl3;
       this.SrcAnl4 = SrcAnl4;
       this.SrcAnl5 = SrcAnl5;
       this.DstAnl1 = DstAnl1;
       this.DstAnl2 = DstAnl2;
       this.DstAnl3 = DstAnl3;
       this.DstAnl4 = DstAnl4;
       this.DstAnl5 = DstAnl5;
       this.SumNat = SumNat;
       this.SumCur = SumCur;
       this.Planned = Planned;
    }
   
     @SequenceGenerator(name="generator", sequenceName="FINSPEC_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DSTTURNFEAT_ID")
    public TurnFeature getDstTurnFeat() {
        return this.DstTurnFeat;
    }
    
    public void setDstTurnFeat(TurnFeature DstTurnFeat) {
        this.DstTurnFeat = DstTurnFeat;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DSTTURNACC_ID")
    public TurnAccount getDstTurnAcc() {
        return this.DstTurnAcc;
    }
    
    public void setDstTurnAcc(TurnAccount DstTurnAcc) {
        this.DstTurnAcc = DstTurnAcc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    public Specification getParent() {
        return this.Parent;
    }
    
    public void setParent(Specification Parent) {
        this.Parent = Parent;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FINOPER_ID")
    public FinOperation getFinOper() {
        return this.FinOper;
    }
    
    public void setFinOper(FinOperation FinOper) {
        this.FinOper = FinOper;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SRCTURNACC_ID")
    public TurnAccount getSrcTurnAcc() {
        return this.SrcTurnAcc;
    }
    
    public void setSrcTurnAcc(TurnAccount SrcTurnAcc) {
        this.SrcTurnAcc = SrcTurnAcc;
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
    @JoinColumn(name="DSTACC_ID")
    public Account getDstAcc() {
        return this.DstAcc;
    }
    
    public void setDstAcc(Account DstAcc) {
        this.DstAcc = DstAcc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SRCACC_ID")
    public Account getSrcAcc() {
        return this.SrcAcc;
    }
    
    public void setSrcAcc(Account SrcAcc) {
        this.SrcAcc = SrcAcc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SRCTURNFEAT_ID")
    public TurnFeature getSrcTurnFeat() {
        return this.SrcTurnFeat;
    }
    
    public void setSrcTurnFeat(TurnFeature SrcTurnFeat) {
        this.SrcTurnFeat = SrcTurnFeat;
    }

    
    @Column(name="SRCANL1_ID", columnDefinition="INTEGER")
    public Integer getSrcAnl1() {
        return this.SrcAnl1;
    }
    
    public void setSrcAnl1(Integer SrcAnl1) {
        this.SrcAnl1 = SrcAnl1;
    }

    
    @Column(name="SRCANL2_ID", columnDefinition="INTEGER")
    public Integer getSrcAnl2() {
        return this.SrcAnl2;
    }
    
    public void setSrcAnl2(Integer SrcAnl2) {
        this.SrcAnl2 = SrcAnl2;
    }

    
    @Column(name="SRCANL3_ID", columnDefinition="INTEGER")
    public Integer getSrcAnl3() {
        return this.SrcAnl3;
    }
    
    public void setSrcAnl3(Integer SrcAnl3) {
        this.SrcAnl3 = SrcAnl3;
    }

    
    @Column(name="SRCANL4_ID", columnDefinition="INTEGER")
    public Integer getSrcAnl4() {
        return this.SrcAnl4;
    }
    
    public void setSrcAnl4(Integer SrcAnl4) {
        this.SrcAnl4 = SrcAnl4;
    }

    
    @Column(name="SRCANL5_ID", columnDefinition="INTEGER")
    public Integer getSrcAnl5() {
        return this.SrcAnl5;
    }
    
    public void setSrcAnl5(Integer SrcAnl5) {
        this.SrcAnl5 = SrcAnl5;
    }

    
    @Column(name="DSTANL1_ID", columnDefinition="INTEGER")
    public Integer getDstAnl1() {
        return this.DstAnl1;
    }
    
    public void setDstAnl1(Integer DstAnl1) {
        this.DstAnl1 = DstAnl1;
    }

    
    @Column(name="DSTANL2_ID", columnDefinition="INTEGER")
    public Integer getDstAnl2() {
        return this.DstAnl2;
    }
    
    public void setDstAnl2(Integer DstAnl2) {
        this.DstAnl2 = DstAnl2;
    }

    
    @Column(name="DSTANL3_ID", columnDefinition="INTEGER")
    public Integer getDstAnl3() {
        return this.DstAnl3;
    }
    
    public void setDstAnl3(Integer DstAnl3) {
        this.DstAnl3 = DstAnl3;
    }

    
    @Column(name="DSTANL4_ID", columnDefinition="INTEGER")
    public Integer getDstAnl4() {
        return this.DstAnl4;
    }
    
    public void setDstAnl4(Integer DstAnl4) {
        this.DstAnl4 = DstAnl4;
    }

    
    @Column(name="DSTANL5_ID", columnDefinition="INTEGER")
    public Integer getDstAnl5() {
        return this.DstAnl5;
    }
    
    public void setDstAnl5(Integer DstAnl5) {
        this.DstAnl5 = DstAnl5;
    }

    
    @Column(name="SUMNAT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumNat() {
        return this.SumNat;
    }
    
    public void setSumNat(BigDecimal SumNat) {
        this.SumNat = SumNat;
    }

    
    @Column(name="SUMCUR", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumCur() {
        return this.SumCur;
    }
    
    public void setSumCur(BigDecimal SumCur) {
        this.SumCur = SumCur;
    }

    
    @Column(name="PLANNED", columnDefinition="SMALLINT")
    public boolean isPlanned() {
        return this.Planned;
    }
    
    public void setPlanned(boolean Planned) {
        this.Planned = Planned;
    }




}


