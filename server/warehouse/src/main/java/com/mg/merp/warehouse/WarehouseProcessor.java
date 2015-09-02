/* WarehouseProcessor.java
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
package com.mg.merp.warehouse;

import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * Интерфейс "Процессор управления запасами"
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessor.java,v 1.4 2008/08/27 09:39:57 sharapov Exp $ 
 */
public interface WarehouseProcessor {

	/**
	 * Этап "Отработка по складу"
	 * 
	 * @param params	параметры этапа
	 * @param doInteractive	выполнять интерактивно
	 * @param doCalculateCost	расчитывать цену
	 * @param listener	слушатель
	 */
	void processWarehouseTransaction(DocFlowPluginInvokeParams params, boolean doInteractive
			, boolean doCalculateCost, WarehouseTransactionListener listener);

	/**
	 * Откат этапа "Отработка по складу"
	 * 
	 * @param params	параметры
	 */
	void rollbackWarehouseTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Этап "Отработка планируемого движения по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void processWarehousePlanTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Отработка планируемого движения по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackWarehousePlanTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Этап "Снятие с плана движения по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void processWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Снятие с плана движения по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Этап "Резервирование по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void processWarehouseReservTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Резервирование по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackWarehouseReservTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Этап "Снятие с резерва по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void processWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Снятие с резерва по складу"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * Этап "Рассчитать цены в документе на основании данных склада"
	 * 
	 * @param params	параметры этапа
	 */
	void processCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Рассчитать цены в документе на основании данных склада"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * Этап "Собрать комплект"
	 * 
	 * @param params	параметры этапа
	 */
	void processAssembleProductTransaction(DocFlowPluginInvokeParams params, boolean assembleProductKind);

	/**
	 * Откат этапа "Собрать комплект"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackAssembleProductTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * Этап "Разобрать комплект"
	 * 
	 * @param params	параметры этапа
	 */
	void processDisAssembleProductTransaction(DocFlowPluginInvokeParams params);

	/**
	 * Откат этапа "Разобрать комплект"
	 * 
	 * @param params	параметры этапа
	 */
	void rollbackDisAssembleProductTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * Этап "Проставить номер ГТД из складской партии"
	 * @param params - параметры этапа
	 * @param warehouseProcessListener - слушатель складского процессора
	 */
	void proccessCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params, WarehouseProcessListener warehouseProcessListener);
	
	/**
	 * Откат этапа "Проставить номер ГТД из складской партии"
	 * @param params - параметры этапа
	 */
	void rollbackCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params);
	
}
