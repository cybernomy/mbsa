/*
 * Container.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.badi.library;

import static com.mg.merp.wb.badi.library.util.Constants.DESCRIPTION;
import static com.mg.merp.wb.badi.library.util.Constants.SRC_ARCH_ROOT;

import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import com.mg.merp.wb.badi.library.util.LibInfo;

/**
 * Контейнер, необходимый для формирования Classpath Java-проекта
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: Container.java,v 1.4 2007/07/11 07:31:29 poroxnenko Exp $
 */
public class Container implements IClasspathContainer {
	private IPath path;

	private IClasspathEntry[] classpathEntries;

	/**
	 * Список имён архивов, доступных для помещения в библиотеку
	 */
	private Set<String> includedLibs;

	private IJavaProject project;

	public Container(IPath pth, IJavaProject project) {
		path = pth;
		this.project = project;
		createIncludedLibs();
		computeClasspathEntries();
	}

	/**
	 * Метод заполняет контейнер jar библиотеками в соответствии с
	 * {@link Container#includedLibs} и включает библиотеки из папки thirdpart
	 */
	private void computeClasspathEntries() {
		createIncludedLibs();
		classpathEntries = new IClasspathEntry[includedLibs.size()
				+ BadiLibraryPlugin.getThirdLibs().size()];
		int i = 0;
		for (String nm : includedLibs) {
			LibInfo jarLoc = BadiLibraryPlugin.getLibs().get(nm);
			if (jarLoc.srcPath != null)
				classpathEntries[i] = JavaCore.newLibraryEntry(new Path(
						jarLoc.jarPath), new Path(jarLoc.srcPath), new Path(
						SRC_ARCH_ROOT), true);
			else
				classpathEntries[i] = JavaCore.newLibraryEntry(new Path(
						jarLoc.jarPath), null, null);
			i++;
		}
		for (String nm : BadiLibraryPlugin.getThirdLibs()) {
			classpathEntries[i] = JavaCore.newLibraryEntry(new Path(nm), null,
					null);
			i++;
		}

	}

	public IClasspathEntry[] getClasspathEntries() {
		return classpathEntries;
	}

	public String getDescription() {
		return BadiLibraryPlugin.getDefault().getString(DESCRIPTION);
	}

	public int getKind() {
		return IClasspathContainer.K_APPLICATION;
	}

	public IPath getPath() {
		return path;
	}

	/**
	 * Метод создаёт Set строк, из конфигурации Java-проекта, и представляет
	 * список имён архивов, доступных для помещения в библиотеку
	 */
	private void createIncludedLibs() {
		includedLibs = BadiLibraryPlugin.initLibsFromPropFile(project);
	}
}
