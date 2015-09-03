/*
 * TableConfigServiceBean.java
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

package com.mg.merp.table.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.table.TableConfigServiceLocal;
import com.mg.merp.table.model.TableConfig;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Табельный учет>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: TableConfigServiceBean.java,v 1.5 2007/01/13 13:33:04 sharapov Exp $
 */
@Stateless(name="merp/table/TableConfigService") //$NON-NLS-1$
public class TableConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TableConfig, Integer> implements TableConfigServiceLocal {

}
