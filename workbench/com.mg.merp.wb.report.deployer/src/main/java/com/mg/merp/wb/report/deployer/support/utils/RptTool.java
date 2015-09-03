/* RptTool.java
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
package com.mg.merp.wb.report.deployer.support.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.dialogs.Dialogs;
import com.mg.merp.wb.core.ui.dialogs.ProjectSelectionDialog;
import com.mg.merp.wb.report.deployer.DeployerPlugin;
import com.mg.merp.wb.report.deployer.support.editor.RptEditorForm;
import com.mg.merp.wb.report.deployer.support.editor.RptEditorInput;
import com.mg.merp.wb.report.deployer.ui.RptView;

/**
 * Вспомогательный класс для управления созданием, изменением, удалением отчётов
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: RptTool.java,v 1.4 2007/11/04 14:28:14 safonov Exp $
 */
public class RptTool {

	public static final String REPORT_DESIGN_EXT = ".rptdesign";
	
	private static final String REPORT_DELETE_ERROR = "tmpl.menu.del.error";
	
	private static final String REPORT_DEPLOY_ERROR = "tmpl.deploy.error";
	
	/**
	 * Создание нового отчёта в базе
	 * 
	 * @param templ
	 *            шаблон
	 */
	public static void addReport(IFile templ) {
		String code = getCode(templ);
		RptMainTransfer rmt = new RptMainTransfer();
		rmt.code = code;
		rmt.classNames = "";
		rmt.secGroupsIds = new ArrayList<Integer>(0);
		rmt.secGroupsNames = new ArrayList<String>(0);
		DeployerPlugin.openEditor(new RptEditorInput(rmt, templ, true),RptEditorForm.EDITOR_ID);
	}

