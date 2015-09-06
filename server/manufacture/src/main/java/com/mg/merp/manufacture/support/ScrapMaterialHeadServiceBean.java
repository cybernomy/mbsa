/*
 * ScrapMaterialHeadServiceBean.java
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
import com.mg.merp.manufacture.ScrapMaterialHeadServiceLocal;
import com.mg.merp.manufacture.ScrapMaterialModelServiceLocal;
import com.mg.merp.manufacture.ScrapMaterialSpecServiceLocal;
import com.mg.merp.manufacture.generic.ScrapDocumentHeadServiceBean;
import com.mg.merp.manufacture.model.ScrapDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Акты на списание потарь материалов"
 *
 * @author leonova
 * @version $Id: ScrapMaterialHeadServiceBean.java,v 1.6 2006/09/20 10:56:37 safonov Exp $
 */
@Stateless(name = "merp/manufacture/ScrapMaterialHeadService")
public class ScrapMaterialHeadServiceBean extends ScrapDocumentHeadServiceBean<ScrapDocumentHead, Integer, ScrapMaterialModelServiceLocal, ScrapMaterialSpecServiceLocal> implements ScrapMaterialHeadServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, ScrapDocumentHead entity) {
    super.onValidate(context, entity);
  }

  @Override
  protected int getDocSectionIdentifier() {
    return ScrapMaterialHeadServiceLocal.DOCSECTION;
  }

}
