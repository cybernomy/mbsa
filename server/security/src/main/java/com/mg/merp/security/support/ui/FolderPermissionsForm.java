/*
 * FolderPermissionsForm.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.ShuttleController;
import com.mg.framework.support.ui.widget.ShuttleListener;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.security.FolderAccessServiceLocal;
import com.mg.merp.security.FolderPermission;

/**
 * Форма настройки прав пользователя на папки
 * 
 * @author Oleg V. Safonov
 * @version $Id: FolderPermissionsForm.java,v 1.1 2007/02/24 14:23:54 safonov Exp $
 */
public class FolderPermissionsForm extends AbstractForm {
	private ShuttleController permissions;
	private Map<String, FolderPermission> folderPerms = new HashMap<String, FolderPermission>();
	private int folderPart;
	private int folderId;
	private FolderAccessServiceLocal folderAccessService;
	
	public FolderPermissionsForm() {
		permissions = new ShuttleController();
		permissions.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				grantPermission(event.getContents());
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				revokePermission(event.getContents());
			}
		});
		
		folderAccessService = (FolderAccessServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/FolderAccess");
	}

	private void grantPermission(Object[] subsystems) {
		for (Object subsystem : subsystems) {
			FolderPermission item = folderPerms.get(subsystem);
			folderAccessService.grantPermission(item, folderPart, folderId);
		}
	}
	
	private void revokePermission(Object[] subsystems) {
		for (Object method : subsystems) {
			FolderPermission item = folderPerms.get(method);
			folderAccessService.revokePermission(item);
		}
	}

	private void fillPermissions(int folderPart, int folderId, ShuttleController shuttle) {
		List<FolderPermission> groups = folderAccessService.loadFolderPermission(folderPart, folderId);
		
		List<String> leadingList = new ArrayList<String>();
		List<String> trailingList = new ArrayList<String>();
		for (FolderPermission item : groups) {
			if (item.getPermission() == null || !item.getPermission())
				leadingList.add(item.getRoleName());
			else
				trailingList.add(item.getRoleName());
			
			folderPerms.put(item.getRoleName(), item);
		}

		shuttle.getModel().setLeadingList(leadingList.toArray(new String[leadingList.size()]));
		shuttle.getModel().setTrailingList(trailingList.toArray(new String[trailingList.size()]));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		view.getWidget("permissions").setReadOnly(
				!SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/Security/FolderAccess", "grantPermission"))
				|| !SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/Security/FolderAccess", "revokePermission")));
	}

	public void execute(int folderPart, int folderId, String className, String parentProperty) {
		this.folderPart = folderPart;
		this.folderId = folderId;
		fillPermissions(folderPart, folderId, permissions);
		run(true);
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
