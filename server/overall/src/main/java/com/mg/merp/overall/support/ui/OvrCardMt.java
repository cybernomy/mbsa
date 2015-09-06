/*
 * OvrCardMt.java
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
package com.mg.merp.overall.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.overall.CardHistServiceLocal;
import com.mg.merp.overall.SizeServiceLocal;
import com.mg.merp.overall.model.OvrCardHist;
import com.mg.merp.overall.model.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес - компонента "Лицевые карточки сотрудников"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCardMt.java,v 1.5 2008/06/30 04:22:00 alikaev Exp $
 */
public class OvrCardMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap sizeProperties = new LocalDataTransferObject();
  protected AttributeMap historyProperties = new LocalDataTransferObject();
  private MaintenanceTableController size;
  private SizeServiceLocal sizeService;
  private MaintenanceTableController history;
  private CardHistServiceLocal historyService;

  public OvrCardMt() throws Exception {
    super();
    addMasterModelListener(this);
    sizeService = (SizeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/overall/Size"); //$NON-NLS-1$
    size = new MaintenanceTableController(sizeProperties);
    size.initController(sizeService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Size s where s.OvrCard = :ovrCard"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrCard"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList);
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
        result.add(new TableEJBQLFieldDef(Size.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "CatalogGroupsTypeId", "s.CatalogGroupsTypeId", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "ClothesSize", "s.ClothesSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "ShoesSize", "s.ShoesSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "HatSize", "s.HatSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "GasMaskSize", "s.GasMaskSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "RespiratorSize", "s.RespiratorSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "MittensSize", "s.MittensSize", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Size.class, "GlovesSize", "s.GlovesSize", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, sizeService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(size);

    historyService = (CardHistServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/overall/CardHist"); //$NON-NLS-1$
    history = new MaintenanceTableController(historyProperties);
    history.initController(historyService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from OvrCardHist och %s where och.OvrCard = :ovrCard"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrCard"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Id", "och.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "CatalogGroupsType", "och.CatalogGroupsType", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Catalog.Code", "cat.Code", "left join och.Catalog as cat", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Catalog.FullName", "cat.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "GiveDate", "och.GiveDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Quantity", "och.Quantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Catalog.Measure1", "meas.Code", "left join cat.Measure1 as meas", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "ShelfLife", "och.ShelfLife", false));         //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "ShelfLifeMeas", "och.ShelfLifeMeas", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "ReadOutDate", "och.ReadOutDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "Deterioration", "och.Deterioration", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "NdeCost", "och.NdeCost", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "NdeSumma", "och.NdeSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "DepreciableValue", "och.DepreciableValue", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "CommonSummaForDinch", "och.NdeSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "DinchedSumma", "och.DinchedSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "RestOfDinchSumma", "och.RestOfDinchSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "ArrearSumma", "och.ArrearSumma", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "IsPeriodic", "och.IsPeriodic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "IsBasic", "och.IsBasic", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "RemoveType", "och.RemoveType", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "RemoveDate", "och.RemoveDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(OvrCardHist.class, "PlanRemoveDate", "och.PlanRemoveDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, historyService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(history);

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    sizeProperties.put("OvrCard", event.getEntity()); //$NON-NLS-1$
    historyProperties.put("OvrCard", event.getEntity());     //$NON-NLS-1$
  }

}
