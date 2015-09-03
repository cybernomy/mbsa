/*
 * FolderServiceBean.java
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
package com.mg.merp.reference.support;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.FolderServiceLocal;

/**
 * Бизнес-компонент "Папки"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: FolderServiceBean.java,v 1.7 2007/08/16 14:17:07 safonov Exp $
 */
@Stateless(name="merp/reference/FolderService")
@PermitAll
public class FolderServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Folder, Integer> implements FolderServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, Folder entity) {
		context.addRule(new MandatoryStringAttribute(entity, "FName"));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(ID[], java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			Folder entity = load(key);
			Folder targetFolder = (Folder) targetEntity;
			//не копируем корневую папку и папку на саму себя, идентификатор должен быть больше идентификатора приемника, в противном
			//случае невозможно построить дерево иерархии
			if (entity.getFolder() != null && entity.getId() > targetFolder.getId() && entity.getFolder().getId() != targetFolder.getId()) {
				entity.setFolder(targetFolder);
				result = true;
			}
		}
		return result;
	}

}
