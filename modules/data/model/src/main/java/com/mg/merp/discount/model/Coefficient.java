package com.mg.merp.discount.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
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
 * Coefficient generated by hbm2java
 */
@Entity
@Table(name="DIS_COEF"
)
public class Coefficient extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Catalog Catalog;
     private CatalogFolder CatalogFolder;
     private Card Card;
     private SysClient SysClient;
     private BigDecimal Coefficient;

    public Coefficient() {
    }

    public Coefficient(Catalog Catalog, CatalogFolder CatalogFolder, Card Card, SysClient SysClient, BigDecimal Coefficient) {
       this.Catalog = Catalog;
       this.CatalogFolder = CatalogFolder;
       this.Card = Card;
       this.SysClient = SysClient;
       this.Coefficient = Coefficient;
    }
   
     @SequenceGenerator(name="generator", sequenceName="DIS_COEF_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
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
    @JoinColumn(name="CATALOGFOLDER_ID")
    public CatalogFolder getCatalogFolder() {
        return this.CatalogFolder;
    }
    
    public void setCatalogFolder(CatalogFolder CatalogFolder) {
        this.CatalogFolder = CatalogFolder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CARD_ID")
    public Card getCard() {
        return this.Card;
    }
    
    public void setCard(Card Card) {
        this.Card = Card;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="COEFFICIENT", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getCoefficient() {
        return this.Coefficient;
    }
    
    public void setCoefficient(BigDecimal Coefficient) {
        this.Coefficient = Coefficient;
    }




}


