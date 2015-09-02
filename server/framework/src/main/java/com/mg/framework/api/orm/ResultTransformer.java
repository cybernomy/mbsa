/*
 * ResultTransformer.java
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
package com.mg.framework.api.orm;

import java.io.Serializable;

/**
 * Implementors define a strategy for transforming criteria query
 * results into the actual application-visible query result list.
 * 
 * @author Oleg V. Safonov
 * @version $Id: ResultTransformer.java,v 1.1 2006/12/12 13:54:52 safonov Exp $
 */
public interface ResultTransformer<T> extends Serializable {
	
	/**
	 * transform tuple
	 * 
	 * @param tuple		values
	 * @param aliases	aliases
	 * @return	application-visible result
	 */
	T transformTuple(Object[] tuple, String[] aliases);
}
