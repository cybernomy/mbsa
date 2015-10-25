package com.mg.merp.reference.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.security.model.SecUser;
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
 * NaturalPerson generated by hbm2java
 */
@Entity
@Table(name = "REF_NATURAL_PERSON")
public class NaturalPerson extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private Folder Folder;

    private SysClient SysClient;

    private SecUser Users;

    private Date ActDate;

    private String Surname;

    private String Name;

    private String Patronymic;

    private Date BornDate;

    private GenderType Sex;

    private String Inn;

    private String AdditionalInfo;

    private String Code;

    private Set<IdentDoc> SetOfRefIdentDoc = new HashSet<IdentDoc>(0);

    private Set<FamilyStatus> SetOfRefFamilyStatus = new HashSet<FamilyStatus>(0);

    private Set<FamilyMember> SetOfRefFamilyMember = new HashSet<FamilyMember>(0);

    private Set<PersonPhone> SetOfRefPersonPhone = new HashSet<PersonPhone>(0);

    private Set<NaturalPersonHist> SetOfRefNaturalPersonHist = new HashSet<NaturalPersonHist>(0);

    private Set<PersonAddress> SetOfRefPersonAddress = new HashSet<PersonAddress>(0);

    private Set<PersonElectronicAddress> SetOfRefPersonEAddress = new HashSet<PersonElectronicAddress>(0);

    public NaturalPerson() {
    }

    public NaturalPerson(Date ActDate, String Surname) {
        this.ActDate = ActDate;
        this.Surname = Surname;
    }

    public NaturalPerson(Folder Folder, SysClient SysClient, SecUser Users, Date ActDate, String Surname, String Name, String Patronymic, Date BornDate, GenderType Sex, String Inn, String AdditionalInfo, String Code, Set<IdentDoc> SetOfRefIdentDoc, Set<FamilyStatus> SetOfRefFamilyStatus, Set<FamilyMember> SetOfRefFamilyMember, Set<PersonPhone> SetOfRefPersonPhone, Set<NaturalPersonHist> SetOfRefNaturalPersonHist, Set<PersonAddress> SetOfRefPersonAddress, Set<PersonElectronicAddress> SetOfRefPersonEAddress) {
        this.Folder = Folder;
        this.SysClient = SysClient;
        this.Users = Users;
        this.ActDate = ActDate;
        this.Surname = Surname;
        this.Name = Name;
        this.Patronymic = Patronymic;
        this.BornDate = BornDate;
        this.Sex = Sex;
        this.Inn = Inn;
        this.AdditionalInfo = AdditionalInfo;
        this.Code = Code;
        this.SetOfRefIdentDoc = SetOfRefIdentDoc;
        this.SetOfRefFamilyStatus = SetOfRefFamilyStatus;
        this.SetOfRefFamilyMember = SetOfRefFamilyMember;
        this.SetOfRefPersonPhone = SetOfRefPersonPhone;
        this.SetOfRefNaturalPersonHist = SetOfRefNaturalPersonHist;
        this.SetOfRefPersonAddress = SetOfRefPersonAddress;
        this.SetOfRefPersonEAddress = SetOfRefPersonEAddress;
    }

    @SequenceGenerator(name = "generator", sequenceName = "REF_NATURAL_PERSON_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public SecUser getUsers() {
        return this.Users;
    }

    public void setUsers(SecUser Users) {
        this.Users = Users;
    }

    @Column(name = "ACT_DATE", nullable = false, columnDefinition = "TIMESTAMP")
    public Date getActDate() {
        return this.ActDate;
    }

    public void setActDate(Date ActDate) {
        this.ActDate = ActDate;
    }

    @Column(name = "SURNAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getSurname() {
        return this.Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "PATRONYMIC", columnDefinition = "VARCHAR", length = 80)
    public String getPatronymic() {
        return this.Patronymic;
    }

    public void setPatronymic(String Patronymic) {
        this.Patronymic = Patronymic;
    }

    @Column(name = "BORNDATE", columnDefinition = "TIMESTAMP")
    public Date getBornDate() {
        return this.BornDate;
    }

    public void setBornDate(Date BornDate) {
        this.BornDate = BornDate;
    }

    @Column(name = "SEX")
    @Enumerated(EnumType.ORDINAL)
    public GenderType getSex() {
        return this.Sex;
    }

    public void setSex(GenderType Sex) {
        this.Sex = Sex;
    }

    @Column(name = "INN", columnDefinition = "CHAR", length = 20)
    public String getInn() {
        return this.Inn;
    }

    public void setInn(String Inn) {
        this.Inn = Inn;
    }

    @Column(name = "ADDITIONAL_INFO", columnDefinition = "VARCHAR", length = 2048)
    public String getAdditionalInfo() {
        return this.AdditionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }

    @Column(name = "CODE", columnDefinition = "VARCHAR", length = 80)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<IdentDoc> getSetOfRefIdentDoc() {
        return this.SetOfRefIdentDoc;
    }

    public void setSetOfRefIdentDoc(Set<IdentDoc> SetOfRefIdentDoc) {
        this.SetOfRefIdentDoc = SetOfRefIdentDoc;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<FamilyStatus> getSetOfRefFamilyStatus() {
        return this.SetOfRefFamilyStatus;
    }

    public void setSetOfRefFamilyStatus(Set<FamilyStatus> SetOfRefFamilyStatus) {
        this.SetOfRefFamilyStatus = SetOfRefFamilyStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<FamilyMember> getSetOfRefFamilyMember() {
        return this.SetOfRefFamilyMember;
    }

    public void setSetOfRefFamilyMember(Set<FamilyMember> SetOfRefFamilyMember) {
        this.SetOfRefFamilyMember = SetOfRefFamilyMember;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<PersonPhone> getSetOfRefPersonPhone() {
        return this.SetOfRefPersonPhone;
    }

    public void setSetOfRefPersonPhone(Set<PersonPhone> SetOfRefPersonPhone) {
        this.SetOfRefPersonPhone = SetOfRefPersonPhone;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<NaturalPersonHist> getSetOfRefNaturalPersonHist() {
        return this.SetOfRefNaturalPersonHist;
    }

    public void setSetOfRefNaturalPersonHist(Set<NaturalPersonHist> SetOfRefNaturalPersonHist) {
        this.SetOfRefNaturalPersonHist = SetOfRefNaturalPersonHist;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<PersonAddress> getSetOfRefPersonAddress() {
        return this.SetOfRefPersonAddress;
    }

    public void setSetOfRefPersonAddress(Set<PersonAddress> SetOfRefPersonAddress) {
        this.SetOfRefPersonAddress = SetOfRefPersonAddress;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "NaturalPerson")
    public Set<PersonElectronicAddress> getSetOfRefPersonEAddress() {
        return this.SetOfRefPersonEAddress;
    }

    public void setSetOfRefPersonEAddress(Set<PersonElectronicAddress> SetOfRefPersonEAddress) {
        this.SetOfRefPersonEAddress = SetOfRefPersonEAddress;
    }
}

