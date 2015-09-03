/*
 * ScheduleServiceBean.java
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

package com.mg.merp.lbschedule.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.Schedule;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;

/**
 * Реализация бизнес-компонента "Графики исполнения обязательств" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleServiceBean.java,v 1.4 2007/09/10 13:27:50 sharapov Exp $
 */
@Stateless(name="merp/lbschedule/ScheduleService") //$NON-NLS-1$
public class ScheduleServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Schedule, Integer> implements ScheduleServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.lbschedule.ScheduleServiceLocal#getDocHead(java.lang.Integer)
	 */
	@PermitAll
	public DocHead getDocHead(Integer scheduleId) {
		return doGetDocHead(scheduleId);
	}
	
	/**
	 * Получить документ на основании которого создан ГИО
	 * @param scheduleId - идентификатор графика исполнения обязательства
	 * @return документ на основании которого создан ГИО, или <code>null</code> если не найден
	 */
	protected DocHead doGetDocHead(Integer scheduleId) {
		if(scheduleId == null)
			return null;
		else
			return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ScheduleDocHeadLink.class)
					.setProjection(Projections.property("DocHead")) //$NON-NLS-1$
					.add(Restrictions.eq("Schedule.Id", scheduleId))); //$NON-NLS-1$
	}

}
