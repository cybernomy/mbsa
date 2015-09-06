/*
 * ElectronicAdressTableModel.java
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
 * Модель таблицы "Электронный адрес"
 *
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: ElectronicAdressTableModel.java,v 1.2 2008/03/04 08:55:01 alikaev Exp $
 */
public class ElectronicAdressTableModel extends AbstractTableModel {

  // Fields

  private String[] columnsName = new String[]{
      Messages.getInstance().getMessage(Messages.ID),
      Messages.getInstance().getMessage(Messages.ELECTRONIC_ADRESS_KIND),
      Messages.getInstance().getMessage(Messages.ELECTRONIC_ADRESS_PROTOCOL),
      Messages.getInstance().getMessage(Messages.ELECTRONIC_ADRESS_NAME),
      Messages.getInstance().getMessage(Messages.ELECTRONIC_ADRESS_IS_ACTIVE)
  };
  private List<ElectronicAdressItem> electronicAdressModel;
  private Integer selectedElectronicAdressId;

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
    return electronicAdressModel.size();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
    if (rows.length == 0)
      selectedElectronicAdressId = null;
    else {
      ElectronicAdressItem item = electronicAdressModel.get(rows[0]);
      selectedElectronicAdressId = (Integer) item.getId();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int column) {
    ElectronicAdressItem item = electronicAdressModel.get(row);
    switch (column) {
      case 0:
        return item.getId();
      case 1:
        return item.getAddressKind();
      case 2:
        return item.getProtocol();
      case 3:
        return item.getAddress();
      case 4:
        return item.isActive(); //$NON-NLS-1$ //$NON-NLS-2$
      default:
        return StringUtils.EMPTY_STRING; //$NON-NLS-1$
    }
  }

  /**
   * @return the selectedPhoneId
   */
  public Integer getSelectedElectronicAdressId() {
    return selectedElectronicAdressId;
  }

  /**
   * @return the electronicAdressModel
   */
  public List<ElectronicAdressItem> getElectronicAdressModel() {
    return electronicAdressModel;
  }

  /**
   * @param electronicAdressModel the electronicAdressModel to set
   */
  public void setElectronicAdressModel(
      List<ElectronicAdressItem> electronicAdressModel) {
    this.electronicAdressModel = electronicAdressModel;
  }

}
