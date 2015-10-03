package com.mg.merp.report.model;

import com.mg.merp.core.model.SysClient;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * RptMain generated by hbm2java
 */
@Entity
@Table(name = "RPT_MAIN")
public class RptMain extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Date SysVersion;

    private SysClient SysClient;

    private String Code;

    private String RptName;

    private boolean AskParams;

    private boolean DirectPrint;

    private String Comment;

    private boolean InvokeFromEdit;

    private byte[] Template;

    private Integer Priority;

    private String ParamsFormName;

    private String OutputFormat;

    private Set<RptRight> Permissions = new HashSet<RptRight>(0);

    private Set<ClassLink> ClassLinks = new HashSet<ClassLink>(0);

    public RptMain() {
    }

    public RptMain(String Code, boolean DirectPrint) {
        this.Code = Code;
        this.DirectPrint = DirectPrint;
    }

    public RptMain(SysClient SysClient, String Code, String RptName, boolean AskParams, boolean DirectPrint, String Comment, boolean InvokeFromEdit, byte[] Template, Integer Priority, String ParamsFormName, String OutputFormat, Set<RptRight> Permissions, Set<ClassLink> ClassLinks) {
        this.SysClient = SysClient;
        this.Code = Code;
        this.RptName = RptName;
        this.AskParams = AskParams;
        this.DirectPrint = DirectPrint;
        this.Comment = Comment;
        this.InvokeFromEdit = InvokeFromEdit;
        this.Template = Template;
        this.Priority = Priority;
        this.ParamsFormName = ParamsFormName;
        this.OutputFormat = OutputFormat;
        this.Permissions = Permissions;
        this.ClassLinks = ClassLinks;
    }

    @SequenceGenerator(name = "generator", sequenceName = "RPT_MAIN_ID_GEN")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", unique = true, columnDefinition = "INTEGER")
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
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "CODE", unique = true, nullable = false, columnDefinition = "CHAR", length = 20)
    public String getCode() {
        return this.Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Column(name = "RPTNAME", columnDefinition = "VARCHAR", length = 80)
    public String getRptName() {
        return this.RptName;
    }

    public void setRptName(String RptName) {
        this.RptName = RptName;
    }

    @Column(name = "ASKPARAMS", columnDefinition = "SMALLINT")
    public boolean isAskParams() {
        return this.AskParams;
    }

    public void setAskParams(boolean AskParams) {
        this.AskParams = AskParams;
    }

    @Column(name = "DIRECT_PRINT", nullable = false, columnDefinition = "SMALLINT")
    public boolean isDirectPrint() {
        return this.DirectPrint;
    }

    public void setDirectPrint(boolean DirectPrint) {
        this.DirectPrint = DirectPrint;
    }

    @Column(name = "COMMENT", columnDefinition = "VARCHAR", length = 256)
    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    @Column(name = "INVOKE_FROM_EDIT", columnDefinition = "SMALLINT")
    public boolean isInvokeFromEdit() {
        return this.InvokeFromEdit;
    }

    public void setInvokeFromEdit(boolean InvokeFromEdit) {
        this.InvokeFromEdit = InvokeFromEdit;
    }

    @Column(name = "TEMPLATE", columnDefinition = "BLOB SUB_TYPE 0")
    public byte[] getTemplate() {
        return this.Template;
    }

    public void setTemplate(byte[] Template) {
        this.Template = Template;
    }

    @Column(name = "PRIORITY", columnDefinition = "INTEGER")
    public Integer getPriority() {
        return this.Priority;
    }

    public void setPriority(Integer Priority) {
        this.Priority = Priority;
    }

    @Column(name = "PARAMS_FORM_NAME", columnDefinition = "VARCHAR", length = 256)
    public String getParamsFormName() {
        return this.ParamsFormName;
    }

    public void setParamsFormName(String ParamsFormName) {
        this.ParamsFormName = ParamsFormName;
    }

    @Column(name = "OUTPUT_FORMAT", columnDefinition = "CHAR", length = 10)
    public String getOutputFormat() {
        return this.OutputFormat;
    }

    public void setOutputFormat(String OutputFormat) {
        this.OutputFormat = OutputFormat;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Rpt")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public Set<RptRight> getPermissions() {
        return this.Permissions;
    }

    public void setPermissions(Set<RptRight> Permissions) {
        this.Permissions = Permissions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Report")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public Set<ClassLink> getClassLinks() {
        return this.ClassLinks;
    }

    public void setClassLinks(Set<ClassLink> ClassLinks) {
        this.ClassLinks = ClassLinks;
    }
}

