/*
 * CatalogWarehouseServiceBean.java
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

package com.mg.merp.planning.support;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.CatalogWarehouseServiceLocal;
import com.mg.merp.planning.GenericItemServiceLocal;
import com.mg.merp.planning.SafetyLevel;
import com.mg.merp.planning.model.CatalogWarehouse;
import com.mg.merp.reference.model.Catalog;

/**
 * Бизнес-компонент "Склады хранения товаров" 
 * 
 * @author leonova
 * @version $Id: CatalogWarehouseServiceBean.java,v 1.4 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name="merp/planning/CatalogWarehouseService")
public class CatalogWarehouseServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CatalogWarehouse, Integer> implements CatalogWarehouseServiceLocal {

	private SafetyLevel[] internalGetSafetyLevel() {
		final GenericItemServiceLocal genericItemService = (GenericItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(GenericItemServiceLocal.SERVICE_NAME);
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CatalogWarehouse.class)
				.setProjection(Projections.projectionList(
						Projections.sum("SafetyLevel"),
						Projections.property("Catalog"),
						Projections.groupProperty("Catalog")))
				.setResultTransformer(new ResultTransformer<SafetyLevel>() {

					public SafetyLevel transformTuple(Object[] tuple, String[] aliases) {
						Catalog catalog = (Catalog) tuple[1];
						return new SafetyLevel((BigDecimal) tuple[0], catalog, genericItemService.findByCatalog(catalog.getId()));
					}
					
				})).toArray(new SafetyLevel[0]);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CatalogWarehouse entity) {
		context.addRule(new MandatoryAttribute(entity, "Warehouse"));
		context.addRule(new MandatoryAttribute(entity, "Catalog"));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.CatalogWarehouseServiceLocal#getSafetyLevel()
	 */
	@PermitAll
	public SafetyLevel[] getSafetyLevel() {
		return internalGetSafetyLevel();
	}

}
