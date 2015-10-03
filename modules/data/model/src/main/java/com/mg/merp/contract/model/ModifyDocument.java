package com.mg.merp.contract.model;
// Generated Oct 4, 2015 2:18:05 AM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;
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
 * ModifyDocument generated by hbm2java
 */
@Entity
@Table(name="CONTRACTMODIFYDOC"
)
public class ModifyDocument extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private Integer Id;
     private DocHead DocHead;
     private SysClient SysClient;
     private DocType DocType;
     private String DocNumber;
     private Date DocDate;
     private String ModifyDesc;
     private String Comment;
     private byte[] Original;
     private DocHead Document;

    public ModifyDocument() {
    }

    public ModifyDocument(DocHead DocHead, SysClient SysClient, DocType DocType, String DocNumber, Date DocDate, String ModifyDesc, String Comment, byte[] Original, DocHead Document) {
       this.DocHead = DocHead;
       this.SysClient = SysClient;
       this.DocType = DocType;
       this.DocNumber = DocNumber;
       this.DocDate = DocDate;
       this.ModifyDesc = ModifyDesc;
       this.Comment = Comment;
       this.Original = Original;
       this.Document = Document;
    }
   
     @SequenceGenerator(name="generator", sequenceName="CONTRACTMODIFYDOC_ID_GEN")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="ID", unique=true, columnDefinition="INTEGER")
    public Integer getId() {
        return this.Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCHEAD_ID")
    public DocHead getDocHead() {
        return this.DocHead;
    }
    
    public void setDocHead(DocHead DocHead) {
        this.DocHead = DocHead;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOCTYPE")
    public DocType getDocType() {
        return this.DocType;
    }
    
    public void setDocType(DocType DocType) {
        this.DocType = DocType;
    }

    
    @Column(name="DOCNUMBER", columnDefinition="CHAR", length=20)
    public String getDocNumber() {
        return this.DocNumber;
    }
    
    public void setDocNumber(String DocNumber) {
        this.DocNumber = DocNumber;
    }

    
    @Column(name="DOCDATE", columnDefinition="TIMESTAMP")
    public Date getDocDate() {
        return this.DocDate;
    }
    
    public void setDocDate(Date DocDate) {
        this.DocDate = DocDate;
    }

    
    @Column(name="MODIFYDESC", columnDefinition="VARCHAR", length=2048)
    public String getModifyDesc() {
        return this.ModifyDesc;
    }
    
    public void setModifyDesc(String ModifyDesc) {
        this.ModifyDesc = ModifyDesc;
    }

    
    @Column(name="COMMENT", columnDefinition="VARCHAR", length=2048)
    public String getComment() {
        return this.Comment;
    }
    
    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    
    @Column(name="ORIGINAL", columnDefinition="BLOB SUB_TYPE 0")
    public byte[] getOriginal() {
        return this.Original;
    }
    
    public void setOriginal(byte[] Original) {
        this.Original = Original;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOC_ID")
    public DocHead getDocument() {
        return this.Document;
    }
    
    public void setDocument(DocHead Document) {
        this.Document = Document;
    }




}


