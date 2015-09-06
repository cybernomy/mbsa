/* AbstractConverter.java
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
package com.mg.framework.generic;

import net.sf.dozer.util.mapping.converters.CustomConverter;

/**
 * Класс-предок для классов-настраиваемых преобразователей одной сущности в другую
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: AbstractConverter.java,v 1.3 2007/09/24 15:49:52 safonov Exp $
 */
public abstract class AbstractConverter implements CustomConverter {

  /**
   * реализация конвертации объектов, должен быть реализован механизм двунаправленной трансформации
   * (destination --> source и source --> destination)
   *
   * @param destination объект приемник
   * @param source      объект источник
   * @param destClass   класс объекта приемника
   * @param sourceClass класс объекта источника
   * @return объект полученный в ходе конвертации
   */
  abstract protected Object doConvert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass);

  /**
   * конвертация одного объекта в другой и обратно
   *
   * @param destination Объект А
   * @param source      Объект В
   * @param destClass   Класс объекта А
   * @param sourceClass Класс объекта В
   * @return Трансформированный объект
   */
  @SuppressWarnings("unchecked")
  public final Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
    return doConvert(destination, source, destClass, sourceClass);
  }

}
