/* EntityTransformator.java
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
package com.mg.framework.api;

/**
 * Интерфейс преобразования сущностей S - класс объекта источника D - тип результата трансформации
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformer.java,v 1.3 2007/09/21 09:53:23 safonov Exp $
 */
public interface EntityTransformer {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:service=EntityTransformerService";

  /**
   * Преобразование сущности
   *
   * @param srcObj   Исходный объект
   * @param dstClass Класс, в который преобразуется srcObj
   * @param mapId    Идентификатор маппинга
   * @return Преобразованный класс
   */
  <S, D> D map(S srcObj, Class<D> dstClass, String mapId);

  /**
   * Преобразование сущности
   *
   * @param srcObj   Исходный объект
   * @param dstClass Класс, в который преобразуется srcObj
   * @return Преобразованный класс
   */
  <S, D> D map(S srcObj, Class<D> dstClass);

  /**
   * Преобразование сущности
   *
   * @param srcObj Исходный объект
   * @param dstObj Объект-приёмник
   * @param mapId  Идентификатор маппинга
   */
  <S, D> void map(S srcObj, D dstObj, String mapId);

  /**
   * Преобразование сущности
   *
   * @param srcObj Исходный объект
   * @param dstObj Объект-приёмник
   */
  <S, D> void map(S srcObj, D dstObj);

  /**
   * Обновление правил разметки преобразования сущностей
   */
  void rebuildMapping();

}
