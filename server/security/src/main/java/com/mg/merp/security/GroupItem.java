/*
 * Created on 20.09.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mg.merp.security;

import java.io.Serializable;

/**
 * @author safonov
 *
 *         TODO To change the template for this generated type comment go to Window - Preferences -
 *         Java - Code Style - Code Templates
 */
public class GroupItem implements Serializable {
  public int key;
  public String name;

  GroupItem() {
    super();
  }

  GroupItem(int key, String name) {
    super();
    this.key = key;
    this.name = name;
  }
}
