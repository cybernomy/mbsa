/*
 * BucketRange.java
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
package com.mg.merp.mfreference;

import java.util.Date;

/**
 * Диапазон бакета
 * 
 * @author Oleg V. Safonov
 * @version $Id: BucketRange.java,v 1.1 2007/07/30 10:25:31 safonov Exp $
 */
public class BucketRange {
	private Date bucketStart;
	private Date bucketEnd;
	
	public BucketRange(Date bucketStart, Date bucketEnd) {
		super();
		this.bucketStart = bucketStart;
		this.bucketEnd = bucketEnd;
	}

	/**
	 * получить дату завершения бакета
	 * 
	 * @return the bucketEnd
	 */
	public Date getBucketEnd() {
		return bucketEnd;
	}

	/**
	 * получить дату старта бакета
	 * 
	 * @return the bucketStart
	 */
	public Date getBucketStart() {
		return bucketStart;
	}

}
