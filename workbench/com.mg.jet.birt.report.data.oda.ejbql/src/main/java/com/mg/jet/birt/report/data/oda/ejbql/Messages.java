/**
 * Messages.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.jet.birt.report.data.oda.ejbql;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Сообщения
 *
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.1 2007/10/29 08:33:24 safonov Exp $
 */
public class Messages {
  private static final String BUNDLE_NAME = "com.mg.jet.birt.report.data.oda.ejbql.messages";//$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  private Messages() {
  }

  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}