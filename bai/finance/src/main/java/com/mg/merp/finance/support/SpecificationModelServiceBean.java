/*
 * SpecificationModelServiceBean.java
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

package com.mg.merp.finance.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.finance.SpecificationModelServiceLocal;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.SpecificationModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация образцов финансовых операций"
 *
 * @author leonova
 * @version $Id: SpecificationModelServiceBean.java,v 1.4 2007/10/08 14:30:23 safonov Exp $
 */
@Stateless(name = "merp/finance/SpecificationModelService")
public class SpecificationModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SpecificationModel, Integer> implements SpecificationModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(SpecificationModel entity) {
    OperationModel om = entity.getFinOper();
    if (om != null)
      entity.setPlanned(om.getPlanned());
  }

}
