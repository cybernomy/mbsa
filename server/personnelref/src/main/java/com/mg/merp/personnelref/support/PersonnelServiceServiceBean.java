/*
 * PersonnelServiceServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.PersonnelServiceServiceLocal;
import com.mg.merp.personnelref.model.PersonnelService;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Стаж сотрудника"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelServiceServiceBean.java,v 1.4 2007/01/16 07:32:16 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelServiceService") //$NON-NLS-1$
public class PersonnelServiceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelService, Integer> implements PersonnelServiceServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelService entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ServiceKind")); //$NON-NLS-1$
  }


}
