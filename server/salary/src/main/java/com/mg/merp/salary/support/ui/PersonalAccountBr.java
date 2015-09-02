/*
 * PersonalAccountBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.salary.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.model.PersonalAccount;

/**
 * Контроллер формы поддержки лицевых счетов сотрудников - плоский список
 * 
 * @author leonova
 * @version $Id: PersonalAccountBr.java,v 1.4 2006/11/02 16:25:25 safonov Exp $ 
 */
public class PersonalAccountBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select distinct %s from PersonalAccount pa %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public PersonalAccountBr() {
		super();
		restrictionFormName = "com/mg/merp/salary/resources/PersonalAccountRest.mfd.xml";
	}
	
	@Override
	protected String createQueryText() {
		//String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);					
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		PersonalAccountRest restForm = (PersonalAccountRest) getRestrictionForm();
		StringBuilder whereText = new StringBuilder(" where 0=0 ").
		append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pa.BeginDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pa.EndDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRangeRestriction("pa.ANumber", restForm.getANumberFrom(), restForm.getANumberTill(), "aNumberFrom", "aNumberTill", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLStringRestriction("Personnel.Person.Surname", restForm.getSurname(), "surname", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLStringRestriction("Personnel.Person.Name", restForm.getName(), "name", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLStringRestriction("Personnel.Person.Patronymic", restForm.getPatronymic(), "patronymic", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLStringRestriction("Personnel.Person.Inn", restForm.getInn(), "inn", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRestriction("Personnel.InsuredClass", restForm.getInsuredClass(), "insuredClass", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRestriction("pf.Position", restForm.getPosition(), "position", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRestriction("pf.PositionFillKind", restForm.getPositionFillKind(), "positionFillKind", paramsName, paramsValue, false)).
		
		append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "pa.Id", restForm.getAddinFieldsRestriction(), false));
		if (whereText.toString().contains("pf.")) {
			whereText = whereText.append(" and pf.PersonalAccount = pa.Id");
			fromList = (", PositionFill pf ").concat(fromList);
		}
		if (restForm.getIsBasic() != 0) {
			whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("pf.IsBasic", BooleanUtils.toBoolean(restForm.getIsBasic(), 1, 2), "isBasic", paramsName, paramsValue, false));
		}		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Id", "pa.Id", true));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Surname", "pa.Personnel.Person.Surname", false));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Name", "pa.Personnel.Person.Name", false));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "Personnel.Person.Patronymic", "pa.Personnel.Person.Patronymic", false));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "ANumber", "pa.ANumber", false));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "BeginDate", "pa.BeginDate", false));
				result.add(new TableEJBQLFieldDef(PersonalAccount.class, "EndDate", "pa.EndDate", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {				
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};

	}

}


