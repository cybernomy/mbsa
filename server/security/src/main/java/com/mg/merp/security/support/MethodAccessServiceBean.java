/*
 * MethodAccessServiceBean.java
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

package com.mg.merp.security.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysMethod;
import com.mg.merp.security.MethodAccessServiceLocal;
import com.mg.merp.security.model.Groups;
import com.mg.merp.security.model.MethodAccess;

/**
 * Реализация бизнес-компонента "Права на методы бизнес-компонентов"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: MethodAccessServiceBean.java,v 1.5 2009/03/17 09:21:19 safonov Exp $
 */
@Stateless(name="merp/security/MethodAccessService") //$NON-NLS-1$
public class MethodAccessServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<MethodAccess, Integer> implements MethodAccessServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.security.MethodAccessServiceLocal#setPermissionForSubsystemBusinessObjects(java.lang.Integer, com.mg.merp.security.model.Groups, boolean, java.util.List)
	 */
	public void setPermissionForSubsystemBusinessObjects(Integer subSystemId, Groups group, boolean isPermit, List<String> methodNames) {
		doSetPermissionForSubsystemBusinessObjects(subSystemId, group, isPermit, methodNames);
	}

	/**
	 * Установить права для всех бизнес-компонентов модуля
	 * @param subSystemId - идентификатор модуля(подсистемы)
	 * @param group - группа пользователей
	 * @param isPermit - <code>true</code> - дать права; <code>false</code> - отобрать права
	 * @param methodNames - список имён методов
	 */
	protected void doSetPermissionForSubsystemBusinessObjects(Integer subSystemId, Groups group, boolean isPermit, List<String> methodNames) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String queryText = new StringBuilder()
		.append("select m.id method_id, a.id access_id ") //$NON-NLS-1$
		.append("from sys_method m ") //$NON-NLS-1$
		.append("left join sys_class c on c.id = m.class_id ") //$NON-NLS-1$
		.append("left join sec_method_access a on (a.method_id = m.id) and (a.group_id = ?) ") //$NON-NLS-1$
		.append("where (m.corba_name = ?) and (c.module_id = ?)") //$NON-NLS-1$
		.toString();
		for (String methodName : methodNames) {
			List<MethodData> methodPermissions = jdbcTemplate.query(queryText, new Object[] {group.getId(), methodName, subSystemId}, new RowMapper<MethodData>() {

				public MethodData mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new MethodData(rs.getInt(1), JdbcUtils.getIntegerValue(rs, 2));
				}
			});
			for (MethodData methodData : methodPermissions) {
				if(methodData.permissionId == null) {
					if(isPermit)
						create(initMethodAccess(group, methodData.methodId, isPermit));
				} else {
					MethodAccess methodAccess = load(methodData.permissionId);
					if(methodAccess != null) {
						methodAccess.setPermission(isPermit);
						store(methodAccess);
					}

				}
			}
		}
		//ServerUtils.getPersistentManager().flush();
	}

	private MethodAccess initMethodAccess(Groups group, Integer methodId, boolean permission) {
		MethodAccess methodAccess = new MethodAccess();
		methodAccess.setGroup(group);
		methodAccess.setMethod(ServerUtils.getPersistentManager().find(SysMethod.class, methodId));
		methodAccess.setPermission(permission);
		return methodAccess;
	}

	class MethodData {
		Integer methodId;
		Integer permissionId;

		public MethodData(Integer methodId, Integer permId) {
			this.methodId = methodId;
			this.permissionId = permId;
		}
	}

}
