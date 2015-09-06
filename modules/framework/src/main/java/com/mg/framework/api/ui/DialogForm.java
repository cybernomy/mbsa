/*
 * DialogForm.java
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

/**
 * Стандартная форма "Диалог"
 *
 * @author Oleg V. Safonov
 * @version $Id: DialogForm.java,v 1.1 2006/10/19 09:32:42 safonov Exp $
 */
public interface DialogForm extends Form {

  /**
   * возвращает список слушателей на событие "ok"
   *
   * @return список слушателей
   */
  FormActionListener[] getOkActionListenerList();

  /**
   * зарегистировать слушатель на событие "ок"
   *
   * @param listener слушатель
   */
  void addOkActionListener(FormActionListener listener);

  /**
   * удалить слушателя на событие "ок"
   *
   * @param listener слушатель
   */
  void removeOkActionListener(FormActionListener listener);


  /**
   * возвращает список слушателей на событие "отмена"
   *
   * @return список слушателей
   */
  FormActionListener[] getCancelActionListenerList();

  /**
   * зарегистировать слушатель на событие "отмена"
   *
   * @param listener слушатель
   */
  void addCancelActionListener(FormActionListener listener);

  /**
   * удалить слушателя на событие "отмена"
   *
   * @param listener слушатель
   */
  void removeCancelActionListener(FormActionListener listener);

  /**
   * запуск диалога (показ)
   */
  void execute();

}
