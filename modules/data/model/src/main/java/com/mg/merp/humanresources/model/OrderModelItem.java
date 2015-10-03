package com.mg.merp.humanresources.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.util.HashSet;
import java.util.Set;
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
 * OrderModelItem generated by hbm2java
 */
@Entity
@Table(name="HR_ORDER_MODEL_ITEM"
)
public class OrderModelItem extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private OrderModel OrderModel;
     private OrderItemKind ItemKind;
     private Set<OrderModelItemParam> OrderModelItemParams = new HashSet<OrderModelItemParam>(0);

    public OrderModelItem() {
    }

	
    public OrderModelItem(OrderModel OrderModel, OrderItemKind ItemKind) {
        this.OrderModel = OrderModel;
        this.ItemKind = ItemKind;
    }
    public OrderModelItem(SysClient SysClient, OrderModel OrderModel, OrderItemKind ItemKind, Set<OrderModelItemParam> OrderModelItemParams) {
       this.SysClient = SysClient;
       this.OrderModel = OrderModel;
       this.ItemKind = ItemKind;
       this.OrderModelItemParams = OrderModelItemParams;
    }
   
     @SequenceGenerator(name="generator", sequenceName="HR_ORDER_MODEL_ITEM_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
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
    @JoinColumn(name="ORDER_MODEL_ID", nullable=false)
    public OrderModel getOrderModel() {
        return this.OrderModel;
    }
    
    public void setOrderModel(OrderModel OrderModel) {
        this.OrderModel = OrderModel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEM_KIND_ID", nullable=false)
    public OrderItemKind getItemKind() {
        return this.ItemKind;
    }
    
    public void setItemKind(OrderItemKind ItemKind) {
        this.ItemKind = ItemKind;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ModelItem")
    public Set<OrderModelItemParam> getOrderModelItemParams() {
        return this.OrderModelItemParams;
    }
    
    public void setOrderModelItemParams(Set<OrderModelItemParam> OrderModelItemParams) {
        this.OrderModelItemParams = OrderModelItemParams;
    }




}


