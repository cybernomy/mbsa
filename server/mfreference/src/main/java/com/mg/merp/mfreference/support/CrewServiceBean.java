/*
 * CrewServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.mfreference.CrewServiceLocal;
import com.mg.merp.mfreference.model.Crew;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Бригады"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: CrewServiceBean.java,v 1.4 2007/08/23 09:48:31 alikaev Exp $
 */
@Stateless(name = "merp/mfreference/CrewService") //$NON-NLS-1$
public class CrewServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Crew, Integer> implements CrewServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Crew entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Crew entity) {
    adjustCrew(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Crew entity) {
    adjustCrew(entity);
  }

  /**
   * функция заполнения поля "Name"
   */
  private void adjustCrew(Crew entity) {
    if (entity.getName() == null)
      entity.setName(StringUtils.EMPTY_STRING);
  }

}
