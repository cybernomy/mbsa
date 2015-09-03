/*
 * FolderAccessServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.security.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.FolderRights;
import com.mg.merp.security.FolderAccessServiceLocal;
import com.mg.merp.security.FolderPermission;
import com.mg.merp.security.model.Groups;

/**
 * Бизнес-компонент "Права на папки"
 *
 * @author Oleg V. Safonov
 * @version $Id: FolderAccessServiceBean.java,v 1.7 2009/03/17 09:20:14 safonov Exp $
 */
@Stateless(name="merp/security/FolderAccessService")
public class FolderAccessServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<FolderRights, Integer> implements FolderAccessServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.security.FolderAccessServiceLocal#loadFolderPermission(int, int)
	 */
	public List<FolderPermission> loadFolderPermission(int folderPart, int folderId) {
		return JdbcTemplate.getInstance().query("select g.id, g.name, r.rights, r.id" +
				" from sec_groups g" +
				" left join  folder_rights r on (r.group_id = g.id) and (r.folderpart = ?) and (r.folder_id = ?)" +
				" order by g.name", new Object[] {folderPart, folderId}, new RowMapper<FolderPermission>() {

					public FolderPermission mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new FolderPermission(rs.getInt(1), rs.getString(2), rs.getBoolean(3), JdbcUtils.getIntegerValue(rs, 4));
					}

		});
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.security.FolderAccessServiceLocal#grantPermission(com.mg.merp.security.FolderPermission)
	 */
	public void grantPermission(FolderPermission perm, int folderPart, int folderId) {
		if (perm == null) {
			getLogger().info("FolderPermission is null");
			return;
		}

		PersistentManager pm = getPersistentManager();

		if (perm.getPermissionId() != null) {
			FolderRights fr = pm.find(FolderRights.class, perm.getPermissionId());
			fr.setPermission(true);
			pm.merge(fr);
		}
		else {
			FolderRights fr = new FolderRights();
			fr.setFolderId(folderId);
			fr.setFolderPart((short) folderPart);
			fr.setPermission(true);
			fr.setSecGroups(pm.find(Groups.class, perm.getRoleId()));
			pm.persist(fr);

			perm.setPermissionId(fr.getId());
			perm.setPermission(true);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.security.FolderAccessServiceLocal#revokePermission(com.mg.merp.security.FolderPermission)
	 */
	public void revokePermission(FolderPermission perm) {
		if (perm == null) {
			getLogger().info("FolderPermission is null");
			return;
		}

		PersistentManager pm = ServerUtils.getPersistentManager();

		FolderRights fr = pm.find(FolderRights.class, perm.getPermissionId());
		fr.setPermission(false);
		pm.merge(fr);
	}

}
