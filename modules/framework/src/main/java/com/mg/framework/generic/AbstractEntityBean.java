/*
 * Created on 27.04.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.framework.generic;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * @author safonov
 *
 *         To change the template for this generated type comment go to Window>Preferences>Java>Code
 *         Generation>Code and Comments
 */
public abstract class AbstractEntityBean
    extends AbstractEnterpriseBean
    implements EntityBean {

  private EntityContext entityContext;

  protected final EntityContext getEntityContext() {
    return entityContext;
  }

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
   */
  public void setEntityContext(EntityContext entityContext)
      throws EJBException {
    this.entityContext = entityContext;
  }

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#ejbActivate()
   */
  public void ejbActivate() throws EJBException {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#ejbPassivate()
   */
  public void ejbPassivate() throws EJBException {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#ejbLoad()
   */
  public abstract void ejbLoad() throws EJBException;

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#ejbRemove()
   */
  public abstract void ejbRemove()
      throws RemoveException, EJBException;

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#ejbStore()
   */
  public abstract void ejbStore() throws EJBException;

  /* (non-Javadoc)
   * @see javax.ejb.EntityBean#unsetEntityContext()
   */
  public void unsetEntityContext() throws EJBException {
    this.entityContext = null;
  }

}
