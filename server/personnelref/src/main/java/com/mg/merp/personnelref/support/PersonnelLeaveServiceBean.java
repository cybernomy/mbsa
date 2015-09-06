/*
 * PersonnelLeaveServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.PersonnelLeaveServiceLocal;
import com.mg.merp.personnelref.model.PersonnelLeave;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Отпуска"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelLeaveServiceBean.java,v 1.4 2007/01/16 07:16:18 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelLeaveService") //$NON-NLS-1$
public class PersonnelLeaveServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelLeave, Integer> implements PersonnelLeaveServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, PersonnelLeave entity) {
    context.addRule(new MandatoryStringAttribute(entity, "LeaveKind"));  //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "BeginDate"));  //$NON-NLS-1$
  }

}
