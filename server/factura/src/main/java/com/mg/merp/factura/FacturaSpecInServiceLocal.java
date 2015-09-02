/*
 * FacturaSpecInServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.factura;

import com.mg.merp.document.model.DocSpec;

/**
 * ������-��������� "������������ �������� ������-������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FacturaSpecInServiceLocal.java,v 1.3 2008/08/27 09:29:31 sharapov Exp $
 */
public interface FacturaSpecInServiceLocal extends com.mg.merp.document.GoodsDocumentSpecification<DocSpec, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/factura/FacturaSpecIn"; //$NON-NLS-1$
	
}
