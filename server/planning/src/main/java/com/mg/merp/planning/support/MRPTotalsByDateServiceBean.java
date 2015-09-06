/*
 * MRPTotalsByDateServiceBean.java
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

package com.mg.merp.planning.support;

import com.mg.merp.planning.MRPTotalsByDateServiceLocal;
import com.mg.merp.planning.model.MrpTotalsByDate;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Результаты расчета ППМ по дням"
 *
 * @author Oleg V. Safonov
 * @version $Id: MRPTotalsByDateServiceBean.java,v 1.3 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name = "merp/planning/MRPTotalsByDateService")
public class MRPTotalsByDateServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<MrpTotalsByDate, Integer> implements MRPTotalsByDateServiceLocal {

}
