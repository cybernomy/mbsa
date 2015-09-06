/*
 * MeasureServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.MeasureServiceLocal;
import com.mg.merp.reference.model.Measure;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Единицы измерения" (Unit of measure)
 *
 * @author Oleg V. Safonov
 * @version $Id: MeasureServiceBean.java,v 1.4 2006/10/19 04:47:37 leonova Exp $
 */
@Stateless(name = "merp/reference/MeasureService")
public class MeasureServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Measure, Integer> implements MeasureServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Measure entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new MandatoryStringAttribute(entity, "FullName"));
    context.addRule(new MandatoryAttribute(entity, "UniversalCode"));
  }

  private void adjustMeasure(Measure entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Measure entity) {
    adjustMeasure(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Measure entity) {
    adjustMeasure(entity);
  }

}
