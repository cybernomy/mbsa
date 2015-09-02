/* StandartBrowserView.java
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

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.mg.merp.wb.core.ui.UiPlugin;
import com.mg.merp.wb.core.ui.widgets.MemoryCombo;

/**
 * Базовый класс для создания браузеров БК.Вид
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartBrowserView.java,v 1.6 2007/07/11 06:05:09 poroxnenko Exp $
 */
public abstract class StandartBrowserView<C extends StandartBrowserViewController>
		extends ViewPart {
	
	protected Table tblItems = null;

	/**
	 * Фильтр кодов для получения отчётов от сервера приложений
	 */
	protected MemoryCombo comboFilter = null;

	private TableViewer tableViewer = null;
	
	protected Menu menu = null;

	/**
	 * Контроллер текущего вида
	 */
	protected C viewController;

	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		top.setLayout(new FillLayout());
		createViewForm(top);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	private ViewForm createViewForm(Composite parent) {
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		createTable(viewForm);
		viewForm.setContent(tblItems);
		viewForm.setTopRight(createOKButton(viewForm));
		viewForm.setTopLeft(createFilterInput(viewForm));
		return viewForm;
	}

	private Button createOKButton(ViewForm viewForm) {
		Button buttonOk = new Button(viewForm, SWT.NONE);
		buttonOk.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_TOOL_FORWARD));
		buttonOk
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						refreshAndStoreFilter();
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		return buttonOk;
	}

	private void createTable(ViewForm viewForm) {
		tblItems = new Table(viewForm, SWT.FULL_SELECTION | SWT.MULTI);
		tblItems.setHeaderVisible(true);
		tblItems.setLinesVisible(true);

		tableViewer = new TableViewer(tblItems);

		menu = new Menu(tblItems);
		
		MenuItem miAdd = new MenuItem(menu, SWT.NONE);
		miAdd.setText(String.format(UiPlugin.getDefault().getString(
				UiPlugin.MENU_ADD), ""));

		miAdd.setImage(UiPlugin.getImageDescriptor(UiPlugin.ADD_ICO)
				.createImage());
		miAdd.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(SelectionEvent e) {
				viewController.doOnAddMenuAction();
			}

		});

		MenuItem miEdit = new MenuItem(menu, SWT.NONE);
		miEdit.setText(String.format(UiPlugin.getDefault().getString(
				UiPlugin.MENU_EDIT), ""));
		miEdit.setImage(UiPlugin.getImageDescriptor(UiPlugin.EDIT_ICO)
				.createImage());
		miEdit.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				if (viewController.getCurrentSelectedItem() != null)
					viewController.doOnEditMenuAction(viewController.getCurrentSelectedItem());
			}
		});

		MenuItem miDel = new MenuItem(menu, SWT.NONE);
		miDel.setText(String.format(UiPlugin.getDefault().getString(
				UiPlugin.MENU_DEL), ""));
		miDel.setImage(UiPlugin.getImageDescriptor(UiPlugin.DEL_ICO)
				.createImage());
		miDel.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				viewController.doOnDelMenuAction(viewController.getSelectedItems());
			}
		});

		menu.addMenuListener(new MenuListener() {

			public void menuHidden(MenuEvent e) {
			}

			public void menuShown(MenuEvent e) {
				Menu mn = (Menu) e.getSource();
				for (int i = 0; i < mn.getItemCount(); i++)
					mn.getItem(i).setEnabled(viewController.isSynchronized());
			}

		});
		addTableColumns(tblItems);
		
		tblItems.setMenu(menu);
		
		tableViewer.setContentProvider(viewController);
		tableViewer.setLabelProvider(viewController);
	}

	private Composite createFilterInput(ViewForm viewForm) {
		Composite filterComposite = new Composite(viewForm, SWT.NONE);
		FillLayout fL = new FillLayout();
		fL.marginHeight = 4;
		filterComposite.setLayout(fL);
		comboFilter = new MemoryCombo(10, filterComposite, SWT.BORDER);
		comboFilter.addKeyListener(new org.eclipse.swt.events.KeyListener() {
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				if (e.keyCode == 13) {
					refreshAndStoreFilter();
				}
			}

			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
			}
		});
		String[] items = viewController.loadFilter();
		if (items != null && items.length > 0)
			comboFilter.setItems(items);

		return filterComposite;
	}

	public C getViewController() {
		return viewController;
	}

	public abstract void addTableColumns(Table table);

	private void refreshAndStoreFilter() {
		comboFilter.add(comboFilter.getText());
		viewController.storeFilter(comboFilter.getItems());
		viewController.refreshView(comboFilter.getText());
	}
	
	public void refresh() {
		viewController.refreshView(comboFilter.getText());
	}

	public Table getTblItems() {
		return tblItems;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
