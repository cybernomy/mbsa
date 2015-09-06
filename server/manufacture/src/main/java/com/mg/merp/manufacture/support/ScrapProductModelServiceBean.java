/*
 * ScrapProductModelServiceBean.java
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

package com.mg.merp.manufacture.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.manufacture.ScrapProductHeadServiceLocal;
import com.mg.merp.manufacture.ScrapProductModelServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы актов на списание потерь с операции"
 *
 * @author leonova
 * @version $Id: ScrapProductModelServiceBean.java,v 1.4 2007/09/10 12:22:50 alikaev Exp $
 */
@Stateless(name = "merp/manufacture/ScrapProductModelService")
public class ScrapProductModelServiceBean extends DocumentModelServiceBean<ScrapDocumentModel, Integer> implements ScrapProductModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return ScrapProductHeadServiceLocal.DOCSECTION;
  }

}
