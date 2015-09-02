/**
 * CustomActionDescriptorImpl.java
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
package com.mg.merp.baiengine.support;

import com.mg.framework.api.ui.CustomActionDescriptor;
import com.mg.merp.baiengine.model.CustomUserAction;

/**
 * Реализация настраиваемого действия
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionDescriptorImpl.java,v 1.1 2007/11/15 09:19:05 safonov Exp $
 */
public class CustomActionDescriptorImpl implements CustomActionDescriptor {
	private String caption;
	private String icon;
	private String keyStroke;
	private String name;
	private String toolTip;
	private boolean forceRefresh;
	private boolean popupMenu;
	private boolean toolBar;
	private boolean separatorBefore;
	private boolean separatorAfter;

	/**
	 * конструктор
	 * 
	 * @param action	сущность настраиваемого действия
	 */
	public CustomActionDescriptorImpl(CustomUserAction action) {
		super();
		this.caption = action.getCaption();
		this.icon = action.getIcon();
		this.keyStroke = action.getKeyStroke();
		this.name = action.getCode();
		this.toolTip = action.getHint();
		this.forceRefresh = action.isForceRefresh();
		this.popupMenu = action.isFromMenu();
		this.toolBar = action.isFromToolbar();
		this.separatorBefore = action.isSeparatorBefore();
		this.separatorAfter = action.isSeparatorAfter();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#getIcon()
	 */
	public String getIcon() {
		return icon;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#getKeyStroke()
	 */
	public String getKeyStroke() {
		return keyStroke;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#getToolTip()
	 */
	public String getToolTip() {
		return toolTip;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#isForceRefresh()
	 */
	public boolean isForceRefresh() {
		return forceRefresh;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#isPopupMenu()
	 */
	public boolean isPopupMenu() {
		return popupMenu;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#isSeparatorAfter()
	 */
	public boolean isSeparatorAfter() {
		return separatorAfter;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#isSeparatorBefore()
	 */
	public boolean isSeparatorBefore() {
		return separatorBefore;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionDescriptor#isToolBar()
	 */
	public boolean isToolBar() {
		return toolBar;
	}

}
