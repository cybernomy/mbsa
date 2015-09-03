/*
 * TimeBoardHelper.java
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
package com.mg.merp.table.support;

import java.util.Date;
import java.util.List;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.Color;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.table.model.TableConfig;
import com.mg.merp.table.model.TimeBoardPosition;
import com.mg.merp.table.model.TimeBoardSpec;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.ui.TimeBoardSpecItem;

/**
 * Класс-помощник для работы с табелем
 * 
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardHelper.java,v 1.1 2008/08/12 14:36:17 sharapov Exp $
 */
public class TimeBoardHelper {

	/**
	 * Цвет фона зафиксированной колонки грида 
	 */
	public static final Color FIXED_COLUMN_BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	private static final String LOAD_TIME_BOARD_SPEC_ITEMS_EJBQL_QUERY_NAME = "Table.TimeBoardHelper.loadTimeBoardSpecItems"; //$NON-NLS-1$
	private static final String LOAD_TIME_BOARD_SPECS_EJBQL_QUERY_NAME = "Table.TimeBoardHelper.loadTimeBoardSpecs"; //$NON-NLS-1$
	
	private OrmTemplate ormTemplate = OrmTemplate.getInstance();
	private List<TimeKind> timeKinds = null;
	
	public TimeBoardHelper() {
	}
	
	public List<TimeBoardSpecItem> loadTimeBoardSpecItems(Integer timeBoardHeadId, Integer staffListUnitId) {
		return ormTemplate.findByNamedQueryAndNamedParam(LOAD_TIME_BOARD_SPEC_ITEMS_EJBQL_QUERY_NAME,
				new String[] {"timeBoardHeadId", "staffListUnitId"}, new Object[] {timeBoardHeadId, staffListUnitId}, //$NON-NLS-1$ //$NON-NLS-2$
				new ResultTransformer<TimeBoardSpecItem>() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
			 */
			public TimeBoardSpecItem transformTuple(Object[] tuple, String[] aliases) {
				return new TimeBoardSpecItem((String) tuple[0], (String) tuple[1], (String) tuple[2], (Date) tuple[3], (Date) tuple[4], (String) tuple[5], (TimeBoardPosition) tuple[6]);
			}
		});
	}
	
	/**
	 * Получить список позиций табеля
	 * @param timeBoardHeadId - идентификатор заголовка табеля
	 * @param staffListUnitId - идентификатор подразделения в штатном расписании
	 * @return список позиций табеля
	 */
	public List<TimeBoardSpecItem> getTimeBoardSpecItems(Integer timeBoardHeadId, Integer staffListUnitId) {
		 List<TimeBoardSpecItem> resultList = loadTimeBoardSpecItems(timeBoardHeadId, staffListUnitId);
		 for (TimeBoardSpecItem timeBoardSpecItem : resultList)
			 timeBoardSpecItem.setTimeBoardSpecs(loadTimeBoardSpecs(timeBoardSpecItem.getTimeBoardPosition()));
		 return resultList;
	}
	
	/**
	 * Загрузить спецификацию табельного учета
	 * @param timeBoardPosition
	 * @return спецификация табельного учета
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private List<TimeBoardSpec> loadTimeBoardSpecs(TimeBoardPosition timeBoardPosition) {
		return ormTemplate.findByNamedQueryAndNamedParam(LOAD_TIME_BOARD_SPECS_EJBQL_QUERY_NAME, "timeBoardPosition", timeBoardPosition); //$NON-NLS-1$
	}
	
	/**
	 * Получить список типов времен
	 * @return список типов времен
	 */
	public List<TimeKind> getTimeKinds() {
		if(timeKinds == null)
			timeKinds = loadTimeKinds(); 
		return timeKinds;
	}
	
	private List<TimeKind> loadTimeKinds() {
		TimeKind workTimeKind = getWorkTimeKindByTableConfig();
		if (workTimeKind == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.SETUP_TIME_KINDS));

		List<TimeKind> timeKinds = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(TimeKind.class)
				.add(Restrictions.eq("IsWholeDay", false)) //$NON-NLS-1$
				.add(Restrictions.ne("Id", workTimeKind.getId())) //$NON-NLS-1$
				.addOrder(Order.asc("Priority"))); //$NON-NLS-1$

		timeKinds.add(0, ServerUtils.getPersistentManager().find(TimeKind.class, workTimeKind.getId()));

		return timeKinds;
	}
	
	/**
	 * Получть тип рабочего времени из конфигурации модуля
	 * @return тип рабочего времени или <code>null</code> если не найден
	 */
	private TimeKind getWorkTimeKindByTableConfig() {
		TableConfig tableConfig = ServerUtils.getPersistentManager().find(TableConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
		return tableConfig == null ? null : tableConfig.getWorkTimeKind();
	}
	
}
