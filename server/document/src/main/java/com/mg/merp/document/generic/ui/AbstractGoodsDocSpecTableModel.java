/**
 * AbstractGoodsDocSpecTableModel.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document.generic.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.model.DocSpec;

import java.util.Set;

/**
 * Базовый класс модели таблицы для отображения спецификаций документа в дополнительном браузере
 * расположенном в основном браузере документов
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractGoodsDocSpecTableModel.java,v 1.2 2009/02/17 12:34:36 safonov Exp $
 */
public abstract class AbstractGoodsDocSpecTableModel extends
    DefaultMaintenanceEJBQLTableModel {
  final private static String LOAD_DOCSPEC_QUERY_TEXT = "select %s from %s ds %s where ds.DocHead.Id = :docHeadId";

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Id", "ds.Id", true));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.Code", "ds.Catalog.Code", true));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.FullName", "ds.Catalog.FullName", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure1", "meas1.Code", "left join ds.Measure1 as meas1", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity", "ds.Quantity", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure2", "meas2.Code", "left join ds.Measure2 as meas2", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity2", "ds.Quantity2", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Weight", "ds.Weight", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Volume", "ds.Volume", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "BestBefore", "ds.BestBefore", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.Articul", "ds.Catalog.Articul", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Contractor", "contr.Code", "left join ds.Contractor as contr", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "Comment", "ds.Comment", false));
    result.add(new TableEJBQLFieldDef(DocSpec.class, "TaxGroup", "tg.Code", "left join ds.TaxGroup as tg", false));
    return result;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected void doLoad() {
    if (getMasterKey() != null)
      setQuery(createQueryText(), new String[]{"docHeadId"}, new Object[]{getMasterKey()});
    else {
      this.rowList.clear();
      fireModelChange();
    }
  }

  /**
   * Формирование запроса для отображение в таблице спецификаций
   *
   * @param docHead - документ
   */
  private String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(LOAD_DOCSPEC_QUERY_TEXT, fieldsList, service.getEntityClass().getName(), fromList);
  }

}
