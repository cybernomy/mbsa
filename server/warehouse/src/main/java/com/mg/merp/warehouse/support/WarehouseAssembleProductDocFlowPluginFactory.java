/*
 * WarehouseAssembleProductDocFlowPluginFactory.java
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
 * Реализация фабрики реализаций этапа ДО "Собрать комплект"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseAssembleProductDocFlowPluginFactory.java,v 1.1 2007/10/23 14:05:19 alikaev Exp $
 */
public class WarehouseAssembleProductDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

	/**
	 * Идентификатор фабрики
	 */
	public final static int FACTORY_IDENTIFIER = 27;

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin() {

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
			 */
			@Override
			protected void doExecute() throws Exception {
				WarehouseProcessorServiceLocal service = (WarehouseProcessorServiceLocal) ApplicationDictionaryLocator.locate()
						.getBusinessService(WarehouseProcessorServiceLocal.LOCAL_SERVICE_NAME);

				service.processAssembleProductTransaction(getParams(), false);
				complete();
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doGetDocActionResultTextRepresentation(com.mg.merp.docprocess.model.DocHeadState)
			 */
			@Override
			protected String doGetDocActionResultTextRepresentation(DocHeadState docHeadState) {
				return StringUtils.BLANK_STRING;
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
			 */
			@Override
			protected void doRoolback() throws Exception {
				WarehouseProcessorServiceLocal service = (WarehouseProcessorServiceLocal) ApplicationDictionaryLocator.locate()
						.getBusinessService(WarehouseProcessorServiceLocal.LOCAL_SERVICE_NAME);

				service.rollbackAssembleProductTransaction(getParams());
				complete();
			}
		}; 
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
	 */
	@Override
	public int getIdentifier() {
		return FACTORY_IDENTIFIER;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
	 */
	@Override
	public String getName() {
		return "Assemble product";
	}

}
