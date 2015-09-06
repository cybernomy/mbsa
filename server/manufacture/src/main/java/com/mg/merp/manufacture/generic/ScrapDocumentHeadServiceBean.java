/*
 * ScrapDocumentHeadServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.manufacture.generic;

import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.GoodsDocumentSpecification;

import java.io.Serializable;

public abstract class ScrapDocumentHeadServiceBean<T extends com.mg.merp.document.model.DocHead, ID extends Serializable, P extends DocumentPattern, S extends GoodsDocumentSpecification>
    extends ManufactureDocumentHeadServiceBean<T, ID, P, S> {
}
