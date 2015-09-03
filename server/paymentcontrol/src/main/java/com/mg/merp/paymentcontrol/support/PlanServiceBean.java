/*
 * PlanServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import javax.ejb.Stateless;

import com.mg.merp.paymentcontrol.PlanServiceLocal;

/**
 * Реализация бизнес-компонента "Планирование платежей"
 * 
 * @author Oleg V. Safonov
 * @version $Id: PlanServiceBean.java,v 1.5 2008/01/29 13:31:45 safonov Exp $
 */
@Stateless(name="merp/paymentcontrol/PlanService")
public class PlanServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements PlanServiceLocal {

}
