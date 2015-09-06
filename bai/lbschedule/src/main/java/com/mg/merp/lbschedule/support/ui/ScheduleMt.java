/*
 * ScheduleMt.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.lbschedule.ItemServiceLocal;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.Schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Контроллер формы поддержки бизнес-компонента "График исполнения обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleMt.java,v 1.7 2007/09/10 13:30:56 sharapov Exp $
 */
public class ScheduleMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap itemsProperties = new LocalDataTransferObject();
  private MaintenanceTableController items;
  private ItemServiceLocal itemsService;
  private DocHead docHead;

  private String docType;
  private String docNumber;
  private Date docDate;


  public ScheduleMt() throws Exception {
    addMasterModelListener(this);

    itemsService = (ItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/lbschedule/Item"); //$NON-NLS-1$
    items = new MaintenanceTableController(itemsProperties);
    items.initController(itemsService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Item i %s where i.Schedule = :schedule"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("schedule"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Item.class, "Id", "i.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "Comments", "i.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "Num", "i.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "ItemKind", "ik.Code", "left join i.ItemKind as ik", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "Status", "i.Status", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "From", "f.Code", "left join i.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "To", "t.Code", "left join i.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResourceFrom", "rf.Name", "left join i.ResourceFrom as rf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResourceTo", "rt.Name", "left join i.ResourceTo as rt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "HasSpec", "i.HasSpec", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "PriceListHead", "plh.PrName", "left join i.PriceListHead as plh", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "PriceType", "pt.Code", "left join i.PriceType as pt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResultDate", "i.ResultDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "ResultDateEnd", "i.ResultDateEnd", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "CurCode", "cc.Code", "left join i.CurCode as cc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "CurRateType", "crt.Code", "left join i.CurRateType as crt", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "CurRateAuthority", "cra.Code", "left join i.CurRateAuthority as cra", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "FactSum", "i.FactSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, itemsService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(items);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    itemsProperties.put("Schedule", event.getEntity());     //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    getDocHead();
    initFieldValues();
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
   */
  @Override
  protected void doOnView() {
    super.doOnView();
    view.getWidget("items").setReadOnly(true); //$NON-NLS-1$
  }

  private void initFieldValues() {
    docType = docHead.getDocType().getCode();
    docNumber = docHead.getDocNumber();
    docDate = docHead.getDocDate();
  }

  private void getDocHead() {
    Schedule schedule = (Schedule) getEntity();
    if (schedule != null)
      docHead = ((ScheduleServiceLocal) getService()).getDocHead(schedule.getId());
  }

  /**
   * Обработчик кнопки "Просмотреть документ"
   *
   * @param event - событие
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public void onActionViewDocument(WidgetEvent event) {
    if (docHead != null)
      MaintenanceHelper.view(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead.getId(), null, null);
  }

  /**
   * @return the docDate
   */
  public Date getDocDate() {
    return docDate;
  }

  /**
   * @param docDate the docDate to set
   */
  public void setDocDate(Date docDate) {
    this.docDate = docDate;
  }

  /**
   * @return the docNumber
   */
  public String getDocNumber() {
    return docNumber;
  }

  /**
   * @param docNumber the docNumber to set
   */
  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  /**
   * @return the docType
   */
  public String getDocType() {
    return docType;
  }

  /**
   * @param docType the docType to set
   */
  public void setDocType(String docType) {
    this.docType = docType;
  }

}
