/*
 * BadiLibraryPlugin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.merp.wb.badi.library.util.LibInfo;
import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.plugin.MerpUIPlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static com.mg.merp.wb.badi.library.util.Constants.FLDR_LIB;
import static com.mg.merp.wb.badi.library.util.Constants.FLDR_SRC;
import static com.mg.merp.wb.badi.library.util.Constants.FLDR_THIRD;
import static com.mg.merp.wb.badi.library.util.Constants.INCLUDED_LIBS_PROPERTY_NAME;
import static com.mg.merp.wb.badi.library.util.Constants.MBAI_PROPERTY_FILE_COMMENT;
import static com.mg.merp.wb.badi.library.util.Constants.MBAI_PROPERTY_FILE_NAME;
import static com.mg.merp.wb.badi.library.util.Constants.SRC_SFX;

/**
 * Плугин-активатор подключаемой библиотеки
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: BadiLibraryPlugin.java,v 1.8 2008/09/23 13:41:22 safonov Exp $
 */
public class BadiLibraryPlugin extends MerpUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "com.mg.merp.wb.badi.library";

  /*
   * Храниться в манифесте
   */
  private static final String MAN_LIB_DEPENDS = "BadiLibrary-Dependencies";

  private static final String MAN_VERSION = "BadiLibrary-ManifestVersion";

  private static final String MAN_LIB_VERSION_1 = "1.0";

  private static final String MAN_LIB_VERSION = "BadiLibrary-Version";

  /*
   * Храниться в файле properties
   */
  private static final String LIB_VENDOR = "Badi.Lib.Vendor";

  private static final String LIB_DESC = "Badi.Lib.Desc";

  private static final String LIB_TITLE = "Badi.Lib.Title";

  /**
   * Дескриптор библиотеки
   */
  private static final String LIB_DESC_FILE_PATH = "META-INF/badilibrary";

  private static final String LIB_DESC_FILE_EXT = "properties";
  private static final String RESOURCE_NAME = "com.mg.merp.wb.badi.library.messages";
  private static final String CONTAINER_ID = "com.mg.merp.wb.badi.library.CONTAINER";
  // The shared instance
  private static BadiLibraryPlugin plugin;
  /**
   * Архивы, доступные для динамического помещения в библиотеку
   */
  private static Map<String, LibInfo> libs;

  /**
   * Пути к архивам thirdpart
   */
  private static List<String> thirdLibs;

  private static String rootPath;

  private static String locVariant = Locale.getDefault().getVariant()
      .length() == 0 ? "" : "_" + Locale.getDefault().getVariant();

  private static String locLang = Locale.getDefault().getLanguage().length() == 0 ? ""
      : "_" + Locale.getDefault().getLanguage();

  private static String locCountry = Locale.getDefault().getCountry()
      .length() == 0 ? "" : "_" + Locale.getDefault().getCountry();

  public BadiLibraryPlugin() {
    super();
    plugin = this;
    resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
  }

  /**
   * Инициализация архивов, которые будут доступны библиотеке
   */
  private static void initLibs() {
    libs = new HashMap<String, LibInfo>();
    thirdLibs = new LinkedList<String>();
    File ff;

    ff = new File(rootPath + "/" + FLDR_LIB);
    if (ff.exists() && ff.isDirectory()) {
      for (byte i = 0; i < ff.list().length; i++) {
        String key = ff.listFiles()[i].getName();
        addArch(key.substring(0, key.length() - 4), ff.listFiles()[i]
            .getAbsolutePath());
      }
    }

    ff = new File(rootPath + "/" + FLDR_THIRD);
    if (ff.exists() && ff.isDirectory()) {
      for (byte i = 0; i < ff.list().length; i++)
        thirdLibs.add(ff.listFiles()[i].getAbsolutePath());
    }
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static BadiLibraryPlugin getDefault() {
    return plugin;
  }

  public static Map<String, LibInfo> getLibs() {
    return libs;
  }

  private static void initPluginPath() throws IOException, URISyntaxException {
    Bundle bundle = plugin.getBundle();
    Path path = new Path("");
    rootPath = new File(FileLocator.resolve(FileLocator.find(bundle, path, null)).toURI()).getAbsolutePath();
  }

  private static String findSrc(String filename) {
    File ff = new File(filename);
    if (ff.exists() && !ff.isDirectory())
      return ff.getAbsolutePath();
    else
      return null;
  }

  private static void addArch(String key, String path) {
    boolean result = true;
    JarFile jarfile = null;
    Manifest mf = null;
    LibInfo li = new LibInfo();
    try {
      jarfile = new JarFile(path);
      mf = jarfile.getManifest();

      Attributes attrs = mf.getMainAttributes();
      /*
			 * Версия BadiLibrary-ManifestVersion: 1.0
			 */
      if (attrs.getValue(MAN_VERSION) != null
          && attrs.getValue(MAN_VERSION).equals(MAN_LIB_VERSION_1)) {
        li.libVersion = attrs.getValue(MAN_LIB_VERSION);
        li.jarPath = path;
        String dpnds = attrs.getValue(MAN_LIB_DEPENDS);
        if (dpnds != null) {
          String[] sa = CoreUtils.stringToStringArray(dpnds, ",");
          Set<String> dp = new LinkedHashSet<String>(Arrays
              .asList(sa));
          li.libDepends = dp;
        }
        li.srcPath = findSrc(rootPath + "/" + FLDR_SRC + "/" + key
            + SRC_SFX + ".jar");
        li.libName = key;

        JarEntry je = null;
        if ((je = jarfile.getJarEntry(LIB_DESC_FILE_PATH + locLang
            + locCountry + locVariant + "." + LIB_DESC_FILE_EXT)) == null)
          if ((je = jarfile.getJarEntry(LIB_DESC_FILE_PATH + locLang
              + locCountry + "." + LIB_DESC_FILE_EXT)) == null)
            if ((je = jarfile.getJarEntry(LIB_DESC_FILE_PATH
                + locLang + "." + LIB_DESC_FILE_EXT)) == null)
              je = jarfile.getJarEntry(LIB_DESC_FILE_PATH + "."
                  + LIB_DESC_FILE_EXT);
        if (je != null) {
          InputStream is = jarfile.getInputStream(je);
          PropertyResourceBundle prb = new PropertyResourceBundle(is);

          try {
            li.libDesc = prb.getString(LIB_DESC);
            li.libTitle = prb.getString(LIB_TITLE);
            li.libVendor = prb.getString(LIB_VENDOR);
          } catch (Exception ex) {
          }
        }
      } else
        result = false;
      if (li.libTitle == null)
        li.libTitle = key;
    } catch (IOException e) {
      result = false;
      e.printStackTrace();
    } finally {
      try {
        jarfile.close();
        if (result)
          libs.put(key, li);
      } catch (IOException ex) {
      }
    }
  }

  public static List<String> getThirdLibs() {
    return thirdLibs;
  }

  /**
   * Включение динамических библиотек при активации из описателя .MBAi
   *
   * @param project проект
   * @return список имен архивов
   */
  public static Set<String> initLibsFromPropFile(IJavaProject project) {
    Set<String> result = null;
    InputStream is = null;
    try {
      is = new FileInputStream(project.getProject().getLocation() + "/"
          + MBAI_PROPERTY_FILE_NAME);
      Properties properties = new Properties();
      properties.load(is);

      String incl = properties.getProperty(INCLUDED_LIBS_PROPERTY_NAME);
      if (incl == null)
        result = new LinkedHashSet<String>();
      else {
        String[] str = CoreUtils.stringToStringArray(incl, ",");
        result = new LinkedHashSet<String>(Arrays.asList(str));
      }
    } catch (Exception e) {
      result = new LinkedHashSet<String>();
    } finally {
      try {
        if (is != null)
          is.close();
      } catch (IOException e) {
      }
    }

    return result;
  }

  /**
   * Сохранение динамических архивов, включённых в библиотеку .MBAi
   *
   * @param project проект
   * @param lst     список имен архивов
   */
  public static void storeLibsInPropFile(IJavaProject project,
                                         List<String> lst) {
    OutputStream os = null;
    try {
      Properties properties = new Properties();
      properties.setProperty(INCLUDED_LIBS_PROPERTY_NAME, CoreUtils
          .stringListToString(lst, ","));
      IPath pth = project.getProject().getLocation();
      os = new FileOutputStream(pth + "/" + MBAI_PROPERTY_FILE_NAME);
      properties.store(os, MBAI_PROPERTY_FILE_COMMENT);

    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    } finally {
      try {
        os.close();
      } catch (IOException e) {
      }
    }
  }

  public void start(BundleContext context) throws Exception {
    super.start(context);
    initPluginPath();
    initLibs();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public String getContainerID() {
    return CONTAINER_ID;
  }
}
