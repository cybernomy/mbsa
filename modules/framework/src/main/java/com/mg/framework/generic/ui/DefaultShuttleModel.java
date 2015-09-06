/*
 * DefaultShuttleModel.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.support.ui.widget.ShuttleModel;
import com.mg.framework.support.ui.widget.ShuttleModelListener;

import java.io.Serializable;

import javax.swing.event.EventListenerList;

/**
 * Стандартная реализация модели элемента "Shuttle"
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultShuttleModel.java,v 1.1 2006/08/31 08:53:48 safonov Exp $
 */
public class DefaultShuttleModel implements ShuttleModel, Serializable {
  private Object[] leadingList;
  private Object[] trailingList;
  private EventListenerList listenerList = new EventListenerList();

  /**
   * отправить сообщение о смене списка источника
   *
   * @param event событие
   */
  public void fireLeadingChanged(ShuttleChangeEvent event) {
    for (ShuttleModelListener listener : listenerList.getListeners(ShuttleModelListener.class))
      listener.leadingChanged(event);
  }

  /**
   * отправить сообщение о смене списка приемника
   *
   * @param event событие
   */
  public void fireTrailingChanged(ShuttleChangeEvent event) {
    for (ShuttleModelListener listener : listenerList.getListeners(ShuttleModelListener.class))
      listener.trailingChanged(event);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#addShuttleModelListener(com.mg.framework.support.ui.widget.ShuttleModelListener)
   */
  public void addShuttleModelListener(ShuttleModelListener listener) {
    listenerList.add(ShuttleModelListener.class, listener);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#removeShuttleModelListener(com.mg.framework.support.ui.widget.ShuttleModelListener)
   */
  public void removeShuttleModelListener(ShuttleModelListener listener) {
    listenerList.remove(ShuttleModelListener.class, listener);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#getLeadingList()
   */
  public Object[] getLeadingList() {
    return leadingList;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#setLeadingList(java.util.List)
   */
  public void setLeadingList(Object[] list) {
    leadingList = list;
    fireLeadingChanged(new ShuttleChangeEvent(this, leadingList));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#getTrailingList()
   */
  public Object[] getTrailingList() {
    return trailingList;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.ShuttleModel#setTrailingList(java.util.List)
   */
  public void setTrailingList(Object[] list) {
    trailingList = list;
    fireTrailingChanged(new ShuttleChangeEvent(this, trailingList));
  }

}