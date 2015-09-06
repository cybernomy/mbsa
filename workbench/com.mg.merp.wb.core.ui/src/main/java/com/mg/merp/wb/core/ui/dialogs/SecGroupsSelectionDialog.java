/* SecGroupsSelectionDialog.java
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
package com.mg.merp.wb.core.ui.dialogs;

import com.mg.merp.security.model.Groups;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.support.connector.WorkbenchService;
import com.mg.merp.wb.core.ui.UiPlugin;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.SelectionDialog;

import java.util.List;

/**
 * Диалог выбора групп пользователей
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: SecGroupsSelectionDialog.java,v 1.2 2007/07/11 05:57:03 poroxnenko Exp $
 */
public class SecGroupsSelectionDialog extends SelectionDialog {

  public static final String TITLE = "dialogs.groups.title";

  private static final String LOAD_GROUPS_ERR = "dialog.groups.load.error";

  private List<Groups> groups;

  private TableViewer tableViewer;

  private int selectedRecordIndex;

  public SecGroupsSelectionDialog() {
    super(CoreUtils.getMainShell());
    try {
      this.groups = WorkbenchService.getSecGroups();
    } catch (Exception ex) {
      Dialogs.openError(UiPlugin.getDefault().getString(LOAD_GROUPS_ERR), UiPlugin.getDefault()
          .getString(UiPlugin.CHECK_SERVER), UiPlugin.ID, ex);
    }
    setTitle(UiPlugin.getDefault().getString(TITLE));
  }

  protected Control createDialogArea(Composite parent) {
    // page group
    Composite composite = (Composite) super.createDialogArea(parent);

    Font font = parent.getFont();
    composite.setFont(font);

    createMessageArea(composite);

    createTableArea(parent);
    return composite;
  }

  public Object[] getResult() {
    setResult(groups);
    return super.getResult();
  }

  private void createTableArea(Composite parent) {
    Table tblGroups = new Table(parent, SWT.BORDER | SWT.V_SCROLL
        | SWT.H_SCROLL | SWT.FULL_SELECTION);
    tblGroups.setHeaderVisible(true);
    tblGroups.setLinesVisible(false);

    TableColumn colGroup = new TableColumn(tblGroups, SWT.NONE);
    colGroup.setWidth(350);
    colGroup.setAlignment(SWT.LEFT);

    tableViewer = new TableViewer(tblGroups);

    tableViewer.setContentProvider(new IStructuredContentProvider() {

      @SuppressWarnings("unchecked")
      public Object[] getElements(Object inputElement) {
        return ((List<Groups>) inputElement).toArray();
      }

      public void dispose() {
        // TODO Auto-generated method stub
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub
      }

    });
    tableViewer.setLabelProvider(new ITableLabelProvider() {

      public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
      }

      public String getColumnText(Object element, int columnIndex) {
        Groups group = (Groups) element;
        return group.getName().trim();
      }

      public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
      }

      public void dispose() {
        // TODO Auto-generated method stub
      }

      public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
      }

      public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

      }

    });

    tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        selectedRecordIndex = ((TableViewer) event.getSource()).getTable().getSelectionIndices()[0];
      }

    });

    tableViewer.setInput(groups);

    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    data.heightHint = 370;
    data.widthHint = 370;
    tableViewer.getTable().setLayoutData(data);
  }

  public Groups getSelectedGroup() {
    return groups.get(selectedRecordIndex);
  }

}
