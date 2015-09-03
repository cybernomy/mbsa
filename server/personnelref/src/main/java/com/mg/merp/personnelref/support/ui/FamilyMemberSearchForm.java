/*
 * FamilyMemberSearchForm.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.FamilyMemberServiceLocal;
import com.mg.merp.reference.model.FamilyMember;

/**
 * Контроллер диалога поиска членов семьи бизнес-компонента "Вычеты на членов семьи"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FamilyMemberSearchForm.java,v 1.2 2009/02/09 12:11:17 safonov Exp $
 */
public class FamilyMemberSearchForm extends AbstractSearchForm {
	private final String INIT_QUERY_TEXT = "select %s from FamilyMember as fm %s where fm.NaturalPerson = :person"; //$NON-NLS-1$
	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private DefaultTableController familyMembersTable;
	private FamilyMemberServiceLocal service = (FamilyMemberServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/FamilyMember"); //$NON-NLS-1$;
	private Serializable[] selectedIDs = null;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		familyMembersTable = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Id", "fm.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "FamilyRelation", "fr.RCode", "left join fm.FamilyRelation as fr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "FName", "fm.FName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Patronymic", "fm.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Surname", "fm.Surname", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Birthdate", "fm.Birthdate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("person");
				paramsValue.add(NaturalPerson);			
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) familyMembersTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				selectedIDs = new Serializable[rows.length];
				for (int i = 0; i < rows.length; i++)
					selectedIDs[i] = (Serializable) getRowList().get(rows[i])[0];
			}

		});
		familyMembersTable.getModel().load();
		showForm();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		int len = selectedIDs == null ? 0 : selectedIDs.length;
		FamilyMember[] selectedEntities = new FamilyMember[len];
		for (int i = 0; i < len; i++)
			selectedEntities[i] = ServerUtils.getPersistentManager().find(FamilyMember.class, selectedIDs[i]);
		return selectedEntities;
	}

	/**
	 * @return the naturalPerson
	 */
	public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
		return NaturalPerson;
	}

	/**
	 * @param naturalPerson the naturalPerson to set
	 */
	public void setNaturalPerson(
			com.mg.merp.reference.model.NaturalPerson naturalPerson) {
		NaturalPerson = naturalPerson;
	}

}
