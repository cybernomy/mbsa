package com.mg.merp.crm.model;
// Generated Sep 28, 2015 11:47:52 PM by Hibernate Tools 3.6.0.Final


import com.mg.merp.core.model.SysClient;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContactLinkId generated by hbm2java
 */
@Embeddable
public class ContactLinkId extends com.mg.merp.core.model.AbstractEntity implements java.io.Serializable {


     private SysClient SysClient;
     private Contact CrmContact;
     private Relation CrmRelation;

    public ContactLinkId() {
    }

    public ContactLinkId(SysClient SysClient, Contact CrmContact, Relation CrmRelation) {
       this.SysClient = SysClient;
       this.CrmContact = CrmContact;
       this.CrmRelation = CrmRelation;
    }
   


    @Column(name="CLIENT_ID", nullable=false)
    public SysClient getSysClient() {
        return this.SysClient;
    }
    
    public void setSysClient(SysClient SysClient) {
        this.SysClient = SysClient;
    }


    @Column(name="CONTACT_ID", nullable=false)
    public Contact getCrmContact() {
        return this.CrmContact;
    }
    
    public void setCrmContact(Contact CrmContact) {
        this.CrmContact = CrmContact;
    }


    @Column(name="RELATION_ID", nullable=false)
    public Relation getCrmRelation() {
        return this.CrmRelation;
    }
    
    public void setCrmRelation(Relation CrmRelation) {
        this.CrmRelation = CrmRelation;
    }




}


