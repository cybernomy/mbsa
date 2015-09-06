/*
 * FamilyStatusKindServiceBean.java
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
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.FamilyStatusKindServiceLocal;
import com.mg.merp.reference.model.FamilyStatusKind;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Типы семейного положения"
 *
 * @author leonova
 * @version $Id: FamilyStatusKindServiceBean.java,v 1.4 2006/10/20 05:44:45 leonova Exp $
 */
@Stateless(name = "merp/reference/FamilyStatusKindService")
public class FamilyStatusKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FamilyStatusKind, Integer> implements FamilyStatusKindServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, FamilyStatusKind entity) {
    context.addRule(new MandatoryStringAttribute(entity, "KCode"));
  }

}
