/*
 * BankAccountTypeServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.BankAccountTypeServiceLocal;
import com.mg.merp.reference.model.BankAccountType;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Типы банковских счетов"
 *
 * @author leonova
 * @version $Id: BankAccountTypeServiceBean.java,v 1.3 2006/08/02 12:13:42 leonova Exp $
 */
@Stateless(name = "merp/reference/BankAccountTypeService")
public class BankAccountTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BankAccountType, Integer> implements BankAccountTypeServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
