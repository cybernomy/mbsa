/**
 * EconomicSpecMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.CalculateOutCostResult;
import com.mg.merp.account.ProcessorServiceLocal;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;

/**
 * Контроллер формы поддержки проводки хозяйственной операции
 * 
 * @author Oleg V. Safonov
 * @version $Id: EconomicSpecMt.java,v 1.2 2009/02/25 15:10:36 safonov Exp $
 */
public class EconomicSpecMt extends DefaultMaintenanceForm {

	/**
	 * обработчик кнопки "расчитать цены списания"
	 * 
	 * @param event
	 */
	public void onActionCalculateOutCost(WidgetEvent event) {
		ProcessorServiceLocal service = (ProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ProcessorServiceLocal.LOCAL_SERVICE_NAME);
		EconomicSpec es = (EconomicSpec) getEntity();
		EconomicOper eo = ServerUtils.getPersistentManager().find(EconomicOper.class, es.getEconomicOper().getId());
		CalculateOutCostResult calcResult = service.calculateOutCost(eo.getKeepDate(), es.getAccBatchKt(), es.getAccKt(), es.getAnlKt1(), es.getAnlKt2(), es.getAnlKt3(), es.getAnlKt4(), es.getAnlKt5(), es.getCatalog(), null, es.getQuantity());
		if (calcResult != null) {
			es.setSummaNat(calcResult.getSummaNat());
			es.setSummaCur(calcResult.getSummaCur());
			es.setQuantity(calcResult.getRealQuantity());
			es.setAccBatchKt(calcResult.getAccBatch());
			es.setAccBatchHistoryKt(calcResult.getAccBatchHistory());
		}
	}

}
