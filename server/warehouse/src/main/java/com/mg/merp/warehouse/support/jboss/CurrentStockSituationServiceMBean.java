/*
 * CurrentStockSituationServiceMBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.jboss;

import com.mg.merp.reference.CurrentStockSituation;

import org.jboss.system.ServiceMBean;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituationServiceMBean.java,v 1.2 2007/05/03 12:43:11 poroxnenko Exp $
 */
public interface CurrentStockSituationServiceMBean extends CurrentStockSituation,
    ServiceMBean {

}
