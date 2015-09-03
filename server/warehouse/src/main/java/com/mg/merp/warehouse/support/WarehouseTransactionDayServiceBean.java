/*
 * WarehouseTransactionDayServiceBean.java
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
package com.mg.merp.warehouse.support;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.warehouse.WarehouseTransactionDayServiceLocal;
import com.mg.merp.warehouse.model.WarehouseTransactionDay;

/**
 * Реализация сервиса бизнес-компонента "Операционные дни"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTransactionDayServiceBean.java,v 1.1 2007/11/29 08:50:24 alikaev Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseTransactionDayService")
public class WarehouseTransactionDayServiceBean extends	AbstractPOJODataBusinessObjectServiceBean<WarehouseTransactionDay, Integer> implements
		WarehouseTransactionDayServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, WarehouseTransactionDay entity) {
		context.addRule(new MandatoryAttribute(entity, "Stock"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseTransactionDayServiceLocal#openTransactionDay(java.io.Serializable[])
	 */
	public void openTransactionDay(Serializable[] dayIds) {
		if(dayIds.length != 0)
			for(int i=0; i<dayIds.length; i++){
				WarehouseTransactionDay wtd = load((Integer)dayIds[i]);
				internalOpenTransactionDay(wtd);
			}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseTransactionDayServiceLocal#closeTransactionDay(java.io.Serializable[])
	 */
	public void closeTransactionDay(Serializable[] dayIds) {
		if(dayIds.length != 0) 
			for(int i=0; i<dayIds.length; i++){
				WarehouseTransactionDay wtd = load((Integer)dayIds[i]);
				internalCloseTransactionDay(wtd);
			}
	}
	
	/**
	 * Открыть операционный день склада
	 * 
	 * @param wtd - операционный день склада
	 */
	private void internalOpenTransactionDay(WarehouseTransactionDay wtd) {
		wtd.setUserStockClosed(null);
		wtd.setOperationDate(null);
		getPersistentManager().merge(wtd);
		ServerUtils.addSystemAuditEvent(getBusinessServiceMetadata().getName(), "openTransactionDay", StringUtils.format(Messages.getInstance().getMessage(Messages.SYSTEM_AUDIT_MESSAGE_OPEN_TR_DAY), wtd.getId(), DateTimeUtils.toDateString(wtd.getClosedDay()), wtd.getStock().getCode().trim()));
	}

	/**
	 * Закрыть операционный день склада
	 * 
	 * @param wtd - операционный день склада
	 */
	private void internalCloseTransactionDay(WarehouseTransactionDay wtd) {
		wtd.setUserStockClosed(ServerUtils.getUserProfile().getUserName());
		wtd.setOperationDate(DateTimeUtils.nowDate());
		getPersistentManager().merge(wtd);
		ServerUtils.addSystemAuditEvent(getBusinessServiceMetadata().getName(), "closeTransactionDay", StringUtils.format(Messages.getInstance().getMessage(Messages.SYSTEM_AUDIT_MESSAGE_CLOSE_TR_DAY),wtd.getId(), DateTimeUtils.toDateString(wtd.getClosedDay()), wtd.getStock().getCode().trim()));
	}

}
