/*
 * UserServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.crm.UserServiceLocal;
import com.mg.merp.crm.model.User;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Пользователи CRM"
 *
 * @author leonova
 * @version $Id: UserServiceBean.java,v 1.4 2006/09/06 05:24:25 leonova Exp $
 */
@Stateless(name = "merp/crm/UserService")
public class UserServiceBean extends AbstractPOJODataBusinessObjectServiceBean<User, Integer> implements UserServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, User entity) {
    context.addRule(new MandatoryAttribute(entity, "Person"));
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public AttributeMap getCurrent() throws ApplicationException {
    return null;//((UserDomainImpl) getDomain()).getCurrent();
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public AttributeMap getSecurityUser(int key) throws ApplicationException {
    return null;//((UserDomainImpl) getDomain()).getSecurityUser(key);
  }
}
