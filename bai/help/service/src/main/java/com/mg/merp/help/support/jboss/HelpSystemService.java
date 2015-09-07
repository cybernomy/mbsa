/*
 * HelpSystemService.java
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
import com.mg.merp.help.support.HelpSystemImpl;

import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса системы помощи для сервера JBoss
 *
 * @author Oleg V. Safonov
 * @version $Id: HelpSystemService.java,v 1.1 2006/11/14 15:29:39 safonov Exp $
 */
public class HelpSystemService extends ServiceMBeanSupport implements
    HelpSystemServiceMBean {
  private HelpSystem delegate = new HelpSystemImpl();

  /* (non-Javadoc)
   * @see com.mg.framework.api.help.HelpSystem#showContextHelp(java.lang.String)
   */
  public void showContextHelp(String helpTopic) {
    this.delegate.showContextHelp(helpTopic);
  }

}
