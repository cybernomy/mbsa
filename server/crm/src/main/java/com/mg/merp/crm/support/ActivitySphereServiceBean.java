/*
 * ActivitysphereServiceBean.java
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
import com.mg.merp.crm.ActivitySphereServiceLocal;
import com.mg.merp.crm.model.ActivitySphere;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Сферы деятельности"
 *
 * @author leonova
 * @version $Id: ActivitySphereServiceBean.java,v 1.5 2006/10/12 05:41:44 leonova Exp $
 */
@Stateless(name = "merp/crm/ActivitySphereService")
public class ActivitySphereServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ActivitySphere, Integer> implements ActivitySphereServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, ActivitySphere entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
