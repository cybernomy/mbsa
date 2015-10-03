package com.mg.merp.lbschedule.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Tax;
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
 * ItemSpecTax generated by hbm2java
 */
@Entity
@Table(name="LS_ITEMSPECTAX"
)
public class ItemSpecTax extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Tax Tax;
     private ItemSpec ItemSpec;
     private SysClient SysClient;
     private BigDecimal Summa;
     private BigDecimal Price;

    public ItemSpecTax() {
    }

    public ItemSpecTax(Tax Tax, ItemSpec ItemSpec, SysClient SysClient, BigDecimal Summa, BigDecimal Price) {
       this.Tax = Tax;
       this.ItemSpec = ItemSpec;
       this.SysClient = SysClient;
       this.Summa = Summa;
       this.Price = Price;
    }
   
     @SequenceGenerator(name="generator", sequenceName="LS_ITEMSPECTAX_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAX_ID")
    public Tax getTax() {
        return this.Tax;
    }
    
    public void setTax(Tax Tax) {
        this.Tax = Tax;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEMSPEC_ID")
    public ItemSpec getItemSpec() {
        return this.ItemSpec;
    }
    
    public void setItemSpec(ItemSpec ItemSpec) {
        this.ItemSpec = ItemSpec;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="SUMMA", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getSumma() {
        return this.Summa;
    }
    
    public void setSumma(BigDecimal Summa) {
        this.Summa = Summa;
    }

    
    @Column(name="PRICE", columnDefinition="NUMERIC", precision=15, scale=4)
    public BigDecimal getPrice() {
        return this.Price;
    }
    
    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }




}


