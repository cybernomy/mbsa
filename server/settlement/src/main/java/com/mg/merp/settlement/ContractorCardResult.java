/*
 * ContractorCardResult.java
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
package com.mg.merp.settlement;

import java.io.Serializable;

/**
 * @author pashistova
 *
 */
public class ContractorCardResult implements Serializable {
	public byte[] valueIn;
	public byte[] valueOut;

	public ContractorCardResult(byte[] valueIn, byte[] valueOut) {
		this.valueIn = valueIn;
		this.valueOut = valueOut;
	}
}
