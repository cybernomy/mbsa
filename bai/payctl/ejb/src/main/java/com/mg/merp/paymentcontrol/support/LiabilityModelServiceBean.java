/*
 * LiabilityModelServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.paymentcontrol.LiabilityModelServiceLocal;
import com.mg.merp.paymentcontrol.model.Liability;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Образцы реестра обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityModelServiceBean.java,v 1.4 2007/05/14 05:18:50 sharapov Exp $
 */
@Stateless(name = "merp/paymentcontrol/LiabilityModelService") //$NON-NLS-1$
public class LiabilityModelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Liability, Integer> implements LiabilityModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Liability entity) {
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(Liability entity) {
    super.onInitialize(entity);
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Liability entity) {
    adjustEntity(entity);
  }

  private void adjustEntity(Liability entity) {
    entity.setModel(true);
  }

}
