/* EntityTransformatorLocator.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.EntityTransformer;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор сервиса трансформации сущностей
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformerLocator.java,v 1.2 2007/09/21 09:50:49 safonov Exp $
 */
public class EntityTransformerLocator {

  private static volatile EntityTransformer instance = null;

  @SuppressWarnings("unchecked")
  public static EntityTransformer locate() throws ApplicationException {
    if (instance == null)
      try {
        instance = (EntityTransformer) ServerUtils.createMBeanProxy(EntityTransformer.class, EntityTransformer.SERVICE_NAME);
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }

}
