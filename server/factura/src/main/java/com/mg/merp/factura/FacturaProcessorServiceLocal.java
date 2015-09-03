/*
 * FacturaProcessorServiceLocal.java.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.factura;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Процессор счетов-фактур
 * 
 * @author Artem V. Sharapov
 * @version $Id: FacturaProcessorServiceLocal.java,v 1.2 2009/03/16 14:30:34 sharapov Exp $
 */
public interface FacturaProcessorServiceLocal extends BusinessObjectService {
	
	static final String SERVICE_NAME = "merp/factura/FacturaProcessor";
		
	/**
	 * Выполнить ЭДО "Регистрация счет-фактуры"
	 * @param params - параметры ЭДО
	 */
	void registerFactura(DocFlowPluginInvokeParams params);
	
	/**
	 * Откатить ЭДО "Регистрация счет-фактуры"
	 * @param params - параметры ЭДО
	 */
	void rollBackRegisterFactura(DocFlowPluginInvokeParams params);
	
	
	/**
	 * Выполнить ЭДО "Регистрация даты оприходования"
	 * @param params - параметры ЭДО
	 */
	void registerStockDate(DocFlowPluginInvokeParams params);
	
	/**
	 * Откатить ЭДО "Регистрация даты оприходования"
	 * @param params - параметры ЭДО
	 */
	void rollBackRegisterStockDate(DocFlowPluginInvokeParams params);
	
	
	/**
	 * Выполнить ЭДО "Регистрация даты оплаты"
	 * @param params - параметры ЭДО
	 */
	void registerPayDate(DocFlowPluginInvokeParams params);
	
	/**
	 * Откатить ЭДО "Регистрация даты оплаты"
	 * @param params - параметры ЭДО
	 */
	void rollBackRegisterPayDate(DocFlowPluginInvokeParams params);
	
	
	/**
	 * Выполнить ЭДО "Регистрация в книге покупок"
	 * @param params - параметры ЭДО
	 */
	void registerInBuyBook(DocFlowPluginInvokeParams params);
	
	/**
	 * Откатить ЭДО "Регистрация в книге покупок"
	 * @param params - параметры ЭДО
	 */
	void rollBackRegisterInBuyBook(DocFlowPluginInvokeParams params);
	
	
	/**
	 * Выполнить ЭДО "Регистрация в книге продаж"
	 * @param params - параметры ЭДО
	 */
	void registerInSaleBook(DocFlowPluginInvokeParams params);
	
	/**
	 * Откатить ЭДО "Регистрация в книге продаж"
	 * @param params - параметры ЭДО
	 */
	void rollBackRegisterInSaleBook(DocFlowPluginInvokeParams params);
	
}
