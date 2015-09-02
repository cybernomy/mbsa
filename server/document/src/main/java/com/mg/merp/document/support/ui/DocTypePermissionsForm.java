/*
 * DocTypePermissionsForm.java
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
package com.mg.merp.document.support.ui;

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
import com.mg.merp.document.DocTypeAccessServiceLocal;
import com.mg.merp.document.DocTypePermission;

/**
 * Контроллер формы настройки прав пользователя на типы документов
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocTypePermissionsForm.java,v 1.1 2007/11/20 14:53:03 sharapov Exp $
 */
public class DocTypePermissionsForm extends AbstractForm {
	
	private ShuttleController permissions;
	private Map<String, DocTypePermission> docTypePerms = new HashMap<String, DocTypePermission>();
	private DocTypeAccessServiceLocal docTypeAccessService;
	private Integer docTypeId;
	
	private static String PERMISSIONS_WIDGET_NAME = "permissions"; //$NON-NLS-1$
	
	private static String BUSINESS_COMPONENT_NAME = "merp/document/DocTypeAccess"; //$NON-NLS-1$
	private static String GRANT_METHOD_NAME = "grantPermission"; //$NON-NLS-1$
	private static String REVOKE_METHOD_NAME = "revokePermission"; //$NON-NLS-1$

	
	// default constructor
	public DocTypePermissionsForm() {
		docTypeAccessService = (DocTypeAccessServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DocTypeAccessServiceLocal.SERVICE_NAME);
		
		permissions = new ShuttleController();
		permissions.addShuttleListener(new ShuttleListener() {
			public void shuttleContentsMoved(ShuttleChangeEvent event) {
				grantPermission(event.getContents());
			}

			public void shuttleContentsRemoved(ShuttleChangeEvent event) {
				revokePermission(event.getContents());
			}
		});
	}
	
	/**
	 * Запустить форму настройки прав для типа документа
	 * @param docTypeId - идентификатор типа документа
	 */
	public void execute(Integer docTypeId) {
		this.docTypeId = docTypeId;
		fillPermissions();
		run(true);
	}
		
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		view.getWidget(PERMISSIONS_WIDGET_NAME).setReadOnly(
				!SecurityUtils.tryCheckPermission(new BusinessMethodPermission(BUSINESS_COMPONENT_NAME, GRANT_METHOD_NAME))
				|| !SecurityUtils.tryCheckPermission(new BusinessMethodPermission(BUSINESS_COMPONENT_NAME, REVOKE_METHOD_NAME)));
	}

	private void grantPermission(Object[] permissions) {
		for (Object permission : permissions)
			docTypeAccessService.grantPermission(docTypePerms.get(permission), docTypeId);
	}
	
	private void revokePermission(Object[] permissions) {
		for (Object permission : permissions)
			docTypeAccessService.revokePermission(docTypePerms.get(permission));
	}
	
	private void fillPermissions()  {
		List<DocTypePermission> groups = docTypeAccessService.loadDocTypePermissions(docTypeId);
		List<String> leadingList = new ArrayList<String>();
		List<String> trailingList = new ArrayList<String>();
		
		for (DocTypePermission item : groups) {
			if (item.getPermission() == null || !item.getPermission())
				leadingList.add(item.getRoleName());
			else
				trailingList.add(item.getRoleName());
			docTypePerms.put(item.getRoleName(), item);
		}
		permissions.getModel().setLeadingList(leadingList.toArray(new String[leadingList.size()]));
		permissions.getModel().setTrailingList(trailingList.toArray(new String[trailingList.size()]));
	}
	
	/**
	 * Обработчик события "Закрыть форму"
	 * @param event - событие
	 */
	public void onActionOk(WidgetEvent event) {
		close();
	}
	
}
