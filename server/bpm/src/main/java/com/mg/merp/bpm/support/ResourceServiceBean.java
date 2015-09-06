/*
 * ResourceServiceBean.java
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
package com.mg.merp.bpm.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.bpm.ResourceServiceLocal;
import com.mg.merp.bpm.model.Resource;
import com.mg.merp.bpm.model.ResourceGroupLink;
import com.mg.merp.security.model.Groups;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: ResourceServiceBean.java,v 1.3 2007/11/15 10:13:53 safonov Exp $
 */
@Stateless(name = "merp/bpm/ResourceService") //$NON-NLS-1$
public class ResourceServiceBean extends
    AbstractPOJODataBusinessObjectServiceBean<Resource, Integer> implements ResourceServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, Resource entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "Key")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.ResourceServiceLocal#addMember(int, int)
   */
  @PermitAll
  public void addMember(int resourceId, int roleId) {
    ResourceGroupLink link = new ResourceGroupLink();
    link.setGroup(getPersistentManager().find(Groups.class, roleId));
    link.setResource(getPersistentManager().find(Resource.class, resourceId));
    getPersistentManager().persist(link);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.ResourceServiceLocal#removeMember(int, int)
   */
  @PermitAll
  public void removeMember(int resourceId, int roleId) {
    for (Object link : OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ResourceGroupLink.class)
        .add(Restrictions.eq("Resource.Id", resourceId)) //$NON-NLS-1$
        .add(Restrictions.eq("Group.Id", roleId)))) //$NON-NLS-1$
      getPersistentManager().remove((PersistentObject) link);
  }

}
