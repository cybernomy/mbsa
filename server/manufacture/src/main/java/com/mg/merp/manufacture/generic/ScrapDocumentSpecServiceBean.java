/*
 * ScrapDocumentSpecServiceBean.java
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

package com.mg.merp.manufacture.generic;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.manufacture.ScrapSpecificationServiceLocal;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;
import com.mg.merp.manufacture.support.CreateManufactureSpecificationInfoImpl;

import javax.annotation.security.PermitAll;

/**
 * Базовая реализация актов на списание потерь
 *
 * @author Oleg V. Safonov
 * @version $Id: ScrapDocumentSpecServiceBean.java,v 1.4 2007/08/16 14:07:09 safonov Exp $
 */
public abstract class ScrapDocumentSpecServiceBean<T extends ScrapDocumentSpec> extends ManufactureDocumentSpecServiceBean<T, Integer>
    implements ScrapSpecificationServiceLocal<T> {

  protected abstract void doCreateSpecifications(ScrapDocumentHead docHead);

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#initializeForBulkCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.CreateSpecificationInfo)
   */
  @Override
  protected T initializeForBulkCreate(DocHead docHead, CreateSpecificationInfo goodsInfo) {
    T result = super.initializeForBulkCreate(docHead, goodsInfo);
    if (goodsInfo instanceof CreateManufactureSpecificationInfoImpl) {
      CreateManufactureSpecificationInfoImpl manufactureSpec = (CreateManufactureSpecificationInfoImpl) goodsInfo;
      result.setMeasure1(manufactureSpec.getMeasure1());
      result.setMeasure2(manufactureSpec.getMeasure2());
      result.setJobRouteResource(manufactureSpec.getJobRouteResource());
      result.setCostCategory(manufactureSpec.getCostCategory());
    }
    return result;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.ScrapSpecificationServiceLocal#createSpecifications(com.mg.merp.manufacture.model.ScrapDocumentHead)
   */
  @PermitAll
  public void createSpecifications(ScrapDocumentHead docHead) {
    //проверим права на метод "создать", не имеет смысла создавать дополнительный метод
    SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));

    if (docHead.getDetectJob() == null || docHead.getDetectOper() == null)
      return;

    clear(docHead);
    doCreateSpecifications(getPersistentManager().find(ReflectionUtils.getEntityClass(docHead), docHead.getId()));
  }

}
