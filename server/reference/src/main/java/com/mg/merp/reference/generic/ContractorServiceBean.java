/*
 * ContractorServiceBean.java
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
package com.mg.merp.reference.generic;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.Contractor;

import java.util.List;

/**
 * Базовая реализация бизнес-компонентов "Контрагенты"
 *
 * @author Oleg V. Safonov
 */
public abstract class ContractorServiceBean<T extends com.mg.merp.reference.model.Contractor>
    extends AbstractPOJODataBusinessObjectServiceBean<T, Integer> implements Contractor<T> {

  protected void adjustOrgUnit(T entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(T entity) {
    adjustOrgUnit(entity);
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(T entity) {
    adjustOrgUnit(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(ID[], java.lang.Object)
   */
  @Override
  public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
    for (Integer key : primaryKeys) {
      T entity = load(key);
      Integer targetFolderId = (Integer) ((PersistentObject) targetEntity).getPrimaryKey();
      entity.setFolderId(targetFolderId);
    }
    return true;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.Contractor#findFromCode(java.lang.String)
   */
  public T findFromCode(String code) {
    List<T> contractors = findByCriteria(Restrictions.eq("UpCode", StringUtils.toUpperCase(code)));
    if (contractors.isEmpty())
      return null;
    else
      return contractors.get(0);
  }

}
