/*
 * DefaultInteractiveParamsStrategy.java
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

import com.mg.framework.api.Session;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.ChooseDocumentPatternListener;
import com.mg.merp.docflow.ChooseDocumentStateListener;
import com.mg.merp.docflow.ChooseFolderListener;
import com.mg.merp.docflow.ChooseNextStageListener;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.InputDocumentParamsListener;
import com.mg.merp.docflow.SilentException;
import com.mg.merp.docflow.generic.AbstractParamsStrategy;
import com.mg.merp.docflow.support.ui.ChooseDocumentSpecDialog;
import com.mg.merp.docflow.support.ui.ChooseDocumentStateDialog;
import com.mg.merp.docflow.support.ui.ChooseNextStageDialog;
import com.mg.merp.docflow.support.ui.InputDocumentSumDialog;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.reference.support.ui.FolderSearchForm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация стратегии ДО для интерактивной работы с пользователем, использование возможно только
 * для интерактивных пользователей
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultInteractiveParamsStrategy.java,v 1.5 2009/02/25 08:40:16 safonov Exp $
 */
public class DefaultInteractiveParamsStrategy extends AbstractParamsStrategy {

  private void checkUIInteractive() {
    Session session = ServerUtils.getCurrentSession();
    //данная стратегия требует наличия пользовательского интерфейса
    if (session != null && !session.isInteractive())
      throw new SilentException();
  }

