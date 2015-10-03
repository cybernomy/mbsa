package com.mg.merp.core.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SystemConfig generated by hbm2java
 */
@Entity
@Table(name = "SYSTEMCONFIG")
public class SystemConfig extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private SystemConfigId id;

    public SystemConfig() {
    }

    public SystemConfig(SystemConfigId id) {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "MajorVersion", column = @Column(name = "MAJOR_VERSION", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "MinorVersion", column = @Column(name = "MINOR_VERSION", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "Release", column = @Column(name = "RELEASE", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "UseAnaliticLink", column = @Column(name = "USE_ANALITIC_LINK", columnDefinition = "SMALLINT")), @AttributeOverride(name = "RecurseFolder", column = @Column(name = "RECURSE_FOLDER", columnDefinition = "SMALLINT")), @AttributeOverride(name = "RecurseCatfolder", column = @Column(name = "RECURSE_CATFOLDER", columnDefinition = "SMALLINT")), @AttributeOverride(name = "RecurseOrgunit", column = @Column(name = "RECURSE_ORGUNIT", columnDefinition = "SMALLINT")), @AttributeOverride(name = "DelaysOn", column = @Column(name = "DELAYS_ON", columnDefinition = "SMALLINT")), @AttributeOverride(name = "CurrencyPrec", column = @Column(name = "CURRENCY_PREC", columnDefinition = "SMALLINT")), @AttributeOverride(name = "DeferRemnacc", column = @Column(name = "DEFER_REMNACC", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "LogV1DocDel", column = @Column(name = "LOG_V1_DOC_DEL", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "LogV1Rollback", column = @Column(name = "LOG_V1_ROLLBACK", nullable = false, columnDefinition = "SMALLINT")), @AttributeOverride(name = "LogV1Partner", column = @Column(name = "LOG_V1_PARTNER", columnDefinition = "SMALLINT")), @AttributeOverride(name = "LogV1Catalog", column = @Column(name = "LOG_V1_CATALOG", columnDefinition = "SMALLINT")) })
    public SystemConfigId getId() {
        return this.id;
    }

    public void setId(SystemConfigId id) {
        this.id = id;
    }
}

