package com.mg.merp.manufacture.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecSerialNum;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.mfreference.model.CostCategories;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.CustomsDeclaration;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.TaxGroup;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * InputDocumentSpec generated by hbm2java
 */
@Entity
@Table(name="MF_INPUT_DOC_SPEC"
)
public class InputDocumentSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {


     private JobRouteResource JobRouteResource;
     private CostCategories CostCategory;

    public InputDocumentSpec() {
    }

	
    public InputDocumentSpec(EnumUserType ShelfLifeMeas) {
        super(ShelfLifeMeas);        
    }
    public InputDocumentSpec(DocSpec OrderSpec, Catalog Catalog, TaxGroup TaxGroup, Contractor DstMol, Contractor SrcMol, PriceListSpec PriceListSpec, DocHead DocHead, Measure Measure2, SysClient SysClient, Contractor DstStock, Measure Measure1, Contractor SrcStock, BigDecimal Quantity, BigDecimal Price, BigDecimal Summa, BigDecimal Price1, BigDecimal Summa1, BigDecimal Weight, BigDecimal Volume, Date BestBefore, BigDecimal ShelfLife, EnumUserType ShelfLifeMeas, Date ProductionDate, BigDecimal Quantity2, String Comment, Contractor Contractor, String UNID, CustomsDeclaration CustomsDeclaration, Country CountryOfOrigin, Set<DocumentSpecTax> Taxes, Set<DocumentSpecSerialNum> SerialNumbers, JobRouteResource JobRouteResource, CostCategories CostCategory) {
        super(OrderSpec, Catalog, TaxGroup, DstMol, SrcMol, PriceListSpec, DocHead, Measure2, SysClient, DstStock, Measure1, SrcStock, Quantity, Price, Summa, Price1, Summa1, Weight, Volume, BestBefore, ShelfLife, ShelfLifeMeas, ProductionDate, Quantity2, Comment, Contractor, UNID, CustomsDeclaration, CountryOfOrigin, Taxes, SerialNumbers);        
       this.JobRouteResource = JobRouteResource;
       this.CostCategory = CostCategory;
    }
   

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_ROUTE_RESOURCE_ID")
    public JobRouteResource getJobRouteResource() {
        return this.JobRouteResource;
    }
    
    public void setJobRouteResource(JobRouteResource JobRouteResource) {
        this.JobRouteResource = JobRouteResource;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COST_CATEGORY_ID")
    public CostCategories getCostCategory() {
        return this.CostCategory;
    }
    
    public void setCostCategory(CostCategories CostCategory) {
        this.CostCategory = CostCategory;
    }




}

