/*
 * MRPVersionControl.java
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
package com.mg.merp.planning.support.ui;

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
import com.mg.merp.planning.MRPVersionForecastServiceLocal;
import com.mg.merp.planning.MRPVersionMPSServiceLocal;
import com.mg.merp.planning.model.MrpVersionForecast;
import com.mg.merp.planning.model.MrpVersionMps;


/**
 * Контроллер формы поддержки ППМ
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: MRPVersionControlMt.java,v 1.6 2008/05/29 07:31:37 alikaev Exp $
 */
public class MRPVersionControlMt extends DefaultMaintenanceForm implements MasterModelListener{
	
	private MaintenanceTableController forecast;
	private MRPVersionForecastServiceLocal forecastService;
	protected AttributeMap forecastProperties = new LocalDataTransferObject();

	private MaintenanceTableController mps;
	private MRPVersionMPSServiceLocal mpsService;
	protected AttributeMap mpsProperties = new LocalDataTransferObject();
	
	public MRPVersionControlMt() throws Exception {
		addMasterModelListener(this);
		setMasterDetail(true);
		
		forecastService = (MRPVersionForecastServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/MRPVersionForecast");
		forecast = new MaintenanceTableController(forecastProperties);
		forecast.initController(forecastService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from MrpVersionForecast mrpfv %s where mrpfv.MrpVersionControl = :versioncontrol";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("versioncontrol");
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
				result.add(new TableEJBQLFieldDef(MrpVersionForecast.class, "Id", "mrpfv.Id", true));
				result.add(new TableEJBQLFieldDef(MrpVersionForecast.class, "ForecastVersion", "fv.Code", "left join mrpfv.ForecastVersion as fv", false));
				result.add(new TableEJBQLFieldDef(MrpVersionForecast.class, "ForecastType", "mrpfv.ForecastType", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, forecastService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(forecast);

		mpsService = (MRPVersionMPSServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/planning/MRPVersionMPS");
		mps = new MaintenanceTableController(mpsProperties);
		mps.initController(mpsService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from MrpVersionMps mrpmps %s where mrpmps.MrpVersionControl = :versioncontrol";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("versioncontrol");
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
				result.add(new TableEJBQLFieldDef(MrpVersionMps.class, "Id", "mrpmps.Id", true));
				result.add(new TableEJBQLFieldDef(MrpVersionMps.class, "Mps", "mrpmps.Mps.Code", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, mpsService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(mps);		

	}
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		forecastProperties.put("MrpVersionControl", event.getEntity());
		mpsProperties.put("MrpVersionControl", event.getEntity());		
	}	

}
