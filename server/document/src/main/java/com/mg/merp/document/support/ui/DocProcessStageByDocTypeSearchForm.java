/*
 * DocProcessStageByDocTypeSearchForm.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocType;

/**
 * Контроллер формы поиска сущностей этапов ДО конкретного типа документа
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocProcessStageByDocTypeSearchForm.java,v 1.1 2008/08/15 06:27:14 sharapov Exp $
 */
public class DocProcessStageByDocTypeSearchForm extends AbstractSearchForm {

	public static final String FORM_NAME = "com.mg.merp.document.DocProcessStageByDocTypeSearchForm"; //$NON-NLS-1$

	protected DefaultTableController table;
	private DocType docType = null;
	private final String INIT_QUERY_TEXT = "select dps from com.mg.merp.docprocess.model.DocProcessStage dps where dps.DocType = :docType"; //$NON-NLS-1$
	private final String[] fieldList = new String[] {"Code", "Name"}; //$NON-NLS-1$ //$NON-NLS-2$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		table = new DefaultTableController(new DefaultEntityListTableModel<DocProcessStage>() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setEntityList(MiscUtils.convertUncheckedList(DocProcessStage.class, OrmTemplate.getInstance().findByNamedParam(INIT_QUERY_TEXT, "docType", docType)), fieldList); //$NON-NLS-1$
			}
		});
		table.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<DocProcessStage>) table.getModel()).getSelectedEntities();
	}

	/**
	 * Установить параметры поиска
	 * @param docType - тип документа
	 */
	public void setSearchParams(DocType docType) {
		this.docType = docType;
	}

}
