/*
 * DiscCardBr.java
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
package com.mg.merp.discount.support.ui;

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
import com.mg.merp.discount.CardServiceLocal;
import com.mg.merp.discount.model.Card;

/**
 * Контроллер браузера "Дисконтные карты"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DiscCardBr.java,v 1.5 2007/12/04 14:58:15 sharapov Exp $
 */
public class DiscCardBr extends DefaultHierarchyBrowseForm {
	
	private final String INIT_QUERY_TEXT = "select %s from Card c %s %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public DiscCardBr() throws Exception {		
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", CardServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/discount/resources/DiscCardRest.mfd.xml"; //$NON-NLS-1$
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
		return CoreUtils.loadFolderHierarchy(CardServiceLocal.FOLDER_PART);
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
				result.add(new TableEJBQLFieldDef(Card.class, "Id", "c.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "CardNum", "c.CardNum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "Owner", "o.Code", "left join c.Owner as o", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Card.class, "Discount", "c.Discount", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "CreditLimit", "c.CreditLimit", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "CreditDepth", "c.CreditDepth", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "Comments", "c.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Card.class, "IsActive", "c.IsActive", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
		StringBuilder whereText = new StringBuilder("where"); //$NON-NLS-1$
		StringBuilder joinText = new StringBuilder();
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		DiscCardRest restForm = (DiscCardRest) getRestrictionForm();
		whereText.append((DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "c.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true))) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLStringRestriction("c.CardNum", restForm.getDisCardNumber(), "cardNum", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRestriction("c.Owner", restForm.getDisCardOwner(), "owner", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("c.CreditLimit", restForm.getCreditLimitFrom(), restForm.getCreditLimitTill(), "creditLimitFrom", "creditLimitTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("(select min(dch.TimeStamp) from CardHist dch where dch.Card = c)", null, restForm.getActDate(), null, "actDate", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		if(restForm.getActivity() != null)
			whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("c.IsActive", CardActivityKind.ACTIVE == restForm.getActivity() ? true : false, "isActive", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		if(restForm.getDisCardUser() != null) {
			joinText.append("left join c.Users cardUsers"); //$NON-NLS-1$
			whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("cardUsers.Contractor", restForm.getDisCardUser(), "cardUsers", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, joinText.toString(), whereText.toString());	
	}

}