/*
 * CreateDocOnComponentsServiceMBean.java
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
package com.mg.merp.document.support.jboss;

import com.mg.merp.document.CreateDocOnComponents;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

import org.jboss.system.ServiceMBean;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponentsServiceMBean.java,v 1.1 2007/10/23 13:55:29 alikaev Exp $
 */
public interface CreateDocOnComponentsServiceMBean<S extends DocHead, D extends DocHead, P extends DocHeadModel> extends CreateDocOnComponents<S, D, P>, ServiceMBean {

}
