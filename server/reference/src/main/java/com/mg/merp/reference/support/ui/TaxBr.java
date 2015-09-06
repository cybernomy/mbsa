/*
 * TaxBr.java
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

package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.model.Tax;

import java.util.Set;

/**
 * Браузер налогов
 *
 * @author Oleg V. Safonov
 * @version $Id: TaxBr.java,v 1.1 2006/08/02 12:03:15 leonova Exp $
 */
public class TaxBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select tax.Id";
  private String fieldsList;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    return DatabaseUtils.embedAddinFieldsBrowseEJBQL(INIT_QUERY_TEXT.concat(fieldsList).concat(" from Tax tax order by tax.Id"), service, "tax.Id", getFieldsSet()); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#getDefaultFieldsSet()
   */
  @Override
  protected Set<String> getDefaultFieldsSet() {
    Set<String> result = super.getDefaultFieldsSet();
    result.addAll(StringUtils.split("Id,Code,TName,ActiveDate,TaxType,TaxForm,DirectRate,InverseRate,Summ,DeactivateDate", ",")); //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultFieldsSet(result, service);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#createColumnsModel()
       */
      @Override
      protected void createColumnsModel() {
        //order is important
        fieldsList = "";
        Set<String> fields = getFieldsSet();
        addColumnDef(Tax.class, "Id", null); //$NON-NLS-1$
        if (fields.contains("Code")) {
          fieldsList = fieldsList.concat(", tax.Code");
          addColumnDef(Tax.class, "Code", null); //$NON-NLS-1$
        }
        if (fields.contains("TName")) {
          fieldsList = fieldsList.concat(", tax.TName");
          addColumnDef(Tax.class, "TName", null); //$NON-NLS-1$
        }
        if (fields.contains("ActiveDate")) {
          fieldsList = fieldsList.concat(", tax.ActiveDate");
          addColumnDef(Tax.class, "ActiveDate", null); //$NON-NLS-1$
        }
        if (fields.contains("TaxType")) {
          fieldsList = fieldsList.concat(", tax.TaxType");
          addColumnDef(Tax.class, "TaxType", null); //$NON-NLS-1$
        }
        if (fields.contains("TaxForm")) {
          fieldsList = fieldsList.concat(", tax.TaxForm");
          addColumnDef(Tax.class, "TaxForm", null); //$NON-NLS-1$
        }
        if (fields.contains("DirectRate")) {
          fieldsList = fieldsList.concat(", tax.DirectRate");
          addColumnDef(Tax.class, "DirectRate", null); //$NON-NLS-1$
        }
        if (fields.contains("InverseRate")) {
          fieldsList = fieldsList.concat(", tax.InverseRate");
          addColumnDef(Tax.class, "InverseRate", null); //$NON-NLS-1$
        }
        if (fields.contains("Summ")) {
          fieldsList = fieldsList.concat(", tax.Summ");
          addColumnDef(Tax.class, "Summ", null); //$NON-NLS-1$
        }
        if (fields.contains("DeactivateDate")) {
          fieldsList = fieldsList.concat(", tax.DeactivateDate");
          addColumnDef(Tax.class, "DeactivateDate", null); //$NON-NLS-1$
        }
        addAddinFieldDef(service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText());
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
