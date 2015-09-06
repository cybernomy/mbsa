/*
 * ColumnMappingElement.java
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
import com.mg.merp.wb.report.birt.data.oda.badi.util.DataTypes;

import org.eclipse.datatools.connectivity.oda.OdaException;

import java.util.HashMap;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ColumnMappingElement.java,v 1.6 2007/08/30 15:05:37 safonov Exp $
 */
public class ColumnMappingElement {
  private static HashMap<String, Integer> nameDisplayNameMapping = new HashMap<String, Integer>();

  static {
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.dateTime"), new Integer(DataTypes.TIMESTAMP));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.decimal"), new Integer(DataTypes.BIGDECIMAL));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.float"), new Integer(DataTypes.DOUBLE));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.integer"), new Integer(DataTypes.INT));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.date"), new Integer(DataTypes.DATE));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.time"), new Integer(DataTypes.TIME));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.string"), new Integer(DataTypes.STRING));
    nameDisplayNameMapping.put(OdaUiPlugin.getDefault().getString("datatypes.boolean"), new Integer(DataTypes.BOOLEAN));
  }

  //column name
  String columnName;
  //column type
  String type;

  String getColumnName() {
    return columnName;
  }

  void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  String getType() {
    return type;
  }

  void setType(String type) {
    this.type = type;
  }

  String getTypeStandardString() {
    Integer type = nameDisplayNameMapping.get(this.type);
    if (type != null)
      try {
        return DataTypes.getTypeString(type);
      } catch (NumberFormatException e) {
        return null;
      } catch (OdaException e) {
        return null;
      }
    else
      return null;
  }
}
