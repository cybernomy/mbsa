/* BusinessAddinWorkbenchImpl.java
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
package com.mg.merp.baiengine.support;

import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddinWorkbench;
import com.mg.merp.baiengine.model.EngineType;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.Folder;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessAddinWorkbenchImpl.java,v 1.2 2007/01/19 07:34:14
 *          poroxnenko Exp $
 */
public class BusinessAddinWorkbenchImpl implements BusinessAddinWorkbench {

	private static final short DEFAULT_BAI_FOLDER_TYPE = 9500;

	public PersistentObject[] getBais(String query) throws Exception {
		byte[] str = query.getBytes();
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '*')
				str[i] = '%';
			else if (str[i] == '?')
				str[i] = '_';

		}
		query = new String(str);
		TransactionManager tm = ServerUtils.getTransactionManager();
		tm.begin();
		try {
			List<Repository> result = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Repository.class)
					.add(Restrictions.like("Code", query))
					.addOrder(Order.asc("Code")));

			tm.commit();
			return result.toArray(new Repository[result.size()]);
		} catch (Exception ex) {
			tm.rollback();
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.BAI_LOAD_ERROR), ex.getCause());
		}
	}

	public PersistentObject editBai(PersistentObject repository) throws Exception {
		TransactionManager tm = ServerUtils.getTransactionManager();
		tm.begin();
		String tmpCode = ((Repository) repository).getCode();
		boolean wasChanged = false;
		try {
			PersistentManager pm = ServerUtils.getPersistentManager();
			Repository rpr = pm.find(Repository.class, ((Repository) repository).getId());
			// был изменён
			wasChanged = rpr.getSysVersion().after(((Repository) repository).getSysVersion());

			// проверка уникальности кода
			// Берём список, т.к. раньше не было уникальности кода алгоритма
			List<Repository> rpzL = OrmTemplate.getInstance().findByCriteria(
					Repository.class, Restrictions.eq("Code", tmpCode));
			if (rpzL.size() == 1 && !wasChanged) {

				rpr.setCode(tmpCode);

				rpr.setName(((Repository) repository).getName());
				rpr.setImplementationName(((Repository) repository).getImplementationName());

				pm.persist(rpr);

				tm.commit();

				return rpr;
			}
		} catch (Exception ex) {
			tm.rollback();
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.BAI_EDIT_ERROR, new Object[] {tmpCode}), ex.getCause());
		}
		tm.rollback();
		if (wasChanged)
			throw new DataAccessException(Messages.getInstance().getMessage(Messages.BAI_VERSION_ERROR, new Object[] {tmpCode}));
		throw new DataAccessException(Messages.getInstance().getMessage(Messages.BAI_UNIQUE_CODE, new Object[] {tmpCode}));
	}

	public PersistentObject addBai(PersistentObject repository) throws NamingException,
			NotSupportedException, SystemException {
		TransactionManager tm = ServerUtils.getTransactionManager();
		tm.begin();
		String tmpCode = ((Repository) repository).getCode();
		try {
			PersistentManager pm = ServerUtils.getPersistentManager();

			Repository rpz = OrmTemplate.getInstance().findUniqueByCriteria(
					Repository.class, Restrictions.eq("Code", tmpCode));
			if (rpz == null) {

				Folder fldr = OrmTemplate.getInstance().findUniqueByCriteria(Folder.class,
						Restrictions.and(Restrictions.eq("FolderType", DEFAULT_BAI_FOLDER_TYPE),
								         Restrictions.isNull("Folder")));
				((Repository) repository).setFolder(fldr);
				((Repository) repository).setEngine(EngineType.JAVA_ENGINE);

				// SysClient проставляется автоматически, но при наличии
				// контекста пользователя. Его сейчас нет
				((Repository) repository).setSysClient(fldr.getSysClient());

				pm.persist(((Repository) repository));
				tm.commit();

				return repository;
			}
		} catch (Exception ex) {
			tm.rollback();
			// операция не удалась
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.BAI_ADD_ERROR, new Object[] {tmpCode}), ex.getCause());
		}
		// если условие (rpz == null)не выполнилось
		tm.rollback();
		throw new DataAccessException(Messages.getInstance().getMessage(Messages.BAI_UNIQUE_CODE, new Object[] {tmpCode}));
	}

	public void deleteBaiList(Integer[] ids) throws Exception {
		TransactionManager tm = ServerUtils.getTransactionManager();
		tm.begin();

		List<Integer> idL = Arrays.asList(ids);
		try {
			OrmTemplate.getInstance().bulkUpdate("delete from Repository r where r.Id in (:ids)", "ids", idL);
			tm.commit();
		} catch (Exception ex) {
			tm.rollback();
			throw new DataAccessException(Messages.getInstance().getMessage(Messages.BAI_DELETE_ERROR), ex.getCause());
		}
	}

}
