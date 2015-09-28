package com.mg.merp.lbschedule.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;
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
 * Item generated by hbm2java
 */
@Entity
@Table(name="LS_ITEM"
)
public class Item extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Contractor From;
     private CurrencyRateAuthority CurRateAuthority;
     private PriceType PriceType;
     private Item DateRelItem;
     private ItemKind ItemKind;
     private Contractor To;
     private PriceListHead PriceListHead;
     private Item SumRelItem;
     private Schedule Schedule;
     private PmcResource ResourceTo;
     private SysClient SysClient;
     private PmcResource ResourceFrom;
     private CurrencyRateType CurRateType;
     private Currency CurCode;
     private String Comments;
     private Short Num;
     private EnumUserType Status;
     private EnumUserType ToSource;
     private EnumUserType FromSource;
     private EnumUserType SpecSource;
     private boolean HasSpec;
     private Date AbsDate;
     private boolean IsAbsDate;
     private EnumUserType DateOffSetKind;
     private Integer DateOffSet;
     private BigDecimal AbsSum;
     private boolean IsAbsSum;
     private Date ResultDate;
     private boolean IsRelFact;
     private BigDecimal FactSum;
     private BigDecimal ResultSum;
     private boolean IsDateRelDoc;
     private boolean IsSumRelDoc;
     private BigDecimal Perc;
     private Date ResultDateEnd;
     private boolean IsDateRelEnd;
     private Set<ItemSpec> LsItemSpecs = new HashSet<ItemSpec>(0);

    public Item() {
    }

    public Item(Contractor From, CurrencyRateAuthority CurRateAuthority, PriceType PriceType, Item DateRelItem, ItemKind ItemKind, Contractor To, PriceListHead PriceListHead, Item SumRelItem, Schedule Schedule, PmcResource ResourceTo, SysClient SysClient, PmcResource ResourceFrom, CurrencyRateType CurRateType, Currency CurCode, String Comments, Short Num, EnumUserType Status, EnumUserType ToSource, EnumUserType FromSource, EnumUserType SpecSource, boolean HasSpec, Date AbsDate, boolean IsAbsDate, EnumUserType DateOffSetKind, Integer DateOffSet, BigDecimal AbsSum, boolean IsAbsSum, Date ResultDate, boolean IsRelFact, BigDecimal FactSum, BigDecimal ResultSum, boolean IsDateRelDoc, boolean IsSumRelDoc, BigDecimal Perc, Date ResultDateEnd, boolean IsDateRelEnd, Set<ItemSpec> LsItemSpecs) {
       this.From = From;
       this.CurRateAuthority = CurRateAuthority;
       this.PriceType = PriceType;
       this.DateRelItem = DateRelItem;
       this.ItemKind = ItemKind;
       this.To = To;
       this.PriceListHead = PriceListHead;
       this.SumRelItem = SumRelItem;
       this.Schedule = Schedule;
       this.ResourceTo = ResourceTo;
       this.SysClient = SysClient;
       this.ResourceFrom = ResourceFrom;
       this.CurRateType = CurRateType;
       this.CurCode = CurCode;
       this.Comments = Comments;
       this.Num = Num;
       this.Status = Status;
       this.ToSource = ToSource;
       this.FromSource = FromSource;
       this.SpecSource = SpecSource;
       this.HasSpec = HasSpec;
       this.AbsDate = AbsDate;
       this.IsAbsDate = IsAbsDate;
       this.DateOffSetKind = DateOffSetKind;
       this.DateOffSet = DateOffSet;
       this.AbsSum = AbsSum;
       this.IsAbsSum = IsAbsSum;
       this.ResultDate = ResultDate;
       this.IsRelFact = IsRelFact;
       this.FactSum = FactSum;
       this.ResultSum = ResultSum;
       this.IsDateRelDoc = IsDateRelDoc;
       this.IsSumRelDoc = IsSumRelDoc;
       this.Perc = Perc;
       this.ResultDateEnd = ResultDateEnd;
       this.IsDateRelEnd = IsDateRelEnd;
       this.LsItemSpecs = LsItemSpecs;
    }
   
     @SequenceGenerator(name="generator", sequenceName="LS_ITEM_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ID")
    public Contractor getFrom() {
        return this.From;
    }
    
    public void setFrom(Contractor From) {
        this.From = From;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATEAUTHORITY_ID")
    public CurrencyRateAuthority getCurRateAuthority() {
        return this.CurRateAuthority;
    }
    
    public void setCurRateAuthority(CurrencyRateAuthority CurRateAuthority) {
        this.CurRateAuthority = CurRateAuthority;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRICETYPE_ID")
    public PriceType getPriceType() {
        return this.PriceType;
    }
    
    public void setPriceType(PriceType PriceType) {
        this.PriceType = PriceType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATERELITEM_ID")
    public Item getDateRelItem() {
        return this.DateRelItem;
    }
    
    public void setDateRelItem(Item DateRelItem) {
        this.DateRelItem = DateRelItem;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEMKIND_ID")
    public ItemKind getItemKind() {
        return this.ItemKind;
    }
    
    public void setItemKind(ItemKind ItemKind) {
        this.ItemKind = ItemKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ID")
    public Contractor getTo() {
        return this.To;
    }
    
    public void setTo(Contractor To) {
        this.To = To;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRICELISTHEAD_ID")
    public PriceListHead getPriceListHead() {
        return this.PriceListHead;
    }
    
    public void setPriceListHead(PriceListHead PriceListHead) {
        this.PriceListHead = PriceListHead;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SUMRELITEM_ID")
    public Item getSumRelItem() {
        return this.SumRelItem;
    }
    
    public void setSumRelItem(Item SumRelItem) {
        this.SumRelItem = SumRelItem;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SCHEDULE_ID")
    public Schedule getSchedule() {
        return this.Schedule;
    }
    
    public void setSchedule(Schedule Schedule) {
        this.Schedule = Schedule;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCETO_ID")
    public PmcResource getResourceTo() {
        return this.ResourceTo;
    }
    
    public void setResourceTo(PmcResource ResourceTo) {
        this.ResourceTo = ResourceTo;
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
    @JoinColumn(name="RESOURCEFROM_ID")
    public PmcResource getResourceFrom() {
        return this.ResourceFrom;
    }
    
    public void setResourceFrom(PmcResource ResourceFrom) {
        this.ResourceFrom = ResourceFrom;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATETYPE_ID")
    public CurrencyRateType getCurRateType() {
        return this.CurRateType;
    }
    
    public void setCurRateType(CurrencyRateType CurRateType) {
        this.CurRateType = CurRateType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURCODE")
    public Currency getCurCode() {
        return this.CurCode;
    }
    
    public void setCurCode(Currency CurCode) {
        this.CurCode = CurCode;
    }

    
    @Column(name="COMMENTS", columnDefinition="VARCHAR", length=256)
    public String getComments() {
        return this.Comments;
    }
    
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    
    @Column(name="NUM", columnDefinition="SMALLINT")
    public Short getNum() {
        return this.Num;
    }
    
    public void setNum(Short Num) {
        this.Num = Num;
    }

    
    @Column(name="STATUS", columnDefinition="SMALLINT")
    public EnumUserType getStatus() {
        return this.Status;
    }
    
    public void setStatus(EnumUserType Status) {
        this.Status = Status;
    }

    
    @Column(name="TOSOURCE", columnDefinition="SMALLINT")
    public EnumUserType getToSource() {
        return this.ToSource;
    }
    
    public void setToSource(EnumUserType ToSource) {
        this.ToSource = ToSource;
    }

    
    @Column(name="FROMSOURCE", columnDefinition="SMALLINT")
    public EnumUserType getFromSource() {
        return this.FromSource;
    }
    
    public void setFromSource(EnumUserType FromSource) {
        this.FromSource = FromSource;
    }

    
    @Column(name="SPECSOURCE", columnDefinition="SMALLINT")
    public EnumUserType getSpecSource() {
        return this.SpecSource;
    }
    
    public void setSpecSource(EnumUserType SpecSource) {
        this.SpecSource = SpecSource;
    }

    
    @Column(name="HAS_SPEC", columnDefinition="SMALLINT")
    public boolean isHasSpec() {
        return this.HasSpec;
    }
    
    public void setHasSpec(boolean HasSpec) {
        this.HasSpec = HasSpec;
    }

    
    @Column(name="ABS_DATE", columnDefinition="TIMESTAMP")
    public Date getAbsDate() {
        return this.AbsDate;
    }
    
    public void setAbsDate(Date AbsDate) {
        this.AbsDate = AbsDate;
    }

    
    @Column(name="IS_ABS_DATE", columnDefinition="SMALLINT")
    public boolean isIsAbsDate() {
        return this.IsAbsDate;
    }
    
    public void setIsAbsDate(boolean IsAbsDate) {
        this.IsAbsDate = IsAbsDate;
    }

    
    @Column(name="DATEOFFSETKIND", columnDefinition="SMALLINT")
    public EnumUserType getDateOffSetKind() {
        return this.DateOffSetKind;
    }
    
    public void setDateOffSetKind(EnumUserType DateOffSetKind) {
        this.DateOffSetKind = DateOffSetKind;
    }

    
    @Column(name="DATEOFFSET", columnDefinition="INTEGER")
    public Integer getDateOffSet() {
        return this.DateOffSet;
    }
    
    public void setDateOffSet(Integer DateOffSet) {
        this.DateOffSet = DateOffSet;
    }

    
    @Column(name="ABS_SUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getAbsSum() {
        return this.AbsSum;
    }
    
    public void setAbsSum(BigDecimal AbsSum) {
        this.AbsSum = AbsSum;
    }

    
    @Column(name="IS_ABS_SUM", columnDefinition="SMALLINT")
    public boolean isIsAbsSum() {
        return this.IsAbsSum;
    }
    
    public void setIsAbsSum(boolean IsAbsSum) {
        this.IsAbsSum = IsAbsSum;
    }

    
    @Column(name="RESULTDATE", columnDefinition="TIMESTAMP")
    public Date getResultDate() {
        return this.ResultDate;
    }
    
    public void setResultDate(Date ResultDate) {
        this.ResultDate = ResultDate;
    }

    
    @Column(name="IS_RELFACT", columnDefinition="SMALLINT")
    public boolean isIsRelFact() {
        return this.IsRelFact;
    }
    
    public void setIsRelFact(boolean IsRelFact) {
        this.IsRelFact = IsRelFact;
    }

    
    @Column(name="FACTSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getFactSum() {
        return this.FactSum;
    }
    
    public void setFactSum(BigDecimal FactSum) {
        this.FactSum = FactSum;
    }

    
    @Column(name="RESULTSUM", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getResultSum() {
        return this.ResultSum;
    }
    
    public void setResultSum(BigDecimal ResultSum) {
        this.ResultSum = ResultSum;
    }

    
    @Column(name="IS_DATERELDOC", columnDefinition="SMALLINT")
    public boolean isIsDateRelDoc() {
        return this.IsDateRelDoc;
    }
    
    public void setIsDateRelDoc(boolean IsDateRelDoc) {
        this.IsDateRelDoc = IsDateRelDoc;
    }

    
    @Column(name="IS_SUMRELDOC", columnDefinition="SMALLINT")
    public boolean isIsSumRelDoc() {
        return this.IsSumRelDoc;
    }
    
    public void setIsSumRelDoc(boolean IsSumRelDoc) {
        this.IsSumRelDoc = IsSumRelDoc;
    }

    
    @Column(name="PERC", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getPerc() {
        return this.Perc;
    }
    
    public void setPerc(BigDecimal Perc) {
        this.Perc = Perc;
    }

    
    @Column(name="RESULTDATEEND", columnDefinition="TIMESTAMP")
    public Date getResultDateEnd() {
        return this.ResultDateEnd;
    }
    
    public void setResultDateEnd(Date ResultDateEnd) {
        this.ResultDateEnd = ResultDateEnd;
    }

    
    @Column(name="IS_DATERELEND", columnDefinition="SMALLINT")
    public boolean isIsDateRelEnd() {
        return this.IsDateRelEnd;
    }
    
    public void setIsDateRelEnd(boolean IsDateRelEnd) {
        this.IsDateRelEnd = IsDateRelEnd;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Item")
    public Set<ItemSpec> getLsItemSpecs() {
        return this.LsItemSpecs;
    }
    
    public void setLsItemSpecs(Set<ItemSpec> LsItemSpecs) {
        this.LsItemSpecs = LsItemSpecs;
    }




}


