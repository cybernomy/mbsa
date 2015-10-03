package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


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
 * IdentDoc generated by hbm2java
 */
@Entity
@Table(name="REF_IDENT_DOC"
)
public class IdentDoc extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Nationality Nationality;
     private NaturalPerson NaturalPerson;
     private IdentDocKind IdentDocKind;
     private SysClient SysClient;
     private PersonAddress PersonAddress;
     private boolean IsBasic;
     private String SeriesLeft;
     private String SeriesDivider;
     private String SeriesRight;
     private String DocNumber;
     private String WhoIssued;
     private Date WhenIssued;
     private Date ActiveTill;
     private String CitizenShip;
     private Date Birthdate;
     private String BirthPlaceCountry;
     private String BirthPlaceRegion;
     private String BirthPlaceDistrict;
     private String BirthPlaceCity;
     private String BirthPlaceOkato;

    public IdentDoc() {
    }

	
    public IdentDoc(IdentDocKind IdentDocKind) {
        this.IdentDocKind = IdentDocKind;
    }
    public IdentDoc(Nationality Nationality, NaturalPerson NaturalPerson, IdentDocKind IdentDocKind, SysClient SysClient, PersonAddress PersonAddress, boolean IsBasic, String SeriesLeft, String SeriesDivider, String SeriesRight, String DocNumber, String WhoIssued, Date WhenIssued, Date ActiveTill, String CitizenShip, Date Birthdate, String BirthPlaceCountry, String BirthPlaceRegion, String BirthPlaceDistrict, String BirthPlaceCity, String BirthPlaceOkato) {
       this.Nationality = Nationality;
       this.NaturalPerson = NaturalPerson;
       this.IdentDocKind = IdentDocKind;
       this.SysClient = SysClient;
       this.PersonAddress = PersonAddress;
       this.IsBasic = IsBasic;
       this.SeriesLeft = SeriesLeft;
       this.SeriesDivider = SeriesDivider;
       this.SeriesRight = SeriesRight;
       this.DocNumber = DocNumber;
       this.WhoIssued = WhoIssued;
       this.WhenIssued = WhenIssued;
       this.ActiveTill = ActiveTill;
       this.CitizenShip = CitizenShip;
       this.Birthdate = Birthdate;
       this.BirthPlaceCountry = BirthPlaceCountry;
       this.BirthPlaceRegion = BirthPlaceRegion;
       this.BirthPlaceDistrict = BirthPlaceDistrict;
       this.BirthPlaceCity = BirthPlaceCity;
       this.BirthPlaceOkato = BirthPlaceOkato;
    }
   
     @SequenceGenerator(name="generator", sequenceName="REF_IDENT_DOC_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NATIONALITY_ID")
    public Nationality getNationality() {
        return this.Nationality;
    }
    
    public void setNationality(Nationality Nationality) {
        this.Nationality = Nationality;
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
    @JoinColumn(name="IDENTDOCKIND_ID", nullable=false)
    public IdentDocKind getIdentDocKind() {
        return this.IdentDocKind;
    }
    
    public void setIdentDocKind(IdentDocKind IdentDocKind) {
        this.IdentDocKind = IdentDocKind;
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
    @JoinColumn(name="ADDRESS_ID")
    public PersonAddress getPersonAddress() {
        return this.PersonAddress;
    }
    
    public void setPersonAddress(PersonAddress PersonAddress) {
        this.PersonAddress = PersonAddress;
    }

    
    @Column(name="IS_BASIC", columnDefinition="SMALLINT")
    public boolean isIsBasic() {
        return this.IsBasic;
    }
    
    public void setIsBasic(boolean IsBasic) {
        this.IsBasic = IsBasic;
    }

    
    @Column(name="SERIES_LEFT", columnDefinition="CHAR", length=10)
    public String getSeriesLeft() {
        return this.SeriesLeft;
    }
    
    public void setSeriesLeft(String SeriesLeft) {
        this.SeriesLeft = SeriesLeft;
    }

    
    @Column(name="SERIES_DIVIDER", columnDefinition="CHAR", length=5)
    public String getSeriesDivider() {
        return this.SeriesDivider;
    }
    
    public void setSeriesDivider(String SeriesDivider) {
        this.SeriesDivider = SeriesDivider;
    }

    
    @Column(name="SERIES_RIGHT", columnDefinition="CHAR", length=10)
    public String getSeriesRight() {
        return this.SeriesRight;
    }
    
    public void setSeriesRight(String SeriesRight) {
        this.SeriesRight = SeriesRight;
    }

    
    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public String getDocNumber() {
        return this.DocNumber;
    }
    
    public void setDocNumber(String DocNumber) {
        this.DocNumber = DocNumber;
    }

    
    @Column(name="WHO_ISSUED", columnDefinition="VARCHAR", length=80)
    public String getWhoIssued() {
        return this.WhoIssued;
    }
    
    public void setWhoIssued(String WhoIssued) {
        this.WhoIssued = WhoIssued;
    }

    
    @Column(name="WHEN_ISSUED", columnDefinition="TIMESTAMP")
    public Date getWhenIssued() {
        return this.WhenIssued;
    }
    
    public void setWhenIssued(Date WhenIssued) {
        this.WhenIssued = WhenIssued;
    }

    
    @Column(name="ACTIVE_TILL", columnDefinition="TIMESTAMP")
    public Date getActiveTill() {
        return this.ActiveTill;
    }
    
    public void setActiveTill(Date ActiveTill) {
        this.ActiveTill = ActiveTill;
    }

    
    @Column(name="CITIZENSHIP", columnDefinition="VARCHAR", length=80)
    public String getCitizenShip() {
        return this.CitizenShip;
    }
    
    public void setCitizenShip(String CitizenShip) {
        this.CitizenShip = CitizenShip;
    }

    
    @Column(name="BIRTHDATE", columnDefinition="TIMESTAMP")
    public Date getBirthdate() {
        return this.Birthdate;
    }
    
    public void setBirthdate(Date Birthdate) {
        this.Birthdate = Birthdate;
    }

    
    @Column(name="BIRTHPLACE_COUNTRY", columnDefinition="VARCHAR", length=80)
    public String getBirthPlaceCountry() {
        return this.BirthPlaceCountry;
    }
    
    public void setBirthPlaceCountry(String BirthPlaceCountry) {
        this.BirthPlaceCountry = BirthPlaceCountry;
    }

    
    @Column(name="BIRTHPLACE_REGION", columnDefinition="VARCHAR", length=80)
    public String getBirthPlaceRegion() {
        return this.BirthPlaceRegion;
    }
    
    public void setBirthPlaceRegion(String BirthPlaceRegion) {
        this.BirthPlaceRegion = BirthPlaceRegion;
    }

    
    @Column(name="BIRTHPLACE_DISTRICT", columnDefinition="VARCHAR", length=80)
    public String getBirthPlaceDistrict() {
        return this.BirthPlaceDistrict;
    }
    
    public void setBirthPlaceDistrict(String BirthPlaceDistrict) {
        this.BirthPlaceDistrict = BirthPlaceDistrict;
    }

    
    @Column(name="BIRTHPLACE_CITY", columnDefinition="VARCHAR", length=80)
    public String getBirthPlaceCity() {
        return this.BirthPlaceCity;
    }
    
    public void setBirthPlaceCity(String BirthPlaceCity) {
        this.BirthPlaceCity = BirthPlaceCity;
    }

    
    @Column(name="BIRTHPLACE_OKATO", columnDefinition="CHAR", length=20)
    public String getBirthPlaceOkato() {
        return this.BirthPlaceOkato;
    }
    
    public void setBirthPlaceOkato(String BirthPlaceOkato) {
        this.BirthPlaceOkato = BirthPlaceOkato;
    }




}


