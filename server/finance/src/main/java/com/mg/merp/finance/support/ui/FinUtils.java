/*
 * FinUtils.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.support.Messages;

import java.text.MessageFormat;

/**
 * @author leonova
 * @version $Id: FinUtils.java,v 1.2 2009/02/16 07:46:40 sharapov Exp $
 */
public class FinUtils {

  /**
   * Возвращает имя БК
   *
   * @param classAnl - id класса
   */
  public static String getBeanName(SysClass classAnl) {
    PersistentManager pm = ServerUtils.getPersistentManager();
    return pm.contains(classAnl) ? classAnl.getBeanName() : pm.find(SysClass.class, classAnl.getId()).getBeanName();
  }

  /**
   * Возвращает значение DataItem
   *
   * @param beanName - имя БК
   * @param idAnl    - id записи
   */
  @SuppressWarnings("unchecked")
  public static String getAnlName(String beanName, Integer idAnl) {
    ApplicationDictionary applicationDictionary = ApplicationDictionaryLocator.locate();
    DataBusinessObjectService<?, Integer> service = (DataBusinessObjectService<?, Integer>) applicationDictionary.getBusinessService(beanName);
    FieldMetadata metadata = applicationDictionary.getFieldMetadata(ReflectionUtils.getClassReflectionMetadata(service.getEntityClass()));
    //не знаем как отобразить, просто возвращаем ID
    if (metadata == null)
      return idAnl.toString();
    PersistentObject entity = service.load(idAnl);
    //return entity == null ? "<unknown>: ".concat(idAnl.toString()) : MiscUtils.getPersistentObjectTextRepresentation(entity, metadata);
    return entity == null ? MessageFormat.format(Messages.getInstance().getMessage(Messages.ENTITY_NOT_EXISTS), service.getBusinessServiceMetadata().getName(), idAnl.toString()) : MiscUtils.getPersistentObjectTextRepresentation(entity, metadata);
  }
}
