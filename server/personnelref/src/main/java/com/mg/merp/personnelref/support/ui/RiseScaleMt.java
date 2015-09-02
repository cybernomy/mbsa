/*
 * RiseScaleMt.java
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
package com.mg.merp.personnelref.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.RisePercentServiceLocal;
import com.mg.merp.personnelref.model.RisePercent;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: RiseScaleMt.java,v 1.4 2006/09/04 13:07:49 leonova Exp $
 */
public class RiseScaleMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController risePercent;
	private RisePercentServiceLocal risePercentService;
	protected BrowseCond risePercentCond = new BrowseCond(null, "", new LocalDataTransferObject(), false, DataBusinessObjectService.INTERNAL_LEGACY_FORMAT);
	protected AttributeMap risePercentProperties = new LocalDataTransferObject();

	public RiseScaleMt() throws Exception {
		addMasterModelListener(this);
		
		risePercentService = (RisePercentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/RisePercent");
		risePercent = new MaintenanceTableController( risePercentProperties);
		risePercent.initController(risePercentService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from RisePercent rp where rp.RiseScale = :riseScale";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("riseScale");
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList);		
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
				result.add(new TableEJBQLFieldDef(RisePercent.class, "Id", "rp.Id", true));
				result.add(new TableEJBQLFieldDef(RisePercent.class, "PercentNumber", "rp.PercentNumber", false));
				result.add(new TableEJBQLFieldDef(RisePercent.class, "ServiceFrom", "rp.ServiceFrom", false));
				result.add(new TableEJBQLFieldDef(RisePercent.class, "ServiceTo", "rp.ServiceTo", false));
				result.add(new TableEJBQLFieldDef(RisePercent.class, "RiseValue", "rp.RiseValue", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, risePercentService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(risePercent);

	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		risePercentProperties.put("RiseScale", event.getEntity()); 
	}
}
