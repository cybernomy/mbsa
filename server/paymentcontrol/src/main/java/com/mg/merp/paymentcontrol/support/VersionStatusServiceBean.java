/*
 * VersionStatusServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import java.util.Date;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.VersionStatusServiceLocal;
import com.mg.merp.paymentcontrol.model.Version;
import com.mg.merp.paymentcontrol.model.VersionStatus;
import com.mg.merp.paymentcontrol.model.VersionStatusKind;
import com.mg.merp.security.model.SecUser;

/**
 * Реализация бизнес-компонента "Статусы версий планирования" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: VersionStatusServiceBean.java,v 1.4 2007/05/14 05:18:50 sharapov Exp $
 */
@Stateless(name="merp/paymentcontrol/VersionStatusService") //$NON-NLS-1$
public class VersionStatusServiceBean extends AbstractPOJODataBusinessObjectServiceBean<VersionStatus, Integer> implements VersionStatusServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.VersionStatusServiceLocal#addVersionStatus(com.mg.merp.paymentcontrol.model.Version, com.mg.merp.paymentcontrol.model.VersionStatusKind, java.util.Date, java.util.Date, java.util.Date, com.mg.merp.security.model.SecUser)
	 */
	public void addVersionStatus(Version version, VersionStatusKind kind, Date createDate, Date dateFrom, Date dateTill) {
		internalAddVersionStatus(version, kind, createDate, dateFrom, dateTill);
	}

	private  void internalAddVersionStatus(Version version, VersionStatusKind kind, Date createDate, Date dateFrom, Date dateTill) {
		VersionStatus versionStatus = initialize();
		versionStatus.setVersion(version);
		versionStatus.setKind(kind);
		versionStatus.setCreateDate(createDate);
		versionStatus.setDateFrom(dateFrom);
		versionStatus.setDateTill(dateTill);
		versionStatus.setCreator(ServerUtils.getPersistentManager().find(SecUser.class, ServerUtils.getUserProfile().getIdentificator()));
		store(versionStatus);
	}

}
