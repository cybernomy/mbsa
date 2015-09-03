/*
 * AvoidPhaseDlg.java
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
package com.mg.merp.contract.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.model.Phase;
import com.mg.merp.document.model.DocHead;

/**
 * Контроллер диалога "Аннулированные этапы договора"
 * 
 * @author Artem V. Sharapov
 * @version $Id: AvoidPhaseDlg.java,v 1.2 2007/03/28 13:51:15 sharapov Exp $
 */
public class AvoidPhaseDlg extends AbstractForm {

	// Fields

	private DefaultTableController avoidPhaseTable;
	private Integer avoidPhaseSelectedIndex;
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private final String PARAM_NAME = "dochead";  //$NON-NLS-1$

	/* Default constructor */
	public AvoidPhaseDlg() {
		paramsName.add(PARAM_NAME);

		avoidPhaseTable = new DefaultTableController(new DefaultEJBQLTableModel() { 
			protected String createQueryText() {
				String INIT_QUERY_TEXT = "select %s from Phase p %s where p.Avoid = 1 AND p.DocHead = :dochead"; //$NON-NLS-1$
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT.toString(), fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					avoidPhaseSelectedIndex = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					avoidPhaseSelectedIndex = (Integer) row[0];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Phase.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "Number", "p.Number", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "SumCur", "p.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "Company", "com.Code", "left join p.Company as com", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Phase.class, "Contractor", "contr.Code", "left join p.Contractor as contr", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Phase.class, "ShippedPayment", "p.ShippedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "ReceivedPayment", "p.ReceivedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "ShippedGood", "p.ShippedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Phase.class, "ReceivedGood", "p.ReceivedGood", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
	}

	// Methods

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		refreshTable();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Отменить аннулирование"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionСancelAvoid(WidgetEvent event) throws Exception {
		if (avoidPhaseSelectedIndex != null) {
			Phase phaseItem = ServerUtils.getPersistentManager().find(Phase.class, avoidPhaseSelectedIndex);
			phaseItem.setAvoid(false);
			ServerUtils.getPersistentManager().merge(phaseItem);
			refreshTable();
		}
	}

	/**
	 * Установить документ
	 * @param docHead - документ
	 */
	public void setDocHead(DocHead docHead) {
		paramsValue.add(docHead);
	}

	private void refreshTable() {
		avoidPhaseTable.getModel().load();
	}

	public void onActionOk(WidgetEvent event) throws Exception {
		closeDialog();
	}

	public void onActionCancel(WidgetEvent event) throws Exception {
		closeDialog();
	}

	private void closeDialog() {
		super.close();
	}

}
