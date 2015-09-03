/*
 * RptBIRTDeployerService.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.report.service.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.report.RptMainTransfer;
import com.mg.merp.report.support.RptBIRTDeployerImpl;

/**
 * Сервис интерфейса связи сервера приложений и редактора отчётов<br>
 * Реализует интерфейс {@link RptBIRTDeployer)
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id:
 */
public class RptBIRTDeployerService extends ServiceMBeanSupport implements
		RptBIRTDeployerServiceMBean {
	private RptBIRTDeployerImpl delegate = new RptBIRTDeployerImpl();

	public RptMainTransfer persistTemplate(RptMainTransfer rmt)
			throws Exception {
		return delegate.persistTemplate(rmt);
	}

	public RptMainTransfer[] getReports(String likeSentence) throws Exception {
		return delegate.getReports(likeSentence);
	}

	public RptMainTransfer addReport(RptMainTransfer rmt) throws Exception {
		return delegate.addReport(rmt);
	}

	public void deleteReportList(Integer[] ids) throws Exception {
		delegate.deleteReportList(ids);
	}

	public RptMainTransfer editReport(RptMainTransfer rmt) throws Exception {
		return delegate.editReport(rmt);
	}
}