  private void handleException(RuntimeException rte, FormEvent event) {
    event.getForm().close();
    throw rte;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseNextStage(java.util.Date, java.util.List, com.mg.merp.docflow.ChooseNextStageListener)
   */
  public void chooseNextStage(final DocHead docHead, final Date processDate, final List<DocProcessStage> stages,
                              final ChooseNextStageListener listener) {
    checkUIInteractive();

    logger.debug("Start ChooseNextStage dialog");
    final ChooseNextStageDialog dialog = (ChooseNextStageDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/ChooseNextStageDialog.mfd.xml");
    dialog.setTitle(String.format(Messages.getInstance().getMessage(Messages.CHOOSE_NEXT_STAGE_TITLE), DocumentUtils.generateDocumentTitle(docHead)));
    dialog.addOkActionListener(new FormActionListener() {
      public void actionPerformed(FormEvent event) {
        try {
          listener.performed(dialog.getProcessDate(), dialog.getStage());
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    dialog.addCancelActionListener(new FormActionListener() {
      public void actionPerformed(FormEvent event) {
        try {
          listener.canceled();
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    dialog.setProcessDate(processDate);
    dialog.setStages(stages);
    dialog.execute();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSpecList(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List)
   */
  public void inputDocumentSpecList(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final List<DocumentSpecItem> specList, final InputDocumentParamsListener listener) {
    checkUIInteractive();

    if (performedStage.isPartial()) {
      logger.debug("Start InputDocumentSpecList dialog");

      ChooseDocumentSpecDialog dialog = (ChooseDocumentSpecDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/ChooseDocumentSpecDialog.mfd.xml");
      dialog.addOkActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          boolean valueOutOfBound = performedStage.isValueOutOfBound();
          List<DocumentSpecItem> tmpSpecList = new ArrayList<DocumentSpecItem>();
          for (DocumentSpecItem specItem : specList) {
            //в список к отработке попадает только отмеченная спецификация
            if (specItem.getPerformedQuantity1() != null && MathUtils.compareToZero(specItem.getPerformedQuantity1()) != 0 ||
                specItem.getPerformedQuantity2() != null && MathUtils.compareToZero(specItem.getPerformedQuantity2()) != 0 ||
                specItem.getPerformedSum() != null && MathUtils.compareToZero(specItem.getPerformedSum()) != 0) {
              BigDecimal freeQuan1 = specItem.getPerformedQuantity1();
              if (freeQuan1 == null)
                freeQuan1 = BigDecimal.ZERO;
              BigDecimal freeQuan2 = specItem.getPerformedQuantity2();
              if (freeQuan2 == null)
                freeQuan2 = BigDecimal.ZERO;
              BigDecimal freeSum = specItem.getPerformedSum();
              if (freeSum == null)
                freeSum = BigDecimal.ZERO;
              //если ограничение включено, то уменьшим выбранную сумму и количество в каждой спецификации
              if (!valueOutOfBound) {
                if (freeQuan1.abs().compareTo(specItem.getFreeQuantity1().abs()) == 1)
                  freeQuan1 = specItem.getFreeQuantity1();
                if (freeQuan2.abs().compareTo(specItem.getFreeQuantity2().abs()) == 1)
                  freeQuan2 = specItem.getFreeQuantity2();
                if (freeSum.abs().compareTo(specItem.getFreeSum().abs()) == 1)
                  freeSum = specItem.getFreeSum();
              }
              DocumentSpecItem tmpSpecItem = new DocumentSpecItem(specItem.getDocSpec(),
                  freeQuan1, freeQuan2, freeSum, specItem.getReadyQuantity1(), specItem.getReadyQuantity2(), specItem.getReadySum());
              //проставим значения к отработке
              tmpSpecItem.setPerformedQuantity1(freeQuan1);
              tmpSpecItem.setPerformedQuantity2(freeQuan2);
              tmpSpecItem.setPerformedSum(freeSum);
              tmpSpecList.add(tmpSpecItem);
            }
          }
          specList.clear();
          try {
            //https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4953
            if (!tmpSpecList.isEmpty())
              listener.performed(docSum, tmpSpecList);
            else
              listener.canceled();
          } catch (RuntimeException e) {
            handleException(e, event);
          }
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          try {
            listener.canceled();
          } catch (RuntimeException e) {
            handleException(e, event);
          }
        }
      });
      dialog.setDocumentSpecList(specList);
      dialog.execute();
    } else
      silentPrepareDocumentSpecList(docSum, specList, listener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSum(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, com.mg.merp.docflow.InputDocumentParamsListener)
   */
  public void inputDocumentSum(final Date processDate, final DocProcessStage performedStage, final BigDecimal docSum, final InputDocumentParamsListener listener) {
    checkUIInteractive();

    if (performedStage.isPartial()) {
      logger.debug("Start InputDocumentSum dialog");
      InputDocumentSumDialog dialog = (InputDocumentSumDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/InputDocumentSumDialog.mfd.xml");
      dialog.addOkActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          BigDecimal sum = ((InputDocumentSumDialog) event.getForm()).getDocSum();
          //если ограничение включено, то уменьшим выбранную сумму
          if (!performedStage.isValueOutOfBound() && sum.abs().compareTo(docSum.abs()) == 1)
            sum = docSum;
          try {
            listener.performed(sum, null);
          } catch (RuntimeException e) {
            handleException(e, event);
          }
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          try {
            listener.canceled();
          } catch (RuntimeException e) {
            handleException(e, event);
          }
        }
      });
      dialog.setDocSum(docSum);
      dialog.execute();

    } else
      listener.performed(docSum, null);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDestanationFolder(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseFolderListener)
   */
  public void chooseDestanationFolder(Date processDate, DocProcessStage performedStage, BigDecimal docSum, List<DocumentSpecItem> specList, final ChooseFolderListener listener) {
    checkUIInteractive();

    logger.debug("Start ChooseDestanationFolder dialog");
    FolderSearchForm form = (FolderSearchForm) UIProducer.produceForm("com/mg/merp/reference/resources/FolderSearchForm.mfd.xml");
    form.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        Folder folder = (Folder) event.getItems()[0];
        try {
          listener.performed(folder);
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }

      public void searchCanceled(SearchHelpEvent event) {
        try {
          listener.canceled();
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    form.setFolderType((short) performedStage.getLinkDocSection().getFolderType());
    form.run(true);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocumentPattern(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.core.model.Folder, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseDocumentPatternListener)
   */
  public void chooseDocumentPattern(Date processDate, DocProcessStage performedStage, BigDecimal docSum, List<DocumentSpecItem> specList, final ChooseDocumentPatternListener listener) {
    checkUIInteractive();

    logger.debug("Start ChooseDestanationModel dialog");
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.document.support.ui.UniversalDocModelSearchHelp");
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject entity = event.getItems()[0];
        try {
          listener.performed((Integer) entity.getPrimaryKey());
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }

      public void searchCanceled(SearchHelpEvent event) {
        try {
          listener.canceled();
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    Map<String, Object> context = new HashMap<String, Object>();
    context.put("DocSection", performedStage.getLinkDocSection());
    context.put("DocModelFolder", performedStage.getLinkDocModelFolder());
    searchHelp.setImportContext(context);
    searchHelp.search();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocHeadState(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.docprocess.model.DocAction, java.util.List, java.util.List, com.mg.merp.docflow.ChooseDocumentStateListener)
   */
  public void chooseDocHeadState(Date processDate, DocProcessStage performedStage, List<DocHeadState> docHeadStates, List<DocumentSpecItem> specList, final ChooseDocumentStateListener listener) {
    checkUIInteractive();

    logger.debug("Start ChooseDocumentState dialog");
    ChooseDocumentStateDialog dialog = (ChooseDocumentStateDialog) UIProducer.produceForm("com/mg/merp/docflow/resources/ChooseDocumentStateDialog.mfd.xml");
    dialog.addOkActionListener(new FormActionListener() {
      public void actionPerformed(FormEvent event) {
        int stateId = ((ChooseDocumentStateDialog) event.getForm()).getStateId();
        try {
          listener.performed(stateId);
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    dialog.addCancelActionListener(new FormActionListener() {
      public void actionPerformed(FormEvent event) {
        try {
          listener.canceled();
        } catch (RuntimeException e) {
          handleException(e, event);
        }
      }
    });
    dialog.setStatesList(docHeadStates);
    dialog.execute();
  }

}
