/*
 * HelpSystem.java
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
package com.mg.framework.api.help;

/**
 * Система помощи
 *
 * @author Oleg V. Safonov
 * @version $Id: HelpSystem.java,v 1.1 2006/11/11 09:44:57 safonov Exp $
 */
public interface HelpSystem {

  /**
   * имя сервиса системы помощи
   */
  String SERVICE_NAME = "merp:service=HelpSystemService";

  /**
   * показать контекстную помощь
   *
   * @param helpTopic тематический раздел помощи
   */
  void showContextHelp(String helpTopic);

}
