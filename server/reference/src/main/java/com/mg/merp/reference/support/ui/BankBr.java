/*
 * BankBr.java
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
package com.mg.merp.reference.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.Bank;

/**
 * Браузер банков
 * 
 * @author leonova
 * @version $Id: BankBr.java,v 1.2 2006/10/11 10:42:29 leonova Exp $ 
 */
public class BankBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Bank b %s";	
			
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

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
				
				result.add(new TableEJBQLFieldDef(Bank.class, "Id", "b.Id", true));
				result.add(new TableEJBQLFieldDef(Bank.class, "BIK", "b.BIK", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "BikUnique", "b.BikUnique", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Name", "b.Name", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Branch", "b.Branch", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Country", "c.CCode", "left join b.Country as c", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "CityType", "b.CityType", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "City", "b.City", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Address", "b.Address", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Zip", "b.Zip", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "CorrAcc", "b.CorrAcc", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Currency", "cur.Code", "left join b.Currency as cur", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "CorrName", "b.CorrName", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "CorrAddress", "b.CorrAddress", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Swift", "b.Swift", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Iban", "b.Iban", false));	
				result.add(new TableEJBQLFieldDef(Bank.class, "Bsc", "b.Bsc", false));	
				result.add(new TableEJBQLFieldDef(Bank.class, "Phone", "b.Phone", false));	
				result.add(new TableEJBQLFieldDef(Bank.class, "Fax", "b.Fax", false));					
				result.add(new TableEJBQLFieldDef(Bank.class, "Email", "b.Email", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Www", "b.Www", false));
				result.add(new TableEJBQLFieldDef(Bank.class, "Comments", "b.Comments", false));							
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
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
