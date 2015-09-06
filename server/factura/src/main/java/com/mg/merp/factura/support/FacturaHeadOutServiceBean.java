/*
 * FacturaHeadOutServiceBean.java
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

package com.mg.merp.factura.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.support.ConfigurationHelper;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.factura.FacturaHeadModelOutServiceLocal;
import com.mg.merp.factura.FacturaHeadOutServiceLocal;
import com.mg.merp.factura.FacturaSpecOutServiceLocal;
import com.mg.merp.factura.model.FacturaHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Исходящие счета - фактуры"
 *
 * @author leonova
 * @version $Id: FacturaHeadOutServiceBean.java,v 1.6 2007/03/23 16:17:14 safonov Exp $
 */
@Stateless(name = "merp/factura/FacturaHeadOutService")
public class FacturaHeadOutServiceBean extends GoodsDocumentServiceBean<FacturaHead, Integer, FacturaHeadModelOutServiceLocal, FacturaSpecOutServiceLocal> implements FacturaHeadOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
   */
  @Override
  protected Configuration doGetConfiguration() {
    return ConfigurationHelper.getDocumentConfiguration();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, FacturaHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));
  }

  @Override
  protected int getDocSectionIdentifier() {
    return FacturaHeadOutServiceLocal.DOCSECTION;
  }

}
