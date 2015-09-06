/*
 * OperationBr.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.model.Operation;

import org.apache.commons.lang.BooleanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: OperationBr.java,v 1.2 2006/09/06 05:22:17 leonova Exp $
 */
public class OperationBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Operation op %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public OperationBr() {
    super();
    restrictionFormName = "com/mg/merp/crm/resources/OperationRest.mfd.xml";
  }

  @Override
  protected String createQueryText() {
    String whereText = "";
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    OperationRest restForm = (OperationRest) getRestrictionForm();
    whereText = " where 0=0 ".
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.Relation", restForm.getRelationName(), "relationName", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.Contact", restForm.getContactCode(), "contactCode", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.Curator", restForm.getCurator(), "curator", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.Responsible", restForm.getResponsible(), "responsible", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.Status", restForm.getStatus(), "status", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRestriction("op.State", restForm.getState(), "state", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("op.PlanDateFrom", restForm.getPlanFrom(), restForm.getPlanTill(), "planFrom", "planTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("op.PlanDateTill", restForm.getPlanFrom(), restForm.getPlanTill(), "planFrom", "planTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("op.FactDateFrom", restForm.getFactFrom(), restForm.getFactTill(), "factFrom", "factTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("op.FactTill", restForm.getFactFrom(), restForm.getFactTill(), "factFrom", "factTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLObjectRangeRestriction("op.CreateDate", restForm.getCreateFrom(), restForm.getCreateTill(), "createFrom", "createTill", paramsName, paramsValue, false)).
        concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "op.Id", restForm.getAddinFieldsRestriction(), false));
    if (restForm.getPlanState() != 0) {
      whereText = whereText.concat(DatabaseUtils.formatEJBQLObjectRestriction("op.IsPlan", BooleanUtils.toBoolean(restForm.getPlanState(), 2, 1), "planState", paramsName, paramsValue, false));
    }
    if (restForm.getOperationState() == 1) {
      whereText = whereText.concat(" and op.FactDateTill > op.PlanDateTill ");
    }
    if (restForm.getOperationState() == 2) {
      whereText = whereText.concat(" and op.FactDateTill < op.PlanDateTill ");
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);


  }

  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Operation.class, "Id", "op.Id", true));
        result.add(new TableEJBQLFieldDef(Operation.class, "Code", "op.Code", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Relation", "rel.Code", "left join op.Relation as rel", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Contact", "pcont.Surname", "left join op.Contact as cont left join cont.Person as pcont", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Kind", "k.Code", "left join op.Kind as k", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Priority", "pr.Code", "left join op.Priority as pr", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Purpose", "pur.Code", "left join op.Purpose as pur", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "IsPlan", "op.IsPlan", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "CreateDate", "op.CreateDate", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "PlanDateFrom", "op.PlanDateFrom", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "PlanDateTill", "op.PlanDateTill", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "FactDateFrom", "op.FactDateFrom", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "FactDateTill", "op.FactDateTill", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Status", "op.Status", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "State", "op.State", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Curator", "pcur.Surname", "left join op.Curator as cur left join cur.Person as pcur", false));
        result.add(new TableEJBQLFieldDef(Operation.class, "Responsible", "presp.Surname", "left join op.Responsible as resp left join resp.Person as presp", false));

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };

  }

}
