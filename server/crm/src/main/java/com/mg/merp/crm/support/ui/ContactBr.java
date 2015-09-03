/*
 * ContactBr.java
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
package com.mg.merp.crm.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.model.Contact;

/**
 * Контроллер формы списка контактных лиц
 * 
 * @author leonova
 * @version $Id: ContactBr.java,v 1.3 2006/10/16 06:49:27 leonova Exp $ 
 */
public class ContactBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Contact c %s %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	public ContactBr() {
		super();
		restrictionFormName = "com/mg/merp/crm/resources/ContactRest.mfd.xml";
	}
	@Override
	protected String createQueryText() {
		String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		ContactRest restForm = (ContactRest) getRestrictionForm();		
		whereText = " where 0=0 ".
		concat(DatabaseUtils.formatEJBQLObjectRestriction("c.Contractor", restForm.getContractorCode(), "contractorCode", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLObjectRestriction("c.Person", restForm.getCode(), "code", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "c.Id", restForm.getAddinFieldsRestriction(), false));
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);		

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
				result.add(new TableEJBQLFieldDef(Contact.class, "Id", "c.Id", true));
				result.add(new TableEJBQLFieldDef(Contact.class, "Person", "pcont.Surname", "left join c.Person as pcont", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "Contractor", "contr.Code", "left join c.Contractor as contr", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "Curator", "pcur.Surname", "left join c.Curator as cur left join cur.Person as pcur", false));	
				result.add(new TableEJBQLFieldDef(Contact.class, "Responsible", "presp.Surname", "left join c.Responsible as resp left join resp.Person as presp", false));			
				result.add(new TableEJBQLFieldDef(Contact.class, "Priority", "c.Priority", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "IsDefault", "c.IsDefault", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "ThePosition", "pos.Name", "left join c.ThePosition as pos", false));				
				result.add(new TableEJBQLFieldDef(Contact.class, "IsRetired", "c.IsRetired", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "NickName", "c.NickName", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "PersonTitle", "pt.Code", "left join c.PersonTitle as pt", false));				
				result.add(new TableEJBQLFieldDef(Contact.class, "AddressSource", "c.AddressSource", false));
				result.add(new TableEJBQLFieldDef(Contact.class, "Comments", "c.Comments", false));				
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

