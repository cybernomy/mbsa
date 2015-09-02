/**
 * Color.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;

/**
 * The <code>Color</code> class is used to encapsulate colors in the default
 * sRGB color space or colors in arbitrary color spaces identified by a
 * {@link java.awt.color.ColorSpace}.  Every color has an implicit alpha value of 255 or
 * an explicit one provided in the constructor.  The alpha value
 * defines the transparency of a color and can be represented by
 * a integer value in the range 0&nbsp;-&nbsp;255.
 * An alpha value of 255 means that the color is completely
 * opaque and an alpha value of 0 means that the color is 
 * completely transparent.
 * When constructing a <code>Color</code> with an explicit alpha or
 * getting the color/alpha components of a <code>Color</code>, the color
 * components are never premultiplied by the alpha component.
 * <p>
 * The default color space for the Java 2D(tm) API is sRGB, a proposed
 * standard RGB color space.  For further information on sRGB,
 * see <A href="http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html">
 * http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html
 * </A>.
 * <p>
 * @author Oleg V. Safonov
 * @version $Id: Color.java,v 1.1 2008/07/24 15:12:25 safonov Exp $
 */
public class Color implements Serializable {
	/**
	 * The color white.  In the default sRGB space.
	 */
	public final static Color WHITE     = new Color(255, 255, 255);

	/**
	 * The color light gray.  In the default sRGB space.
	 */
	public final static Color LIGHT_GRAY = new Color(192, 192, 192);

	/**
	 * The color gray.  In the default sRGB space.
	 */
	public final static Color GRAY      = new Color(128, 128, 128);

	/**
	 * The color dark gray.  In the default sRGB space.
	 */
	public final static Color DARK_GRAY  = new Color(64, 64, 64);

	/**
	 * The color black.  In the default sRGB space.
	 */
	public final static Color BLACK 	= new Color(0, 0, 0);


	/**
	 * The color red.  In the default sRGB space.
	 */
	public final static Color RED       = new Color(255, 0, 0);

	/**
	 * The color pink.  In the default sRGB space.
	 */
	public final static Color PINK      = new Color(255, 175, 175);

	/**
	 * The color orange.  In the default sRGB space.
	 */
	public final static Color ORANGE 	= new Color(255, 200, 0);

	/**
	 * The color yellow.  In the default sRGB space.
	 */
	public final static Color YELLOW 	= new Color(255, 255, 0);

	/**
	 * The color green.  In the default sRGB space.
	 */
	public final static Color GREEN 	= new Color(0, 255, 0);

	/**
	 * The color magenta.  In the default sRGB space.
	 */
	public final static Color MAGENTA	= new Color(255, 0, 255);

	/**
	 * The color cyan.  In the default sRGB space.
	 */
	public final static Color CYAN 	= new Color(0, 255, 255);

	/**
	 * The color blue.  In the default sRGB space.
	 */
	public final static Color BLUE 	= new Color(0, 0, 255);
	
	/**
	 * The color value.
	 * @serial
	 * @see #getRGB
	 */
	int value;

	/**
	 * Creates an opaque sRGB color with the specified red, green, 
	 * and blue values in the range (0 - 255).  
	 * The actual color used in rendering depends
	 * on finding the best match given the color space 
	 * available for a given output device.  
	 * Alpha is defaulted to 255.
	 *
	 * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
	 *        or <code>b</code> are outside of the range
	 *        0 to 255, inclusive
	 * @param r the red component
	 * @param g the green component
	 * @param b the blue component
	 * @see #getRed
	 * @see #getGreen
	 * @see #getBlue
	 * @see #getRGB
	 */
	public Color(int blue, int green, int red) {
		this(blue, green, red, 255);
	}

	/**
	 * Creates an sRGB color with the specified red, green, blue, and alpha
	 * values in the range (0 - 255).
	 *
	 * @throws IllegalArgumentException if <code>r</code>, <code>g</code>,
	 *        <code>b</code> or <code>a</code> are outside of the range
	 *        0 to 255, inclusive
	 * @param r the red component
	 * @param g the green component
	 * @param b the blue component
	 * @param a the alpha component
	 * @see #getRed
	 * @see #getGreen
	 * @see #getBlue
	 * @see #getAlpha
	 * @see #getRGB
	 */
	public Color(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
        testColorValueRange(r,g,b,a);
	}

	/**
	 * Creates an opaque sRGB color with the specified combined RGB value
	 * consisting of the red component in bits 16-23, the green component
	 * in bits 8-15, and the blue component in bits 0-7.  The actual color
	 * used in rendering depends on finding the best match given the
	 * color space available for a particular output device.  Alpha is
	 * defaulted to 255.
	 *
	 * @param rgb the combined RGB components
	 * @see java.awt.image.ColorModel#getRGBdefault
	 * @see #getRed
	 * @see #getGreen
	 * @see #getBlue
	 * @see #getRGB
	 */
	public Color(int rgb) {
		value = 0xff000000 | rgb;
	}

	/**
	 * Checks the color integer components supplied for validity.
	 * Throws an {@link IllegalArgumentException} if the value is out of
	 * range.
	 * @param r the Red component
	 * @param g the Green component
	 * @param b the Blue component
	 **/
	private static void testColorValueRange(int r, int g, int b, int a) {
		boolean rangeError = false;
		String badComponentString = "";

		if ( a < 0 || a > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Alpha";
		}
		if ( r < 0 || r > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Red";
		}
		if ( g < 0 || g > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Green";
		}
		if ( b < 0 || b > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Blue";
		}
		if ( rangeError == true ) {
			throw new IllegalArgumentException("Color parameter outside of expected range:"
					+ badComponentString);
		}
	}

    /**
     * Returns the red component in the range 0-255 in the default sRGB
     * space.
     * @return the red component.
     * @see #getRGB
     */
	public int getRed() {
		return (getRGB() >> 16) & 0xFF;
	}

    /**
     * Returns the green component in the range 0-255 in the default sRGB
     * space.
     * @return the green component.
     * @see #getRGB
     */
	public int getGreen() {
		return (getRGB() >> 8) & 0xFF;
	}

	/**
	 * Returns the blue component in the range 0-255 in the default sRGB
	 * space.
	 * @return the blue component.
	 * @see #getRGB
	 */
	public int getBlue() {
		return (getRGB() >> 0) & 0xFF;
	}

	/**
	 * Returns the alpha component in the range 0-255.
	 * @return the alpha component.
	 * @see #getRGB
	 */
	public int getAlpha() {
		return (getRGB() >> 24) & 0xff;
	}

    /**
     * Returns the RGB value representing the color in the default sRGB
     * {@link java.awt.image.ColorModel}.
     * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
     * blue).
     * @return the RGB value of the color in the default sRGB
     *         <code>ColorModel</code>.
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public int getRGB() {
    	return value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return value;
	}

}
