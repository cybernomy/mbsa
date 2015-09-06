/*
 * ItemFamilyServiceBean.java
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

package com.mg.merp.planning.support;

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.ItemFamilyServiceLocal;
import com.mg.merp.planning.model.ItemFamily;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Семейcтва обобщенных товаров"
 *
 * @author leonova
 * @version $Id: ItemFamilyServiceBean.java,v 1.4 2007/08/27 07:10:43 alikaev Exp $
 */
@Stateless(name = "merp/planning/ItemFamilyService") //$NON-NLS-1$
public class ItemFamilyServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ItemFamily, Integer> implements ItemFamilyServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, final ItemFamily entity) {
    context.addRule(new MandatoryAttribute(entity, "ParentGenericItem")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "PlanningLevel")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "ChildGenericItem")); //$NON-NLS-1$
    context.addRule(new Rule() {

      /*
       * (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#getMessage()
       */
      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.ITEM_FAMILY_PARENT_CONCUR_WIDTH_CHILD_ITEM);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
       */
      public void validate(ValidationContext context) {
        if (isParentConcurWithChildItem(entity))
          context.getStatus().error(this);
      }
    });

  }

  /**
   * функция проверяет совпадает ли родительский и обобщенные товары
   *
   * @return возвращает <code>true</code> если совпадают, иначе <code>false</code>
   */
  private boolean isParentConcurWithChildItem(ItemFamily entity) {
    if (entity.getParentGenericItem() == null)
      return false;
    if (entity.getChildGenericItem() == null)
      return false;
    if (entity.getParentGenericItem().getId().equals(entity.getChildGenericItem().getId()))
      return true;
    else return false;
  }

}
