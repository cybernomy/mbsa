/*
 * FeeSummaryHeadServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.personnelref.support.ConfigurationHelper;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.FeeSummaryModelServiceLocal;
import com.mg.merp.salary.FeeSummarySpecServiceLocal;
import com.mg.merp.salary.model.FeeSummaryHead;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Своды н/у по аналитике"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeSummaryHeadServiceBean.java,v 1.7 2007/08/27 06:19:40 sharapov Exp $
 */
@Stateless(name = "merp/salary/FeeSummaryHeadService") //$NON-NLS-1$
public class FeeSummaryHeadServiceBean extends GoodsDocumentServiceBean<FeeSummaryHead, Integer, FeeSummaryModelServiceLocal, FeeSummarySpecServiceLocal> implements FeeSummaryHeadServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, FeeSummaryHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "From")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "To")); //$NON-NLS-1$
    //context.addRule(new MandatoryAttribute(entity, "PayRoll"));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return FeeSummaryHeadServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
   */
  @Override
  protected Configuration doGetConfiguration() {
    return ConfigurationHelper.getDocumentConfiguration();
  }

}
