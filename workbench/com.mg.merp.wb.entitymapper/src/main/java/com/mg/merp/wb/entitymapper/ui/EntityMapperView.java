/* EntityMapperView.java
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
package com.mg.merp.wb.entitymapper.ui;

import com.mg.merp.wb.core.ui.view.StandartBrowserView;
import com.mg.merp.wb.entitymapper.Activator;
import com.mg.merp.wb.entitymapper.ui.support.EntityMapperViewController;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperView.java,v 1.1 2007/05/07 13:08:48 poroxnenko Exp $
 */
public class EntityMapperView extends StandartBrowserView<EntityMapperViewController> {

  public static final String ID = "com.mg.merp.wb.entitymapper.ui.EntityMapperView";
  private static final String COL1 = "table.columns.col1";
  private static final String COL2 = "table.columns.col2";
  private static final String COL3 = "table.columns.col3";

  public EntityMapperView() {
    viewController = new EntityMapperViewController(this);
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
    column1.setText(Activator.getDefault().getString(COL1));
    column1.setAlignment(SWT.LEFT);

    TableColumn column2 = new TableColumn(table, SWT.NONE);
    column2.setWidth(250);
    column2.setText(Activator.getDefault().getString(COL2));
    column2.setAlignment(SWT.LEFT);

    TableColumn column3 = new TableColumn(table, SWT.NONE);
    column3.setWidth(350);
    column3.setText(Activator.getDefault().getString(COL3));
    column3.setAlignment(SWT.LEFT);
  }
}
