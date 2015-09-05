/**
 * EntityAuditForm.java
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
import java.util.EnumSet;
import java.util.List;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.DatabaseAuditType;
import com.mg.framework.api.orm.MatchMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.DatabaseAudit;
import com.mg.merp.core.model.DatabaseAuditDetail;

/**
 * Контроллер формы аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityAuditForm.java,v 1.1 2007/10/19 06:50:09 safonov Exp $
 */
public class EntityAuditForm extends AbstractForm {
	private DefaultTableController entityAudit;
	private DefaultTableController entityAuditDetail;
	private List<DatabaseAudit> auditList;
	private List<DatabaseAuditDetail> auditDetailList;
	private Date eventDateFrom;
	private Date eventDateTill;
	private boolean auditTypeCreate = true;
	private boolean auditTypeModify = true;
	private boolean auditTypeRemove = true;
	private String userName;

	public EntityAuditForm() {
		super();
		entityAudit = new DefaultTableController(new EntityAuditTableModel());
		entityAuditDetail = new DefaultTableController(new EntityAuditDetailTableModel());
	}

	private class EntityAuditTableModel extends DefaultEntityListTableModel<DatabaseAudit> {

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			setEntityList(auditList);
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.DefaultEntityListTableModel#setSelectedRows(int[])
		 */
		@Override
		public void setSelectedRows(int[] rows) {
			super.setSelectedRows(rows);
			loadEntityAuditDetail(getSelectedEntities());
		}
		
	}
	
	private class EntityAuditDetailTableModel extends DefaultEntityListTableModel<DatabaseAuditDetail> {

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			setEntityList(auditDetailList);
			fireModelChange();
		}
		
	}
	
	private void loadEntityAudit() {
		Criteria criteria = OrmTemplate.createCriteria(DatabaseAudit.class);
		if (eventDateFrom != null)
			criteria.add(Restrictions.ge("EventDateTime", eventDateFrom));
		if (eventDateTill != null)
			criteria.add(Restrictions.le("EventDateTime", eventDateTill));
		if (!StringUtils.stringNullOrEmpty(userName))
			criteria.add(Restrictions.like("UserName", userName, MatchMode.START));
		EnumSet<DatabaseAuditType> auditTypeSet = EnumSet.allOf(DatabaseAuditType.class);
		if (!auditTypeCreate)
			auditTypeSet.remove(DatabaseAuditType.CREATE);
		if (!auditTypeModify)
			auditTypeSet.remove(DatabaseAuditType.MODIFY);
		if (!auditTypeRemove)
			auditTypeSet.remove(DatabaseAuditType.REMOVE);
		if (!auditTypeSet.isEmpty())
			criteria.add(Restrictions.in("AuditType", auditTypeSet));
		auditList = OrmTemplate.getInstance().findByCriteria(criteria.setMaxResults(1000));
		entityAudit.getModel().load();
	}
	
	private void loadEntityAuditDetail(DatabaseAudit[] auditList) {
		if (auditList.length == 0)
			return;
		auditDetailList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DatabaseAuditDetail.class)
				.add(Restrictions.eq("DatabaseAudit", auditList[0])));
		entityAuditDetail.getModel().load();
	}
	
	protected void onActionRefresh(WidgetEvent event) {
		loadEntityAudit();
		((EntityAuditTableModel) entityAudit.getModel()).fireModelChange();
	}

	protected void onActionCheckClose(WidgetEvent event) {
		close();
	}

	/**
	 * запуск формы
	 */
	public void execute() {
		eventDateFrom = DateTimeUtils.nowDate();
		loadEntityAudit();
		run();
	}
	
}
