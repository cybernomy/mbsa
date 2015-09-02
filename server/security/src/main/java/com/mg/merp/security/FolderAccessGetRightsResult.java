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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FolderAccessGetRightsResult implements Serializable {
	public GroupItem[] srcList;
	public GroupItem[] dstList;
	
	FolderAccessGetRightsResult(GroupItem[] srcList, GroupItem[] dstList) {
		super();
		this.dstList = dstList;
		this.srcList = srcList;
	}
}
