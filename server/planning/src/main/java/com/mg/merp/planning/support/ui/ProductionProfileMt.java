/*
 * ProductionProfileMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.planning.support.ui;

import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.DateField;
import com.mg.framework.api.ui.widget.IntegerField;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.PlanningLevelBucket;

/**
 * Контроллер формы поддержки бизнес-компонента "Профили производства"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ProductionProfileMt.java,v 1.1 2007/01/15 08:09:26 sharapov Exp $
 */
public class ProductionProfileMt extends DefaultMaintenanceForm {
	private final String SEARCH_PROPERTY = "PlanningLevel"; //$NON-NLS-1$
	private PlanningLevel searchValue = null;
	private Map<String, Object> searchMap = null;

	/**
	 * Обработчик события нажатия кнопки "Выбор начального периода"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionChooseStartBucketOffset(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.mfreference.support.ui.BucketBeginSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				setStartBucketValues((PlanningLevelBucket)event.getItems()[0]);
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		if(getSearchMap() != null) {
			searchHelp.setImportContext(searchMap);
			searchHelp.search();
		}
	}

	/**
	 * Обработчик события нажатия кнопки "Выбор конечного периода"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionChooseEndBucketOffset(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.mfreference.support.ui.BucketBeginSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				setEndBucketValues((PlanningLevelBucket)event.getItems()[0]);
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		if(getSearchMap() != null) {
			searchHelp.setImportContext(searchMap);
			searchHelp.search();
		}
	}

	/**
	 * Создает список условий для поиска
	 * @return список условий для поиска
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private Map<String, Object> getSearchMap() throws NoSuchFieldException, IllegalAccessException {
		searchValue = (PlanningLevel) getFieldValue(SEARCH_PROPERTY);
		if (searchValue != null) {
			searchMap = new HashMap<String, Object>();
			searchMap.put(SEARCH_PROPERTY, searchValue);
		}
		return searchMap;
	}

	/**
	 * Устанавливает соответствующие значения для начального периода
	 * @param startBucket - объект-сущность: Период уровня планирования 
	 */
	private void setStartBucketValues(PlanningLevelBucket startBucket) {
		((IntegerField) view.getWidget("StartBucketOffset")).setEditorValue(new Integer(startBucket.getBucketOffset())); //$NON-NLS-1$
		((DateField) view.getWidget("StartBucketStartDate")).setEditorValue(startBucket.getStartDate()); //$NON-NLS-1$
		((DateField) view.getWidget("StartBucketEndDate")).setEditorValue(startBucket.getEndDate()); //$NON-NLS-1$
		view.flushForm();
	}

	/**
	 * Устанавливает соответствующие значения для конечного периода
	 * @param endBucket - объект-сущность: Период уровня планирования
	 */
	private void setEndBucketValues(PlanningLevelBucket endBucket) {
		((IntegerField) view.getWidget("EndBucketOffset")).setEditorValue(new Integer(endBucket.getBucketOffset())); //$NON-NLS-1$
		((DateField) view.getWidget("EndBucketStartDate")).setEditorValue(endBucket.getStartDate()); //$NON-NLS-1$
		((DateField) view.getWidget("EndBucketEndDate")).setEditorValue(endBucket.getEndDate()); //$NON-NLS-1$
		view.flushForm();
	}

}
