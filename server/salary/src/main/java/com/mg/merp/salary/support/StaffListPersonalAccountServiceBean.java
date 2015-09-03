/*
 * StaffListPersonalAccountServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.salary.StaffListPersonalAccountServiceLocal;
import com.mg.merp.personnelref.model.PersonalAccount;

/**
 * Бизнес-компонент "Лицевые счета сотрудников" 
 * 
 * @author leonova
 * @version $Id: StaffListPersonalAccountServiceBean.java,v 1.5 2006/11/02 16:24:34 safonov Exp $
 */
@Stateless(name="merp/salary/StaffListPersonalAccountService")
public class StaffListPersonalAccountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonalAccount, Integer> implements StaffListPersonalAccountServiceLocal {

 
}
