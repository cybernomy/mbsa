/*
 * TimeBoardPositionServiceBean.java
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

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.support.PersonnelrefUtils;
import com.mg.merp.table.TimeBoardPositionServiceLocal;
import com.mg.merp.table.TimeBoardSpecServiceLocal;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;
import com.mg.merp.table.model.TimeBoardHead;
import com.mg.merp.table.model.TimeBoardPosition;
import com.mg.merp.table.model.TimeBoardSpec;

/**
 * Реализация бизнес-компонента "Список сотрудников в табеле" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardPositionServiceBean.java,v 1.5 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name="merp/table/TimeBoardPositionService") //$NON-NLS-1$
public class TimeBoardPositionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TimeBoardPosition, Integer> implements TimeBoardPositionServiceLocal {

	private final static String LOAD_POSITION_FROM_STAFF_LIST_QUERY_NAME = "Table.TimeBoardPositionServiceBean.loadPositionFromStaffList"; //$NON-NLS-1$
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TimeBoardPosition entity) {
		context.addRule(new MandatoryAttribute(entity, "PositionFill")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.table.TimeBoardPositionServiceLocal#addTimeBoardPositions(com.mg.merp.table.model.TimeBoardHead, com.mg.merp.personnelref.model.PositionFill[])
	 */
	@PermitAll
	public void addTimeBoardPositions(Integer timeBoardHeadId, PositionFill[] positionFills) {
		doAddTimeBoardPositions(timeBoardHeadId, positionFills);
	}

	protected void doAddTimeBoardPositions(Integer timeBoardHeadId, PositionFill[] positionFills) {
		if(timeBoardHeadId == null)
			return;
		
		TimeBoardHead timeBoardHead = ServerUtils.getPersistentManager().find(TimeBoardHead.class, timeBoardHeadId);
		TimeBoardPosition[] timeBoardPositions = new TimeBoardPosition[positionFills.length]; 
		for(int i = 0; i < positionFills.length; i++)
			timeBoardPositions[i] = initializeTimeBoardPosition(timeBoardHead, positionFills[i]);
		createTimeBoardPositions(timeBoardPositions);
		
		for(TimeBoardPosition timeBoardPosition : timeBoardPositions)
			createSpecificationBySchedule(timeBoardPosition);
	}

	protected TimeBoardPosition initializeTimeBoardPosition(TimeBoardHead timeBoardHead, PositionFill positionFill) {
		TimeBoardPosition timeBoardPosition = initialize();
		timeBoardPosition.setTimeBoardHead(timeBoardHead);
		timeBoardPosition.setStaffListUnit(positionFill.getSlPositionUnique().getStaffListUnit());
		timeBoardPosition.setPositionFill(positionFill);
		return timeBoardPosition;
	}

	protected void createTimeBoardPositions(TimeBoardPosition[] timeBoardPositions) {
		for (TimeBoardPosition timeBoardPosition : timeBoardPositions) 
			create(timeBoardPosition);
	}
	
	protected void createSpecificationBySchedule(TimeBoardPosition timeBoardPosition) {
		PositionFromStaffListResult positionFromStaffList = loadPositionFromStaffList(timeBoardPosition);
		if(positionFromStaffList.scheduleHead != null) {
			List<ScheduleSpec> scheduleSpecs = getScheduleSpecs(
									positionFromStaffList.scheduleHead,
									PersonnelrefUtils.getMaxDate(positionFromStaffList.calcPeriodBeginDate, positionFromStaffList.positionBeginDate, positionFromStaffList.positionFillBeginDate),
									PersonnelrefUtils.getMinDate(positionFromStaffList.calcPeriodEndDate, positionFromStaffList.positionEndDate, positionFromStaffList.positionFillEndDate));
			
			TimeBoardSpec[] timeBoardSpecs = new TimeBoardSpec[scheduleSpecs.size()];
			for (int i = 0; i < scheduleSpecs.size(); i++)
				timeBoardSpecs[i] = initializeTimeBoardSpecByScheduleSpec(timeBoardPosition, scheduleSpecs.get(i));
			createTimeBoardSpecs(timeBoardSpecs);
		}
	}
	
	protected void createTimeBoardSpecs(TimeBoardSpec[] timeBoardSpecs) {
		TimeBoardSpecServiceLocal timeBoardSpecService = getTimeBoardSpecService();
		for(TimeBoardSpec timeBoardSpec : timeBoardSpecs)
			timeBoardSpecService.create(timeBoardSpec);			 
	}

	protected TimeBoardSpec initializeTimeBoardSpecByScheduleSpec(TimeBoardPosition timeBoardPosition, ScheduleSpec scheduleSpec) {
		TimeBoardSpec timeBoardSpec = new TimeBoardSpec();
		timeBoardSpec.setTimeBoardPosition(timeBoardPosition);
		timeBoardSpec.setTimeKind(scheduleSpec.getTimeKind());
		timeBoardSpec.setHoursQuantity(scheduleSpec.getHoursQuantity());
		timeBoardSpec.setTimeBoardDate(scheduleSpec.getScheduleDate());
		return timeBoardSpec;
	}
	
	protected List<ScheduleSpec> getScheduleSpecs(ScheduleHead scheduleHead, Date beginDate, Date endDate) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ScheduleSpec.class)
					.add(Restrictions.eq("ScheduleHead", scheduleHead)) //$NON-NLS-1$
					.add(Restrictions.between("ScheduleDate", beginDate, endDate))); //$NON-NLS-1$
	}
	
	protected PositionFromStaffListResult loadPositionFromStaffList(TimeBoardPosition timeBoardPosition) {
		List<Object[]> dataList = MiscUtils.convertUncheckedList(Object[].class, OrmTemplate.getInstance().findByNamedQueryAndNamedParam(LOAD_POSITION_FROM_STAFF_LIST_QUERY_NAME, "timeBoardPosition", timeBoardPosition)); //$NON-NLS-1$
		if(!dataList.isEmpty()) {
			Object[] data = dataList.get(0);
			return new PositionFromStaffListResult(
							(ScheduleHead) data[0],
							(Date) data[1],
							(Date) data[2],
							(Date) data[3],
							(Date) data[4],
							(Date) data[5],
							(Date) data[6]);
		}
		else
			return new PositionFromStaffListResult(); 
	}
	
	private TimeBoardSpecServiceLocal getTimeBoardSpecService() {
		return (TimeBoardSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TimeBoardSpecServiceLocal.SERVICE_NAME);
	}
	
	private class PositionFromStaffListResult {
		ScheduleHead scheduleHead;
		Date calcPeriodBeginDate;
		Date calcPeriodEndDate;
		Date positionBeginDate;
		Date positionEndDate;
		Date positionFillBeginDate;
		Date positionFillEndDate;
		
		public PositionFromStaffListResult() {
		}
		
		public PositionFromStaffListResult(ScheduleHead scheduleHead, Date calcPeriodBeginDate, Date calcPeriodEndDate, Date positionBeginDate, Date positionEndDate, Date positionFillBeginDate, Date positionFillEndDate) {
			this.scheduleHead = scheduleHead;
			this.calcPeriodBeginDate = calcPeriodBeginDate;
			this.calcPeriodEndDate = calcPeriodEndDate;
			this.positionBeginDate = positionBeginDate;
			this.positionEndDate = positionEndDate;
			this.positionFillBeginDate = positionFillBeginDate;
			this.positionFillEndDate = positionFillEndDate;
		}
	}

}
