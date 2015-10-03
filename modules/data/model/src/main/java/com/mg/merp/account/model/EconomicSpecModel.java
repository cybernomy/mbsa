package com.mg.merp.account.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.TaxGroup;
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
 * EconomicSpecModel generated by hbm2java
 */
@Entity
@Table(name="ECONOMICSPECMODEL"
)
public class EconomicSpecModel extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private EconomicSpecFeature Feature1;
     private Catalog Catalog;
     private EconomicSpecFeature Feature2;
     private AnlPlan AnlKt2;
     private AccPlan AccKt;
     private Repository SumAlg;
     private AnlPlan AnlDb2;
     private TaxGroup TaxGroup;
     private EconomicSpecFeature Feature3;
     private EconomicSpecFeature Feature4;
     private AnlPlan AnlKt3;
     private EconomicOperModel EconomicOperModel;
     private SysClient SysClient;
     private AnlPlan AnlDb3;
     private AnlPlan AnlDb1;
     private AnlPlan AnlDb4;
     private AnlPlan AnlKt5;
     private AccPlan AccDb;
     private AnlPlan AnlDb5;
     private Repository QtyAlg;
     private AnlPlan AnlKt1;
     private EconomicSpecFeature Feature5;
     private AnlPlan AnlKt4;
     private CatalogFolder EntryFolder;
     private BigDecimal Quantity;
     private BigDecimal SummaNat;
     private BigDecimal SummaCur;
     private BigDecimal CurCource;
     private String SummaFormula;
     private String QuanFormula;
     private EnumUserType EntryGoodType;

    public EconomicSpecModel() {
    }

    public EconomicSpecModel(EconomicSpecFeature Feature1, Catalog Catalog, EconomicSpecFeature Feature2, AnlPlan AnlKt2, AccPlan AccKt, Repository SumAlg, AnlPlan AnlDb2, TaxGroup TaxGroup, EconomicSpecFeature Feature3, EconomicSpecFeature Feature4, AnlPlan AnlKt3, EconomicOperModel EconomicOperModel, SysClient SysClient, AnlPlan AnlDb3, AnlPlan AnlDb1, AnlPlan AnlDb4, AnlPlan AnlKt5, AccPlan AccDb, AnlPlan AnlDb5, Repository QtyAlg, AnlPlan AnlKt1, EconomicSpecFeature Feature5, AnlPlan AnlKt4, CatalogFolder EntryFolder, BigDecimal Quantity, BigDecimal SummaNat, BigDecimal SummaCur, BigDecimal CurCource, String SummaFormula, String QuanFormula, EnumUserType EntryGoodType) {
       this.Feature1 = Feature1;
       this.Catalog = Catalog;
       this.Feature2 = Feature2;
       this.AnlKt2 = AnlKt2;
       this.AccKt = AccKt;
       this.SumAlg = SumAlg;
       this.AnlDb2 = AnlDb2;
       this.TaxGroup = TaxGroup;
       this.Feature3 = Feature3;
       this.Feature4 = Feature4;
       this.AnlKt3 = AnlKt3;
       this.EconomicOperModel = EconomicOperModel;
       this.SysClient = SysClient;
       this.AnlDb3 = AnlDb3;
       this.AnlDb1 = AnlDb1;
       this.AnlDb4 = AnlDb4;
       this.AnlKt5 = AnlKt5;
       this.AccDb = AccDb;
       this.AnlDb5 = AnlDb5;
       this.QtyAlg = QtyAlg;
       this.AnlKt1 = AnlKt1;
       this.Feature5 = Feature5;
       this.AnlKt4 = AnlKt4;
       this.EntryFolder = EntryFolder;
       this.Quantity = Quantity;
       this.SummaNat = SummaNat;
       this.SummaCur = SummaCur;
       this.CurCource = CurCource;
       this.SummaFormula = SummaFormula;
       this.QuanFormula = QuanFormula;
       this.EntryGoodType = EntryGoodType;
    }
   
     @SequenceGenerator(name="generator", sequenceName="ECONOMICSPECMODEL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEATURE1_ID")
    public EconomicSpecFeature getFeature1() {
        return this.Feature1;
    }
    
    public void setFeature1(EconomicSpecFeature Feature1) {
        this.Feature1 = Feature1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }
    
    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEATURE2_ID")
    public EconomicSpecFeature getFeature2() {
        return this.Feature2;
    }
    
    public void setFeature2(EconomicSpecFeature Feature2) {
        this.Feature2 = Feature2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLKT2_ID")
    public AnlPlan getAnlKt2() {
        return this.AnlKt2;
    }
    
    public void setAnlKt2(AnlPlan AnlKt2) {
        this.AnlKt2 = AnlKt2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCKT_ID")
    public AccPlan getAccKt() {
        return this.AccKt;
    }
    
    public void setAccKt(AccPlan AccKt) {
        this.AccKt = AccKt;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SUMALG_ID")
    public Repository getSumAlg() {
        return this.SumAlg;
    }
    
    public void setSumAlg(Repository SumAlg) {
        this.SumAlg = SumAlg;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLDB2_ID")
    public AnlPlan getAnlDb2() {
        return this.AnlDb2;
    }
    
    public void setAnlDb2(AnlPlan AnlDb2) {
        this.AnlDb2 = AnlDb2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTRYTAXGROUP")
    public TaxGroup getTaxGroup() {
        return this.TaxGroup;
    }
    
    public void setTaxGroup(TaxGroup TaxGroup) {
        this.TaxGroup = TaxGroup;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEATURE3_ID")
    public EconomicSpecFeature getFeature3() {
        return this.Feature3;
    }
    
    public void setFeature3(EconomicSpecFeature Feature3) {
        this.Feature3 = Feature3;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEATURE4_ID")
    public EconomicSpecFeature getFeature4() {
        return this.Feature4;
    }
    
    public void setFeature4(EconomicSpecFeature Feature4) {
        this.Feature4 = Feature4;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLKT3_ID")
    public AnlPlan getAnlKt3() {
        return this.AnlKt3;
    }
    
    public void setAnlKt3(AnlPlan AnlKt3) {
        this.AnlKt3 = AnlKt3;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ECONOMICOPER_ID")
    public EconomicOperModel getEconomicOperModel() {
        return this.EconomicOperModel;
    }
    
    public void setEconomicOperModel(EconomicOperModel EconomicOperModel) {
        this.EconomicOperModel = EconomicOperModel;
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
    @JoinColumn(name="ANLDB3_ID")
    public AnlPlan getAnlDb3() {
        return this.AnlDb3;
    }
    
    public void setAnlDb3(AnlPlan AnlDb3) {
        this.AnlDb3 = AnlDb3;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLDB1_ID")
    public AnlPlan getAnlDb1() {
        return this.AnlDb1;
    }
    
    public void setAnlDb1(AnlPlan AnlDb1) {
        this.AnlDb1 = AnlDb1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLDB4_ID")
    public AnlPlan getAnlDb4() {
        return this.AnlDb4;
    }
    
    public void setAnlDb4(AnlPlan AnlDb4) {
        this.AnlDb4 = AnlDb4;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLKT5_ID")
    public AnlPlan getAnlKt5() {
        return this.AnlKt5;
    }
    
    public void setAnlKt5(AnlPlan AnlKt5) {
        this.AnlKt5 = AnlKt5;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCDB_ID")
    public AccPlan getAccDb() {
        return this.AccDb;
    }
    
    public void setAccDb(AccPlan AccDb) {
        this.AccDb = AccDb;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLDB5_ID")
    public AnlPlan getAnlDb5() {
        return this.AnlDb5;
    }
    
    public void setAnlDb5(AnlPlan AnlDb5) {
        this.AnlDb5 = AnlDb5;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QTYALG_ID")
    public Repository getQtyAlg() {
        return this.QtyAlg;
    }
    
    public void setQtyAlg(Repository QtyAlg) {
        this.QtyAlg = QtyAlg;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLKT1_ID")
    public AnlPlan getAnlKt1() {
        return this.AnlKt1;
    }
    
    public void setAnlKt1(AnlPlan AnlKt1) {
        this.AnlKt1 = AnlKt1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FEATURE5_ID")
    public EconomicSpecFeature getFeature5() {
        return this.Feature5;
    }
    
    public void setFeature5(EconomicSpecFeature Feature5) {
        this.Feature5 = Feature5;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANLKT4_ID")
    public AnlPlan getAnlKt4() {
        return this.AnlKt4;
    }
    
    public void setAnlKt4(AnlPlan AnlKt4) {
        this.AnlKt4 = AnlKt4;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTRYFOLDER")
    public CatalogFolder getEntryFolder() {
        return this.EntryFolder;
    }
    
    public void setEntryFolder(CatalogFolder EntryFolder) {
        this.EntryFolder = EntryFolder;
    }

    
    @Column(name="QUANTITY", columnDefinition="NUMERIC", precision=15, scale=3)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }
    
    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    
    @Column(name="SUMMANAT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSummaNat() {
        return this.SummaNat;
    }
    
    public void setSummaNat(BigDecimal SummaNat) {
        this.SummaNat = SummaNat;
    }

    
    @Column(name="SUMMACUR", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSummaCur() {
        return this.SummaCur;
    }
    
    public void setSummaCur(BigDecimal SummaCur) {
        this.SummaCur = SummaCur;
    }

    
    @Column(name="CURCOURCE", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getCurCource() {
        return this.CurCource;
    }
    
    public void setCurCource(BigDecimal CurCource) {
        this.CurCource = CurCource;
    }

    
    @Column(name="SummaFormula", columnDefinition="VARCHAR", length=2048)
    public String getSummaFormula() {
        return this.SummaFormula;
    }
    
    public void setSummaFormula(String SummaFormula) {
        this.SummaFormula = SummaFormula;
    }

    
    @Column(name="QUANFORMULA", columnDefinition="VARCHAR", length=2048)
    public String getQuanFormula() {
        return this.QuanFormula;
    }
    
    public void setQuanFormula(String QuanFormula) {
        this.QuanFormula = QuanFormula;
    }

    
    @Column(name="ENTRYGOODTYPE", columnDefinition="SMALLINT")
    public EnumUserType getEntryGoodType() {
        return this.EntryGoodType;
    }
    
    public void setEntryGoodType(EnumUserType EntryGoodType) {
        this.EntryGoodType = EntryGoodType;
    }




}

