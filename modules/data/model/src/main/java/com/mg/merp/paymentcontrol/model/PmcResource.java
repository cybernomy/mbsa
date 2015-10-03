package com.mg.merp.paymentcontrol.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;
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
 * PmcResource generated by hbm2java
 */
@Entity
@Table(name="PMC_RESOURCE"
)
public class PmcResource extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Catalog Catalog;
     private DocHeadModel OutDocModel2;
     private CurrencyRateAuthority CurRateAuthority;
     private DocHeadModel OutDocModel1;
     private Folder Folder;
     private SysClient SysClient;
     private DocHeadModel InDocModel1;
     private CurrencyRateType CurRateType;
     private DocHeadModel InDocModel2;
     private Currency CurCode;
     private Contractor OrgUnit;
     private String Name;
     private String Description;
     private Date ActDateTill;
     private Date ActDateFrom;
     private Set<Item> SetOfLsItem = new HashSet<Item>(0);
     private Set<Execution> SetOfPmcExecution = new HashSet<Execution>(0);
     private Set<Liability> SetOfPmcLiability = new HashSet<Liability>(0);
     private Set<Item> SetOfLsItem_1 = new HashSet<Item>(0);

    public PmcResource() {
    }

    public PmcResource(Catalog Catalog, DocHeadModel OutDocModel2, CurrencyRateAuthority CurRateAuthority, DocHeadModel OutDocModel1, Folder Folder, SysClient SysClient, DocHeadModel InDocModel1, CurrencyRateType CurRateType, DocHeadModel InDocModel2, Currency CurCode, Contractor OrgUnit, String Name, String Description, Date ActDateTill, Date ActDateFrom, Set<Item> SetOfLsItem, Set<Execution> SetOfPmcExecution, Set<Liability> SetOfPmcLiability, Set<Item> SetOfLsItem_1) {
       this.Catalog = Catalog;
       this.OutDocModel2 = OutDocModel2;
       this.CurRateAuthority = CurRateAuthority;
       this.OutDocModel1 = OutDocModel1;
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.InDocModel1 = InDocModel1;
       this.CurRateType = CurRateType;
       this.InDocModel2 = InDocModel2;
       this.CurCode = CurCode;
       this.OrgUnit = OrgUnit;
       this.Name = Name;
       this.Description = Description;
       this.ActDateTill = ActDateTill;
       this.ActDateFrom = ActDateFrom;
       this.SetOfLsItem = SetOfLsItem;
       this.SetOfPmcExecution = SetOfPmcExecution;
       this.SetOfPmcLiability = SetOfPmcLiability;
       this.SetOfLsItem_1 = SetOfLsItem_1;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PMC_RESOURCE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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
    @JoinColumn(name="OUTDOCMODEL2_ID")
    public DocHeadModel getOutDocModel2() {
        return this.OutDocModel2;
    }
    
    public void setOutDocModel2(DocHeadModel OutDocModel2) {
        this.OutDocModel2 = OutDocModel2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATEAUTHORITY_ID")
    public CurrencyRateAuthority getCurRateAuthority() {
        return this.CurRateAuthority;
    }
    
    public void setCurRateAuthority(CurrencyRateAuthority CurRateAuthority) {
        this.CurRateAuthority = CurRateAuthority;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OUTDOCMODEL1_ID")
    public DocHeadModel getOutDocModel1() {
        return this.OutDocModel1;
    }
    
    public void setOutDocModel1(DocHeadModel OutDocModel1) {
        this.OutDocModel1 = OutDocModel1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOLDER_ID")
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INDOCMODEL1_ID")
    public DocHeadModel getInDocModel1() {
        return this.InDocModel1;
    }
    
    public void setInDocModel1(DocHeadModel InDocModel1) {
        this.InDocModel1 = InDocModel1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRATETYPE_ID")
    public CurrencyRateType getCurRateType() {
        return this.CurRateType;
    }
    
    public void setCurRateType(CurrencyRateType CurRateType) {
        this.CurRateType = CurRateType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INDOCMODEL2_ID")
    public DocHeadModel getInDocModel2() {
        return this.InDocModel2;
    }
    
    public void setInDocModel2(DocHeadModel InDocModel2) {
        this.InDocModel2 = InDocModel2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURCODE")
    public Currency getCurCode() {
        return this.CurCode;
    }
    
    public void setCurCode(Currency CurCode) {
        this.CurCode = CurCode;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGUNIT_ID")
    public Contractor getOrgUnit() {
        return this.OrgUnit;
    }
    
    public void setOrgUnit(Contractor OrgUnit) {
        this.OrgUnit = OrgUnit;
    }

    
    @Column(name="NAME", columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="DESCRIPTION", columnDefinition="VARCHAR", length=256)
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    @Column(name="ACTDATETILL", columnDefinition="TIMESTAMP")
    public Date getActDateTill() {
        return this.ActDateTill;
    }
    
    public void setActDateTill(Date ActDateTill) {
        this.ActDateTill = ActDateTill;
    }

    
    @Column(name="ACTDATEFROM", columnDefinition="TIMESTAMP")
    public Date getActDateFrom() {
        return this.ActDateFrom;
    }
    
    public void setActDateFrom(Date ActDateFrom) {
        this.ActDateFrom = ActDateFrom;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCEFROM_ID", updatable=false)
    public Set<Item> getSetOfLsItem() {
        return this.SetOfLsItem;
    }
    
    public void setSetOfLsItem(Set<Item> SetOfLsItem) {
        this.SetOfLsItem = SetOfLsItem;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCE_ID", updatable=false)
    public Set<Execution> getSetOfPmcExecution() {
        return this.SetOfPmcExecution;
    }
    
    public void setSetOfPmcExecution(Set<Execution> SetOfPmcExecution) {
        this.SetOfPmcExecution = SetOfPmcExecution;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PREFRESOURCE_ID", updatable=false)
    public Set<Liability> getSetOfPmcLiability() {
        return this.SetOfPmcLiability;
    }
    
    public void setSetOfPmcLiability(Set<Liability> SetOfPmcLiability) {
        this.SetOfPmcLiability = SetOfPmcLiability;
    }

@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCETO_ID", updatable=false)
    public Set<Item> getSetOfLsItem_1() {
        return this.SetOfLsItem_1;
    }
    
    public void setSetOfLsItem_1(Set<Item> SetOfLsItem_1) {
        this.SetOfLsItem_1 = SetOfLsItem_1;
    }




}


