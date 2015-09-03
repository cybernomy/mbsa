/* MemoryFilter.java
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
package com.mg.merp.wb.core.ui.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * Компонент, используемый в качестве фильтра с памятью в браузерах БК
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: MemoryFilter.java,v 1.3 2007/07/11 06:05:15 poroxnenko Exp $ 
 */
public class MemoryFilter extends Composite {

	private MemoryCombo comboFilter;
	private Button actionBut;
	private PatternFilter patternFilter = new PatternFilter();;
	
	/**
	 * ID храненения данных фильтра
	 */
	private String settingID = null;
	private SelectionListener selectionListener;
	
	public MemoryFilter(Composite parent, int style) {
		super(parent, style);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		this.setLayout(layout);
		
		GridData memoryCmbGDgridData = new GridData();
		memoryCmbGDgridData.grabExcessHorizontalSpace = true;
		memoryCmbGDgridData.verticalAlignment = GridData.CENTER;
		memoryCmbGDgridData.horizontalAlignment = GridData.FILL;
		
		comboFilter = new MemoryCombo(10,this, SWT.BORDER);
		comboFilter.setLayoutData(memoryCmbGDgridData);
		
		actionBut = new Button(this, SWT.NONE);
		actionBut.setImage(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImage(ISharedImages.IMG_TOOL_FORWARD));
		actionBut.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				patternFilter.setPattern(comboFilter.getText());
				comboFilter.add(comboFilter.getText());
				selectionListener.widgetSelected(e);
			}
			
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
	}

	public Button getActionBut() {
		return actionBut;
	}

	public void setActionBut(Button actionBut) {
		this.actionBut = actionBut;
	}

	public MemoryCombo getComboFilter() {
		return comboFilter;
	}

	public void setComboFilter(MemoryCombo comboFilter) {
		this.comboFilter = comboFilter;
	}

	public PatternFilter getPatternFilter() {
		return patternFilter;
	}

	public void setPatternFilter(PatternFilter patternFilter) {
		this.patternFilter = patternFilter;
	}
	
	public void addSelectionListener (SelectionListener listener) {
		TypedListener typedListener = new TypedListener (listener);
		addListener(SWT.Selection, typedListener);
		this.selectionListener = listener;
	}
	
	public int getMemorySize() {
		return comboFilter.getMemorySize();
	}

	public void setMemorySize(int size) {
		this.comboFilter.setMemorySize(size);
	}

	public String getSettingID() {
		return settingID;
	}

	public void setSettingID(String settingID) {
		this.settingID = settingID;
	}
	
}
