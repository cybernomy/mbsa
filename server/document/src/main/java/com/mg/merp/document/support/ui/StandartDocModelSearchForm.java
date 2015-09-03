/*
 * StandartDocModelSearchForm.java
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
package com.mg.merp.document.support.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Форма поиска стандартных (унаследованных от {@link com.mg.merp.document.model.DocHeadModel DocHeadModel}) образов документов.
 * 
 * @author Oleg V. Safonov
 * @version $Id: StandartDocModelSearchForm.java,v 1.5 2008/12/18 12:35:40 safonov Exp $
 */
public class StandartDocModelSearchForm extends AbstractSearchForm {
	private final static String LOAD_DOCMODEL_EJBQL = "from %s dhm where (dhm.DocSection = :docSection) and %s"; //$NON-NLS-1$
	private final static String[] fieldList = new String[] {"ModelName"}; //$NON-NLS-1$
	
	private DefaultTableController docModelList;
	private Folder folder = null;
	private DocSection docSection;

	public StandartDocModelSearchForm() {
		docModelList = new DefaultTableController(new DefaultEntityListTableModel<DocHeadModel>() {
			@Override
			protected void doLoad() {
				List<String> paramsName = new ArrayList<String>();
				List<Object> paramsValue = new ArrayList<Object>();
				paramsName.add("docSection");
				paramsValue.add(docSection);
				//получим через сервис имя сущности и используем в запросе для исключения полиморфного запроса
				DocumentPattern<?, ?> pattern = DocumentUtils.getDocumentPatternService(docSection);
				String whereClause = DatabaseUtils.formatEJBQLHierarchyRestriction(folder != null, "dhm.Folder", 0, "folder", folder, paramsName, paramsValue, true);
				setEntityList(MiscUtils.convertUncheckedList(DocHeadModel.class, OrmTemplate.getInstance().findByNamedParam(String.format(LOAD_DOCMODEL_EJBQL, pattern.getEntityClass().getName(), whereClause), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]))), fieldList);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		docModelList.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		return ((DefaultEntityListTableModel<?>) docModelList.getModel()).getSelectedEntities();
	}

	/**
	 * @param docSection The docSection to set.
	 */
	public void setDocSection(DocSection docSection) {
		this.docSection = docSection;
	}

	/**
	 * @param folder The folderId to set.
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

}
