/* BusinessObjectPlugin.java
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
package com.mg.merp.wb.core.ui.plugin;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.mg.merp.wb.core.ui.editor.StandartEditorForm;
import com.mg.merp.wb.core.ui.editor.StandartEditorInput;
import com.mg.merp.wb.core.ui.view.StandartBrowserView;

/**
 * �����-������ ��� �������� ���������� ������ ���������<br>
 * T-����� ���������� ������ �������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessObjectPlugin.java,v 1.2 2007/05/07 13:05:06 poroxnenko Exp $
 */
public abstract class BusinessObjectPlugin<T> extends MerpUIPlugin {

	/**
	 * �������� ���������� ������� � ����
	 * 
	 * @param bo
	 *            �������
	 * @return ������ � ����� ��������� ������ ��� NULL, � ������ ������
	 * @throws Exception
	 */
	abstract public T addBusinessObject(T bo) throws Exception;

	/**
	 * �������� �������� �� ����
	 * 
	 * @param keys
	 *            ������ ������ ��������, ���������� ��������
	 * @return ��� ����������. 0 - �������. -1 - ������
	 * @throws Exception
	 */
	abstract public void deleteBusinessObjectsList(Integer[] ids)
			throws Exception;

	/**
	 * �������� ��������� �������
	 * 
	 * @param bo
	 *            ������
	 * @return ������ � ����� ��������� ������, NULL-� ������ ������
	 * @throws Exception
	 */
	abstract public T editBusinessObject(T bo) throws Exception;

	/**
	 * �������� ��������� ������ ��������
	 * 
	 * @param query
	 *            �����. ������ ���������� ��� ����������� "like" �
	 *            EJBQL(������� '*' ������������� ���������� �� '%', � '?' ��
	 *            '_')
	 * @return ������ �������� ��� NULL, � ������ ������
	 * @throws Exception
	 */
	abstract public T[] synchronize(String query) throws Exception;

	/**
	 * ��������� ����� �������������� ������-�������
	 * 
	 * @param input
	 *            ������ ������-�������
	 * @return ����� �������������� ������-�������
	 */
	public static <EI extends StandartEditorInput> StandartEditorForm openEditor(
			EI input, String editorId) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			try {
				return (StandartEditorForm) page.openEditor(input, editorId);
			} catch (PartInitException e) {
				return null;
			}
		}
		return null;
	}

	public StandartBrowserView getView() {
		return (StandartBrowserView) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().findView(getViewId());
	}

	/**
	 * 
	 * @return ID ���� ������-�������
	 */
	public abstract String getViewId();

}
