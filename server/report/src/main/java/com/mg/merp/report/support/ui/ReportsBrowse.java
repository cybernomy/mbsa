/**
 * ReportsBrowse.java
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
package com.mg.merp.report.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import com.mg.framework.api.report.RptEngine;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.RptEngineLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.report.RptMainServiceLocal;
import com.mg.merp.report.model.RptMain;

/**
 * Контроллер формы списка отчетов для сериса MERP/REPORT/REPORTS
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: ReportsBrowse.java,v 1.5 2008/10/09 09:35:43 safonov Exp $
 */
public class ReportsBrowse extends AbstractForm {
	private final static String LOAD_EJBQL = "select %s from RptMain rm join rm.ClassLinks as cl where cl.Report = rm and cl.SysClass.BeanName = :sysClass and exists (select rr from rm.Permissions rr where rr.SecGroups.Id in (:ids) and rr.Rights = 1)"; //$NON-NLS-1$	
	private DefaultTableController reportsList;
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private final static String SERVICE_NAME = "MERP/REPORT/REPORTS";
	
	@Override
	protected void doOnRun() {
		reportsList = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {			

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getDefaultFieldDefsSet();					
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("sysClass");
				paramsValue.add(SERVICE_NAME);						
				paramsName.add("ids");
				paramsValue.add(ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups()));						
				return String.format(LOAD_EJBQL, fieldsList);		
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
				result.add(new TableEJBQLFieldDef(RptMain.class, "Id", "rm.Id", true));
				result.add(new TableEJBQLFieldDef(RptMain.class, "Code", "rm.Code", true));
				result.add(new TableEJBQLFieldDef(RptMain.class, "RptName", "rm.RptName", true));				
				return result;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));				
			}

		});
		reportsList.getModel().load();
		showForm();
	}
	
	public void onActionPrint(WidgetEvent event) {
		Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel)reportsList.getModel()).getSelectedPrimaryKeys();
		RptEngine re = RptEngineLocator.locate();
		RptProperties prop = re.createProperies();
		prop.setLocale(ServerUtils.getUserLocale());
		if (keys.length > 0){
			RptMainServiceLocal rptServ = (RptMainServiceLocal) ApplicationDictionaryLocator.locate()
					.getBusinessService(RptMainServiceLocal.SERVICE_NAME);
			re.runAndRenderReport(rptServ.load((Integer) keys[0]), prop);
		}
	}

	public void onActionRefresh(WidgetEvent event) {
		reportsList.getModel().load();
	}

}