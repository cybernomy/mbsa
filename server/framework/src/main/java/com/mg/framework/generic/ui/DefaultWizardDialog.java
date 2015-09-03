/*
 * DefaultWizardDialog.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.ui.ConversationBeginner;

/**
 * Класс диалога поддерживающего пользовательскую транзакцию (conversation). При запуске диалога
 * стартует пользовательская транзакция и диалог становится ее владельцем, если она не стартована,
 * либо участвует в текущей транзакции. После закрытия диалога если он является владельцем пользовательской
 * транзакции она будет завершена.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultWizardDialog.java,v 1.1 2006/09/11 09:27:53 safonov Exp $
 */
public class DefaultWizardDialog extends DefaultDialog implements
		ConversationBeginner {

}
