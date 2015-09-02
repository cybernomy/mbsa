/*
 * DocumentPattern.java
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
package com.mg.merp.document;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.document.model.DocHeadModel;

/**
 * Бизнес-компонент "Образец документа"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentPattern.java,v 1.1 2006/09/20 10:40:51 safonov Exp $
 */
public interface DocumentPattern<T extends DocHeadModel, ID extends Serializable> extends DataBusinessObjectService<T, ID> {
	
}
