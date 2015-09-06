/*
 * DataItemKind.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Тип элемента данных
 *
 * @author Oleg V. Safonov
 * @version $Id: DataItemKind.java,v 1.2 2008/03/03 13:11:02 safonov Exp $
 */
@DataItemName("Core.DataItemKind")
public enum DataItemKind {
  /**
   * Используется домен
   */
  DOMAIN,

  /**
   * Ссылка на элемент данных
   */
  REFERENCE
}