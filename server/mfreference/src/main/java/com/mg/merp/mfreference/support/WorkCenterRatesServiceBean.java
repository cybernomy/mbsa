/*
 * WorkcenterratesServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.WorkCenterRatesServiceLocal;
import com.mg.merp.mfreference.model.WorkCenterRates;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Нормы стоимости"
 *
 * @author leonova
 * @version $Id: WorkCenterRatesServiceBean.java,v 1.3 2006/08/24 12:31:25 leonova Exp $
 */
@Stateless(name = "merp/mfreference/WorkCenterRatesService")
public class WorkCenterRatesServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WorkCenterRates, Integer> implements WorkCenterRatesServiceLocal {


}
