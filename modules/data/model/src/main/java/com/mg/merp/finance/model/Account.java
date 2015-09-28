package com.mg.merp.finance.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Currency;
import java.util.HashSet;
import java.util.Set;
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
 * Account generated by hbm2java
 */
@Entity
@Table(name="FINACCOUNT"
)
public class Account extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClass Anl4Class;
     private Folder Folder;
     private SysClient SysClient;
     private SysClass Anl3Class;
     private SysClass Anl5Class;
     private SysClass Anl2Class;
     private SysClass Anl1Class;
     private Currency CurrencyCode;
     private String Code;
     private String UpCode;
     private String AccName;
     private short Kind;
     private boolean Anl1Kind;
     private boolean Anl2Kind;
     private boolean Anl3Kind;
     private boolean Anl4Kind;
     private boolean Anl5Kind;
     private Set<Analytics> SetOfFinAnl = new HashSet<Analytics>(0);

    public Account() {
    }

	
    public Account(String Code, String UpCode, short Kind) {
        this.Code = Code;
        this.UpCode = UpCode;
        this.Kind = Kind;
    }
    public Account(SysClass Anl4Class, Folder Folder, SysClient SysClient, SysClass Anl3Class, SysClass Anl5Class, SysClass Anl2Class, SysClass Anl1Class, Currency CurrencyCode, String Code, String UpCode, String AccName, short Kind, boolean Anl1Kind, boolean Anl2Kind, boolean Anl3Kind, boolean Anl4Kind, boolean Anl5Kind, Set<Analytics> SetOfFinAnl) {
       this.Anl4Class = Anl4Class;
       this.Folder = Folder;
       this.SysClient = SysClient;
       this.Anl3Class = Anl3Class;
       this.Anl5Class = Anl5Class;
       this.Anl2Class = Anl2Class;
       this.Anl1Class = Anl1Class;
       this.CurrencyCode = CurrencyCode;
       this.Code = Code;
       this.UpCode = UpCode;
       this.AccName = AccName;
       this.Kind = Kind;
       this.Anl1Kind = Anl1Kind;
       this.Anl2Kind = Anl2Kind;
       this.Anl3Kind = Anl3Kind;
       this.Anl4Kind = Anl4Kind;
       this.Anl5Kind = Anl5Kind;
       this.SetOfFinAnl = SetOfFinAnl;
    }
   
     @SequenceGenerator(name="generator", sequenceName="FINACCOUNT_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANL4_CLASS_ID")
    public SysClass getAnl4Class() {
        return this.Anl4Class;
    }
    
    public void setAnl4Class(SysClass Anl4Class) {
        this.Anl4Class = Anl4Class;
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
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANL3_CLASS_ID")
    public SysClass getAnl3Class() {
        return this.Anl3Class;
    }
    
    public void setAnl3Class(SysClass Anl3Class) {
        this.Anl3Class = Anl3Class;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANL5_CLASS_ID")
    public SysClass getAnl5Class() {
        return this.Anl5Class;
    }
    
    public void setAnl5Class(SysClass Anl5Class) {
        this.Anl5Class = Anl5Class;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANL2_CLASS_ID")
    public SysClass getAnl2Class() {
        return this.Anl2Class;
    }
    
    public void setAnl2Class(SysClass Anl2Class) {
        this.Anl2Class = Anl2Class;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ANL1_CLASS_ID")
    public SysClass getAnl1Class() {
        return this.Anl1Class;
    }
    
    public void setAnl1Class(SysClass Anl1Class) {
        this.Anl1Class = Anl1Class;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_CODE")
    public Currency getCurrencyCode() {
        return this.CurrencyCode;
    }
    
    public void setCurrencyCode(Currency CurrencyCode) {
        this.CurrencyCode = CurrencyCode;
    }

    
    @Column(name="CODE", nullable=false, columnDefinition="CHAR", length=20)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="UPCODE", nullable=false, columnDefinition="CHAR", length=20)
    public String getUpCode() {
        return this.UpCode;
    }
    
    public void setUpCode(String UpCode) {
        this.UpCode = UpCode;
    }

    
    @Column(name="ACCNAME", columnDefinition="VARCHAR", length=80)
    public String getAccName() {
        return this.AccName;
    }
    
    public void setAccName(String AccName) {
        this.AccName = AccName;
    }

    
    @Column(name="KIND", nullable=false, columnDefinition="SMALLINT")
    public short getKind() {
        return this.Kind;
    }
    
    public void setKind(short Kind) {
        this.Kind = Kind;
    }

    
    @Column(name="ANL1_KIND", columnDefinition="SMALLINT")
    public boolean isAnl1Kind() {
        return this.Anl1Kind;
    }
    
    public void setAnl1Kind(boolean Anl1Kind) {
        this.Anl1Kind = Anl1Kind;
    }

    
    @Column(name="ANL2_KIND", columnDefinition="SMALLINT")
    public boolean isAnl2Kind() {
        return this.Anl2Kind;
    }
    
    public void setAnl2Kind(boolean Anl2Kind) {
        this.Anl2Kind = Anl2Kind;
    }

    
    @Column(name="ANL3_KIND", columnDefinition="SMALLINT")
    public boolean isAnl3Kind() {
        return this.Anl3Kind;
    }
    
    public void setAnl3Kind(boolean Anl3Kind) {
        this.Anl3Kind = Anl3Kind;
    }

    
    @Column(name="ANL4_KIND", columnDefinition="SMALLINT")
    public boolean isAnl4Kind() {
        return this.Anl4Kind;
    }
    
    public void setAnl4Kind(boolean Anl4Kind) {
        this.Anl4Kind = Anl4Kind;
    }

    
    @Column(name="ANL5_KIND", columnDefinition="SMALLINT")
    public boolean isAnl5Kind() {
        return this.Anl5Kind;
    }
    
    public void setAnl5Kind(boolean Anl5Kind) {
        this.Anl5Kind = Anl5Kind;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="FinAcc")
    public Set<Analytics> getSetOfFinAnl() {
        return this.SetOfFinAnl;
    }
    
    public void setSetOfFinAnl(Set<Analytics> SetOfFinAnl) {
        this.SetOfFinAnl = SetOfFinAnl;
    }




}


