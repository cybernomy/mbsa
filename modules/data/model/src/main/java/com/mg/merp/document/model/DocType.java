package com.mg.merp.document.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.report.model.RptMain;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * DocType generated by hbm2java
 */
@Entity
@Table(name="TYPEDOC"
)
public class DocType extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private RptMain Report;
     private SysClient SysClient;
     private Repository NumberingAlgorithm;
     private String UpCode;
     private String Code;
     private String Name;
     private boolean SolidDocProcess;
     private Set<DocTypeRights> SetOfDocTypeRights = new HashSet<DocTypeRights>(0);
     private Set<DocTypeDocSectionLink> SetOfDocTypeDocSectionLink = new HashSet<DocTypeDocSectionLink>(0);

    public DocType() {
    }

	
    public DocType(String UpCode) {
        this.UpCode = UpCode;
    }
    public DocType(RptMain Report, SysClient SysClient, Repository NumberingAlgorithm, String UpCode, String Code, String Name, boolean SolidDocProcess, Set<DocTypeRights> SetOfDocTypeRights, Set<DocTypeDocSectionLink> SetOfDocTypeDocSectionLink) {
       this.Report = Report;
       this.SysClient = SysClient;
       this.NumberingAlgorithm = NumberingAlgorithm;
       this.UpCode = UpCode;
       this.Code = Code;
       this.Name = Name;
       this.SolidDocProcess = SolidDocProcess;
       this.SetOfDocTypeRights = SetOfDocTypeRights;
       this.SetOfDocTypeDocSectionLink = SetOfDocTypeDocSectionLink;
    }
   
     @SequenceGenerator(name="generator", sequenceName="TYPEDOC_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RPT_ID")
    public RptMain getReport() {
        return this.Report;
    }
    
    public void setReport(RptMain Report) {
        this.Report = Report;
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
    @JoinColumn(name="NUMBERING_ALG_ID")
    public Repository getNumberingAlgorithm() {
        return this.NumberingAlgorithm;
    }
    
    public void setNumberingAlgorithm(Repository NumberingAlgorithm) {
        this.NumberingAlgorithm = NumberingAlgorithm;
    }

    
    @Column(name="UPCODE", unique=true, nullable=false, columnDefinition="CHAR", length=15)
    public String getUpCode() {
        return this.UpCode;
    }
    
    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    
    @Column(name="CODE", columnDefinition="CHAR", length=15)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="TDNAME", columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="SOLID_DOC_PROCESS", columnDefinition="SMALLINT")
    public boolean isSolidDocProcess() {
        return this.SolidDocProcess;
    }
    
    public void setSolidDocProcess(boolean SolidDocProcess) {
        this.SolidDocProcess = SolidDocProcess;
    }

@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="DocType")
    public Set<DocTypeRights> getSetOfDocTypeRights() {
        return this.SetOfDocTypeRights;
    }
    
    public void setSetOfDocTypeRights(Set<DocTypeRights> SetOfDocTypeRights) {
        this.SetOfDocTypeRights = SetOfDocTypeRights;
    }

@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="DocType")
    public Set<DocTypeDocSectionLink> getSetOfDocTypeDocSectionLink() {
        return this.SetOfDocTypeDocSectionLink;
    }
    
    public void setSetOfDocTypeDocSectionLink(Set<DocTypeDocSectionLink> SetOfDocTypeDocSectionLink) {
        this.SetOfDocTypeDocSectionLink = SetOfDocTypeDocSectionLink;
    }




}

