/*
 * PriceListAccessServiceBean.java
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
package com.mg.merp.reference.support;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.JdbcUtils;
import com.mg.merp.reference.PriceListAccessResult;
import com.mg.merp.reference.PriceListAccessServiceLocal;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceListHeadRights;
import com.mg.merp.security.model.Groups;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Права на заголовки прайс-листов"
 *
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListAccessServiceBean.java,v 1.6 2009/03/17 09:15:59 safonov Exp $
 */
@Stateless(name = "merp/reference/PriceListAccessService")
public class PriceListAccessServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PriceListHeadRights, Integer> implements PriceListAccessServiceLocal {

  private static final String LOAD_PRICELIST_PERMISSIONS_SQL = "select g.id, g.name, r.rights, r.id from sec_groups g left join pricelisthead_rights r on (r.group_id = g.id) and (r.rec_id = ?) order by g.name"; //$NON-NLS-1$

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.PriceListAccessServiceLocal#grantPermission(com.mg.merp.reference.PriceListAccessResult, java.lang.Integer)
   */
  public void grantPermission(PriceListAccessResult permission, Integer priceListId) {
    doGrantPermission(permission, priceListId);
  }

  /**
   * Установить право доступа для прайс-листа
   *
   * @param permission  - право доступа
   * @param priceListId - идентификатор прайс-листа
   */
  protected void doGrantPermission(PriceListAccessResult permission, Integer priceListId) {
    if (permission == null) {
      getLogger().info("PriceListAccessResult is null"); //$NON-NLS-1$
      return;
    }
    PersistentManager pm = getPersistentManager();
    if (permission.getPermissionId() != null) {
      PriceListHeadRights priceListHeadRights = pm.find(PriceListHeadRights.class, permission.getPermissionId());
      priceListHeadRights.setRights(1);
      pm.merge(priceListHeadRights);
    } else {
      PriceListHeadRights priceListHeadRights = new PriceListHeadRights();
      priceListHeadRights.setPriceListHead(pm.find(PriceListHead.class, priceListId));
      priceListHeadRights.setRights(1);
      priceListHeadRights.setGroups(pm.find(Groups.class, permission.getRoleId()));
      pm.persist(priceListHeadRights);
      permission.setPermissionId(priceListHeadRights.getId());
      permission.setPermission(true);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.PriceListAccessServiceLocal#loadPriceListPermissions(java.lang.Integer)
   */
  public List<PriceListAccessResult> loadPriceListPermissions(Integer priceListId) {
    return doLoadPriceListPermissions(priceListId);
  }

  /**
   * Загрузить список прав пользователя для прайс-листа
   *
   * @param priceListId - идентификатор прайс-листа
   * @return - список прав пользователя для прайс-листа
   */
  protected List<PriceListAccessResult> doLoadPriceListPermissions(final Integer priceListId) {
    return JdbcTemplate.getInstance().query(LOAD_PRICELIST_PERMISSIONS_SQL, new Object[]{priceListId}, new RowMapper<PriceListAccessResult>() {

      public PriceListAccessResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PriceListAccessResult(rs.getInt(1), rs.getString(2), new Integer(1).equals(JdbcUtils.getIntegerValue(rs, 3)), JdbcUtils.getIntegerValue(rs, 4));
      }

    });
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.PriceListAccessServiceLocal#revokePermission(com.mg.merp.reference.PriceListAccessResult)
   */
  public void revokePermission(PriceListAccessResult permission) {
    doRevokePermission(permission);
  }

  /**
   * Отменить право доступа для прас-листа
   *
   * @param permission - право доступа
   */
  protected void doRevokePermission(PriceListAccessResult permission) {
    if (permission == null) {
      getLogger().info("PriceListAccessResult is null"); //$NON-NLS-1$
      return;
    }
    PersistentManager pm = getPersistentManager();
    PriceListHeadRights priceListHeadRights = pm.find(PriceListHeadRights.class, permission.getPermissionId());
    priceListHeadRights.setRights(0);
    pm.merge(priceListHeadRights);
  }

}
