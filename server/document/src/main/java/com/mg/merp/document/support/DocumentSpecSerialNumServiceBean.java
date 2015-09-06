/*
 * DocumentSpecSerialNumServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.document.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.document.DocumentSpecSerialNumServiceLocal;
import com.mg.merp.document.model.DocumentSpecSerialNum;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Серийные номера"
 *
 * @author leonova
 * @version $Id: DocumentSpecSerialNumServiceBean.java,v 1.3 2006/08/22 07:13:46 leonova Exp $
 */
@Stateless(name = "merp/document/DocumentSpecSerialNumService")
public class DocumentSpecSerialNumServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocumentSpecSerialNum, Integer> implements DocumentSpecSerialNumServiceLocal {


}
