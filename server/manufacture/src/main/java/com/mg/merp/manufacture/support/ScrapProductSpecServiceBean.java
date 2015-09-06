/*
 * ScrapProductSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.manufacture.ScrapProductHeadServiceLocal;
import com.mg.merp.manufacture.ScrapProductSpecServiceLocal;
import com.mg.merp.manufacture.generic.ManufactureDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.ScrapDocumentHead;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация актов на списание потерь с операции
 *
 * @author leonova
 * @version $Id: ScrapProductSpecServiceBean.java,v 1.7 2007/08/16 14:07:08 safonov Exp $
 */
@Stateless(name = "merp/manufacture/ScrapProductSpecService")
public class ScrapProductSpecServiceBean extends ManufactureDocumentSpecServiceBean<DocSpec, Integer> implements ScrapProductSpecServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return ScrapProductHeadServiceLocal.DOCSECTION;
  }

  protected void doCreateSpecifications(ScrapDocumentHead docHead) {
    DocSpec spec = initialize();
    Job job = docHead.getDetectJob();
    spec.setDocHead(docHead);
    spec.setCatalog(job.getCatalog());
    spec.setQuantity(job.getQtyReleased().subtract(job.getQtyComplete().subtract(job.getQtyScrapped())));
    spec.setMeasure1(job.getCatalog().getMeasure1());

    create(spec);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.ScrapProductSpecServiceLocal#createSpecifications(com.mg.merp.manufacture.model.ScrapDocumentHead)
   */
  @PermitAll
  public void createSpecifications(ScrapDocumentHead docHead) {
    //проверим права на методо "создать", не имеет смысла создавать дополнительный метод
    SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));

    if (docHead.getDetectJob() == null)
      return;

    clear(docHead);
    doCreateSpecifications(getPersistentManager().find(ReflectionUtils.getEntityClass(docHead), docHead.getId()));
  }

}
