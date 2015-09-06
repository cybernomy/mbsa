/*
 * Created on 16.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mg.framework.generic;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;

/**
 * @author safonov
 *
 *         TODO To change the template for this generated type comment go to Window - Preferences -
 *         Java - Code Style - Code Templates
 */
public abstract class AbstractBusinessObjectServiceImpl implements BusinessObjectService {

	/* (non-Javadoc)
     * @see com.mg.framework.api.BusinessObjectService#loadMetadata()
	 */
//	public BeanMetadata loadMetadata() throws ApplicationException {
//		// TODO Auto-generated method stub
//		return null;
//	}

  /* (non-Javadoc)
   * @see com.mg.framework.api.BusinessObjectService#translateDataAccessException(com.mg.framework.api.ApplicationException)
   */
  public String translateDataAccessException(ApplicationException e)
      throws ApplicationException {
    // TODO Auto-generated method stub
    return null;
  }

}
