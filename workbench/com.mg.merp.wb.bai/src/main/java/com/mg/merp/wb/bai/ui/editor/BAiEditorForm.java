/* BAiEditorForm.java
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
package com.mg.merp.wb.bai.ui.editor;

import java.rmi.ConnectException;

import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.wb.bai.BAiPlugin;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.editor.StandartEditorForm;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;

/**
 * Форма редактирования BAi
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: BAiEditorForm.java,v 1.3 2007/11/04 14:33:36 safonov Exp $
 */
public class BAiEditorForm extends
		StandartEditorForm<BAiEditorPage, Repository> {

	public static final String EDITOR_ID = "com.mg.merp.wb.bai.BAiEditor";

	private static final String FORM_PAGE = "bai.form.edit.page";

	private static final String BAI_EDIT_ERROR = "bai.edit.error";

	private static final String BAI_ADD_ERROR = "bai.add.error";

	private static final String BAI_CREATE_ERROR = "bai.create.error";
	
	@Override
	protected BAiEditorPage getEditorPage() {
		return new BAiEditorPage(this);
	}

	@Override
	protected void pagePostCreating() {
		setPageText(0, BAiPlugin.getDefault().getString(FORM_PAGE));
	}

	@Override
	protected void hookBusinessObjectActException(boolean isNew, RuntimeException e) {
		String str = BAI_EDIT_ERROR;
		if (isNew)
			str = BAI_ADD_ERROR;
		Dialogs.openError(BAiPlugin.getDefault().getString(str), e.getLocalizedMessage(),
				BAiPlugin.ID, e);
	}

	@Override
	protected void hookServerConnectException(boolean isNew, ConnectException e) {
		String str = BAI_EDIT_ERROR;
		if (isNew)
			str = BAI_CREATE_ERROR;
		Dialogs.openError(BAiPlugin.getDefault().getString(str), BAiPlugin
						.getDefault().getString(UiPlugin.CHECK_SERVER),
						BAiPlugin.ID, e);
	}

	@Override
	protected BusinessObjectPlugin<Repository> getBusinessObjectTool() {
		return BAiPlugin.getDefault();
	}

}
