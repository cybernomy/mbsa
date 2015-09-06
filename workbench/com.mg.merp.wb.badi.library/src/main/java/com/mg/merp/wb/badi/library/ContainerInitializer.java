/* ContainerInitializer.java
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
package com.mg.merp.wb.badi.library;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Инициализатор библиотеки .MBAi
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: ContainerInitializer.java,v 1.1 2006/11/22 13:03:19 poroxnenko Exp $
 */
public class ContainerInitializer extends ClasspathContainerInitializer {

  /**
   * Если библиотека подключена к проекту, то при старте платформы её необходимо инициализировать.
   */
  @Override
  public void initialize(IPath containerPath, IJavaProject project)
      throws CoreException {
    if (containerPath.segment(0).equals(
        BadiLibraryPlugin.getDefault().getContainerID())) {
      IClasspathContainer container = new Container(containerPath,
          project);
      JavaCore.setClasspathContainer(containerPath,
          new IJavaProject[]{project},
          new IClasspathContainer[]{container}, null);
    }
  }

}
