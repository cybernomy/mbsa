/*
 * ScrapDocumentBr.java
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
 * Контроллер формы списка актов на списание потерь
 *
 * @author leonova
 * @version $Id: ScrapDocumentBr.java,v 1.4 2008/02/21 12:23:10 alikaev Exp $
 */
public class ScrapDocumentBr extends ManufactureDocumentBr {
  protected String INIT_QUERY_TEXT = "select distinct %s from ScrapDocumentHead d %s %s  order by d.DocDate, d.Id ";
  protected ScrapHeadRest restDocument;

  /*
   * (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    Set<TableEJBQLFieldDef> fieldDefs = ((ScrapDocumentMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    restDocument = (ScrapHeadRest) getRestrictionForm();

    whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcStock", restDocument.getSrcStockCode(), "srcStockCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.SrcMol", restDocument.getSrcMolCode(), "srcMolCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ScrapReason", restDocument.getScrapReason(), "scrapReason", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ScrapType", restDocument.getScrapType(), "scrapType", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectContractor", restDocument.getDetectContractor(), "detectContractorCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectCrew", restDocument.getDetectCrew(), "detectCrewCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectEmployee", restDocument.getDetectEmployee(), "detectEmployeeCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectJob", restDocument.getDetectJob(), "detectJobNumber", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectOper", restDocument.getDetectOper(), "detectOperNumber", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DetectWC", restDocument.getDetectWorkCenter(), "detectWCCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseContractor", restDocument.getCauseContractor(), "causeContractorCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseCrew", restDocument.getCauseCrew(), "causeCrewCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseEmployee", restDocument.getCauseEmployee(), "causeEmployeeCode", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseJob", restDocument.getCauseJob(), "causeJobNumber", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseOper", restDocument.getCauseOper(), "causeOperNumber", paramsName, paramsValue, false))
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.CauseWC", restDocument.getCauseWorkCenter(), "causeWCCode", paramsName, paramsValue, false));

    if (whereText.indexOf("Catalog") != -1) {
      fromList = (", ScrapDocumentSpec as ds ").concat(fromList);
    }

    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }
}
