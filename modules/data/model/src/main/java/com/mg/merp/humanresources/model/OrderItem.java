package com.mg.merp.humanresources.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * OrderItem generated by hbm2java
 */
@Entity
@Table(name="HR_ORDER_ITEM"
)
public class OrderItem extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Order Order;
     private SysClient SysClient;
     private OrderItemKind OrderItemKind;
     private Set<OrderItemRollback> OrderRollbackItems = new HashSet<OrderItemRollback>(0);
     private Set<OrderItemParam> OrderItemParams = new HashSet<OrderItemParam>(0);

    public OrderItem() {
    }

	
    public OrderItem(Order Order, OrderItemKind OrderItemKind) {
        this.Order = Order;
        this.OrderItemKind = OrderItemKind;
    }
    public OrderItem(Order Order, SysClient SysClient, OrderItemKind OrderItemKind, Set<OrderItemRollback> OrderRollbackItems, Set<OrderItemParam> OrderItemParams) {
       this.Order = Order;
       this.SysClient = SysClient;
       this.OrderItemKind = OrderItemKind;
       this.OrderRollbackItems = OrderRollbackItems;
       this.OrderItemParams = OrderItemParams;
    }
   
     @SequenceGenerator(name="generator", sequenceName="HR_ORDER_ITEM_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_ID", nullable=false)
    public Order getOrder() {
        return this.Order;
    }
    
    public void setOrder(Order Order) {
        this.Order = Order;
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
    @JoinColumn(name="ITEM_KIND_ID", nullable=false)
    public OrderItemKind getOrderItemKind() {
        return this.OrderItemKind;
    }
    
    public void setOrderItemKind(OrderItemKind OrderItemKind) {
        this.OrderItemKind = OrderItemKind;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="OrderItem")
    public Set<OrderItemRollback> getOrderRollbackItems() {
        return this.OrderRollbackItems;
    }
    
    public void setOrderRollbackItems(Set<OrderItemRollback> OrderRollbackItems) {
        this.OrderRollbackItems = OrderRollbackItems;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="OrderItem")
    public Set<OrderItemParam> getOrderItemParams() {
        return this.OrderItemParams;
    }
    
    public void setOrderItemParams(Set<OrderItemParam> OrderItemParams) {
        this.OrderItemParams = OrderItemParams;
    }




}

