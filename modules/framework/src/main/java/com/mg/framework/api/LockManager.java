/*
 * LockManager.java
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
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Менеджер управления блокировками объектов, используется пессимистический механизм блокировок
 *
 * @author Oleg V. Safonov
 * @version $Id: LockManager.java,v 1.1 2006/12/15 09:26:17 safonov Exp $
 */
public interface LockManager {
  /**
   * блокировка объекта
   *
   * @param entity объект
   * @throws LockingException если объект был заблокирован конкурирующей транзакцией
   */
  void lock(PersistentObject entity) throws LockingException;

  /**
   * проверка блокировки объекта
   *
   * @param entity объект
   * @return <code>true</code> если объект удачно заблокирован, в противном случае
   * <code>false</code>
   */
  boolean tryLock(PersistentObject entity);

  /**
   * снять блокировку объекта
   *
   * @param entity объект
   */
  void unlock(PersistentObject entity);

}
