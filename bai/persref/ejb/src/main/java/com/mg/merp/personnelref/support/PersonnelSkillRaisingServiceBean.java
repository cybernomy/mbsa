/*
 * PersonnelSkillRaisingServiceBean.java
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
import com.mg.merp.personnelref.PersonnelSkillRaisingServiceLocal;
import com.mg.merp.personnelref.model.PersonnelSkillRaising;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Повышение квалификации"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelSkillRaisingServiceBean.java,v 1.4 2007/01/16 07:37:42 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelSkillRaisingService") //$NON-NLS-1$
public class PersonnelSkillRaisingServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelSkillRaising, Integer> implements PersonnelSkillRaisingServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelSkillRaising entity) {
    context.addRule(new MandatoryStringAttribute(entity, "SkillRaisingKind")); //$NON-NLS-1$
  }

}
