/**
 * TaskServiceBean.java
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
package com.mg.merp.scheduler.support;

import java.text.ParseException;

import javax.ejb.Stateless;

import org.quartz.CronExpression;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.scheduler.TaskServiceLocal;
import com.mg.merp.scheduler.model.Task;

/**
 * Бизнес-компонент "Задачи планировщика"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TaskServiceBean.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
@Stateless(name="merp/schedule/TaskService")
public class TaskServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Task, Integer>
		implements TaskServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, final Task entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "BAi")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "CronExpression")); //$NON-NLS-1$
		if (!StringUtils.stringNullOrEmpty(entity.getCronExpression()))
			context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.INVALID_CRON_EXPRESSION), entity, "CronExpression") { //$NON-NLS-1$
				@Override
				protected void doValidate(ValidationContext context) {
					try {
						CronExpression cronExpression = new CronExpression(entity.getCronExpression());
						getLogger().debug("cron expression " + cronExpression.toString());
					} catch (ParseException e) {
						context.getStatus().error(this);
					}
				}
			});
	}

}
