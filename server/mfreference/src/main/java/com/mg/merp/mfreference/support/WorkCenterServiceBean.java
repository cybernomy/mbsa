/*
 * WorkcenterServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.WorkCenterServiceLocal;
import com.mg.merp.mfreference.model.WorkCenter;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Рабочие центры"
 *
 * @author leonova
 * @version $Id: WorkCenterServiceBean.java,v 1.4 2006/09/28 13:04:06 leonova Exp $
 */
@Stateless(name = "merp/mfreference/WorkCenterService")
public class WorkCenterServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WorkCenter, Integer> implements WorkCenterServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final WorkCenter entity) {
    context.addRule(new MandatoryStringAttribute(entity, "WcCode"));
    context.addRule(new MandatoryStringAttribute(entity, "WcName"));
    context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "WcCode") {
      @Override
      protected void doValidate(ValidationContext context) {
        if (OrmTemplate.getInstance().findUniqueByCriteria(WorkCenter.class, Restrictions.eq("WcCode", entity.getWcCode()), Restrictions.ne("Id", entity.getId())) != null)
          context.getStatus().error(this);
      }
    });

  }


}
