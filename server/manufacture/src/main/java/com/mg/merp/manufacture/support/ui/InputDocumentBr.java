/*
 * InputDocumentBr.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;

import java.util.Set;

/**
 * Контроллер формы списка актов на списание
 *
 * @author leonova
 * @version $Id: InputDocumentBr.java,v 1.4 2008/02/21 12:23:10 alikaev Exp $
 */
public class InputDocumentBr extends ManufactureDocumentBr {
  protected String INIT_QUERY_TEXT = "select distinct %s from InputDocumentHead d %s %s order by d.DocDate, d.Id ";
  protected InputHeadRest restDocument;

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    Set<TableEJBQLFieldDef> fieldDefs = ((InputDocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    restDocument = (InputHeadRest) getRestrictionForm();

    whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("d.OutputDocHead.DocDate", restDocument.getOutputDocDateFrom(), restDocument.getOutputDocDateTill(), "outputDocDateFrom", "outputDocDateTill", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLStringRestriction("d.OutputDocHead.DocNumber", restDocument.getOutputDocNumber(), "outputDocNumber", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.OutputDocHead.DocType", restDocument.getOutputDocType(), "outputDocType", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restDocument.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.From", restDocument.getFromCode(), "fromCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.To", restDocument.getToCode(), "toCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Contractor", restDocument.getContractor(), "contractor", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Employee", restDocument.getEmployee(), "employee", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Job", restDocument.getJob(), "job", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Oper", restDocument.getOper(), "oper", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Crew", restDocument.getCrew(), "crew", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.WC", restDocument.getWorkCenter(), "workCenter", paramsName, paramsValue, false));
    if (whereText.indexOf("Catalog") != -1) {
      fromList = (", InputDocumentSpec as ds ").concat(fromList);
    }

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

}

