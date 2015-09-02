/*
 * AbstractEntityInterceptor.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.generic;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.api.orm.PersistentObject;

/**
 * Абстрактная реализация перехватчика действий над объектами сущностями, используется
 * в качестве базового класса для прикладных перехватчиков
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractEntityInterceptor.java,v 1.3 2007/12/17 09:10:50 safonov Exp $
 */
public abstract class AbstractEntityInterceptor implements EntityInterceptor {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#getName()
	 */
	public abstract String getName();
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#getHandledEntities()
	 */
	public abstract String[] getHandledEntities();
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostLoad(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostLoad(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostPersist(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostPersist(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostRemove(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostRemove(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostUpdate(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostUpdate(PersistentObject entity, AttributeMap oldState) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPrePersist(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPrePersist(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPreRemove(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPreRemove(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPreUpdate(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPreUpdate(PersistentObject entity, AttributeMap oldState) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostCommitPersist(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostCommitPersist(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostCommitRemove(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostCommitRemove(PersistentObject entity) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptor#onPostCommitUpdate(com.mg.framework.api.orm.PersistentObject)
	 */
	public void onPostCommitUpdate(PersistentObject entity, AttributeMap oldState) {
	}

}
