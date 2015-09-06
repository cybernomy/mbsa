/*
 * VersionBr.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.paymentcontrol.VersionStatusServiceLocal;
import com.mg.merp.paymentcontrol.model.Version;

import java.io.Serializable;
import java.util.Set;

/**
 * Контроллер формы списка версий планирования платежей
 *
 * @author leonova
 * @version $Id: VersionBr.java,v 1.5 2007/05/23 09:58:56 sharapov Exp $
 */
public class VersionBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from Version v %s %s"; //$NON-NLS-1$

  public VersionBr() {
    super();
    restrictionFormName = "com/mg/merp/paymentcontrol/resources/VersionRest.mfd.xml"; //$NON-NLS-1$
  }

  @Override
  protected String createQueryText() {
    String whereText = ""; //$NON-NLS-1$
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    VersionRest restForm = (VersionRest) getRestrictionForm();
    if (restForm.isAvailableOnly()) {
      whereText = " where ".concat(" v.Available = 1 "); //$NON-NLS-1$ //$NON-NLS-2$
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
        result.add(new TableEJBQLFieldDef(Version.class, "Id", "v.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Version.class, "Name", "v.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Version.class, "Creator", "cr.Name", "left join v.Creator as cr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Version.class, "CreateDate", "v.CreateDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Version.class, "Available", "v.Available", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
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

  public void onActionShowStatus(WidgetEvent event) throws Exception {
    final VersionStatusServiceLocal service = (VersionStatusServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/VersionStatus"); //$NON-NLS-1$
    VersionStatusBr form = (VersionStatusBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      form.setVersionId(key);
      form.run();
      break;
    }
  }

  /**
   * Обработчик события КМ "Установить статус версии"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionSetStatus(WidgetEvent event) throws Exception {
    PersistentObject[] searchedEntities = getSearchedEntities();
    if (searchedEntities != null && searchedEntities.length > 0) {
      final Version version = (Version) searchedEntities[0];
      if (version == null)
        return;

      final VersionStatusDlg dialog = (VersionStatusDlg) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/VersionStatusDlg.mfd.xml"); //$NON-NLS-1$
      dialog.addOkActionListener(new FormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
         */
        public void actionPerformed(FormEvent event) {
          VersionStatusServiceLocal versionStatusService = (VersionStatusServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(VersionStatusServiceLocal.LOCAL_SERVICE_NAME);
          versionStatusService.addVersionStatus(version, dialog.getKind(), dialog.getCreateDate(), dialog.getDateFrom(), dialog.getDateTill());
        }
      });
      dialog.execute();
    }
  }

  /**
   * Обработчик события КМ "Планирование платежей"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionShowPlanPayment(WidgetEvent event) throws Exception {
    PmcPlaningDlg dialog = (PmcPlaningDlg) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcPlaningDlg.mfd.xml"); //$NON-NLS-1$
    PersistentObject[] searchedEntities = getSearchedEntities();
    if (searchedEntities != null && searchedEntities.length > 0) {
      Integer versionId = ((Version) searchedEntities[0]).getId();
      dialog.setVersionId(versionId);
      dialog.run();
    }
  }

}
