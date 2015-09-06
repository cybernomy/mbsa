/*
 * FeatureValServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.core.support;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.core.FeatureValServiceLocal;
import com.mg.merp.core.model.FeatureVal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Значания дополнительных признаков"
 *
 * @author leonova
 * @version $Id: FeatureValServiceBean.java,v 1.6 2007/07/06 08:54:08 safonov Exp $
 */
@Stateless(name = "merp/core/FeatureValService")
public class FeatureValServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeatureVal, Integer> implements FeatureValServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.core.FeatureValServiceLocal#loadEnumValues(int)
   */
  @PermitAll
  public String[] loadEnumValues(int featureId) {
    List<String> result = JdbcTemplate.getInstance().query("select val from featureval where feature_id = ?", new Object[]{featureId}, new RowMapper<String>() {

      public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(1);
      }

    });
    return result.toArray(new String[result.size()]);
  }

}
