package com.mg.merp.reference.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.util.Date;
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
 * PartnerEmpl generated by hbm2java
 */
@Entity
@Table(name="PARTNEMPL"
)
public class PartnerEmpl extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Partner Partner;
     private NaturalPerson NaturalPerson;
     private SysClient SysClient;
     private String Code;
     private String Name;
     private String Patronymic;
     private String Surname;
     private String Address;
     private String Militia;
     private String MilitCity;
     private Date PasspDate;
     private String PasspNum;
     private String PasspSer;
     private String Office;
     private String Comment;

    public PartnerEmpl() {
    }

    public PartnerEmpl(Partner Partner, NaturalPerson NaturalPerson, SysClient SysClient, String Code, String Name, String Patronymic, String Surname, String Address, String Militia, String MilitCity, Date PasspDate, String PasspNum, String PasspSer, String Office, String Comment) {
       this.Partner = Partner;
       this.NaturalPerson = NaturalPerson;
       this.SysClient = SysClient;
       this.Code = Code;
       this.Name = Name;
       this.Patronymic = Patronymic;
       this.Surname = Surname;
       this.Address = Address;
       this.Militia = Militia;
       this.MilitCity = MilitCity;
       this.PasspDate = PasspDate;
       this.PasspNum = PasspNum;
       this.PasspSer = PasspSer;
       this.Office = Office;
       this.Comment = Comment;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PARTNEMPL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTNER_ID")
    public Partner getPartner() {
        return this.Partner;
    }
    
    public void setPartner(Partner Partner) {
        this.Partner = Partner;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSON_ID")
    public NaturalPerson getNaturalPerson() {
        return this.NaturalPerson;
    }
    
    public void setNaturalPerson(NaturalPerson NaturalPerson) {
        this.NaturalPerson = NaturalPerson;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="CODE", columnDefinition="CHAR", length=20)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="FNAME", columnDefinition="VARCHAR", length=20)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="PATRONYMIC", columnDefinition="VARCHAR", length=20)
    public String getPatronymic() {
        return this.Patronymic;
    }
    
    public void setPatronymic(String Patronymic) {
        this.Patronymic = Patronymic;
    }

    
    @Column(name="SURNAME", columnDefinition="VARCHAR", length=20)
    public String getSurname() {
        return this.Surname;
    }
    
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    
    @Column(name="ADDRESS", columnDefinition="VARCHAR", length=256)
    public String getAddress() {
        return this.Address;
    }
    
    public void setAddress(String Address) {
        this.Address = Address;
    }

    
    @Column(name="MILITIA", columnDefinition="VARCHAR", length=256)
    public String getMilitia() {
        return this.Militia;
    }
    
    public void setMilitia(String Militia) {
        this.Militia = Militia;
    }

    
    @Column(name="MILIT_CITY", columnDefinition="VARCHAR", length=20)
    public String getMilitCity() {
        return this.MilitCity;
    }
    
    public void setMilitCity(String MilitCity) {
        this.MilitCity = MilitCity;
    }

    
    @Column(name="PASSP_DATE", columnDefinition="TIMESTAMP")
    public Date getPasspDate() {
        return this.PasspDate;
    }
    
    public void setPasspDate(Date PasspDate) {
        this.PasspDate = PasspDate;
    }

    
    @Column(name="PASSP_NUM", columnDefinition="CHAR", length=10)
    public String getPasspNum() {
        return this.PasspNum;
    }
    
    public void setPasspNum(String PasspNum) {
        this.PasspNum = PasspNum;
    }

    
    @Column(name="PASSP_SER", columnDefinition="CHAR", length=10)
    public String getPasspSer() {
        return this.PasspSer;
    }
    
    public void setPasspSer(String PasspSer) {
        this.PasspSer = PasspSer;
    }

    
    @Column(name="OFFICE", columnDefinition="VARCHAR", length=100)
    public String getOffice() {
        return this.Office;
    }
    
    public void setOffice(String Office) {
        this.Office = Office;
    }

    
    @Column(name="COMMENT", columnDefinition="VARCHAR", length=256)
    public String getComment() {
        return this.Comment;
    }
    
    public void setComment(String Comment) {
        this.Comment = Comment;
    }




}

