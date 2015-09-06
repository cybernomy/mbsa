/*
 * Created on 27.04.2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.mg.framework.generic;

/**
 * @author safonov
 *
 *         To change the template for this generated type comment go to Window>Preferences>Java>Code
 *         Generation>Code and Comments
 */
public abstract class AbstractEntityBMPBean extends AbstractEntityBean {

  private boolean dirty = false;

  public boolean isModified() {
    return dirty;
  }

  protected void makeDirty() {
    dirty = true;
  }

  protected void makeClean() {
    dirty = false;
  }

}
