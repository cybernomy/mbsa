/*
 * PhoneListModel.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.crm.support.Messages;

import java.util.List;

/**
 * Модель таблицы "Телефоны"
 *
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PhoneListModel.java,v 1.2 2008/03/04 08:55:01 alikaev Exp $
 */
public class PhoneListModel extends AbstractTableModel {

  // Fields
  private String[] columnsName = new String[]{
      Messages.getInstance().getMessage(Messages.ID),
      Messages.getInstance().getMessage(Messages.PHONE_AREA_CODE),
      Messages.getInstance().getMessage(Messages.PHONE_NAME),
      Messages.getInstance().getMessage(Messages.PHONE_KIND),
  };
  private List<PhoneItem> phoneListModel;
  private Integer selectedPhoneId;

  // Methods

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
   */
  public int getColumnCount() {
    return columnsName.length;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
   */
  public String getColumnName(int column) {
    return columnsName[column];
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
   */
  public int getRowCount() {
    return phoneListModel.size();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
    if (rows.length == 0)
      selectedPhoneId = null;
    else {
      PhoneItem phoneItem = phoneListModel.get(rows[0]);
      selectedPhoneId = (Integer) phoneItem.getId();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int column) {
    PhoneItem phoneItem = phoneListModel.get(row);
    switch (column) {
      case 0:
        return phoneItem.getId();
      case 1:
        return phoneItem.getAreaCode();
      case 2:
        return phoneItem.getPhone();
      case 3:
        return phoneItem.getPhoneKind();
      default:
        return StringUtils.EMPTY_STRING;
    }
  }

  /**
   * @return the selectedPhoneId
   */
  public Integer getSelectedPhoneId() {
    return selectedPhoneId;
  }

  /**
   * @return the phoneListModel
   */
  public List<PhoneItem> getPhoneListModel() {
    return phoneListModel;
  }

  /**
   * @param phoneListModel the phoneListModel to set
   */
  public void setPhoneListModel(List<PhoneItem> phoneListModel) {
    this.phoneListModel = phoneListModel;
  }

}
