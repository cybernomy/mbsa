/*
 * ActivitykindServiceBean.java
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
import com.mg.merp.crm.ActivityKindServiceLocal;
import com.mg.merp.crm.model.ActivityKind;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Виды деятельности"
 *
 * @author leonova
 * @version $Id: ActivityKindServiceBean.java,v 1.5 2006/10/12 05:42:49 leonova Exp $
 */
@Stateless(name = "merp/crm/ActivityKindService")
public class ActivityKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ActivityKind, Integer> implements ActivityKindServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, ActivityKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
