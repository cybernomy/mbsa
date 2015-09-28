package com.mg.merp.humanresources.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import java.util.Date;
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
 * Order generated by hbm2java
 */
@Entity
@Table(name="HR_ORDER"
)
public class Order extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Folder Folder;
     private SysClient SysClient;
     private String OrderNumber;
     private Date OrderDate;
     private String Header;
     private String Footer;
     private Short OrderStatus;
     private Set<OrderItem> OrderItems = new HashSet<OrderItem>(0);

    public Order() {
    }

	
    public Order(Folder Folder, String OrderNumber, Date OrderDate) {
        this.Folder = Folder;
        this.OrderNumber = OrderNumber;
        this.OrderDate = OrderDate;
    }
    public Order(Folder Folder, SysClient SysClient, String OrderNumber, Date OrderDate, String Header, String Footer, Short OrderStatus, Set<OrderItem> OrderItems) {
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.OrderNumber = OrderNumber;
       this.OrderDate = OrderDate;
       this.Header = Header;
       this.Footer = Footer;
       this.OrderStatus = OrderStatus;
       this.OrderItems = OrderItems;
    }
   
     @SequenceGenerator(name="generator", sequenceName="HR_ORDER_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOLDER_ID", nullable=false)
    public Folder getFolder() {
        return this.Folder;
    }
    
    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="ORDER_NUMBER", nullable=false, columnDefinition="CHAR", length=20)
    public String getOrderNumber() {
        return this.OrderNumber;
    }
    
    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    
    @Column(name="ORDER_DATE", nullable=false, columnDefinition="TIMESTAMP")
    public Date getOrderDate() {
        return this.OrderDate;
    }
    
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    
    @Column(name="HEADER", columnDefinition="VARCHAR", length=2048)
    public String getHeader() {
        return this.Header;
    }
    
    public void setHeader(String Header) {
        this.Header = Header;
    }

    
    @Column(name="FOOTER", columnDefinition="VARCHAR", length=2048)
    public String getFooter() {
        return this.Footer;
    }
    
    public void setFooter(String Footer) {
        this.Footer = Footer;
    }

    
    @Column(name="ORDER_STATUS", columnDefinition="SMALLINT")
    public Short getOrderStatus() {
        return this.OrderStatus;
    }
    
    public void setOrderStatus(Short OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Order")
    public Set<OrderItem> getOrderItems() {
        return this.OrderItems;
    }
    
    public void setOrderItems(Set<OrderItem> OrderItems) {
        this.OrderItems = OrderItems;
    }




}


