/*
 * DocTypeSearchForm.java
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
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocTypeServiceLocal;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocumentKind;

/**
 * Контроллер формы поиска сущностей "Типы документа"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocTypeSearchForm.java,v 1.4 2009/03/04 15:44:46 safonov Exp $
 */
public class DocTypeSearchForm extends AbstractSearchForm {

	private static final String LOAD_DOCTYPE_EJBQL = "select distinct %s from DocType as dt join dt.SetOfDocTypeRights dtr %s where (dtr.Rights = 1) and (dtr.SecGroups.Id in (%s)) %s order by dt.Code"; //$NON-NLS-1$
	private DocTypeServiceLocal docTypeService = (DocTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/document/DocType"); //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Integer[] selectedIds;
	private DefaultTableController docTypeList;
	protected Integer docSection; 
	protected DocumentKind docKind;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		docTypeList = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {			

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				StringBuilder whereText = new StringBuilder();
				StringBuilder joinText = new StringBuilder();
				paramsName.clear();
				paramsValue.clear();	
				if (docSection != null) {
					joinText.append(" join dt.SetOfDocTypeDocSectionLink dtl "); //$NON-NLS-1$
					whereText.append(" and dtl.DocType = dt and dtl.DocSection = :docSection and dtl.Kind = :docKind"); //$NON-NLS-1$
					paramsName.add("docSection"); //$NON-NLS-1$
					paramsValue.add(new DocSection(docSection));
					paramsName.add("docKind"); //$NON-NLS-1$
					paramsValue.add(docKind);					
				}				
				return String.format(LOAD_DOCTYPE_EJBQL, fieldsList, joinText.toString(), ServerUtils.getUserProfile().getGroupsCommaText(), whereText.toString());
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();								
				result.add(new TableEJBQLFieldDef(DocType.class, "Id", "dt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocType.class, "Code", "dt.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocType.class, "Name", "dt.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, docTypeService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				selectedIds = new Integer[rows.length];
				for (int i = 0; i < rows.length; i++)
					selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
			}
		});
		docTypeList.getModel().load();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		if (selectedIds == null)
			return new PersistentObject[0];

		PersistentManager persistentManager = ServerUtils.getPersistentManager();
		PersistentObject[] result = new PersistentObject[selectedIds.length];
		for (int i = 0; i < selectedIds.length; i++)
			result[i] = persistentManager.find(DocType.class, selectedIds[i]);

		return result;
	}
	
	public void setParam(Integer docSection, DocumentKind docKind) {
		this.docSection = docSection;
		this.docKind = docKind;
	}

}
