/*
 * WarehouseServiceBean.java
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
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * Бизнес-компонент "Склады"
 * 
 * @author leonova
 * @version $Id: WarehouseServiceBean.java,v 1.10 2007/11/29 08:50:24 alikaev Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseService")
public class WarehouseServiceBean extends
AbstractPOJODataBusinessObjectServiceBean<Warehouse, Integer> implements
WarehouseServiceLocal {

	@PermitAll
	public Warehouse addWarehouse(OrgUnit orgUnit) {
		//проверим стандартный метод
		SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));

		JdbcTemplate.getInstance().update("INSERT INTO WH_WAREHOUSE (ID, CONTRACTOR_ID, CLIENT_ID) VALUES (?, ?, ?)", new Object[] {orgUnit.getId(), orgUnit.getId(), ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId()}); //$NON-NLS-1$ //$NON-NLS-2$
		return load(orgUnit.getId());
	}

	@PermitAll
	public void eraseWarehouse(int id) {
		//проверим стандартный метод
		SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.ERASE_METHOD));

		JdbcTemplate.getInstance().update("DELETE FROM WH_WAREHOUSE WHERE (id = ?)", new Object[] {id});//$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseServiceLocal#getWarehouseDefaultMOL(com.mg.merp.warehouse.model.Warehouse)
	 */
	@PermitAll
	public Employees getWarehouseDefaultMOL(Warehouse warehouse) {
		return doGetWarehouseDefaultMOL(warehouse);
	}

	/**
	 * Получить МОЛ по умолчанию для склада
	 * @param warehouse - склад
	 * @return МОЛ(сотрудник) по умолчанию для склада, или <code>null</code> если не найден
	 */
	protected Employees doGetWarehouseDefaultMOL(Warehouse warehouse) {
		List<Employees> employees = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Employees.class)
				.add(Restrictions.eq("FolderId", warehouse.getId())) //$NON-NLS-1$
				.add(Restrictions.eq("IsDefault", true))); //$NON-NLS-1$
		if(!employees.isEmpty())
			return employees.get(0);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseServiceLocal#closeWarehouse(java.io.Serializable[])
	 */
	public void closeWarehouse(Serializable[] warehouseIds, Date closedDateTill ) {
		if(warehouseIds.length != 0) 
			for(int i=0; i<warehouseIds.length; i++){
				Warehouse wtd = load((Integer)warehouseIds[i]);
				wtd.setClosedDateTill(closedDateTill);
				internalCloseWarehouse(wtd);
			}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseServiceLocal#openWarehouse(java.io.Serializable[])
	 */
	public void openWarehouse(Serializable[] warehouseIds) {
		if(warehouseIds.length != 0) 
			for(int i=0; i<warehouseIds.length; i++){
				Warehouse wtd = load((Integer)warehouseIds[i]);
				internalOpenWarehouse(wtd);
			}		
	}

	/**
	 * Закрыть склад
	 * @param wh - склад
	 */
	private void internalCloseWarehouse(final Warehouse wh) {
		wh.setWarehouseTransactionClosed(true);
		wh.setOperationDate(DateTimeUtils.nowDate());
		wh.setUserStockClosed(ServerUtils.getUserProfile().getUserName());
		getPersistentManager().merge(wh);
		ServerUtils.addSystemAuditEvent(getBusinessServiceMetadata().getName(), "closeStock"
				, StringUtils.format(Messages.getInstance().getMessage(Messages.SYSTEM_AUDIT_MESSAGE_CLOSE_WAREHOUSE), wh.getCode().trim(), DateTimeUtils.toDateString(wh.getClosedDateTill())));
	}

	/**
	 * Открыть склад
	 * @param wh - склад
	 */
	private void internalOpenWarehouse(final Warehouse wh) {
		wh.setClosedDateTill(null);
		wh.setOperationDate(null);
		wh.setUserStockClosed(null);
		wh.setWarehouseTransactionClosed(false);
		getPersistentManager().merge(wh);
		ServerUtils.addSystemAuditEvent(getBusinessServiceMetadata().getName(), "openStock"
				, StringUtils.format(Messages.getInstance().getMessage(Messages.SYSTEM_AUDIT_MESSAGE_OPEN_WAREHOUSE), wh.getCode().trim()));		
	}
}
