/*
 * RtlInvoiceHeadMt.java
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
package com.mg.merp.retail.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.discount.CardServiceLocal;
import com.mg.merp.discount.model.Card;
import com.mg.merp.discount.model.CardUser;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.retail.InvoiceHeadServiceLocal;
import com.mg.merp.retail.InvoiceSpecServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceHead;
import com.mg.merp.retail.model.RtlInvoiceSpec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки документов на отпуск
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: RtlInvoiceHeadMt.java,v 1.13 2009/02/05 08:54:03 sharapov Exp $
 */
public class RtlInvoiceHeadMt extends GoodsDocumentMaintenanceForm {
  protected CardServiceLocal disCardService;

  public RtlInvoiceHeadMt() throws Exception {
    super();
    specService = ((InvoiceSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/retail/InvoiceSpec")); //$NON-NLS-1$
    disCardService = (CardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/discount/Card"); //$NON-NLS-1$

    spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(RtlInvoiceSpec.class, "Discount", "ds.Discount", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcStock", "ss.Code", "left join ds.SrcStock as ss", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcMol", "sm.Code", "left join ds.SrcMol as sm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "DstStock", "dst.Code", "left join ds.DstStock as dst", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "DstMol", "dm.Code", "left join ds.DstMol as dm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "BestBefore", "ds.BestBefore", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Price1", "ds.Price1", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa1", "ds.Summa1", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
        result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#createQueryText()
       */
      @Override
      protected String getDocSpecModelName() {
        return RtlInvoiceSpec.class.getName();
      }

    });
    addMasterModelListener(spec);
  }

  /**
   * Обработчик кнопки "Выбрать дисконтную карту"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionChooseDisCard(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.discount.support.ui.CardSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        Card disCard = (Card) event.getItems()[0];
        RtlInvoiceHead rtlInvoiceHead = ((RtlInvoiceHead) getEntity());
        rtlInvoiceHead.setDiscountCard(disCard);
        rtlInvoiceHead.setBaseDiscount(disCardService.getDiscountFromHistory(disCard, rtlInvoiceHead.getDocDate()));

        List<CustomerSelectTableModelItem> customerSelectTableModelItems = loadCustomerSelectTableModelItems(disCard);

        if (customerSelectTableModelItems.size() == 1)
          completeCusomerSelection(customerSelectTableModelItems.get(0).getContractor());
        else {
          final CustomerSelectDlg customerSelectDlg = (CustomerSelectDlg) UIProducer.produceForm("com/mg/merp/retail/resources/CustomerSelectDlg.mfd.xml"); //$NON-NLS-1$
          customerSelectDlg.addOkActionListener(new FormActionListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
             */
            public void actionPerformed(FormEvent formEvent) {
              completeCusomerSelection(customerSelectDlg.getSelectedCustomer());
            }
          });
          customerSelectDlg.addCancelActionListener(new FormActionListener() {

            /* (non-Javadoc)
             * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
             */
            public void actionPerformed(FormEvent event) {
              ((RtlInvoiceHead) getEntity()).setTo(null);
              view.flushModel();
            }
          });
          customerSelectDlg.executeDlg(customerSelectTableModelItems);
        }
      }

      public void searchCanceled(SearchHelpEvent event) {
        // do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Обработчик кнопки "Удалить дисконтную карту"
   *
   * @param event - событие
   */
  public void onActionClearDisCard(WidgetEvent event) {
    ((RtlInvoiceHead) getEntity()).setDiscountCard(null);
    ((RtlInvoiceHead) getEntity()).setBaseDiscount(BigDecimal.ZERO);
    view.flushModel();
  }

  /**
   * Обработчик кнопки "Просмотреть дисконтную карту"
   *
   * @param event - событие
   */
  public void onActionViewDisCard(WidgetEvent event) {
    Card disCard = ((RtlInvoiceHead) getEntity()).getDiscountCard();
    if (disCard != null)
      MaintenanceHelper.view(disCardService, disCard.getId(), null, null);
  }

  /**
   * Обработчик кнопки "Применить скидку/наценку"
   *
   * @param event - событие
   */
  public void onActionApplyDiscount(WidgetEvent event) {
    doApplyDiscount();
  }

  /**
   * реализация применения скидок/наценок
   */
  protected void doApplyDiscount() {
    DocHead docHead = (DocHead) getEntity();
    if (docHead.getId() == null) //не обрабатываем не созданную запись
      return;

    ((InvoiceHeadServiceLocal) getService()).applyDiscount(docHead, new ApplyDiscountListener() {

      /* (non-Javadoc)
       * @see com.mg.merp.discount.ApplyDiscountListener#aborted()
       */
      public void aborted() {
        // do nothing
      }

      /* (non-Javadoc)
       * @see com.mg.merp.discount.ApplyDiscountListener#completed()
       */
      public void completed() {
        refreshModel();
      }
    });
  }

  protected List<CustomerSelectTableModelItem> loadCustomerSelectTableModelItems(Card disCard) {
    List<CustomerSelectTableModelItem> tableModelItems = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CardUser.class)
        .setProjection(Projections.property("Contractor")) //$NON-NLS-1$
        .add(Restrictions.eq("Card", disCard)) //$NON-NLS-1$
        .setResultTransformer(new ResultTransformer<CustomerSelectTableModelItem>() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
           */
          public CustomerSelectTableModelItem transformTuple(Object[] tuple, String[] aliases) {
            return new CustomerSelectTableModelItem(ServerUtils.getPersistentManager().find(Contractor.class, ((Contractor) tuple[0]).getId()), false);
          }

        }));
    if (disCard.getOwner() != null)
      tableModelItems.add(0, new CustomerSelectTableModelItem(ServerUtils.getPersistentManager().find(Contractor.class, disCard.getOwner().getId()), true));

    return tableModelItems;
  }

  protected void completeCusomerSelection(Contractor customer) {
    ((RtlInvoiceHead) getEntity()).setTo(customer);
    view.flushModel();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#setSpecificationEditable()
   */
  @Override
  protected void setSpecificationEditable() {
    Widget specWidget = view.getWidget(SPEC_TABLE_WIDGET);
    if (specWidget != null)
      specWidget.setReadOnly(false);
    setEnabledApplyDiscountButton(true);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnEdit()
   */
  @Override
  protected void doOnEdit() {
    super.doOnEdit();
    setEnabledApplyDiscountButton(false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    setEnabledApplyDiscountButton(false);
  }

  /**
   * Сделать активной/неактивной кнопку "Применить с/н"
   *
   * @param enabled - признак "активности"
   */
  private void setEnabledApplyDiscountButton(boolean enabled) {
    Widget applyDiscountButton = view.getWidget("ApplyDiscount"); //$NON-NLS-1$
    if (applyDiscountButton != null)
      applyDiscountButton.setEnabled(enabled);
  }

}

