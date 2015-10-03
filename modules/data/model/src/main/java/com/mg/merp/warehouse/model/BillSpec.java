package com.mg.merp.warehouse.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecSerialNum;
import com.mg.merp.document.model.DocumentSpecTax;
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
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillSpec generated by hbm2java
 */
@Entity
@Table(name = "BILLSPEC")
public class BillSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

    private BigDecimal Discount;

    private BigDecimal PriceWithDiscount;

    private BigDecimal SummaWithDiscount;

    private BigDecimal Cost;

    private BigDecimal AcceptanceQuan;

    private BigDecimal AcceptanceSum;

    private BigDecimal AcceptancePackingQuan;

    private BigDecimal DocDiscount;

    public BillSpec() {
    }

    public BillSpec(TimePeriodKind ShelfLifeMeas) {
        super(ShelfLifeMeas);
    }

    public BillSpec(DocSpec OrderSpec, Catalog Catalog, TaxGroup TaxGroup, Contractor DstMol, Contractor SrcMol, PriceListSpec PriceListSpec, DocHead DocHead, Measure Measure2, SysClient SysClient, Contractor DstStock, Measure Measure1, Contractor SrcStock, BigDecimal Quantity, BigDecimal Price, BigDecimal Summa, BigDecimal Price1, BigDecimal Summa1, BigDecimal Weight, BigDecimal Volume, Date BestBefore, BigDecimal ShelfLife, TimePeriodKind ShelfLifeMeas, Date ProductionDate, BigDecimal Quantity2, String Comment, Contractor Contractor, String UNID, CustomsDeclaration CustomsDeclaration, Country CountryOfOrigin, Set<DocumentSpecTax> Taxes, Set<DocumentSpecSerialNum> SerialNumbers, BigDecimal Discount, BigDecimal PriceWithDiscount, BigDecimal SummaWithDiscount, BigDecimal Cost, BigDecimal AcceptanceQuan, BigDecimal AcceptanceSum, BigDecimal AcceptancePackingQuan, BigDecimal DocDiscount) {
        super(OrderSpec, Catalog, TaxGroup, DstMol, SrcMol, PriceListSpec, DocHead, Measure2, SysClient, DstStock, Measure1, SrcStock, Quantity, Price, Summa, Price1, Summa1, Weight, Volume, BestBefore, ShelfLife, ShelfLifeMeas, ProductionDate, Quantity2, Comment, Contractor, UNID, CustomsDeclaration, CountryOfOrigin, Taxes, SerialNumbers);
        this.Discount = Discount;
        this.PriceWithDiscount = PriceWithDiscount;
        this.SummaWithDiscount = SummaWithDiscount;
        this.Cost = Cost;
        this.AcceptanceQuan = AcceptanceQuan;
        this.AcceptanceSum = AcceptanceSum;
        this.AcceptancePackingQuan = AcceptancePackingQuan;
        this.DocDiscount = DocDiscount;
    }

    @Column(name = "DISCOUNT", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getDiscount() {
        return this.Discount;
    }

    public void setDiscount(BigDecimal Discount) {
        this.Discount = Discount;
    }

    @Column(name = "PRICE_WITH_DISCOUNT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getPriceWithDiscount() {
        return this.PriceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal PriceWithDiscount) {
        this.PriceWithDiscount = PriceWithDiscount;
    }

    @Column(name = "SUMMA_WITH_DISCOUNT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getSummaWithDiscount() {
        return this.SummaWithDiscount;
    }

    public void setSummaWithDiscount(BigDecimal SummaWithDiscount) {
        this.SummaWithDiscount = SummaWithDiscount;
    }

    @Column(name = "COST", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getCost() {
        return this.Cost;
    }

    public void setCost(BigDecimal Cost) {
        this.Cost = Cost;
    }

    @Column(name = "ACCEPTANCEQUAN", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getAcceptanceQuan() {
        return this.AcceptanceQuan;
    }

    public void setAcceptanceQuan(BigDecimal AcceptanceQuan) {
        this.AcceptanceQuan = AcceptanceQuan;
    }

    @Column(name = "ACCEPTANCESUM", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getAcceptanceSum() {
        return this.AcceptanceSum;
    }

    public void setAcceptanceSum(BigDecimal AcceptanceSum) {
        this.AcceptanceSum = AcceptanceSum;
    }

    @Column(name = "ACCEPTANCEPACKINGQUAN", columnDefinition = "NUMERIC", precision = 18, scale = 3)
    public BigDecimal getAcceptancePackingQuan() {
        return this.AcceptancePackingQuan;
    }

    public void setAcceptancePackingQuan(BigDecimal AcceptancePackingQuan) {
        this.AcceptancePackingQuan = AcceptancePackingQuan;
    }

    @Column(name = "DOC_DISCOUNT", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getDocDiscount() {
        return this.DocDiscount;
    }

    public void setDocDiscount(BigDecimal DocDiscount) {
        this.DocDiscount = DocDiscount;
    }
}

