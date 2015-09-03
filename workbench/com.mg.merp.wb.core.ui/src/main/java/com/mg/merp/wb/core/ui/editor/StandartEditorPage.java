/* StandartEditorPage.java
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
package com.mg.merp.wb.core.ui.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.mg.merp.wb.core.ui.UiPlugin;

/**
 * Содержание формы редактирования бизнес объекта
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartEditorPage.java,v 1.2 2007/07/11 06:00:05 poroxnenko Exp $
 */
public abstract class StandartEditorPage<T> extends FormPage {

	protected FormToolkit toolkit;

	protected ScrolledForm form;

	/**
	 * Форма редактирования
	 */
	protected FormEditor editor;

	protected static GridData commonGridData = new GridData();

	protected static GridLayout commonGridLayout = new GridLayout();
	static {
		commonGridData.horizontalAlignment = GridData.FILL;
		commonGridData.grabExcessHorizontalSpace = true;
		commonGridData.verticalAlignment = GridData.CENTER;

		commonGridLayout.marginWidth = 1;
		commonGridLayout.marginHeight = 1;
		commonGridLayout.numColumns = 3;
	}

	public static final String REQUIRED_FIELD_COLOR = "requiredFieldColor";

	public StandartEditorPage(FormEditor editor) {
		super(editor, "", "");
		this.editor = editor;
	}

	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		
		formContentPrecreating(((StandartEditorInput<T>)getEditorInput()).getData());
		
		form = managedForm.getForm();
		toolkit = managedForm.getToolkit();

		toolkit.getColors().createColor(REQUIRED_FIELD_COLOR,
				new RGB(255, 0, 0));

		form.setText(getEditorText());

		Composite editorComposite = form.getBody();
		editorComposite.setLayout(new GridLayout());
		editorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createEditorArea(editorComposite);
		createButtonSection(editorComposite);
	}

	private void createButtonSection(Composite parent) {
		Section section = toolkit.createSection(parent,
				ExpandableComposite.COMPACT);
		section.setLayout(new GridLayout());
		section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite sectionClient = toolkit.createComposite(section);
		section.setClient(sectionClient);

		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 1;
		gridLayout.marginWidth = 7;
		gridLayout.marginHeight = 1;
		gridLayout.horizontalSpacing = 1;
		gridLayout.numColumns = 3;

		sectionClient.setLayout(gridLayout);

		Button bSave = toolkit.createButton(sectionClient, UiPlugin
				.getDefault().getString(UiPlugin.BUTT_SAVE_TEXT), SWT.PUSH
				| SWT.CENTER);
		bSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doOnSaveClick();
			}
		});

		// 1 Blank column
		Label dummy = toolkit.createLabel(sectionClient, "");
		GridData dummyLabelDataLayout = new GridData(
				GridData.HORIZONTAL_ALIGN_CENTER);
		dummyLabelDataLayout.horizontalSpan = 1;
		dummyLabelDataLayout.widthHint = 30;
		dummy.setLayoutData(dummyLabelDataLayout);

		Button bCancel = toolkit.createButton(sectionClient, UiPlugin
				.getDefault().getString(UiPlugin.BUTT_CANCEL_TEXT), SWT.PUSH
				| SWT.CENTER);
		bCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.close(false);
			}
		});

		toolkit.paintBordersFor(sectionClient);
	}

	/**
	 * Создание зоны редактировани
	 * 
	 * @param parent
	 */
	public abstract void createEditorArea(Composite parent);

	/**
	 * Обработчик события нажатия кнопки "Save"
	 * 
	 */
	public abstract void doOnSaveClick();

	/**
	 * Заголовок формы редактирования
	 */
	public abstract String getEditorText();
	
	/**
	 * Инициализация формы редактирования
	 * 
	 * @param object
	 * 			БК
	 */
	public abstract void formContentPrecreating(T object);
}
