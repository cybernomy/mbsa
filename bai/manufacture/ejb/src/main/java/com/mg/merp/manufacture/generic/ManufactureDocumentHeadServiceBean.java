/*
 * ManufactureDocumentHeadServiceBean.java
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
package com.mg.merp.manufacture.generic;

import com.mg.merp.document.Configuration;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.mfreference.support.ConfigurationHelper;

import java.io.Serializable;

/**
 * Базовый класс производственных документов
 *
 * @author Oleg V. Safonov
 * @version $Id: ManufactureDocumentHeadServiceBean.java,v 1.4 2007/03/26 10:48:28 safonov Exp $
 */
public abstract class ManufactureDocumentHeadServiceBean<T extends com.mg.merp.document.model.DocHead, ID extends Serializable, P extends DocumentPattern, S extends GoodsDocumentSpecification> extends
    GoodsDocumentServiceBean<T, ID, P, S> {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
   */
  @Override
  protected Configuration doGetConfiguration() {
    return ConfigurationHelper.getDocumentConfiguration();
  }

}
