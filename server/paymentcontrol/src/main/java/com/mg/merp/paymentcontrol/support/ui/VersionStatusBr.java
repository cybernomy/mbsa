/*
 * VersionStatusBr.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.paymentcontrol.model.VersionStatus;

/**
 * Браузер статусов версий планирования
 * 
 * @author leonova
 * @version $Id: VersionStatusBr.java,v 1.3 2007/05/14 05:23:52 sharapov Exp $ 
 */
public class VersionStatusBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from VersionStatus vs %s where vs.Version.Id = :versionId";
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();	
	protected Serializable versionId;
	
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("versionId");
		paramsValue.add((Integer)versionId);
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
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "Id", "vs.Id", true));
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "Kind", "vs.Kind", false));
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "DateFrom", "vs.DateFrom", false));
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "DateTill", "vs.DateTill", false));
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "Creator", "c.Name", "left join vs.Creator as c", false));
				result.add(new TableEJBQLFieldDef(VersionStatus.class, "CreateDate", "vs.CreateDate", false));				
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

	public void setVersionId(Serializable versionId) {
		this.versionId = versionId;
	}

}
