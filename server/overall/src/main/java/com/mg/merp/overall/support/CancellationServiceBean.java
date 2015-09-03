/*
 * CancellationServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.overall.support;

import javax.ejb.Stateless;

import com.mg.merp.overall.CancellationServiceLocal;
import com.mg.merp.overall.model.Cancellation;

/**
 * Реализация бизнес - компонента "Погашение"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: CancellationServiceBean.java,v 1.1 2008/06/30 04:19:36 alikaev Exp $
 */
@Stateless(name="merp/overall/CancellationService")
public class CancellationServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Cancellation, Integer> implements CancellationServiceLocal {

}
