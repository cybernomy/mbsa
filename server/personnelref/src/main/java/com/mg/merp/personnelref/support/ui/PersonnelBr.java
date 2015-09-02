/*
 * PersonnelBr.java
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
package com.mg.merp.personnelref.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.Personnel;
import com.mg.merp.personnelref.model.PersonnelGroup;

/**
 * Браузер основных сведений о сотрудниках
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: PersonnelBr.java,v 1.9 2007/11/08 16:13:05 safonov Exp $
 */
public class PersonnelBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Personnel p %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public PersonnelBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelGroup");
		tree.setParentPropertyName("ParentId");
		restrictionFormName = "com/mg/merp/personnelref/resources/PersonnelRest.mfd.xml";
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {
		uiProperties.put("Group", master);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() {
		List<PersonnelGroup> list = OrmTemplate.getInstance().find(PersonnelGroup.class, String.format("from PersonnelGroup pg where %s order by pg.ParentId, pg.FldName", DatabaseUtils.generateFlatBrowseWhereEJBQL("pg.Id", 5)));
		return PersonnelGroupTreeNode.createTree(list);
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
		PersonnelRest restForm = (PersonnelRest) getRestrictionForm();
		whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "p.Group", 5, "folder", folderEntity, paramsName, paramsValue, true)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Surname", restForm.getSurName(), "surName", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Name", restForm.getName(), "name", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLStringRestriction("np.Patronymic", restForm.getPatronymic(), "patronymic", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("np.BornDate", restForm.getBornDate(), "bornDate", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("p.ActDate", restForm.getActDateFrom(), restForm.getActDateTill(), "actDateFrom", "actDateTill", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("p.TableNumber", restForm.getTableNumberFrom(), restForm.getTableNumberTill(), "tableNumberFrom", "tableNumberTill", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "p.Id", restForm.getAddinFieldsRestriction(), false));

		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);		

	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
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
				result.add(new TableEJBQLFieldDef(Personnel.class, "Id", "p.Id", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "TableNumber", "p.TableNumber", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "Person", "(np.Surname||' '||np.Name||' '||np.Patronymic) as person", "left join p.Person np", false));								
				result.add(new TableEJBQLFieldDef(Personnel.class, "Person.BornDate", "p.Person.BornDate", false));				
				result.add(new TableEJBQLFieldDef(Personnel.class, "Stature", "p.Stature", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MeasureUpCode", "meas.Code", "left join p.MeasureUpCode as meas", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "PensionNumber", "p.PensionNumber", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "InsuredClass", "ic.CCode", "left join p.InsuredClass as ic", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "EducationDegree", "ideg.Code", "left join p.EducationDegree as ideg", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "PensionNumber", "p.LabourContractNumber", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "PensionNumber", "p.LabourContractDate", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "RetireReason", "p.RetireReason", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilReserveCategory", "mrc.Name", "left join p.MilReserveCategory as mrc", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilRank", "mr.Code", "left join p.MilRank as mr", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilRank", "mr.Code", "left join p.MilRank as mr", false));				
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilRankKind", "mrk.Code", "left join p.MilRankKind as mrk", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilValidity", "mv.Code", "left join p.MilValidity as mv", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilIsTakenOff", "p.MilIsTakenOff", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilSpeciality", "p.MilSpeciality", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilCommissariat", "p.MilCommissariat", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilRegPartyNumber", "p.MilRegPartyNumber", false));
				result.add(new TableEJBQLFieldDef(Personnel.class, "MilRegSpecial", "p.MilRegSpecial", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}			

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

		};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#setupFolderPermissions()
	 */
	@Override
	protected void setupFolderPermissions() {
		if (folderEntity != null)
			ServerUtils.getSecuritySystem().setupTreePermission((Integer) folderEntity.getPrimaryKey(), 5, "com.mg.merp.personnelref.model.PersonnelGroup", "ParentId");
	}

}
