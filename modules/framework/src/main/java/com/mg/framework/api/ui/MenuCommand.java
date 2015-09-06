/*
 * MenuCommand.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.Map;

/**
 * Команда меню, используется для описания команды выполняемой из меню системы
 *
 * @author Oleg V. Safonov
 * @version $Id: MenuCommand.java,v 1.3 2008/05/14 09:36:24 safonov Exp $
 */
public interface MenuCommand extends Serializable {

  /**
   * инициализация команды
   *
   * @param params именованный список параметров, каждая реализация интерпретирует данный список
   */
  void init(Map<String, String> params);

  /**
   * выполнить действие
   *
   * @throws Exception в случае любой ИС
   */
  void execute() throws Exception;

  /**
   * проверка доступности команды текущему принципалу
   *
   * @return <code>true</code> если на выполнение команды у текущего принципала имеются права
   */
  boolean isPermitted();
}
