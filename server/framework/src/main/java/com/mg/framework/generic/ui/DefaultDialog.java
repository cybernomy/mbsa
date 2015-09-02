/*
 * DefaultDialog.java
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
package com.mg.framework.generic.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.DialogForm;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Button;

/**
 * ����� ������������ "�������", ������ ���������. ��� ������� ���������� ������� ����� <code>execute</code>.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultDialog.java,v 1.7 2008/01/10 08:54:43 safonov Exp $
 */
public class DefaultDialog extends AbstractForm implements DialogForm {
	/**
	 * ������ ���������� �� ������� "��"
	 */
	private List<FormActionListener> okActionListener = new ArrayList<FormActionListener>();
	/**
	 * ������ ���������� �� ������� "������"
	 */
	private List<FormActionListener> cancelActionListener = new ArrayList<FormActionListener>();

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnActionClose(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	protected void doOnActionClose(WidgetEvent event) {
		fireCancelAction(new FormEvent(this));
		super.doOnActionClose(event);
	}

	protected String getDefaultButtonName() {
		return "OkButton";
	}
	
	/**
	 * ���������� ������� "��"
	 * 
	 * @param event	�������
	 */
	public void onActionOk(WidgetEvent event) {
		fireOkAction(new FormEvent(this));
		close();
	}

	/**
	 * ���������� ������� "������"
	 * 
	 * @param event	�������
	 */
	public void onActionCancel(WidgetEvent event) {
		fireCancelAction(new FormEvent(this));
		close();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#addOkActionListener(com.mg.framework.api.ui.FormActionListener)
	 */
	public void addOkActionListener(FormActionListener listener) {
		okActionListener.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#getOkActionListenerList()
	 */
	public FormActionListener[] getOkActionListenerList() {
		return okActionListener.toArray(new FormActionListener[0]);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#removeOkActionListener(com.mg.framework.api.ui.FormActionListener)
	 */
	public void removeOkActionListener(FormActionListener listener) {
		okActionListener.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#addCancelActionListener(com.mg.framework.api.ui.FormActionListener)
	 */
	public void addCancelActionListener(FormActionListener listener) {
		cancelActionListener.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#getCancelActionListenerList()
	 */
	public FormActionListener[] getCancelActionListenerList() {
		return cancelActionListener.toArray(new FormActionListener[0]);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#removeCancelActionListener(com.mg.framework.api.ui.FormActionListener)
	 */
	public void removeCancelActionListener(FormActionListener listener) {
		cancelActionListener.remove(listener);
	}
	
	/**
	 * �������� ������� � ������� "��"
	 * 
	 * @param event	�������
	 * @throws ApplicationException
	 */
	public void fireOkAction(FormEvent event) throws ApplicationException {
		for (FormActionListener listener : okActionListener)
			listener.actionPerformed(event);
	}

	/**
	 * �������� ������� � ������� "������"
	 * 
	 * @param event	�������
	 * @throws ApplicationException
	 */
	public void fireCancelAction(FormEvent event) throws ApplicationException {
		for (FormActionListener listener : cancelActionListener)
			listener.actionPerformed(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.DialogForm#execute()
	 */
	public void execute() {
		super.run(true);//������� ������ ���������
		Button okButton = (Button) view.getWidget(getDefaultButtonName());
		view.getWindow().setDefaultButton(okButton);
	}

}
