/*
 * RCCPHeaderServiceBean.java
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

package com.mg.merp.planning.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.planning.RCCPHeaderServiceLocal;
import com.mg.merp.planning.model.RccpHeader;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "УППМ"
 *
 * @author leonova
 * @version $Id: RCCPHeaderServiceBean.java,v 1.3 2006/08/25 10:18:33 leonova Exp $
 */
@Stateless(name = "merp/planning/RCCPHeaderService")
public class RCCPHeaderServiceBean extends AbstractPOJODataBusinessObjectServiceBean<RccpHeader, Integer> implements RCCPHeaderServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, RccpHeader entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
    context.addRule(new MandatoryAttribute(entity, "Mps"));
    context.addRule(new MandatoryAttribute(entity, "WeekCal"));
  }


}
