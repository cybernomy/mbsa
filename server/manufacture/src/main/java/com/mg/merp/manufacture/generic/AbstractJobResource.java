/*
 * AbstractJobResource.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture.generic;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.manufacture.model.JobRouteResource;
import com.mg.merp.mfreference.support.MfUtils;

import java.io.Serializable;

/**
 * Базовый класс сервисов ресурсов операций ЗНП
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractJobResource.java,v 1.2 2007/08/21 15:27:26 safonov Exp $
 */
public abstract class AbstractJobResource<T extends JobRouteResource, ID extends Serializable> extends
    AbstractPOJODataBusinessObjectServiceBean<T, ID> {

  protected void doAdjust(T entity) {
    MfUtils.adjustEffectiveDate(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(T entity) {
    entity.setStdCostDetail(MfUtils.createCostDetail());
    entity.setActCostDetail(MfUtils.createCostDetail());
  }

  @Override
  protected void onCreate(T entity) {
    doAdjust(entity);
  }

  @Override
  protected void onStore(T entity) {
    doAdjust(entity);
  }

}
