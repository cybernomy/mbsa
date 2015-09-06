/*
 * ContactRest.java
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
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.crm.model.Relation;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * @author leonova
 * @version $Id: ContactRest.java,v 1.2 2006/09/06 05:23:16 leonova Exp $
 */
public class ContactRest extends DefaultRestrictionForm {
  @DataItemName("CRM.Contact.Contractor")
  private Contractor contractorCode = null;
  private Relation relationName = null;
  @DataItemName("CRM.Contact.Person")
  private NaturalPerson code = null;

  @Override
  protected void doClearRestrictionItem() {
    this.contractorCode = null;
    this.relationName = null;
    this.code = null;
  }

  /**
   * @return Returns the code.
   */
  protected NaturalPerson getCode() {
    return code;
  }

  /**
   * @return Returns the contractorCode.
   */
  protected Contractor getContractorCode() {
    return contractorCode;
  }

  /**
   * @return Returns the relationName.
   */
  protected Relation getRelationName() {
    return relationName;
  }


}
