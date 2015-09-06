/*
 * JobServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobStatus;

import java.util.Date;
import java.util.EnumSet;

/**
 * Бизнес-компонент "ЗНП"
 *
 * @author Oleg V. Safonov
 * @version $Id: JobServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface JobServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Job, Integer> {

  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/Job";

  /**
   * тип папки для заказ-нарядов на производство
   */
  final static short FOLDER_PART = 12501;

  /**
   * копирование БОМа в ЗНП
   *
   * @param jobId идентификатор ЗНП
   */
  void copyBOM(int jobId);

  /**
   * копирование БОМа в ЗНП
   *
   * @param job ЗНП
   */
  void copyBOM(Job job);

  /**
   * запустить ЗНП
   *
   * @param jobId идентификатор ЗНП
   */
  void run(int jobId);

  /**
   * остановить ЗНП
   *
   * @param jobId идентификатор ЗНП
   */
  void stop(int jobId);

  /**
   * завершить ЗНП
   *
   * @param jobId идентификатор ЗНП
   */
  void complete(int jobId);

  /**
   * изменение даты расчета нормативной себестоимости
   *
   * @param job  ЗНП
   * @param date дата расчета
   */
  void updateRollupDateTime(Job job, Date date);

  /**
   * проверка статуса ЗНП
   *
   * @param job       ЗНП
   * @param jobStatus множество проверяемых статусов
   * @throws InvalidJobStatusException если статус ЗНП не соответствует проверяемым статусам
   */
  void checkStatus(Job job, EnumSet<JobStatus> jobStatus) throws InvalidJobStatusException;

}