	/**
	 * Разворачивание шаблона в базе
	 * 
	 * @param templ
	 *            шаблон
	 */
	public static void persistTemplate(IFile templ) {
		if (Dialogs.showMessage(DeployerPlugin.getDefault().getString("tmpl.menu.deploy.question")
				, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION) == SWT.OK) {
			String code = getCode(templ);
			RptMainTransfer rmt = getReportFromRepository(code);
			if (rmt != null) {
				try {
					rmt.template = getData(templ);
					rmt = DeployerPlugin.getDefault().persistTemplate(rmt);
					if (rmt == null) {
						if (Dialogs.showMessage(DeployerPlugin.getDefault().getFormattedString("tmpl.menu.deploy.notexist", code),
								SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION) == SWT.OK)
							addReport(templ);
					} else if (rmt.code == null)
						Dialogs.showMessage(DeployerPlugin.getDefault().getString("tmpl.menu.deploy.error")
								, SWT.OK | SWT.ICON_ERROR);
					else {
						Dialogs.showMessage(DeployerPlugin.getDefault().getString("tmpl.menu.deploy.success")
								, SWT.OK | SWT.ICON_INFORMATION);
						//http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4450
						getRptView().getViewController().getReports().get(code).sysVersion = rmt.sysVersion;
					}
				} catch (java.rmi.ConnectException e) {
					Dialogs.openError(DeployerPlugin.getDefault().getString(REPORT_DEPLOY_ERROR)
							, DeployerPlugin.getDefault().getString(UiPlugin.CHECK_SERVER), DeployerPlugin.ID, e);
				} catch (RuntimeException e) {
					Dialogs.openError(DeployerPlugin.getDefault().getString(REPORT_DEPLOY_ERROR)
							, e.getLocalizedMessage(), DeployerPlugin.ID, e);
				} catch (Exception e) {
					Dialogs.openError(UiPlugin.getDefault().getString(UiPlugin.UNKNOWN_EXCEPTION)
							, UiPlugin.getDefault().getString(UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.ID, e);
				}
			}
		}
	}

	/**
	 * Удаление списка отчётов
	 * 
	 * @param templToDel
	 *            список отчётов
	 */
	public static void deleteReportList(List<IFile> templToDel) {
		if (Dialogs.showMessage(DeployerPlugin.getDefault().getFormattedString("tmpl.menu.del.question", templToDel.size())
				, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION) == SWT.OK) {
			List<RptMainTransfer> rpts = new ArrayList<RptMainTransfer>(templToDel.size());
			List<IFile> toDelete = new ArrayList<IFile>(templToDel.size());
			for (IFile fl : templToDel) {
				RptMainTransfer rmt = getReportFromRepository(getCode(fl));
				rpts.add(rmt);
				toDelete.add(fl);
			}
			try {
				Integer[] ids = new Integer[rpts.size()];
				for (int i = 0; i < rpts.size(); i++)
					ids[i] = rpts.get(i).id;
				DeployerPlugin.getDefault().deleteBusinessObjectsList(ids);
				for (IFile fl : toDelete)
					fl.delete(true, false, null);
				Dialogs.showMessage(DeployerPlugin.getDefault().getString("tmpl.menu.del.success")
						, SWT.OK | SWT.ICON_INFORMATION);
				
			} catch (java.rmi.ConnectException e) {
				Dialogs.openError(DeployerPlugin.getDefault().getString(REPORT_DELETE_ERROR)
						, DeployerPlugin.getDefault().getString(UiPlugin.CHECK_SERVER), DeployerPlugin.ID, e);
			} catch (RuntimeException e) {
				Dialogs.openError(DeployerPlugin.getDefault().getString(REPORT_DELETE_ERROR)
						, e.getLocalizedMessage(), DeployerPlugin.ID, e);
			} catch (Exception e) {
				Dialogs.openError(UiPlugin.getDefault().getString(UiPlugin.UNKNOWN_EXCEPTION)
						, UiPlugin.getDefault().getString(UiPlugin.UNKNOWN_EXCEPTION), UiPlugin.ID, e);
			}
		}
	}

	/**
	 * Изменение отчёта в базе
	 * 
	 * @param templ
	 *            шаблон
	 */
	public static void editReport(IFile templ) {
		String code = getCode(templ);
		DeployerPlugin.openEditor(new RptEditorInput(getReportFromRepository(code), templ, false), RptEditorForm.EDITOR_ID);
	}

	/**
	 * Возвращает код отчёта, который представляет из себя имя файла шаблона
	 * отчёта
	 * 
	 * @param templ
	 *            шаблон
	 * @return код отчёта
	 */
	public static String getCode(IFile templ) {
		return templ.getName().substring(0,	templ.getName().length() - templ.getFileExtension().length() - 1);
	}

	/**
	 * Возвращает бинарное представление файла шаблона
	 * 
	 * @param templ
	 *            шаблон
	 * @return бинарное представление шаблона
	 */
	public static byte[] getData(IFile templ) {
		byte[] buf = null;
		try {
			File f = templ.getLocation().toFile();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			buf = new byte[(int) f.length()];
			bis.read(buf);
		} catch (Exception e) {
			return null;
		}
		return buf;
	}
	
	/**
	 * Получить отчёт по коду из репозитория
	 * 
	 * @param code
	 *            код
	 * @return отчёт или NULL
	 */
	public static RptMainTransfer getReportFromRepository(String code) {
		return getRptView().getViewController().getReports().get(code);
	}

	/**
	 * Получить панель RptView
	 * 
	 * @return панель RptView
	 */
	public static RptView getRptView() {
		return (RptView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView(RptView.ID);
	}

	/**
	 * Выбор проекта для шаблона отчёта
	 * 
	 * @return ресурс-проект
	 */
	public static IResource selectResourceForTemplate() {
		ProjectSelectionDialog psd = new ProjectSelectionDialog(CoreUtils.getMainShell()
				, DeployerPlugin.getDefault().getString("tmpl.select.project.title"));
		IProject pr = null;
		if (psd.open() == Window.OK)
			pr = (IProject) psd.getResult()[0];
		return pr;
	}
}
