/*
 * CellServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.CellServiceLocal;
import com.mg.merp.mfreference.model.Cell;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Производственные линии"
 *
 * @author leonova
 * @version $Id: CellServiceBean.java,v 1.4 2006/10/23 10:12:37 leonova Exp $
 */
@Stateless(name = "merp/mfreference/CellService")
public class CellServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Cell, Integer> implements CellServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Cell entity) {
    context.addRule(new MandatoryStringAttribute(entity, "CellName"));
    context.addRule(new MandatoryAttribute(entity, "WeekCal"));
  }


}
