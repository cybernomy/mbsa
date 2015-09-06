/*
 * ChooseBrowseForm.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api.ui;

import com.mg.framework.api.orm.PersistentObject;


/**
 * Форма поиска сущностей
 *
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpForm.java,v 1.3 2009/02/09 12:59:03 safonov Exp $
 */
public interface SearchHelpForm extends Form {
  /**
   * наименование элемента кнопки выбора сущностей
   */
  static final String STANDART_CHOOSE_BUTTON = "сhooseButton";

  /**
   * зарегистрировать слушателя на событие поиска
   *
   * @param listener слушатель
   */
  void addSearchHelpListener(SearchHelpListener listener);

  /**
   * удалить зарегистрированный слушатель на событие поиска
   *
   * @param listener слушатель
   */
  void removeSearchHelpListener(SearchHelpListener listener);

  /**
   * получить список зарегистрированных слушателей на событие поиска
   *
   * @return массив слушателей, если нет зарегистрированных слушателей то пустой массив
   */
  SearchHelpListener[] getSearchHelpListeners();

  /**
   * установить сущность для позиционирования, например может использоваться механизмом поиска
   * сущностей для передачи уже установленного значения в форму поиска для правильного
   * позиционирования в UI
   *
   * @param entity сущность, может быть <code>null</code>
   */
  void setTargetEntity(PersistentObject entity);

}
