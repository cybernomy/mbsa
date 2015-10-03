package com.mg.merp.core.model;

import com.mg.framework.api.metadata.ApplicationLayer;
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
 * EntityTransformerMapping generated by hbm2java
 */
@Entity
@Table(name = "ENTITY_TRANSFORMER_MAPPING")
public class EntityTransformerMapping extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Date SysVersion;

    private SysClient SysClient;

    private ApplicationLayer ApplicationLayer;

    private String ClassA;

    private String ClassB;

    private String MapId;

    private int HashAB;

    public EntityTransformerMapping() {
    }

    public EntityTransformerMapping(SysClient SysClient, ApplicationLayer ApplicationLayer, String ClassA, String ClassB, String MapId, int HashAB) {
        this.SysClient = SysClient;
        this.ApplicationLayer = ApplicationLayer;
        this.ClassA = ClassA;
        this.ClassB = ClassB;
        this.MapId = MapId;
        this.HashAB = HashAB;
    }

    @SequenceGenerator(name = "generator", sequenceName = "ENTITY_TRANSFORMER_MPNG_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SYS_VERSION", nullable = false)
    public Date getSysVersion() {
        return this.SysVersion;
    }

    public void setSysVersion(Date SysVersion) {
        this.SysVersion = SysVersion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "APP_LAYER", nullable = false, columnDefinition = "SMALLINT")
    public ApplicationLayer getApplicationLayer() {
        return this.ApplicationLayer;
    }

    public void setApplicationLayer(ApplicationLayer ApplicationLayer) {
        this.ApplicationLayer = ApplicationLayer;
    }

    @Column(name = "CLASS_A", nullable = false, columnDefinition = "VARCHAR", length = 256)
    public String getClassA() {
        return this.ClassA;
    }

    public void setClassA(String ClassA) {
        this.ClassA = ClassA;
    }

    @Column(name = "CLASS_B", nullable = false, columnDefinition = "VARCHAR", length = 256)
    public String getClassB() {
        return this.ClassB;
    }

    public void setClassB(String ClassB) {
        this.ClassB = ClassB;
    }

    @Column(name = "MAP_ID", unique = true, nullable = false, columnDefinition = "VARCHAR", length = 20)
    public String getMapId() {
        return this.MapId;
    }

    public void setMapId(String MapId) {
        this.MapId = MapId;
    }

    @Column(name = "HASHAB", nullable = false, columnDefinition = "INTEGER")
    public int getHashAB() {
        return this.HashAB;
    }

    public void setHashAB(int HashAB) {
        this.HashAB = HashAB;
    }
}

