/* RptView.java
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
package com.mg.merp.wb.report.deployer.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.mg.merp.wb.core.ui.view.StandartBrowserView;
import com.mg.merp.wb.report.deployer.DeployerPlugin;
import com.mg.merp.wb.report.deployer.support.RptViewController;

/**
 * Панель репозитария отчётов
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: RptView.java,v 1.6 2007/04/11 06:58:44 poroxnenko Exp $
 */
public class RptView extends StandartBrowserView<RptViewController> {

	public static final String ID = "com.mg.merp.wb.report.deployer.ui.RptView"; // TODO

	private static final String COL1_NAME = "templtable.columns.col1";

	private static final String COL2_NAME = "templtable.columns.col2";

	private static final String COL3_NAME = "templtable.columns.col3";

	public RptView() {
		viewController = new RptViewController(this);
	}

	@Override
	public void addTableColumns(Table table) {

		table.addMouseListener(new MouseListener() {

			public void mouseDoubleClick(MouseEvent e) {
				viewController.putTemplateToProject();
			}

			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setWidth(180);
		column1.setText(DeployerPlugin.getDefault().getString(COL1_NAME));
		column1.setAlignment(SWT.LEFT);

		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setWidth(180);
		column2.setText(DeployerPlugin.getDefault().getString(COL2_NAME));
		column2.setAlignment(SWT.LEFT);

		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setWidth(320);
		column3.setText(DeployerPlugin.getDefault().getString(COL3_NAME));
		column3.setAlignment(SWT.LEFT);
		setMenu(null);
	}
}
