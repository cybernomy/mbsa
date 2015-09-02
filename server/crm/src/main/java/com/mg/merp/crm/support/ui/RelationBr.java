/*
 * RelationBr.java
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
import com.mg.merp.crm.RelationServiceLocal;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.crm.model.Relation;
import com.mg.merp.reference.support.ReferenceUtils;

/**
 *  онтроллер браузера "деловых отношений"
 * 
 * @author leonova
 * @version $Id: RelationBr.java,v 1.5 2007/05/16 06:48:07 sharapov Exp $
 */
public class RelationBr extends DefaultHierarchyBrowseForm {
	private final String INIT_QUERY_TEXT = "select distinct %s from Relation r %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public RelationBr() throws Exception {
		super();
		folderService =  (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", RelationServiceLocal.FOLDER_PART); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/crm/resources/RelationRest.mfd.xml"; //$NON-NLS-1$
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
		return ReferenceUtils.loadFolderHierarchy(RelationServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Relation.class, "Id", "r.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Relation.class, "Code", "r.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Relation.class, "Name", "r.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Relation.class, "Market", "m.Code", "left join r.Market as m", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "DeliveryKind", "dk.Code", "left join r.DeliveryKind as dk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "Info", "r.Info", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Curator", "pcur.Surname", "left join r.Curator as cur left join cur.Person as pcur", false));	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Responsible", "presp.Surname", "left join r.Responsible as resp left join resp.Person as presp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "ClientType", "ct.Code", "left join r.ClientType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "ClientRank", "cr.Code", "left join r.ClientRank as cr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "PaymentCond", "pc.Code", "left join r.PaymentCond as pc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "Status", "st.Code", "left join r.Status as st", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "Parent", "p.Code", "left join r.Parent as p", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "NaturalPerson", "np.Surname", "left join r.NaturalPerson as np", false));	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "NickName", "r.NickName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				//result.add(new TableEJBQLFieldDef(Relation.class, "Uin", "r.Uin", false));
				result.add(new TableEJBQLFieldDef(Relation.class, "PersonTitle", "ptit.Code", "left join r.PersonTitle as ptit", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "LegalPerson", "lp.Code", "left join r.LegalPerson as lp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "Branch", "b.Code", "left join r.Branch as b", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "ActivitySphere", "asp.Code", "left join r.ActivitySphere as asp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "ActivityKind", "ak.Code", "left join r.ActivityKind as ak", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Relation.class, "OwnershipForm", "os.Code", "left join r.OwnershipForm as os", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

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
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		RelationRest restForm = (RelationRest) getRestrictionForm();		
		StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
		.append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "r.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.append(DatabaseUtils.formatEJBQLStringRestriction("r.Code", restForm.getCode(), "code", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLStringRestriction("r.Name", restForm.getName(), "name", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.Curator", restForm.getCuratorCode(), "curatorCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.Responsible", restForm.getResponsibleCode(), "responsibleCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.Parent", restForm.getParentCode(), "parentCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.Status", restForm.getStatusCode(), "statusCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.LegalPerson", restForm.getPartnerCode(), "partnerCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("r.NaturalPerson", restForm.getPersonCode(), "personCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLStringRestriction("r.NickName", restForm.getNick(), "nick", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("cont.Person", restForm.getContactPersonCode(), "contactPersonCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("cont.Contractor", restForm.getContactCompanyCode(), "companyCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLStringRestriction("cont.NickName", restForm.getContactNick(), "companyNick", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "r.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
		if (whereText.toString().contains("cont.")) { //$NON-NLS-1$
			fromList = (", ContactLinkId cl, Contact cont ").concat(fromList); //$NON-NLS-1$
			whereText.append(" and cl.CrmRelation = r.Id and cont.Id = cl.CrmContact"); //$NON-NLS-1$
		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}	

}
