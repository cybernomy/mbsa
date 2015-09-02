/*
 * JobrouteresourceServiceBean.java
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

package com.mg.merp.manufacture.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.manufacture.JobRouteResourceServiceLocal;
import com.mg.merp.manufacture.model.JobRouteResource;

/**
 * ������-��������� "������� ���" 
 * 
 * @author leonova
 * @version $Id: JobRouteResourceServiceBean.java,v 1.2 2006/09/09 09:36:56 leonova Exp $
 */
@Stateless(name="merp/manufacture/JobRouteResourceService")
public class JobRouteResourceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<JobRouteResource, Integer> implements JobRouteResourceServiceLocal{



}
