/**
 * InventoryActAggregatePropertiesStrategy.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.warehouse.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.generic.DefaultDocumentAggregatePropertiesStrategy;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.warehouse.InventoryActHeadServiceLocal;
import com.mg.merp.warehouse.model.InventoryActHead;

import java.math.BigDecimal;

/**
 * Реализация стратегии изменения агрегирующих свойств актов инвенторизации
 *
 * @author Oleg V. Safonov
 * @version $Id: InventoryActAggregatePropertiesStrategy.java,v 1.1 2007/09/26 09:51:31 safonov Exp
 *          $
 */
public class InventoryActAggregatePropertiesStrategy extends
    DefaultDocumentAggregatePropertiesStrategy {

  public InventoryActAggregatePropertiesStrategy(InventoryActHeadServiceLocal service, DocSpec[] docSpecs, boolean isRemove, int currencyScale) {
    super(service, docSpecs, new String[]{"SumCur", "Volume", "Weight", "RealSummaCur"}, new String[]{"Summa", "Volume", "Weight", "RealSumma"}, isRemove, currencyScale);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DefaultDocumentAggregatePropertiesStrategy#doAdjustDocHead(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected void doAdjustDocHead(DocHead docHead) {
    super.doAdjustDocHead(docHead);
    InventoryActHead inventoryActHead = (InventoryActHead) docHead;
    BigDecimal realDocSumCur = inventoryActHead.getRealSummaCur();
    BigDecimal realDocSumNat = MathUtils.multiply(realDocSumCur, docHead.getCurCource(), new RoundContext(getCurrencyScale()));
    inventoryActHead.setRealSummaNat(realDocSumNat);
  }

}
