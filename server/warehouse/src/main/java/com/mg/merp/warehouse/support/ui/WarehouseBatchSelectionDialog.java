/*
 * WarehouseBatchSelectionForm.java
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
package com.mg.merp.warehouse.support.ui;

import java.math.BigDecimal;
import java.util.List;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.support.ui.widget.TableModelListener;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.support.BatchQuanTransfer;

/**
 * Диалог выбора партий, с которых будет производиться списание
 * 
 * @author Valentin A. Poroxnenko
 * @version $$Id: WarehouseBatchSelectionDialog.java,v 1.2 2007/03/01 09:24:16 poroxnenko Exp $$
 */
public class WarehouseBatchSelectionDialog extends DefaultDialog {
	protected TableControllerAdapter warehouseBatchesTable;

	public static final String FORM_PATH = "com/mg/merp/warehouse/resources/WarehouseBatchSelectionDialog.mfd.xml";

	private static final String CATALOG_CODE = "catalogCodeValue";

	private static final String CATALOG_NAME = "catalogNameValue";

	private static final String SPEC_QUAN1 = "specQuan1";

	private static final String SPEC_QUAN2 = "specQuan2";

	private static final String MARK_QUAN1 = "markQuan1";

	private static final String MARK_QUAN2 = "markQuan2";

	private WarehouseBatchTableModel model;

	private DocSpec docSpec;

	private BigDecimal allMarkQuan1 = new BigDecimal(0);

	private BigDecimal allMarkQuan2 = new BigDecimal(0);

	public WarehouseBatchSelectionDialog() {
		model = new WarehouseBatchTableModel();
		model.addTableModelListener(new TableModelListener() {

			public void tableCellUpdated(int row, int col) {
				calculateMarkedQuan();
			}

			public void tableChanged() {
			}

			public void tableRowsDeleted(int arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			public void tableRowsInserted(int arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			public void tableRowsUpdated(int arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			public void tableStructureChanged() {
				// TODO Auto-generated method stub

			}

		});
		warehouseBatchesTable = new DefaultTableController(model);
	}

	public void setBatchesList(List<StockBatch> batchesList) {
		model.setBatchesList(batchesList);
	}

	protected Object doGetFieldValue(String name) throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		if (CATALOG_CODE.equals(name))
			return docSpec.getCatalog().getCode().trim();
		else if (CATALOG_NAME.equals(name))
			return docSpec.getCatalog().getFullName().trim();
		else if (SPEC_QUAN1.equals(name))
			return docSpec.getQuantity();
		else if (SPEC_QUAN2.equals(name))
			return docSpec.getQuantity2();
		else if (MARK_QUAN1.equals(name))
			return allMarkQuan1;
		else if (MARK_QUAN2.equals(name))
			return allMarkQuan2;
		else
			return super.doGetFieldValue(name);

	}

	private boolean isCorrect() {
		return ((MathUtils.compareToZero(allMarkQuan1.subtract(docSpec
				.getQuantity())) == 0) && ((docSpec.getQuantity2() == null) || ((docSpec
				.getQuantity2() != null) && (MathUtils
				.compareToZero(allMarkQuan2.subtract(docSpec.getQuantity2())) == 0))));
	}

	public void setDocSpec(DocSpec docSpec) {
		this.docSpec = docSpec;
	}

	public List<BatchQuanTransfer> getDispQuan() {
		return model.getDispQuan();
	}

	private void calculateMarkedQuan() {
		allMarkQuan1 = new BigDecimal(0);
		allMarkQuan2 = new BigDecimal(0);
		for (int i = 0; i < model.getRowCount(); i++) {
			BigDecimal q1 = (BigDecimal) model.getValueAt(i, 2);
			if (q1 != null)
				allMarkQuan1 = allMarkQuan1.add(q1);
			BigDecimal q2 = (BigDecimal) model.getValueAt(i, 5);
			if (q2 != null)
				allMarkQuan2 = allMarkQuan2.add(q2);
		}
		view.flushModel();
	}
	
	@Override
	public void onActionOk(WidgetEvent event) {
		if (isCorrect())
			super.onActionOk(event);
	}

}
