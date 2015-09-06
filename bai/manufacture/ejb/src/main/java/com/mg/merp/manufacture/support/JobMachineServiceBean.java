/*
 * JobmachineServiceBean.java
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
import com.mg.merp.manufacture.JobMachineServiceLocal;
import com.mg.merp.manufacture.generic.AbstractJobResource;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.mfreference.support.ConfigurationHelper;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Оборудование в ЗНП"
 *
 * @author leonova
 * @version $Id: JobMachineServiceBean.java,v 1.7 2009/03/05 12:32:15 safonov Exp $
 */
@Stateless(name = "merp/manufacture/JobMachineService")
public class JobMachineServiceBean extends AbstractJobResource<JobMachine, Integer> implements JobMachineServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.generic.AbstractJobResource#onInitialize(com.mg.merp.manufacture.model.JobRouteResource)
   */
  @Override
  protected void onInitialize(JobMachine entity) {
    super.onInitialize(entity);
    entity.setRunTimeMchUm(ConfigurationHelper.getConfiguration().getMainTimeUM());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, JobMachine entity) {
    context.addRule(new MandatoryAttribute(entity, "RunTimeMchUm"));
    context.addRule(new MandatoryAttribute(entity, "MchRateCurrency"));
    context.addRule(new MandatoryAttribute(entity, "MchOhRateCurrency"));
  }

}
