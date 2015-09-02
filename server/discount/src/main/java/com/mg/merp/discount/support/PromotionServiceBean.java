/*
 * PromotionServiceBean.java
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
package com.mg.merp.discount.support;

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.discount.PromotionServiceLocal;
import com.mg.merp.discount.model.Promotion;
import com.mg.merp.discount.model.PromotionLine;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;

/**
 * –еализаци€ бизнес-компонента "–екламное меропри€тие"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionServiceBean.java,v 1.1 2007/10/30 14:10:57 sharapov Exp $
 */
@Stateless(name="merp/discount/PromotionService") //$NON-NLS-1$
public class PromotionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Promotion, Integer> implements PromotionServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, Promotion entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "PromotionType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DateApprove")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Number")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(Promotion entity) {
		entity.setPriority(1);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.discount.PromotionServiceLocal#getPromotions(java.util.Date, com.mg.merp.reference.model.Catalog, com.mg.merp.reference.model.CatalogFolder)
	 */
	@PermitAll
	public List<PromotionLine> getPromotions(Date actualDate, Catalog catalog, CatalogFolder catalogFolder) {
		return doGetPromotions(actualDate, catalog, catalogFolder);
	}
	
	/**
	 * ѕолучить список действующих рекламных меропри€тий по приоритету на дату, дл€ позиции каталога или дл€ папки каталога 
	 * @param actualDate - на дату
	 * @param catalog - позици€ каталога
	 * @param catalogFolder - папка каталога 
	 * @return список действующих рекламных меропри€тий по приоритету на дату, по позиции каталога или папке каталога
	 */
	protected List<PromotionLine> doGetPromotions(Date actualDate, Catalog catalog, CatalogFolder catalogFolder) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PromotionLine.class)
					.createAlias("Promotion", "promotion") //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.eq("IsActive", true)) //$NON-NLS-1$
					.add(Restrictions.or(
						Restrictions.eq("Catalog", catalog), //$NON-NLS-1$
						Restrictions.eq("CatalogFolder", catalogFolder))) //$NON-NLS-1$
					.add(Restrictions.le("promotion.DateFrom", actualDate)) //$NON-NLS-1$
					.add(Restrictions.ge("promotion.DateTill", actualDate)) //$NON-NLS-1$
					.add(Restrictions.eq("promotion.IsActive", true)) //$NON-NLS-1$
					.addOrder(Order.asc("promotion.Priority"))); //$NON-NLS-1$
	}

}
