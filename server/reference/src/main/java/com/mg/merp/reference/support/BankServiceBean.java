/*
 * BankServiceBean.java
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
import com.mg.merp.reference.BankServiceLocal;
import com.mg.merp.reference.model.Bank;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Банки"
 *
 * @author leonova
 * @version $Id: BankServiceBean.java,v 1.3 2006/08/02 12:13:42 leonova Exp $
 */
@Stateless(name = "merp/reference/BankService")
public class BankServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Bank, Integer> implements BankServiceLocal {

}
