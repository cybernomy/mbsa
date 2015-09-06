/*
 * JobrouteServiceBean.java
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
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.manufacture.JobRouteServiceLocal;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.model.ManufactureConfig;
import com.mg.merp.mfreference.support.ConfigurationHelper;
import com.mg.merp.mfreference.support.MfUtils;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Операции ЗНП"
 *
 * @author leonova
 * @version $Id: JobRouteServiceBean.java,v 1.8 2009/03/05 12:32:15 safonov Exp $
 */
@Stateless(name = "merp/manufacture/JobRouteService")
public class JobRouteServiceBean extends AbstractPOJODataBusinessObjectServiceBean<JobRoute, Integer> implements JobRouteServiceLocal {

  protected void doAdjust(JobRoute entity) {
    MfUtils.adjustEffectiveDate(entity);
  }

  /* (non-Javadoc)
  * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
  */
  @Override
  protected void onInitialize(JobRoute entity) {
    ManufactureConfig config = ConfigurationHelper.getConfiguration();
    entity.setMoveTimeUM(config.getMainTimeUM());
    entity.setSetupTimeUM(config.getMainTimeUM());
    entity.setRunTimeUM(config.getMainTimeUM());
    entity.setSchedTimeUM(config.getMainTimeUM());
    entity.setSchedOffSetTimeUM(config.getMainTimeUM());
    entity.setQueueTimeUM(config.getMainTimeUM());

    entity.setStdCostDetail(MfUtils.createCostDetail());
    entity.setActCostDetail(MfUtils.createCostDetail());
  }

  @Override
  protected void onCreate(JobRoute entity) {
    doAdjust(entity);
  }

  @Override
  protected void onStore(JobRoute entity) {
    doAdjust(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, JobRoute entity) {
    context.addRule(new MandatoryAttribute(entity, "SetupTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "MoveTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "SchedOffSetTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "SchedTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "RunTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "QueueTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "OperNum"));
    context.addRule(new MandatoryAttribute(entity, "WorkCenter"));
  }

  /**
   * @ejb.interface-method view-type = "local"
   *
   * @param routeId
   * @return
   * @throws ApplicationException
   */
// 	public JobRouteMaterialItem[] materialList(int routeId) throws ApplicationException {
// 		return null;//((JobRouteDomainImpl) getDomain()).materialList(routeId);
// 	}

  /**
   * @ejb.interface-method view-type = "local"
   *
   * @param routeId
   * @return
   * @throws ApplicationException
   */
// 	public JobRouteLaborItem[] laborList(int routeId) throws ApplicationException {
// 		return null;//((JobRouteDomainImpl) getDomain()).laborList(routeId);
// 	}

  /**
   * @ejb.interface-method view-type = "local"
   *
   * @param routeId
   * @return
   * @throws ApplicationException
   */
// 	public JobRouteMachineItem[] machineList(int routeId) throws ApplicationException {
// 		return null;//((JobRouteDomainImpl) getDomain()).machineList(routeId);
// 	}
}
