/*
 * ScrapLaborModelServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.manufacture.ScrapLaborHeadServiceLocal;
import com.mg.merp.manufacture.ScrapLaborModelServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы актов на списание потерь времени, отработанного РС"
 *
 * @author leonova
 * @version $Id: ScrapLaborModelServiceBean.java,v 1.3 2006/09/12 11:08:13 leonova Exp $
 */
@Stateless(name = "merp/manufacture/ScrapLaborModelService")
public class ScrapLaborModelServiceBean extends DocumentModelServiceBean<ScrapDocumentModel, Integer> implements ScrapLaborModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return ScrapLaborHeadServiceLocal.FOLDER_PART;
  }

}
