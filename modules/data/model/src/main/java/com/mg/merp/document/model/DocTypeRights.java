package com.mg.merp.document.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.security.model.Groups;
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
 * DocTypeRights generated by hbm2java
 */
@Entity
@Table(name="DOCTYPE_RIGHTS"
)
public class DocTypeRights extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocType DocType;
     private Groups SecGroups;
     private SysClient SysClient;
     private Boolean Rights;

    public DocTypeRights() {
    }

    public DocTypeRights(DocType DocType, Groups SecGroups, SysClient SysClient, Boolean Rights) {
       this.DocType = DocType;
       this.SecGroups = SecGroups;
       this.SysClient = SysClient;
       this.Rights = Rights;
    }
   
     @SequenceGenerator(name="generator", sequenceName="DOCTYPE_RIGHTS_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REC_ID")
    public DocType getDocType() {
        return this.DocType;
    }
    
    public void setDocType(DocType DocType) {
        this.DocType = DocType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    public Groups getSecGroups() {
        return this.SecGroups;
    }
    
    public void setSecGroups(Groups SecGroups) {
        this.SecGroups = SecGroups;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="RIGHTS", columnDefinition="INTEGER")
    public Boolean getRights() {
        return this.Rights;
    }
    
    public void setRights(Boolean Rights) {
        this.Rights = Rights;
    }




}


