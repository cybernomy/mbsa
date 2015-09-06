/*
 * PermissionForSubsystemBusinessObjectsDlg.java
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
package com.mg.merp.security.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.security.support.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер диалога установки/сброса прав для всех бизнес-компонентов модуля
 *
 * @author Artem V. Sharapov
 * @version $Id: PermissionForSubsystemBusinessObjectsDlg.java,v 1.2 2008/07/15 08:18:24 safonov Exp
 *          $
 */
public class PermissionForSubsystemBusinessObjectsDlg extends DefaultDialog {

  public static String FORM_NAME = "com.mg.merp.security.PermissionForSubsystemBusinessObjectsDlg"; //$NON-NLS-1$
  protected DefaultTableController methodsTable;
  private String moduleName;
  private Integer permissionAction = 0;
  private List<MethodsTableModelItem> methodsTableItemList = new ArrayList<MethodsTableModelItem>();


  public PermissionForSubsystemBusinessObjectsDlg() {
    methodsTable = new DefaultTableController(new MethodsTableModel());
  }

  /**
   * Запустить диалог на показ
   *
   * @param moduleName - наименование модуля
   */
  public void execute(String moduleName) {
    this.moduleName = moduleName;
    loadMethodsTable();
    execute();
  }

  /**
   * Получить список выбранных методов
   *
   * @return список выбранных методов
   */
  public List<String> getSelectedMethodsList() {
    List<String> selectedMethods = new ArrayList<String>();
    for (MethodsTableModelItem methodsTableModelItem : methodsTableItemList) {
      if (methodsTableModelItem.getIsChecked())
        selectedMethods.add(methodsTableModelItem.getMethodName());
    }
    return selectedMethods;
  }

  /**
   * Получить признак установки/сброса прав
   *
   * @return <code>true</code> - установка прав; <code>false</code> - во всех остальных случаях
   */
  public boolean isGrantPermission() {
    return permissionAction == 0;
  }

  /**
   * Загрузить данные в таблицу
   */
  protected void loadMethodsTable() {
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.LOAD_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.BROWSE_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.CREATE_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.ERASE_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.STORE_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.MOVE_METHOD));
    methodsTableItemList.add(new MethodsTableModelItem(BusinessMethodPermission.CHANGE_HIERARCHY_METHOD));
  }

  /**
   * @return the moduleName
   */
  public String getModuleName() {
    return this.moduleName;
  }

  /**
   * @param moduleName the moduleName to set
   */
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  private class MethodsTableModel extends AbstractTableModel {
    private String[] columnsNames = null;

    public MethodsTableModel() {
      initializeColumnsNames();
    }

    private void initializeColumnsNames() {
      Messages msg = Messages.getInstance();
      columnsNames = new String[]{msg.getMessage(Messages.SELECTED), msg.getMessage(Messages.METHOD_NAME)};
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
     */
    public String getColumnName(int column) {
      return columnsNames[column];
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
      MethodsTableModelItem item = methodsTableItemList.get(row);
      switch (column) {
        case 0:
          return item.getIsChecked();
        case 1:
          return item.getMethodName();
        default:
          return StringUtils.EMPTY_STRING;
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
     */
    public int getColumnCount() {
      return columnsNames.length;
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
     */
    public int getRowCount() {
      return methodsTableItemList.size();
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return columnIndex == 0;
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
      switch (column) {
        case 0:
          methodsTableItemList.get(row).setChecked((Boolean) value);
          break;
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
      if (column == 0)
        return Boolean.class;
      else
        return null;
    }
  }

  private class MethodsTableModelItem {
    private boolean isChecked;
    private String methodName;

    public MethodsTableModelItem(String methodName) {
      this(false, methodName);
    }

    /**
     * @param isChecked
     * @param methodName
     */
    public MethodsTableModelItem(boolean isCheked, String methodName) {
      this.isChecked = isCheked;
      this.methodName = methodName;
    }

    /**
     * @return the isChecked
     */
    public boolean getIsChecked() {
      return this.isChecked;
    }

    /**
     * @param isChecked the isChecked to set
     */
    public void setChecked(boolean isChecked) {
      this.isChecked = isChecked;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
      return this.methodName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
      this.methodName = methodName;
    }
  }

}
