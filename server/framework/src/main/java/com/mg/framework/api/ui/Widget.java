/*
 * Widget.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import org.dom4j.Element;

import com.mg.framework.api.ui.widget.PopupMenu;

/**
 * Widget
 * 
 * @author Oleg V. Safonov
 * @version $Id: Widget.java,v 1.15 2008/09/25 08:34:53 safonov Exp $
 */
public interface Widget {
	
	/**
	 * ������� �������������� �������� ����������, �������� ������ ���� ����������
	 * � ������ ��������� �����
	 */
	static final String WIDGET_ID = "id"; //$NON-NLS-1$
	
	/**
	 * ������� �������� "������ ������", ����� ��� <code>boolean</code>
	 */
	static final String READ_ONLY = "readOnly"; //$NON-NLS-1$
	
	/**
	 * ������� �������� "���������", ����� ��� <code>boolean</code>
	 */
	static final String VISIBLE = "visible"; //$NON-NLS-1$
	
	/**
	 * ������� �������� "�����������", ����� ��� <code>boolean</code>
	 */
	static final String ENABLED = "enabled"; //$NON-NLS-1$

	/**
	 * ������� �������� "���������"
	 */
	static final String TOOL_TIP = "toolTip"; //$NON-NLS-1$

	/**
	 * ������� ������������� ������� ������� ������
	 */
	static final String HELP_TOPIC = "helpTopic"; //$NON-NLS-1$
	
	/**
	 * ������� ���������� ����������� ������� �������� ����������, ������ ����� ��������� ������
	 * "������[,������]", ��� ������ � ������ �������� ������� � ��������
	 */
	static final String MINIMUM_SIZE = "minimumSize"; //$NON-NLS-1$

	/**
	 * ������� ���������� ������������ ������� �������� ����������, ������ ����� ��������� ������
	 * "������[,������]", ��� ������ � ������ �������� ������� � ��������
	 */
	static final String MAXIMUM_SIZE = "maximumSize"; //$NON-NLS-1$

	/**
	 * ������� ���������� ������� ����������� �������� ����������, ������ ����� ��������� ������
	 * "������[,������]", ��� ������ � ������ �������� ������� � ��������
	 */
	static final String PREFERRED_SIZE = "preferredSize"; //$NON-NLS-1$

	/**
	 * ������� ������� �������������� ��������, ����� ��� <code>boolean</code>
	 */
	static final String OPAQUE = "opaque"; //$NON-NLS-1$

	/**
	 * ������� �������� ��������� ������ �� �������, ����� ��� <code>boolean</code>
	 */
	static final String FOCUSABLE = "focusable"; //$NON-NLS-1$

	/**
	 * ������� ���������� �������� �������� �� ������� ������ ������� ����� ����� �������� ��������
	 */
	static final String NEXT_FOCUSABLE_WIDGET = "nextFocusableWidget"; //$NON-NLS-1$

	/**
	 * ��� ���������� ���� ���� �������� ����������
	 */
	static final String BACKGROUND = "jfd:background"; //$NON-NLS-1$

	/**
	 * ��� ���������� ���� ������ �������� ����������
	 */
	static final String FOREGROUND = "jfd:foreground"; //$NON-NLS-1$

	/**
	 * ��� ������ �������� ����������
	 */
	static final String FONT = "jfd:font"; //$NON-NLS-1$

	/**
	 * ������� ���������� ����� ��������� ����� ��� ��������, ����� ��������� ���, ���� ������, ��
	 * ����� ������� ����� ��� �������� ��������
	 */
	static final String LABEL = "label"; //$NON-NLS-1$
	
	/**
	 * Returns name of this component
	 * 
	 * @return	name
	 */
	String getName();
	
	/**
	 * Initialize widget by information from descriptor tag
	 * 
	 * @param element descriptor tag
	 */
	void init(Element element);
	
	/**
	 * Shows or hides this component as specified by <code>visible</code>. By default
	 * components are visible, except top-level components such as
	 * <code>Frame</code>. If the parent is invisible this method has no effect.
	 *
	 * @param visible 		 if <code>true</code>, show this component; otherwise, hide this component.
	 * @see		#isVisible
	 *
	 */
	void setVisible(boolean visible);
	
	/**
	 * Returns whether this component is visible (provided its parent is visible as
	 * well). By default components are visible, except top-level components such as
	 * <code>Frame</code>.
	 *
	 * @return		<code>true</code> if the component is visible; <code>false</code> otherwise.
	 * @see		#setVisible
	 */
	boolean isVisible();
	
	/**
	 * Enables or disables this component. An enabled
	 * component reacts to user input and generates events. By default components
	 * are enabled.
	 *
	 * @param enabled 		 if <code>true</code>, this component is enabled; otherwise this component is disabled.
	 * @see		#isEnabled
	 */
	void setEnabled(boolean enabled);
	
	/**
	 * Returns whether this component is enabled or disabled. By default components
	 * are enabled. An enabled component reacts to user input and generates events.
	 * The method {@link #setEnabled} changes the enabled state accordingly.
	 *
	 * @return		<code>true</code> if the component is enabled; <code>false</code> otherwise.
	 * @see		#setEnabled
	 */
	boolean isEnabled();

	/**
	 * Sets the ReadOnly property
	 * 
	 * @param readOnly	the ReadOnly property
	 * 
	 * @see #isReadOnly()
	 */
	void setReadOnly(boolean readOnly);
	
	/**
	 * Returns false for an editable component, otherwise true
	 * 
	 * @return	is component not editable
	 */
	boolean isReadOnly();

	/**
	 * Sets the background color of this component.
	 * 
	 * @param background	the color to become this component's background color. If null is passed the component inherits the background color of its parent.
	 */
	void setBackground(Color background);

	/**
	 * Gets the background color of this component.
	 * 
	 * @return	The background color of this component. If no background color has been specified for this component, the background color of the component's parent is returned
	 */
	Color getBackground();

	/**
	 * Sets the foreground color of this component.
	 * 
	 * @param foreground	the color to set as foreground for this component. If null is passed the component inherits the foreground color of its parent.
	 */
	void setForeground(Color foreground);

	/**
	 * Gets the foreground color of this component.
	 * 
	 * @return	the foreground color of this component. If no foreground color has been set for this component, the foreground color of the component's parent is returned
	 */
	Color getForeground();

	/**
	 * Sets the font of this component.
	 * 
	 * @param font	the font to be set for this component. If null is passed the component inherits the font of its parent.
	 */
	void setFont(Font font);

	/**
	 * Gets the font of this component.
	 * 
	 * @return	The font of this component. If no font has been specified for this component, the font of the component's parent is returned
	 */
	Font getFont();

	/**
	 * Requests the input focus for this component. Only visible components may
	 * request the focus.
	 */
	void requestFocus();
	
	/**
	 * Get implementation of widget, do not use directly from code
	 * 
	 * @return implementation
	 */
	Object getDelegate();
	
	/**
	 * Get popup menu of widget
	 * 
	 * @return popup menu
	 */
	PopupMenu getPopupMenu();
	
	/**
	 * Returns true for a long component, otherwise false
	 * 
	 * @return	is component long
	 */
	boolean isLong();
	
	/**
	 * Returns true for a scrollable component, otherwise false
	 * 
	 * @return	is component scrollable
	 */
	boolean isScrollable();
	
	/**
	 * dispose widget, called before a view closed
	 *
	 */
	void dispose();
	
	/**
	 * Get view
	 * 
	 * @return	view
	 */
	View getView();
	
}
