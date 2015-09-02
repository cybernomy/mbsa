/* EntityTransformatorService.java
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.service.EntityTransformerImpl;

/**
 * Реализация сервиса преобразования сущностей для контейнера JBoss
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformerService.java,v 1.3 2007/09/21 09:51:23 safonov Exp $ 
 */
public class EntityTransformerService extends ServiceMBeanSupport implements EntityTransformerServiceMBean {

	private EntityTransformerImpl delegate = null;

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		super.createService();
		delegate = new EntityTransformerImpl();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		super.destroyService();
		delegate = null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Class, java.lang.String)
	 */
	public <S, D> D map(S srcObj, Class<D> dstClass, String mapId) {
		return delegate.map(srcObj, dstClass, mapId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Class)
	 */
	public <S, D> D map(S srcObj, Class<D> dstClass) {
		return delegate.map(srcObj, dstClass);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Object, java.lang.String)
	 */
	public <S, D> void map(S srcObj, D dstObj, String mapId) {
		delegate.map(srcObj, dstObj, mapId);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#map(java.lang.Object, java.lang.Object)
	 */
	public <S, D> void map(S srcObj, D dstObj) {
		delegate.map(srcObj, dstObj);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityTransformer#rebuildMapping()
	 */
	public void rebuildMapping() {
		delegate.rebuildMapping();
	}

}
