package com.mg.merp.docprocess.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * LinkStage generated by hbm2java
 */
@Entity
@Table(name="LINKSTAGE"
)
public class LinkStage extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocProcessStage NextStage;
     private DocProcessStage PrevStage;
     private SysClient SysClient;
     private boolean Directly;

    public LinkStage() {
    }

	
    public LinkStage(boolean Directly) {
        this.Directly = Directly;
    }
    public LinkStage(DocProcessStage NextStage, DocProcessStage PrevStage, SysClient SysClient, boolean Directly) {
       this.NextStage = NextStage;
       this.PrevStage = PrevStage;
       this.SysClient = SysClient;
       this.Directly = Directly;
    }
   
     @SequenceGenerator(name="generator", sequenceName="LINKSTAGE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NEXTSTAGE_ID")
    public DocProcessStage getNextStage() {
        return this.NextStage;
    }
    
    public void setNextStage(DocProcessStage NextStage) {
        this.NextStage = NextStage;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PREVSTAGE_ID")
    public DocProcessStage getPrevStage() {
        return this.PrevStage;
    }
    
    public void setPrevStage(DocProcessStage PrevStage) {
        this.PrevStage = PrevStage;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="DIRECTLY", nullable=false, columnDefinition="SMALLINT")
    public boolean isDirectly() {
        return this.Directly;
    }
    
    public void setDirectly(boolean Directly) {
        this.Directly = Directly;
    }




}


