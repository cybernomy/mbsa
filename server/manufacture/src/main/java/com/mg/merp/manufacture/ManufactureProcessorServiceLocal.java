/*
 * ManufactureProcessorServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.manufacture.model.Job;

/**
 * Бизнес-компонет "Процессор производства"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ManufactureProcessorServiceLocal.java,v 1.1 2007/08/06 12:46:24 safonov Exp $
 */
public interface ManufactureProcessorServiceLocal extends BusinessObjectService {

	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/ManufactureProcessor";

	/**
	 * создание документов на отклонения
	 * 
	 * @param job	ЗНП
	 */
	void createVarianceDocument(Job job);
	
	void updateJobFromOutputDocument(DocFlowPluginInvokeParams params);
	
	void rollbackUpdateJobFromOutputDocument(DocFlowPluginInvokeParams params);

	void updateJobFromScrapDocument(DocFlowPluginInvokeParams params);
	
	void rollbackUpdateJobFromScrapDocument(DocFlowPluginInvokeParams params);

	void performBackflush(DocFlowPluginInvokeParams params);
	
	void rollbackPerformBackflush(DocFlowPluginInvokeParams params);
	
	void postToWorkInProgress(DocFlowPluginInvokeParams params);
	
	void rollbackPostToWorkInProgress(DocFlowPluginInvokeParams params);

	void performScrap(DocFlowPluginInvokeParams params);
	
	void rollbackPerformScrap(DocFlowPluginInvokeParams params);
	
}
