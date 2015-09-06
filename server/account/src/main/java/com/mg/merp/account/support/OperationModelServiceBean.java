/*
 * OperationModelServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.account.EconomicSpecModelServiceLocal;
import com.mg.merp.account.OperationModelServiceLocal;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.EconomicSpecModel;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Образцы хозяйственных операций"
 *
 * @author leonova
 * @version $Id: OperationModelServiceBean.java,v 1.5 2007/11/08 07:05:47 sharapov Exp $
 */
@Stateless(name = "merp/account/OperationModelService") //$NON-NLS-1$
public class OperationModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<EconomicOperModel, Integer> implements OperationModelServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, EconomicOperModel entity) {
    context.addRule(new MandatoryStringAttribute(entity, "ModelName"));     //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(EconomicOperModel entity, EconomicOperModel entityClone) {
    final String ECONOMIC_OPER_MODEL_ATTRIBUTE_NAME = "EconomicOperModel"; //$NON-NLS-1$
    EconomicSpecModelServiceLocal economicSpecModelService = (EconomicSpecModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpecModel"); //$NON-NLS-1$
    AttributeMap initAttributes = new LocalDataTransferObject();
    initAttributes.put(ECONOMIC_OPER_MODEL_ATTRIBUTE_NAME, entityClone);
    for (EconomicSpecModel economicSpecModel : economicSpecModelService.findByCriteria(Restrictions.eq(ECONOMIC_OPER_MODEL_ATTRIBUTE_NAME, entity)))
      economicSpecModelService.clone(economicSpecModel, true, initAttributes);
  }

}