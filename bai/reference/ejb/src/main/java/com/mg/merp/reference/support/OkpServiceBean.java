/*
 * OkpServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
import com.mg.merp.reference.OkpServiceLocal;
import com.mg.merp.reference.model.Okp;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Коды ОКР"
 *
 * @author leonova
 * @version $Id: OkpServiceBean.java,v 1.4 2006/10/19 04:39:35 leonova Exp $
 */
@Stateless(name = "merp/reference/OkpService")
public class OkpServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Okp, Integer> implements OkpServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Okp entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }

}
