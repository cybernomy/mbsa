/*
 * CardHistServiceBean.java
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

package com.mg.merp.discount.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.discount.CardHistServiceLocal;
import com.mg.merp.discount.model.CardHist;
import com.mg.merp.security.model.SecUser;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "История дисконтных карт"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardHistServiceBean.java,v 1.5 2007/10/03 05:26:39 sharapov Exp $
 */
@Stateless(name = "merp/discount/CardHistService") //$NON-NLS-1$
public class CardHistServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CardHist, Integer> implements CardHistServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, CardHist entity) {
    context.addRule(new MandatoryAttribute(entity, "TimeStamp")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(CardHist entity) {
    entity.setUser(ServerUtils.getPersistentManager().find(SecUser.class, ServerUtils.getUserProfile().getIdentificator()));
  }

}
