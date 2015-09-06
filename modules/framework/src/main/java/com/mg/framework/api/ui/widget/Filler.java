/*
 * Filler.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Заполнитель"
 *
 * @author Oleg V. Safonov
 * @version $Id: Filler.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface Filler extends Widget {

  /**
   * атрибут тип заполнителя, может иметь следующие значения </br>glue </br>horizontalGlue
   * </br>verticalGlue </br>verticalStrut </br>horizontalStrut </br>rigidArea
   *
   * @see #GLUE
   * @see #HORIZONTAL_GLUE
   * @see #VERTICAL_GLUE
   * @see #VERTICAL_STRUT
   * @see #HORIZONTAL_STRUT
   * @see #RIGID_AREA
   */
  final static String FILLER_TYPE = "type";

  /**
   * растягивается по вертикали и горизонтали занимая все свободное пространство
   */
  final static String GLUE = "glue";

  /**
   * растягивается по горизонтали занимая все свободное пространство
   */
  final static String HORIZONTAL_GLUE = "horizontalGlue";

  /**
   * растягивается по вертикали занимая все свободное пространство
   */
  final static String VERTICAL_GLUE = "verticalGlue";

  /**
   * вертикальная распорка, размер распорки устанавливается через атрибут {@link #HEIGHT}
   */
  final static String VERTICAL_STRUT = "verticalStrut";

  /**
   * горизонтальная распорка, размер распорки устанавливается через атрибут {@link #WIDTH}
   */
  final static String HORIZONTAL_STRUT = "horizontalStrut";

  /**
   * распорка по горизонтали и вертикали, размер распорки устанавливается через атрибуты {@link
   * #HEIGHT} и {@link #WIDTH}
   */
  final static String RIGID_AREA = "rigidArea";

  /**
   * атрибут размера распорки по горизонтали, имеет тип <code>Integer</code>
   */
  final static String WIDTH = "width";

  /**
   * атрибут размера распорки по вертикали, имеет тип <code>Integer</code>
   */
  final static String HEIGHT = "height";
}
