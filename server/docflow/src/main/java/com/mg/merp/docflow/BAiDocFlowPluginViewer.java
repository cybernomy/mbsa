/**
 * BAiDocFlowPluginViewer.java
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
package com.mg.merp.docflow;

import java.io.Serializable;

import com.mg.merp.docprocess.model.DocHeadState;

/**
 * Вывод результатов выполнения этапа ДО бизнес-расширением в UI
 * 
 * @author Oleg V. Safonov
 * @version $Id: BAiDocFlowPluginViewer.java,v 1.1 2007/09/27 09:21:05 safonov Exp $
 */
public interface BAiDocFlowPluginViewer extends Serializable {

	/**
	 * получить текстовое представление результата выполнения этапа ДО
	 * 
	 * @param docHeadState	состояние ДО
	 * @return	текстовое представление
	 */
	String getDocActionResultTextRepresentation(DocHeadState docHeadState);
	
	/**
	 * показать результат выполнения этапа ДО (например если этап создал документ, то можно
	 * показать данный документ в UI)
	 * 
	 * @param docHeadState	состояние ДО
	 */
	void showDocActionResult(DocHeadState docHeadState);

}
