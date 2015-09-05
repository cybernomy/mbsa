/*
 * Created on 05.04.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.merp.core;

import com.mg.framework.api.FatalExeption;

/**
 * @author safonov
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CreateConnectionError extends FatalExeption {
	public CreateConnectionError(String s, Throwable ex) {
		super(s, ex);
	}
}
