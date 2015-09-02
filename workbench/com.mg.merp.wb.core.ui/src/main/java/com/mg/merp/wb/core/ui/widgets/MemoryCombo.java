/* MemoryCombo.java
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

import java.util.ArrayList;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * Combobox с памятью
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: MemoryCombo.java,v 1.5 2007/07/11 06:05:15 poroxnenko Exp $ 
 */
public class MemoryCombo extends Combo {

	int size;
	
	private static final int DEFAULT_SIZE = 10;
	private static final String TOO_BIG="items lengs great than max size";
	
	public MemoryCombo(int size, Composite parent, int style) {
		super(parent, style);
		this.size = size;
	}
	
	public MemoryCombo(Composite parent, int style) {
		super(parent, style);
		this.size = DEFAULT_SIZE;
	}
	
	public void add (String str) {
		change(0,str);
	}
	
	public void add (String str, int index) {
		change(index,str);
	}
	
	public void setItems (String [] items) {
		if (items.length > size)
			throw new IllegalArgumentException(TOO_BIG);
		else
			super.setItems(items);
	}
	
	protected void checkSubclass () {
	}
	
	private void change(int index, String str) {
		String[] items = getItems();
		ArrayList<String> itemsList = new ArrayList<String>(items.length);
		int pos = size-1;
		boolean inList = false;
		for(int i = index; i<items.length; i++)
		{
			itemsList.add(items[i]);
			if (str.equals(items[i]))
			{
				pos = i;
				inList = true;
			}
		}
		if (items.length < size && !inList){
			super.add(str,index);
		}
		else{ 
			itemsList.add(index, str);
			itemsList.remove(pos+1);
			itemsList.toArray(items);
			setItems(items);
		}
		setText(str);
		select(index);
	}

	public int getMemorySize() {
		return size;
	}

	public void setMemorySize(int size) {
		this.size = size;
	}
}
