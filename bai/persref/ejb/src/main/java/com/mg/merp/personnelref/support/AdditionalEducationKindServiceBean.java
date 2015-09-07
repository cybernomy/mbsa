/*
 * AdditionalEducationKindServiceBean.java
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
import com.mg.merp.personnelref.AdditionalEducationKindServiceLocal;
import com.mg.merp.personnelref.model.AdditionalEducationKind;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Виды дополнительного образования"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: AdditionalEducationKindServiceBean.java,v 1.4 2007/01/16 09:29:46 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/AdditionalEducationKindService") //$NON-NLS-1$
public class AdditionalEducationKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AdditionalEducationKind, Integer> implements AdditionalEducationKindServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, AdditionalEducationKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
  }


}
