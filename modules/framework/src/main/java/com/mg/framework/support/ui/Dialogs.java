/*
 * Dialogs.java
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
package com.mg.framework.support.ui;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;

/**
 * Класс помощник диалогов для запроса значений  
 * 
 * @author Oleg V. Safonov
 * @version $Id: Dialogs.java,v 1.5 2009/01/14 13:14:52 safonov Exp $
 */
public class Dialogs {

	/**
	 * Слушатель событий диалога для запроса одиночного параметра
	 */
	public interface InputQueryDialogListener<T> {
		/**
		 * ввод значения параметра выполнен
		 * 
		 * @param value	значение параметра
		 */
		public void inputPerformed(T value);
		
		/**
		 * ввод отменен
		 */
		public void inputCanceled();
	}
	
	/**
	 * ввод одиночного параметра
	 * 
	 * @param <T>				тип параметра
	 * @param title				заголовок диалога
	 * @param prompt			подсказка
	 * @param value				значение, не может быть <code>null</code>
	 * @param actionListener	слушатель событий
	 */
	@SuppressWarnings("unchecked")
	public static <T> void inputQuery(final String title, final String prompt, T value,
			final InputQueryDialogListener<T> actionListener) {
		final InputQueryDialog<T> dialog = (InputQueryDialog<T>) UIProducer.produceForm("com/mg/framework/resources/InputQueryDialog.mfd.xml");
		dialog.addOkActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
				if (actionListener != null)
					actionListener.inputPerformed(dialog.getValue());
			}
		});
		dialog.addCancelActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {
				if (actionListener != null)
					actionListener.inputCanceled();
			}
		});
		dialog.setTitle(title);
		dialog.setPrompt(prompt);
		dialog.setValue(value);
		dialog.execute();
	}

}
