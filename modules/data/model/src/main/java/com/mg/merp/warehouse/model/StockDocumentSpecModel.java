package com.mg.merp.warehouse.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.TaxGroup;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StockDocumentSpecModel generated by hbm2java
 */
@Entity
@Table(name="STOCKDOCUMENTSPECMODEL"
)
public class StockDocumentSpecModel extends com.mg.merp.document.model.DocSpecModel implements java.io.Serializable {


     private BigDecimal Cost;
     private BigDecimal Discount;
     private BigDecimal PriceWithDiscount;
     private BigDecimal SummaWithDiscount;
     private BigDecimal DocDiscount;

    public StockDocumentSpecModel() {
    }

	
    public StockDocumentSpecModel(EnumUserType ShelfLifeMeas) {
        super(ShelfLifeMeas);        
    }
    public StockDocumentSpecModel(Catalog Catalog, Contractor DstMol, Contractor SrcMol, DocHeadModel DocHeadModel, Measure Measure2, SysClient SysClient, Contractor DstStock, Measure Measure1, Contractor SrcStock, PriceListSpec PriceListSpec, BigDecimal Quantity, BigDecimal Price, BigDecimal Summa, BigDecimal Price1, BigDecimal Summa1, BigDecimal Weight, BigDecimal Volume, Date BestBefore, TaxGroup TaxGroup, Date ProductionDate, BigDecimal ShelfLife, EnumUserType ShelfLifeMeas, BigDecimal Quantity2, String Comment, Contractor Contractor, BigDecimal Cost, BigDecimal Discount, BigDecimal PriceWithDiscount, BigDecimal SummaWithDiscount, BigDecimal DocDiscount) {
        super(Catalog, DstMol, SrcMol, DocHeadModel, Measure2, SysClient, DstStock, Measure1, SrcStock, PriceListSpec, Quantity, Price, Summa, Price1, Summa1, Weight, Volume, BestBefore, TaxGroup, ProductionDate, ShelfLife, ShelfLifeMeas, Quantity2, Comment, Contractor);        
       this.Cost = Cost;
       this.Discount = Discount;
       this.PriceWithDiscount = PriceWithDiscount;
       this.SummaWithDiscount = SummaWithDiscount;
       this.DocDiscount = DocDiscount;
    }
   

    
    @Column(name="COST", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getCost() {
        return this.Cost;
    }
    
    public void setCost(BigDecimal Cost) {
        this.Cost = Cost;
    }

    
    @Column(name="DISCOUNT", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getDiscount() {
        return this.Discount;
    }
    
    public void setDiscount(BigDecimal Discount) {
        this.Discount = Discount;
    }

    
    @Column(name="PRICE_WITH_DISCOUNT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getPriceWithDiscount() {
        return this.PriceWithDiscount;
    }
    
    public void setPriceWithDiscount(BigDecimal PriceWithDiscount) {
        this.PriceWithDiscount = PriceWithDiscount;
    }

    
    @Column(name="SUMMA_WITH_DISCOUNT", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSummaWithDiscount() {
        return this.SummaWithDiscount;
    }
    
    public void setSummaWithDiscount(BigDecimal SummaWithDiscount) {
        this.SummaWithDiscount = SummaWithDiscount;
    }

    
    @Column(name="DOC_DISCOUNT", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getDocDiscount() {
        return this.DocDiscount;
    }
    
    public void setDocDiscount(BigDecimal DocDiscount) {
        this.DocDiscount = DocDiscount;
    }




}

