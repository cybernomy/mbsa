/*
 * ShowComponentBrowse.java
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
package com.mg.framework.support.ui;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.SecurityUtils;

/**
 * Обработчик команды меню для показа формы списка бизнес-компонента, обрабатывает два параметра:
 * <code>ServiceName</code> - наименование бизнес-компонента, <code>FormName</code> - наименование
 * формы списка
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShowComponentBrowse.java,v 1.5 2006/08/31 08:59:49 safonov Exp $
 */
public class ShowComponentBrowse implements MenuCommand, Serializable {
	protected String serviceName;
	protected String formName = null;

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#init(java.lang.Object...)
	 */
	public void init(Map<String, String> params) {
		if ((params.size() == 0))
			throw new IllegalArgumentException();
		
		serviceName = params.get("ServiceName");
		formName = params.get("FormName");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#execute()
	 */
	public void execute() throws Exception {
		SecurityUtils.checkPermission(new BusinessMethodPermission(serviceName, BusinessMethodPermission.BROWSE_METHOD));
		DataBusinessObjectService service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(serviceName);
		ApplicationDictionaryLocator.locate().getBrowseForm(service, formName).run();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#isPermitted()
	 */
	public boolean isPermitted() {
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(serviceName, BusinessMethodPermission.BROWSE_METHOD));
	}

}
