/*
 * PartnerBr.java
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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.model.Partner;

/**
 * Браузер партнеров
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PartnerBr.java,v 1.9 2009/02/09 16:32:44 safonov Exp $
 */
public class PartnerBr extends DefaultHierarchyBrowseForm {	
	private final String INIT_QUERY_TEXT = "select %s from Partner p %s %s order by p.Code ";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	/**
	 * 
	 */
	public PartnerBr() throws Exception{
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");		
		treeUIProperties.put("FolderType", PartnerServiceLocal.FOLDER_PART);
		tree.setParentPropertyName("Folder.Id");
		restrictionFormName = "com/mg/merp/reference/resources/PartnerRest.mfd.xml";
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
	 */
	@Override
	protected void initializeMaster(PersistentObject master) {
		uiProperties.put("FolderId", master.getAttribute("Id"));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#getFolderAttributeName()
	 */
	@Override
	protected String getFolderAttributeName() {
		return "FolderId";
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(PartnerServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Partner.class, "Id", "p.Id", true));
				result.add(new TableEJBQLFieldDef(Partner.class, "Code", "p.Code", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "FullName", "p.FullName", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "INN", "p.INN", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "KPP", "p.KPP", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "OKONH", "p.OKONH", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "OKVED", "p.OKVED", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "OKPO", "p.OKPO", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "MaxSupCredit", "p.MaxSupCredit", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "MaxSupCreditCur", "supcur.Code", "left join p.MaxSupCreditCur as supcur", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "TermSupCredit", "p.TermSupCredit", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "PenaltySup", "p.PenaltySup", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "MaxCusCredit", "p.MaxCusCredit", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "MaxCusCreditCur", "cuscur.Code", "left join p.MaxCusCreditCur as cuscur", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "TermCusCredit", "p.TermCusCredit", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "PenaltyCus", "p.PenaltyCus", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "NaturalPerson", "(np.Surname||' '||np.Name||' '||np.Patronymic) as person", "left join p.NaturalPerson as np", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "ZipCode", "zc.Code", "left join p.ZipCode as zc", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Country", "c.CName", "left join p.Country as c", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Region", "r.Name", "left join p.Region as r", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "District", "d.Name", "left join p.District as d", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "Place", "pl.Name", "left join p.Place as pl", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Street", "p.Street", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "House", "p.House", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Building", "p.Building", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Room", "p.Room", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "ZipCode1", "zc1.Code", "left join p.ZipCode as zc1", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Country1", "c1.CName", "left join p.Country as c1", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Region1", "r1.Name", "left join p.Region as r1", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "District1", "d1.Name", "left join p.District as d1", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Place1", "pl1.Name", "left join p.Place as pl1", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "Street1", "p.Street", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "House1", "p.House1", false));				
				result.add(new TableEJBQLFieldDef(Partner.class, "Building1", "p.Building1", false));
				result.add(new TableEJBQLFieldDef(Partner.class, "Room1", "p.Room1", false));
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
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		StringBuilder fromList = new StringBuilder(DatabaseUtils.generateEJBQLFromClause(fieldDefs));
		paramsName.clear();
		paramsValue.clear();	
		PartnerRest restForm = (PartnerRest) getRestrictionForm();
		StringBuilder whereText = new StringBuilder(" where ")
				.append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "p.FolderId", 0, "folderId", folderEntity == null ? null : folderEntity.getAttribute("Id"), paramsName, paramsValue, true))
				.append(DatabaseUtils.formatEJBQLStringRestriction("p.Code", restForm.getCode(), "code", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLStringRestriction("p.FullName", restForm.getFullName(), "fullName", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLStringRestriction("p.INN", restForm.getInn(), "inn", paramsName, paramsValue, false))
				.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "p.Id", restForm.getAddinFieldsRestriction(), false));
		if (!StringUtils.stringNullOrEmpty(restForm.getBankAcc())
				|| !StringUtils.stringNullOrEmpty(restForm.getCorrAcc())
				|| !StringUtils.stringNullOrEmpty(restForm.getBik())) {
			fromList.append(", BankAccount as ba ").append(fromList);
			whereText.append(" and ba.Contractor = p.Id ")
					.append(DatabaseUtils.formatEJBQLStringRestriction("ba.Account", restForm.getBankAcc(), "bankAcc", paramsName, paramsValue, false));
			if (!StringUtils.stringNullOrEmpty(restForm.getCorrAcc())
					|| !StringUtils.stringNullOrEmpty(restForm.getBik())) {
				whereText.append(DatabaseUtils.formatEJBQLStringRestriction("ba.Bank.BIK", restForm.getBik(), "bik", paramsName, paramsValue, false))
						.append(DatabaseUtils.formatEJBQLStringRestriction("ba.Bank.CorrAcc", restForm.getCorrAcc(), "corrAcc", paramsName, paramsValue, false));
			}			
		}
		String fieldsList = null;
		if (fromList.indexOf("BankAccount") != -1) {
			fieldsList = " distinct ".concat(DatabaseUtils.generateEJBQLSelectClause(fieldDefs));
		} else {
			fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);	
		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList.toString(), whereText.toString());
	}

}
