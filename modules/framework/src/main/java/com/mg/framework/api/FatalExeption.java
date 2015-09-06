/*
 * Created on 05.04.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.framework.api;

/**
 * @author safonov
 *
 *         To change the template for this generated type comment go to Window>Preferences>Java>Code
 *         Generation>Code and Comments
 */
public class FatalExeption extends Exception {
  public FatalExeption(String s, Throwable ex) {
    super(s, ex);
  }

  public FatalExeption(String s) {
    super(s);
  }

  public FatalExeption() {
    super();
  }

  public FatalExeption(Throwable cause) {
    super(cause);
  }
}
