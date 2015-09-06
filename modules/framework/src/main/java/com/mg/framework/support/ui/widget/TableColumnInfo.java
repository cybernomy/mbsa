/**
 * TableColumnInfo.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.support.ui.widget;

import java.io.Serializable;

/**
 * Информация о столбцах таблицы
 *
 * @author Oleg V. Safonov
 * @version $Id: TableColumnInfo.java,v 1.1 2008/12/23 09:19:54 safonov Exp $
 */
public class TableColumnInfo implements Serializable {
  private String name;
  private String title;
  private boolean isVisible;
  private boolean isMandatory;

  /**
   * @param name        имя поля отображаемого в данном столбце
   * @param title       заголовок столбца в UI
   * @param isVisible   признак показа в UI
   * @param isMandatory признак обязательного присутствия в списке столбцов
   */
  public TableColumnInfo(String name, String title, boolean isVisible, boolean isMandatory) {
    super();
    this.name = name;
    this.title = title;
    this.isVisible = isVisible;
    this.isMandatory = isMandatory;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the isVisible
   */
  public boolean isVisible() {
    return isVisible;
  }

  /**
   * @param isVisible the isVisible to set
   */
  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible || isMandatory;
  }

  /**
   * @return the isMandatory
   */
  public boolean isMandatory() {
    return isMandatory;
  }

}
