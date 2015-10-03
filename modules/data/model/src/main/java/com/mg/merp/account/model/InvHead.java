package com.mg.merp.account.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
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
 * InvHead generated by hbm2java
 */
@Entity
@Table(name = "ACC_INVHEAD")
public class InvHead extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Catalog Catalog;

    private Okof Okof;

    private Inventory Parent;

    private Folder Folder;

    private InvLocation InvLocation;

    private Contractor Contractor;

    private String GroupNum;

    private String CardNum;

    private String ObjNum;

    private String Manufacturer;

    private String Model;

    private String SerialNum;

    private String PasspNum;

    private String InOperDocNum;

    private Date InOperDate;

    private String OutOperDocNum;

    private Date OutOperDate;

    private boolean IsComplex;

    private boolean IsCommon;

    private String Comment;

    private String InvName;

    private String IncomeDocNum;

    private Date IncomeDate;

    private Date ProductDate;

    private Set<Inventory> SetOfInventory = new HashSet<Inventory>(0);

    private Set<InvProduction> SetOfAccInvProduction = new HashSet<InvProduction>(0);

    public InvHead() {
    }

    public InvHead(Catalog Catalog, Okof Okof, Inventory Parent, Folder Folder, InvLocation InvLocation, Contractor Contractor, String GroupNum, String CardNum, String ObjNum, String Manufacturer, String Model, String SerialNum, String PasspNum, String InOperDocNum, Date InOperDate, String OutOperDocNum, Date OutOperDate, boolean IsComplex, boolean IsCommon, String Comment, String InvName, String IncomeDocNum, Date IncomeDate, Date ProductDate, Set<Inventory> SetOfInventory, Set<InvProduction> SetOfAccInvProduction) {
        this.Catalog = Catalog;
        this.Okof = Okof;
        this.Parent = Parent;
        this.Folder = Folder;
        this.InvLocation = InvLocation;
        this.Contractor = Contractor;
        this.GroupNum = GroupNum;
        this.CardNum = CardNum;
        this.ObjNum = ObjNum;
        this.Manufacturer = Manufacturer;
        this.Model = Model;
        this.SerialNum = SerialNum;
        this.PasspNum = PasspNum;
        this.InOperDocNum = InOperDocNum;
        this.InOperDate = InOperDate;
        this.OutOperDocNum = OutOperDocNum;
        this.OutOperDate = OutOperDate;
        this.IsComplex = IsComplex;
        this.IsCommon = IsCommon;
        this.Comment = Comment;
        this.InvName = InvName;
        this.IncomeDocNum = IncomeDocNum;
        this.IncomeDate = IncomeDate;
        this.ProductDate = ProductDate;
        this.SetOfInventory = SetOfInventory;
        this.SetOfAccInvProduction = SetOfAccInvProduction;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ACC_INVHEAD_ID_GEN")
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
    @JoinColumn(name = "CATALOG_ID")
    public Catalog getCatalog() {
        return this.Catalog;
    }

    public void setCatalog(Catalog Catalog) {
        this.Catalog = Catalog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OKOF_ID")
    public Okof getOkof() {
        return this.Okof;
    }

    public void setOkof(Okof Okof) {
        this.Okof = Okof;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public Inventory getParent() {
        return this.Parent;
    }

    public void setParent(Inventory Parent) {
        this.Parent = Parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }

    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVLOCATION_ID")
    public InvLocation getInvLocation() {
        return this.InvLocation;
    }

    public void setInvLocation(InvLocation InvLocation) {
        this.InvLocation = InvLocation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTOR_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }

    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

    @Column(name = "GROUPNUM", columnDefinition = "CHAR", length = 15)
    public String getGroupNum() {
        return this.GroupNum;
    }

    public void setGroupNum(String GroupNum) {
        this.GroupNum = GroupNum;
    }

    @Column(name = "CARDNUM", columnDefinition = "CHAR", length = 15)
    public String getCardNum() {
        return this.CardNum;
    }

    public void setCardNum(String CardNum) {
        this.CardNum = CardNum;
    }

    @Column(name = "OBJNUM", columnDefinition = "CHAR", length = 30)
    public String getObjNum() {
        return this.ObjNum;
    }

    public void setObjNum(String ObjNum) {
        this.ObjNum = ObjNum;
    }

    @Column(name = "MANUFACTURER", columnDefinition = "VARCHAR", length = 80)
    public String getManufacturer() {
        return this.Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    @Column(name = "MODEL", columnDefinition = "VARCHAR", length = 80)
    public String getModel() {
        return this.Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    @Column(name = "SERIALNUM", columnDefinition = "CHAR", length = 15)
    public String getSerialNum() {
        return this.SerialNum;
    }

    public void setSerialNum(String SerialNum) {
        this.SerialNum = SerialNum;
    }

    @Column(name = "PASSPNUM", columnDefinition = "CHAR", length = 15)
    public String getPasspNum() {
        return this.PasspNum;
    }

    public void setPasspNum(String PasspNum) {
        this.PasspNum = PasspNum;
    }

    @Column(name = "INOPERDOCNUM", columnDefinition = "CHAR", length = 20)
    public String getInOperDocNum() {
        return this.InOperDocNum;
    }

    public void setInOperDocNum(String InOperDocNum) {
        this.InOperDocNum = InOperDocNum;
    }

    @Column(name = "INOPERDATE", columnDefinition = "TIMESTAMP")
    public Date getInOperDate() {
        return this.InOperDate;
    }

    public void setInOperDate(Date InOperDate) {
        this.InOperDate = InOperDate;
    }

    @Column(name = "OUTOPERDOCNUM", columnDefinition = "CHAR", length = 20)
    public String getOutOperDocNum() {
        return this.OutOperDocNum;
    }

    public void setOutOperDocNum(String OutOperDocNum) {
        this.OutOperDocNum = OutOperDocNum;
    }

    @Column(name = "OUTOPERDATE", columnDefinition = "TIMESTAMP")
    public Date getOutOperDate() {
        return this.OutOperDate;
    }

    public void setOutOperDate(Date OutOperDate) {
        this.OutOperDate = OutOperDate;
    }

    @Column(name = "ISCOMPLEX", columnDefinition = "SMALLINT")
    public boolean isIsComplex() {
        return this.IsComplex;
    }

    public void setIsComplex(boolean IsComplex) {
        this.IsComplex = IsComplex;
    }

    @Column(name = "ISCOMMON", columnDefinition = "SMALLINT")
    public boolean isIsCommon() {
        return this.IsCommon;
    }

    public void setIsCommon(boolean IsCommon) {
        this.IsCommon = IsCommon;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Column(name = "INVNAME", columnDefinition = "VARCHAR", length = 256)
    public String getInvName() {
        return this.InvName;
    }

    public void setInvName(String InvName) {
        this.InvName = InvName;
    }

    @Column(name = "INCOMEDOCNUM", columnDefinition = "CHAR", length = 20)
    public String getIncomeDocNum() {
        return this.IncomeDocNum;
    }

    public void setIncomeDocNum(String IncomeDocNum) {
        this.IncomeDocNum = IncomeDocNum;
    }

    @Column(name = "INCOMEDATE", columnDefinition = "TIMESTAMP")
    public Date getIncomeDate() {
        return this.IncomeDate;
    }

    public void setIncomeDate(Date IncomeDate) {
        this.IncomeDate = IncomeDate;
    }

    @Column(name = "PRODUCTDATE", columnDefinition = "TIMESTAMP")
    public Date getProductDate() {
        return this.ProductDate;
    }

    public void setProductDate(Date ProductDate) {
        this.ProductDate = ProductDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "InvHead")
    public Set<Inventory> getSetOfInventory() {
        return this.SetOfInventory;
    }

    public void setSetOfInventory(Set<Inventory> SetOfInventory) {
        this.SetOfInventory = SetOfInventory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "AccInvHead")
    public Set<InvProduction> getSetOfAccInvProduction() {
        return this.SetOfAccInvProduction;
    }

    public void setSetOfAccInvProduction(Set<InvProduction> SetOfAccInvProduction) {
        this.SetOfAccInvProduction = SetOfAccInvProduction;
    }
}

