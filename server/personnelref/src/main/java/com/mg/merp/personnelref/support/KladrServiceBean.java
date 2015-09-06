/*
 * KladrServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.KladrServiceLocal;
import com.mg.merp.personnelref.model.Kladr;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Классификатор адресов (КЛАДР)"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: KladrServiceBean.java,v 1.3 2007/07/16 13:18:14 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/KladrService") //$NON-NLS-1$
public class KladrServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Kladr, Integer> implements KladrServiceLocal {

}
