/**
 * SystemAuditForm.java
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
package com.mg.merp.core.support.ui;

import java.util.Date;
import java.util.List;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.MatchMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysAudit;

/**
 * Контроллер формы аудита системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemAuditForm.java,v 1.1 2007/10/19 06:50:09 safonov Exp $
 */
public class SystemAuditForm extends AbstractForm {
	private DefaultTableController systemAudit;
	private List<SysAudit> auditList;
	private Date eventDateFrom;
	private Date eventDateTill;
	private String userName;
	
	public SystemAuditForm() {
		systemAudit = new DefaultTableController(new SystemAuditTableModel());
	}
	
	private class SystemAuditTableModel extends DefaultEntityListTableModel<SysAudit> {

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			setEntityList(auditList);
		}
		
	}

	private void loadSystemAudit() {
		Criteria criteria = OrmTemplate.createCriteria(SysAudit.class);
		if (eventDateFrom != null)
			criteria.add(Restrictions.ge("EventDateTime", eventDateFrom));
		if (eventDateTill != null)
			criteria.add(Restrictions.le("EventDateTime", eventDateTill));
		if (!StringUtils.stringNullOrEmpty(userName))
			criteria.add(Restrictions.like("UserName", userName, MatchMode.START));
		auditList = OrmTemplate.getInstance().findByCriteria(criteria.setMaxResults(1000));
		systemAudit.getModel().load();		
	}
	
	protected void onActionRefresh(WidgetEvent event) {
		loadSystemAudit();
		((SystemAuditTableModel) systemAudit.getModel()).fireModelChange();
	}

	protected void onActionCheckClose(WidgetEvent event) {
		close();
	}

	/**
	 * запуск формы
	 */
	public void execute() {
		eventDateFrom = DateTimeUtils.nowDate();
		loadSystemAudit();
		run();
	}

}
