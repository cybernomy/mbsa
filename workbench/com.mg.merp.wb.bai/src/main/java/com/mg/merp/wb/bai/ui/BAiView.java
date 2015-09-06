/* BAiView.java
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
package com.mg.merp.wb.bai.ui;

import com.mg.merp.wb.bai.BAiPlugin;
import com.mg.merp.wb.bai.ui.support.BAiViewController;
import com.mg.merp.wb.core.ui.view.StandartBrowserView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BAiView.java,v 1.2 2007/01/23 16:11:21 poroxnenko Exp $
 */
public class BAiView extends StandartBrowserView<BAiViewController> {

  public static final String ID = "com.mg.merp.wb.bai.ui.BAiView";
  private static final String COL1 = "table.columns.col1";
  private static final String COL2 = "table.columns.col2";
  private static final String COL3 = "table.columns.col3";

  public BAiView() {
    viewController = new BAiViewController(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.mg.merp.wb.core.ui.support.StandartBrowserView#addTableColumns(org.eclipse.swt.widgets.Table)
   */
  @Override
  public void addTableColumns(Table table) {
    TableColumn column1 = new TableColumn(table, SWT.NONE);
    column1.setWidth(150);
    column1.setText(BAiPlugin.getDefault().getString(COL1));
    column1.setAlignment(SWT.LEFT);

    TableColumn column2 = new TableColumn(table, SWT.NONE);
    column2.setWidth(250);
    column2.setText(BAiPlugin.getDefault().getString(COL2));
    column2.setAlignment(SWT.LEFT);

    TableColumn column3 = new TableColumn(table, SWT.NONE);
    column3.setWidth(350);
    column3.setText(BAiPlugin.getDefault().getString(COL3));
    column3.setAlignment(SWT.LEFT);
  }
}
