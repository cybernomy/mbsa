package com.mg.merp.core.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.security.model.MethodAccess;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysMethod generated by hbm2java
 */
@Entity
@Table(name="SYS_METHOD"
)
public class SysMethod extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private SysClass SysClass;
     private String CorbaName;
     private String Description;
     private Set<MethodAccess> Permissions = new HashSet<MethodAccess>(0);

    public SysMethod() {
    }

	
    public SysMethod(Integer Id) {
        this.Id = Id;
    }
    public SysMethod(Integer Id, SysClass SysClass, String CorbaName, String Description, Set<MethodAccess> Permissions) {
       this.Id = Id;
       this.SysClass = SysClass;
       this.CorbaName = CorbaName;
       this.Description = Description;
       this.Permissions = Permissions;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLASS_ID")
    public SysClass getSysClass() {
        return this.SysClass;
    }
    
    public void setSysClass(SysClass SysClass) {
        this.SysClass = SysClass;
    }

    
    @Column(name="CORBA_NAME", columnDefinition="VARCHAR", length=80)
    public String getCorbaName() {
        return this.CorbaName;
    }
    
    public void setCorbaName(String CorbaName) {
        this.CorbaName = CorbaName;
    }

    
    @Column(name="DESCRIPTION", columnDefinition="VARCHAR", length=80)
    public String getDescription() {
        return this.Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="Method")
    public Set<MethodAccess> getPermissions() {
        return this.Permissions;
    }
    
    public void setPermissions(Set<MethodAccess> Permissions) {
        this.Permissions = Permissions;
    }




}

