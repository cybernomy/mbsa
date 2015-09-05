/*
 * ConstantMt.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine.support.ui;

import java.util.Date;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.baiengine.ConstantServiceLocal;
import com.mg.merp.baiengine.ConstantValueServiceLocal;
import com.mg.merp.baiengine.model.Constant;
import com.mg.merp.baiengine.model.ConstantValue;

/**
 * Контроллер формы поддержи бизнес-компонента "Константа"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantMt.java,v 1.1 2007/08/21 12:56:51 alikaev Exp $
 */
public class ConstantMt extends DefaultMaintenanceForm implements MasterModelListener {

	protected MaintenanceTableController constantValueTable;
	private ConstantValueServiceLocal constantValueTableService;
	protected AttributeMap constantValueTableProperties = new LocalDataTransferObject();

	public ConstantMt() {
		super();
		setMasterDetail(true);
		addMasterModelListener(this);
		constantValueTableService = (ConstantValueServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/baiengine/ConstantValue"); //$NON-NLS-1$
		constantValueTable = new MaintenanceTableController(constantValueTableProperties);
		constantValueTable.initController(constantValueTableService, new DefaultMaintenanceEJBQLTableModel() {

			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(ConstantValue.class,"Id",null,true)); //$NON-NLS-1$
				result.add(new TableEJBQLFieldDef(ConstantValue.class,"Val",null,false)); //$NON-NLS-1$
				result.add(new TableEJBQLFieldDef(ConstantValue.class,"StartDate",null,false)); //$NON-NLS-1$
				return result;
			}

			@Override
			protected void doLoad() {
				setRowList(OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ConstantValue.class)
						.setProjection(Projections.projectionList(Projections.property("Id"), Projections.property("Val"),Projections.property("StartDate"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						.add(Restrictions.eq("Const", getEntity())))); //$NON-NLS-1$
			}
		
		});
		addMasterModelListener(this);
	}

	public void masterChange(ModelChangeEvent event) {
		constantValueTable.getModel().load();
		constantValueTableProperties.put("Const", getEntity()); //$NON-NLS-1$
	}

	protected void doOnEdit() {
		view.getWidget("DataType").setReadOnly(true);
	}
		
	@Override
	protected void doOnClone() {
		view.getWidget("DataType").setReadOnly(true);
	}

	/**
	 * заглушка для тестирования 
	 * @param event
	 */
	public void onActionFindActualValue(WidgetEvent event) {
		ConstantServiceLocal constantService = (ConstantServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/baiengine/Constant");
		Object strVal = constantService.getActualValue(((Constant)this.getEntity()).getCode(), new Date());
		throw new BusinessException(strVal != null ? strVal.toString() : "Convertation fail");
	}

}
