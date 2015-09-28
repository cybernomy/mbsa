package com.mg.merp.reference.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BankRequis generated by hbm2java
 */
@Entity
@Table(name="BANKREQUIS"
)
public class BankRequis extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private BankRequisId id;

    public BankRequis() {
    }

    public BankRequis(BankRequisId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="Id", column=@Column(name="ID", columnDefinition="INTEGER") ), 
        @AttributeOverride(name="Partner", column=@Column(name="PARTNER_ID", columnDefinition="INTEGER") ), 
        @AttributeOverride(name="Bank", column=@Column(name="BANK", columnDefinition="VARCHAR", length=256) ), 
        @AttributeOverride(name="BankAcc", column=@Column(name="BANK_ACC", columnDefinition="VARCHAR", length=30) ), 
        @AttributeOverride(name="CorrAcc", column=@Column(name="CORR_ACC", columnDefinition="VARCHAR", length=30) ), 
        @AttributeOverride(name="BankAddr", column=@Column(name="BANK_ADDR", columnDefinition="VARCHAR", length=256) ), 
        @AttributeOverride(name="BankCity", column=@Column(name="BANK_CITY", columnDefinition="VARCHAR", length=256) ), 
        @AttributeOverride(name="BankIdent", column=@Column(name="BANK_IDENT", columnDefinition="VARCHAR", length=30) ), 
        @AttributeOverride(name="IsDefault", column=@Column(name="IS_DEFAULT", columnDefinition="SMALLINT") ), 
        @AttributeOverride(name="BankBranch", column=@Column(name="BANK_BRANCH", columnDefinition="VARCHAR", length=256) ), 
        @AttributeOverride(name="Unid", column=@Column(name="UNID", columnDefinition="VARCHAR", length=32) ) } )
    public BankRequisId getId() {
        return this.id;
    }
    
    public void setId(BankRequisId id) {
        this.id = id;
    }




}


