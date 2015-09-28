package com.mg.merp.reference.model;
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
 * SetOfGood generated by hbm2java
 */
@Entity
@Table(name="SETOFGOOD"
)
public class SetOfGood extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Catalog CatalogGood;
     private SysClient SysClient;
     private Catalog CatalogComponent;
     private BigDecimal Quantity;
     private BigDecimal PriceRelate;

    public SetOfGood() {
    }

	
    public SetOfGood(Catalog CatalogGood, Catalog CatalogComponent, BigDecimal Quantity) {
        this.CatalogGood = CatalogGood;
        this.CatalogComponent = CatalogComponent;
        this.Quantity = Quantity;
    }
    public SetOfGood(Catalog CatalogGood, SysClient SysClient, Catalog CatalogComponent, BigDecimal Quantity, BigDecimal PriceRelate) {
       this.CatalogGood = CatalogGood;
       this.SysClient = SysClient;
       this.CatalogComponent = CatalogComponent;
       this.Quantity = Quantity;
       this.PriceRelate = PriceRelate;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SETOFGOOD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GOOD_ID", nullable=false)
    public Catalog getCatalogGood() {
        return this.CatalogGood;
    }
    
    public void setCatalogGood(Catalog CatalogGood) {
        this.CatalogGood = CatalogGood;
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
    @JoinColumn(name="COMPONENT_ID", nullable=false)
    public Catalog getCatalogComponent() {
        return this.CatalogComponent;
    }
    
    public void setCatalogComponent(Catalog CatalogComponent) {
        this.CatalogComponent = CatalogComponent;
    }

    
    @Column(name="QUANTITY", nullable=false, columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }
    
    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    
    @Column(name="PRICERELATE", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getPriceRelate() {
        return this.PriceRelate;
    }
    
    public void setPriceRelate(BigDecimal PriceRelate) {
        this.PriceRelate = PriceRelate;
    }




}


