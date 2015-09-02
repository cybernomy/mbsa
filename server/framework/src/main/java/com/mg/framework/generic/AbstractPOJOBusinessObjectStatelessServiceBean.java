/*
 * AbstractPOJOBusinessObjectStatelessServiceBean.java
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
package com.mg.framework.generic;

import javax.annotation.security.PermitAll;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.metadata.BusinessServiceMetadata;
import com.mg.framework.utils.BusinessObjectUtils;

/**
 * Абстрактная реализация бизнес-компонента основанного на сервисах не имеющих состояние во время выполнения (stateless)
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractPOJOBusinessObjectStatelessServiceBean.java,v 1.4 2006/09/28 12:24:12 safonov Exp $
 */
public abstract class AbstractPOJOBusinessObjectStatelessServiceBean extends
		AbstractStatelessSessionBean implements BusinessObjectService {
	private BusinessServiceMetadata serviceMetadata = null;

	/**
	 * Перехватчик безопасности
	 * 
	 * @param ctx	контекст вызова
	 * @return	результат вызова
	 * @throws Exception	ИС сгенерированная во время вызова
	 */
	@AroundInvoke
	public Object securityCheckIntercept(InvocationContext ctx) throws Exception {
		return BusinessObjectUtils.securityCheckInterceptor(ctx, getLogger(), getBusinessServiceMetadata());
	}

	/**
	 * Реализация загрузки метаданных бизнес-компонента
	 * 
	 * @return	метаданные
	 */
	protected BusinessServiceMetadata doLoadBusinessServiceMetadata() {
		return BusinessObjectUtils.loadBusinessServiceMetadata(getSessionContext());
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.BusinessObjectService#loadBusinessServiceMetadata()
	 */
	@PermitAll
	public BusinessServiceMetadata getBusinessServiceMetadata() {
		if (serviceMetadata == null)
			serviceMetadata = doLoadBusinessServiceMetadata();
		return serviceMetadata;
	}

}
