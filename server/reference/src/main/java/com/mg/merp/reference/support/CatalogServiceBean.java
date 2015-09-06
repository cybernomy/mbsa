/*
 * CatalogServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.CatalogServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogExpDate;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.TimePeriodKind;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Каталог"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CatalogServiceBean.java,v 1.8 2007/08/10 13:34:44 safonov Exp $
 */
@Stateless(name = "merp/reference/CatalogService") //$NON-NLS-1$
public class CatalogServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Catalog, Integer> implements CatalogServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(Catalog entity) {
    entity.setExpDateCalcKind(CatalogExpDate.EDCKPRODUCTION);
    entity.setShelfLifeMeas(TimePeriodKind.NONE);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CatalogServiceLocal#findByCode(java.lang.String)
   */
  @PermitAll
  public Catalog findByCode(String code) {
    List<Catalog> list = findByCriteria(Restrictions.eq("UpCode", StringUtils.toUpperCase(code)));
    if (list.isEmpty())
      return null;
    else
      return list.get(0);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CatalogServiceLocal#findByBarCode(java.lang.String)
   */
  @PermitAll
  public Catalog findByBarCode(String barCode) {
    List<Catalog> list = findByCriteria(Restrictions.eq("BarCode", barCode));
    if (list.isEmpty())
      return null;
    else
      return list.get(0);
  }

  private void adjustCatalog(Catalog entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Catalog entity) {
    adjustCatalog(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Catalog entity) {
    adjustCatalog(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(Catalog entity) {
    entity.setCode(DataUtils.generateUniqueString(20));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final Catalog entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "GoodType")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "FullName")); //$NON-NLS-1$
    CatalogType catalogType = entity.getGoodType();
    if (catalogType != CatalogType.SERVICE_NOT_FIXED && catalogType != CatalogType.TAX && catalogType != CatalogType.TARIFF)
      context.addRule(new MandatoryAttribute(entity, "Measure1")); //$NON-NLS-1$
    context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") { //$NON-NLS-1$
      @Override
      protected void doValidate(ValidationContext context) {
        if (OrmTemplate.getInstance().findUniqueByCriteria(Catalog.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId())) != null) //$NON-NLS-1$ //$NON-NLS-2$
          context.getStatus().error(this);
      }
    });

  }

}
