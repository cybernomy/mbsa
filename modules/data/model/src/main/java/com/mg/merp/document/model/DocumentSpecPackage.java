package com.mg.merp.document.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Packing;
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
 * DocumentSpecPackage generated by hbm2java
 */
@Entity
@Table(name="DOC_SPEC_PACKAGE"
)
public class DocumentSpecPackage extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Packing Packing;
     private DocSpec DocSpec;
     private SysClient SysClient;
     private BigDecimal Quantity;
     private BigDecimal Weight;
     private BigDecimal Volume;

    public DocumentSpecPackage() {
    }

    public DocumentSpecPackage(Packing Packing, DocSpec DocSpec, SysClient SysClient, BigDecimal Quantity, BigDecimal Weight, BigDecimal Volume) {
       this.Packing = Packing;
       this.DocSpec = DocSpec;
       this.SysClient = SysClient;
       this.Quantity = Quantity;
       this.Weight = Weight;
       this.Volume = Volume;
    }
   
     @SequenceGenerator(name="generator", sequenceName="DOCSPEC_PACKAGE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PACKAGE_ID")
    public Packing getPacking() {
        return this.Packing;
    }
    
    public void setPacking(Packing Packing) {
        this.Packing = Packing;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCSPEC_ID")
    public DocSpec getDocSpec() {
        return this.DocSpec;
    }
    
    public void setDocSpec(DocSpec DocSpec) {
        this.DocSpec = DocSpec;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="QUANTITY", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getQuantity() {
        return this.Quantity;
    }
    
    public void setQuantity(BigDecimal Quantity) {
        this.Quantity = Quantity;
    }

    
    @Column(name="WEIGHT", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getWeight() {
        return this.Weight;
    }
    
    public void setWeight(BigDecimal Weight) {
        this.Weight = Weight;
    }

    
    @Column(name="VOLUME", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getVolume() {
        return this.Volume;
    }
    
    public void setVolume(BigDecimal Volume) {
        this.Volume = Volume;
    }




}


