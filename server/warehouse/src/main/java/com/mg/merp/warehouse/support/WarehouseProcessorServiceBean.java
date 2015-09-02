/* WarehouseProcessorServiceBean.java
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

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessorServiceLocal;
import com.mg.merp.warehouse.WarehouseTransactionListener;

/**
 * Класс-реализация интерфейса {@link WarehouseProcessorServiceLocal}
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessorServiceBean.java,v 1.19 2008/08/27 09:41:21 sharapov Exp $
 */
@Stateful(name = "merp/warehouse/WarehouseProcessorService")
public class WarehouseProcessorServiceBean extends
		AbstractPOJOBusinessObjectStatefulServiceBean implements
		WarehouseProcessorServiceLocal {
	
	private WarehouseProcessorImpl delegate = new WarehouseProcessorImpl();

	@PermitAll
	@Remove
	public void processCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params) {
		delegate.processCalcPricesByWarehouseDataTransaction(params);
	}

	@PermitAll
	@Remove
	public void processWarehousePlanTransaction(DocFlowPluginInvokeParams params) {
		delegate.processWarehousePlanTransaction(params);
	}

	@PermitAll
	@Remove
	public void processWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		delegate.processWarehousePlanWithdrawalTransaction(params);
	}

	@PermitAll
	@Remove
	public void processWarehouseReservTransaction(DocFlowPluginInvokeParams params) {
		delegate.processWarehouseReservTransaction(params);
	}

	@PermitAll
	@Remove
	public void processWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		delegate.processWarehouseReservWithdrawalTransaction(params);
	}

	@PermitAll
	@Remove
	public void processWarehouseTransaction(DocFlowPluginInvokeParams params, boolean doInteractive, boolean doCalculateCost, WarehouseTransactionListener listener) {
		delegate.processWarehouseTransaction(params, doInteractive, doCalculateCost, listener);
	}

	@PermitAll
	@Remove
	public void rollbackCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackCalcPricesByWarehouseDataTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackWarehousePlanTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackWarehousePlanTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackWarehousePlanWithdrawalTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackWarehouseReservTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackWarehouseReservTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackWarehouseReservWithdrawalTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackWarehouseTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackWarehouseTransaction(params);
	}

	@PermitAll
	@Remove
	public void processAssembleProductTransaction(DocFlowPluginInvokeParams params, boolean assembleProductKind) {
		delegate.processAssembleProductTransaction(params, assembleProductKind);
	}

	@PermitAll
	@Remove
	public void rollbackAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackAssembleProductTransaction(params);
	}

	@PermitAll
	@Remove
	public void processDisAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		delegate.processDisAssembleProductTransaction(params);
	}

	@PermitAll
	@Remove
	public void rollbackDisAssembleProductTransaction(DocFlowPluginInvokeParams params) {
		delegate.rollbackDisAssembleProductTransaction(params);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessor#proccessCustomsDeclarationByStockBach(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.warehouse.WarehouseProcessListener)
	 */
	@PermitAll
	@Remove
	public void proccessCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params, WarehouseProcessListener warehouseProcessListener) {
		delegate.proccessCustomsDeclarationByStockBach(params, warehouseProcessListener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.WarehouseProcessor#rollbackCustomsDeclarationByStockBach(com.mg.merp.docflow.DocFlowPluginInvokeParams)
	 */
	@PermitAll
	@Remove
	public void rollbackCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params) {
		delegate.rollbackCustomsDeclarationByStockBach(params);
	}
	
}
