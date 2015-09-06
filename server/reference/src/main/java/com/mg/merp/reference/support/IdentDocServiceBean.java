/*
 * IdentDocServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.IdentDocServiceLocal;
import com.mg.merp.reference.model.IdentDoc;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Документы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: IdentDocServiceBean.java,v 1.4 2007/01/16 06:12:09 sharapov Exp $
 */
@Stateless(name = "merp/reference/IdentDocService") //$NON-NLS-1$
public class IdentDocServiceBean extends AbstractPOJODataBusinessObjectServiceBean<IdentDoc, Integer> implements IdentDocServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, IdentDoc entity) {
    context.addRule(new MandatoryStringAttribute(entity, "IdentDocKind")); //$NON-NLS-1$
  }

}
