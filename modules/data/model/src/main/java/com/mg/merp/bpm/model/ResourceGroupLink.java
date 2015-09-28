package com.mg.merp.bpm.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


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
 * ResourceGroupLink generated by hbm2java
 */
@Entity
@Table(name="MBPM_RESOURCE_GROUP_LINK"
)
public class ResourceGroupLink extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private int Id;
     private Groups Group;
     private Resource Resource;

    public ResourceGroupLink() {
    }

    public ResourceGroupLink(Groups Group, Resource Resource) {
       this.Group = Group;
       this.Resource = Resource;
    }
   
     @SequenceGenerator(name="generator", sequenceName="MBPM_RESOURCE_GROUP_LINK_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, nullable=false, columnDefinition="INTEGER")
    public int getId() {
        return this.Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    public Groups getGroup() {
        return this.Group;
    }
    
    public void setGroup(Groups Group) {
        this.Group = Group;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESOURCE_ID")
    public Resource getResource() {
        return this.Resource;
    }
    
    public void setResource(Resource Resource) {
        this.Resource = Resource;
    }




}


