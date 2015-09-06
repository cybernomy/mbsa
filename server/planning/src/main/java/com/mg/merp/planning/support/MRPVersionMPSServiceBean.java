/*
 * MRPVersionMPSServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.merp.planning.MRPVersionMPSServiceLocal;
import com.mg.merp.planning.model.MrpVersionMps;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Версии ОПП в ППМ"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: MRPVersionMPSServiceBean.java,v 1.6 2008/05/29 07:32:14 alikaev Exp $
 */
@Stateless(name = "merp/planning/MRPVersionMPSService")
@PermitAll
public class MRPVersionMPSServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MrpVersionMps, Integer> implements MRPVersionMPSServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, MrpVersionMps entity) {
    context.addRule(new MandatoryAttribute(entity, "Mps")); //$NON-NLS-1$
  }

}
