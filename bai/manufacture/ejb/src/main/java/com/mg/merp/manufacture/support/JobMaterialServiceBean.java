/*
 * JobmaterialServiceBean.java
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.manufacture.JobMaterialServiceLocal;
import com.mg.merp.manufacture.generic.AbstractJobResource;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.mfreference.support.ConfigurationHelper;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Материалы в ЗНП"
 *
 * @author leonova
 * @version $Id: JobMaterialServiceBean.java,v 1.5 2007/07/30 10:27:27 safonov Exp $
 */
@Stateless(name = "merp/manufacture/JobMaterialService")
public class JobMaterialServiceBean extends AbstractJobResource<JobMaterial, Integer> implements JobMaterialServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(JobMaterial entity) {
    super.onInitialize(entity);
    entity.setCurrency(ConfigurationHelper.getConfiguration().getBaseCurrency());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, JobMaterial entity) {
    context.addRule(new MandatoryAttribute(entity, "Currency"));
    context.addRule(new MandatoryAttribute(entity, "Measure"));
    context.addRule(new MandatoryAttribute(entity, "Catalog"));
  }

}
