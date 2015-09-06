/*
 * PhoneKindServiceBean.java
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
import com.mg.merp.reference.PhoneKindServiceLocal;
import com.mg.merp.reference.model.PhoneKind;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Типы телефонных номеров"
 *
 * @author leonova
 * @version $Id: PhoneKindServiceBean.java,v 1.4 2006/10/19 04:21:56 leonova Exp $
 */
@Stateless(name = "merp/reference/PhoneKindService")
public class PhoneKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PhoneKind, Integer> implements PhoneKindServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, PhoneKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "KName"));
  }

}
