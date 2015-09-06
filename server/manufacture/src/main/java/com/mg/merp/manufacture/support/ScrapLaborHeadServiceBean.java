/*
 * ScrapLaborHeadServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.merp.manufacture.ScrapLaborHeadServiceLocal;
import com.mg.merp.manufacture.ScrapLaborModelServiceLocal;
import com.mg.merp.manufacture.ScrapLaborSpecServiceLocal;
import com.mg.merp.manufacture.generic.ScrapDocumentHeadServiceBean;
import com.mg.merp.manufacture.model.ScrapDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Акты на списание потерь времени, отработанного РС"
 *
 * @author leonova
 * @version $Id: ScrapLaborHeadServiceBean.java,v 1.7 2007/02/06 17:15:40 safonov Exp $
 */
@Stateless(name = "merp/manufacture/ScrapLaborHeadService")
public class ScrapLaborHeadServiceBean extends ScrapDocumentHeadServiceBean<ScrapDocumentHead, Integer, ScrapLaborModelServiceLocal, ScrapLaborSpecServiceLocal> implements ScrapLaborHeadServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, ScrapDocumentHead entity) {
    super.onValidate(context, entity);
  }

  @Override
  protected int getDocSectionIdentifier() {
    return ScrapLaborHeadServiceLocal.DOCSECTION;
  }

}
