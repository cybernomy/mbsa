/*
 * PersonnelEducationServiceBean.java
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
import com.mg.merp.personnelref.PersonnelEducationServiceLocal;
import com.mg.merp.personnelref.model.PersonnelEducation;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Образование"
 *
 * @author Artem V. Sharapov
 * @author leonova
 * @version $Id: PersonnelEducationServiceBean.java,v 1.4 2007/01/16 07:47:31 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelEducationService") //$NON-NLS-1$
public class PersonnelEducationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelEducation, Integer> implements PersonnelEducationServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelEducation entity) {
    context.addRule(new MandatoryStringAttribute(entity, "InstitutionName")); //$NON-NLS-1$
  }

}
