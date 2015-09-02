/*
 * DropAction.java
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
package com.mg.framework.api.ui.dnd;

/**
 * Тип операции DnD
 * 
 * @author Oleg V. Safonov
 * @version $Id: DropAction.java,v 1.1 2007/08/16 13:48:06 safonov Exp $
 */
public enum DropAction {
	/**
	 * Represents the none action.
	 */
	ACTION_NONE,
	
	/**
	 * Represents the copy action.
	 */
	ACTION_COPY,
	
	/**
	 * Represents the move action.
	 */
	ACTION_MOVE,
	
	/**
	 * Represents either the copy or the move action.
	 */
	ACTION_COPY_OR_MOVE,
	
	/**
	 * Represents the link action. The semantic of the link action may both by
	 * platform and/or application dependent. Broadly spoken, link means "do not
	 * copy, or move the operand, but create a reference to it". Reference is the
	 * ambigous part of the semantic.
	 */
	ACTION_LINK
}
