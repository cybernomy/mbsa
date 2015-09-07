/*
 * CalcListFeeParamServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.salary.CalcListFeeParamServiceLocal;
import com.mg.merp.salary.model.CalcListFeeParam;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Параметры начислений удержаний в расчетных листках"
 *
 * @author leonova
 * @version $Id: CalcListFeeParamServiceBean.java,v 1.5 2008/02/01 10:18:12 safonov Exp $
 */
@Stateless(name = "merp/salary/CalcListFeeParamService")
public class CalcListFeeParamServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcListFeeParam, Integer> implements CalcListFeeParamServiceLocal {

}
