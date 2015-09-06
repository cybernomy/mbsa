/*
 * DefaultBulkDocFlowParamsStrategy.java
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
package com.mg.merp.docflow.support;

import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.ChooseDocumentPatternListener;
import com.mg.merp.docflow.ChooseFolderListener;
import com.mg.merp.docflow.ChooseNextStageListener;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.InputDocumentParamsListener;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Реализация стратегии ДО для массовой отработки документов по выбранному этапу
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultBulkDocFlowParamsStrategy.java,v 1.1 2006/12/12 15:24:36 safonov Exp $
 */
public class DefaultBulkDocFlowParamsStrategy extends DefaultInteractiveParamsStrategy {
  private boolean isBulkPartial = false;
  private DocProcessStage stageForBulk = null;
  private Folder destFolderForBulk = null;
  private Integer destDocPatternForBulk = null;

  public DefaultBulkDocFlowParamsStrategy(boolean isBulkPartial, DocProcessStage stageForBulk, Folder destFolderForBulk, Integer destDocPatternForBulk) {
    super();
    this.isBulkPartial = isBulkPartial;
    this.stageForBulk = stageForBulk;
    this.destFolderForBulk = destFolderForBulk;
    this.destDocPatternForBulk = destDocPatternForBulk;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDestanationFolder(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseFolderListener)
   */
  public void chooseDestanationFolder(Date processDate,
                                      DocProcessStage performedStage, BigDecimal docSum,
                                      List<DocumentSpecItem> specList, final ChooseFolderListener listener) {
    if (destFolderForBulk == null)
      super.chooseDestanationFolder(processDate, performedStage, docSum, specList, new ChooseFolderListener() {
        public void canceled() {
          listener.canceled();
        }

        public void performed(Folder folder) {
          //спросим папку один раз, будем использовать для всех документов
          destFolderForBulk = folder;
          listener.performed(folder);
        }
      });
    else
      listener.performed(destFolderForBulk);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocumentPattern(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.core.model.Folder, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseDocumentPatternListener)
   */
  public void chooseDocumentPattern(Date processDate,
                                    DocProcessStage performedStage, Folder folder, BigDecimal docSum,
                                    List<DocumentSpecItem> specList,
                                    final ChooseDocumentPatternListener listener) {
    if (destDocPatternForBulk == null)
      super.chooseDocumentPattern(processDate, performedStage, folder, docSum, specList, new ChooseDocumentPatternListener() {
        public void canceled() {
          listener.canceled();
        }

        public void performed(Integer patternId) {
          //спросим образец один раз, будем использовать для всех документов
          destDocPatternForBulk = patternId;
          listener.performed(patternId);
        }
      });
    else
      listener.performed(destDocPatternForBulk);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseNextStage(com.mg.merp.document.model.DocHead, java.util.Date, java.util.List, com.mg.merp.docflow.ChooseNextStageListener)
   */
  public void chooseNextStage(DocHead docHead, Date processDate,
                              List<DocProcessStage> stages, ChooseNextStageListener listener) {
    assert stageForBulk == null;
    //при массовой отработке выполняем этап который указан юзером
    listener.performed(processDate, stageForBulk);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSpecList(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.InputDocumentParamsListener)
   */
  public void inputDocumentSpecList(Date processDate,
                                    DocProcessStage performedStage, BigDecimal docSum,
                                    List<DocumentSpecItem> specList,
                                    InputDocumentParamsListener listener) {
    if (performedStage.isPartial() && isBulkPartial)
      super.inputDocumentSpecList(processDate, performedStage, docSum, specList, listener);
    else
      silentPrepareDocumentSpecList(docSum, specList, listener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSum(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, com.mg.merp.docflow.InputDocumentParamsListener)
   */
  public void inputDocumentSum(Date processDate,
                               DocProcessStage performedStage, BigDecimal docSum,
                               InputDocumentParamsListener listener) {
    if (performedStage.isPartial() && isBulkPartial)
      super.inputDocumentSum(processDate, performedStage, docSum, listener);
    else
      listener.performed(docSum, null);
  }

}
