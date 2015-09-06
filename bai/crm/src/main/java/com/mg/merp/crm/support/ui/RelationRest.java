/*
 * RelationRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.crm.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.crm.model.Relation;
import com.mg.merp.crm.model.RelationStatus;
import com.mg.merp.crm.model.User;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Контроллер формы условий отбора
 *
 * @author leonova
 * @version $Id: RelationRest.java,v 1.5 2006/10/16 07:17:51 leonova Exp $
 */
public class RelationRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("CRM.BigCode")
  private String code = "";
  @DataItemName("CRM.BigName")
  private String name = "";
  @DataItemName("CRM.Relation.Curator")
  private User curatorCode = null;
  @DataItemName("CRM.Relation.Responsible")
  private User responsibleCode = null;
  private RelationStatus statusCode = null;
  @DataItemName("CRM.Relation.Parent")
  private Relation parentCode = null;
  private NaturalPerson personCode = null;
  @DataItemName("CRM.Relation.LegalPerson")
  private Contractor partnerCode = null;
  @DataItemName("CRM.Relation.NickName")
  private String nick = "";
  private NaturalPerson contactPersonCode = null;
  @DataItemName("CRM.Contact.NickName")
  private String contactNick = "";
  @DataItemName("CRM.Contact.Contractor")
  private Contractor contactCompanyCode = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.responsibleCode = null;
    this.curatorCode = null;
    this.code = "";
    this.name = "";
    this.parentCode = null;
    this.statusCode = null;
    this.personCode = null;
    this.partnerCode = null;
    this.nick = "";
    this.contactNick = "";
    this.contactCompanyCode = null;
    this.contactPersonCode = null;
  }

  /**
   * @return Returns the code.
   */
  protected String getCode() {
    return code;
  }

  /**
   * @return Returns the contactCompanyCode.
   */
  protected Contractor getContactCompanyCode() {
    return contactCompanyCode;
  }

  /**
   * @return Returns the contactNick.
   */
  protected String getContactNick() {
    return contactNick;
  }

  /**
   * @return Returns the contactPersonCode.
   */
  protected NaturalPerson getContactPersonCode() {
    return contactPersonCode;
  }

  /**
   * @return Returns the curatorCode.
   */
  protected User getCuratorCode() {
    return curatorCode;
  }

  /**
   * @return Returns the name.
   */
  protected String getName() {
    return name;
  }

  /**
   * @return Returns the nick.
   */
  protected String getNick() {
    return nick;
  }

  /**
   * @return Returns the parentCode.
   */
  protected Relation getParentCode() {
    return parentCode;
  }

  /**
   * @return Returns the partnerCode.
   */
  protected Contractor getPartnerCode() {
    return partnerCode;
  }

  /**
   * @return Returns the personCode.
   */
  protected NaturalPerson getPersonCode() {
    return personCode;
  }

  /**
   * @return Returns the responsibleCode.
   */
  protected User getResponsibleCode() {
    return responsibleCode;
  }

  /**
   * @return Returns the statusCode.
   */
  protected RelationStatus getStatusCode() {
    return statusCode;
  }


}
