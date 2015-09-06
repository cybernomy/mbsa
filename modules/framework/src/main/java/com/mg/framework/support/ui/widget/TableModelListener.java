/*
 * TableModelListener.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;
import java.util.EventListener;

/**
 * Table model listener
 *
 * @author Oleg V. Safonov
 * @version $Id: TableModelListener.java,v 1.3 2006/08/31 08:49:30 safonov Exp $
 */
public interface TableModelListener extends EventListener, Serializable {

  /**
   * Notify when all cells have been changed
   */
  void tableChanged();

  /**
   * Notify when a range of rows has been removed
   */
  void tableRowsDeleted(int firstRow, int lastRow);

  /**
   * Notify when a range of rows has been updated
   */
  void tableRowsUpdated(int firstRow, int lastRow);

  /**
   * Notify when a range of rows has been inserted
   */
  void tableRowsInserted(int firstRow, int lastRow);

  /**
   * Notify wher structure of table have been changed
   */
  void tableStructureChanged();

  /**
   * Notify when a single cell has been updated
   */
  void tableCellUpdated(int row, int column);
}
