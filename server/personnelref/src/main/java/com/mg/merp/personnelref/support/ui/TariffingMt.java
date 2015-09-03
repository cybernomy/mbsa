/*
 * TariffingMt.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.personnelref.model.TariffScaleClass;
import com.mg.merp.personnelref.model.Tariffing;

/**
 * Контроллер формы поддержки "Тарифы"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TariffingMt.java,v 1.1 2007/07/18 11:12:13 sharapov Exp $
 */
public class TariffingMt extends DefaultMaintenanceForm {

	private final String TARIFF_SCALE_CLASS_SEARCH_HELP_NAME = "com.mg.merp.personnelref.support.ui.TariffScaleClassSearchHelp"; //$NON-NLS-1$


	public TariffingMt() {
		super();
	}

	/**
	 * Обработчик кнопки "Выбрать тарифную сетку"
	 * @param event - событие
	 */
	public void onActionChooseTariffScale(WidgetEvent event) throws Exception {
		TariffScaleClassSearchHelp searchHelp = (TariffScaleClassSearchHelp) SearchHelpProcessor.createSearch(TARIFF_SCALE_CLASS_SEARCH_HELP_NAME);
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				TariffScaleClass tariffScaleClass = (TariffScaleClass) event.getItems()[0];
				((Tariffing) getEntity()).setTariffScaleCode(tariffScaleClass.getTariffScale().getSCode());
				((Tariffing) getEntity()).setTariffClass(tariffScaleClass.getClassNumber());
				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Обработчик кнопки "Очистить тарифную сетку"
	 * @param event - событие
	 */
	public void onActionClearTariffScale(WidgetEvent event) {
		((Tariffing) getEntity()).setTariffScaleCode(null);
		view.flushModel();
	}

}
