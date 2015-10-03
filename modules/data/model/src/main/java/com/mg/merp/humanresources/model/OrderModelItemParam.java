package com.mg.merp.humanresources.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
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
 * OrderModelItemParam generated by hbm2java
 */
@Entity
@Table(name="HR_ORDER_MODEL_ITEM_PARAM"
)
public class OrderModelItemParam extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private OrderItemKindParam ItemKindParam;
     private SysClient SysClient;
     private OrderModelItem ModelItem;
     private String ParamValue;

    public OrderModelItemParam() {
    }

	
    public OrderModelItemParam(OrderItemKindParam ItemKindParam, OrderModelItem ModelItem) {
        this.ItemKindParam = ItemKindParam;
        this.ModelItem = ModelItem;
    }
    public OrderModelItemParam(OrderItemKindParam ItemKindParam, SysClient SysClient, OrderModelItem ModelItem, String ParamValue) {
       this.ItemKindParam = ItemKindParam;
       this.SysClient = SysClient;
       this.ModelItem = ModelItem;
       this.ParamValue = ParamValue;
    }
   
     @SequenceGenerator(name="generator", sequenceName="HR_ORDER_MODEL_ITEM_PARAM_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEM_KIND_PARAM_ID", nullable=false)
    public OrderItemKindParam getItemKindParam() {
        return this.ItemKindParam;
    }
    
    public void setItemKindParam(OrderItemKindParam ItemKindParam) {
        this.ItemKindParam = ItemKindParam;
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
    @JoinColumn(name="MODEL_ITEM_ID", nullable=false)
    public OrderModelItem getModelItem() {
        return this.ModelItem;
    }
    
    public void setModelItem(OrderModelItem ModelItem) {
        this.ModelItem = ModelItem;
    }

    
    @Column(name="PARAM_VALUE", columnDefinition="VARCHAR", length=80)
    public String getParamValue() {
        return this.ParamValue;
    }
    
    public void setParamValue(String ParamValue) {
        this.ParamValue = ParamValue;
    }




}


