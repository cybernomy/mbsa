/*
 * AnlPlanServiceBean.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AnlPlanServiceLocal;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.AnlStdForm;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Аналитические счета"
 *
 * @author leonova
 * @version $Id: AnlPlanServiceBean.java,v 1.5 2009/02/25 15:00:45 safonov Exp $
 */
@Stateless(name = "merp/account/AnlPlanService")
public class AnlPlanServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AnlPlan, Integer> implements AnlPlanServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(AnlPlan entity) {
    entity.setStdForm(AnlStdForm.THROUGH);
  }

  private void adjustAnlPlan(AnlPlan entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(AnlPlan entity) {
    adjustAnlPlan(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(AnlPlan entity) {
    adjustAnlPlan(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final AnlPlan entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "AnlName"));
    context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") {
      @Override
      protected void doValidate(ValidationContext context) {
        if (OrmTemplate.getInstance().findUniqueByCriteria(AnlPlan.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode()))
            , Restrictions.ne("Id", entity.getId())
            , Restrictions.eq("AnlLevel", entity.getAnlLevel())
            , Restrictions.eq("AccPlan", entity.getAccPlan())) != null)
          context.getStatus().error(this);
      }
    });
  }

}
