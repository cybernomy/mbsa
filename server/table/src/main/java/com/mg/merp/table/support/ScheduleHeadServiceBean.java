/*
 * ScheduleHeadServiceBean.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.table.PatternSpecServiceLocal;
import com.mg.merp.table.ScheduleHeadServiceLocal;
import com.mg.merp.table.ScheduleSpecServiceLocal;
import com.mg.merp.table.model.HolidayAccountKind;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternKind;
import com.mg.merp.table.model.PatternSpec;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;
import com.mg.merp.table.model.TableConfig;
import com.mg.merp.table.model.TimeKind;

/**
 * Реализация бизнес-компонента "Графики работ в табельном учете" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleHeadServiceBean.java,v 1.5 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name="merp/table/ScheduleHeadService") //$NON-NLS-1$
public class ScheduleHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScheduleHead, Integer> implements ScheduleHeadServiceLocal {

	private static final String DELETE_SPECS_OVER_PERIOD_EJBQL_QUERY_NAME = "Table.ScheduleHeadServiceBean.deleteSpecsOverPeriod"; //$NON-NLS-1$
	private static final String GET_HOLIDAY_DATES_EJBQL_QUERY_NAME = "Table.ScheduleHeadServiceBean.getHolidayDates"; //$NON-NLS-1$


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ScheduleHead entity) {
		context.addRule(new MandatoryAttribute(entity, "WorkSchedule")); //$NON-NLS-1$
		if(HolidayAccountKind.AnotherTimeKind == entity.getHolidayAccountKind() && entity.getHolidayWorkTimeKind() == null)
			context.addRule(new MandatoryAttribute(entity, "HolidayWorkTimeKind")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.table.ScheduleHeadServiceLocal#createByPattern(com.mg.merp.table.model.ScheduleHead, com.mg.merp.table.model.PatternHead, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<ScheduleSpec> createByPattern(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate) {
		return doCreateByPattern(scheduleHead, patternHead, initialShift, beginDate, endDate);
	}

	/**
	 * Создать график по образцу
	 * @param scheduleHead - заголовок графика в табельном учете
	 * @param patternHead - заголовок образца
	 * @param initialShift - смещение(для сменных графиков)
	 * @param beginDate - дата с
	 * @param endDate - дата по
	 * @return список позиций спецификации графика в табельном учете
	 */
	protected List<ScheduleSpec> doCreateByPattern(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate) {
		List<ScheduleSpec> scheduleSpecs = doFillByPattern(scheduleHead, patternHead, initialShift, beginDate, endDate);
		createScheduleSpecs(scheduleSpecs);
		return scheduleSpecs;
	}

	/**
	 * Заполнить график по образцу
	 * @param scheduleHead - заголовок графика в табельном учете
	 * @param patternHead - заголовок образца
	 * @param initialShift - смещение(для сменных графиков)
	 * @param beginDate - дата с
	 * @param endDate - дата по
	 * @return список позиций спецификации графика в табельном учете
	 */
	protected List<ScheduleSpec> doFillByPattern(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate) {
		List<ScheduleSpec> scheduleSpecs = new ArrayList<ScheduleSpec>();
		if(beginDate == null || endDate == null || beginDate.after(endDate))
			return scheduleSpecs;

		TableConfig tableConfig = getTableConfig();
		if(tableConfig == null || tableConfig.getHolidayTimeKind() == null || tableConfig.getWorkTimeKind() == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.SETUP_TIME_KINDS));

		TimeKind holidayWorkTimeKind = null;
		HolidayAccountKind holidayAccountKind = scheduleHead.getHolidayAccountKind() == null ? HolidayAccountKind.DontAccount : scheduleHead.getHolidayAccountKind();
		if(HolidayAccountKind.AnotherTimeKind == holidayAccountKind && scheduleHead.getHolidayWorkTimeKind() == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.NEED_TO_FILL_HOLIDAY_WORK_TIME_KIND));

		deleteSpecsOverPeriod(scheduleHead, beginDate, endDate);

		BigDecimal preholidayReduction = scheduleHead.getPreHolidayReduction() == null ? BigDecimal.ZERO : scheduleHead.getPreHolidayReduction();
		int daysPerWeek = 7;
		int actualShift = 0;
		int patternDuration = 0;
		int periodDuration = ((Long) DateTimeUtils.getDaysBetween(endDate, beginDate)).intValue() + 1;
		if(PatternKind.WEEKLY == patternHead.getPatternKind()) {
			patternDuration = daysPerWeek;
			actualShift = (daysPerWeek - getDayOfWeekIndex(beginDate) + 1) % daysPerWeek;
		} else {
			patternDuration = patternHead.getDuration();
			actualShift = initialShift % patternDuration;
		}

		// holidays accounting
		boolean holidays[] = new boolean[periodDuration + 1]; 
		if(HolidayAccountKind.DontAccount != holidayAccountKind) {
			List<Date> holidayDates = getHolidayDates(beginDate, DateTimeUtils.incDay(endDate, 1));
			if(!holidayDates.isEmpty()) {
				for(int i = 0; i < holidayDates.size(); i++) {
					holidays[((Long) DateTimeUtils.getDaysBetween(holidayDates.get(i), beginDate)).intValue()] = true;
				}
				//preholidayReduction = scheduleHead.getPreHolidayReduction() == null ? BigDecimal.ZERO : ;
				if(HolidayAccountKind.AnotherTimeKind == holidayAccountKind)
					holidayWorkTimeKind = scheduleHead.getHolidayWorkTimeKind();
			} else // if there are no holidays in period, don't account them
				holidayAccountKind = HolidayAccountKind.DontAccount;
		}

		// insert pattern records into schedule
		ScheduleSpecServiceLocal scheduleSpecService = getScheduleSpecService();
		boolean isNeedInsert = false;
		List<PatternSpec> patternSpecs = loadPatternSpec(patternHead);
		for(int i = 0; i < periodDuration; i++) {
			for (int j = 0; j < patternSpecs.size(); j++) {
				PatternSpec patternSpec = patternSpecs.get(j);
				TimeKind patternSpecTimeKind = patternSpec.getTimeKind();
				if(((i + (patternDuration - actualShift)) % patternDuration) == patternSpec.getDayNumber() - 1) {
					isNeedInsert = true;
					// don't insert any time kinds except worktime in holiday
					if(holidays[i] && HolidayAccountKind.AsHoliday == holidayAccountKind) {
						if(!(patternSpecTimeKind.getId() == tableConfig.getWorkTimeKind().getId() || patternSpecTimeKind.getId() == tableConfig.getHolidayTimeKind().getId()))
							isNeedInsert = false;
					}
					// don't insert work time kind in holiday
					if(holidays[i] && HolidayAccountKind.AnotherTimeKind == holidayAccountKind) {
						if(patternSpecTimeKind.getId() == holidayWorkTimeKind.getId())
							isNeedInsert = false;
					}

					if(isNeedInsert) {
						ScheduleSpec scheduleSpec = scheduleSpecService.initialize();
						scheduleSpec.setScheduleHead(scheduleHead);
						scheduleSpec.setScheduleDate(DateTimeUtils.incDay(beginDate, i));

						// set preholiday reduction
						if(holidays[i + 1] && HolidayAccountKind.DontAccount != holidayAccountKind && patternSpecTimeKind.getId() == tableConfig.getWorkTimeKind().getId())
							scheduleSpec.setHoursQuantity(BigDecimal.ZERO.max(patternSpec.getHoursQuantity().subtract(preholidayReduction)));
						else
							scheduleSpec.setHoursQuantity(patternSpec.getHoursQuantity());

						// if holiday is accounted as holiday
						if(holidays[i] && HolidayAccountKind.AsHoliday == holidayAccountKind) {
							scheduleSpec.setTimeKind(tableConfig.getHolidayTimeKind());
							scheduleSpec.setHoursQuantity(null);
						}
						else // if holiday worktime is accounted as another time kind 
							if(holidays[i] && HolidayAccountKind.AnotherTimeKind == holidayAccountKind && patternSpecTimeKind.getId() == tableConfig.getWorkTimeKind().getId()) {
								scheduleSpec.setTimeKind(holidayWorkTimeKind);
								scheduleSpec.setHoursQuantity(null);
							} else
								scheduleSpec.setTimeKind(patternSpecTimeKind);

						scheduleSpecs.add(scheduleSpec);
					}
				}
			}
		}
		return scheduleSpecs;
	}

	/**
	 * Создать спецификацию графика работ в табельном учете
	 * @param scheduleSpecs - список позиций
	 */
	private void createScheduleSpecs(List<ScheduleSpec> scheduleSpecs) {
		ScheduleSpecServiceLocal scheduleSpecService = getScheduleSpecService();
		for (ScheduleSpec scheduleSpec : scheduleSpecs)
			scheduleSpecService.create(scheduleSpec);
	}

	/**
	 * Удалить спецификации которые лежат в диапазоне дат
	 * @param scheduleHead - заголовок графика работ
	 * @param beginDate - дата с
	 * @param endDate - дата по
	 */
	private void deleteSpecsOverPeriod(ScheduleHead scheduleHead, Date beginDate, Date endDate) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery(DELETE_SPECS_OVER_PERIOD_EJBQL_QUERY_NAME,  new String[] {"scheduleHead", "beginDate", "endDate"}, new Object[] {scheduleHead, beginDate, endDate}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * Получить список дат праздников которые лежат в диапазоне дат
	 * @param dateBegin - дата с
	 * @param dateEnd - дата по
	 * @return список дат праздников
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private List<Date> getHolidayDates(Date dateBegin, Date dateEnd) {
		return OrmTemplate.getInstance().findByNamedQueryAndNamedParam(GET_HOLIDAY_DATES_EJBQL_QUERY_NAME, new String[] {"dateBegin", "dateEnd"}, new Object[] {dateBegin, dateEnd}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Загрузить спецификацию шаблона
	 * @param patternHead - заголовок шаблона
	 * @return список позиций
	 */
	protected List<PatternSpec> loadPatternSpec(PatternHead patternHead) {
		return getPatternSpecService().loadSpecs(patternHead);
	}

	/**
	 * Получить конфигурацию модуля 
	 * @return конфигурация модуля или <code>null</code> если не инициализирована
	 */
	protected TableConfig getTableConfig() {
		return ServerUtils.getPersistentManager().find(TableConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
	}

	protected PatternSpecServiceLocal getPatternSpecService() {
		return (PatternSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PatternSpecServiceLocal.SERVICE_NAME);
	}

	protected ScheduleSpecServiceLocal getScheduleSpecService() {
		return (ScheduleSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleSpecServiceLocal.SERVICE_NAME);
	}

	/**
	 * Получить индекс дня недели
	 * @param date - дата
	 * @return индекс дня недели (1 - понедельник ... 7 - воскресенье)
	 */
	private int getDayOfWeekIndex(Date date) {
		int dayOfWeekIndex = 0;
		int dayOfWeek = DateTimeUtils.getDayOfWeek(date);
		switch (dayOfWeek) {
		case Calendar.MONDAY:
			dayOfWeekIndex = 1;
			break;
		case Calendar.TUESDAY:
			dayOfWeekIndex = 2;
			break;
		case Calendar.WEDNESDAY:
			dayOfWeekIndex = 3;
			break;
		case Calendar.THURSDAY:
			dayOfWeekIndex = 4;
			break;
		case Calendar.FRIDAY:
			dayOfWeekIndex = 5;
			break;
		case Calendar.SATURDAY:
			dayOfWeekIndex = 6;
			break;
		case Calendar.SUNDAY:
			dayOfWeekIndex = 7;
			break;
		}
		return dayOfWeekIndex;
	}

}
