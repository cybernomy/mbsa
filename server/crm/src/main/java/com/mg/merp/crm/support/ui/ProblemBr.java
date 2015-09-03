/*
 * ProblemBr.java
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
package com.mg.merp.crm.support.ui;

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
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 * Контроллер браузера бизнес-компонента "Проблемы"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProblemBr.java,v 1.4 2007/02/07 07:01:42 sharapov Exp $
 */
public class ProblemBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Problem p %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public ProblemBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", ProblemServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/crm/resources/ProblemRest.mfd.xml"; //$NON-NLS-1$
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {		
		uiProperties.put("Folder", master); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return ReferenceUtils.loadFolderHierarchy(ProblemServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Problem.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "Keywords", "p.Keywords", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "ProblemType", "pt.Code", "left join p.ProblemType as pt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Problem.class, "Priority", "p.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "ValidFrom", "p.ValidFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "ValidTo", "p.ValidTo", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Problem.class, "Creator", "per.Surname", "left join p.Creator as c left join c.Person per", false));		 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
				//setRowList(createRowList());
			}
		};
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		ProblemRest restForm = (ProblemRest) getRestrictionForm();
		StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "p.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if(restForm.getSearchText() != null && restForm.getSearchText().length() > 0) {
			if(restForm.isSearchInName())
				whereText.append(getSearchQueryText(true, "p.Name", restForm.getSearchText(), restForm.isExact(), restForm.isCaseSensetive())); //$NON-NLS-1$
			if(restForm.isSearchInKeyWords())
				if(!restForm.isSearchInName() && !restForm.isSearchInInfo())
					whereText.append(getSearchQueryText(true, "p.Keywords", restForm.getSearchText(), restForm.isExact(), restForm.isCaseSensetive())); //$NON-NLS-1$
				else
					whereText.append(getSearchQueryText(false, "p.Keywords", restForm.getSearchText(), restForm.isExact(), restForm.isCaseSensetive())); //$NON-NLS-1$
			if(restForm.isSearchInInfo())
				if(!restForm.isSearchInName() && !restForm.isSearchInKeyWords())
					whereText.append(getSearchQueryText(true, "p.Info", restForm.getSearchText(), restForm.isExact(), restForm.isCaseSensetive())); //$NON-NLS-1$
				else
					whereText.append(getSearchQueryText(false, "p.Info", restForm.getSearchText(), restForm.isExact(), restForm.isCaseSensetive())); //$NON-NLS-1$
		}
		if(restForm.getSolution() != null) {
			fromList = fromList.concat(" join p.LinkProblemSolutions lps "); //$NON-NLS-1$
			whereText = whereText.append(" "). //$NON-NLS-1$
			append(DatabaseUtils.formatEJBQLObjectRestriction("lps.Id.CrmSolution", restForm.getSolution(), "solution", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());	
	}

	private String getSearchQueryText(boolean isAnd, String fieldName, String fieldValue, boolean isExact, boolean isCaseSensetive) {
		StringBuilder queryText = new StringBuilder();
		String equal;
		if(isAnd)
			queryText.append(" and "); //$NON-NLS-1$
		else
			queryText.append(" or "); //$NON-NLS-1$

		if(!isCaseSensetive) {
			fieldName = " upper (" + fieldName + ") "; //$NON-NLS-1$ //$NON-NLS-2$
			fieldValue = fieldValue.toUpperCase(); 
		}

		if(isExact)
			equal = " = '" + fieldValue + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		else
			equal = " like '%" + fieldValue + "%'"; //$NON-NLS-1$ //$NON-NLS-2$
		queryText.append(fieldName).append(equal);
		return queryText.toString();
	}

//	private List<Object[]> createRowList() {
//	Projection projection = Projections.projectionList(Projections.property("Id"), Projections.property("Name"), Projections.property("Keywords"), Projections.property("ProblemType"), Projections.property("Priority"), Projections.property("ValidFrom"), Projections.property("ValidTo"), Projections.property("Creator"));
//	ProblemRest restForm = (ProblemRest) getRestrictionForm();
//	Criteria criteria = OrmTemplate.createCriteria(Problem.class);
//	criteria.add(Restrictions.like("Name", restForm.getSearchText()));
//	criteria.setProjection(projection);
//	criteria.setResultTransformer(new ResultTransformer<Object[]>() {

//	/* (non-Javadoc)
//	* @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
//	*/
//	public Object[] transformTuple(Object[] tuple, String[] aliases) {
//	return new Object[]{(Integer) tuple[0], (String) tuple[1], (String) tuple[2]};
//	}

//	});
//	List<Object[]> problemList = OrmTemplate.getInstance().findByCriteria(criteria);
//	return problemList;
//	}


}

