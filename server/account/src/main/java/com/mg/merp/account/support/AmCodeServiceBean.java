/*
 * AmCodeServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.account.AmCodeServiceLocal;
import com.mg.merp.account.model.AmCode;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Шифры амортизации"
 *
 * @author leonova
 * @version $Id: AmCodeServiceBean.java,v 1.3 2006/09/01 13:02:40 leonova Exp $
 */
@Stateless(name = "merp/account/AmCodeService")
public class AmCodeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AmCode, Integer> implements AmCodeServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, AmCode entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "CName"));
  }


}
