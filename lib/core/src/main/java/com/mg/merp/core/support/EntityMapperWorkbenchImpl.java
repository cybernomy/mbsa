/* EntityMapperWorkbenchImpl.java
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
package com.mg.merp.core.support;

import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.EntityTransformerImpl;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.EntityMapperWorkbench;
import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.core.model.SysClient;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperWorkbenchImpl.java,v 1.2 2007/09/21 11:42:40 safonov Exp $
 */
public class EntityMapperWorkbenchImpl implements EntityMapperWorkbench {

	public EntityTransformerMapping[] getEntityMappers(String query) throws Exception {
		byte[] str = query.getBytes();
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '*')
				str[i] = '%';
			else if (str[i] == '?')
				str[i] = '_';

		}
		query = new String(str);
		try {
			List<EntityTransformerMapping> result = OrmTemplate.getInstance().findByCriteria(EntityTransformerMapping.class, Restrictions.like("MapId", query));

			return result.toArray(new EntityTransformerMapping[result.size()]);
		} catch (Exception ex) {
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.ENTITYMAPPERS_LOAD_ERROR), ex.getCause());
		}
	}

	public EntityTransformerMapping editEntityMapper(EntityTransformerMapping mapping) throws Exception {
		boolean wasChanged = false;
		try {
			PersistentManager pm = ServerUtils.getPersistentManager();
			EntityTransformerMapping etm = pm.find(EntityTransformerMapping.class, mapping.getId());
			// был изменён
			wasChanged = etm.getSysVersion().after(mapping.getSysVersion());
			if (!wasChanged) {
				etm.setMapId(mapping.getMapId());
				//TODO: затупка, взять нормальный
				etm.setApplicationLayer(mapping.getApplicationLayer());
				etm.setClassA(mapping.getClassA());
				etm.setClassB(mapping.getClassB());
				etm.setHashAB(EntityTransformerImpl.getHash(mapping.getClassA(), mapping.getClassB()));

				pm.persist(etm);

				return etm;
			}else
				throw new DataAccessException(Messages.getInstance().getMessage(Messages.ENTITYMAPPER_VERSION_ERROR, new Object[] {mapping.getMapId()}));	
		} catch (DataAccessException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.ENTITYMAPPER_EDIT_ERROR, new Object[] {mapping.getMapId()}), ex.getCause());
		}
	}

	public EntityTransformerMapping addEntityMapper(EntityTransformerMapping mapping) throws NamingException,
			NotSupportedException, SystemException {
		try {
			PersistentManager pm = ServerUtils.getPersistentManager();
			// SysClient проставляется автоматически, но при наличии
			// контекста пользователя. Его сейчас нет
			SysClient sc = pm.find(SysClient.class, 1);
			mapping.setSysClient(sc);
			mapping.setHashAB(EntityTransformerImpl.getHash(mapping.getClassA(), mapping.getClassB()));
		
			pm.persist(mapping);

			return mapping;
		} catch (Exception ex) {
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.ENTITYMAPPER_ADD_ERROR, new Object[] {mapping.getMapId()}), ex.getCause());
		}
	}

	public void deleteEntityMappersList(Integer[] ids) throws Exception {
		List<Integer> idL = Arrays.asList(ids);
		try {
			OrmTemplate.getInstance().bulkUpdate("delete from EntityTransformerMapping etm where etm.Id in (:ids)", "ids", idL);
		} catch (Exception ex) {
			throw new DataAccessException(Messages.getInstance().getMessage(Messages.ENTITYMAPPERS_DELETE_ERROR), ex.getCause());
		}
	}

}
