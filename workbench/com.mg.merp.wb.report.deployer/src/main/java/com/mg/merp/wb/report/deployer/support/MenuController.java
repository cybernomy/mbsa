/* MenuController.java
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
package com.mg.merp.wb.report.deployer.support;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.wb.report.deployer.support.utils.RptTool;

/**
 * Контроллер контекстного меню шаблона отчёта
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: MenuController.java,v 1.6 2007/04/11 07:01:54 poroxnenko Exp $
 */
public class MenuController implements IObjectActionDelegate {

	/**
	 * Флаг, показывает факт синхронизации файла шаблона с репозитарием отчётов
	 */
	private static boolean isDeployEnabled;

	/**
	 * Файл шаблона
	 */
	private IFile template;

	/**
	 * Список файлов шаблонов, подлежащих удалению
	 */
	private List<IFile> templToDel;

	private static final String MENU_ID_DEPLOY = "com.mg.merp.wb.report.deployer.menu.Deploy";

	private static final String MENU_ID_ADD = "com.mg.merp.wb.report.deployer.menu.Add";

	private static final String MENU_ID_DEL = "com.mg.merp.wb.report.deployer.menu.Del";

	private static final String MENU_ID_EDIT = "com.mg.merp.wb.report.deployer.menu.Edit";

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (action.getId().equals(MENU_ID_DEPLOY)) {
			persistTemplate(template);
		} else if (action.getId().equals(MENU_ID_ADD))
			createNewReport(template);
		else if (action.getId().equals(MENU_ID_DEL))
			deleteReportList(templToDel);
		else if (action.getId().equals(MENU_ID_EDIT))
			editReport(template);
	}

	@SuppressWarnings("unchecked")
	public void selectionChanged(IAction action, ISelection selection) {
		if (!selection.isEmpty()
				&& RptTool.getRptView().getViewController().isSynchronized()) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			if (action.getId().equals(MENU_ID_DEL)) {
				templToDel = structured.toList();
				action.setEnabled(isAllSynchronized(templToDel));
			} else {
				template = (IFile) structured.getFirstElement();
				RptMainTransfer rmt = RptTool.getReportFromRepository(RptTool
						.getCode(template));
				isDeployEnabled = rmt != null;
				if (action.getId().equals(MENU_ID_DEPLOY)
						|| action.getId().equals(MENU_ID_EDIT))
					action.setEnabled(isDeployEnabled);
				if (action.getId().equals(MENU_ID_ADD))
					action.setEnabled(!isDeployEnabled);
			}
		} else
			action.setEnabled(false);
	}

	/**
	 * Проверка синхронизированности отчётов
	 * 
	 * @param list
	 *            список файлов отчётов, подлежащих проверке на
	 *            синхронизированность с репозитарием
	 * @return
	 */
	private boolean isAllSynchronized(List<IFile> list) {
		boolean result = false;
		if (list != null && !list.isEmpty()) {
			Iterator<IFile> it = list.listIterator();
			while (it.hasNext()
					&& (result = RptTool.getRptView().getViewController()
							.isContainCode(RptTool.getCode(it.next())))) {
			}
		}
		return result;
	}

	private void persistTemplate(IFile templ) {
		RptTool.persistTemplate(templ);
	}

	private void createNewReport(IFile templ) {
		RptTool.addReport(templ);
	}

	private void deleteReportList(List<IFile> templToDel) {
		RptTool.deleteReportList(templToDel);
	}

	private void editReport(IFile templ) {
		RptTool.editReport(templ);
	}
}
