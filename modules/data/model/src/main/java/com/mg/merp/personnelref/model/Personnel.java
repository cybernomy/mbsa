package com.mg.merp.personnelref.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.humanresources.model.Order;
import com.mg.merp.reference.model.FamilyMember;
import com.mg.merp.reference.model.IdentDoc;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.NaturalPerson;
import java.io.Serializable;
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

/**
 * Personnel generated by hbm2java
 */
@Entity
@Table(name="PREF_PERSONNEL"
)
public class Personnel extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Order RetireOrder;
     private PersonnelGroup Group;
     private NaturalPerson Person;
     private InsuredClass InsuredClass;
     private EducationDegree EducationDegree;
     private SysClient SysClient;
     private ReserveCategory MilReserveCategory;
     private MilitaryRankKind MilRankKind;
     private MilitaryRank MilRank;
     private MilitaryValidity MilValidity;
     private Date ActDate;
     private String TableNumber;
     private String Name1;
     private String Name2;
     private String Name3;
     private Date BornDate;
     private Integer Sex;
     private BigDecimal Stature;
     private Measure MeasureUpCode;
     private Serializable Photo;
     private String PensionNumber;
     private String Inn;
     private String LabourContractNumber;
     private Date LabourContractDate;
     private String AdditionalInfo;
     private String RetireReason;
     private Date RetireDate;
     private String MilSpeciality;
     private String MilCommissariat;
     private String MilRegPartyNumber;
     private String MilRegSpecial;
     private MilitaryRegTakenOff MilIsTakenOff;
     private Set<PersonnelProfession> SetOfPrefPersonnelProfession = new HashSet<PersonnelProfession>(0);
     private Set<PersonnelReward> SetOfPrefPersonnelReward = new HashSet<PersonnelReward>(0);
     private Set<PersonnelAttestation> SetOfPrefPersonnelAttestation = new HashSet<PersonnelAttestation>(0);
     private Set<FamilyMember> SetOfPrefFamilyMembers = new HashSet<FamilyMember>(0);
     private Set<PersonnelLanguage> SetOfPrefPersonnelLanguage = new HashSet<PersonnelLanguage>(0);
     private Set<PersonnelRecord> SetOfPrefPersonnelRecord = new HashSet<PersonnelRecord>(0);
     private Set<PersonnelSocialBenefit> SetOfPrefPersonnelSocialBenefit = new HashSet<PersonnelSocialBenefit>(0);
     private Set<PersonnelSkillRaising> SetOfPrefPersonnelSkillRaising = new HashSet<PersonnelSkillRaising>(0);
     private Set<PersonnelLabourContract> SetOfPrefPersonnelLabourContract = new HashSet<PersonnelLabourContract>(0);
     private Set<PersonnelVocationalTraining> SetOfPrefPersVocationalTraining = new HashSet<PersonnelVocationalTraining>(0);
     private Set<PersonnelTransfer> SetOfPrefPersonnelTransfer = new HashSet<PersonnelTransfer>(0);
     private Set<PersonnelLeave> SetOfPrefPersonnelLeave = new HashSet<PersonnelLeave>(0);
     private Set<PersonnelService> SetOfPrefPersonnelService = new HashSet<PersonnelService>(0);
     private Set<IdentDoc> SetOfPrefIdentDoc = new HashSet<IdentDoc>(0);
     private Set<Address> SetOfPrefAddress = new HashSet<Address>(0);
     private Set<PersonnelPhone> SetOfPrefPersonnelPhone = new HashSet<PersonnelPhone>(0);
     private Set<PersonnelEducation> SetOfPrefPersonnelEducation = new HashSet<PersonnelEducation>(0);

    public Personnel() {
    }

    public Personnel(Order RetireOrder, PersonnelGroup Group, NaturalPerson Person, InsuredClass InsuredClass, EducationDegree EducationDegree, SysClient SysClient, ReserveCategory MilReserveCategory, MilitaryRankKind MilRankKind, MilitaryRank MilRank, MilitaryValidity MilValidity, Date ActDate, String TableNumber, String Name1, String Name2, String Name3, Date BornDate, Integer Sex, BigDecimal Stature, Measure MeasureUpCode, Serializable Photo, String PensionNumber, String Inn, String LabourContractNumber, Date LabourContractDate, String AdditionalInfo, String RetireReason, Date RetireDate, String MilSpeciality, String MilCommissariat, String MilRegPartyNumber, String MilRegSpecial, MilitaryRegTakenOff MilIsTakenOff, Set<PersonnelProfession> SetOfPrefPersonnelProfession, Set<PersonnelReward> SetOfPrefPersonnelReward, Set<PersonnelAttestation> SetOfPrefPersonnelAttestation, Set<FamilyMember> SetOfPrefFamilyMembers, Set<PersonnelLanguage> SetOfPrefPersonnelLanguage, Set<PersonnelRecord> SetOfPrefPersonnelRecord, Set<PersonnelSocialBenefit> SetOfPrefPersonnelSocialBenefit, Set<PersonnelSkillRaising> SetOfPrefPersonnelSkillRaising, Set<PersonnelLabourContract> SetOfPrefPersonnelLabourContract, Set<PersonnelVocationalTraining> SetOfPrefPersVocationalTraining, Set<PersonnelTransfer> SetOfPrefPersonnelTransfer, Set<PersonnelLeave> SetOfPrefPersonnelLeave, Set<PersonnelService> SetOfPrefPersonnelService, Set<IdentDoc> SetOfPrefIdentDoc, Set<Address> SetOfPrefAddress, Set<PersonnelPhone> SetOfPrefPersonnelPhone, Set<PersonnelEducation> SetOfPrefPersonnelEducation) {
       this.RetireOrder = RetireOrder;
       this.Group = Group;
       this.Person = Person;
       this.InsuredClass = InsuredClass;
       this.EducationDegree = EducationDegree;
       this.SysClient = SysClient;
       this.MilReserveCategory = MilReserveCategory;
       this.MilRankKind = MilRankKind;
       this.MilRank = MilRank;
       this.MilValidity = MilValidity;
       this.ActDate = ActDate;
       this.TableNumber = TableNumber;
       this.Name1 = Name1;
       this.Name2 = Name2;
       this.Name3 = Name3;
       this.BornDate = BornDate;
       this.Sex = Sex;
       this.Stature = Stature;
       this.MeasureUpCode = MeasureUpCode;
       this.Photo = Photo;
       this.PensionNumber = PensionNumber;
       this.Inn = Inn;
       this.LabourContractNumber = LabourContractNumber;
       this.LabourContractDate = LabourContractDate;
       this.AdditionalInfo = AdditionalInfo;
       this.RetireReason = RetireReason;
       this.RetireDate = RetireDate;
       this.MilSpeciality = MilSpeciality;
       this.MilCommissariat = MilCommissariat;
       this.MilRegPartyNumber = MilRegPartyNumber;
       this.MilRegSpecial = MilRegSpecial;
       this.MilIsTakenOff = MilIsTakenOff;
       this.SetOfPrefPersonnelProfession = SetOfPrefPersonnelProfession;
       this.SetOfPrefPersonnelReward = SetOfPrefPersonnelReward;
       this.SetOfPrefPersonnelAttestation = SetOfPrefPersonnelAttestation;
       this.SetOfPrefFamilyMembers = SetOfPrefFamilyMembers;
       this.SetOfPrefPersonnelLanguage = SetOfPrefPersonnelLanguage;
       this.SetOfPrefPersonnelRecord = SetOfPrefPersonnelRecord;
       this.SetOfPrefPersonnelSocialBenefit = SetOfPrefPersonnelSocialBenefit;
       this.SetOfPrefPersonnelSkillRaising = SetOfPrefPersonnelSkillRaising;
       this.SetOfPrefPersonnelLabourContract = SetOfPrefPersonnelLabourContract;
       this.SetOfPrefPersVocationalTraining = SetOfPrefPersVocationalTraining;
       this.SetOfPrefPersonnelTransfer = SetOfPrefPersonnelTransfer;
       this.SetOfPrefPersonnelLeave = SetOfPrefPersonnelLeave;
       this.SetOfPrefPersonnelService = SetOfPrefPersonnelService;
       this.SetOfPrefIdentDoc = SetOfPrefIdentDoc;
       this.SetOfPrefAddress = SetOfPrefAddress;
       this.SetOfPrefPersonnelPhone = SetOfPrefPersonnelPhone;
       this.SetOfPrefPersonnelEducation = SetOfPrefPersonnelEducation;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_PERSONNEL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RETIRE_ORDER_ID")
    public Order getRetireOrder() {
        return this.RetireOrder;
    }
    
    public void setRetireOrder(Order RetireOrder) {
        this.RetireOrder = RetireOrder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    public PersonnelGroup getGroup() {
        return this.Group;
    }
    
    public void setGroup(PersonnelGroup Group) {
        this.Group = Group;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSON_ID", unique=true)
    public NaturalPerson getPerson() {
        return this.Person;
    }
    
    public void setPerson(NaturalPerson Person) {
        this.Person = Person;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INSUREDCLASS_ID")
    public InsuredClass getInsuredClass() {
        return this.InsuredClass;
    }
    
    public void setInsuredClass(InsuredClass InsuredClass) {
        this.InsuredClass = InsuredClass;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EDUCATION_DEGREE_ID")
    public EducationDegree getEducationDegree() {
        return this.EducationDegree;
    }
    
    public void setEducationDegree(EducationDegree EducationDegree) {
        this.EducationDegree = EducationDegree;
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
    @JoinColumn(name="MIL_RESERVE_CATEGORY_ID")
    public ReserveCategory getMilReserveCategory() {
        return this.MilReserveCategory;
    }
    
    public void setMilReserveCategory(ReserveCategory MilReserveCategory) {
        this.MilReserveCategory = MilReserveCategory;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MIL_RANK_KIND_ID")
    public MilitaryRankKind getMilRankKind() {
        return this.MilRankKind;
    }
    
    public void setMilRankKind(MilitaryRankKind MilRankKind) {
        this.MilRankKind = MilRankKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MIL_RANK_ID")
    public MilitaryRank getMilRank() {
        return this.MilRank;
    }
    
    public void setMilRank(MilitaryRank MilRank) {
        this.MilRank = MilRank;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MIL_VALIDITY_ID")
    public MilitaryValidity getMilValidity() {
        return this.MilValidity;
    }
    
    public void setMilValidity(MilitaryValidity MilValidity) {
        this.MilValidity = MilValidity;
    }

    
    @Column(name="ACT_DATE", columnDefinition="TIMESTAMP")
    public Date getActDate() {
        return this.ActDate;
    }
    
    public void setActDate(Date ActDate) {
        this.ActDate = ActDate;
    }

    
    @Column(name="TABLE_NUMBER", columnDefinition="CHAR", length=20)
    public String getTableNumber() {
        return this.TableNumber;
    }
    
    public void setTableNumber(String TableNumber) {
        this.TableNumber = TableNumber;
    }

    
    @Column(name="NAME1", columnDefinition="VARCHAR", length=80)
    public String getName1() {
        return this.Name1;
    }
    
    public void setName1(String Name1) {
        this.Name1 = Name1;
    }

    
    @Column(name="NAME2", columnDefinition="VARCHAR", length=80)
    public String getName2() {
        return this.Name2;
    }
    
    public void setName2(String Name2) {
        this.Name2 = Name2;
    }

    
    @Column(name="NAME3", columnDefinition="VARCHAR", length=80)
    public String getName3() {
        return this.Name3;
    }
    
    public void setName3(String Name3) {
        this.Name3 = Name3;
    }

    
    @Column(name="BORNDATE", columnDefinition="TIMESTAMP")
    public Date getBornDate() {
        return this.BornDate;
    }
    
    public void setBornDate(Date BornDate) {
        this.BornDate = BornDate;
    }

    
    @Column(name="SEX", columnDefinition="INTEGER")
    public Integer getSex() {
        return this.Sex;
    }
    
    public void setSex(Integer Sex) {
        this.Sex = Sex;
    }

    
    @Column(name="STATURE", columnDefinition="NUMERIC", precision=18, scale=3)
    public BigDecimal getStature() {
        return this.Stature;
    }
    
    public void setStature(BigDecimal Stature) {
        this.Stature = Stature;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEASURE_UPCODE")
    public Measure getMeasureUpCode() {
        return this.MeasureUpCode;
    }
    
    public void setMeasureUpCode(Measure MeasureUpCode) {
        this.MeasureUpCode = MeasureUpCode;
    }

    
    @Column(name="PHOTO", columnDefinition="BLOB SUB_TYPE 0")
    public Serializable getPhoto() {
        return this.Photo;
    }
    
    public void setPhoto(Serializable Photo) {
        this.Photo = Photo;
    }

    
    @Column(name="PENSION_NUMBER", columnDefinition="CHAR", length=20)
    public String getPensionNumber() {
        return this.PensionNumber;
    }
    
    public void setPensionNumber(String PensionNumber) {
        this.PensionNumber = PensionNumber;
    }

    
    @Column(name="INN", columnDefinition="CHAR", length=20)
    public String getInn() {
        return this.Inn;
    }
    
    public void setInn(String Inn) {
        this.Inn = Inn;
    }

    
    @Column(name="LABOUR_CONTRACT_NUMBER", columnDefinition="CHAR", length=20)
    public String getLabourContractNumber() {
        return this.LabourContractNumber;
    }
    
    public void setLabourContractNumber(String LabourContractNumber) {
        this.LabourContractNumber = LabourContractNumber;
    }

    
    @Column(name="LABOUR_CONTRACT_DATE", columnDefinition="TIMESTAMP")
    public Date getLabourContractDate() {
        return this.LabourContractDate;
    }
    
    public void setLabourContractDate(Date LabourContractDate) {
        this.LabourContractDate = LabourContractDate;
    }

    
    @Column(name="ADDITIONAL_INFO", columnDefinition="VARCHAR", length=2048)
    public String getAdditionalInfo() {
        return this.AdditionalInfo;
    }
    
    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }

    
    @Column(name="RETIRE_REASON", columnDefinition="VARCHAR", length=256)
    public String getRetireReason() {
        return this.RetireReason;
    }
    
    public void setRetireReason(String RetireReason) {
        this.RetireReason = RetireReason;
    }

    
    @Column(name="RETIRE_DATE", columnDefinition="TIMESTAMP")
    public Date getRetireDate() {
        return this.RetireDate;
    }
    
    public void setRetireDate(Date RetireDate) {
        this.RetireDate = RetireDate;
    }

    
    @Column(name="MIL_SPECIALITY", columnDefinition="CHAR", length=20)
    public String getMilSpeciality() {
        return this.MilSpeciality;
    }
    
    public void setMilSpeciality(String MilSpeciality) {
        this.MilSpeciality = MilSpeciality;
    }

    
    @Column(name="MIL_COMMISSARIAT", columnDefinition="VARCHAR", length=80)
    public String getMilCommissariat() {
        return this.MilCommissariat;
    }
    
    public void setMilCommissariat(String MilCommissariat) {
        this.MilCommissariat = MilCommissariat;
    }

    
    @Column(name="MIL_REG_PARTY_NUMBER", columnDefinition="VARCHAR", length=80)
    public String getMilRegPartyNumber() {
        return this.MilRegPartyNumber;
    }
    
    public void setMilRegPartyNumber(String MilRegPartyNumber) {
        this.MilRegPartyNumber = MilRegPartyNumber;
    }

    
    @Column(name="MIL_REG_SPECIAL", columnDefinition="VARCHAR", length=80)
    public String getMilRegSpecial() {
        return this.MilRegSpecial;
    }
    
    public void setMilRegSpecial(String MilRegSpecial) {
        this.MilRegSpecial = MilRegSpecial;
    }

    
    @Column(name="MIL_IS_TAKEN_OFF", columnDefinition="SMALLINT")
    public MilitaryRegTakenOff getMilIsTakenOff() {
        return this.MilIsTakenOff;
    }
    
    public void setMilIsTakenOff(MilitaryRegTakenOff MilIsTakenOff) {
        this.MilIsTakenOff = MilIsTakenOff;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelProfession> getSetOfPrefPersonnelProfession() {
        return this.SetOfPrefPersonnelProfession;
    }
    
    public void setSetOfPrefPersonnelProfession(Set<PersonnelProfession> SetOfPrefPersonnelProfession) {
        this.SetOfPrefPersonnelProfession = SetOfPrefPersonnelProfession;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelReward> getSetOfPrefPersonnelReward() {
        return this.SetOfPrefPersonnelReward;
    }
    
    public void setSetOfPrefPersonnelReward(Set<PersonnelReward> SetOfPrefPersonnelReward) {
        this.SetOfPrefPersonnelReward = SetOfPrefPersonnelReward;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelAttestation> getSetOfPrefPersonnelAttestation() {
        return this.SetOfPrefPersonnelAttestation;
    }
    
    public void setSetOfPrefPersonnelAttestation(Set<PersonnelAttestation> SetOfPrefPersonnelAttestation) {
        this.SetOfPrefPersonnelAttestation = SetOfPrefPersonnelAttestation;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="unresolved")
    public Set<FamilyMember> getSetOfPrefFamilyMembers() {
        return this.SetOfPrefFamilyMembers;
    }
    
    public void setSetOfPrefFamilyMembers(Set<FamilyMember> SetOfPrefFamilyMembers) {
        this.SetOfPrefFamilyMembers = SetOfPrefFamilyMembers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelLanguage> getSetOfPrefPersonnelLanguage() {
        return this.SetOfPrefPersonnelLanguage;
    }
    
    public void setSetOfPrefPersonnelLanguage(Set<PersonnelLanguage> SetOfPrefPersonnelLanguage) {
        this.SetOfPrefPersonnelLanguage = SetOfPrefPersonnelLanguage;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelRecord> getSetOfPrefPersonnelRecord() {
        return this.SetOfPrefPersonnelRecord;
    }
    
    public void setSetOfPrefPersonnelRecord(Set<PersonnelRecord> SetOfPrefPersonnelRecord) {
        this.SetOfPrefPersonnelRecord = SetOfPrefPersonnelRecord;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelSocialBenefit> getSetOfPrefPersonnelSocialBenefit() {
        return this.SetOfPrefPersonnelSocialBenefit;
    }
    
    public void setSetOfPrefPersonnelSocialBenefit(Set<PersonnelSocialBenefit> SetOfPrefPersonnelSocialBenefit) {
        this.SetOfPrefPersonnelSocialBenefit = SetOfPrefPersonnelSocialBenefit;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelSkillRaising> getSetOfPrefPersonnelSkillRaising() {
        return this.SetOfPrefPersonnelSkillRaising;
    }
    
    public void setSetOfPrefPersonnelSkillRaising(Set<PersonnelSkillRaising> SetOfPrefPersonnelSkillRaising) {
        this.SetOfPrefPersonnelSkillRaising = SetOfPrefPersonnelSkillRaising;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelLabourContract> getSetOfPrefPersonnelLabourContract() {
        return this.SetOfPrefPersonnelLabourContract;
    }
    
    public void setSetOfPrefPersonnelLabourContract(Set<PersonnelLabourContract> SetOfPrefPersonnelLabourContract) {
        this.SetOfPrefPersonnelLabourContract = SetOfPrefPersonnelLabourContract;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelVocationalTraining> getSetOfPrefPersVocationalTraining() {
        return this.SetOfPrefPersVocationalTraining;
    }
    
    public void setSetOfPrefPersVocationalTraining(Set<PersonnelVocationalTraining> SetOfPrefPersVocationalTraining) {
        this.SetOfPrefPersVocationalTraining = SetOfPrefPersVocationalTraining;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelTransfer> getSetOfPrefPersonnelTransfer() {
        return this.SetOfPrefPersonnelTransfer;
    }
    
    public void setSetOfPrefPersonnelTransfer(Set<PersonnelTransfer> SetOfPrefPersonnelTransfer) {
        this.SetOfPrefPersonnelTransfer = SetOfPrefPersonnelTransfer;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelLeave> getSetOfPrefPersonnelLeave() {
        return this.SetOfPrefPersonnelLeave;
    }
    
    public void setSetOfPrefPersonnelLeave(Set<PersonnelLeave> SetOfPrefPersonnelLeave) {
        this.SetOfPrefPersonnelLeave = SetOfPrefPersonnelLeave;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelService> getSetOfPrefPersonnelService() {
        return this.SetOfPrefPersonnelService;
    }
    
    public void setSetOfPrefPersonnelService(Set<PersonnelService> SetOfPrefPersonnelService) {
        this.SetOfPrefPersonnelService = SetOfPrefPersonnelService;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="unresolved")
    public Set<IdentDoc> getSetOfPrefIdentDoc() {
        return this.SetOfPrefIdentDoc;
    }
    
    public void setSetOfPrefIdentDoc(Set<IdentDoc> SetOfPrefIdentDoc) {
        this.SetOfPrefIdentDoc = SetOfPrefIdentDoc;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<Address> getSetOfPrefAddress() {
        return this.SetOfPrefAddress;
    }
    
    public void setSetOfPrefAddress(Set<Address> SetOfPrefAddress) {
        this.SetOfPrefAddress = SetOfPrefAddress;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelPhone> getSetOfPrefPersonnelPhone() {
        return this.SetOfPrefPersonnelPhone;
    }
    
    public void setSetOfPrefPersonnelPhone(Set<PersonnelPhone> SetOfPrefPersonnelPhone) {
        this.SetOfPrefPersonnelPhone = SetOfPrefPersonnelPhone;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Personnel")
    public Set<PersonnelEducation> getSetOfPrefPersonnelEducation() {
        return this.SetOfPrefPersonnelEducation;
    }
    
    public void setSetOfPrefPersonnelEducation(Set<PersonnelEducation> SetOfPrefPersonnelEducation) {
        this.SetOfPrefPersonnelEducation = SetOfPrefPersonnelEducation;
    }




}


