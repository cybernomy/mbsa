/*
 * PersonnelTransferServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */

package com.mg.merp.personnelref.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.personnelref.PersonnelTransferServiceLocal;
import com.mg.merp.personnelref.model.PersonnelTransfer;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Прием и переводы на работу"
 *
 * @author leonova
 * @version $Id: PersonnelTransferServiceBean.java,v 1.4 2007/07/10 07:32:26 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelTransferService") //$NON-NLS-1$
public class PersonnelTransferServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelTransfer, Integer> implements PersonnelTransferServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelTransfer entity) {
    context.addRule(new MandatoryAttribute(entity, "TransferDate")); //$NON-NLS-1$
  }

}
