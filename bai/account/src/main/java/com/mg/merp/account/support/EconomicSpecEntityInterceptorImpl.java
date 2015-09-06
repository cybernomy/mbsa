/*
 * EconomicSpecEntityInterceptorImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.merp.account.model.EconomicSpec;

/**
 * Реализация перехватчика сущностей проводок ХО
 *
 * @author Oleg V. Safonov
 * @version $Id: EconomicSpecEntityInterceptorImpl.java,v 1.3 2008/03/13 06:20:53 alikaev Exp $
 */
public class EconomicSpecEntityInterceptorImpl extends AbstractEntityInterceptor {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
   */
  @Override
  public String[] getHandledEntities() {
    return new String[]{EconomicSpec.class.getName()};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
   */
  @Override
  public String getName() {
    return "merp/account/EconomicSpecEntityInterceptor";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostPersist(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostPersist(PersistentObject entity) {
    //отражение изменений в оборотках
    if (!((EconomicSpec) entity).isBulkOperation())
      AccountTurnoverUpdater.execute(((EconomicSpec) entity).getEconomicOper().getId(), false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostRemove(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostRemove(PersistentObject entity) {
    //отражение изменений в оборотках
    if (!((EconomicSpec) entity).isBulkOperation())
      AccountTurnoverUpdater.execute(((EconomicSpec) entity).getEconomicOper().getId(), false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostUpdate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostUpdate(PersistentObject entity, AttributeMap oldState) {
    //отражение изменений в оборотках
    if (!((EconomicSpec) entity).isBulkOperation())
      AccountTurnoverUpdater.execute(((EconomicSpec) entity).getEconomicOper().getId(), false);
  }

}
