package com.mg.merp.reference.model;

import com.mg.merp.core.model.SysClient;
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
 * CustomsDeclaration generated by hbm2java
 */
@Entity
@Table(name = "REF_CUSTOMS_DECLARATION")
public class CustomsDeclaration extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {

    private Integer Id;

    private SysClient SysClient;

    private String Number;

    private String Note;

    public CustomsDeclaration() {
    }

    public CustomsDeclaration(String Number) {
        this.Number = Number;
    }

    public CustomsDeclaration(SysClient SysClient, String Number, String Note) {
        this.SysClient = SysClient;
        this.Number = Number;
        this.Note = Note;
    }

    @SequenceGenerator(name = "generator", sequenceName = "REF_CUSTOMS_DECLARATION_ID_GEN")
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

    @Column(name = "CDNUMBER", unique = true, nullable = false, columnDefinition = "VARCHAR", length = 30)
    public String getNumber() {
        return this.Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    @Column(name = "NOTE", columnDefinition = "VARCHAR", length = 256)
    public String getNote() {
        return this.Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
}

