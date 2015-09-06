/*
 * RiseScaleServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.RisePercentServiceLocal;
import com.mg.merp.personnelref.RiseScaleServiceLocal;
import com.mg.merp.personnelref.model.RisePercent;
import com.mg.merp.personnelref.model.RiseScale;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Шкалы надбавок"
 *
 * @author leonova
 * @version $Id: RiseScaleServiceBean.java,v 1.4 2007/11/08 06:53:56 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/RiseScaleService") //$NON-NLS-1$
public class RiseScaleServiceBean extends AbstractPOJODataBusinessObjectServiceBean<RiseScale, Integer> implements RiseScaleServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, RiseScale entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ScaleNumber")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(RiseScale entity) {
    entity.setScaleNumber(entity.hashCode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(RiseScale entity, RiseScale entityClone) {
    final String RISE_SCALE_ATTRIBUTE_NAME = "RiseScale"; //$NON-NLS-1$
    RisePercentServiceLocal risePercentService = (RisePercentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(RisePercentServiceLocal.SERVICE_NAME);
    AttributeMap initAttr = new LocalDataTransferObject();
    initAttr.put(RISE_SCALE_ATTRIBUTE_NAME, entityClone);
    for (RisePercent risePercent : risePercentService.findByCriteria(Restrictions.eq(RISE_SCALE_ATTRIBUTE_NAME, entity)))
      risePercentService.clone(risePercent, true, initAttr);
  }

}
