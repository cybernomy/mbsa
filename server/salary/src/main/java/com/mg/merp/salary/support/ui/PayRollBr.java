/*
 * PayRollBr.java
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
package com.mg.merp.salary.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.CalcListServiceLocal;
import com.mg.merp.salary.FeeSummaryHeadServiceLocal;
import com.mg.merp.salary.PayRollServiceLocal;
import com.mg.merp.salary.PaySheetServiceLocal;
import com.mg.merp.salary.model.FeeSummaryHead;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.PaySheet;

/**
 * Êîíòðîëëåð áðàóçåðà ðàñ÷åòíûõ âåäîìîñòåé
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PayRollBr.java,v 1.5 2007/08/28 06:18:53 sharapov Exp $ 
 */
public class PayRollBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from PayRoll pr %s"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	private final String CREATE_FEESUMARY_FORM_NAME = "com/mg/merp/salary/resources/CreateFeeSummaryDlg.mfd.xml"; //$NON-NLS-1$
	PayRollServiceLocal payRollService = (PayRollServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PayRollServiceLocal.SERVICE_NAME);
	PaySheetServiceLocal paySheetService = (PaySheetServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaySheetServiceLocal.SERVICE_NAME);


	public PayRollBr() {
		super();
		restrictionFormName = "com/mg/merp/salary/resources/PayRollRest.mfd.xml"; //$NON-NLS-1$
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
		paramsName.clear();
		paramsValue.clear();	
		PayRollRest restForm = (PayRollRest) getRestrictionForm();
		whereText = " where 0=0 ".concat(DatabaseUtils.formatEJBQLObjectRestriction("pr.RollKind", restForm.getRollKindName(), "rollKindName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLStringRestriction("pr.RName", restForm.getRName(), "rName", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLStringRestriction("pr.RNumber", restForm.getRNumber(), "rNumber", paramsName, paramsValue, false)).		 //$NON-NLS-1$ //$NON-NLS-2$
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.CalcPeriod.BeginDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("pr.CalcPeriod.EndDate", restForm.getBeginDate(), restForm.getEndDate(), "beginDate", "endDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "pr.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
		return String.format(INIT_QUERY_TEXT, fieldsList, whereText);	
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
				result.add(new TableEJBQLFieldDef(PayRoll.class, "Id", "pr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "CalcPeriod", "pr.CalcPeriod.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "RollKind", "pr.RollKind.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "Number", "pr.Number", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "Name", "pr.Name", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "Comments", "pr.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "CalcBeginDate", "pr.CalcBeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PayRoll.class, "CalcEndDate", "pr.CalcEndDate", false));				 //$NON-NLS-1$ //$NON-NLS-2$
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


	public void onActionShowSpecPayRoll(WidgetEvent event) throws Exception {
		final CalcListServiceLocal service = (CalcListServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/CalcList"); //$NON-NLS-1$
		CalcListBr form = (CalcListBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		for (Serializable key : keys) {			
			form.setPayRollId(key);
			form.run();
			break;
		}		
	}

	/**
	 * Îáðàáîò÷èê ïóíêòà ÊÌ "Ñîçäàòü ïëàòåæíóþ âåäîìîñòü"
	 * @param event - ñîáûòèå
	 */
	public void onActionCreatePaySheet(WidgetEvent event) {
		Serializable[] selectedPayRollIds = getSelectedPrimaryKeys();
		if(selectedPayRollIds != null && selectedPayRollIds.length > 0) {
			PaySheet paySheet = payRollService.createPaySheet((Integer) selectedPayRollIds[0]);
			if(paySheet != null)
				MaintenanceHelper.edit(paySheetService, paySheet.getId(), null, null);
		}
	}

	/**
	 * Îáðàáîò÷èê ïóíêòà ÊÌ "Ñîçäàòü ñâîä í/ó ïî àíàëèòèêå"
	 * @param event - ñîáûòèå
	 */
	public void onActionCreateFeeSummary(WidgetEvent event) {
		final Serializable[] selectedPayRollIds = getSelectedPrimaryKeys();
		if(selectedPayRollIds != null && selectedPayRollIds.length > 0) {
			final FeeSummaryHeadServiceLocal feeSummaryHeadService = (FeeSummaryHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FeeSummaryHeadServiceLocal.SERVICE_NAME);
			final CreateFeeSummaryDlg ñreateFeeSummaryDlg = (CreateFeeSummaryDlg) UIProducer.produceForm(CREATE_FEESUMARY_FORM_NAME);
			ñreateFeeSummaryDlg.addOkActionListener(new FormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
				 */
				public void actionPerformed(FormEvent event) {
					FeeSummaryHead feeSummaryHead = payRollService.createFeeSummary(selectedPayRollIds, ñreateFeeSummaryDlg.getFeeSummaryPattern());
					if(feeSummaryHead != null && ñreateFeeSummaryDlg.isShowCreatedDocument() ) 
						MaintenanceHelper.edit(feeSummaryHeadService, feeSummaryHead.getId(), null, null);
				}
			});
			ñreateFeeSummaryDlg.setDocSection(feeSummaryHeadService.getDocSection());
			ñreateFeeSummaryDlg.execute();
		}
	}

	private Serializable[] getSelectedPrimaryKeys() {
		return ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
	}

}
