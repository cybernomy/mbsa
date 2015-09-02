/*
 * NaturalPersonBr.java
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
package com.mg.merp.reference.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.NaturalPersonServiceLocal;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Браузер физических лиц
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: NaturalPersonBr.java,v 1.8 2008/05/12 07:23:22 alikaev Exp $
 */
public class NaturalPersonBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from NaturalPerson np %s %s order by np.Surname, np.Name, np.Patronymic  ";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	/**
	 * 
	 */
	public NaturalPersonBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
		treeUIProperties.put("FolderType", NaturalPersonServiceLocal.FOLDER_PART);
		tree.setParentPropertyName("Folder.Id");
		restrictionFormName = "com/mg/merp/reference/resources/NaturalPersonRest.mfd.xml";
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {		
		uiProperties.put("Folder", master);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(NaturalPersonServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Id", "np.Id", true));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Surname", "np.Surname", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Name", "np.Name", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Patronymic", "np.Patronymic", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "ActDate", "np.ActDate", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Sex", "np.Sex", false));				
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "BornDate", "np.BornDate", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Inn", "np.Inn", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "AdditionalInfo", "np.AdditionalInfo", false));
				result.add(new TableEJBQLFieldDef(NaturalPerson.class, "Users", "us.Name", "left join np.Users as us", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}
			
		};
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		NaturalPersonRest restForm = (NaturalPersonRest) getRestrictionForm();		
		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "np.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Surname", restForm.getSurName(), "surName", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Name", restForm.getName(), "name", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Patronymic", restForm.getPatronymic(), "patronymic", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("np.BornDate", restForm.getBornDate(), "bornDate", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("np.ActDate", restForm.getActDateFrom(), restForm.getActDateTill(), "actDateFrom", "actDateTill", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "np.Id", restForm.getAddinFieldsRestriction(), false));

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	

		
	}	

}
