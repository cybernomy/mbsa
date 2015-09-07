/*
 * OvrCardBr.java
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.overall.model.OvrCard;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.support.ui.OrgUnitTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы списка бизнес-компонента "Лицевые карточки сотрудников"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: OvrCardBr.java,v 1.5 2008/03/25 14:06:35 alikaev Exp $
 */
public class OvrCardBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from OvrCard oc %s %s";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public OvrCardBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/OrgUnit");
    tree.setParentPropertyName("OrgUnit.Id");
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("OrgUnit", master);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<OrgUnit> list = OrmTemplate.getInstance().find(OrgUnit.class, String.format("from OrgUnit org where %s order by org.Id", DatabaseUtils.generateFlatBrowseWhereEJBQL("org.Id", 4)));
    return OrgUnitTreeNode.createTree(list);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    paramsName.clear();
    paramsValue.clear();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(true, "oc.OrgUnit", 1, "folder", folderEntity, paramsName, paramsValue, true));
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
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
        result.add(new TableEJBQLFieldDef(OvrCard.class, "Id", "oc.Id", true));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "StfJob", "j.Name", "left join oc.StfJob as j", false));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "StfPosition", "pos.Name", "left join oc.StfPosition as pos", false));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "ActDateFrom", "oc.ActDateFrom", false));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "ActDateTo", "oc.ActDateTo", false));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "OvrCardNumber", "oc.OvrCardNumber", false));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "RefPersonnel", "np.Surname||' '||np.Name||' '||np.Patronymic", "left join oc.RefPersonnel as pers left join pers.Person as np", true));
        result.add(new TableEJBQLFieldDef(OvrCard.class, "RefPersonnel.TableNumber", "pers.TableNumber", false));
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

}
