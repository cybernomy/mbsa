/*
 * Created on 06.09.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mg.merp.document;

import java.io.Serializable;

/**
 * @author safonov
 *
 *         TODO To change the template for this generated type comment go to Window - Preferences -
 *         Java - Code Style - Code Templates
 */
public class DocTypeAccessGetRightResult implements Serializable {
  public GroupItem[] sourceList;
  public GroupItem[] destList;

  public DocTypeAccessGetRightResult(GroupItem[] sourceList, GroupItem[] destList) {
    this.sourceList = sourceList;
    this.destList = destList;
  }
}
