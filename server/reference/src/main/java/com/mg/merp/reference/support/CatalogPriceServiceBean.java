/*
 * CatalogPriceServiceBean.java
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

package com.mg.merp.reference.support;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.reference.CatalogPriceServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.Currency;

/**
 * Бизнес-компонент "Каталог / Нормативные цены" 
 * 
 * @author leonova
 * @version $Id: CatalogPriceServiceBean.java,v 1.5 2007/07/27 12:08:53 safonov Exp $
 */
@Stateless(name="merp/reference/CatalogPriceService")
public class CatalogPriceServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CatalogPrice, Integer> implements CatalogPriceServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CatalogPrice entity) {
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateType"));
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateAuthority"));
		context.addRule(new MandatoryAttribute(entity, "Currency"));
		context.addRule(new MandatoryAttribute(entity, "InAction"));
		context.addRule(new MandatoryAttribute(entity, "EquivalentPrice"));
		context.addRule(new MandatoryAttribute(entity, "Price"));
/*		context.addRule(new EntityBeanRule(com.mg.merp.reference.support.Messages.getInstance().getMessage(Messages.EXISTS_PRICE), entity, "InAction") {
			@Override
			protected void doValidate(ValidationContext context) {
				if (OrmTemplate.getInstance().findUniqueByCriteria(CatalogPrice.class, Restrictions.eq("InAction", entity.getInAction()), Restrictions.eq("CurrencyRateAuthority", entity.getCurrencyRateAuthority()), Restrictions.eq("CurrencyRateType", entity.getCurrencyRateType()), Restrictions.eq("Currency", entity.getCurrency()), Restrictions.ne("Id", entity.getId())) != null)
					context.getStatus().error(this);
			}
		});*/
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.CatalogPriceServiceLocal#findActual(java.util.Date, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.Currency)
	 */
	@PermitAll
	public CatalogPrice findActual(Date actualityDate, Catalog catalog, Currency currency) {
		DetachedCriteria dc = DetachedCriteria.forClass(CatalogPrice.class, "cp")
				.setProjection(Projections.max("cp.InAction"))
				.add(Restrictions.eq("cp.Catalog", catalog))
				.add(Restrictions.eq("cp.Currency", currency))
				.add(Restrictions.le("cp.InAction", actualityDate));
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(CatalogPrice.class)
				.add(Restrictions.eq("Catalog", catalog))
				.add(Subqueries.propertyEq("InAction", dc)));
	}

}
