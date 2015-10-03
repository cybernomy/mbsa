package com.mg.merp.reference.model;

import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
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

/**
 * OriginalDocument generated by hbm2java
 */
@Entity
@Table(name = "REF_ORIGINAL_DOCUMENT")
public class OriginalDocument extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private Folder Folder;

    private SysClient SysClient;

    private String DocNumber;

    private Date DocDate;

    private String DocName;

    private Date CreateDate;

    private String Comments;

    private byte[] Original;

    public OriginalDocument() {
    }

    public OriginalDocument(Folder Folder, SysClient SysClient, String DocNumber, Date DocDate, String DocName, Date CreateDate, String Comments, byte[] Original) {
        this.Folder = Folder;
        this.SysClient = SysClient;
        this.DocNumber = DocNumber;
        this.DocDate = DocDate;
        this.DocName = DocName;
        this.CreateDate = CreateDate;
        this.Comments = Comments;
        this.Original = Original;
    }

    @SequenceGenerator(name = "generator", sequenceName = "REF_ORIGINAL_DOCUMENT_ID_GEN")
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
    @JoinColumn(name = "FOLDER_ID")
    public Folder getFolder() {
        return this.Folder;
    }

    public void setFolder(Folder Folder) {
        this.Folder = Folder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

    @Column(name = "DOCNUMBER", columnDefinition = "CHAR", length = 20)
    public String getDocNumber() {
        return this.DocNumber;
    }

    public void setDocNumber(String DocNumber) {
        this.DocNumber = DocNumber;
    }

    @Column(name = "DOCDATE", columnDefinition = "TIMESTAMP")
    public Date getDocDate() {
        return this.DocDate;
    }

    public void setDocDate(Date DocDate) {
        this.DocDate = DocDate;
    }

    @Column(name = "DOCNAME", columnDefinition = "VARCHAR", length = 80)
    public String getDocName() {
        return this.DocName;
    }

    public void setDocName(String DocName) {
        this.DocName = DocName;
    }

    @Column(name = "CREATEDATE", columnDefinition = "TIMESTAMP")
    public Date getCreateDate() {
        return this.CreateDate;
    }

    public void setCreateDate(Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Column(name = "COMMENTS", columnDefinition = "VARCHAR", length = 256)
    public String getComments() {
        return this.Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    @Column(name = "ORIGINAL", columnDefinition = "BLOB SUB_TYPE 0")
    public byte[] getOriginal() {
        return this.Original;
    }

    public void setOriginal(byte[] Original) {
        this.Original = Original;
    }
}

