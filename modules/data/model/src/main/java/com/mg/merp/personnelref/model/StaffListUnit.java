package com.mg.merp.personnelref.model;
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
 * StaffListUnit generated by hbm2java
 */
@Entity
@Table(name="PREF_STAFF_LIST_UNIT"
)
public class StaffListUnit extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private CostsAnl CostsAnl1;
     private CostsAnl CostsAnl2;
     private CostsAnl CostsAnl3;
     private StaffListUnit Parent;
     private CostsAnl CostsAnl4;
     private SysClient SysClient;
     private TaxCalcKind TaxCalcKind;
     private StaffList StaffList;
     private CostsAnl CostsAnl5;
     private WorkSchedule WorkSchedule;
     private String UCode;
     private String UName;
     private String Comments;
     private String FolderTag;

    public StaffListUnit() {
    }

	
    public StaffListUnit(String UCode) {
        this.UCode = UCode;
    }
    public StaffListUnit(CostsAnl CostsAnl1, CostsAnl CostsAnl2, CostsAnl CostsAnl3, StaffListUnit Parent, CostsAnl CostsAnl4, SysClient SysClient, TaxCalcKind TaxCalcKind, StaffList StaffList, CostsAnl CostsAnl5, WorkSchedule WorkSchedule, String UCode, String UName, String Comments, String FolderTag) {
       this.CostsAnl1 = CostsAnl1;
       this.CostsAnl2 = CostsAnl2;
       this.CostsAnl3 = CostsAnl3;
       this.Parent = Parent;
       this.CostsAnl4 = CostsAnl4;
       this.SysClient = SysClient;
       this.TaxCalcKind = TaxCalcKind;
       this.StaffList = StaffList;
       this.CostsAnl5 = CostsAnl5;
       this.WorkSchedule = WorkSchedule;
       this.UCode = UCode;
       this.UName = UName;
       this.Comments = Comments;
       this.FolderTag = FolderTag;
    }
   
     @SequenceGenerator(name="generator", sequenceName="PREF_STAFF_LIST_UNIT_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL1_ID")
    public CostsAnl getCostsAnl1() {
        return this.CostsAnl1;
    }
    
    public void setCostsAnl1(CostsAnl CostsAnl1) {
        this.CostsAnl1 = CostsAnl1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL2_ID")
    public CostsAnl getCostsAnl2() {
        return this.CostsAnl2;
    }
    
    public void setCostsAnl2(CostsAnl CostsAnl2) {
        this.CostsAnl2 = CostsAnl2;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL3_ID")
    public CostsAnl getCostsAnl3() {
        return this.CostsAnl3;
    }
    
    public void setCostsAnl3(CostsAnl CostsAnl3) {
        this.CostsAnl3 = CostsAnl3;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    public StaffListUnit getParent() {
        return this.Parent;
    }
    
    public void setParent(StaffListUnit Parent) {
        this.Parent = Parent;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL4_ID")
    public CostsAnl getCostsAnl4() {
        return this.CostsAnl4;
    }
    
    public void setCostsAnl4(CostsAnl CostsAnl4) {
        this.CostsAnl4 = CostsAnl4;
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
    @JoinColumn(name="TAX_CALC_KIND_ID")
    public TaxCalcKind getTaxCalcKind() {
        return this.TaxCalcKind;
    }
    
    public void setTaxCalcKind(TaxCalcKind TaxCalcKind) {
        this.TaxCalcKind = TaxCalcKind;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STAFFLIST_ID")
    public StaffList getStaffList() {
        return this.StaffList;
    }
    
    public void setStaffList(StaffList StaffList) {
        this.StaffList = StaffList;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COSTS_ANL5_ID")
    public CostsAnl getCostsAnl5() {
        return this.CostsAnl5;
    }
    
    public void setCostsAnl5(CostsAnl CostsAnl5) {
        this.CostsAnl5 = CostsAnl5;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WORK_SCHEDULE_ID")
    public WorkSchedule getWorkSchedule() {
        return this.WorkSchedule;
    }
    
    public void setWorkSchedule(WorkSchedule WorkSchedule) {
        this.WorkSchedule = WorkSchedule;
    }

    
    @Column(name="UCODE", nullable=false, columnDefinition="CHAR", length=20)
    public String getUCode() {
        return this.UCode;
    }
    
    public void setUCode(String UCode) {
        this.UCode = UCode;
    }

    
    @Column(name="UNAME", columnDefinition="VARCHAR", length=80)
    public String getUName() {
        return this.UName;
    }
    
    public void setUName(String UName) {
        this.UName = UName;
    }

    
    @Column(name="COMMENTS", columnDefinition="VARCHAR", length=256)
    public String getComments() {
        return this.Comments;
    }
    
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    
    @Column(name="FOLDER_TAG", columnDefinition="VARCHAR", length=80)
    public String getFolderTag() {
        return this.FolderTag;
    }
    
    public void setFolderTag(String FolderTag) {
        this.FolderTag = FolderTag;
    }




}


