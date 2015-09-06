/* CreateDocumentBasisOfLocator.java
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
 * @author Valentin A. Poroxnenko
 * @version $Id: CreateDocumentBasisOfLocator.java,v 1.1 2007/05/03 05:34:15 poroxnenko Exp $
 */
public class CreateDocumentBasisOfLocator {

  private static volatile CreateDocumentBasisOf instance = null;

  @SuppressWarnings("unchecked")
  public static <T1 extends DocHead, T2 extends DocHead, T3 extends DocHeadModel> CreateDocumentBasisOf<T1, T2, T3> locate() throws ApplicationException {
    if (instance == null)
      try {
        instance = (CreateDocumentBasisOf<T1, T2, T3>) ServerUtils.createMBeanProxy(CreateDocumentBasisOf.class, CreateDocumentBasisOf.SERVICE_NAME);
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }
}
