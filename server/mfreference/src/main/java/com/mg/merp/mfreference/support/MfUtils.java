/*
 * MfUtils.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.mfreference.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.mfreference.BucketRange;
import com.mg.merp.mfreference.CostDetailLineServiceLocal;
import com.mg.merp.mfreference.DayTimeServiceLocal;
import com.mg.merp.mfreference.PlanningLevelBucketServiceLocal;
import com.mg.merp.mfreference.TimeRange;
import com.mg.merp.mfreference.WeekCalendarChangeServiceLocal;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.CostCategories;
import com.mg.merp.mfreference.model.CostDetail;
import com.mg.merp.mfreference.model.CostDetailLine;
import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.model.QuantityRateFlag;
import com.mg.merp.mfreference.model.ScheduleDirection;
import com.mg.merp.mfreference.model.TimeRateFlag;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.Measure;

/**
 * ������� ��� ������� ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MfUtils.java,v 1.6 2007/08/21 15:23:57 safonov Exp $
 */
public class MfUtils {

	/**
	 * ���������� ����� � �������, 10^-3 seconds
	 */
	public final static long TICKS_PER_SECOND = 1000L;
	
	/**
	 * ���������� ����� � ������
	 */
	public final static long TICKS_PER_MIN = 60L * TICKS_PER_SECOND;
	
	/**
	 * ���������� ����� � ����
	 */
	public final static long TICKS_PER_HOUR = 60L * TICKS_PER_MIN;
	
	/**
	 * ���������� ����� � ���
	 */
	public final static long TICKS_PER_DAY = 24L * TICKS_PER_HOUR;

	/**
	 * �� ���
	 */
	public final static Measure HOUR = new Measure();
	
	private static final String TICK_UM_CODE = "TICK";
	
	private final static long LEGACY_DATE_DELTA = 25569L * TICKS_PER_DAY;
	
	private final static List<String> standartTimeCode = new ArrayList<String>();
	
	private static volatile Measure tickUM = null;
	
	private static final String EFF_ON_DATE = "EffOnDate";

	private static final String EFF_OFF_DATE = "EffOffDate";

	static {
		//FIXME use i18n resources and ISO UM codes
		standartTimeCode.add("\u0421\u0415\u041a");
		standartTimeCode.add("SEC");
		standartTimeCode.add("\u041c\u0418\u041d");
		standartTimeCode.add("MIN");
		standartTimeCode.add("\u0427\u0410\u0421");
		standartTimeCode.add("HOUR");
		standartTimeCode.add(TICK_UM_CODE);
		standartTimeCode.add(TICK_UM_CODE);
		
		HOUR.setCode("HOUR");
	}
	
	private static int indexOfStandartTime(Measure timeUM) {
		return standartTimeCode.indexOf(timeUM.getCode().trim().toUpperCase(ServerUtils.getUserLocale()));
	}
	
