/*
 * ReserveCategoryServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.ReserveCategoryServiceLocal;
import com.mg.merp.personnelref.model.ReserveCategory;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Категории резерва"
 *
 * @author leonova
 * @version $Id: ReserveCategoryServiceBean.java,v 1.3 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name = "merp/personnelref/ReserveCategoryService")
public class ReserveCategoryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ReserveCategory, Integer> implements ReserveCategoryServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, ReserveCategory entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
  }


}
