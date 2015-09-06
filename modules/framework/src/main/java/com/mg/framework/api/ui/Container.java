/*
 * Container.java
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
package com.mg.framework.api.ui;

import org.dom4j.Element;

/**
 * Контейнер содержащий другие элементы пользовательского интерфейса
 *
 * @author Oleg V. Safonov
 * @version $Id: Container.java,v 1.2 2006/11/21 15:31:23 safonov Exp $
 */
public interface Container extends Widget {

  /**
   * атрибут типа менеджера расположения
   */
  final static String LAYOUT = "layout";

  /**
   * начало обработки контейнера, необходимо вызвать перед добавлением элементов
   */
  public void startContainer();

  /**
   * завершение обработки контейнера, необходимо вызвать после добавлениея всех элементов
   */
  public void endContainer();

  /**
   * вставка элемента пользовательского интерфейса в контейнер
   */
  public void add(Widget widget, Widget widgetLabel, Element element);
}
