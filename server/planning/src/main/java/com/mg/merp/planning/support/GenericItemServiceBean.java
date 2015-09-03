/*
 * GenericItemServiceBean.java
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

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.planning.GenericItemServiceLocal;
import com.mg.merp.planning.model.GenericItem;
import com.mg.merp.reference.model.Catalog;

/**
 * Реализация бизнес-компонента "Обобщенный товар" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Oleg V. Safonov
 * @version $Id: GenericItemServiceBean.java,v 1.7 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name="merp/planning/GenericItemService") //$NON-NLS-1$
public class GenericItemServiceBean extends AbstractPOJODataBusinessObjectServiceBean<GenericItem, Integer> implements GenericItemServiceLocal {

	private void resetAllLowLevelCodes() {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.GenericItem.resetAllLowLevelCodes");
	}
	
	private void performBOM(GenericItem genericItem, short level) {
		Date nowDate = DateTimeUtils.nowDate();
		OrmTemplate tmpl = OrmTemplate.getInstance();
		//загрузим материалы из БОМов которые описывают текущий обобщенный товар
		List<Catalog> catalogList = tmpl.findByCriteria(OrmTemplate.createCriteria(BomMaterial.class, "bm")
				.createAlias("bm.BomRoute", "br")
				.createAlias("br.Bom", "b")
				.add(Restrictions.eq("b.Catalog", genericItem.getCatalog()))
				.add(Restrictions.le("br.EffOnDate", nowDate))
				.add(Restrictions.ge("br.EffOffDate", nowDate))
				.add(Restrictions.le("bm.EffOnDate", nowDate))
				.add(Restrictions.ge("bm.EffOffDate", nowDate))
				.setProjection(Projections.property("bm.Catalog")));
		//загрузим материалы из ЗНП которые описывают текущий обобщенный товар
		List<Catalog> jobCatalogList = tmpl.findByCriteria(OrmTemplate.createCriteria(JobMaterial.class, "jm")
				.createAlias("jm.Oper", "jr")
				.createAlias("jr.Job", "j")
				.add(Restrictions.eq("j.Catalog", genericItem.getCatalog()))
				.add(Restrictions.le("jr.EffOnDate", nowDate))
				.add(Restrictions.ge("jr.EffOffDate", nowDate))
				.add(Restrictions.le("jm.EffOnDate", nowDate))
				.add(Restrictions.ge("jm.EffOffDate", nowDate))
				.setProjection(Projections.property("jm.Catalog")));
		catalogList.addAll(jobCatalogList);
		
		for (Catalog catalog : catalogList) {
			//ищем обобщенный товар для материала, если нашли, значит на один уровень вниз
			GenericItem gi = tmpl.findUniqueByCriteria(OrmTemplate.createCriteria(GenericItem.class)
					.add(Restrictions.eq("Catalog", catalog)));
			if (gi != null) {
				gi.setLowLevelCode((short) (level + 1));
				store(gi);
			}
		}
	}
	
	/**
	 * реализация расчета кода нижнего уровня
	 *
	 */
	protected void internalBuildLowLevelCodes() {
		boolean exitFlag = false;
		short level = 1;
		short maxLevel = Short.MAX_VALUE;
		while (!exitFlag && level <= maxLevel) {
			List<GenericItem> genericItems = findByCriteria(Restrictions.eq("LowLevelCode", level));
			exitFlag = genericItems.size() == 0;
			if (!exitFlag) {
				for (GenericItem genericItem : genericItems) {
					performBOM(genericItem, level);
				}
				level++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final GenericItem entity) {
		context.addRule(new MandatoryStringAttribute(entity, "GenericItemCode")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Measure"));	 //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "GenericItemName"));	 //$NON-NLS-1$
		//на одну позицию каталога может ссылаться только один обобщенный товар
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.DUPLICATE_GENERIC_ITEM_CATALOG), entity, "Catalog") {

			@Override
			protected void doValidate(ValidationContext context) {
				Criteria criteria = OrmTemplate.createCriteria(GenericItem.class)
						.add(Restrictions.eq("Catalog", entity.getCatalog()));
				if (entity.getId() != null)
					criteria.add(Restrictions.ne("Id", entity.getId()));
				if (OrmTemplate.getInstance().findUniqueByCriteria(criteria) != null)
					context.getStatus().error(this);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.GenericItemServiceLocal#buildLowLevelCodes()
	 */
	public void buildLowLevelCodes() {
		resetAllLowLevelCodes();
		internalBuildLowLevelCodes();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.GenericItemServiceLocal#findByCatalog(int)
	 */
	@PermitAll
	public GenericItem findByCatalog(int catalogId) {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(GenericItem.class)
				.add(Restrictions.eq("Catalog.Id", catalogId)));
	}

}