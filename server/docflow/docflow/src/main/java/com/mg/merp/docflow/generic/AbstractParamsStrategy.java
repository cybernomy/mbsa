/*
 * AbstractParamsStrategy.java
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
package com.mg.merp.docflow.generic;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.ChooseDocumentPatternListener;
import com.mg.merp.docflow.ChooseDocumentStateListener;
import com.mg.merp.docflow.ChooseFolderListener;
import com.mg.merp.docflow.ChooseNextStageListener;
import com.mg.merp.docflow.DocFlowParamsStrategy;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.InputDocumentParamsListener;
import com.mg.merp.docprocess.model.DocAction;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Абстрактная реализация стратегии выбора параметров ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractParamsStrategy.java,v 1.1 2006/12/12 15:23:55 safonov Exp $
 */
public abstract class AbstractParamsStrategy implements DocFlowParamsStrategy {
  protected Logger logger = ServerUtils.getLogger(getClass());

  /**
   * установка параметров документа к отработке всеми возможными значениями
   */
  protected void silentPrepareDocumentSpecList(final BigDecimal docSum,
                                               final List<DocumentSpecItem> specList,
                                               final InputDocumentParamsListener listener) {
    //проставим значения к отработке всем доступным
    for (DocumentSpecItem specItem : specList) {
      specItem.setPerformedQuantity1(specItem.getFreeQuantity1());
      specItem.setPerformedQuantity2(specItem.getFreeQuantity2());
      specItem.setPerformedSum(specItem.getFreeSum());
    }
    listener.performed(docSum, specList);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDestanationFolder(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseFolderListener)
   */
  public void chooseDestanationFolder(Date processDate,
                                      DocProcessStage performedStage, BigDecimal docSum,
                                      List<DocumentSpecItem> specList, ChooseFolderListener listener) {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocHeadState(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.docprocess.model.DocAction, java.util.List, java.util.List, com.mg.merp.docflow.ChooseDocumentStateListener)
   */
  public void chooseDocHeadState(Date processDate,
                                 DocProcessStage performedStage, DocAction action,
                                 List<DocHeadState> docHeadStates, List<DocumentSpecItem> specList,
                                 ChooseDocumentStateListener listener) {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocumentPattern(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.core.model.Folder, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseDocumentPatternListener)
   */
  public void chooseDocumentPattern(Date processDate,
                                    DocProcessStage performedStage, Folder folder, BigDecimal docSum,
                                    List<DocumentSpecItem> specList,
                                    ChooseDocumentPatternListener listener) {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseNextStage(com.mg.merp.document.model.DocHead, java.util.Date, java.util.List, com.mg.merp.docflow.ChooseNextStageListener)
   */
  public void chooseNextStage(DocHead docHead, Date processDate,
                              List<DocProcessStage> stages, ChooseNextStageListener listener) {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSpecList(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.InputDocumentParamsListener)
   */
  public void inputDocumentSpecList(final Date processDate,
                                    final DocProcessStage performedStage, final BigDecimal docSum,
                                    final List<DocumentSpecItem> specList,
                                    final InputDocumentParamsListener listener) {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSum(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, com.mg.merp.docflow.InputDocumentParamsListener)
   */
  public void inputDocumentSum(Date processDate,
                               DocProcessStage performedStage, BigDecimal docSum,
                               InputDocumentParamsListener listener) {
  }

}
