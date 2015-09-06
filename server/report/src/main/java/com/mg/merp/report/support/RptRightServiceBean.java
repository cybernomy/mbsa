/*
 * RptRightServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

package com.mg.merp.report.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.report.RptRightServiceLocal;
import com.mg.merp.report.model.RptRight;

import javax.ejb.Stateless;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: RptRightServiceBean.java,v 1.1 2007/04/11 06:46:06 poroxnenko Exp $
 */
@Stateless(name = "merp/report/RptRightService")
public class RptRightServiceBean extends AbstractPOJODataBusinessObjectServiceBean<RptRight, Integer> implements RptRightServiceLocal {

}
