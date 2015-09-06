/*
 * ColumnMappingTableViewer.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.wb.report.birt.data.oda.badi.ui.wizards;

import com.mg.merp.wb.report.birt.data.oda.badi.ui.OdaUiPlugin;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ColumnMappingTableViewer.java,v 1.6 2007/08/30 15:05:37 safonov Exp $
 */
public final class ColumnMappingTableViewer {
  private static HashMap<Integer, String> typeIdDisplayNameMapping = new HashMap<Integer, String>();

  static {
    typeIdDisplayNameMapping.put(DataTypes.TIMESTAMP,
        OdaUiPlugin.getDefault().getString("datatypes.dateTime"));
    typeIdDisplayNameMapping.put(DataTypes.BIGDECIMAL,
        OdaUiPlugin.getDefault().getString("datatypes.decimal"));
    typeIdDisplayNameMapping.put(DataTypes.DOUBLE, OdaUiPlugin
        .getDefault().getString("datatypes.float"));
    typeIdDisplayNameMapping.put(DataTypes.INT, OdaUiPlugin
        .getDefault().getString("datatypes.integer"));
    typeIdDisplayNameMapping.put(DataTypes.DATE, OdaUiPlugin
        .getDefault().getString("datatypes.date"));
    typeIdDisplayNameMapping.put(DataTypes.TIME, OdaUiPlugin
        .getDefault().getString("datatypes.time"));
    typeIdDisplayNameMapping.put(DataTypes.STRING, OdaUiPlugin
        .getDefault().getString("datatypes.string"));
    typeIdDisplayNameMapping.put(DataTypes.BOOLEAN, OdaUiPlugin
        .getDefault().getString("datatypes.boolean"));
  }

  private TableViewer viewer;
  private Composite mainControl;
  private Button btnRemove;
  private Button btnUp;
  private Button btnDown;
  private MenuItem itmRemove;
  private MenuItem itmRemoveAll;

  /**
   * column mapping table viewer. it supplys the button of remove, up , down and the menu of
   * remove,removeAll....
   */
  public ColumnMappingTableViewer(Composite parent, boolean showMenus,
                                  boolean showButtons, boolean enableKeyStrokes) {
    mainControl = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    mainControl.setLayout(layout);

    GridData data = null;
    viewer = new TableViewer(mainControl, SWT.FULL_SELECTION);
    data = new GridData(GridData.FILL_BOTH);
    viewer.getControl().setLayoutData(data);

    viewer.getTable().setHeaderVisible(true);
    viewer.getTable().setLinesVisible(true);

    if (showButtons) {
      Composite btnComposite = new Composite(mainControl, SWT.NONE);
      data = new GridData();
      data.verticalAlignment = SWT.CENTER;
      btnComposite.setLayoutData(data);
      GridLayout btnLayout = new GridLayout();
      layout.verticalSpacing = 20;
      btnComposite.setLayout(btnLayout);

      GridData btnData = new GridData(GridData.CENTER);
      btnData.widthHint = 20;
      btnData.heightHint = 20;
      btnUp = new Button(btnComposite, SWT.ARROW | SWT.UP);
      btnUp.setLayoutData(btnData);

      btnData = new GridData(GridData.CENTER);
      btnData.widthHint = 20;
      btnData.heightHint = 20;
      btnRemove = new Button(btnComposite, SWT.PUSH);
      btnRemove.setImage(PlatformUI.getWorkbench().getSharedImages()
          .getImage(ISharedImages.IMG_TOOL_DELETE));
      btnRemove.setLayoutData(btnData);
      btnRemove.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent e) {
        }

        public void widgetDefaultSelected(SelectionEvent e) {
        }

      });

      btnData = new GridData(GridData.CENTER);
      btnData.widthHint = 20;
      btnData.heightHint = 20;
      btnDown = new Button(btnComposite, SWT.ARROW | SWT.DOWN);
      btnDown.setLayoutData(btnData);
      btnDown.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent e) {

        }

        public void widgetDefaultSelected(SelectionEvent e) {
        }

      });
    }

    if (showMenus) {
      Menu menu = new Menu(viewer.getTable());
      menu.addMenuListener(new MenuAdapter() {

        public void menuShown(MenuEvent e) {
          viewer.cancelEditing();
        }
      });
      itmRemove = new MenuItem(menu, SWT.NONE);
      itmRemove.setText(OdaUiPlugin.getDefault().getString(
          "menu.menuItem.remove")); //$NON-NLS-1$

      itmRemoveAll = new MenuItem(menu, SWT.NONE);
      itmRemoveAll.setText(OdaUiPlugin.getDefault().getString(
          "menu.menuItem.removeAll")); //$NON-NLS-1$

      viewer.getTable().setMenu(menu);
    }

    if (enableKeyStrokes) {
      viewer.getTable().addKeyListener(new KeyListener() {

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
          if (e.keyCode == SWT.DEL) {
          }
        }

      });
    }
  }

  /**
   * get the table viewer
   */
  public TableViewer getViewer() {
    return viewer;
  }

  /**
   * get the main control
   */
  public Composite getControl() {
    return mainControl;
  }

  /**
   * get the up button
   */
  public Button getUpButton() {
    return btnUp;
  }

  /**
   * get the down button
   */
  public Button getDownButton() {
    return btnDown;
  }

  /**
   * get the remove button
   */
  public Button getRemoveButton() {
    return btnRemove;
  }

  /**
   * get the remove menu item
   */
  public MenuItem getRemoveMenuItem() {
    return itmRemove;
  }

  /**
   * get the remove all menu item
   */
  public MenuItem getRemoveAllMenuItem() {
    return itmRemoveAll;
  }

  /**
   * Accoring to the relation infomation of the query, refresh the table items
   */
  public List<ColumnMappingElement> refresh(RelationInformation info,
                                            Map<String, ColumnMappingElement> columnMapping) {
    ArrayList<ColumnMappingElement> columnsList = new ArrayList<ColumnMappingElement>();
    if (info == null)
      return columnsList;
    String[] columnName = info.getTableColumnNames();
    String[] columnType = new String[columnName.length];
    ColumnMappingElement element = null;
    for (int i = 0; i < columnName.length; i++) {
      columnType[i] = info.getTableColumnType(columnName[i]);
      element = new ColumnMappingElement();
      element.setColumnName(columnName[i]);
      try {
        //Set type to its display name.
        element.setType(typeIdDisplayNameMapping.get(
            DataTypes.getType(columnType[i])).toString());
      } catch (OdaException e) {
        //Should not arrive here.
      }
      columnMapping.put(columnName[i], element);
      columnsList.add(element);
    }
    return columnsList;
  }
}
