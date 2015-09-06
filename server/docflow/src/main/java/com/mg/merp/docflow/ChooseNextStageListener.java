/*
 * ChooseNextStageListener.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow;

import com.mg.merp.docprocess.model.DocProcessStage;

import java.util.Date;
import java.util.EventListener;

/**
 * Слушатель выбора этапа для отработки
 *
 * @author Oleg V. Safonov
 * @version $Id: ChooseNextStageListener.java,v 1.1 2006/12/12 15:23:33 safonov Exp $
 */
public interface ChooseNextStageListener extends EventListener {

  /**
   * этап выбран
   *
   * @param processDate    дата для выполнения отработки
   * @param performedStage этап для отработки
   */
  void performed(Date processDate, DocProcessStage performedStage);

  /**
   * выбор этапа отменен
   */
  void canceled();

}
