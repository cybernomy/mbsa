/*
 * ZipCodeServiceBean.java
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

import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.ZipCodeServiceLocal;
import com.mg.merp.reference.model.ZipCode;

import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Почтовые индексы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ZipCodeServiceBean.java,v 1.6 2008/09/23 09:17:19 safonov Exp $
 */
@Stateless(name = "merp/reference/ZipCodeService") //$NON-NLS-1$
public class ZipCodeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ZipCode, Integer> implements ZipCodeServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.ZipCodeServiceLocal#findByCode(java.lang.String)
   */
  public ZipCode findByCode(String code) {
    List<ZipCode> codes = findByCriteria(Restrictions.eq("Code", code));
    return codes.size() == 0 ? null : codes.get(0);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, ZipCode entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
  }
}
