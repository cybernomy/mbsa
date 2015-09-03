/* StockSituationBrowser.java
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
package com.mg.merp.reference;

import com.mg.framework.api.ui.WidgetEvent;

/**
 * Браузеры, наследующие этот интерфейс могут вызывать стандартную
 * форму "Количество на складах"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StockSituationBrowser.java,v 1.1 2007/04/05 12:25:41 poroxnenko Exp $ 
 */
public interface StockSituationBrowser {

	/**
	 * Вызов формы "Количество на складах"
	 * 
	 * @param event
	 * @throws Exception
	 */
	void onActionShowStockSituation(WidgetEvent event) throws Exception;
}
