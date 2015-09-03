/*
 * CreateDocOnComponentsService.java
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
package com.mg.merp.document.support.jboss;

import java.util.Date;
import java.util.List;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.CreateDocOnComponents;
import com.mg.merp.document.CreateDocumentBasisOfCallback;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.support.CreateDocOnComponentsImpl;

/**
 * Реализация сервиса создания документа на комплектующие для JBoss
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponentsService.java,v 1.1 2007/10/23 13:55:29 alikaev Exp $
 */
@Service(objectName=CreateDocOnComponentsServiceMBean.SERVICE_NAME, name="merp/document/CreateDocOnComponentsService")
@Management(CreateDocOnComponentsServiceMBean.class)
@Depends("jboss.j2ee:jar=050mreference-ejb.jar,name=merp/reference/CurrencyRateService,service=EJB3")
public class CreateDocOnComponentsService<S extends DocHead, D extends DocHead, P extends DocHeadModel> extends ServiceMBeanSupport implements CreateDocOnComponentsServiceMBean<S, D, P> {

	private CreateDocOnComponents<S, D, P> delegate = new CreateDocOnComponentsImpl<S, D, P>();

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, java.lang.Class, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder)
	 */
	public D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder) {
		return delegate.doCreate(srcDoc, dstClass, model, date, specList, destFolder);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder)
	 */
	public void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder) {	
		delegate.doCreate(srcDoc, dstDoc, model, date, specList, destFolder);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, java.lang.Class, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder, com.mg.merp.document.CreateDocumentBasisOfCallback)
	 */
	public D doCreate(S srcDoc, Class<D> dstClass, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
			CreateDocumentBasisOfCallback createCallback) {
		return delegate.doCreate(srcDoc, dstClass, model, date, specList, destFolder, createCallback);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.CreateDocumentBasisOf#doCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHead, com.mg.merp.document.model.DocHeadModel, java.util.Date, java.util.List, com.mg.merp.core.model.Folder, com.mg.merp.document.CreateDocumentBasisOfCallback)
	 */
	public void doCreate(S srcDoc, D dstDoc, P model, Date date, List<DocumentSpecItem> specList, Folder destFolder,
			CreateDocumentBasisOfCallback createCallback) {	
		delegate.doCreate(srcDoc, dstDoc, model, date, specList, destFolder, createCallback);		
	}

}
