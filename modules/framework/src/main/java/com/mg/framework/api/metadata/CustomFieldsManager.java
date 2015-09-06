/*
 * CustomFieldsManager.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;

import java.util.Map;


/**
 * Менеджер управления пользовательскими полями
 *
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManager.java,v 1.3 2008/12/23 09:09:50 safonov Exp $
 */
public interface CustomFieldsManager {
  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:service=CustomFieldsManagerService";

  /**
   * имя стандартного динамического макроса для отображения пользовательских полей в формах
   * поддержки
   */
  final static String CUSTOM_FIELDS_AREA_MACROS = "com.mg.framework.CustomFieldsMacros";

  /**
   * суффикс наименования пользовательского атрибута
   */
  final static String CUSTOM_FIELD_NAME_PREFIX = "FEAT$";

  /**
   * делитель отделяющий индекс свойства типа массив
   */
  final static String INDEX_DELIMITER = "$";

  /**
   * загрузка метаданных пользовательских полей
   *
   * @param service бизнес-компонент
   * @return метаданные пользовательских полей
   */
  FieldMetadata[] loadFieldsMetadata(DataBusinessObjectService<?, ?> service);

  /**
   * генерация тела макроса для отображения пользовательских полей в формах поддержки
   *
   * @param service бизнес-компонент
   * @return тело макроса
   */
  String generateMaintenanceArea(DataBusinessObjectService<?, ?> service);

  /**
   * сохранения значений пользовательских полей
   *
   * @param fieldsValues значения
   * @param service      бизнес-компонент
   * @param key          идентификатор сущности
   */
  void storeValues(Map<String, Object> fieldsValues, DataBusinessObjectService<?, ?> service, Object key);

  /**
   * загрузка значений пользовательских полей
   *
   * @param service бизнес-компонент
   * @param key     идентификатор сущности
   * @return значения
   */
  Map<String, Object> loadValues(DataBusinessObjectService<?, ?> service, Object key);

  /**
   * создание репозитария для хранения пользовательских полей
   *
   * @param service бизнес-компонент
   * @param key     идентификатор сущности
   * @return репозитарий
   */
  EntityCustomFieldsStorage createStorage(DataBusinessObjectService<?, ?> service, Object key);

  /**
   * сохранения значений пользовательских полей
   *
   * @param storage репозитарий
   * @param service бизнес-компонент
   * @param key     идентификатор сущности
   */
  void storeValues(EntityCustomFieldsStorage storage, DataBusinessObjectService<?, ?> service, Object key);

  /**
   * удалений пользовательских полей
   *
   * @param service бизнес-компонент
   * @param key     идентификатор сущности
   */
  void removeValues(DataBusinessObjectService<?, ?> service, Object key);

  /**
   * копирование пользовательских полей
   *
   * @param serviceSrc бизнес-компонент источник
   * @param entitySrc  сущность источник
   * @param serviceDst бизнес-компонент приемник
   * @param entityDst  сущность приемник
   */
  void cloneValues(DataBusinessObjectService<?, ?> serviceSrc, PersistentObject entitySrc,
                   DataBusinessObjectService<?, ?> serviceDst, PersistentObject entityDst);

}
