/*
 * SubsystemPermissionsForm.java
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
package com.mg.merp.security.support.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.ShuttleController;
import com.mg.framework.support.ui.widget.ShuttleListener;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysModule;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.ModuleAccess;
import com.mg.merp.security.support.Messages;

/**
 * Форма настройки доступа к подсистемам
 *
 * @author Oleg V. Safonov
 * @version $Id: SubsystemPermissionsForm.java,v 1.3 2009/03/17 09:23:44 safonov Exp $
 */
public class SubsystemPermissionsForm extends AbstractForm {
	private ShuttleController subsystems;
	private Map<String, SubsystemItem> subsystemPerms = new HashMap<String, SubsystemItem>();
	private Groups role = null;

	public SubsystemPermissionsForm() {
		subsystems = new ShuttleController();
		subsystems.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				grantPermission(event.getContents());
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				revokePermission(event.getContents());
			}
		});
	}

	class SubsystemItem {
		private Integer subsystemId;
		private String desc;
		private String name;
		private Integer permId;

		private SubsystemItem(Integer subsystemId, String desc, String name, Integer permId) {
			super();
			this.subsystemId = subsystemId;
			this.desc = desc;
			this.name = name;
			this.permId = permId;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format(ServerUtils.getUserLocale(), "%s (%s)", desc, name);
		}

	}

	private void grantPermission(Object[] subsystems) {
		PersistentManager pm = ServerUtils.getPersistentManager();
		for (Object subsystem : subsystems) {
			SubsystemItem item = subsystemPerms.get(subsystem);
			if (item != null) {
				ModuleAccess ma = new ModuleAccess();
				ma.setGroup(role);
				ma.setModule(pm.find(SysModule.class, item.subsystemId));
				pm.persist(ma);
			}
		}
	}

	private void revokePermission(Object[] subsystems) {
		PersistentManager pm = ServerUtils.getPersistentManager();
		for (Object method : subsystems) {
			SubsystemItem item = subsystemPerms.get(method);
			pm.remove(pm.find(ModuleAccess.class, item.permId));
		}
	}

	private void fillSubsystems(Groups role, ShuttleController shuttle) {
		List<SubsystemItem> subsytemsList = JdbcTemplate.getInstance().query("select m.id, m.description, m.name, (select ma.id from sec_module_access ma where (m.id = ma.module_id) and (ma.group_id = ?)) as selected " +
				"from sys_module m order by m.description", new Object[] {role.getId()}, new RowMapper<SubsystemItem>() {

					public SubsystemItem mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new SubsystemItem(rs.getInt(1), rs.getString(2), rs.getString(3), JdbcUtils.getIntegerValue(rs, 4));
					}

		});

		List<String> leadingList = new ArrayList<String>();
		List<String> trailingList = new ArrayList<String>();
		for (SubsystemItem item : subsytemsList) {
			if (item.permId == null)
				leadingList.add(item.toString());
			else
				trailingList.add(item.toString());

			subsystemPerms.put(item.toString(), item);
		}

		shuttle.getModel().setLeadingList(leadingList.toArray(new String[leadingList.size()]));
		shuttle.getModel().setTrailingList(trailingList.toArray(new String[trailingList.size()]));
	}

	/**
	 * запуск формы
	 *
	 * @param role	роль
	 */
	public void execute(Groups role) {
		this.role = role;
		view.setTitle(Messages.getInstance().getMessage(Messages.SUBSYSTEM_PERMISSION_TITLE, new Object[] {role.getName()}));
		fillSubsystems(role, subsystems);
		run(UIUtils.isModalMode());
	}

	/**
	 * Обработчик события "Закрыть форму"
	 *
	 * @param event
	 */
	public void onActionOk(WidgetEvent event) {
		close();
	}

}
