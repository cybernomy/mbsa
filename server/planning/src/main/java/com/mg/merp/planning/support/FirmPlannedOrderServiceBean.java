/*
 * FirmPlannedOrderServiceBean.java
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

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.FirmPlannedOrderServiceLocal;
import com.mg.merp.planning.model.FirmPlannedOrder;
import com.mg.merp.planning.model.MrpRecommendation;
import com.mg.merp.planning.model.RecommendType;

/**
 * Бизнес-компонент "Подтвержденные заказы" 
 * 
 * @author leonova
 * @version $Id: FirmPlannedOrderServiceBean.java,v 1.7 2007/08/27 09:38:12 alikaev Exp $
 */
@Stateless(name="merp/planning/FirmPlannedOrderService") //$NON-NLS-1$
public class FirmPlannedOrderServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FirmPlannedOrder, Integer> implements FirmPlannedOrderServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, FirmPlannedOrder entity) {
		context.addRule(new MandatoryAttribute(entity, "MrpVersionControl"));  //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Catalog"));  //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Measure"));  //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Warehouse"));  //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "PurchaseOrTransfer"));  //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.planning.FirmPlannedOrderServiceLocal#createByMrpRecommendation(int)
	 */
	public void createByMrpRecommendation(int mrpVersionId) {
		//Отбираем только рекомендации на межскладское перемещение
		OrmTemplate ormTemplate = OrmTemplate.getInstance();
		List<MrpRecommendation> recommends = ormTemplate.findByCriteria(OrmTemplate.createCriteria(MrpRecommendation.class)
				.add(Restrictions.eq("MrpVersionControl.Id", mrpVersionId)) //$NON-NLS-1$
				.add(Restrictions.eq("FirmPlanSuggestedOrder", true)) //$NON-NLS-1$
				.add(Restrictions.eq("PurchaseOrTransfer", RecommendType.TRANSFER)) //$NON-NLS-1$
				.add(Restrictions.eq("MrpOrdered", false))); //$NON-NLS-1$
 		
		for (MrpRecommendation recommend : recommends) {
			//пытаемся найти одобренные заказы
			List<FirmPlannedOrder> orders = ormTemplate.findByCriteria(OrmTemplate.createCriteria(FirmPlannedOrder.class)
					.add(Restrictions.eq("MrpRecommendation", recommend))); //$NON-NLS-1$
			//если нет одобренных заказов, то создаем
			if (orders.isEmpty()) {
				FirmPlannedOrder order = initialize();
				order.setMrpVersionControl(recommend.getMrpVersionControl());
				order.setMrpRecommendation(recommend);
				order.setFixedInput(true);
				order.setRequisitionFlag(false);
				order.setManualEntry(false);
				order.setVendor(recommend.getVendor());
				order.setMeasure(recommend.getMeasure());
				order.setCatalog(recommend.getCatalog());
				order.setWarehouse(recommend.getWarehouse());
				order.setSourceWarehouse(recommend.getSourceWarehouse());
				order.setRequiredDate(recommend.getRequiredDate());
				order.setOrderQty(recommend.getOrderQty());
				order.setOrderDate(recommend.getOrderDate());
				order.setPurchaseOrTransfer(recommend.getPurchaseOrTransfer());
				
				create(order);
				
				//изменим флаг в рекомендациях
				recommend.setMrpOrdered(true);
			}
		}
 	}
	
}
