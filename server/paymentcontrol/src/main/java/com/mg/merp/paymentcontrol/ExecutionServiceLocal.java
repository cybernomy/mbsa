/*
 * ExecutionServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TransferParams;

/**
 * Сервис бизнес-компонента "Исполнение обязательств"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ExecutionServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface ExecutionServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Execution, Integer> {
	
	/**
	 * локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Execution"; //$NON-NLS-1$
	
	/**
	 * тип папки для средств платежа
	 */
	final static short FOLDER_TYPE = 13400;

	/**
	 * Исполнить обязательство
	 * @param liability - обязательство
	 * @param pmcResource - средство платежа
	 * @param planDate - дата исполнения
	 * @param sumCur - сумма исполнения
	 */
	void executeLiability(Liability liability, PmcResource pmcResource, Date planDate, BigDecimal sumCur);
		
	/**
	 * Исполнить обязательство
	 * @param resourceId - идентификатор средства платежа 
	 * @param resourceFolderId - идентификатор группы средств платежа
	 * @param liability - обязательство
	 * @param versionId - идентификатор версии планирования
	 * @param execDate - дата исполнения
	 * @param sumCur - сумма исполнения
	 */
	void executeLiability(Integer resourceId, Integer resourceFolderId, Liability liability, Integer versionId, Date execDate, BigDecimal sumCur);

	/**
	 * Переместить средства
	 * @param transferParams - параметры перемещения средств
	 * @param versionId - идентификатор версии планирования
	 */
	void transferResourses(TransferParams transferParams, Integer versionId);
	
	/**
	 * Сформировать документы
	 * @param executionIds - список идентификаторов исполнений
	 * @param date - дата, на которую формируются документы
	 * @return отчет о результах формирования
	 */
	String createDocuments(Serializable[] executionIds, Date date);

	/**
	 * Утвердить/снять утверждение
	 * @param executionIds - список идентификаторов исполнений
	 * @param isApproved - признак утверждения
	 */
	void setApproved(Serializable[] executionIds, boolean isApproved);
	
	/**
	 * Показать созданный документ
	 * @param executionId - идентификатор исполнения
	 */
	void showCreatedDocument(Integer executionId);
	
	/**
	 * Удалить созданный документ
	 * @param executionIds - список идентификаторов исполнений
	 */
	void deleteCreatedDocument(Serializable[] executionIds);
	
	/**
	 * Проверка возможности осуществления операции изменение/удаление над сущностью "исполнение обязательства"
	 * @param entity - объект-сущность "исполнение обязательства"
	 */
	void checkForOperationPossibility(Execution entity);
	
	/**
	 * Проверить статус версии планирования
	 * @param versionId - идентификатор версии планирования 
	 * @param execDate - дата
	 */
	void checkVersionStatus(Integer versionId, Date execDate);

}
