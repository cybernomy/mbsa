package com.mg.merp.account.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import java.math.BigDecimal;
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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * Inventory generated by hbm2java
 */
@Entity
@Table(name = "INVENTORY")
public class Inventory extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private InvLocation InvLocation;

    private AccPlan AccKt;

    private Contractor Contractor;

    private AnlPlan Anl3;

    private AnlPlan Anl1;

    private AccPlan AccPlan;

    private AnlPlan Anl2;

    private AnlPlan AnlKt3;

    private AnlPlan AnlDb5;

    private AnlPlan AnlKt1;

    private Catalog Catalog;

    private InvHead InvHead;

    private AnlPlan Anl4;

    private Folder Folder;

    private AnlPlan AnlKt2;

    private AnlPlan AnlDb2;

    private AccKind AccKind;

    private AnlPlan Anl5;

    private Okof Okof;

    private Inventory Inventory;

    private SysClient SysClient;

    private AnlPlan AnlDb3;

    private AnlPlan AnlDb1;

    private AnlPlan AnlKt5;

    private AnlPlan AnlDb4;

    private AccGroup AccGroup;

    private AccPlan AccDb;

    private AnlPlan AnlKt4;

    private AmCode AmCode;

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

    private BigDecimal BalanceCost;

    private BigDecimal BeginCost;

    private BigDecimal BeginLoss;

    private BigDecimal Amort;

    private BigDecimal EndCost;

    private Date BeginLossDate;

    private Date AmortDate;

    private boolean IsComplex;

    private boolean IsCommon;

    private Float Factor;

    private AmortAlgorithmType Algorithm;

    private BigDecimal YearAmortRate;

    private Float ExplPeriodY;

    private Float ExplPeriodM;

    private Double Production;

    private String Comment;

    private BigDecimal Initialloss;

    private String IncomeDocNum;

    private Date IncomeDate;

    private String InvName;

    private Set<Amortization> Amortizations = new HashSet<Amortization>(0);

    private Set<InvHistory> InvHistories = new HashSet<InvHistory>(0);

    public Inventory() {
    }

    public Inventory(InvLocation InvLocation, AccPlan AccKt, Contractor Contractor, AnlPlan Anl3, AnlPlan Anl1, AccPlan AccPlan, AnlPlan Anl2, AnlPlan AnlKt3, AnlPlan AnlDb5, AnlPlan AnlKt1, Catalog Catalog, InvHead InvHead, AnlPlan Anl4, Folder Folder, AnlPlan AnlKt2, AnlPlan AnlDb2, AccKind AccKind, AnlPlan Anl5, Okof Okof, Inventory Inventory, SysClient SysClient, AnlPlan AnlDb3, AnlPlan AnlDb1, AnlPlan AnlKt5, AnlPlan AnlDb4, AccGroup AccGroup, AccPlan AccDb, AnlPlan AnlKt4, AmCode AmCode, String GroupNum, String CardNum, String ObjNum, String Manufacturer, String Model, String SerialNum, String PasspNum, String InOperDocNum, Date InOperDate, String OutOperDocNum, Date OutOperDate, BigDecimal BalanceCost, BigDecimal BeginCost, BigDecimal BeginLoss, BigDecimal Amort, BigDecimal EndCost, Date BeginLossDate, Date AmortDate, boolean IsComplex, boolean IsCommon, Float Factor, AmortAlgorithmType Algorithm, BigDecimal YearAmortRate, Float ExplPeriodY, Float ExplPeriodM, Double Production, String Comment, BigDecimal Initialloss, String IncomeDocNum, Date IncomeDate, String InvName, Set<Amortization> Amortizations, Set<InvHistory> InvHistories) {
        this.InvLocation = InvLocation;
        this.AccKt = AccKt;
        this.Contractor = Contractor;
        this.Anl3 = Anl3;
        this.Anl1 = Anl1;
        this.AccPlan = AccPlan;
        this.Anl2 = Anl2;
        this.AnlKt3 = AnlKt3;
        this.AnlDb5 = AnlDb5;
        this.AnlKt1 = AnlKt1;
        this.Catalog = Catalog;
        this.InvHead = InvHead;
        this.Anl4 = Anl4;
        this.Folder = Folder;
        this.AnlKt2 = AnlKt2;
        this.AnlDb2 = AnlDb2;
        this.AccKind = AccKind;
        this.Anl5 = Anl5;
        this.Okof = Okof;
        this.Inventory = Inventory;
        this.SysClient = SysClient;
        this.AnlDb3 = AnlDb3;
        this.AnlDb1 = AnlDb1;
        this.AnlKt5 = AnlKt5;
        this.AnlDb4 = AnlDb4;
        this.AccGroup = AccGroup;
        this.AccDb = AccDb;
        this.AnlKt4 = AnlKt4;
        this.AmCode = AmCode;
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
        this.BalanceCost = BalanceCost;
        this.BeginCost = BeginCost;
        this.BeginLoss = BeginLoss;
        this.Amort = Amort;
        this.EndCost = EndCost;
        this.BeginLossDate = BeginLossDate;
        this.AmortDate = AmortDate;
        this.IsComplex = IsComplex;
        this.IsCommon = IsCommon;
        this.Factor = Factor;
        this.Algorithm = Algorithm;
        this.YearAmortRate = YearAmortRate;
        this.ExplPeriodY = ExplPeriodY;
        this.ExplPeriodM = ExplPeriodM;
        this.Production = Production;
        this.Comment = Comment;
        this.Initialloss = Initialloss;
        this.IncomeDocNum = IncomeDocNum;
        this.IncomeDate = IncomeDate;
        this.InvName = InvName;
        this.Amortizations = Amortizations;
        this.InvHistories = InvHistories;
    }

    @SequenceGenerator(name = "generator", sequenceName = "INVENTORY_ID_GEN")
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
    @JoinColumn(name = "INVLOCATION_ID")
    public InvLocation getInvLocation() {
        return this.InvLocation;
    }

    public void setInvLocation(InvLocation InvLocation) {
        this.InvLocation = InvLocation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCKT_ID")
    public AccPlan getAccKt() {
        return this.AccKt;
    }

    public void setAccKt(AccPlan AccKt) {
        this.AccKt = AccKt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACTOR_ID")
    public Contractor getContractor() {
        return this.Contractor;
    }

    public void setContractor(Contractor Contractor) {
        this.Contractor = Contractor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANL3_ID")
    public AnlPlan getAnl3() {
        return this.Anl3;
    }

    public void setAnl3(AnlPlan Anl3) {
        this.Anl3 = Anl3;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANL1_ID")
    public AnlPlan getAnl1() {
        return this.Anl1;
    }

    public void setAnl1(AnlPlan Anl1) {
        this.Anl1 = Anl1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACC_ID")
    public AccPlan getAccPlan() {
        return this.AccPlan;
    }

    public void setAccPlan(AccPlan AccPlan) {
        this.AccPlan = AccPlan;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANL2_ID")
    public AnlPlan getAnl2() {
        return this.Anl2;
    }

    public void setAnl2(AnlPlan Anl2) {
        this.Anl2 = Anl2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLKT3_ID")
    public AnlPlan getAnlKt3() {
        return this.AnlKt3;
    }

    public void setAnlKt3(AnlPlan AnlKt3) {
        this.AnlKt3 = AnlKt3;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLDB5_ID")
    public AnlPlan getAnlDb5() {
        return this.AnlDb5;
    }

    public void setAnlDb5(AnlPlan AnlDb5) {
        this.AnlDb5 = AnlDb5;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLKT1_ID")
    public AnlPlan getAnlKt1() {
        return this.AnlKt1;
    }

    public void setAnlKt1(AnlPlan AnlKt1) {
        this.AnlKt1 = AnlKt1;
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
    @JoinColumn(name = "INVHEAD_ID")
    public InvHead getInvHead() {
        return this.InvHead;
    }

    public void setInvHead(InvHead InvHead) {
        this.InvHead = InvHead;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANL4_ID")
    public AnlPlan getAnl4() {
        return this.Anl4;
    }

    public void setAnl4(AnlPlan Anl4) {
        this.Anl4 = Anl4;
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
    @JoinColumn(name = "ANLKT2_ID")
    public AnlPlan getAnlKt2() {
        return this.AnlKt2;
    }

    public void setAnlKt2(AnlPlan AnlKt2) {
        this.AnlKt2 = AnlKt2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLDB2_ID")
    public AnlPlan getAnlDb2() {
        return this.AnlDb2;
    }

    public void setAnlDb2(AnlPlan AnlDb2) {
        this.AnlDb2 = AnlDb2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCKIND_ID")
    public AccKind getAccKind() {
        return this.AccKind;
    }

    public void setAccKind(AccKind AccKind) {
        this.AccKind = AccKind;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANL5_ID")
    public AnlPlan getAnl5() {
        return this.Anl5;
    }

    public void setAnl5(AnlPlan Anl5) {
        this.Anl5 = Anl5;
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
    public Inventory getInventory() {
        return this.Inventory;
    }

    public void setInventory(Inventory Inventory) {
        this.Inventory = Inventory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLDB3_ID")
    public AnlPlan getAnlDb3() {
        return this.AnlDb3;
    }

    public void setAnlDb3(AnlPlan AnlDb3) {
        this.AnlDb3 = AnlDb3;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLDB1_ID")
    public AnlPlan getAnlDb1() {
        return this.AnlDb1;
    }

    public void setAnlDb1(AnlPlan AnlDb1) {
        this.AnlDb1 = AnlDb1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLKT5_ID")
    public AnlPlan getAnlKt5() {
        return this.AnlKt5;
    }

    public void setAnlKt5(AnlPlan AnlKt5) {
        this.AnlKt5 = AnlKt5;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLDB4_ID")
    public AnlPlan getAnlDb4() {
        return this.AnlDb4;
    }

    public void setAnlDb4(AnlPlan AnlDb4) {
        this.AnlDb4 = AnlDb4;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCGROUP_ID")
    public AccGroup getAccGroup() {
        return this.AccGroup;
    }

    public void setAccGroup(AccGroup AccGroup) {
        this.AccGroup = AccGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCDB_ID")
    public AccPlan getAccDb() {
        return this.AccDb;
    }

    public void setAccDb(AccPlan AccDb) {
        this.AccDb = AccDb;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANLKT4_ID")
    public AnlPlan getAnlKt4() {
        return this.AnlKt4;
    }

    public void setAnlKt4(AnlPlan AnlKt4) {
        this.AnlKt4 = AnlKt4;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMCODE_ID")
    public AmCode getAmCode() {
        return this.AmCode;
    }

    public void setAmCode(AmCode AmCode) {
        this.AmCode = AmCode;
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

    @Column(name = "BALANCECOST", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getBalanceCost() {
        return this.BalanceCost;
    }

    public void setBalanceCost(BigDecimal BalanceCost) {
        this.BalanceCost = BalanceCost;
    }

    @Column(name = "BEGINCOST", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getBeginCost() {
        return this.BeginCost;
    }

    public void setBeginCost(BigDecimal BeginCost) {
        this.BeginCost = BeginCost;
    }

    @Column(name = "BEGINLOSS", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getBeginLoss() {
        return this.BeginLoss;
    }

    public void setBeginLoss(BigDecimal BeginLoss) {
        this.BeginLoss = BeginLoss;
    }

    @Column(name = "AMORT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getAmort() {
        return this.Amort;
    }

    public void setAmort(BigDecimal Amort) {
        this.Amort = Amort;
    }

    @Column(name = "ENDCOST", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getEndCost() {
        return this.EndCost;
    }

    public void setEndCost(BigDecimal EndCost) {
        this.EndCost = EndCost;
    }

    @Column(name = "BEGINLOSSDATE", columnDefinition = "TIMESTAMP")
    public Date getBeginLossDate() {
        return this.BeginLossDate;
    }

    public void setBeginLossDate(Date BeginLossDate) {
        this.BeginLossDate = BeginLossDate;
    }

    @Column(name = "AMORTDATE", columnDefinition = "TIMESTAMP")
    public Date getAmortDate() {
        return this.AmortDate;
    }

    public void setAmortDate(Date AmortDate) {
        this.AmortDate = AmortDate;
    }

    @Column(name = "ISCOMPLEX", columnDefinition = "SMALLINT")
    public boolean isComplex() {
        return this.IsComplex;
    }

    public void setComplex(boolean IsComplex) {
        this.IsComplex = IsComplex;
    }

    @Column(name = "ISCOMMON", columnDefinition = "SMALLINT")
    public boolean isCommon() {
        return this.IsCommon;
    }

    public void setCommon(boolean IsCommon) {
        this.IsCommon = IsCommon;
    }

    @Column(name = "FACTOR", columnDefinition = "FLOAT")
    public Float getFactor() {
        return this.Factor;
    }

    public void setFactor(Float Factor) {
        this.Factor = Factor;
    }

    @Column(name = "ALGORITHM")
    @Enumerated(EnumType.ORDINAL)
    public AmortAlgorithmType getAlgorithm() {
        return this.Algorithm;
    }

    public void setAlgorithm(AmortAlgorithmType Algorithm) {
        this.Algorithm = Algorithm;
    }

    @Column(name = "YEARAMORTRATE", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    public BigDecimal getYearAmortRate() {
        return this.YearAmortRate;
    }

    public void setYearAmortRate(BigDecimal YearAmortRate) {
        this.YearAmortRate = YearAmortRate;
    }

    @Column(name = "EXPLPERIOD_Y", columnDefinition = "FLOAT")
    public Float getExplPeriodY() {
        return this.ExplPeriodY;
    }

    public void setExplPeriodY(Float ExplPeriodY) {
        this.ExplPeriodY = ExplPeriodY;
    }

    @Column(name = "EXPLPERIOD_M", columnDefinition = "FLOAT")
    public Float getExplPeriodM() {
        return this.ExplPeriodM;
    }

    public void setExplPeriodM(Float ExplPeriodM) {
        this.ExplPeriodM = ExplPeriodM;
    }

    @Column(name = "PRODUCTION", columnDefinition = "FLOAT")
    public Double getProduction() {
        return this.Production;
    }

    public void setProduction(Double Production) {
        this.Production = Production;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Column(name = "INITIALLOSS", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    public BigDecimal getInitialloss() {
        return this.Initialloss;
    }

    public void setInitialloss(BigDecimal Initialloss) {
        this.Initialloss = Initialloss;
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

    @Column(name = "INVNAME", columnDefinition = "VARCHAR", length = 256)
    public String getInvName() {
        return this.InvName;
    }

    public void setInvName(String InvName) {
        this.InvName = InvName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Inventory")
    public Set<Amortization> getAmortizations() {
        return this.Amortizations;
    }

    public void setAmortizations(Set<Amortization> Amortizations) {
        this.Amortizations = Amortizations;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Inventory")
    public Set<InvHistory> getInvHistories() {
        return this.InvHistories;
    }

    public void setInvHistories(Set<InvHistory> InvHistories) {
        this.InvHistories = InvHistories;
    }
}

