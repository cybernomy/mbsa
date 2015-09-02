/* StandartBrowserController.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.core.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;

/**
 * ������� ����� ��� �������� ��������� ��.����������
 * 
 * V - ����� ������������ ����.<br>
 * I - ����� �������� ���������� �������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartBrowserViewController.java,v 1.1 2006/12/29 15:16:33
 *          poroxnenko Exp $
 */
public abstract class StandartBrowserViewController<V extends StandartBrowserView, I>
		implements ITableLabelProvider, IStructuredContentProvider {

	/**
	 * ���� ������� ������������� ������ � ������� ����������. ����
	 * ������������� �����������, �� ������� �������� �� �������� � ���������
	 */
	protected boolean isSynchronized = false;

	/**
	 * ���, ����������� ������ ������������
	 */
	protected V view;

	public StandartBrowserViewController(V view) {
		this.view = view;
		this.isSynchronized = false;
	}

	public boolean isSynchronized() {
		return isSynchronized;
	}
	
	@SuppressWarnings("unchecked")
	public I getCurrentSelectedItem(){
		TableItem[] ti = view.tblItems.getSelection();
		if (ti != null && ti.length >= 0)
			return (I)(view.tblItems.getSelection()[0].getData());
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	List<I> getSelectedItems(){
		TableItem[] tia = view.tblItems.getSelection();
		if (tia != null && tia.length >= 0){
			ArrayList<I> objs = new ArrayList<I>(tia.length);
			for(TableItem ti: tia)
				objs.add((I)ti.getData());
			return objs;
		}
		else
			return null;
	}

	public abstract void refreshView(String query);

	public abstract Image getColumnImage(Object element, int columnIndex);

	public abstract String getColumnText(Object element, int columnIndex);

	public abstract void addListener(ILabelProviderListener listener);

	public abstract void dispose();

	public abstract boolean isLabelProperty(Object element, String property);

	public abstract void removeListener(ILabelProviderListener listener);

	public abstract Object[] getElements(Object inputElement);

	public abstract void abstractinputChanged(Viewer viewer, Object oldInput,
			Object newInput);

	public abstract void storeFilter(String[] array);

	public abstract String[] loadFilter();

	/**
	 * ���������� ������� ������ ������ ���� "��������"
	 */
	public abstract void doOnAddMenuAction();

	/**
	 * ���������� ������� ������ ������ ���� "��������"
	 * 
	 * @param data
	 * 			���������� ��
	 */
	public abstract void doOnEditMenuAction(I data);

	/**
	 * ���������� ������� ������ ������ ���� "�������"
	 * 
	 * @param objects
	 * 			������ ��, ���������� ��������
	 */
	public abstract void doOnDelMenuAction(List<I> objects);
}
