/*
 * PlanningLevelBucketServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.mfreference.BucketRange;
import com.mg.merp.mfreference.PlanningLevelBucketServiceLocal;
import com.mg.merp.mfreference.model.PlanningLevelBucket;

/**
 * ������-��������� "������ ������� ������������" 
 * 
 * @author leonova
 * @version $Id: PlanningLevelBucketServiceBean.java,v 1.5 2007/07/30 10:24:41 safonov Exp $
 */
@Stateless(name="merp/mfreference/PlanningLevelBucketService")
public class PlanningLevelBucketServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PlanningLevelBucket, Integer> implements PlanningLevelBucketServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.PlanningLevelBucketServiceLocal#nearestBucketStartDate(int, long)
	 */
	@PermitAll
	public Date nearestBucketStartDate(int planningLevel, Date planningDate) {
		OrmTemplate tmpl = OrmTemplate.getInstance();
		//���� � ���������
		PlanningLevelBucket plb = tmpl.findUniqueByCriteria(OrmTemplate.createCriteria(PlanningLevelBucket.class)
				.add(Restrictions.eq("PlanningLevel.Id", planningLevel))
				.add(Restrictions.le("StartDate", planningDate))
				.add(Restrictions.ge("EndDate", planningDate)));
		if (plb != null)
			return plb.getStartDate();
		//���� �� �����, �� ���� ��������� �����
		List<PlanningLevelBucket> list = tmpl.findByCriteria(OrmTemplate.createCriteria(PlanningLevelBucket.class)
				.add(Restrictions.eq("PlanningLevel.Id", planningLevel)));
		//���� �� �����
		if (list.isEmpty())
			return null;
		
		Date result = DateTimeUtils.ZERO_DATE;
		for (PlanningLevelBucket planningLevelBucket : list) {
			Date startDate = planningLevelBucket.getStartDate();
			if (Math.abs(startDate.getTime() - planningDate.getTime()) < Math.abs(result.getTime() - planningDate.getTime()))
				result = startDate;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.PlanningLevelBucketServiceLocal#determineOffset(int, long)
	 */
	@PermitAll
	public short determineOffset(int planningLevelId, Date offsetDate) {
		//��� ��������� ��� ������ ������������ ���� � ������ ���� �����
		Date date = DateTimeUtils.getDayStart(offsetDate);
		PlanningLevelBucket planningLevelBucket = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(PlanningLevelBucket.class)
				.add(Restrictions.eq("PlanningLevel.Id", planningLevelId))
				.add(Restrictions.le("StartDate", date))
				.add(Restrictions.ge("EndDate", date)));
		if (planningLevelBucket == null)
			return -1;
		else
			return planningLevelBucket.getBucketOffset();//((PlanningLevelBucketDomainImpl) getDomain()).determineOffset(planningLevel, offsetDate);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.PlanningLevelBucketServiceLocal#determineRange(int, short)
	 */
	@PermitAll
	public BucketRange determineRange(int planningLevelId, short bucketOffset) {
		//������ ������������ ���� � ������ ���� �����
		PlanningLevelBucket planningLevelBucket = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(PlanningLevelBucket.class)
				.add(Restrictions.eq("PlanningLevel.Id", planningLevelId))
				.add(Restrictions.eq("BucketOffset", bucketOffset)));
		if (planningLevelBucket == null)
			return new BucketRange(new Date(0), new Date(0)); //����� ��� ����������� �� 1970 ��� �� �����
		else
			return new BucketRange(planningLevelBucket.getStartDate(), planningLevelBucket.getEndDate());
	}

}
