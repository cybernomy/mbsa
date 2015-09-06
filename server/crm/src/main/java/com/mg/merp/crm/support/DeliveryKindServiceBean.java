/*
 * DeliverykindServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.DeliveryKindServiceLocal;
import com.mg.merp.crm.model.DeliveryKind;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Виды доставки"
 *
 * @author leonova
 * @version $Id: DeliveryKindServiceBean.java,v 1.5 2006/10/12 05:57:42 leonova Exp $
 */
@Stateless(name = "merp/crm/DeliveryKindService")
public class DeliveryKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DeliveryKind, Integer> implements DeliveryKindServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, DeliveryKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
