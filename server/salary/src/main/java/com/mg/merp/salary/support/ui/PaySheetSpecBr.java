/*
 * PaySheetSpecBr.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.model.PaySheet;
import com.mg.merp.salary.model.PaySheetSpec;
import com.mg.merp.salary.support.Messages;

/**
 * Контроллер браузера "Спецификация платежной ведомости"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaySheetSpecBr.java,v 1.4 2007/07/19 07:52:07 sharapov Exp $ 
 */
public class PaySheetSpecBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from PaySheetSpec pss %s where pss.PaySheet = :paySheet"; //$NON-NLS-1$
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();

	private PaySheet paySheet;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		adjustTitle();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		paramsName.add("paySheet"); //$NON-NLS-1$
		paramsValue.add(paySheet);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				
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
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "Id", "pss.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "PositionFill.SlPositionUnique", "pss.PositionFill.SlPositionUnique.SlPositionUniqueId", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "PositionFill.PersonalAccount", "np.Surname", "left join pss.PositionFill.PersonalAccount as pa left join pa.Personnel as p left join p.Person np", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "SummaFull", "pss.SummaFull", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "SummaPaidOut", "pss.SummaPaidOut", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "SummaDeposited", "pss.SummaDeposited", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PaySheetSpec.class, "SummaRest", "pss.SummaRest", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
	 * Установить заголовок платежной ведомости
	 */
	public void setPaySheet(PaySheet paySheet) {
		this.paySheet = paySheet;
	}

	/**
	 * Настроить заголовок браузера в соответствии с контекстом отображения
	 */
	protected void adjustTitle() {
		if(paySheet != null)
			view.setTitle(String.format(Messages.getInstance().getMessage(Messages.PAY_SHEET_SPEC_BROWSE_FORM_TITLE), paySheet.getSNumber().trim(), paySheet.getPayRoll().getCalcPeriod().getPName()));
	}

}
