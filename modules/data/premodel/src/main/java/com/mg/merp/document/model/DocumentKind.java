/*
 * DocumentKind.java
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
package com.mg.merp.document.model;

/**
 * Вид документа
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentKind.java,v 1.1 2006/08/31 09:07:19 safonov Exp $
 */
public enum DocumentKind {
  /**
   * Документ
   */
  DOCUMENT,

  /**
   * Документ подтверждение
   */
  CONFIRMATION,

  /**
   * Документ основание
   */
  BASE,

  /**
   * Контракт
   */
  CONTRACT
}
