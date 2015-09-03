/*
 * PayRollBr.java
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
package com.mg.merp.salary.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.PaySheetSpecServiceLocal;
import com.mg.merp.salary.model.PaySheet;

/**
 * Контроллер браузера заголовков платежных ведомостей
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaySheetBr.java,v 1.4 2007/07/19 07:52:07 sharapov Exp $ 
 */
public class PaySheetBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from PaySheet ps %s %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();


	public PaySheetBr() {
		super();
		restrictionFormName = "com/mg/merp/salary/resources/PaySheetRest.mfd.xml"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		String whereText = ""; //$NON-NLS-1$
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		PaySheetRest restForm = (PaySheetRest) getRestrictionForm();
		whereText = " where 0=0 ".concat(DatabaseUtils.formatEJBQLObjectRestriction("ps.PayRoll.RollKind", restForm.getRollKindName(), "rollKindName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLObjectRestriction("ps.PayRoll.RName", restForm.getRollKindName(), "rollKindName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLObjectRestriction("ps.PayRoll.RNumber", restForm.getPayRollNumber(), "payRollNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("ps.PayRoll.CalcPeriod.BeginDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("ps.PayRoll.CalcPeriod.EndDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("ps.DocHead.DocDate", restForm.getDocDateFrom(), restForm.getDocDateTill(), "docDateFrom", "docDateTill", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLStringRestriction("ps.DocHead.DocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLObjectRestriction("ps.DocHead.DocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "pr.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(PaySheet.class, "Id", "ps.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "PayRoll.CalcPeriod", "cp.PName", "left join ps.PayRoll.CalcPeriod as cp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "PayRoll.RollKind", "rk.Name", "left join ps.PayRoll.RollKind as rk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "PayRoll.Number", "ps.PayRoll.Number", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "SNumber", "ps.SNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "BeginDate", "ps.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "EndDate", "ps.EndDate", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "InDocHead.DocNumber", "ind.DocNumber", "left join ps.InDocHead as ind", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "InDocHead.DocType", "intype.Code", "left join ind.DocType as intype", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "InDocHead.DocDate", "ind.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "DocHead.DocNumber", "d.DocNumber", "left join ps.DocHead as d", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "DocHead.DocType", "dtype.Code", "left join d.DocType as dtype", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "DocHead.DocDate", "d.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "SummaFull", "ps.SummaFull", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "Cashier", "cash.Code", "left join ps.Cashier as cash", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "Chief", "ch.Code", "left join ps.Chief as ch", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheet.class, "Bookkeeper", "b.Code", "left join ps.Bookkeeper as b", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

	/**
	 * Обработчик пункта КМ "Открыть ведомость" (платежную)
	 * @param event - событие
	 */
	public void onActionShowSpecPaySheet(WidgetEvent event) {
		Serializable[] selectedPrimaryKeys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if(selectedPrimaryKeys != null && selectedPrimaryKeys.length > 0) {
			PaySheetSpecBr browseForm = (PaySheetSpecBr) ApplicationDictionaryLocator.locate().getBrowseForm((PaySheetSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/PaySheetSpec"), null); //$NON-NLS-1$
			browseForm.setPaySheet((PaySheet) service.load(selectedPrimaryKeys[0]));
			browseForm.run();
		}
	}

}
