/*
 * HelpSystemServiceMBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.help.support.jboss;

import com.mg.framework.api.help.HelpSystem;

import org.jboss.system.ServiceMBean;

/**
 * Сервис системы помощи для сервера JBoss
 *
 * @author Oleg V. Safonov
 * @version $Id: HelpSystemServiceMBean.java,v 1.1 2006/11/14 15:29:39 safonov Exp $
 */
public interface HelpSystemServiceMBean extends HelpSystem, ServiceMBean {

}
