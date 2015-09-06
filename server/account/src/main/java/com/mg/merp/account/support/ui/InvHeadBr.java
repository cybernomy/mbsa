/*
 * InvHead.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AccInventoryMoveParams;
import com.mg.merp.account.AccInventoryRetireParams;
import com.mg.merp.account.AccRevaluateParams;
import com.mg.merp.account.AmortizationServiceLocal;
import com.mg.merp.account.InvHeadServiceLocal;
import com.mg.merp.account.model.AccKind;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.InvLocation;
import com.mg.merp.account.support.Messages;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.model.Contractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Браузер инвентарной картотеки
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Konstantin S. Alikaev
 * @version $Id: InvHeadBr.java,v 1.11 2008/07/15 08:15:09 safonov Exp $
 */
public class InvHeadBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from InvHead ih %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  // данные которые нужно сохранить для операции перемещение,
  // перемещение осуществляется по всем видам учета и с одинаковым контрагентом кому
  private Contractor contractor = null;
  private InvLocation invLocation = null;
  private String operDocNum = StringUtils.EMPTY_STRING;
  private Date operDocDate = null;

  private AmortizationServiceLocal amortizationService = null;

  private InvHeadServiceLocal invHeadService = null;

  private Messages messages = Messages.getInstance();

  private boolean isFirst = true;

  public InvHeadBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    tree.setParentPropertyName("Folder.Id");
    treeUIProperties.put("FolderType", InvHeadServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(InvHeadServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(InvHead.class, "Id", "ih.Id", true));
        result.add(new TableEJBQLFieldDef(InvHead.class, "GroupNum", "ih.GroupNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "CardNum", "ih.CardNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "ObjNum", "ih.ObjNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "InvName", "ih.InvName", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Catalog.Code", "cat.Code", "left join ih.Catalog as cat", true));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Catalog.FullName", "cat.FullName", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Contractor", "c.Code", "left join ih.Contractor as c", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "InvLocation", "il.Code", "left join ih.InvLocation as il", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Okof", "o.Code", "left join ih.Okof as o", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Manufacturer", "ih.Manufacturer", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Model", "ih.Model", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "SerialNum", "ih.SerialNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "PasspNum", "ih.PasspNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "InOperDocNum", "ih.InOperDocNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "InOperDate", "ih.InOperDate", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "OutOperDocNum", "ih.OutOperDocNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "OutOperDate", "ih.OutOperDate", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "IncomeDocNum", "ih.IncomeDocNum", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "IncomeDate", "ih.IncomeDate", false));
        result.add(new TableEJBQLFieldDef(InvHead.class, "Comment", "ih.Comment", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    String whereText = StringUtils.EMPTY_STRING;
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    whereText = " where ".concat(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "ih.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true));

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);

  }

  /**
   * Обработчик кнопки контекстного меню "Начисление амортизации"
   */
  public void onActionCalcAmortization(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    SearchHelp accKindSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccKindSearchHelp");
    accKindSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        final PersistentObject[] accKindList = event.getItems();
        final AmRateDlg dialog = (AmRateDlg) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AmRateDlg");
        dialog.addOkActionListener(new FormActionListener() {
          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
           */
          public void actionPerformed(FormEvent event) {
            int months = dialog.getYear() * 12 + dialog.getNumberMonth();
            List<InvHead> invHeads = new ArrayList<InvHead>();
            for (PersistentObject invHead : getSearchedEntities())
              invHeads.add((InvHead) invHead);
            List<AccKind> accKinds = new ArrayList<AccKind>();
            for (PersistentObject accKind : accKindList)
              accKinds.add((AccKind) accKind);
            queryCalcAmortizationInventoryParams(accKinds.iterator(), invHeads, (short) months);
          }
        });
        dialog.execute();
      }
    });
    accKindSearchHelp.search();
  }

  /**
   * Вызов формы запроса параметров переоценка/дооценка инвентарной карточки
   *
   * @param iterator - итератор списка виды учета
   * @param invHeads - список инвентарных картотек
   */
  private void queryCalcAmortizationInventoryParams(final Iterator<AccKind> iterator, final List<InvHead> invHeads, final short months) {
    if (iterator.hasNext()) {
      final AmortizationBr amBr = (AmortizationBr) ApplicationDictionaryLocator.locate().getBrowseForm(getAmortizationService(), null);
      final AccKind accKind = iterator.next();
      final int batch = getInvHeadService().calcAmortization(months, invHeads, accKind);
      amBr.setBatch(batch);
      amBr.setAccKindName(accKind.getName());
      amBr.addOkActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          amBr.setOkAction(true);
          OperationFolderSearchHelp folderSearchHelper = new OperationFolderSearchHelp();
          folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchCanceled(SearchHelpEvent event) {
              getAmortizationService().rollbackAmortization(batch);
            }

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchPerformed(SearchHelpEvent event) {
              try {
                getAmortizationService().commitAmortization(batch, (Folder) event.getItems()[0]);
                queryCalcAmortizationInventoryParams(iterator, invHeads, months);
              } catch (RuntimeException e) {
                getAmortizationService().rollbackAmortization(batch);
                UIUtils.abortConversation();
                throw e;
              }
            }
          });
          folderSearchHelper.search();
        }
      });
      amBr.addCloseActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          if (!amBr.isOkAction()) {
            getAmortizationService().rollbackAmortization(batch);
            throw new BusinessException(messages.getMessage(Messages.CLOSED_CALC_AMORTIZATION_FOR_INVENTORY));
          }
        }

      });
      amBr.execute();
    }
  }

  /**
   * Обработчик кнопки контекстного меню "Переоценка"
   */
  public void onActionRevaluateOvervalue(WidgetEvent event) throws ApplicationException {
    revaluate(false);
  }

  /**
   * Обработчик кнопки контекстного меню "Дооценка"
   */
  public void onActionRevaluateOverestimation(WidgetEvent event) throws ApplicationException {
    revaluate(true);
  }

  /**
   * Возвращает бизнес-компонент "Ведомость начисления амортизации"
   */
  private AmortizationServiceLocal getAmortizationService() {
    if (amortizationService == null)
      amortizationService = (AmortizationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Amortization");
    return amortizationService;
  }

  /**
   * Обработчик действия переоценка/дооценка
   *
   * @param isOverestimation - <code>false</code> - переоценка, иначе дооценка
   */
  private void revaluate(final boolean isOverestimation) {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    final List<InvHead> invHeads = new ArrayList<InvHead>();
    for (PersistentObject invHead : getSearchedEntities())
      invHeads.add((InvHead) invHead);
    SearchHelp accKindSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccKindSearchHelp");
    accKindSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        List<AccKind> accKinds = new ArrayList<AccKind>();
        for (PersistentObject accKind : event.getItems())
          accKinds.add((AccKind) accKind);
        queryRevaluateInventoryParams(accKinds.iterator(), invHeads, isOverestimation);
      }
    });
    accKindSearchHelp.search();
  }

  /**
   * Вызов формы запроса параметров переоценка/дооценка инвентарной карточки
   *
   * @param iterator - итератор списка виды учета
   * @param invHeads - список инвентарных картотек
   */
  private void queryRevaluateInventoryParams(final Iterator<AccKind> iterator, final List<InvHead> invHeads, final boolean isOverestimation) {
    if (iterator.hasNext()) {
      final AccRevaluateDlg dialog = (AccRevaluateDlg) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AccRevaluateDlg");
      final AccKind accKind = iterator.next();
      String titleDlg = isOverestimation ? StringUtils.format(messages.getMessage(Messages.ACC_REVALUTE_DLG_TITLE_OVERESTIMATION), ((AccKind) accKind).getName()) :
          StringUtils.format(messages.getMessage(Messages.ACC_REVALUTE_DLG_TITLE_OVERVALUE), ((AccKind) accKind).getName());
      dialog.setAccRevaluateDlgTitle(titleDlg);
      dialog.addOkActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          OperationFolderSearchHelp folderSearchHelper = new OperationFolderSearchHelp();
          folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchCanceled(SearchHelpEvent event) {
            }

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchPerformed(SearchHelpEvent event) {
              AccRevaluateParams params = new AccRevaluateParams(dialog.getRevalDate(), (Folder) event.getItems()[0], DataUtils.convertEnumToOrdinal(dialog.getKind()).shortValue(), dialog.getValue(),
                  dialog.getDocType(), dialog.getDocNumber(), dialog.getDocDate(), dialog.getBaseDocType(), dialog.getBaseDocNumber(), dialog.getBaseDocDate(), dialog.getAccPlan(),
                  dialog.getAnl1(), dialog.getAnl2(), dialog.getAnl3(), dialog.getAnl4(), dialog.getAnl5(), isOverestimation);
              getInvHeadService().revaluate(invHeads, (AccKind) accKind, params);
              queryRevaluateInventoryParams(iterator, invHeads, isOverestimation);
            }
          });
          folderSearchHelper.search();
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          dialog.close();
          throw new BusinessException(messages.getMessage(Messages.CLOSED_REVALUATE_FOR_INVENTORY));
        }
      });
      dialog.execute();
    }
  }

  /**
   * Обработчик кнопки контекстного меню "Отменить последнее действие"
   */
  public void onActionRollback(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    SearchHelp accKindSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccKindSearchHelp");
    accKindSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        List<AccKind> accKinds = new ArrayList<AccKind>();
        for (PersistentObject accKind : event.getItems())
          accKinds.add((AccKind) accKind);
        List<InvHead> invHeads = new ArrayList<InvHead>();
        for (PersistentObject invHead : getSearchedEntities())
          invHeads.add((InvHead) invHead);
        getInvHeadService().rollback(invHeads, accKinds);
      }
    });
    accKindSearchHelp.search();
  }

  /**
   * Обработчик кнопки контекстного меню "Перемещение"
   */
  public void onActionMove(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    List<AccKind> accKinds = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccKind.class));
    List<InvHead> invHeads = new ArrayList<InvHead>();
    for (PersistentObject invHead : getSearchedEntities())
      invHeads.add((InvHead) invHead);
    this.contractor = null;
    this.isFirst = true;
    queryMoveInventoryParams(accKinds.iterator(), invHeads);
  }

  /**
   * Вызов формы запроса параметров перемещения инвентарной карточки
   *
   * @param iterator - итератор списка виды учета
   * @param invHeads - список инвентарных картотек
   */
  private void queryMoveInventoryParams(final Iterator<AccKind> iterator, final List<InvHead> invHeads) {
    if (iterator.hasNext()) {
      final AccInventoryMoveDlg dialog = (AccInventoryMoveDlg) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AccInventoryMoveDlg");
      final AccKind accKind = iterator.next();
      String titleDlg = StringUtils.format(messages.getMessage(Messages.ACC_INVENTORY_MOVE_DLG_TITLE), ((AccKind) accKind).getName());
      dialog.setDialogTitle(titleDlg);
      if (!this.isFirst)
        dialog.setContractor(this.contractor);
      dialog.addOkActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          OperationFolderSearchHelp folderSearchHelper = new OperationFolderSearchHelp();
          folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchCanceled(SearchHelpEvent event) {
            }

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchPerformed(SearchHelpEvent event) {
              if (isFirst) {
                isFirst = false;
                contractor = dialog.getContractor();
              }
              AccInventoryMoveParams params = new AccInventoryMoveParams(dialog.getRevalDate(), dialog.getContractor(), dialog.getInOperDate(), dialog.getInOperDocNum(),
                  dialog.getInvLocation(), dialog.getDocType(), dialog.getDocNumber(), dialog.getDocDate(),
                  dialog.getAccPlan(), dialog.getAnl1(), dialog.getAnl2(), dialog.getAnl3(), dialog.getAnl4(), dialog.getAnl5(),
                  dialog.getAccKt(), dialog.getAnlKt1(), dialog.getAnlKt2(), dialog.getAnlKt3(), dialog.getAnlKt4(), dialog.getAnlKt5(),
                  (Folder) event.getItems()[0]);
              invLocation = dialog.getInvLocation();
              operDocDate = dialog.getInOperDate();
              operDocNum = dialog.getInOperDocNum();
              getInvHeadService().moveInventory(invHeads, (AccKind) accKind, params);
              queryMoveInventoryParams(iterator, invHeads);
            }
          });
          folderSearchHelper.search();
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          dialog.close();
          throw new BusinessException(messages.getMessage(Messages.CLOSED_MOVE_FOR_INVENTORY));
        }
      });
      dialog.execute();
    } else
      for (InvHead invHead : invHeads) {
        invHead.setContractor(this.contractor);
        invHead.setInvLocation(this.invLocation);
        invHead.setInOperDocNum(this.operDocNum);
        invHead.setInOperDate(this.operDocDate);
      }
  }

  /**
   * Обработчик кнопки контекстного меню "Списание"
   */
  public void onActionRetire(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    List<AccKind> accKinds = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(AccKind.class));
    List<InvHead> invHeads = new ArrayList<InvHead>();
    for (PersistentObject invHead : getSearchedEntities())
      invHeads.add((InvHead) invHead);
    queryRetireInventoryParams(accKinds.iterator(), invHeads);
  }

  /**
   * Вызов формы запроса параметров списание инвентарной карточки
   *
   * @param iterator - итератор списка виды учета
   * @param invHeads - список инвентарных картотек
   */
  private void queryRetireInventoryParams(final Iterator<AccKind> iterator, final List<InvHead> invHeads) {
    if (iterator.hasNext()) {
      final AccInventoryRetireDialog dialog = (AccInventoryRetireDialog) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AccInventoryRetireDialog");
      final AccKind accKind = iterator.next();
      String titleDlg = StringUtils.format(messages.getMessage(Messages.ACC_INVENTORY_RETIRE_DLG_TITLE), ((AccKind) accKind).getName());
      dialog.setDialogTitle(titleDlg);
      dialog.addOkActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          OperationFolderSearchHelp folderSearchHelper = new OperationFolderSearchHelp();
          folderSearchHelper.addSearchHelpListener(new SearchHelpListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchCanceled(SearchHelpEvent event) {
            }

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
             */
            public void searchPerformed(SearchHelpEvent event) {
              AccInventoryRetireParams params = new AccInventoryRetireParams(dialog.getRevalDate(), (Folder) event.getItems()[0],
                  dialog.getDocType(), dialog.getDocNumber(), dialog.getDocDate(), dialog.getBaseDocType(), dialog.getBaseDocNumber(), dialog.getBaseDocDate(),
                  dialog.getAccPlan(), dialog.getAnl1(), dialog.getAnl2(), dialog.getAnl3(), dialog.getAnl4(), dialog.getAnl5());
              getInvHeadService().retire(invHeads, (AccKind) accKind, params);
              operDocDate = dialog.getDocDate();
              operDocNum = dialog.getDocNumber();
              queryRetireInventoryParams(iterator, invHeads);
            }
          });
          folderSearchHelper.search();
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {
        public void actionPerformed(FormEvent event) {
          dialog.close();
          throw new BusinessException(messages.getMessage(Messages.CLOSED_RETIRE_FOR_INVENTORY));
        }
      });
      dialog.execute();
    } else
      for (InvHead invHead : invHeads) {
        invHead.setOutOperDate(this.operDocDate);
        invHead.setOutOperDocNum(this.operDocNum);
      }
  }

  /**
   * Обработчик кнопки контекстного меню "Консервация"
   */
  public void onActionFreeze(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_CHOOSE_ENTITIES));
    final List<InvHead> invHeads = new ArrayList<InvHead>();
    for (PersistentObject invHead : getSearchedEntities())
      invHeads.add((InvHead) invHead);
    SearchHelp accKindSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccKindSearchHelp");
    accKindSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        final PersistentObject[] accKinds = event.getItems();
        Dialogs.inputQuery(messages.getMessage(Messages.ACC_INVENTORY_FREEZE_DLG_TITLE), messages.getMessage(Messages.ACC_INVENTORY_FREEZE_DLG_PROMT), DateTimeUtils.nowDate(), new Dialogs.InputQueryDialogListener<Date>() {

          public void inputCanceled() {
          }

          public void inputPerformed(Date freezeDate) {
            for (PersistentObject accKind : accKinds)
              getInvHeadService().freeze(invHeads, (AccKind) accKind, freezeDate);
          }
        });
      }
    });
    accKindSearchHelp.search();
  }

  /**
   * Обработчик кнопки контекстного меню "Формирование остатков"
   */
  public void onActionMakeRemains(WidgetEvent event) throws ApplicationException {
    int countEntities = getSearchedEntities().length;
    if (countEntities == 0)
      throw new BusinessException(messages.getMessage(Messages.NOT_CHOOSE_ENTITIES));
    final List<InvHead> invHeads = new ArrayList<InvHead>();
    for (PersistentObject invHead : getSearchedEntities())
      invHeads.add((InvHead) invHead);
    SearchHelp accKindSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccKindSearchHelp");
    accKindSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchCanceled(SearchHelpEvent event) {
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
       */
      public void searchPerformed(SearchHelpEvent event) {
        final PersistentObject[] accKinds = event.getItems();
        final AccInventoryMakeRemainsDialog dialog = (AccInventoryMakeRemainsDialog) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AccInventoryMakeRemainsDialog");
        dialog.addOkActionListener(new FormActionListener() {
          public void actionPerformed(FormEvent event) {
            for (PersistentObject accKind : accKinds)
              getInvHeadService().makeRemains(invHeads, (AccKind) accKind, dialog.getPeriod());
          }
        });
        dialog.addCancelActionListener(new FormActionListener() {
          public void actionPerformed(FormEvent event) {
            dialog.close();
            throw new BusinessException(messages.getMessage(Messages.CLOSED_MAKE_REMAINS_FOR_INVENTORY));
          }
        });
        dialog.execute();
      }
    });
    accKindSearchHelp.search();
  }

  /**
   * Бизнес-компонент инвентарная картотека
   */
  private InvHeadServiceLocal getInvHeadService() {
    if (invHeadService == null)
      invHeadService = (InvHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvHead");
    return invHeadService;
  }

}

