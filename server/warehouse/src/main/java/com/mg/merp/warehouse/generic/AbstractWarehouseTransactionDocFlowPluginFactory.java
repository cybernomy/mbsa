/**
 * AbstractWarehouseTransactionDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.generic;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.warehouse.WarehouseProcessorServiceLocal;
import com.mg.merp.warehouse.WarehouseTransactionListener;

/**
 * Абстрактная реализация фабрик этапов ДО по отработке в складской подсистеме
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractWarehouseTransactionDocFlowPluginFactory.java,v 1.1 2008/04/18 15:18:20 safonov Exp $
 */
public abstract class AbstractWarehouseTransactionDocFlowPluginFactory extends
		AbstractDocFlowPluginFactory {

	protected abstract boolean isInteractive();
	
	protected abstract boolean isCalculateCost();
	
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
				
				service.processWarehouseTransaction(getParams(), isInteractive(), isCalculateCost(), new WarehouseTransactionListener() {

					public void aborted() {
						cancel();
					}

					public void completed() {
						complete();
					}
					
				});
				
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
		
				service.rollbackWarehouseTransaction(getParams());
				complete();
			}

		};
	}

}
