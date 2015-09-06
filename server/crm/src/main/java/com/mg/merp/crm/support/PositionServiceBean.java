/*
 * Created on 12.01.2005
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
 */
package com.mg.merp.crm.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.PositionServiceLocal;
import com.mg.merp.crm.model.Position;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Должности"
 *
 * @author leonova
 * @version $Id: PositionServiceBean.java,v 1.4 2006/10/12 05:57:42 leonova Exp $
 */
@Stateless(name = "merp/crm/PositionService")
public class PositionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Position, Integer> implements PositionServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Position entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name"));
  }

}
