/*
 * OutputProductSpecServiceBean.java
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.manufacture.OutputProductHeadServiceLocal;
import com.mg.merp.manufacture.OutputProductSpecServiceLocal;
import com.mg.merp.manufacture.generic.ManufactureDocumentSpecServiceBean;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.OutputProductHead;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация актов выпкуска готовой продукции"
 *
 * @author leonova
 * @version $Id: OutputProductSpecServiceBean.java,v 1.7 2007/08/16 14:07:09 safonov Exp $
 */
@Stateless(name = "merp/manufacture/OutputProductSpecService")
public class OutputProductSpecServiceBean extends ManufactureDocumentSpecServiceBean<DocSpec, Integer> implements OutputProductSpecServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return OutputProductHeadServiceLocal.DOCSECTION;
  }

  protected void doCreateSpecifications(OutputProductHead docHead) {
    DocSpec spec = initialize();
    Job job = docHead.getJob();
    spec.setDocHead(docHead);
    spec.setCatalog(job.getCatalog());
    spec.setQuantity(job.getQtyReleased().subtract(job.getQtyComplete().subtract(job.getQtyScrapped())));
    spec.setMeasure1(job.getCatalog().getMeasure1());

    create(spec);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.OutputProductSpecServiceLocal#createSpecifications(com.mg.merp.manufacture.model.OutputProductHead)
   */
  @PermitAll
  public void createSpecifications(OutputProductHead docHead) {
    //проверим права на методо "создать", не имеет смысла создавать дополнительный метод
    SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));

    if (docHead.getJob() == null)
      return;

    clear(docHead);
    doCreateSpecifications(getPersistentManager().find(ReflectionUtils.getEntityClass(docHead), docHead.getId()));
  }

}
