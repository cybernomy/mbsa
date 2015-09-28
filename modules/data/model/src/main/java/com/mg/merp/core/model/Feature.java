package com.mg.merp.core.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.framework.support.orm.EnumUserType;
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
 * Feature generated by hbm2java
 */
@Entity
@Table(name="FEATURE"
)
public class Feature extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private SysClass SysClass;
     private SysClient SysClient;
     private String Name;
     private EnumUserType DataType;
     private Integer Priority;
     private String NullValue;
     private String Code;
     private boolean IsArray;
     private Short ArraySize;
     private Set<FeatureLink> FeatureLinks = new HashSet<FeatureLink>(0);
     private Set<FeatureVal> FeatureValues = new HashSet<FeatureVal>(0);

    public Feature() {
    }

	
    public Feature(String Name, String Code) {
        this.Name = Name;
        this.Code = Code;
    }
    public Feature(SysClass SysClass, SysClient SysClient, String Name, EnumUserType DataType, Integer Priority, String NullValue, String Code, boolean IsArray, Short ArraySize, Set<FeatureLink> FeatureLinks, Set<FeatureVal> FeatureValues) {
       this.SysClass = SysClass;
       this.SysClient = SysClient;
       this.Name = Name;
       this.DataType = DataType;
       this.Priority = Priority;
       this.NullValue = NullValue;
       this.Code = Code;
       this.IsArray = IsArray;
       this.ArraySize = ArraySize;
       this.FeatureLinks = FeatureLinks;
       this.FeatureValues = FeatureValues;
    }
   
     @SequenceGenerator(name="generator", sequenceName="FEATURE_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_CLASS_ID")
    public SysClass getSysClass() {
        return this.SysClass;
    }
    
    public void setSysClass(SysClass SysClass) {
        this.SysClass = SysClass;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    
    @Column(name="NAME", unique=true, nullable=false, columnDefinition="VARCHAR", length=80)
    public String getName() {
        return this.Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    
    @Column(name="DATATYPE", columnDefinition="SMALLINT")
    public EnumUserType getDataType() {
        return this.DataType;
    }
    
    public void setDataType(EnumUserType DataType) {
        this.DataType = DataType;
    }

    
    @Column(name="PRIORITY", columnDefinition="INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }
    
    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }

    
    @Column(name="NULL_VALUE", columnDefinition="VARCHAR", length=80)
    public String getNullValue() {
        return this.NullValue;
    }
    
    public void setNullValue(String NullValue) {
        this.NullValue = NullValue;
    }

    
    @Column(name="CODE", unique=true, nullable=false, columnDefinition="CHAR", length=20)
    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    
    @Column(name="IS_ARRAY", columnDefinition="SMALLINT")
    public boolean isIsArray() {
        return this.IsArray;
    }
    
    public void setIsArray(boolean IsArray) {
        this.IsArray = IsArray;
    }

    
    @Column(name="ARRAY_SIZE", columnDefinition="SMALLINT")
    public Short getArraySize() {
        return this.ArraySize;
    }
    
    public void setArraySize(Short ArraySize) {
        this.ArraySize = ArraySize;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Feature")
    public Set<FeatureLink> getFeatureLinks() {
        return this.FeatureLinks;
    }
    
    public void setFeatureLinks(Set<FeatureLink> FeatureLinks) {
        this.FeatureLinks = FeatureLinks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Feature")
    public Set<FeatureVal> getFeatureValues() {
        return this.FeatureValues;
    }
    
    public void setFeatureValues(Set<FeatureVal> FeatureValues) {
        this.FeatureValues = FeatureValues;
    }




}


