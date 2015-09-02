/*
 * TimeBoardHeadServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.personnelref.model.CalcPeriod;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.personnelref.model.StaffListUnit;
import com.mg.merp.table.ScheduleSpecServiceLocal;
import com.mg.merp.table.TimeBoardHeadServiceLocal;
import com.mg.merp.table.TimeBoardPositionServiceLocal;
import com.mg.merp.table.TimeBoardSpecServiceLocal;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;
import com.mg.merp.table.model.TimeBoardHead;
import com.mg.merp.table.model.TimeBoardPosition;
import com.mg.merp.table.model.TimeBoardSpec;

/**
 * Реализация бизнес-компонента "Заголовки табелей" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardHeadServiceBean.java,v 1.4 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name="merp/table/TimeBoardHeadService") //$NON-NLS-1$
public class TimeBoardHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TimeBoardHead, Integer> implements TimeBoardHeadServiceLocal {

	private static final String GET_SCHEDULE_SPECS_EJBQL_QUERY_NAME = "Table.TimeBoardHeadServiceBean.getScheduleSpecs"; //$NON-NLS-1$
	private static final String LOAD_POSITIONS_FROM_STAFF_LIST_EJBQL_QUERY_NAME = "Table.TimeBoardHeadServiceBean.loadPositionsFromStaffList"; //$NON-NLS-1$

	protected TimeBoardPositionServiceLocal timeBoardPositionService = (TimeBoardPositionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TimeBoardPositionServiceLocal.SERVICE_NAME);
	protected ScheduleSpecServiceLocal scheduleSpecService = (ScheduleSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleSpecServiceLocal.SERVICE_NAME);
	protected TimeBoardSpecServiceLocal timeBoardSpecService = (TimeBoardSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TimeBoardSpecServiceLocal.SERVICE_NAME);

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TimeBoardHead entity) {
		context.addRule(new MandatoryStringAttribute(entity, "BNumber")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CalcPeriod")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(TimeBoardHead entity) {
		fillBySchedule(entity);
	}

	protected void fillBySchedule(TimeBoardHead timeBoardHead) {
		CalcPeriod calcPeriod = timeBoardHead.getCalcPeriod();
		Date periodBeginDate = calcPeriod.getBeginDate();
		Date periodEndDate = calcPeriod.getEndDate();
		Integer positionFillId = 0;
		TimeBoardPosition timeBoardPosition = null;
		List<PositionFromStaffListResult> positionsFromStaffList = loadPositionsFromStaffList(calcPeriod.getStaffList(), periodBeginDate, periodEndDate);
		for (PositionFromStaffListResult positionFromStaffList : positionsFromStaffList) {
			// several records can exist for one position 
			if(positionFillId != positionFromStaffList.positionFill.getId()) {
				positionFillId = positionFromStaffList.positionFill.getId();
				timeBoardPosition = initializeTimeBoardPosition(timeBoardHead, positionFromStaffList.positionFill, positionFromStaffList.staffListUnit);
				createTimeBoardPosition(timeBoardPosition);
			}
			if(positionFromStaffList.scheduleHead != null) {
				Date beginDate = DateTimeUtils.getMaxDate(periodBeginDate, positionFromStaffList.staffListPositionBeginDate, positionFromStaffList.positionFillBeginDate);
				Date endDate = DateTimeUtils.getMinDate(periodEndDate, positionFromStaffList.staffListPositionEndDate, positionFromStaffList.positionFillEndDate);
				List<ScheduleSpec> scheduleSpecs = getScheduleSpecs(positionFromStaffList.scheduleHead, beginDate, endDate);
				for (ScheduleSpec scheduleSpec : scheduleSpecs)
					createTimeBoardSpec(initializeTimeBoardSpecByScheduleSpec(timeBoardPosition, scheduleSpec));
			}
		}
	}

	protected TimeBoardPosition initializeTimeBoardPosition(TimeBoardHead timeBoardHead, PositionFill positionFill, StaffListUnit staffListUnit) {
		TimeBoardPosition timeBoardPosition = timeBoardPositionService.initialize();
		timeBoardPosition.setTimeBoardHead(timeBoardHead);
		timeBoardPosition.setPositionFill(positionFill);
		timeBoardPosition.setStaffListUnit(staffListUnit);
		return timeBoardPosition;
	}

	protected TimeBoardSpec initializeTimeBoardSpecByScheduleSpec(TimeBoardPosition timeBoardPosition, ScheduleSpec scheduleSpec) {
		TimeBoardSpec timeBoardSpec = timeBoardSpecService.initialize();
		timeBoardSpec.setTimeBoardPosition(timeBoardPosition);
		timeBoardSpec.setTimeKind(scheduleSpec.getTimeKind());
		timeBoardSpec.setHoursQuantity(scheduleSpec.getHoursQuantity());
		timeBoardSpec.setTimeBoardDate(scheduleSpec.getScheduleDate());
		return timeBoardSpec;
	}

	protected void createTimeBoardPosition(TimeBoardPosition timeBoardPosition) {
		timeBoardPositionService.create(timeBoardPosition);
	}

	protected void createTimeBoardSpec(TimeBoardSpec timeBoardSpec) {
		timeBoardSpecService.create(timeBoardSpec);
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected List<ScheduleSpec> getScheduleSpecs(ScheduleHead scheduleHead, Date dateFrom, Date dateTill) {
		return OrmTemplate.getInstance().findByNamedQueryAndNamedParam(GET_SCHEDULE_SPECS_EJBQL_QUERY_NAME,
				new String[] {"scheduleHead", "dateFrom", "dateTill"}, new Object[] {scheduleHead, dateFrom, dateTill});  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	protected List<PositionFromStaffListResult> loadPositionsFromStaffList(StaffList staffList, Date beginDate, Date endDate) {
		return OrmTemplate.getInstance().findByNamedQueryAndNamedParam(LOAD_POSITIONS_FROM_STAFF_LIST_EJBQL_QUERY_NAME,
				new String[] {"staffList", "beginDate", "endDate"}, new Object[] {staffList, beginDate, endDate}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ResultTransformer<PositionFromStaffListResult>() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
			 */
			public PositionFromStaffListResult transformTuple(Object[] tuple, String[] aliases) {
				return new PositionFromStaffListResult((PositionFill) tuple[0], (StaffListUnit) tuple[1], (ScheduleHead) tuple[2], (Date) tuple[3], (Date) tuple[4], (Date) tuple[5], (Date) tuple[6]);
			}
		});
	}

	private class PositionFromStaffListResult {
		PositionFill positionFill;
		StaffListUnit staffListUnit;
		ScheduleHead scheduleHead;
		Date staffListPositionBeginDate;
		Date staffListPositionEndDate;
		Date positionFillBeginDate; 
		Date positionFillEndDate;

		public PositionFromStaffListResult(PositionFill positionFill, StaffListUnit staffListUnit, ScheduleHead scheduleHead, Date staffListPositionBeginDate, Date staffListPositionEndDate, Date positionFillBeginDate, Date positionFillEndDate) {
			this.positionFill = positionFill;
			this.staffListUnit = staffListUnit;
			this.scheduleHead = scheduleHead;
			this.staffListPositionBeginDate = staffListPositionBeginDate;
			this.staffListPositionEndDate = staffListPositionEndDate;
			this.positionFillBeginDate = positionFillBeginDate;
			this.positionFillEndDate = positionFillEndDate;
		}
	}

}
