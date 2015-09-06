/*
 * CalcTaxesKindMt.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.CalcTaxesKindServiceLocal;
import com.mg.merp.reference.model.CalcTaxesKind;
import com.mg.merp.reference.model.CalcTaxesLink;
import com.mg.merp.reference.model.CalcTaxesSubject;

import java.util.Set;

/**
 * @author leonova
 * @version $Id: CalcTaxesKindMt.java,v 1.5 2006/10/26 06:33:46 leonova Exp $
 */
public class CalcTaxesKindMt extends DefaultMaintenanceForm implements MasterModelListener {

  private final static String LOAD_TAXLINK_EJBQL = "select ctl.Id, ctl.Tax.Code, ctl.Tax.TName, ctl.Included, ctl.FeeOrder, ctl.Subject from CalcTaxesLink ctl where ctl.CalcTaxesKind = :calcTaxKind";
  private final CalcTaxesKindChange dialog = (CalcTaxesKindChange) UIProducer.produceForm("com/mg/merp/reference/resources/CalcTaxesKindChangeMt.mfd.xml");
  protected DefaultTableController taxesTable;
  private Integer taxLink;
  private Short feeOrder;
  private Boolean included;
  private CalcTaxesSubject subject;

  public CalcTaxesKindMt() throws Exception {
    setMasterDetail(true);

    taxesTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Id", "ctl.Id", true));
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Tax.Code", "ctl.Tax.Code", true));
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Tax.TName", "ctl.Tax.TName", true));
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Included", "ctl.Included", true));
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "FeeOrder", "ctl.FeeOrder", true));
        result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Subject", "ctl.Subject", true));
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          taxLink = null;
        else {
          Object[] row = getRowList().get(rows[0]);
          taxLink = (Integer) row[0];
          included = (Boolean) row[3];
          feeOrder = (Short) row[4];
          subject = (CalcTaxesSubject) row[5];
        }
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(LOAD_TAXLINK_EJBQL, new String[]{"calcTaxKind"}, new Object[]{getEntity()});
      }

    });

    addMasterModelListener(this);
  }

  private CalcTaxesKindServiceLocal getCalcTaxKindService() {
    return (CalcTaxesKindServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CalcTaxesKind");
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    PopupMenu menu = view.getWidget("taxesTable").getPopupMenu();
    menu.getMenuItem("includeTaxItem").setEnabled(false);
    menu.getMenuItem("excludeTaxItem").setEnabled(false);
    menu.getMenuItem("changeTaxItem").setEnabled(false);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    taxesTable.getModel().load();
  }

  public void onActionIncludeTax(WidgetEvent event) throws ApplicationException {

    for (FormActionListener listener : dialog.getOkActionListenerList()) {
      dialog.removeOkActionListener(listener);
    }

    dialog.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        getCalcTaxKindService().includeTax((CalcTaxesKind) getEntity(), dialog.getTax(), dialog.getFeeOrder(), dialog.isIncluded(), dialog.getSubject());
        taxesTable.getModel().load();
      }
    });
    dialog.setTax(null);
    dialog.setFeeOrder((short) 1);
    dialog.setIncluded(false);
    dialog.setSubject(CalcTaxesSubject.PRICE);
    dialog.execute();
  }

  public void onActionExcludeTax(WidgetEvent event) throws ApplicationException {
    if (taxLink == null)
      return;
    getCalcTaxKindService().excludeTax(ServerUtils.getPersistentManager().find(CalcTaxesLink.class, taxLink));
    taxesTable.getModel().load();
  }

  public void onActionChangeFeeOrder(WidgetEvent event) throws ApplicationException {
    for (FormActionListener listener : dialog.getOkActionListenerList()) {
      dialog.removeOkActionListener(listener);
    }

    dialog.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        getCalcTaxKindService().editTax(ServerUtils.getPersistentManager().find(CalcTaxesLink.class, taxLink), dialog.getTax(), dialog.getFeeOrder(), dialog.isIncluded(), dialog.getSubject());
        taxesTable.getModel().load();
      }

    });
    dialog.setTax(ServerUtils.getPersistentManager().find(CalcTaxesLink.class, taxLink).getTax());
    dialog.setFeeOrder(feeOrder);
    dialog.setIncluded(included);
    dialog.setSubject(subject);
    dialog.execute();
  }
}
