/*
 * Rectangle.java
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
package com.mg.framework.support.ui.widget.graph;

/**
 * @author Oleg V. Safonov
 * @version $Id: Rectangle.java,v 1.1 2006/03/27 10:43:34 safonov Exp $
 */
public class Rectangle extends Vertex {
  private int x;
  private int y;
  private int width;
  private int height;
  private String label;

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
  }

  /**
   * @return Returns the height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * @param height The height to set.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * @return Returns the label.
   */
  public String getLabel() {
    return label;
  }

  /**
   * @param label The label to set.
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * @return Returns the width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * @param width The width to set.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * @return Returns the x.
   */
  public int getX() {
    return x;
  }

  /**
   * @param x The x to set.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * @return Returns the y.
   */
  public int getY() {
    return y;
  }

  /**
   * @param y The y to set.
   */
  public void setY(int y) {
    this.y = y;
  }

}
