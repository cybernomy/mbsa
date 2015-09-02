/*
 * TableHelper.java
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
package com.mg.merp.salary.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.table.model.TimeBoardPosition;
import com.mg.merp.table.model.TimeBoardSpec;

/**
 * ����� ��������������� ������� ������ � ��������� ����� � �������
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id$
 */
public class TableHelper {
	
	private final static String IS_SCHEDULE_EXISTS_QUERY_NAME = "Salary.TableHelper.isScheduleExists"; //$NON-NLS-1$
	private final static String DAYS_QUANTITY_FROM_SCHEDULE_QUERY_NAME = "Salary.TableHelper.daysQuantityFromSchedule"; //$NON-NLS-1$
	private final static String HOURS_QUANTITY_FROM_SCHEDULE_QUERY_NAME = "Salary.TableHelper.hoursQuantityFromSchedule"; //$NON-NLS-1$
	
	
	/**
	 * �������� ���������� ���� �� ��������� ������, ��� ������� � ������� ����� ���� ������ � ��������� ����� �������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param staffList - ������� ����������
	 * @return ���������� ���� �� ��������� ������, ��� ������� � ������� ����� ���� ������ � ��������� ����� �������
	 */
	public static BigDecimal getDaysFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate, StaffList staffList) {
		return getDaysQuantityFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
	}
	
	/**
	 * �������� ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param staffList - ������� ����������
	 * @return ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 */
	public static BigDecimal getHoursFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate, StaffList staffList) {
		return getHoursQuantityFromSchedule(timeKindCode, positionFill, beginDate, endDate, staffList);
	}
	
	/**
	 * �������� ���������� ���� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ���� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 */
	public static BigDecimal getDaysFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return getDaysOrHoursFromTimeBoard(true, timeKindCode, positionFill, beginDate, endDate);
	}
	
	/**
	 * �������� ���������� ����� � ��������� ����� ������� �� ������, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ����� � ��������� ����� ������� �� ������, �� ��������� ������. ��� ����� ������� � ������ �� ���� ������ 0.
	 */
	public static BigDecimal getHoursFromTimeBoard(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		return getDaysOrHoursFromTimeBoard(false, timeKindCode, positionFill, beginDate, endDate);
	}
	
	/**
	 * ���������� true, ���� ��� ���������� ����� ������ ����� �� ��������� ����
	 * @param positionFill - ���������� ���������
	 * @param actualDate - ����������� ����
	 * @param staffList - ������� ����������
	 * @return true, ���� ��� ���������� ����� ������ ����� �� ��������� ����
	 */
	public static boolean isScheduleExists(PositionFill positionFill, Date actualDate, StaffList staffList) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("staffList", staffList); //$NON-NLS-1$
		queryMap.put("positionFill", positionFill); //$NON-NLS-1$
		queryMap.put("actualDate", actualDate); //$NON-NLS-1$
		
		List<Integer> scheduleHeadIds = MiscUtils.convertUncheckedList(Integer.class, OrmTemplate.getInstance().findByNamedQueryAndNamedParam(IS_SCHEDULE_EXISTS_QUERY_NAME, queryMap));
				
		if(scheduleHeadIds.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * ���������� true, ���� �� ��������� ���� ��������� ���� � ������
	 * @param positionFill - ���������� ���������
	 * @param actualDate - ���������� ���������
	 * @return true, ���� �� ��������� ���� ��������� ���� � ������
	 */
	public static boolean isTimeBoardExists(PositionFill positionFill, Date actualDate) {
		Integer timeBoardPositionsCount = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(TimeBoardPosition.class)
												.setProjection(Projections.count("Id")) //$NON-NLS-1$
												.createAlias("TimeBoardHead", "tbh", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
												.createAlias("tbh.CalcPeriod", "cp", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
												.add(Restrictions.eq("PositionFill", positionFill)) //$NON-NLS-1$
												.add(Restrictions.and(Restrictions.le("cp.BeginDate", actualDate), Restrictions.ge("cp.EndDate", actualDate)))); //$NON-NLS-1$ //$NON-NLS-2$
		 
		if(timeBoardPositionsCount == null || timeBoardPositionsCount == 0)
			 return false;
		 else
			 return true;
	}
		
	/**
	 * �������� ���������� ���� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param staffList - ������� ����������
	 * @return ���������� ���� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 */
	private static BigDecimal getDaysQuantityFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate, StaffList staffList) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("timeKindCode", timeKindCode); //$NON-NLS-1$
		queryMap.put("positionFill", positionFill); //$NON-NLS-1$
		queryMap.put("beginDate", beginDate); //$NON-NLS-1$
		queryMap.put("endDate", endDate); //$NON-NLS-1$
		queryMap.put("staffList", staffList); //$NON-NLS-1$

		List<Long> daysFromScheduleQuantity = MiscUtils.convertUncheckedList(Long.class, OrmTemplate.getInstance().findByNamedQueryAndNamedParam(DAYS_QUANTITY_FROM_SCHEDULE_QUERY_NAME, queryMap));
  				
		if(!daysFromScheduleQuantity.isEmpty())
			return new BigDecimal(daysFromScheduleQuantity.get(0).longValue());
		else
			return BigDecimal.ZERO;
	}
	
	/**
	 * �������� ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @param staffList - ������� ����������
	 * @return ���������� ����� � ��������� ����� ������� �� ������� �����, �� ��������� ������
	 */
	private static BigDecimal getHoursQuantityFromSchedule(String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate, StaffList staffList) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("timeKindCode", timeKindCode); //$NON-NLS-1$
		queryMap.put("positionFill", positionFill); //$NON-NLS-1$
		queryMap.put("beginDate", beginDate); //$NON-NLS-1$
		queryMap.put("endDate", endDate); //$NON-NLS-1$
		queryMap.put("staffList", staffList); //$NON-NLS-1$

		List<BigDecimal> hoursFromScheduleQuantity = MiscUtils.convertUncheckedList(BigDecimal.class, OrmTemplate.getInstance().findByNamedQueryAndNamedParam(HOURS_QUANTITY_FROM_SCHEDULE_QUERY_NAME, queryMap));
  		
		if(!hoursFromScheduleQuantity.isEmpty())
			return hoursFromScheduleQuantity.get(0);
		else
			return BigDecimal.ZERO;
	}
	
	/**
	 * �������� ���������� ����/����� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 * @param isDaysInfo - ������� ������ ����������: true - ����; false - �����
	 * @param timeKindCode - ��� ���� �������
	 * @param positionFill - ���������� ���������
	 * @param beginDate - ���� ������ �������
	 * @param endDate - ���� ��������� �������
	 * @return ���������� ����/����� �� ��������� ������, ��� ������� � ������ ���� ������ � ��������� ����� �������
	 */
	private static BigDecimal getDaysOrHoursFromTimeBoard(boolean isDaysInfo, String timeKindCode, PositionFill positionFill, Date beginDate, Date endDate) {
		Criteria criteria = OrmTemplate.createCriteria(TimeBoardSpec.class)
					.createAlias("TimeBoardPosition", "tbp", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.createAlias("TimeKind", "tk", JoinType.INNER_JOIN) //$NON-NLS-1$ //$NON-NLS-2$
					.add(Restrictions.between("TimeBoardDate", beginDate, endDate)) //$NON-NLS-1$
					.add(Restrictions.eq("tk.Code", timeKindCode)) //$NON-NLS-1$
					.add(Restrictions.eq("tbp.PositionFill", positionFill)); //$NON-NLS-1$
		
		if(isDaysInfo)
			criteria.setProjection(Projections.count("Id")); //$NON-NLS-1$
		else
			criteria.setProjection(Projections.sum("HoursQuantity")); //$NON-NLS-1$
		
		Object result = OrmTemplate.getInstance().findUniqueByCriteria(criteria);

		if(isDaysInfo)
			return result == null ? BigDecimal.ZERO : new BigDecimal(((Integer) result).intValue());
		else
			return result == null ? BigDecimal.ZERO : (BigDecimal) result;
	}
			
}
