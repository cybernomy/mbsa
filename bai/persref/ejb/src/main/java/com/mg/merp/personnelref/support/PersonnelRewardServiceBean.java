/*
 * PersonnelRewardServiceBean.java
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
import com.mg.merp.personnelref.PersonnelRewardServiceLocal;
import com.mg.merp.personnelref.model.PersonnelReward;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Поощрения и награды"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelRewardServiceBean.java,v 1.4 2007/01/16 08:22:41 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelRewardService") //$NON-NLS-1$
public class PersonnelRewardServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelReward, Integer> implements PersonnelRewardServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelReward entity) {
    context.addRule(new MandatoryStringAttribute(entity, "RewardName")); //$NON-NLS-1$
  }

}
