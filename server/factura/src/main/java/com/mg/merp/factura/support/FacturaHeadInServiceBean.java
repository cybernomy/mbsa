/*
 * FacturaHeadInServiceBean.java
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
import com.mg.merp.factura.FacturaHeadInServiceLocal;
import com.mg.merp.factura.FacturaHeadModelInServiceLocal;
import com.mg.merp.factura.FacturaSpecInServiceLocal;
import com.mg.merp.factura.model.FacturaHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Входящие счета - фактуры"
 *
 * @author leonova
 * @version $Id: FacturaHeadInServiceBean.java,v 1.7 2008/11/24 12:54:40 safonov Exp $
 */
@Stateless(name = "merp/factura/FacturaHeadInService")
public class FacturaHeadInServiceBean extends GoodsDocumentServiceBean<FacturaHead, Integer, FacturaHeadModelInServiceLocal, FacturaSpecInServiceLocal> implements FacturaHeadInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void doAdjust(FacturaHead entity) {
    super.doAdjust(entity);
    entity.setSrcStock(null);
    entity.setSrcMol(null);
  }

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

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return FacturaHeadInServiceLocal.DOCSECTION;
  }

}
