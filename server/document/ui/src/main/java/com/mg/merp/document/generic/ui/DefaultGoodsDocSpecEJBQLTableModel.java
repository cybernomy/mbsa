/*
 * GoodsDocSpecDefaultEJBQLTableModel.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;

import java.util.Set;

/**
 * Стандартная модель таблицы спецификаций для отображения формы списка спецификаций товарных
 * документов
 *
 * @author Konstantin S. Alikaev
 * @version $Id: DefaultGoodsDocSpecEJBQLTableModel.java,v 1.1 2009/02/12 08:22:23 safonov Exp $
 */
public class DefaultGoodsDocSpecEJBQLTableModel extends AbstractGoodsDocSpecTableModel {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
  }

}
