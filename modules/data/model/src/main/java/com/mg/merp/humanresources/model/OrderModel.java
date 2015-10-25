package com.mg.merp.humanresources.model;

import com.mg.merp.core.model.Folder;
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
 * OrderModel generated by hbm2java
 */
@Entity
@Table(name = "HR_ORDER_MODEL")
public class OrderModel extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Folder Folder;

    private SysClient SysClient;

    private String Code;

    private String Name;

    private String Header;

    private String Footer;

    private Set<OrderModelItem> OrderModelItems = new HashSet<OrderModelItem>(0);

    public OrderModel() {
    }

    public OrderModel(Folder Folder, String Code) {
        this.Folder = Folder;
        this.Code = Code;
    }

    public OrderModel(Folder Folder, SysClient SysClient, String Code, String Name, String Header, String Footer, Set<OrderModelItem> OrderModelItems) {
        this.Folder = Folder;
        this.SysClient = SysClient;
        this.Code = Code;
        this.Name = Name;
        this.Header = Header;
        this.Footer = Footer;
        this.OrderModelItems = OrderModelItems;
    }

    @SequenceGenerator(name = "generator", sequenceName = "HR_ORDER_MODEL_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLDER_ID", nullable = false)
    public Folder getFolder() {
        return this.Folder;
    }

    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "CODE", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "HEADER", columnDefinition = "VARCHAR", length = 2048)
    public String getHeader() {
        return this.Header;
    }

    public void setHeader(String Header) {
        this.Header = Header;
    }

    @Column(name = "FOOTER", columnDefinition = "VARCHAR", length = 2048)
    public String getFooter() {
        return this.Footer;
    }

    public void setFooter(String Footer) {
        this.Footer = Footer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OrderModel")
    public Set<OrderModelItem> getOrderModelItems() {
        return this.OrderModelItems;
    }

    public void setOrderModelItems(Set<OrderModelItem> OrderModelItems) {
        this.OrderModelItems = OrderModelItems;
    }
}

