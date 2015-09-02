/*
 * Alert.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

/**
 * Alert
 * 
 * @author Oleg V. Safonov
 * @version $Id: Alert.java,v 1.1 2006/06/27 12:05:56 safonov Exp $
 */
public interface Alert {
	
	/**
	 * Message type
	 * 
	 */
	enum MessageType {
		
		/**
		 * No icon is used.
		 */
		PLAIN_MESSAGE,
		
		/**
		 * Used for error messages.
		 */
		ERROR_MESSAGE,
		
		/**
		 * Used for information messages.
		 */
		INFORMATION_MESSAGE,
		
		/**
		 * Used for warning messages.
		 */
		WARNING_MESSAGE,
		
		/**
		 * Used for questions.
		 */
		QUESTION_MESSAGE
	}
	
	/**
	 * displays an alert message and a choice of one, two, or three buttons
	 * 
	 * @param messageType		the message type of this alert
	 * @param title				the title of the alert
	 * @param message			the message of the alert
	 * @param firstButtonLabel	the label of the first button
	 * @param secondButtonLabel	the label of the second button
	 * @param thirdButtonLabel	the label of the third button
	 */
	void show(MessageType messageType, String title, String message, String firstButtonLabel, String secondButtonLabel, String thirdButtonLabel);

	/**
	 * Registers the specified window listener with the alert window. If l is null,
	 * nothing happens.
	 * 
	 * @param listener	the window listener
	 */
	void addAlertListener(AlertListener listener);
	
	/**
	 * Removes the specified window listener so that it no longer receives window
	 * events from this window. If l is null, no exception is thrown and no action
	 * is performed.
	 * 
	 * @param listener	the window listener
	 */
	void removeAlertListener(AlertListener listener);
}
