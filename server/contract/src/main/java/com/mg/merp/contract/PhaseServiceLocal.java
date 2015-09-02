/*
 * PhaseServiceLocal.java
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
package com.mg.merp.contract;

import java.math.BigDecimal;

import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PlanAndFactSumsByKindResult;

/**
 * Сервис бизнес-компонента "Этапы контракта"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseServiceLocal.java,v 1.4 2007/07/16 10:15:01 sharapov Exp $
 */
public interface PhaseServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Phase, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/contract/Phase"; //$NON-NLS-1$

	/**
	 * Получить сумму этапа контракта по видам пунктов
	 * @param contractPhase - этап контракта
	 * @return сумма этапа контракта по видам пунктов
	 */
	BigDecimal[] calculateTotalPhaseSumByKind(Phase contractPhase);

	/**
	 * Получить сумму
	 * @param shippedPayment - сумма платежей контрагенту
	 * @param receivedPayment - сумма платежей от контрагента
	 * @return сумма
	 */
	BigDecimal calculateSum(BigDecimal shippedPayment, BigDecimal receivedPayment);
	
	/**
	 * Расчет суммы этапа контракта
	 * @param phase - этап контракта
	 */
	void adjust(Phase phase);
	
	/**
	 * Получить агрегированные по видам пунктов значения фактичесих и планируемых сумм
	 * @param contractPhase - этап контракта
	 * @return агрегированные по видам пунктов значения фактичесих и планируемых сумм
	 */
	PlanAndFactSumsByKindResult getTotalPlanAndFactSumsByKind(Phase contractPhase);
		
	/**
	 * Аннулировать этап контракта
	 * @param contractPhase - этап контракта 
	 */
	void madeAvoid(Phase contractPhase);

}
