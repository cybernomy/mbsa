/*
 * DocFlowParamsStrategy.java
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

import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Стратегия выбора параметров выполнения ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowParamsStrategy.java,v 1.1 2006/12/12 15:23:33 safonov Exp $
 */
public interface DocFlowParamsStrategy {

  /**
   * выбор этапа для отработки
   *
   * @param docHead     документ
   * @param processDate дата выполнения ДО
   * @param stages      этапа доступные для отработки
   * @param listener    слушатель события
   */
  void chooseNextStage(final DocHead docHead, final Date processDate, final List<DocProcessStage> stages, final ChooseNextStageListener listener);

  /**
   * выбор спецификаций документа для отработки
   *
   * @param processDate    дата выполнения ДО
   * @param performedStage отрабатываемый этап
   * @param docSum         сумма документа к отработке
   * @param specList       спецификации доступные для отработки
   * @param listener       слушатель события
   */
  void inputDocumentSpecList(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final InputDocumentParamsListener listener);

  /**
   * ввод суммы к отработке
   *
   * @param processDate    дата выполнения ДО
   * @param performedStage отрабатываемый этап
   * @param docSum         сумма документа к отработке
   * @param listener       слушатель события
   */
  void inputDocumentSum(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final InputDocumentParamsListener listener);

  /**
   * выбор папки приемника для создаваемого документа
   *
   * @param processDate    дата выполнения ДО
   * @param performedStage отрабатываемый этап
   * @param docSum         сумма документа к отработке
   * @param specList       спецификации к отработке
   * @param listener       слушатель события
   */
  void chooseDestanationFolder(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final ChooseFolderListener listener);

  /**
   * выбор образца документа на основании которого будет создаваться документ
   *
   * @param processDate    дата выполнения ДО
   * @param performedStage отрабатываемый этап
   * @param docSum         сумма документа к отработке
   * @param specList       спецификации к отработке
   * @param listener       слушатель события
   */
  void chooseDocumentPattern(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final ChooseDocumentPatternListener listener);

  /**
   * выбор состояния документа для отката
   *
   * @param processDate    дата выполнения ДО
   * @param performedStage откатываемый этап
   * @param docHeadStates  состояния документа доступные к откату
   * @param specList       спецификации доступные к откату
   * @param listener       слушатель события
   */
  void chooseDocHeadState(final Date processDate, final DocProcessStage performedStage, final List<DocHeadState> docHeadStates, final List<DocumentSpecItem> specList, final ChooseDocumentStateListener listener);

}
