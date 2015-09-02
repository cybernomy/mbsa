/* EntityMapperEditorForm.java
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
package com.mg.merp.wb.entitymapper.ui.editor;

import java.rmi.ConnectException;

import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.editor.StandartEditorForm;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;
import com.mg.merp.wb.entitymapper.Activator;

/**
 * Форма редактирования маппинга преобразования классов
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperEditorForm.java,v 1.2 2007/11/04 14:34:24 safonov Exp $
 */
public class EntityMapperEditorForm extends
		StandartEditorForm<EntityMapperEditorPage, EntityTransformerMapping> {

	public static final String EDITOR_ID = "com.mg.merp.wb.entitymapper.EntityMapperEditor";

	private static final String ENTITYMAPPER_FORM_PAGE = "entitymapper.form.edit.page";

	private static final String ENTITYMAPPER_EDIT_ERROR = "entitymapper.edit.error";

	private static final String ENTITYMAPPER_ADD_ERROR = "entitymapper.add.error";

	private static final String ENTITYMAPPER_CREATE_ERROR = "entitymapper.create.error";
	
	@Override
	protected EntityMapperEditorPage getEditorPage() {
		return new EntityMapperEditorPage(this);
	}

	@Override
	protected void pagePostCreating() {
		setPageText(0, Activator.getDefault().getString(ENTITYMAPPER_FORM_PAGE));
	}

	@Override
	protected void hookBusinessObjectActException(boolean isNew, RuntimeException e) {
		String str = ENTITYMAPPER_EDIT_ERROR;
		if (isNew)
			str = ENTITYMAPPER_ADD_ERROR;
		Dialogs.openError(Activator.getDefault().getString(str), e.getLocalizedMessage(), Activator.ID, e);
	}

	@Override
	protected void hookServerConnectException(boolean isNew, ConnectException e) {
		String str = ENTITYMAPPER_EDIT_ERROR;
		if (isNew)
			str = ENTITYMAPPER_CREATE_ERROR;
		Dialogs.openError(Activator.getDefault().getString(str), Activator
						.getDefault().getString(UiPlugin.CHECK_SERVER),
						Activator.ID, e);
	}

	@Override
	protected BusinessObjectPlugin<EntityTransformerMapping> getBusinessObjectTool() {
		return Activator.getDefault();
	}

}
