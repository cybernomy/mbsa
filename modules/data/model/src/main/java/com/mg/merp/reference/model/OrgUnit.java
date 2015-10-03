package com.mg.merp.reference.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrgUnit generated by hbm2java
 */
@Entity
@Table(name="ORGUNIT"
)
public class OrgUnit extends com.mg.merp.reference.model.Contractor implements java.io.Serializable {


     private Contractor Partner;
     private OrgUnitType OrgUnitKind;
     private Boolean IsOffice;
     private Boolean IsStore;
     private String FolderTag;

    public OrgUnit() {
    }

	
    public OrgUnit(String Code, String UpCode, String FullName, short Kind) {
        super(Code, UpCode, FullName, Kind);        
    }
    public OrgUnit(SysClient SysClient, String Code, String UpCode, String FullName, Integer FolderId, short Kind, Contractor Partner, OrgUnitType OrgUnitKind, Boolean IsOffice, Boolean IsStore, String FolderTag) {
        super(SysClient, Code, UpCode, FullName, FolderId, Kind);        
       this.Partner = Partner;
       this.OrgUnitKind = OrgUnitKind;
       this.IsOffice = IsOffice;
       this.IsStore = IsStore;
       this.FolderTag = FolderTag;
    }
   

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTNER_ID")
    public Contractor getPartner() {
        return this.Partner;
    }
    
    public void setPartner(Contractor Partner) {
        this.Partner = Partner;
    }

    
    @Column(name="KIND", columnDefinition="SMALLINT")
    public OrgUnitType getOrgUnitKind() {
        return this.OrgUnitKind;
    }
    
    public void setOrgUnitKind(OrgUnitType OrgUnitKind) {
        this.OrgUnitKind = OrgUnitKind;
    }

    
    @Column(name="IS_OFFICE", columnDefinition="SMALLINT")
    public Boolean getIsOffice() {
        return this.IsOffice;
    }
    
    public void setIsOffice(Boolean IsOffice) {
        this.IsOffice = IsOffice;
    }

    
    @Column(name="IS_STORE", columnDefinition="SMALLINT")
    public Boolean getIsStore() {
        return this.IsStore;
    }
    
    public void setIsStore(Boolean IsStore) {
        this.IsStore = IsStore;
    }

    
    @Column(name="FOLDER_TAG", columnDefinition="VARCHAR", length=80)
    public String getFolderTag() {
        return this.FolderTag;
    }
    
    public void setFolderTag(String FolderTag) {
        this.FolderTag = FolderTag;
    }




}


