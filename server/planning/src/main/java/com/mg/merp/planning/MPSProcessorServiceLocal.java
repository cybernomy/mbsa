/*
 * MPSProcessorServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.planning;

/**
 * Бизнес-компонент "Процессор MPS"
 *
 * @author leonova
 * @version $Id: MPSProcessorServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface MPSProcessorServiceLocal extends com.mg.framework.api.BusinessObjectService {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/planning/MPSProcessor";

  /**
   * генерация ОПП
   *
   * @param mpsId ООП
   */
  void generateMps(int mpsId);

  /**
   * перенос ОПП на другой уровень
   *
   * @param mpsSrcId ОПП источник
   * @param mpsDstId ОПП приемник (на который переносится источник)
   */
  void mpsLevelTransfer(int mpsSrcId, int mpsDstId);

}
