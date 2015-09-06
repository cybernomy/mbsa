/*
 * JobServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.manufacture.BOMAlreadyExistException;
import com.mg.merp.manufacture.BOMNotFoundException;
import com.mg.merp.manufacture.InvalidJobStatusException;
import com.mg.merp.manufacture.JobLaborServiceLocal;
import com.mg.merp.manufacture.JobMachineServiceLocal;
import com.mg.merp.manufacture.JobMaterialServiceLocal;
import com.mg.merp.manufacture.JobRouteServiceLocal;
import com.mg.merp.manufacture.JobServiceLocal;
import com.mg.merp.manufacture.ManufactureProcessorServiceLocal;
import com.mg.merp.manufacture.ProductIsEmptyException;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.manufacture.model.JobRouteResource;
import com.mg.merp.manufacture.model.JobStatus;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.BomMachine;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.BomRouteResource;
import com.mg.merp.mfreference.model.LaborClass;
import com.mg.merp.mfreference.support.MfUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;


/**
 * Бизнес-компонент "Заказ-наряд на производство"
 *
 * @author leonova
 * @version $Id: JobServiceBean.java,v 1.10 2008/02/15 07:30:30 alikaev Exp $
 */
@Stateless(name = "merp/manufacture/JobService")
public class JobServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Job, Integer> implements JobServiceLocal {

  private void checkChangeStatus(Job job, JobStatus jobStatus) throws InvalidJobStatusException {
    switch (jobStatus) {
      case CREATED:
        break;
      case RUNNING:
        if (!EnumSet.of(JobStatus.CREATED, JobStatus.STOPPED).contains(job.getJobStatus()))
          invalidStatus(job.getJobStatus());
        break;
      case STOPPED:
        if (job.getJobStatus() != JobStatus.RUNNING)
          invalidStatus(job.getJobStatus());
        break;
      case COMPLETED:
        if (job.getJobStatus() == JobStatus.CREATED)
          invalidStatus(job.getJobStatus());
        break;
    }
  }

  private void internalChangeStatus(Job job, JobStatus jobStatus) {
    checkChangeStatus(job, jobStatus);
    job.setJobStatus(jobStatus);
  }

  private void invalidStatus(JobStatus status) throws InvalidJobStatusException {
    throw new InvalidJobStatusException(status);
  }

  private boolean checkBOM(Job job) {
    return job.getJobRoutes() == null || job.getJobRoutes().size() == 0;
  }

  private void createVarianceDocument(Job job) {
    ((ManufactureProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ManufactureProcessorServiceLocal.SERVICE_NAME))
        .createVarianceDocument(job);
  }

  private void internalComplete(Job job) {
    internalChangeStatus(job, JobStatus.COMPLETED);
    createVarianceDocument(job);
  }

  private void copyJobResource(JobRouteResource jobRouteResource, JobRoute jobRoute, BomRouteResource bomRouteResource) {
    jobRouteResource.setOper(jobRoute);
    jobRouteResource.setResourceGroup(bomRouteResource.getResourceGroup());
    jobRouteResource.setTimeSequence(bomRouteResource.getTimeSequence());
    jobRouteResource.setEffOnDate(bomRouteResource.getEffOnDate());
    jobRouteResource.setEffOffDate(bomRouteResource.getEffOffDate());
    jobRouteResource.setComment(bomRouteResource.getComment());
  }

