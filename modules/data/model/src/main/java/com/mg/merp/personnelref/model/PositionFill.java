package com.mg.merp.personnelref.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import java.math.BigDecimal;
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
 * PositionFill generated by hbm2java
 */
@Entity
@Table(name="SAL_POSITION_FILL"
)
public class PositionFill extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private PersonalAccount PersonalAccount;
     private SysClient SysClient;
     private PositionFillKind PositionFillKind;
     private PrefPosition Position;
     private StaffListPosition SlPositionUnique;
     private Date BeginDate;
     private Date EndDate;
     private String DocType;
     private String DocNumber;
     private Date DocDate;
     private BigDecimal RateNumber;
     private boolean IsBasic;

    public PositionFill() {
    }

	
    public PositionFill(StaffListPosition SlPositionUnique) {
        this.SlPositionUnique = SlPositionUnique;
    }
    public PositionFill(PersonalAccount PersonalAccount, SysClient SysClient, PositionFillKind PositionFillKind, PrefPosition Position, StaffListPosition SlPositionUnique, Date BeginDate, Date EndDate, String DocType, String DocNumber, Date DocDate, BigDecimal RateNumber, boolean IsBasic) {
       this.PersonalAccount = PersonalAccount;
       this.SysClient = SysClient;
       this.PositionFillKind = PositionFillKind;
       this.Position = Position;
       this.SlPositionUnique = SlPositionUnique;
       this.BeginDate = BeginDate;
       this.EndDate = EndDate;
       this.DocType = DocType;
       this.DocNumber = DocNumber;
       this.DocDate = DocDate;
       this.RateNumber = RateNumber;
       this.IsBasic = IsBasic;
    }
   
     @SequenceGenerator(name="generator", sequenceName="SAL_POSITION_FILL_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSONALACCOUNT_ID")
    public PersonalAccount getPersonalAccount() {
        return this.PersonalAccount;
    }
    
    public void setPersonalAccount(PersonalAccount PersonalAccount) {
        this.PersonalAccount = PersonalAccount;
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
    @JoinColumn(name="POSITIONFILLKIND_ID")
    public PositionFillKind getPositionFillKind() {
        return this.PositionFillKind;
    }
    
    public void setPositionFillKind(PositionFillKind PositionFillKind) {
        this.PositionFillKind = PositionFillKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="POSITION_ID")
    public PrefPosition getPosition() {
        return this.Position;
    }
    
    public void setPosition(PrefPosition Position) {
        this.Position = Position;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SL_POSITION_UNIQUE_ID", nullable=false)
    public StaffListPosition getSlPositionUnique() {
        return this.SlPositionUnique;
    }
    
    public void setSlPositionUnique(StaffListPosition SlPositionUnique) {
        this.SlPositionUnique = SlPositionUnique;
    }

    
    @Column(name="BEGINDATE", columnDefinition="TIMESTAMP")
    public Date getBeginDate() {
        return this.BeginDate;
    }
    
    public void setBeginDate(Date BeginDate) {
        this.BeginDate = BeginDate;
    }

    
    @Column(name="ENDDATE", columnDefinition="TIMESTAMP")
    public Date getEndDate() {
        return this.EndDate;
    }
    
    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    
    @Column(name="DOCTYPE", columnDefinition="CHAR", length=15)
    public String getDocType() {
        return this.DocType;
    }
    
    public void setDocType(String DocType) {
        this.DocType = DocType;
    }

    
    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public String getDocNumber() {
        return this.DocNumber;
    }
    
    public void setDocNumber(String DocNumber) {
        this.DocNumber = DocNumber;
    }

    
    @Column(name="DOCDATE", columnDefinition="TIMESTAMP")
    public Date getDocDate() {
        return this.DocDate;
    }
    
    public void setDocDate(Date DocDate) {
        this.DocDate = DocDate;
    }

    
    @Column(name="RATE_NUMBER", columnDefinition="NUMERIC", precision=18, scale=6)
    public BigDecimal getRateNumber() {
        return this.RateNumber;
    }
    
    public void setRateNumber(BigDecimal RateNumber) {
        this.RateNumber = RateNumber;
    }

    
    @Column(name="IS_BASIC", columnDefinition="SMALLINT")
    public boolean isIsBasic() {
        return this.IsBasic;
    }
    
    public void setIsBasic(boolean IsBasic) {
        this.IsBasic = IsBasic;
    }




}


