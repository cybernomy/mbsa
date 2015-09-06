/*
 * CoefficientServiceBean.java
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

package com.mg.merp.discount.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.discount.CoefficientServiceLocal;
import com.mg.merp.discount.model.Coefficient;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Коэффициенты дисконтной карты"
 *
 * @author leonova
 * @version $Id: CoefficientServiceBean.java,v 1.4 2007/09/07 12:02:18 safonov Exp $
 */
@Stateless(name = "merp/discount/CoefficientService")
public class CoefficientServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Coefficient, Integer> implements CoefficientServiceLocal {

}
