/* ComboBoxFieldEditor.java
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
package com.mg.merp.wb.core.ui.widgets;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.mg.merp.wb.core.support.CoreUtils;

/**
 * Редактор однострочного текста с памятью
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: ComboFieldEditor.java,v 1.3 2007/07/11 06:05:15 poroxnenko Exp $ 
 */
public class ComboFieldEditor extends FieldEditor {

	MemoryCombo combo;
	String[] items;
	int style;
	int size;
	int mw = 90;
	
	private static final String STORE_ITEMS_KEY = "memoryComboBoxItems"; 
	
	
	public ComboFieldEditor(String name, String labelText, int size, int style, Composite parent) {
    	init(name, labelText);
        this.style = style;
        this.size = size;
        createControl(parent);
    }
	
    public ComboFieldEditor(String name, String labelText, String[] items, int style, Composite parent) {
    	init(name, labelText);
        this.items = items;
        this.style = style;
        this.size = items.length;
        createControl(parent);
    }
    
    public void setMinimumWidth(int mw){
    	this.mw = mw;
    }
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#adjustForNumColumns(int)
	 */
	@Override
	protected void adjustForNumColumns(int numColumns) {
		GridData gd = (GridData) combo.getLayoutData();
        gd.horizontalSpan = numColumns - 1;
        // We only grab excess space if we have to
        // If another field editor has more columns then
        // we assume it is setting the width.
        gd.grabExcessHorizontalSpace = gd.horizontalSpan == 1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#doFillIntoGrid(org.eclipse.swt.widgets.Composite, int)
	 */
	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		getLabelControl(parent);
		combo = new MemoryCombo(size, parent, style);
		if (items != null && items.length != 0)
			combo.setItems(items);
        GridData gd = new GridData();
        gd.horizontalSpan = numColumns - 1;
        gd.minimumWidth = mw;
        combo.setLayoutData(gd);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#doLoad()
	 */
	@Override
	protected void doLoad() {
        if (combo != null) {
        	String[] itms = CoreUtils.stringToStringArray(getPreferenceStore().getString(getPreferenceName()+STORE_ITEMS_KEY), ";");
            Integer index = getPreferenceStore().getInt(getPreferenceName());
        	if (itms != null && itms.length != 0){
    			combo.setItems(itms);
    			combo.select(index);
        	}
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#doLoadDefault()
	 */
	@Override
	protected void doLoadDefault() {
        if (combo != null) {
        	Integer index = getPreferenceStore().getDefaultInt(getPreferenceName());
        	combo.select(index);
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#doStore()
	 */
	@Override
	protected void doStore() {
		if((combo.getStyle() & SWT.READ_ONLY) == 0)
			combo.add(combo.getText());
		getPreferenceStore().setValue(getPreferenceName(), combo.getSelectionIndex());
		getPreferenceStore().setValue(getPreferenceName()+STORE_ITEMS_KEY, CoreUtils.stringArrToString(combo.getItems(), ";"));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#getNumberOfControls()
	 */
	@Override
	public int getNumberOfControls() {
		return 2;
	}
	
	public MemoryCombo getComboControl(Composite parent) {
        if (combo == null) {
        	combo = new MemoryCombo(10, parent, SWT.NONE);
        	combo.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                	combo = null;
                }
            });
        } else {
            checkParent(combo, parent);
        }
        return combo;
    }

}
