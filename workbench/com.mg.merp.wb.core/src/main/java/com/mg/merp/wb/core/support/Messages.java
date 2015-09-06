/* Messages.java
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
package com.mg.merp.wb.core.support;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Вспомогательный класс для предоставления локализованых сообщений
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: Messages.java,v 1.5 2007/07/10 10:48:55 poroxnenko Exp $
 */
public class Messages {

  private Messages() {
  }

  /**
   * Получить локализованную строку
   *
   * @param resourceBundle строковой ресурс
   * @param key            ключ
   * @return строка
   */
  public static String getString(ResourceBundle resourceBundle, String key) {
    try {
      return resourceBundle.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }

  /**
   * Получить форматированную локализованую строку
   *
   * @param resourceBundle строковой ресурс
   * @param key            ключ
   * @param arguments      аргументы для вставки
   * @return строка
   */
  public static String getFormattedString(ResourceBundle resourceBundle, String key, Object[] arguments) {
    return String.format(getString(resourceBundle, key), arguments);
  }
}
