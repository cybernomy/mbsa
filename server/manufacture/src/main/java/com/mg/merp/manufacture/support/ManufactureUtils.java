/*
 * ManufactureUtils.java
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
package com.mg.merp.manufacture.support;

import java.math.BigDecimal;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.model.QuantityRateFlag;
import com.mg.merp.mfreference.model.TimeRateFlag;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.reference.model.Measure;

/**
 * Утилиты подсистем производства
 * 
 * @author Oleg V. Safonov
 * @version $Id: ManufactureUtils.java,v 1.2 2007/08/06 12:44:53 safonov Exp $
 */
public class ManufactureUtils {

	/**
	 * вычисление времени выполнения операции ЗНП
	 * 
	 * @param jobMaterial	материал операции
	 * @return	время выполнения
	 */
	public static BigDecimal calculateTimeJobOper(JobMaterial jobMaterial) {
		Measure hour = new Measure();
		hour.setCode("HOUR");
		List<Object[]> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobLabor.class)
				.setProjection(Projections.projectionList(
						Projections.sum("RunTicksLbr"),
						Projections.property("TimeRateFlag"),
						Projections.groupProperty("TimeRateFlag")))
				.add(Restrictions.eq("Oper", jobMaterial.getOper())));
		BigDecimal timeLabor = BigDecimal.ZERO, t1 = BigDecimal.ZERO, t2 = BigDecimal.ZERO;
		for (Object[] item : list) {
			long runTick = (Long) item[0];
			switch ((TimeRateFlag) item[1]) {
			case TIME:
			case RATE:
				timeLabor = MfUtils.tickToTime(runTick, hour);
				t1 = t1.add(timeLabor);
				break;
			case FIXED:
				timeLabor = MfUtils.tickToTime(runTick, hour).divide(jobMaterial.getOper().getJob().getQtyReleased());
				t2 = t2.add(timeLabor);
				break;
			}
		}
		return t1.add(t2);
	}
	
	/**
	 * вычисление количества материала в ЗНП
	 * 
	 * @param jobMaterial	материал
	 * @return	количество
	 */
	public static BigDecimal calculateJobMaterialQuan(JobMaterial jobMaterial) {
		boolean calcLotQuan = jobMaterial.getQuantityRateFlag() == QuantityRateFlag.FIXED;
		BigDecimal lotQuan = BigDecimal.ZERO;
		if (calcLotQuan)
			lotQuan = jobMaterial.getOper().getJob().getQtyReleased();
		return MfUtils.calculateMaterialQuan(jobMaterial.getQuantityRateFlag(), jobMaterial.getMtlQty(), calculateTimeJobOper(jobMaterial), jobMaterial.getScrapFactor(), lotQuan);
	}

	/**
	 * загрузка РС
	 * 
	 * @param jobRoute операция
	 * @return	список РС
	 */
	public static List<JobLabor> loadJobRouteLabor(JobRoute jobRoute) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobLabor.class)
				.add(Restrictions.eq("Oper", jobRoute)));
	}

	/**
	 * загрузка оборудования
	 * 
	 * @param jobRoute	операция
	 * @return	список оборудования
	 */
	public static List<JobMachine> loadJobRouteMachine(JobRoute jobRoute) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobMachine.class)
				.add(Restrictions.eq("Oper", jobRoute)));
	}

	/**
	 * загрузка материалов
	 * 
	 * @param jobRoute	операция
	 * @return	список материалов
	 */
	public static List<JobMaterial> loadJobRouteMaterial(JobRoute jobRoute) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(JobMaterial.class)
				.add(Restrictions.eq("Oper", jobRoute)));
	}

}
