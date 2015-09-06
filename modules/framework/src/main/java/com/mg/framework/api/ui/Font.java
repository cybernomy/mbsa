/**
 * Font.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api.ui;

import java.io.Serializable;

/**
 * The <code>Font</code> class is used to define fonts to be used for components. For a
 * <code>Font</code> definition, the font name, font style and font size have to be specified.
 *
 * @author Oleg V. Safonov
 * @version $Id: Font.java,v 1.1 2008/07/24 15:12:25 safonov Exp $
 */
public class Font implements Serializable {
  /**
   * The plain style constant.
   */
  public static final int PLAIN = 0;

  /**
   * The bold style constant.  This can be combined with the other style constants (except PLAIN)
   * for mixed styles.
   */
  public static final int BOLD = 1;

  /**
   * The italicized style constant.  This can be combined with the other style constants (except
   * PLAIN) for mixed styles.
   */
  public static final int ITALIC = 2;

  /**
   * The logical name of this <code>Font</code>, as passed to the constructor.
   *
   * @serial
   * @see #getName
   */
  protected String name;

  /**
   * The style of this <code>Font</code>, as passed to the constructor. This style can be PLAIN,
   * BOLD, ITALIC, or BOLD+ITALIC.
   *
   * @serial
   * @see #getStyle()
   */
  protected int style;

  /**
   * The point size of this <code>Font</code>, rounded to integer.
   *
   * @serial
   * @see #getSize()
   */
  protected int size;

  public Font(String name, int style, int size) {
    this.name = (name != null) ? name : "Default";
    this.style = (style & ~0x03) == 0 ? style : 0;
    this.size = size;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the style
   */
  public int getStyle() {
    return style;
  }

  /**
   * @return the size
   */
  public int getSize() {
    return size;
  }

  /**
   * Indicates whether or not this <code>Font</code> object's style is PLAIN.
   *
   * @return <code>true</code> if this <code>Font</code> has a PLAIN sytle; <code>false</code>
   * otherwise.
   * @see java.awt.Font#getStyle
   */
  public boolean isPlain() {
    return style == 0;
  }

  /**
   * Indicates whether or not this <code>Font</code> object's style is BOLD.
   *
   * @return <code>true</code> if this <code>Font</code> object's style is BOLD; <code>false</code>
   * otherwise.
   * @see java.awt.Font#getStyle
   */
  public boolean isBold() {
    return (style & BOLD) != 0;
  }

  /**
   * Indicates whether or not this <code>Font</code> object's style is ITALIC.
   *
   * @return <code>true</code> if this <code>Font</code> object's style is ITALIC;
   * <code>false</code> otherwise.
   * @see java.awt.Font#getStyle
   */
  public boolean isItalic() {
    return (style & ITALIC) != 0;
  }

}
