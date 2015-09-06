/*
 * DocTypeBr.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.model.DocType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Форма браузера бизнес-компонента "Тип документа"
 *
 * @author Oleg V. Safonov
 * @version $Id: DocTypeBr.java,v 1.4 2007/11/20 14:54:16 sharapov Exp $
 */
public class DocTypeBr extends DefaultPlainBrowseForm {
  private static final String LOAD_DOCTYPE_BROWSE_EJBQL = "select distinct %s from DocType dt join dt.SetOfDocTypeRights dtr %s where (dtr.Rights = 1) and (dtr.SecGroups.Id in (%s)) %s order by dt.Code";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  /**
   * коструктор
   */
  public DocTypeBr() {
    super();
    restrictionFormName = "com/mg/merp/document/resources/DocTypeRest.mfd.xml";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
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
        result.add(new TableEJBQLFieldDef(DocType.class, "Id", "dt.Id", true));
        result.add(new TableEJBQLFieldDef(DocType.class, "Code", "dt.Code", false));
        result.add(new TableEJBQLFieldDef(DocType.class, "Name", "dt.Name", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  /**
   * обработчик кнопки показа карты ДО
   */
  public void onActionShowDocFlowMap(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys)
      DocFlowHelper.showDocFlowMap(key);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    DocTypeRest restForm = (DocTypeRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder().append(DatabaseUtils.formatEJBQLStringRestriction("dt.Code", restForm.getCode(), "code", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLObjectRestriction("dtl.DocSection", restForm.getDocSection(), "docSection", paramsName, paramsValue, false)).
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "dt.Id", restForm.getAddinFieldsRestriction(), false));

    StringBuilder joinText = new StringBuilder();
    if (restForm.getDocSection() != null)
      joinText.append(" join dt.SetOfDocTypeDocSectionLink dtl ");

    return String.format(LOAD_DOCTYPE_BROWSE_EJBQL, fieldsList, joinText.toString(), ServerUtils.getUserProfile().getGroupsCommaText(), whereText.toString());
  }

  /**
   * Обработчик события "Настройки прав доступа на типы документов"
   *
   * @param event - событие
   */
  public void onActionSetupDocTypePermissions(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      ((DocTypePermissionsForm) UIProducer.produceForm("com/mg/merp/document/resources/DocTypePermissionsForm.mfd.xml")).execute((Integer) keys[0]);
  }

}
