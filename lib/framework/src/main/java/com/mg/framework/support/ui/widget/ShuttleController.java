/*
 * ShuttleController.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;

import javax.swing.event.EventListenerList;

import com.mg.framework.api.ui.ShuttleChangeEvent;
import com.mg.framework.generic.ui.DefaultShuttleModel;

/**
 * Реализация адаптера элемента "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleController.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public class ShuttleController implements ShuttleControllerAdapter, Serializable {
	private EventListenerList listenerList = new EventListenerList();
	private ShuttleModel shuttleModel;

	public ShuttleController() {
		shuttleModel = new DefaultShuttleModel();
	}

	/**
	 * отправить событие о перемещении из списка источника в список приемник
	 * 
	 * @param contents	содержимое
	 */
	public void fireMove(ShuttleChangeEvent event) {
		for (ShuttleListener listener : listenerList.getListeners(ShuttleListener.class))
			listener.shuttleContentsMoved(event);
	}

	/**
	 * отправить событие о перемещении из списка приемника в список источник
	 * 
	 * @param contents	содержимое
	 */
	public void fireRemove(ShuttleChangeEvent event) {
		for (ShuttleListener listener : listenerList.getListeners(ShuttleListener.class))
			listener.shuttleContentsRemoved(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.ShuttleControllerAdapter#getModel()
	 */
	public ShuttleModel getModel() {
		return shuttleModel;
	}

	public void setModel(ShuttleModel model) {
		this.shuttleModel = model;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.ShuttleControllerAdapter#addShuttleListener(com.mg.framework.support.ui.widget.ShuttleListener)
	 */
	public void addShuttleListener(ShuttleListener listener) {
		listenerList.add(ShuttleListener.class, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.ShuttleControllerAdapter#removeShuttleListener(com.mg.framework.support.ui.widget.ShuttleListener)
	 */
	public void removeShuttleListener(ShuttleListener listener) {
		listenerList.remove(ShuttleListener.class, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.ShuttleControllerAdapter#move(java.lang.Object[])
	 */
	public void move(Object[] contents) {
		fireMove(new ShuttleChangeEvent(this, contents));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.ShuttleControllerAdapter#remove(java.lang.Object[])
	 */
	public void remove(Object[] contents) {
		fireRemove(new ShuttleChangeEvent(this, contents));
	}
}
