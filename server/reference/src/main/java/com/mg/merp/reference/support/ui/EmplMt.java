/*
 * EmplMt.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.PartnEmplLinkServiceLocal;
import com.mg.merp.reference.model.PartnEmplLink;


/**
 * @author leonova
 * @version $Id: EmplMt.java,v 1.5 2006/10/04 07:50:25 leonova Exp $
 */
public class EmplMt  extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController linkPartner;
	private PartnEmplLinkServiceLocal linkPartnerService;
	protected AttributeMap linkPartnerProperties = new LocalDataTransferObject();
	
	public EmplMt() throws Exception {
		addMasterModelListener(this);
		
		linkPartnerService = (PartnEmplLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PartnEmplLink");
		linkPartner = new MaintenanceTableController(linkPartnerProperties);
		linkPartner.setMaintenanceForm("maintenanceFromEmployees");
		linkPartner.initController(linkPartnerService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PartnEmplLink pel %s where pel.Employees = :employees";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("employees");
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "Id", "pel.Id", true));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "Partner.Code", "pel.Partner.Code", false));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "DateBegin", "pel.DateBegin", false));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "DateEnd", "pel.DateEnd", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, linkPartnerService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(linkPartner);
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		linkPartnerProperties.put("Employees", event.getEntity());
	}

}