	private static Measure getTickUM() {
		if (tickUM == null)
			tickUM = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Measure.class)
					.add(Restrictions.eq("UpCode", TICK_UM_CODE)));
		if (tickUM == null)
			throw new IllegalStateException("Tick UM not found");
		return tickUM;
	}
	
	/**
	 * �������������� ����� �� ����� ���������� � ��
	 * 
	 * @param tick		����
	 * @param timeUM	�� �������
	 * @return			�����
	 */
	public static BigDecimal tickToTime(Long tick, Measure timeUM) {
		int idx = indexOfStandartTime(timeUM);
		if (idx != -1)
			return tickToStandartTime(tick, idx);
		else
			return ((MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME))
					.conversion(getTickUM(), timeUM, null, null, new BigDecimal(tick));
	}
	
	private static BigDecimal tickToStandartTime(Long tick, int index) {
		switch (index) {
		case 0:
		case 1:
			return new BigDecimal(tick).divide(new BigDecimal(TICKS_PER_SECOND));
		case 2:
		case 3:
			return new BigDecimal(tick).divide(new BigDecimal(TICKS_PER_MIN));
		case 4:
		case 5:
			return new BigDecimal(tick).divide(new BigDecimal(TICKS_PER_HOUR));
		case 6:
		case 7:
			return new BigDecimal(tick); //ticks
		default:
			throw new IllegalArgumentException();	
		}
	}
	
	/**
	 * �������������� ������� ����������� � �� � ���� ������������
	 * 
	 * @param tick		�����
	 * @param timeUM	�� �������
	 * @return			����
	 */
	public static Long timeToTick(BigDecimal time, Measure timeUM) {
		int idx = indexOfStandartTime(timeUM);
		if (idx != -1)
			return standartTimeToTick(time, idx);
		else
			return ((MeasureConversionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MeasureConversionServiceLocal.LOCAL_SERVICE_NAME))
					.conversion(timeUM, getTickUM(), null, null, time).longValue();
	}
	
	private static Long standartTimeToTick(BigDecimal time, int index) {
		switch (index) {
		case 0:
		case 1:
			return time.multiply(new BigDecimal(TICKS_PER_SECOND)).longValue();
		case 2:
		case 3:
			return time.multiply(new BigDecimal(TICKS_PER_MIN)).longValue();
		case 4:
		case 5:
			return time.multiply(new BigDecimal(TICKS_PER_HOUR)).longValue();
		case 6:
		case 7:
			return time.longValue(); //ticks
		default:
			throw new IllegalArgumentException();	
		}
	}

	/**
	 * �������������� ������� � ���� ������������
	 * 
	 * @param date	�����
	 * @return	����� � �����
	 */
	public static long dateToTick(Date date) {
		return date.getTime() + LEGACY_DATE_DELTA;
	}
	
	/**
	 * �������������� ������� � ����� ������������ � �����
	 * 
	 * @param tick	����� � �����
	 * @return	�����
	 */
	public static Date tickToDate(long tick) {
		return new Date(tick - LEGACY_DATE_DELTA);
	}
	
	/**
	 * ���������� ���������� ���������
	 * 
	 * @param quantityRateFlag	��� ���������� ����������
	 * @param quan				����������
	 * @param timeOper			����� ��������
	 * @param scrapFactor		���������� �����
	 * @param lotQuan			���������� � ������
	 * @return	���������� ���������
	 */
	public static BigDecimal calculateMaterialQuan(QuantityRateFlag quantityRateFlag, BigDecimal quan, BigDecimal timeOper, BigDecimal scrapFactor, BigDecimal lotQuan) {
		switch (quantityRateFlag) {
		case FIXED: return quan.divide(BigDecimal.ONE.subtract(scrapFactor).multiply(lotQuan));
		case TIME: return timeOper.multiply(quan).divide(BigDecimal.ONE.subtract(scrapFactor));
		case UNIT: return quan.divide(BigDecimal.ONE.subtract(scrapFactor));
		default:
			throw new IllegalArgumentException("Invalid quantity rate flag");
		}
	}
	
	private static BigDecimal calculateTimeBOMOper(int bomOperId, Date actualityDate, BigDecimal lotQuan) {
		List<Object[]> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomLabor.class)
				.createAlias("LaborClass", "lc")
				.add(Restrictions.eq("BomRoute.Id", bomOperId))
				.add(Restrictions.le(EFF_ON_DATE, actualityDate))
				.add(Restrictions.ge(EFF_OFF_DATE, actualityDate))
				.setProjection(Projections.projectionList(
						Projections.sum("RunTicksLbr"),
						Projections.property("lc.TimeRateFlag"),
						Projections.groupProperty("lc.TimeRateFlag"))));
		long timeLabor = 0, t1 = 0, t2 = 0;
		for (Object[] item : list) {
			switch ((TimeRateFlag) item[1]) {
			case TIME:
			case RATE:
				timeLabor = (Long) item[0];
				t1 += timeLabor;
				break;
			case FIXED:
				timeLabor = (Long) item[0] / lotQuan.longValue();
				t2 += timeLabor;
				break;
			default:
				throw new IllegalArgumentException("Invalid time rate flag");
			}
		}
		return new BigDecimal(t1 + t2);
	}
	
	/**
	 * ��������� ��������� �������
	 * 
	 * @param weekCalId			��������� ���������
	 * @param baseDateTime		�����
	 * @param runTime			����� ������
	 * @param schedDirection	����������� ������������
	 * @return	��������
	 */
	public static TimeRange getTimes(int weekCalId, long baseDateTime, long runTime, ScheduleDirection schedDirection) {
		return ((DayTimeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DayTimeServiceLocal.SERVICE_NAME)).getTimes(weekCalId, baseDateTime, runTime, schedDirection);
	}
	
	/**
	 * ���������� ���������� ��������� ��� ������� �������
	 * 
	 * @param bomMaterial	�������� � ������� �������
	 * @param actualityDate	����
	 * @param lotQuan		������ ������
	 * @return	���������� ���������
	 */
	public static BigDecimal calculateBOMMaterialQuan(BomMaterial bomMaterial, Date actualityDate, BigDecimal lotQuan) {
		return calculateMaterialQuan(bomMaterial.getQuantityRateFlag(), bomMaterial.getMtlQty()
				, calculateTimeBOMOper(bomMaterial.getBomRoute().getId(), actualityDate, lotQuan), bomMaterial.getScrapFactor(), lotQuan);
	}
	
	/**
	 * ��������� �������� ���������
	 * 
	 * @param weekCalId		��������� ���������
	 * @param searchDate	����
	 * @return	������� ���������
	 */
	public static DayCalendar getDayCalendar(int weekCalId, Date searchDate) {
		return ((WeekCalendarChangeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WeekCalendarChangeServiceLocal.SERVICE_NAME)).getDayCalendar(weekCalId, searchDate);
	}
	
	/**
	 * ������� ����������� ���������
	 * 
	 * @return	�������������� ���������
	 */
	public static CostDetail createCostDetail() {
		CostDetail result = new CostDetail();
		ServerUtils.getPersistentManager().persist(result);
		return result;
	}

	/**
	 * ������� ������ ����������� ���������
	 * 
	 * @param costDetail
	 * @param costCategory
	 * @param cost
	 * @param currency
	 * @return
	 */
	public static CostDetailLine createCostDetailLine(CostDetail costDetail, CostCategories costCategory, BigDecimal cost, Currency currency) {
		CostDetailLine result = new CostDetailLine();
		result.setCostDetail(costDetail);
		result.setCostCategories(costCategory);
		result.setSumma(cost);
		result.setCurrency(currency);
		ServerUtils.getPersistentManager().persist(result);
		return result;
	}
	
	/**
	 * ����������� �������� ������
	 * 
	 * @param planningLevelId	������� ������������
	 * @param bucketOffsetDate	����
	 * @return	�������� ������
	 */
	public static short determineBucketOffset(int planningLevelId, Date bucketOffsetDate) {
		return ((PlanningLevelBucketServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PlanningLevelBucketServiceLocal.SERVICE_NAME)).determineOffset(planningLevelId, bucketOffsetDate);
	}

	/**
	 * ����������� ��������� ������
	 * 
	 * @param planningLevelId	������� ������������
	 * @param bucketOffset		�������� ������
	 * @return
	 */
	public static BucketRange determineBucketRange(int planningLevelId, short bucketOffset) {
		return ((PlanningLevelBucketServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PlanningLevelBucketServiceLocal.SERVICE_NAME)).determineRange(planningLevelId, bucketOffset);
	}

	/**
	 * �������� �������������
	 * 
	 * @param costDetail	�������������
	 */
	public static void clearCostDetailLine(CostDetail costDetail) {
		((CostDetailLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CostDetailLineServiceLocal.SERVICE_NAME))
				.clear(costDetail);
	}

	/**
	 * ��������� ��� �������� �������� ������������, ������������ ����� ����� � ������� EffOffDate � EffOnDate,
	 * ���� ������ ���� �� �����������, �� ���������� � ����������� ���������
	 * 
	 * @param entity	������ ������������
	 */
	public static void adjustEffectiveDate(PersistentObject entity) {
		if (entity.hasAttribute(EFF_OFF_DATE)) {
			Date date = (Date) entity.getAttribute(EFF_OFF_DATE);
			if (date == null || date.compareTo(DateTimeUtils.ZERO_DATE) < 0)
				entity.setAttribute(EFF_OFF_DATE, DateTimeUtils.MAX_DATE);			
		}
		if (entity.hasAttribute(EFF_ON_DATE)) {
			Date date = (Date) entity.getAttribute(EFF_ON_DATE);
			if (date == null || date.compareTo(DateTimeUtils.MAX_DATE) > 0)
				entity.setAttribute(EFF_ON_DATE, DateTimeUtils.ZERO_DATE);			
		}
	}
	
}
