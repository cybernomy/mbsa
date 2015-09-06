/*
 * CatalogFolderServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.CatalogFolderServiceLocal;
import com.mg.merp.reference.model.CatalogFolder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Иерархическая структура каталога, реализация сервиса бизнес-компонента "Папки каталога"
 *
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogFolderServiceBean.java,v 1.8 2008/05/16 05:54:25 alikaev Exp $
 */
@Stateless(name = "merp/reference/CatalogFolderService")
@PermitAll
public class CatalogFolderServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<CatalogFolder, Integer> implements CatalogFolderServiceLocal {

  /**
   * Получить список дочерних папок каталога
   *
   * @param catalogFolder - папка каталога
   * @return список дочерних папок каталога
   */
  private static List<CatalogFolder> getChildCatalogFolders(CatalogFolder catalogFolder) {
    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CatalogFolder.class)
        .add(Restrictions.eq("CatalogFolder", catalogFolder))
        .setFlushMode(FlushMode.MANUAL));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(CatalogFolder entity) {
    entity.setGroupCode(StringUtils.EMPTY_STRING);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, CatalogFolder entity) {
    context.addRule(new MandatoryStringAttribute(entity, "FName"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
   */
  @Override
  public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
    boolean result = false;
    for (Integer key : primaryKeys) {
      CatalogFolder entity = load(key);
      CatalogFolder targetFolder = (CatalogFolder) targetEntity;
      //не копируем корневую папку и папку на саму себя, идентификатор должен быть больше идентификатора приемника, в противном
      //случае невозможно построить дерево иерархии
      if (entity.getCatalogFolder() != null && entity.getId() > targetFolder.getId() && entity.getCatalogFolder().getId() != targetFolder.getId()) {
        entity.setCatalogFolder(targetFolder);
        result = true;
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.CatalogFolderServiceLocal#getNestedCatalogFolders(com.mg.merp.reference.model.CatalogFolder, boolean, boolean)
   */
  public List<CatalogFolder> getNestedCatalogFolders(CatalogFolder catalogFolder, boolean isRecurseSearch, boolean isIncludeRootFolder) {
    return doGetNestedCatalogFolders(catalogFolder, isRecurseSearch, isIncludeRootFolder);
  }

  /**
   * Получть список вложенных папок каталога для заданной папки каталога
   *
   * @param catalogFolder       - папка каталога
   * @param isRecurseSearch     - признак "рекурсивный поиск вложенных папок каталога"
   * @param isIncludeRootFolder - признак "включать указанную папку каталога в список"
   * @return список вложенных папок каталога для заданной папки каталога
   */
  protected List<CatalogFolder> doGetNestedCatalogFolders(CatalogFolder catalogFolder, boolean isRecurseSearch, boolean isIncludeRootFolder) {
    List<CatalogFolder> nestedFolders = new ArrayList<CatalogFolder>();
    if (isIncludeRootFolder)
      nestedFolders.add(catalogFolder);

    if (isRecurseSearch)
      recurseSearchChildCatalogFolders(catalogFolder, nestedFolders);
    else
      nestedFolders.addAll(getChildCatalogFolders(catalogFolder));

    return nestedFolders;
  }

  /**
   * Выполнить рекурсивный поиск дочерних папок каталога
   *
   * @param catalogFolder - папка каталога
   * @param nestedFolders - вложенные папки
   */
  private void recurseSearchChildCatalogFolders(CatalogFolder catalogFolder, List<CatalogFolder> nestedFolders) {
    if (catalogFolder == null)
      return;
    List<CatalogFolder> childFolders = getChildCatalogFolders(catalogFolder);
    if (!childFolders.isEmpty())
      for (CatalogFolder childFolder : childFolders) {
        nestedFolders.add(childFolder);
        recurseSearchChildCatalogFolders(childFolder, nestedFolders);
      }
  }

}
