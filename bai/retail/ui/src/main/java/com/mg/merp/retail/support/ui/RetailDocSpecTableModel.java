/**
 * RetailDocSpecTableModel.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.retail.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.AbstractGoodsDocSpecTableModel;
import com.mg.merp.retail.model.RtlInvoiceSpec;

import java.util.Set;

/**
 * Модель списка спецификаций дополнительно браузера спецификаций
 *
 * @author Oleg V. Safonov
 * @version $Id: RetailDocSpecTableModel.java,v 1.1 2009/02/12 08:23:40 safonov Exp $
 */
public class RetailDocSpecTableModel extends AbstractGoodsDocSpecTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.AbstractGoodsDocSpecTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(RtlInvoiceSpec.class, "Price1", "ds.Price1", false));
    result.add(new TableEJBQLFieldDef(RtlInvoiceSpec.class, "Summa1", "ds.Summa1", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
  }

}
