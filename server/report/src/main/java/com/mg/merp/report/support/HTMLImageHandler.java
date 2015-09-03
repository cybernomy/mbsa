/**
 * HTMLImageHandler.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.report.support;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IImage;

import com.mg.framework.support.ui.ContainerContextFactory;
import com.mg.framework.support.ui.DownloadManager;
import com.mg.framework.support.ui.UniversalFileWebResourceProvider;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация обработчика изображений генератора отчетов для отображения через
 * менеджер загрузок
 * 
 * @author Oleg V. Safonov
 * @version $Id: HTMLImageHandler.java,v 1.1 2007/11/09 12:05:07 safonov Exp $
 */
public class HTMLImageHandler extends HTMLServerImageHandler {

	private static Map<String, String> imageMap = new HashMap<String, String>();
	
	/* (non-Javadoc)
	 * @see org.eclipse.birt.report.engine.api.HTMLServerImageHandler#handleImage(org.eclipse.birt.report.engine.api.IImage, java.lang.Object, java.lang.String, boolean)
	 */
	@Override
	protected String handleImage(IImage image, Object context, String prefix,
			boolean needMap) {
		String mapID = null;
		if (needMap) {
			mapID = getImageMapID(image);
			synchronized (imageMap)	{
				if (imageMap.containsKey(mapID)) {
					return imageMap.get(mapID);
				}
			}
		}
		
		String ret = null;
		String imageDir = ServerUtils.getServerTempDir().getAbsolutePath();
		String fileName;
		File file;
		String extension = image.getExtension();
		if (extension != null && extension.length( ) > 0) {
			fileName = createUniqueFileName(imageDir, prefix, extension);
		} else {
			fileName = createUniqueFileName(imageDir, prefix);
		}
		
		file = new File(imageDir, fileName);
		try	{
			image.writeImage(file);
		} catch (IOException e)	{
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		file.deleteOnExit();//временные файлы
		
		DownloadManager.getInstance().put(fileName, new UniversalFileWebResourceProvider(file));
		ret = generateURI(fileName);
		if (needMap) {
			synchronized (imageMap)	{
				imageMap.put(mapID, ret);
			}
		}
		return ret;
	}

	private String generateURI(String fileName) {
		StringBuilder sb = new StringBuilder(ContainerContextFactory.getInstance().getDefaultContainerContext().getApplicationURL());
		return sb.append(DownloadManager.determineUrl(fileName)).toString();
	}
	
}
