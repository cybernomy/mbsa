/*
 * CreateDocOnComponentsLocator.java
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
package com.mg.merp.document;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponentsLocator.java,v 1.1 2007/10/23 13:46:50 alikaev Exp $
 */
public class CreateDocOnComponentsLocator {

  private static volatile CreateDocOnComponents instance = null;

  @SuppressWarnings("unchecked")
  public static <S extends DocHead, D extends DocHead, P extends DocHeadModel> CreateDocOnComponents<S, D, P> locate() throws ApplicationException {
    if (instance == null)
      try {
        instance = (CreateDocOnComponents<S, D, P>) ServerUtils.createMBeanProxy(CreateDocOnComponents.class, CreateDocOnComponents.SERVICE_NAME);
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }

}
