package com.mg.merp.bpm.model;

import com.mg.merp.core.model.SysClient;
import com.mg.merp.security.model.SecUser;
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
 * Resource generated by hbm2java
 */
@Entity
@Table(name = "MBPM_RESOURCE")
public class Resource extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private int Id;

    private SysClient SysClient;

    private String Name;

    private String Key;

    private SecUser User;

    private Set<ResourceGroupLink> GroupLinks = new HashSet<ResourceGroupLink>(0);

    public Resource() {
    }

    public Resource(String Name, String Key) {
        this.Name = Name;
        this.Key = Key;
    }

    public Resource(SysClient SysClient, String Name, String Key, SecUser User, Set<ResourceGroupLink> GroupLinks) {
        this.SysClient = SysClient;
        this.Name = Name;
        this.Key = Key;
        this.User = User;
        this.GroupLinks = GroupLinks;
    }

    @SequenceGenerator(name = "generator", sequenceName = "MBPM_RESOURCE_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
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

    @Column(name = "RESOURCE_NAME", nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "RESOURCE_KEY", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getKey() {
        return this.Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public SecUser getUser() {
        return this.User;
    }

    public void setUser(SecUser User) {
        this.User = User;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Resource")
    public Set<ResourceGroupLink> getGroupLinks() {
        return this.GroupLinks;
    }

    public void setGroupLinks(Set<ResourceGroupLink> GroupLinks) {
        this.GroupLinks = GroupLinks;
    }
}

