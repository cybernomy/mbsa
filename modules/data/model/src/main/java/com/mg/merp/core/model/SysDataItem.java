package com.mg.merp.core.model;

import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.api.metadata.DataItemKind;
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
 * SysDataItem generated by hbm2java
 */
@Entity
@Table(name = "SYS_DATAITEM")
public class SysDataItem extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private ApplicationLayer ApplicationLayer;

    private String Name;

    private String Description;

    private SysDomain SysDomain;

    private String ReferenceDataItemName;

    private DataItemKind Kind;

    private String DefaultComponentName;

    private String ShortLabel;

    private String MediumLabel;

    private String LongLabel;

    private String Header;

    private String ReportLabel;

    private String SearchHelpName;

    private String EntityPropertyText;

    private String EntityTextFormat;

    private String AssignParameterName;

    private Boolean ReadOnly;

    public SysDataItem() {
    }

    public SysDataItem(ApplicationLayer ApplicationLayer, String Name, Boolean ReadOnly) {
        this.ApplicationLayer = ApplicationLayer;
        this.Name = Name;
        this.ReadOnly = ReadOnly;
    }

    public SysDataItem(ApplicationLayer ApplicationLayer, String Name, String Description, SysDomain SysDomain, String ReferenceDataItemName, DataItemKind Kind, String DefaultComponentName, String ShortLabel, String MediumLabel, String LongLabel, String Header, String ReportLabel, String SearchHelpName, String EntityPropertyText, String EntityTextFormat, String AssignParameterName, Boolean ReadOnly) {
        this.ApplicationLayer = ApplicationLayer;
        this.Name = Name;
        this.Description = Description;
        this.SysDomain = SysDomain;
        this.ReferenceDataItemName = ReferenceDataItemName;
        this.Kind = Kind;
        this.DefaultComponentName = DefaultComponentName;
        this.ShortLabel = ShortLabel;
        this.MediumLabel = MediumLabel;
        this.LongLabel = LongLabel;
        this.Header = Header;
        this.ReportLabel = ReportLabel;
        this.SearchHelpName = SearchHelpName;
        this.EntityPropertyText = EntityPropertyText;
        this.EntityTextFormat = EntityTextFormat;
        this.AssignParameterName = AssignParameterName;
        this.ReadOnly = ReadOnly;
    }

    @SequenceGenerator(name = "generator", sequenceName = "SYS_DATAITEM_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Column(name = "APP_LAYER", nullable = false, columnDefinition = "SMALLINT")
    public ApplicationLayer getApplicationLayer() {
        return this.ApplicationLayer;
    }

    public void setApplicationLayer(ApplicationLayer ApplicationLayer) {
        this.ApplicationLayer = ApplicationLayer;
    }

    @Column(name = "NAME", unique = true, nullable = false, columnDefinition = "VARCHAR", length = 80)
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR", length = 1024)
    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOMAIN_ID")
    public SysDomain getSysDomain() {
        return this.SysDomain;
    }

    public void setSysDomain(SysDomain SysDomain) {
        this.SysDomain = SysDomain;
    }

    @Column(name = "REFERENCE_DATAITEM_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getReferenceDataItemName() {
        return this.ReferenceDataItemName;
    }

    public void setReferenceDataItemName(String ReferenceDataItemName) {
        this.ReferenceDataItemName = ReferenceDataItemName;
    }

    @Column(name = "DATAITEM_KIND", columnDefinition = "SMALLINT")
    public DataItemKind getKind() {
        return this.Kind;
    }

    public void setKind(DataItemKind Kind) {
        this.Kind = Kind;
    }

    @Column(name = "DEFAULT_COMPONENT_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getDefaultComponentName() {
        return this.DefaultComponentName;
    }

    public void setDefaultComponentName(String DefaultComponentName) {
        this.DefaultComponentName = DefaultComponentName;
    }

    @Column(name = "SHORT_LABEL", columnDefinition = "VARCHAR", length = 80)
    public String getShortLabel() {
        return this.ShortLabel;
    }

    public void setShortLabel(String ShortLabel) {
        this.ShortLabel = ShortLabel;
    }

    @Column(name = "MEDIUM_LABEL", columnDefinition = "VARCHAR", length = 80)
    public String getMediumLabel() {
        return this.MediumLabel;
    }

    public void setMediumLabel(String MediumLabel) {
        this.MediumLabel = MediumLabel;
    }

    @Column(name = "LONG_LABEL", columnDefinition = "VARCHAR", length = 80)
    public String getLongLabel() {
        return this.LongLabel;
    }

    public void setLongLabel(String LongLabel) {
        this.LongLabel = LongLabel;
    }

    @Column(name = "HEADER", columnDefinition = "VARCHAR", length = 80)
    public String getHeader() {
        return this.Header;
    }

    public void setHeader(String Header) {
        this.Header = Header;
    }

    @Column(name = "REPORT_LABEL", columnDefinition = "VARCHAR", length = 80)
    public String getReportLabel() {
        return this.ReportLabel;
    }

    public void setReportLabel(String ReportLabel) {
        this.ReportLabel = ReportLabel;
    }

    @Column(name = "SEARCH_HELP", columnDefinition = "VARCHAR", length = 256)
    public String getSearchHelpName() {
        return this.SearchHelpName;
    }

    public void setSearchHelpName(String SearchHelpName) {
        this.SearchHelpName = SearchHelpName;
    }

    @Column(name = "ENTITY_PROPERTY_TEXT", columnDefinition = "VARCHAR", length = 80)
    public String getEntityPropertyText() {
        return this.EntityPropertyText;
    }

    public void setEntityPropertyText(String EntityPropertyText) {
        this.EntityPropertyText = EntityPropertyText;
    }

    @Column(name = "ENTITY_TEXT_FORMAT", columnDefinition = "VARCHAR", length = 80)
    public String getEntityTextFormat() {
        return this.EntityTextFormat;
    }

    public void setEntityTextFormat(String EntityTextFormat) {
        this.EntityTextFormat = EntityTextFormat;
    }

    @Column(name = "ASSIGN_PARAMETER_NAME", columnDefinition = "VARCHAR", length = 80)
    public String getAssignParameterName() {
        return this.AssignParameterName;
    }

    public void setAssignParameterName(String AssignParameterName) {
        this.AssignParameterName = AssignParameterName;
    }

    @Column(name = "IS_READ_ONLY", nullable = false, columnDefinition = "SMALLINT")
    public Boolean getReadOnly() {
        return this.ReadOnly;
    }

    public void setReadOnly(Boolean ReadOnly) {
        this.ReadOnly = ReadOnly;
    }
}

