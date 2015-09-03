/*
 * PlanningLevelServiceBean.java
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

package com.mg.merp.mfreference.support;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.mfreference.PlanningLevelBucketServiceLocal;
import com.mg.merp.mfreference.PlanningLevelServiceLocal;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.PlanningLevelBucket;

/**
 * Реализация бизнес-компонента "Уровни планирования" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PlanningLevelServiceBean.java,v 1.6 2007/07/30 10:24:41 safonov Exp $
 */
@Stateless(name="merp/mfreference/PlanningLevelService") //$NON-NLS-1$
public class PlanningLevelServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PlanningLevel, Integer> implements PlanningLevelServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PlanningLevel entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Description")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.mfreference.PlanningLevelServiceLocal#generateBuckets(com.mg.merp.mfreference.model.PlanningLevel, java.lang.Integer, java.lang.Integer)
	 */
	public void generateBuckets(PlanningLevel planningLevel, Integer bucketLength, Integer bucketNumber) {
		internalGenerateBuckets(planningLevel, bucketLength, bucketNumber);
	}

	/**
	 * Создать периоды уровня планирования
	 * @param planningLevel - уровень планирования
	 * @param bucketLength - длина периода в днях
	 * @param bucketNumber - количество периодов
	 */
	private void internalGenerateBuckets(PlanningLevel planningLevel, Integer bucketLength, Integer bucketNumber) {
		if((planningLevel != null) && (bucketLength != null && bucketLength > 0) && (bucketNumber != null && bucketNumber > 0)) {
			PlanningLevelBucket lastBucket = getLastBucket(planningLevel);
			java.util.Date bucketEndDate;
			java.util.Date bucketStartDate;
			java.lang.Short bucketOffset;
			if(lastBucket != null) {
				bucketEndDate = lastBucket.getEndDate();
				bucketOffset = lastBucket.getBucketOffset();
				for(int i = 1; i <= bucketNumber; i++) {
					bucketStartDate = DateTimeUtils.incDay(bucketEndDate, 1);
					bucketEndDate = DateTimeUtils.incDay(bucketEndDate, bucketLength);
					PlanningLevelBucket bucket = initializeBucket(planningLevel, bucketStartDate, bucketEndDate, (short) (i + bucketOffset));
					storeBucket(bucket);
				}
			}
		}
	}

	/**
	 * Получить последний "бакет" уровня планирования
	 * @param planningLevel - уровень планирования
	 * @return "бакет" с максимальным порядковым номером
	 */
	private PlanningLevelBucket getLastBucket(PlanningLevel planningLevel) {
		List<PlanningLevelBucket> lastBuckets = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PlanningLevelBucket.class)
				.addOrder(Order.desc("BucketOffset")) //$NON-NLS-1$
				.add(Restrictions.eq("PlanningLevel", planningLevel))); //$NON-NLS-1$

		if(lastBuckets != null && !lastBuckets.isEmpty())
			return lastBuckets.get(0);
		else
			return null;
	}

	/**
	 * Инициализировать "бакет"
	 * @param planningLevel  - уровень планирования
	 * @param startDate - начальная дата "бакета"
	 * @param endDate - конечная дата "бакета"
	 * @param bucketOffset - порядковый номер "бакета"
	 * @return сущность "бакет"
	 */
	private PlanningLevelBucket initializeBucket(PlanningLevel planningLevel, Date startDate, Date endDate, short bucketOffset) {
		PlanningLevelBucket bucket = getPlanningLevelBucketService().initialize();
		bucket.setPlanningLevel(planningLevel);
		bucket.setStartDate(startDate);
		bucket.setEndDate(endDate);
		bucket.setBucketOffset(bucketOffset);
		return bucket;
	}

	/**
	 * Поместить "бакет" в перманентное хранилище
	 * @param bucket - сущность "бакет"
	 */
	private void storeBucket(PlanningLevelBucket bucket) {
		if(bucket != null)
			ServerUtils.getPersistentManager().persist(bucket);
	}

	private PlanningLevelBucketServiceLocal getPlanningLevelBucketService() {
		return (PlanningLevelBucketServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/PlanningLevelBucket");  //$NON-NLS-1$
	}

}
