/*
 * TariffScaleBr.java
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

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.TariffScaleClassServiceLocal;
import com.mg.merp.personnelref.model.TariffScale;
import com.mg.merp.personnelref.model.TariffScaleClass;

/**
 * ������� ��������� �����
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: TariffScaleBr.java,v 1.4 2006/10/02 06:23:31 leonova Exp $
 */
public class TariffScaleBr extends DefaultMDBrowseForm implements MasterModelListener {
	private final String INIT_QUERY_TEXT = "select %s from TariffScale ts %s";
	private final String INIT_QUERY_TEXT_DETAIL = "select %s from TariffScaleClass tsc where tsc.TariffScale.Id = :tariffScaleId";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public TariffScaleBr() throws Exception {
		super();
		this.detailService = (TariffScaleClassServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/TariffScaleClass");
		master.addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createDetailModel()
	 */
	@Override
	protected MaintenanceTableModel createDetailModel() {
		return new DefaultMaintenanceEJBQLTableModel() {
			
			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("tariffScaleId");
				paramsValue.add(getMasterKey());					
				return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList);		
			}
			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Id", "tsc.Id", true));
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "ClassNumber", "tsc.ClassNumber", false));
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Factor", "tsc.Factor", false));
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Rate", "tsc.Rate", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, detailService);

			}

			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createMasterModel()
	 */
	@Override
	protected MaintenanceTableModel createMasterModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}
			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TariffScale.class, "Id", "ts.Id", true));
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SCode", "ts.SCode", false));
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SName", "ts.SName", false));
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SType", "ts.SType", false));				
				result.add(new TableEJBQLFieldDef(TariffScale.class, "BeginDate", "ts.BeginDate", false));
				result.add(new TableEJBQLFieldDef(TariffScale.class, "FirstClassAlg", "alg.Code", "left join ts.FirstClassAlg as alg", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, masterService);

			}

			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}

			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		detailUIProperties.put("TariffScale.Id", event.getModelKey()); //$NON-NLS-1$
	}	
	
}
