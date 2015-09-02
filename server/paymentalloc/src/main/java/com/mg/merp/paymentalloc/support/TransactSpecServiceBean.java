/*
 * TransactSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.paymentalloc.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.paymentalloc.TransactSpecServiceLocal;
import com.mg.merp.paymentalloc.model.TransactSpec;

/**
 * Реализация бизнес-компонента "Спецификации связанных документов" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TransactSpecServiceBean.java,v 1.4 2007/07/31 06:21:17 sharapov Exp $
 */
@Stateless(name="merp/paymentalloc/TransactSpecService") //$NON-NLS-1$
@PermitAll
public class TransactSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TransactSpec, Integer> implements TransactSpecServiceLocal {

}
