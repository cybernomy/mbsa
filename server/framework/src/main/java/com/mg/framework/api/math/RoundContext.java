/*
 * RoundContext.java
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
package com.mg.framework.api.math;

import java.math.RoundingMode;

/**
 * �������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: RoundContext.java,v 1.1 2006/12/02 12:05:10 safonov Exp $
 */
public class RoundContext {
	private static final RoundingMode DEFAULT_ROUNDINGMODE = RoundingMode.HALF_UP;
	
	final int scale;
	
	final RoundingMode roundingMode;

	/**
	 * �������� ��������� � ��������� � ������� ���������� �� ��������� {@link RoundingMode.HALF_UP}
	 * 
	 * @param scale	�������� (���������� ������ ����� �����)
	 */
	public RoundContext(int scale) {
		this(scale, DEFAULT_ROUNDINGMODE);
	}
	
	/**
	 * �������� ��������� � ��������� � ������� ����������
	 * 
	 * @param scale			�������� (���������� ������ ����� �����)
	 * @param roundingMode	����� ����������
	 */
	public RoundContext(int scale, RoundingMode roundingMode) {
		this.scale = scale;
		this.roundingMode = roundingMode;
	}

	/**
	 * �������� ����� ����������
	 * @see java.math.RoundingMode
	 * 
	 * @return the roundingMode
	 */
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}

	/**
	 * �������� �������� ���������� (���������� ������ ����� �����)
	 * 
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}
}
