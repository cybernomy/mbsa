/*
 * FinSpecEntityInterceptorImpl.java
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
package com.mg.merp.finance.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.merp.finance.model.Specification;

/**
 * Реализация перехватчика для модели проводки и признака финансовой операции
 *
 * @author Oleg V. Safonov
 * @version $Id: FinSpecEntityInterceptorImpl.java,v 1.2 2007/12/17 09:14:41 safonov Exp $
 */
public class FinSpecEntityInterceptorImpl extends AbstractEntityInterceptor {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
   */
  @Override
  public String[] getHandledEntities() {
    return new String[]{Specification.class.getName()};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
   */
  @Override
  public String getName() {
    return "merp/finance/FinSpecEntityInterceptor";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostPersist(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostPersist(PersistentObject entity) {
    FinanceTurnoverUpdater.execute(((Specification) entity).getFinOper().getId());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostRemove(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostRemove(PersistentObject entity) {
    FinanceTurnoverUpdater.execute(((Specification) entity).getFinOper().getId());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostUpdate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  public void onPostUpdate(PersistentObject entity, AttributeMap oldState) {
    FinanceTurnoverUpdater.execute(((Specification) entity).getFinOper().getId());
  }

}
