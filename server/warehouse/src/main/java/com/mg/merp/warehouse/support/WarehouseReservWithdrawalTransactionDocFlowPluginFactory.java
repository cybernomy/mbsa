/* WarehouseReservDisposalTransactionDocFlowPluginFactory.java
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

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.warehouse.WarehouseProcessorServiceLocal;

/**
 * Реализация фабрики реализаций этапа ДО "Снятие с резерва по складу"
 * 
 * @author Valentin A. Poroxnenko
 * @version $$Id: WarehouseReservWithdrawalTransactionDocFlowPluginFactory.java,v 1.2 2007/03/29 08:49:08 poroxnenko Exp $$
 */
public class WarehouseReservWithdrawalTransactionDocFlowPluginFactory extends
		AbstractDocFlowPluginFactory {
	public final static int FACTORY_IDENTIFIER = 24;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin() {

			@Override
			protected void doExecute() throws Exception {
				WarehouseProcessorServiceLocal service = (WarehouseProcessorServiceLocal) ApplicationDictionaryLocator.locate()
						.getBusinessService(WarehouseProcessorServiceLocal.LOCAL_SERVICE_NAME);
				
				service.processWarehouseReservWithdrawalTransaction(getParams());
				complete();
			}

			@Override
			protected String doGetDocActionResultTextRepresentation(
					DocHeadState docHeadState) {
				// TODO: спросить консультантов
				return StringUtils.BLANK_STRING;
			}

			@Override
			protected void doRoolback() throws Exception {
				WarehouseProcessorServiceLocal service = (WarehouseProcessorServiceLocal) ApplicationDictionaryLocator.locate()
						.getBusinessService(WarehouseProcessorServiceLocal.LOCAL_SERVICE_NAME);
		
				service.rollbackWarehouseReservWithdrawalTransaction(getParams());
				complete();
			}

		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
	 */
	@Override
	public int getIdentifier() {
		return FACTORY_IDENTIFIER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
	 */
	@Override
	public String getName() {
		return "Warehouse reserv withdrawal transaction";
	}

}
