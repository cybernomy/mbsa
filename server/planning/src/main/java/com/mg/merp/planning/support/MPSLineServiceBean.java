/*
 * MPSLineServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.planning.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.manufacture.BOMNotFoundException;
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.BucketRange;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.planning.MPSLineServiceLocal;
import com.mg.merp.planning.model.MpsLine;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Строки производственного плана"
 *
 * @author leonova
 * @version $Id: MPSLineServiceBean.java,v 1.5 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name = "merp/planning/MPSLineService")
public class MPSLineServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MpsLine, Integer> implements MPSLineServiceLocal {

  private void internalFirm(int[] list) {
    for (int id : list) {
      MpsLine line = load(id);
      if (MathUtils.compareToZero(line.getPlannedQty().add(line.getAdjustmentQty())) > 0)
        line.setFirmPlanSuggested(true);
    }
  }

  private void internalCreateJob(int folderId, int mpsId) {
    JobServiceLocal jobService = (JobServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobServiceLocal.SERVICE_NAME);
    BOMServiceLocal bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    List<MpsLine> lines = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(MpsLine.class)
        .add(Restrictions.eq("Mps.Id", mpsId))
        .add(Restrictions.eq("FirmPlanSuggested", true))
        .add(Restrictions.eq("MpsOrdered", false)));
    for (MpsLine line : lines) {
      Bom bom = bomService.findCurrentBOM(line.getPlanningItem().getCatalog().getId());
      if (bom == null)
        throw new BOMNotFoundException(line.getPlanningItem().getCatalog());
      Job job = jobService.initialize();
      job.setJobDate(DateTimeUtils.nowDate());
      job.setFolder(getPersistentManager().find(Folder.class, folderId));
      job.setCatalog(line.getPlanningItem().getCatalog());
      job.setQtyReleased(line.getPlannedQty().add(line.getAdjustmentQty()));
      job.setQtyComplete(BigDecimal.ZERO);
      job.setQtyScrapped(BigDecimal.ZERO);
      job.setDefSrcStock(bom.getDefSrcStock());
      job.setDefSrcMol(bom.getDefSrcMol());
      job.setDefDstStock(bom.getDefDstStock());
      job.setDefDstMol(bom.getDefDstMol());

      BucketRange bucketRange = MfUtils.determineBucketRange(line.getMps().getPlanningLevel().getId(), line.getBucketOffset());
      job.setStartDate(bucketRange.getBucketEnd());
      job.setEndDate(bucketRange.getBucketEnd());
      job.setMrpEndDate(bucketRange.getBucketEnd());

      jobService.create(job);
      jobService.copyBOM(job);
      //TODO реализовать формирование номера ЗНП
      job.setJobNumber(String.valueOf(job.getId()));

      line.setMpsOrdered(true);
    }
  }

  /* (non-Javadoc)
  * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
  */
  @Override
  protected void onInitialize(MpsLine entity) {
    entity.setAdjustmentQty(BigDecimal.ZERO);
    entity.setDemandQty(BigDecimal.ZERO);
    entity.setPlannedQty(BigDecimal.ZERO);
    entity.setDependantDemand(BigDecimal.ZERO);
    entity.setProductionDemandQty(BigDecimal.ZERO);
    entity.setProductionProfileQty(BigDecimal.ZERO);
    entity.setProductionQty(BigDecimal.ZERO);
    entity.setPurchaseForecastQty(BigDecimal.ZERO);
    entity.setPurchaseOrderQty(BigDecimal.ZERO);
    entity.setPurchaseQty(BigDecimal.ZERO);
    entity.setQtyAvailable(BigDecimal.ZERO);
    entity.setSalesForecastQty(BigDecimal.ZERO);
    entity.setSalesOrderQty(BigDecimal.ZERO);
    entity.setSalesQty(BigDecimal.ZERO);
    entity.setTransfersInQty(BigDecimal.ZERO);
    entity.setTransfersOutQty(BigDecimal.ZERO);
    entity.setLiveProductionDemand(BigDecimal.ZERO);
    entity.setSafetyLevelQty(BigDecimal.ZERO);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MPSLineServiceLocal#firm(int[])
   */
  public void firm(int[] list) {
    internalFirm(list);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MPSLineServiceLocal#createJob(int, int)
   */
  public void createJob(int folderId, int mpsId) {
    internalCreateJob(folderId, mpsId);
  }
}
