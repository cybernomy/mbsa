package com.mg.merp.document.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.CustomsDeclaration;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.TaxGroup;
import com.mg.merp.reference.model.TimePeriodKind;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
 * DocSpec generated by hbm2java
 */
@Entity
@Table(name = "DOCSPEC")
public class DocSpec extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private DocSpec OrderSpec;

    private Catalog Catalog;

    private TaxGroup TaxGroup;

    private Contractor DstMol;

    private Contractor SrcMol;

    private PriceListSpec PriceListSpec;

    private DocHead DocHead;

    private Measure Measure2;

    private SysClient SysClient;

    private Contractor DstStock;

    private Measure Measure1;

    private Contractor SrcStock;

    private BigDecimal Quantity;

    private BigDecimal Price;

    private BigDecimal Summa;

    private BigDecimal Price1;

    private BigDecimal Summa1;

    private BigDecimal Weight;

    private BigDecimal Volume;

    private Date BestBefore;

    private BigDecimal ShelfLife;

    private TimePeriodKind ShelfLifeMeas;

    private Date ProductionDate;

    private BigDecimal Quantity2;

    private String Comment;

    private Contractor Contractor;

    private String UNID;

    private CustomsDeclaration CustomsDeclaration;

    private Country CountryOfOrigin;

    private Set<DocumentSpecTax> Taxes = new HashSet<DocumentSpecTax>(0);

    private Set<DocumentSpecSerialNum> SerialNumbers = new HashSet<DocumentSpecSerialNum>(0);

    public DocSpec() {
    }

    public DocSpec(TimePeriodKind ShelfLifeMeas) {
        this.ShelfLifeMeas = ShelfLifeMeas;
    }

    public DocSpec(DocSpec OrderSpec, Catalog Catalog, TaxGroup TaxGroup, Contractor DstMol, Contractor SrcMol, PriceListSpec PriceListSpec, DocHead DocHead, Measure Measure2, SysClient SysClient, Contractor DstStock, Measure Measure1, Contractor SrcStock, BigDecimal Quantity, BigDecimal Price, BigDecimal Summa, BigDecimal Price1, BigDecimal Summa1, BigDecimal Weight, BigDecimal Volume, Date BestBefore, BigDecimal ShelfLife, TimePeriodKind ShelfLifeMeas, Date ProductionDate, BigDecimal Quantity2, String Comment, Contractor Contractor, String UNID, CustomsDeclaration CustomsDeclaration, Country CountryOfOrigin, Set<DocumentSpecTax> Taxes, Set<DocumentSpecSerialNum> SerialNumbers) {
        this.OrderSpec = OrderSpec;
        this.Catalog = Catalog;
        this.TaxGroup = TaxGroup;
        this.DstMol = DstMol;
        this.SrcMol = SrcMol;
        this.PriceListSpec = PriceListSpec;
        this.DocHead = DocHead;
        this.Measure2 = Measure2;
        this.SysClient = SysClient;
        this.DstStock = DstStock;
        this.Measure1 = Measure1;
        this.SrcStock = SrcStock;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Summa = Summa;
        this.Price1 = Price1;
        this.Summa1 = Summa1;
        this.Weight = Weight;
        this.Volume = Volume;
        this.BestBefore = BestBefore;
        this.ShelfLife = ShelfLife;
        this.ShelfLifeMeas = ShelfLifeMeas;
        this.ProductionDate = ProductionDate;
        this.Quantity2 = Quantity2;
        this.Comment = Comment;
        this.Contractor = Contractor;
        this.UNID = UNID;
        this.CustomsDeclaration = CustomsDeclaration;
        this.CountryOfOrigin = CountryOfOrigin;
        this.Taxes = Taxes;
        this.SerialNumbers = SerialNumbers;
    }

    @SequenceGenerator(name = "generator", sequenceName = "DOCSPEC_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_SPEC_ID")
    public DocSpec getOrderSpec() {
        return this.OrderSpec;
    }

    public void setOrderSpec(DocSpec OrderSpec) {
        this.OrderSpec = OrderSpec;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }

    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAXGROUP_ID")
    public TaxGroup getTaxGroup() {
        return this.TaxGroup;
    }

    public void setTaxGroup(TaxGroup TaxGroup) {
        this.TaxGroup = TaxGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOLDEST")
    public Contractor getDstMol() {
        return this.DstMol;
    }

    public void setDstMol(Contractor DstMol) {
        this.DstMol = DstMol;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOLSRC")
    public Contractor getSrcMol() {
        return this.SrcMol;
    }

    public void setSrcMol(Contractor SrcMol) {
        this.SrcMol = SrcMol;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRICELISTSPEC_ID")
    public PriceListSpec getPriceListSpec() {
        return this.PriceListSpec;
    }

    public void setPriceListSpec(PriceListSpec PriceListSpec) {
        this.PriceListSpec = PriceListSpec;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCHEAD_ID")
    public DocHead getDocHead() {
        return this.DocHead;
    }

    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE2_ID")
    public Measure getMeasure2() {
        return this.Measure2;
    }

    public void setMeasure2(Measure Measure2) {
        this.Measure2 = Measure2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCKDEST")
    public Contractor getDstStock() {
        return this.DstStock;
    }

    public void setDstStock(Contractor DstStock) {
        this.DstStock = DstStock;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE1_ID")
    public Measure getMeasure1() {
        return this.Measure1;
    }

    public void setMeasure1(Measure Measure1) {
        this.Measure1 = Measure1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCKSRC")
    public Contractor getSrcStock() {
        return this.SrcStock;
    }

    public void setSrcStock(Contractor SrcStock) {
        this.SrcStock = SrcStock;
    }

    @Column(name = "QUANTITY", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    @Column(name = "PRICE", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getPrice() {
        return this.Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    @Column(name = "SUMMA", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getSumma() {
        return this.Summa;
    }

    public void setSumma(BigDecimal Summa) {
        this.Summa = Summa;
    }

    @Column(name = "PRICE1", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getPrice1() {
        return this.Price1;
    }

    public void setPrice1(BigDecimal Price1) {
        this.Price1 = Price1;
    }

    @Column(name = "SUMMA1", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getSumma1() {
        return this.Summa1;
    }

    public void setSumma1(BigDecimal Summa1) {
        this.Summa1 = Summa1;
    }

    @Column(name = "WEIGHT", columnDefinition = "NUMERIC", precision = 15, scale = 3)
    public BigDecimal getWeight() {
        return this.Weight;
    }

    public void setWeight(BigDecimal Weight) {
        this.Weight = Weight;
    }

    @Column(name = "VOLUME", columnDefinition = "NUMERIC", precision = 15, scale = 3)
    public BigDecimal getVolume() {
        return this.Volume;
    }

    public void setVolume(BigDecimal Volume) {
        this.Volume = Volume;
    }

    @Column(name = "BESTBEFORE", columnDefinition = "TIMESTAMP")
    public Date getBestBefore() {
        return this.BestBefore;
    }

    public void setBestBefore(Date BestBefore) {
        this.BestBefore = BestBefore;
    }

    @Column(name = "SHELFLIFE", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getShelfLife() {
        return this.ShelfLife;
    }

    public void setShelfLife(BigDecimal ShelfLife) {
        this.ShelfLife = ShelfLife;
    }

    @Column(name = "SHELFLIFE_MEAS", nullable = false, columnDefinition = "SMALLINT")
    public TimePeriodKind getShelfLifeMeas() {
        return this.ShelfLifeMeas;
    }

    public void setShelfLifeMeas(TimePeriodKind ShelfLifeMeas) {
        this.ShelfLifeMeas = ShelfLifeMeas;
    }

    @Column(name = "PRODUCTIONDATE", columnDefinition = "TIMESTAMP")
    public Date getProductionDate() {
        return this.ProductionDate;
    }

    public void setProductionDate(Date ProductionDate) {
        this.ProductionDate = ProductionDate;
    }

    @Column(name = "QUANTITY2", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getQuantity2() {
        return this.Quantity2;
    }

    public void setQuantity2(BigDecimal Quantity2) {
        this.Quantity2 = Quantity2;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTOR_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }

    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

    @Column(name = "UNID", unique = true, updatable = false, columnDefinition = "VARCHAR", length = 32)
    public String getUNID() {
        return this.UNID;
    }

    public void setUNID(String UNID) {
        this.UNID = UNID;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMS_DECLARATION_ID")
    public CustomsDeclaration getCustomsDeclaration() {
        return this.CustomsDeclaration;
    }

    public void setCustomsDeclaration(CustomsDeclaration CustomsDeclaration) {
        this.CustomsDeclaration = CustomsDeclaration;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_OF_ORIGIN_ID")
    public Country getCountryOfOrigin() {
        return this.CountryOfOrigin;
    }

    public void setCountryOfOrigin(Country CountryOfOrigin) {
        this.CountryOfOrigin = CountryOfOrigin;
    }

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "DocSpec")
    public Set<DocumentSpecTax> getTaxes() {
        return this.Taxes;
    }

    public void setTaxes(Set<DocumentSpecTax> Taxes) {
        this.Taxes = Taxes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "DocSpec")
    public Set<DocumentSpecSerialNum> getSerialNumbers() {
        return this.SerialNumbers;
    }

    public void setSerialNumbers(Set<DocumentSpecSerialNum> SerialNumbers) {
        this.SerialNumbers = SerialNumbers;
    }
}

