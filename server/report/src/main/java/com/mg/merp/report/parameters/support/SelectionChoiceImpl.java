/**
 * SelectionChoiceImpl.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.report.parameters.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.birt.report.engine.api.IParameterSelectionChoice;

import com.mg.merp.report.parameters.SelectionChoice;

/**
 * Реализация элемента выбора параметра
 * 
 * @author Oleg V. Safonov
 * @version $Id: SelectionChoiceImpl.java,v 1.1 2008/05/13 15:14:20 safonov Exp $
 */
public class SelectionChoiceImpl implements SelectionChoice {
	private String label;
	private Object value;

	public SelectionChoiceImpl(String label, Object value) {
		super();
		this.label = label;
		this.value = value;
	}

	public SelectionChoiceImpl(IParameterSelectionChoice parameterSelectionChoice) {
		this(parameterSelectionChoice.getLabel(), parameterSelectionChoice.getValue());
	}

	/**
	 * конвертация значений параметров BIRT
	 * 
	 * @param selectionList	значения платформы BIRT
	 * @return	значения платформы Jet
	 */
	@SuppressWarnings("unchecked")
	public static List<SelectionChoice> convertEngineParameterSelectionChoice(Collection selectionList) {
		if ( selectionList == null )
			return Collections.EMPTY_LIST;
		List<SelectionChoice> ret = new ArrayList<SelectionChoice>();
		for (IParameterSelectionChoice parameterSelectionChoice : (Collection<IParameterSelectionChoice>) selectionList)
			ret.add(new SelectionChoiceImpl(parameterSelectionChoice));
		return ret;
	}

	/**
	 * найти индекс в списке значений параметра по значению параметра
	 * 
	 * @param selectionList	список значений
	 * @param value	значение параметра
	 * @return	индекс или -1 если не найден
	 */
	public static int findSelectionIndexByValue(List<SelectionChoice> selectionList, Object value) {
		if (value != null)
			for (int i = 0, length = selectionList.size(); i < length; i++)
				if (value.equals(selectionList.get(i).getValue()))
					return i;
		return -1;
	}

	/**
	 * найти индекс в списке значений параметра по метке UI параметра
	 * 
	 * @param selectionList	список значений
	 * @param label	метка UI
	 * @return	индекс или -1 если не найден
	 */
	public static int findSelectionIndexByLabel(List<SelectionChoice> selectionList, String label) {
		if (label != null)
			for (int i = 0, length = selectionList.size(); i < length; i++)
				if (label.equals(selectionList.get(i).getLabel()))
					return i;
		return -1;
	}

	/**
	 * конвертация списка выбора параметров в список значений параметра 
	 * 
	 * @param selectionList	список выбора
	 * @return	список значений
	 */
	public static Object[] convertParameterSelectionChoiceToValues(List<SelectionChoice> selectionList) {
		if (selectionList == null)
			return new Object[0];
		Object[] result = new Object[selectionList.size()];
		for (int i = 0, length = selectionList.size(); i < length; i++)
			result[i] = selectionList.get(i).getLabel();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.SelectionChoice#getLabel()
	 */
	public String getLabel() {
		return this.label;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.report.parameters.SelectionChoice#getValue()
	 */
	public Object getValue() {
		return this.value;
	}

}
