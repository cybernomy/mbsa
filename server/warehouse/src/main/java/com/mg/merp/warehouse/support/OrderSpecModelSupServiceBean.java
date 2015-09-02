/*
 * OrderSpecModelSupServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.warehouse.support;

import java.math.BigDecimal;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.reference.model.TimePeriodKind;
import com.mg.merp.warehouse.OrderSpecModelSupServiceLocal;
import com.mg.merp.warehouse.model.OrderSpecModel;

/**
 * Бизнес-компонент "Образцы спецификаций заказов постащикам" 
 * 
 * @author leonova
 * @version $Id: OrderSpecModelSupServiceBean.java,v 1.4 2007/09/07 08:06:59 alikaev Exp $
 */
@Stateless(name="merp/warehouse/OrderSpecModelSupService")  //$NON-NLS-1$
public class OrderSpecModelSupServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrderSpecModel, Integer> implements OrderSpecModelSupServiceLocal {
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, OrderSpecModel entity) {
		context.addRule(new MandatoryAttribute(entity, "Catalog")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Measure1")); //$NON-NLS-1$
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(OrderSpecModel entity) {
		adjustOrderSpecModelCus(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(OrderSpecModel entity) {
		adjustOrderSpecModelCus(entity);
	}
	
	/**
	 * Заполняет обязательные поля
	 * @param entity
	 */
	private void adjustOrderSpecModelCus(OrderSpecModel entity) {
		if (entity.getShelfLife() == null)
			entity.setShelfLife(new BigDecimal(0));
		if (entity.getShelfLifeMeas() == null)
			entity.setShelfLifeMeas(TimePeriodKind.NONE);
	}

}
