/*
 * ApplicationDictionaryServiceMBean.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.metadata.ApplicationDictionary;

/**
 * @author Oleg V. Safonov
 * @version $Id: ApplicationDictionaryServiceMBean.java,v 1.1 2006/01/24 14:09:07 safonov Exp $
 */
public interface ApplicationDictionaryServiceMBean extends
		ApplicationDictionary, ServiceMBean {

}
