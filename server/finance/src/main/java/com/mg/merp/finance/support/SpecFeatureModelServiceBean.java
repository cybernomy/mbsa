/*
 * SpecFeatureModelServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.finance.support;

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.finance.SpecFeatureModelServiceLocal;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.SpecificationModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Признаки образцов финансовых операций"
 *
 * @author leonova
 * @version $Id: SpecFeatureModelServiceBean.java,v 1.5 2007/11/27 11:55:33 alikaev Exp $
 */
@Stateless(name = "merp/finance/SpecFeatureModelService")
public class SpecFeatureModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SpecificationModel, Integer> implements SpecFeatureModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(SpecificationModel entity) {
    OperationModel om = entity.getFinOper();
    if (om != null)
      entity.setPlanned(om.getPlanned());
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, final SpecificationModel entity) {
    context.addRule(new Rule() {

      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.FEATURE_NOT_NULL);
      }

      public void validate(ValidationContext context) {
        if (entity.getSrcAcc() == null)
          context.getStatus().error(this);
      }

    });
    context.addRule(new Rule() {

      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.PARENT_NOT_NULL);
      }

      public void validate(ValidationContext context) {
        if (entity.getParent() == null)
          context.getStatus().error(this);
      }
    });
  }

}
