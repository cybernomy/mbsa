/*
 * DataItemDocumentation.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.ApplicationException;

import java.util.Locale;

/**
 * @author Oleg V. Safonov
 * @version $Id: DataItemDocumentation.java,v 1.1 2006/01/24 13:45:28 safonov Exp $
 */
public interface DataItemDocumentation {
  public Status getStatus() throws ApplicationException;

  public String getShort() throws ApplicationException;

  public String getShort(Locale locale) throws ApplicationException;

  public String getDefenition() throws ApplicationException;

  public String getDefenition(Locale locale) throws ApplicationException;

  public enum Status {

    /**
     * Стандартный, документация существует или будет создана
     */
    DOCUMENTED,

    /**
     * Объект не используется в пользовательском интерфейсе, документация не создана и не требуется
     */
    NOT_USED_IN_SCREEN,

    /**
     * Краткий текст в достаточной степени описывает объект, документация не создана и не требуется
     */
    EXPLAINED_BY_SHORT,

    /**
     * Может быть использовано для описания элемента данных которые не полностью реализованы,
     * документация не существует
     */
    POSTPONED
  }
}
