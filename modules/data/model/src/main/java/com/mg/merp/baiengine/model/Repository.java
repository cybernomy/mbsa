package com.mg.merp.baiengine.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.Folder;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Repository generated by hbm2java
 */
@Entity
@Table(name="ALG_REPOSITORY"
)
public class Repository extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Date SysVersion;
     private Folder Folder;
     private SysClient SysClient;
     private String Code;
     private String Name;
     private String Algorithm;
     private EngineType Engine;
     private String ImplementationName;

    public Repository() {
    }

	
    public Repository(SysClient SysClient) {
        this.SysClient = SysClient;
    }
    public Repository(Folder Folder, SysClient SysClient, String Code, String Name, String Algorithm, EngineType Engine, String ImplementationName) {
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.Code = Code;
       this.Name = Name;
       this.Algorithm = Algorithm;
       this.Engine = Engine;
       this.ImplementationName = ImplementationName;
    }
   
     @SequenceGenerator(name="generator", sequenceName="ALG_REPOSITORY_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="SYS_VERSION", nullable=false)
    public Date getSysVersion() {
        return this.SysVersion;
    }
    
    public void setSysVersion(Date SysVersion) {
        this.SysVersion = SysVersion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }
    
    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID", nullable=false)
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

    
    @Column(name="NAME", columnDefinition="VARCHAR", length=256)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="ALGORITHM", columnDefinition="BLOB SUB_TYPE 1")
    public String getAlgorithm() {
        return this.Algorithm;
    }
    
    public void setAlgorithm(String Algorithm) {
        this.Algorithm = Algorithm;
    }

    
    @Column(name="ENGINE", columnDefinition="SMALLINT")
    public EngineType getEngine() {
        return this.Engine;
    }
    
    public void setEngine(EngineType Engine) {
        this.Engine = Engine;
    }

    
    @Column(name="IMPL_NAME", columnDefinition="VARCHAR", length=256)
    public String getImplementationName() {
        return this.ImplementationName;
    }
    
    public void setImplementationName(String ImplementationName) {
        this.ImplementationName = ImplementationName;
    }




}


