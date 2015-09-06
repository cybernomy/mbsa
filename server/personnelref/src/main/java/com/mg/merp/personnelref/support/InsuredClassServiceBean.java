/*
 * InsuredClassServiceBean.java
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.InsuredClassServiceLocal;
import com.mg.merp.personnelref.model.InsuredClass;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Категории плательщиков страховых взносов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InsuredClassServiceBean.java,v 1.4 2007/07/18 12:41:25 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/InsuredClassService") //$NON-NLS-1$
public class InsuredClassServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InsuredClass, Integer> implements InsuredClassServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, InsuredClass entity) {
    context.addRule(new MandatoryStringAttribute(entity, "CCode")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "CName")); //$NON-NLS-1$
  }

}
