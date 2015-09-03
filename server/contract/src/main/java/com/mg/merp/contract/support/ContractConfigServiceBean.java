/*
 * ContractConfigServiceBean.java
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

package com.mg.merp.contract.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.ContractConfigServiceLocal;
import com.mg.merp.contract.model.ContractConfig;
import com.mg.merp.core.model.SysClient;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Контракты>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ContractConfigServiceBean.java,v 1.7 2007/05/11 12:48:44 sharapov Exp $
 */
@Stateless(name="merp/contract/ContractConfigService") //$NON-NLS-1$
public class ContractConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractConfig, Integer> implements ContractConfigServiceLocal {
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(ContractConfig entity) {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		entity.setSysClientId(sysClient.getId());
	}

}
