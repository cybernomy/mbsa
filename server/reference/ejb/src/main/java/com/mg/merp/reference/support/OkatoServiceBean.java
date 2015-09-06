/*
 * OkatoServiceBean.java
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
import com.mg.merp.reference.OkatoServiceLocal;
import com.mg.merp.reference.model.Okato;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Коды ОКАТО"
 *
 * @author leonova
 * @version $Id: OkatoServiceBean.java,v 1.4 2006/10/19 04:35:38 leonova Exp $
 */
@Stateless(name = "merp/reference/OkatoService")
public class OkatoServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Okato, Integer> implements OkatoServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Okato entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
