/*
 * ForeignLanguageServiceBean.java
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
import com.mg.merp.personnelref.ForeignLanguageServiceLocal;
import com.mg.merp.personnelref.model.ForeignLanguage;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Иностранные языки"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ForeignLanguageServiceBean.java,v 1.4 2007/01/16 09:24:32 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/ForeignLanguageService") //$NON-NLS-1$
public class ForeignLanguageServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ForeignLanguage, Integer> implements ForeignLanguageServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, ForeignLanguage entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
  }

}
