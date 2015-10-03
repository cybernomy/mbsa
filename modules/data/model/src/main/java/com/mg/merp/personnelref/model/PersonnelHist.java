package com.mg.merp.personnelref.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonnelHist generated by hbm2java
 */
@Entity
@Table(name="PREF_PERSONNEL_HIST"
)
public class PersonnelHist extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Personnel Personnel;
     private InsuredClass InsuredClass;
     private SysClient SysClient;
     private Date ActDate;
     private String TableNumber;
     private String Name1;
     private String Name2;
     private String Name3;
     private Date BornDate;
     private Integer Sex;
     private BigDecimal Stature;
     private String MeasureUpCode;
     private Serializable Photo;
     private String PensionNumber;
     private String Inn;

    public PersonnelHist() {
    }

	
    public PersonnelHist(Integer Id) {
        this.Id = Id;
    }
    public PersonnelHist(Integer Id, Personnel Personnel, InsuredClass InsuredClass, SysClient SysClient, Date ActDate, String TableNumber, String Name1, String Name2, String Name3, Date BornDate, Integer Sex, BigDecimal Stature, String MeasureUpCode, Serializable Photo, String PensionNumber, String Inn) {
       this.Id = Id;
       this.Personnel = Personnel;
       this.InsuredClass = InsuredClass;
       this.SysClient = SysClient;
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
    }
   
     @Id 

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSONNEL_ID")
    public Personnel getPersonnel() {
        return this.Personnel;
    }
    
    public void setPersonnel(Personnel Personnel) {
        this.Personnel = Personnel;
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
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
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

    
    @Column(name="MEASURE_UPCODE", columnDefinition="CHAR", length=5)
    public String getMeasureUpCode() {
        return this.MeasureUpCode;
    }
    
    public void setMeasureUpCode(String MeasureUpCode) {
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




}

