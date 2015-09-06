/* CreateDocumentBasisOfServiceMBean.java
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

import com.mg.merp.document.CreateDocumentBasisOf;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

import org.jboss.system.ServiceMBean;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: CreateDocumentBasisOfServiceMBean.java,v 1.1 2007/05/02 10:35:42 poroxnenko Exp $
 */
public interface CreateDocumentBasisOfServiceMBean<T1 extends DocHead, T2 extends DocHead, T3 extends DocHeadModel> extends CreateDocumentBasisOf<T1, T2, T3>, ServiceMBean {

}
