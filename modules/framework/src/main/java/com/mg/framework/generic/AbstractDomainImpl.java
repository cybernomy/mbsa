/*
 * AbstractDomainImpl.java
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
package com.mg.framework.generic;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.Domain;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.BusinessObjectFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import static com.mg.framework.support.Messages.INTERNAL_SOFTWARE_EXCEPTION;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractDomainImpl.java,v 1.7 2006/09/28 12:24:12 safonov Exp $
 * @deprecated
 */
@Deprecated
public abstract class AbstractDomainImpl implements Domain {

  private int getClassId() throws ApplicationException {
    List<Integer> list = getJdbcTemplate().query("select c.id from sys_class c where c.bean_name = ?", new Object[]{getName()},
        new RowMapper<Integer>() {
          public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Integer(rs.getInt("id"));
          }
        }
    );
    if (list.size() == 0)
      return 0;
    else
      return list.get(0).intValue();

  }

  protected BusinessObjectService getService(String name) throws NamingException, ApplicationException {
    return BusinessObjectFactory.getInstance().getLocalService(name);
  }

  public String getDeploymentDescriptorName() throws ApplicationException {
    return null; //$NON-NLS-1$
  }

  protected String getName() {
    return "";
  }

//	protected BeanMetadata doLoadMetadata() throws ApplicationException {
//        BeanMetadata result = new BeanMetadata();
//        result.name = getName();
//        result.class_id = getClassId();
//        return result;
//        //return DDUtils.loadMetadata(getDeploymentDescriptorName());
//	}

  protected JdbcTemplate getJdbcTemplate() {
    return JdbcTemplate.getInstance();
  }

//	public BeanMetadata loadMetadata() throws ApplicationException {
//		/*BeanMetadata result = new BeanMetadata();
//		result.class_id = 74;
//		result.attr_metadata_list = new com.mg.framework.api.metadata.AttributeMetadata[0];
//		result.feature_list = new com.mg.framework.api.metadata.BeanFeature[0];
//		result.name = "Reference/Measure";
//		return result;*/
//		return doLoadMetadata();
//	}

  public void reloadMetadata() throws ApplicationException {
  }

  public String translateDataAccessException(ApplicationException e) throws ApplicationException {
    Throwable cause = e.getCause();
    return (cause != null) && (cause.getLocalizedMessage() != null) && (cause.getLocalizedMessage().equals(e.getLocalizedMessage())) ?
        Messages.getInstance().getMessage(INTERNAL_SOFTWARE_EXCEPTION) :
        e.getLocalizedMessage();
  }

  public void destroy() throws Throwable {
  }
}
