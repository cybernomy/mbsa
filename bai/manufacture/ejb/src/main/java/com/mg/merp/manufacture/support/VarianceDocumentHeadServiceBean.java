/*
 * VarianceDocumentHeadServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.manufacture.VarianceDocumentHeadServiceLocal;
import com.mg.merp.manufacture.VarianceDocumentModelServiceLocal;
import com.mg.merp.manufacture.VarianceDocumentSpecServiceLocal;
import com.mg.merp.manufacture.generic.ManufactureDocumentHeadServiceBean;
import com.mg.merp.manufacture.model.VarianceDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Докумементы по отклонениям"
 *
 * @author leonova
 * @version $Id: VarianceDocumentHeadServiceBean.java,v 1.6 2006/09/20 10:56:37 safonov Exp $
 */
@Stateless(name = "merp/manufacture/VarianceDocumentHeadService")
public class VarianceDocumentHeadServiceBean extends ManufactureDocumentHeadServiceBean<VarianceDocumentHead, Integer, VarianceDocumentModelServiceLocal, VarianceDocumentSpecServiceLocal> implements VarianceDocumentHeadServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, VarianceDocumentHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));

  }

  @Override
  protected int getDocSectionIdentifier() {
    return VarianceDocumentHeadServiceLocal.DOCSECTION;
  }

}
