/*
 * NationalityServiceBean.java
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
import com.mg.merp.reference.NationalityServiceLocal;
import com.mg.merp.reference.model.Nationality;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Национальности"
 *
 * @author leonova
 * @version $Id: NationalityServiceBean.java,v 1.4 2006/10/20 05:38:59 leonova Exp $
 */
@Stateless(name = "merp/reference/NationalityService")
public class NationalityServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Nationality, Integer> implements NationalityServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Nationality entity) {
    context.addRule(new MandatoryStringAttribute(entity, "NName"));
  }


}
