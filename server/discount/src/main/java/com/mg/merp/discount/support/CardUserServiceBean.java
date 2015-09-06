/*
 * CarduserServiceBean.java
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
import com.mg.merp.discount.CardUserServiceLocal;
import com.mg.merp.discount.model.CardUser;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Пользователи дисконтной карты"
 *
 * @author leonova
 * @version $Id: CardUserServiceBean.java,v 1.4 2007/09/07 12:02:18 safonov Exp $
 */
@Stateless(name = "merp/discount/CardUserService")
public class CardUserServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CardUser, Integer> implements CardUserServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, CardUser entity) {
    context.addRule(new MandatoryAttribute(entity, "Contractor"));
  }

}
