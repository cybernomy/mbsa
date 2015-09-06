/*
 * InputLaborHeadServiceBean.java
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
import com.mg.merp.manufacture.InputLaborHeadServiceLocal;
import com.mg.merp.manufacture.InputLaborModelServiceLocal;
import com.mg.merp.manufacture.InputLaborSpecServiceLocal;
import com.mg.merp.manufacture.model.InputDocumentHead;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Акты на списание времени, отработанного РС в НЗП"
 *
 * @author leonova
 * @version $Id: InputLaborHeadServiceBean.java,v 1.6 2006/09/20 10:56:37 safonov Exp $
 */
@Stateless(name = "merp/manufacture/InputLaborHeadService")
public class InputLaborHeadServiceBean extends com.mg.merp.manufacture.generic.InputDocumentHeadServiceBean<InputDocumentHead, Integer, InputLaborModelServiceLocal, InputLaborSpecServiceLocal> implements InputLaborHeadServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, InputDocumentHead entity) {
    super.onValidate(context, entity);

    context.addRule(new MandatoryAttribute(entity, "To"));
    context.addRule(new MandatoryAttribute(entity, "From"));
  }

  @Override
  protected int getDocSectionIdentifier() {
    return InputLaborHeadServiceLocal.DOCSECTION;
  }

}