  private void performCopyBOM(Job job, Bom bom) {
    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    JobRouteServiceLocal jobRouteService = (JobRouteServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobRouteServiceLocal.SERVICE_NAME);
    JobMaterialServiceLocal jobMaterialService = (JobMaterialServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobMaterialServiceLocal.SERVICE_NAME);
    JobMachineServiceLocal jobMachineService = (JobMachineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobMachineServiceLocal.SERVICE_NAME);
    JobLaborServiceLocal jobLaborService = (JobLaborServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobLaborServiceLocal.SERVICE_NAME);
    List<BomRoute> bomRoutes = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
        .add(Restrictions.eq("Bom", bom))
        .setFlushMode(FlushMode.MANUAL));
    for (BomRoute bomRoute : bomRoutes) {
      //copy routes
      JobRoute jobRoute = jobRouteService.initialize();
      jobRoute.setJob(job);
      jobRoute.setOperNum(bomRoute.getOperNum());
      jobRoute.setDescription(bomRoute.getDescription());
      jobRoute.setEffOnDate(bomRoute.getEffOnDate());
      jobRoute.setEffOffDate(bomRoute.getEffOffDate());
      jobRoute.setWorkCenter(bomRoute.getWorkCenter());
      jobRoute.setCompleteFlag(false);
      jobRoute.setEfficiencyFactor(bomRoute.getEfficiency());
      jobRoute.setStartDate(DateTimeUtils.ZERO_DATE);
      jobRoute.setEndDate(DateTimeUtils.ZERO_DATE);
      jobRoute.setStartTick(0L);
      jobRoute.setEndTick(0L);
      jobRoute.setMoveTicks(bomRoute.getMoveTicks());
      jobRoute.setMoveTimeUM(bomRoute.getMoveTimeUM());
      jobRoute.setSetupTicks(bomRoute.getSetupTicks());
      jobRoute.setSetupTimeUM(bomRoute.getSetupTimeUM());
      jobRoute.setRunTicks(bomRoute.getRunTicks());
      jobRoute.setRunTimeUM(bomRoute.getRunTimeUM());
      jobRoute.setSchedTicks(bomRoute.getSchedTicks());
      jobRoute.setSchedTimeUM(bomRoute.getSchedTimeUM());
      jobRoute.setSchedOffsetTicks(bomRoute.getSchedOffsetTicks());
      jobRoute.setSchedOffSetTimeUM(bomRoute.getSchedTimeUM());
      jobRoute.setQueueTicks(bomRoute.getQueueTicks());
      jobRoute.setQueueTimeUM(bomRoute.getQueueTimeUM());
      jobRoute.setFreezeScheduleFlag(false);
      jobRoute.setQtyReceived(BigDecimal.ZERO);
      jobRoute.setQtyComplete(BigDecimal.ZERO);
      jobRoute.setQtyScrapped(BigDecimal.ZERO);
      jobRoute.setQtyMoved(BigDecimal.ZERO);
      jobRoute.setControlPointFlag(bomRoute.getControlPointFlag());
      jobRoute.setComment(bomRoute.getComment());

      jobRouteService.create(jobRoute);

      //copy materials
      List<BomMaterial> bomMaterials = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMaterial.class)
          .add(Restrictions.eq("BomRoute", bomRoute))
          .setFlushMode(FlushMode.MANUAL));
      for (BomMaterial bomMaterial : bomMaterials) {
        JobMaterial jobMaterial = jobMaterialService.initialize();
        copyJobResource(jobMaterial, jobRoute, bomMaterial);
        jobMaterial.setCatalog(bomMaterial.getCatalog());
        jobMaterial.setRevision(bomMaterial.getRevision());
        jobMaterial.setViewSequence(bomMaterial.getViewSequence());
        jobMaterial.setReportSequence(bomMaterial.getReportSequence());
        jobMaterial.setQuantityRateFlag(bomMaterial.getQuantityRateFlag());
        jobMaterial.setMtlQty(bomMaterial.getMtlQty());
        jobMaterial.setScrapFactor(bomMaterial.getScrapFactor());
        jobMaterial.setMeasure(bomMaterial.getMeasure());
        jobMaterial.setMtlCostCategory(bomMaterial.getMtlCostCategory());
        jobMaterial.setMtlBackflushFlag(bomMaterial.isMtlBackflushFlag());
        jobMaterial.setBackflushZone(bomMaterial.getBackflushZone());
        jobMaterial.setMtlOhAllocationFlag(bomMaterial.getMtlOhAllocationFlag());
        jobMaterial.setMtlOhRate(bomMaterial.getMtlOhRate());
        jobMaterial.setCurrency(bomMaterial.getCurrency());
        jobMaterial.setMtlOhRatio(bomMaterial.getMtlOhRatio());
        jobMaterial.setMtlOhCostCategory(bomMaterial.getMtlOhCostCategory());
        jobMaterial.setMtlOhBackflushFlag(bomMaterial.isMtlOhBackflushFlag());

        jobMaterialService.create(jobMaterial);
      }

      //copy machines
      List<BomMachine> bomMachines = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomMachine.class)
          .add(Restrictions.eq("BomRoute", bomRoute))
          .setFlushMode(FlushMode.MANUAL));
      for (BomMachine bomMachine : bomMachines) {
        JobMachine jobMachine = jobMachineService.initialize();
        copyJobResource(jobMachine, jobRoute, bomMachine);
        jobMachine.setTimeRateFlag(bomMachine.getTimeRateFlag());
        jobMachine.setRunTicksMch(bomMachine.getRunTicksMch());
        jobMachine.setRunTimeMchUm(bomMachine.getRunTimeMchUm());
        jobMachine.setMchNumber(bomMachine.getMchNumber());
        jobMachine.setMchRecoveryFlag(bomMachine.getMchRecoveryFlag());
        jobMachine.setMchRate(bomMachine.getMchRate());
        jobMachine.setMchRateCurrency(bomMachine.getMchRateCurrency());
        jobMachine.setMchCostCategory(bomMachine.getMchCostCategory());
        jobMachine.setMchBackflushFlag(bomMachine.isMchBackflushFlag());
        jobMachine.setMchOhAllocationFlag(bomMachine.getMchOhAllocationFlag());
        jobMachine.setMchOhRate(bomMachine.getMchOhRate());
        jobMachine.setMchOhRateCurrency(bomMachine.getMchOhRateCurrency());
        jobMachine.setMchOhRatio(bomMachine.getMchOhRatio());
        jobMachine.setMchOhCostCategory(bomMachine.getMchOhCostCategory());
        jobMachine.setMchOhBackflushFlag(bomMachine.isMchOhBackflushFlag());

        jobMachineService.create(jobMachine);
      }

      //copy labors
      List<BomLabor> bomLabors = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomLabor.class)
          .add(Restrictions.eq("BomRoute", bomRoute))
          .setFlushMode(FlushMode.MANUAL));
      for (BomLabor bomLabor : bomLabors) {
        JobLabor jobLabor = jobLaborService.initialize();
        copyJobResource(jobLabor, jobRoute, bomLabor);

        LaborClass laborClass = bomLabor.getLaborClass();
        jobLabor.setTimeRateFlag(laborClass.getTimeRateFlag());
        jobLabor.setRunTicksLbr(bomLabor.getRunTicksLbr());
        jobLabor.setRunTimeLbrUm(bomLabor.getRunTimeLbrUm());
        jobLabor.setLbrNumber(bomLabor.getLbrNumber());
        jobLabor.setLbrRate(laborClass.getLbrRate());
        jobLabor.setLbrCostCategory(laborClass.getLbrCostCategory());
        jobLabor.setLbrRateCurrency(laborClass.getLbrRateCurrency());
        jobLabor.setLbrBackflushFlag(bomLabor.isLbrBackflushFlag());
        jobLabor.setLbrOhAllocationFlag(laborClass.getLbrOhAllocationFlag());
        jobLabor.setLbrOhRate(laborClass.getLbrOhRate());
        jobLabor.setLbrOhRateCurrency(laborClass.getLbrOhRateCurrency());
        jobLabor.setLbrOhRatio(laborClass.getLbrOhRatio());
        jobLabor.setLbrOhCostCategory(laborClass.getLbrOhCostCategory());
        jobLabor.setLbrOhBackflushFlag(bomLabor.isLbrOhBackflushFlag());

        jobLaborService.create(jobLabor);
      }

    }
  }

  private void internalCopyBOM(Job job) {
    //check status
    if (JobStatus.CREATED != job.getJobStatus())
      invalidStatus(job.getJobStatus());
    //check BOM in Job
    if (!checkBOM(job))
      throw new BOMAlreadyExistException();
    //check product
    if (job.getCatalog() == null)
      throw new ProductIsEmptyException();
    //check BOM
    BOMServiceLocal bomService = (BOMServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BOMServiceLocal.SERVICE_NAME);
    Bom bom = bomService.findCurrentBOM(job.getCatalog().getId());
    if (bom == null)
      throw new BOMNotFoundException(job.getCatalog());

    performCopyBOM(job, bom);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(Job entity) {
    entity.setJobStatus(JobStatus.CREATED);
    entity.setPriority(BigDecimal.ZERO);
    entity.setQtyComplete(BigDecimal.ZERO);
    entity.setQtyScrapped(BigDecimal.ZERO);
    entity.setUseMoveTimes(false);
    entity.setUseQueueTimes(false);
    entity.setUseFiniteCapacity(false);
    entity.setPriorityFreezeFlag(false);
    entity.setChangeJobApproved(false);

    entity.setStdCostDetail(MfUtils.createCostDetail());
    entity.setActWipCostDetail(MfUtils.createCostDetail());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Job entity) {
    context.addRule(new MandatoryAttribute(entity, "DefDstStock"));
    context.addRule(new MandatoryAttribute(entity, "DefSrcStock"));
    context.addRule(new MandatoryAttribute(entity, "DefDstMol"));
    context.addRule(new MandatoryAttribute(entity, "DefSrcMol"));
    context.addRule(new MandatoryAttribute(entity, "JobStatus"));
    context.addRule(new MandatoryAttribute(entity, "Catalog"));
    context.addRule(new MandatoryStringAttribute(entity, "JobNumber"));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#copyBOM(int)
   */
  public void copyBOM(int jobId) {
    internalCopyBOM(load(jobId));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#copyBOM(com.mg.merp.manufacture.model.Job)
   */
  public void copyBOM(Job job) {
    internalCopyBOM(job);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#run(int)
   */
  public void run(int jobId) {
    internalChangeStatus(load(jobId), JobStatus.RUNNING);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#stop(int)
   */
  public void stop(int jobId) {
    internalChangeStatus(load(jobId), JobStatus.STOPPED);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#complete(int)
   */
  public void complete(int jobId) {
    internalComplete(load(jobId));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#updateRollupDateTime(com.mg.merp.mfreference.model.Bom, java.util.Date)
   */
  @PermitAll
  public void updateRollupDateTime(Job job, Date date) {
    job = load(job.getId());
    job.setRollUpDateTime(date);
    store(job);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.JobServiceLocal#checkStatus(com.mg.merp.manufacture.model.Job, java.util.EnumSet)
   */
  @PermitAll
  public void checkStatus(Job job, EnumSet<JobStatus> jobStatus) throws InvalidJobStatusException {
    if (!jobStatus.contains(job.getJobStatus()))
      invalidStatus(job.getJobStatus());
  }

}
