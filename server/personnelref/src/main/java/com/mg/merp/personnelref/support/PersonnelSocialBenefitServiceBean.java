/*
 * PersonnelSocialBenefitServiceBean.java
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
import com.mg.merp.personnelref.PersonnelSocialBenefitServiceLocal;
import com.mg.merp.personnelref.model.PersonnelSocialBenefit;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Социальные льготы"
 *
 * @author Artem V. Sharapov
 * @author leonova
 * @version $Id: PersonnelSocialBenefitServiceBean.java,v 1.4 2007/01/16 07:52:38 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelSocialBenefitService") //$NON-NLS-1$
public class PersonnelSocialBenefitServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelSocialBenefit, Integer> implements PersonnelSocialBenefitServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelSocialBenefit entity) {
    context.addRule(new MandatoryStringAttribute(entity, "BenefitName")); //$NON-NLS-1$
  }

}
