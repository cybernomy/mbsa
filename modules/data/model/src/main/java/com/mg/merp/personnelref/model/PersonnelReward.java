package com.mg.merp.personnelref.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.OriginalDocument;
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
 * PersonnelReward generated by hbm2java
 */
@Entity
@Table(name="PREF_PERSONNEL_REWARD"
)
public class PersonnelReward extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private Personnel Personnel;
     private SysClient SysClient;
     private OriginalDocument OriginalDocument;
     private String RewardName;

    public PersonnelReward() {
    }

	
    public PersonnelReward(String RewardName) {
        this.RewardName = RewardName;
    }
    public PersonnelReward(Personnel Personnel, SysClient SysClient, OriginalDocument OriginalDocument, String RewardName) {
       this.Personnel = Personnel;
       this.SysClient = SysClient;
       this.OriginalDocument = OriginalDocument;
       this.RewardName = RewardName;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_PERSONNEL_REWARD_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
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
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORIGINAL_DOCUMENT_ID")
    public OriginalDocument getOriginalDocument() {
        return this.OriginalDocument;
    }
    
    public void setOriginalDocument(OriginalDocument OriginalDocument) {
        this.OriginalDocument = OriginalDocument;
    }

    
    @Column(name="REWARD_NAME", nullable=false, columnDefinition="VARCHAR", length=80)
    public String getRewardName() {
        return this.RewardName;
    }
    
    public void setRewardName(String RewardName) {
        this.RewardName = RewardName;
    }




}


