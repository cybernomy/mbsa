package com.mg.merp.finance.model;
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
 * FinPeriod generated by hbm2java
 */
@Entity
@Table(name="FINPERIOD"
)
public class FinPeriod extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClient SysClient;
     private String PName;
     private Date DateFrom;
     private Date DateTo;
     private String WhoClosed;
     private Date DateClose;

    public FinPeriod() {
    }

	
    public FinPeriod(String PName, Date DateFrom, Date DateTo) {
        this.PName = PName;
        this.DateFrom = DateFrom;
        this.DateTo = DateTo;
    }
    public FinPeriod(SysClient SysClient, String PName, Date DateFrom, Date DateTo, String WhoClosed, Date DateClose) {
       this.SysClient = SysClient;
       this.PName = PName;
       this.DateFrom = DateFrom;
       this.DateTo = DateTo;
       this.WhoClosed = WhoClosed;
       this.DateClose = DateClose;
    }
   
     @SequenceGenerator(name="generator", sequenceName="FINPERIOD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="PNAME", nullable=false, columnDefinition="CHAR", length=20)
    public String getPName() {
        return this.PName;
    }
    
    public void setPName(String PName) {
        this.PName = PName;
    }

    
    @Column(name="DATEFROM", nullable=false, columnDefinition="TIMESTAMP")
    public Date getDateFrom() {
        return this.DateFrom;
    }
    
    public void setDateFrom(Date DateFrom) {
        this.DateFrom = DateFrom;
    }

    
    @Column(name="DATETO", nullable=false, columnDefinition="TIMESTAMP")
    public Date getDateTo() {
        return this.DateTo;
    }
    
    public void setDateTo(Date DateTo) {
        this.DateTo = DateTo;
    }

    
    @Column(name="WHOCLOSED", columnDefinition="CHAR", length=31)
    public String getWhoClosed() {
        return this.WhoClosed;
    }
    
    public void setWhoClosed(String WhoClosed) {
        this.WhoClosed = WhoClosed;
    }

    
    @Column(name="DATECLOSE", columnDefinition="TIMESTAMP")
    public Date getDateClose() {
        return this.DateClose;
    }
    
    public void setDateClose(Date DateClose) {
        this.DateClose = DateClose;
    }




}


