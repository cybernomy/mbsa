/*
 * BOMRouteServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.mfreference.BOMRouteServiceLocal;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.ManufactureConfig;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Операции"
 *
 * @author leonova
 * @version $Id: BOMRouteServiceBean.java,v 1.8 2009/03/05 12:36:48 safonov Exp $
 */
@Stateless(name = "merp/mfreference/BOMRouteService")
public class BOMRouteServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BomRoute, Integer> implements BOMRouteServiceLocal {

  protected void doAdjust(BomRoute entity) {
    MfUtils.adjustEffectiveDate(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(BomRoute entity) {
    ManufactureConfig config = ConfigurationHelper.getConfiguration();
    entity.setMoveTimeUM(config.getMainTimeUM());
    entity.setSetupTimeUM(config.getMainTimeUM());
    entity.setRunTimeUM(config.getMainTimeUM());
    entity.setSchedTimeUM(config.getMainTimeUM());
    entity.setSchedOffSetTimeUM(config.getMainTimeUM());
    entity.setQueueTimeUM(config.getMainTimeUM());
    entity.setStandartCostDetail(MfUtils.createCostDetail());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, BomRoute entity) {
    context.addRule(new MandatoryAttribute(entity, "OperNum"));
    context.addRule(new MandatoryAttribute(entity, "QueueTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "SetupTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "SchedOffSetTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "MoveTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "SchedTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "RunTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "WorkCenter"));
  }

  @Override
  protected void onCreate(BomRoute entity) {
    doAdjust(entity);
  }

  @Override
  protected void onStore(BomRoute entity) {
    doAdjust(entity);
  }

}
