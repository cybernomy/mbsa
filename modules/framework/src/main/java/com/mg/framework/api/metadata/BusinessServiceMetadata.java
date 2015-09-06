/*
 * BusinessServiceMetadata.java
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
package com.mg.framework.api.metadata;

/**
 * Метаданные бизнес-компонента
 *
 * @author Oleg V. Safonov
 * @version $Id: BusinessServiceMetadata.java,v 1.2 2006/07/27 15:35:14 safonov Exp $
 */
public interface BusinessServiceMetadata {

  /**
   * получить идентификатор сервиса из хранилища
   *
   * @return идентификатор
   */
  int getIdentificator();

  /**
   * получить имя бизнес-компонента
   *
   * @return имя
   */
  String getName();
}
