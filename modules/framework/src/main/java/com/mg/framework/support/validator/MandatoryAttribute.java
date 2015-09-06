/*
 * MandatoryAttribute.java
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
package com.mg.framework.support.validator;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;

/**
 * Правило гарантирующее, что атрибут сущности не будет <code>null</code>
 *
 * @author Oleg V. Safonov
 * @version $Id: MandatoryAttribute.java,v 1.1 2006/08/14 14:08:44 safonov Exp $
 */
public class MandatoryAttribute extends EntityBeanRule {

  /**
   * создает правило
   *
   * @param entity       объект-сущность контроля
   * @param propertyName наименование атрибута контроля
   */
  public MandatoryAttribute(PersistentObject entity, String propertyName) {
    super(Messages.getInstance().getMessage(Messages.MANDATORY_VALIDATOR), entity, propertyName);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.validator.Rule#doValidate(com.mg.framework.api.validator.ValidationContext)
   */
  @Override
  protected void doValidate(ValidationContext context) {
    Object toValidate = toValidate();
    //проверка на null
    if (toValidate == null)
      context.getStatus().error(this);
  }

}
