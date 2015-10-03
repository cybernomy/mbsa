package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
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
 * FamilyRelation generated by hbm2java
 */
@Entity
@Table(name="REF_FAMILY_RELATION"
)
public class FamilyRelation extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private SysClient SysClient;
     private String RCode;
     private Integer Priority;
     private String Okin;

    public FamilyRelation() {
    }

	
    public FamilyRelation(String RCode) {
        this.RCode = RCode;
    }
    public FamilyRelation(SysClient SysClient, String RCode, Integer Priority, String Okin) {
       this.SysClient = SysClient;
       this.RCode = RCode;
       this.Priority = Priority;
       this.Okin = Okin;
    }
   
     @SequenceGenerator(name="generator", sequenceName="REF_FAMILY_RELATION_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
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

    
    @Column(name="RCODE", nullable=false, columnDefinition="CHAR", length=20)
    public String getRCode() {
        return this.RCode;
    }
    
    public void setRCode(String RCode) {
        this.RCode = RCode;
    }

    
    @Column(name="PRIORITY", columnDefinition="INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }
    
    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }

    
    @Column(name="OKIN", columnDefinition="CHAR", length=5)
    public String getOkin() {
        return this.Okin;
    }
    
    public void setOkin(String Okin) {
        this.Okin = Okin;
    }




}


