/*
 * BranchServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.BranchServiceLocal;
import com.mg.merp.crm.model.Branch;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Отрасли"
 *
 * @author leonova
 * @version $Id: BranchServiceBean.java,v 1.4 2006/10/12 05:39:42 leonova Exp $
 */
@Stateless(name = "merp/crm/BranchService")
public class BranchServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Branch, Integer> implements BranchServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, Branch entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
