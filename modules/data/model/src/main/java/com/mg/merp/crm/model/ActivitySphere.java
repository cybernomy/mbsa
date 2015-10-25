package com.mg.merp.crm.model;

import com.mg.merp.core.model.SysClient;
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
 * ActivitySphere generated by hbm2java
 */
@Entity
@Table(name = "CRM_ACTIVITY_SPHERE")
public class ActivitySphere extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Code;

    private String Name;

    private Set<Relation> SetOfCrmRelation = new HashSet<Relation>(0);

    public ActivitySphere() {
    }

    public ActivitySphere(SysClient SysClient, String Code, String Name, Set<Relation> SetOfCrmRelation) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.Name = Name;
        this.SetOfCrmRelation = SetOfCrmRelation;
    }

    @SequenceGenerator(name = "generator", sequenceName = "CRM_ACTIVITY_SPHERE_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "CODE", columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_SPHERE_ID", updatable = false)
    public Set<Relation> getSetOfCrmRelation() {
        return this.SetOfCrmRelation;
    }

    public void setSetOfCrmRelation(Set<Relation> SetOfCrmRelation) {
        this.SetOfCrmRelation = SetOfCrmRelation;
    }
}

